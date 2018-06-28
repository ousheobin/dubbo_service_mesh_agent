package cn.shaobin.agent.registry;

import com.coreos.jetcd.Client;
import com.coreos.jetcd.KV;
import com.coreos.jetcd.Lease;
import com.coreos.jetcd.Watch;
import com.coreos.jetcd.data.ByteSequence;
import com.coreos.jetcd.kv.GetResponse;
import com.coreos.jetcd.options.GetOption;
import com.coreos.jetcd.options.PutOption;
import com.coreos.jetcd.options.WatchOption;
import com.coreos.jetcd.watch.WatchEvent;
import com.coreos.jetcd.watch.WatchResponse;

import cn.shaobin.agent.concurrent.ThreadPoolService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EtcdRegistry implements IRegistry {
	
	private Logger logger = LoggerFactory.getLogger(EtcdRegistry.class);

	private final String rootPath = "dubbomesh";
	private Lease lease;
	private KV kv;
	private Watch watch;
	private long leaseId;
	private Map<String,List<Endpoint>> enpointMap;
	private Object pullLock = new Object();

	public EtcdRegistry(String registryAddress) {
		Client client = Client.builder().endpoints(registryAddress).build();
		this.watch = client.getWatchClient();
		this.lease = client.getLeaseClient();
		this.kv = client.getKVClient();
		this.enpointMap = new ConcurrentHashMap<String,List<Endpoint>>();
		try {
			this.leaseId = lease.grant(30).get().getID();
		} catch (Exception e) {
			logger.error("Catch exception while get leaseId.",e);
		}

		keepAlive();
	}

	public void register(String serviceName, int port) throws Exception {
		String strKey = MessageFormat.format("/{0}/{1}/{2}:{3}", rootPath, serviceName, IpHelper.getHostIp(),
				String.valueOf(port));
		ByteSequence key = ByteSequence.fromString(strKey);
		ByteSequence val = ByteSequence.fromString("");
		kv.put(key, val, PutOption.newBuilder().withLeaseId(leaseId).build()).get();
		logger.info("Register a new service at:" + strKey);
	}

	public void keepAlive() {
		ThreadPoolService.execute(() -> {
			try {
				Lease.KeepAliveListener listener = lease.keepAlive(leaseId);
				listener.listen();
				logger.info("KeepAlive lease:" + leaseId + "; Hex format:" + Long.toHexString(leaseId));
			} catch (Exception e) {
				logger.error("Catch Exception while sending keep alive message to the etcd service. ",e);
			}
		});
	}
	
	public void addWatcher(ByteSequence key) {
		ThreadPoolService.submit(()->{
			String strKey = key.toStringUtf8();
			Thread.currentThread().setName("ETCD Watcher-"+strKey);
			logger.info("Add watcher:"+ strKey);
			while(true) {
				try {
					WatchResponse watchResponse = this.watch.watch(key,WatchOption.newBuilder().withPrefix(key).build()).listen();
					if(!watchResponse.getEvents().isEmpty()) {
						for(WatchEvent event:watchResponse.getEvents()) {
							String eventKey = event.getKeyValue().getKey().toStringUtf8();
							int index = eventKey.lastIndexOf("/");
							String endpointStr = eventKey.substring(index + 1, eventKey.length());

							String host = endpointStr.split(":")[0];
							int port = Integer.valueOf(endpointStr.split(":")[1]);
							
							if(event.getEventType().equals(WatchEvent.EventType.DELETE)){
								Iterator<Endpoint> endpointIter = enpointMap.get(strKey).iterator();
								while(endpointIter.hasNext()) {
									Endpoint endpoint = endpointIter.next();
									if( endpoint.getHost().equals(host) && endpoint.getPort() == port) {
										logger.info("Remove Endpoint: "+endpoint);
										endpointIter.remove();
										break;
									}
								}
							}else if(event.getEventType().equals(WatchEvent.EventType.PUT)) {
								logger.info("Add Endpoint: "+ eventKey);
								enpointMap.get(strKey).add(new Endpoint(host, port));
							}
						}
					}
				} catch (InterruptedException e) {
					logger.error("Wathcer Error: ",e);
				}
			}
		});
	}
	
	private void pullEndpoints(ByteSequence key) throws Exception {
		String keyStr = key.toStringUtf8();
		
		if( !this.enpointMap.containsKey(keyStr)) {
			synchronized(pullLock){
				if( !this.enpointMap.containsKey(keyStr)) {
					logger.info("Try to add etcd cache");
					GetResponse response = kv.get(key, GetOption.newBuilder().withPrefix(key).build()).get();

					List<Endpoint> endpoints = new ArrayList<>();
					for (com.coreos.jetcd.data.KeyValue kv : response.getKvs()) {
						String s = kv.getKey().toStringUtf8();
						int index = s.lastIndexOf("/");
						String endpointStr = s.substring(index + 1, s.length());

						String host = endpointStr.split(":")[0];
						int port = Integer.valueOf(endpointStr.split(":")[1]);

						endpoints.add(new Endpoint(host, port));
					}
					logger.info("Pull Endpoints:"+endpoints);
					this.enpointMap.put(keyStr, endpoints);
//					this.addWatcher(key);
				}
			}
		}
	}

	public List<Endpoint> find(String serviceName) throws Exception {
		String strKey = MessageFormat.format("/{0}/{1}", rootPath, serviceName);
		ByteSequence key = ByteSequence.fromString(strKey);
		if(!enpointMap.containsKey(strKey)) {
			pullEndpoints(key);
		}
		return enpointMap.get(strKey);
	}
}
