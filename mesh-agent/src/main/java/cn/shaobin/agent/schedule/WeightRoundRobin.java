package cn.shaobin.agent.schedule;

import java.net.InetAddress;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.shaobin.agent.concurrent.ThreadPoolService;
import cn.shaobin.agent.registry.Endpoint;

/**
 * Scheduling 调度器的一个实现方法 Auto Weighted Round Robin
 * <p>
 * <strong>Weighted round robin (WRR)</strong> is a network scheduling
 * discipline.
 * </p>
 * 
 * @author Ou Sheobin - me@oushaobin.cn
 */
public class WeightRoundRobin implements Scheduling {

	private boolean isInit = false;
	public List<Endpoint> endpoints;
	public AtomicReference<Integer> currentWeight = new AtomicReference<Integer>(0);
	public AtomicReference<Integer> serverIndex =  new AtomicReference<Integer>(-1);
	public AtomicReference<Integer> maxWeight = new AtomicReference<Integer>(0);
	public AtomicReference<Integer> gcdWeight = new AtomicReference<Integer>(0);

	public InetAddress smallAddr;
	public InetAddress mediumAddr;
	public InetAddress largeAddr;

	private Logger logger = LoggerFactory.getLogger(WeightRoundRobin.class);

	public WeightRoundRobin(List<Endpoint> servers) {
		try{
			smallAddr = InetAddress.getByName("provider-small");
			mediumAddr = InetAddress.getByName("provider-medium");
			largeAddr = InetAddress.getByName("provider-large");
		}catch (Exception ex){

		}

		try {
			this.endpoints = servers;
			initialization();
		} catch (Exception e) {
			logger.error("Exception:", e);
		}
	}

	@Override
	public void initialization() throws Exception {
		if (!isInit) {
			for (Endpoint endpoint : endpoints){
				InetAddress address = endpoint.getInetSocketAddress().getAddress();
				if(smallAddr != null &&  address.equals(smallAddr)){
					endpoint.setWeight(1);
				}else if(mediumAddr != null && address.equals(mediumAddr)){
					endpoint.setWeight(2);
				}else if(largeAddr != null &&  address.equals(largeAddr)){
					endpoint.setWeight(3);
				}else {
					endpoint.setWeight(1);
				}
			}
			maxWeight.set(max(endpoints));
			gcdWeight.set(gcd(endpoints));
			logger.info( "Finish WWR init, maxWeight=" + maxWeight + ",gcdWeight=" + gcdWeight + ",endpoints" + endpoints);
//			ThreadPoolService.submit(() -> {
//				while (true) {
//					try {
//						Thread.sleep(10 * 1000);
//						this.recalParams();
//					} catch (Exception e) {
//						logger.error("Catch Exception while recall factors",e);
//					}
//				}
//			});
		} else {
			throw new Exception("Scheduling has already started.");
		}
	}

	/**
	 * 重新计算gcdWeight和maxWeight
	 */
	public void recalParams() {
		for (int i = 0; i < endpoints.size(); i++) {
			Endpoint endpoint = endpoints.get(i);
			logger.info("Endpoint:"+endpoint.getHost()+":"+endpoint.getPort()+" qps:"+endpoint.getCountPreTenSec()/10);
			endpoint.setWeight(endpoint.getCountPreTenSec()/5000 + 1 );
			endpoint.resetCount();
		}
		maxWeight.set(max(endpoints));
		gcdWeight.set(gcd(endpoints));
		logger.info( "Finish factors recal, maxWeight=" + maxWeight + ",gcdWeight=" + gcdWeight + ",endpoints" + endpoints);
	}

	@Override
	public Endpoint getEndpoint() throws Exception {
		if(endpoints.size()==0) {
			return null;
		}
		int currentIndex = serverIndex.get();
		int weight = currentWeight.get();
		int currentGcdWeight= gcdWeight.get();
		int currentMaxWeight = maxWeight.get();
		while (true) {
			currentIndex = (currentIndex + 1) % endpoints.size();
			if (currentIndex == 0) {
				weight = weight - currentGcdWeight;
				if (weight < 0) {
					weight = currentMaxWeight;
					if (weight == 0) {
						currentWeight.set(weight);
						serverIndex.set(currentIndex);
						return null;
					}
				}
			}
			if (endpoints.get(currentIndex).getWeight() >= weight) {
				currentWeight.set(weight);
				serverIndex.set(currentIndex);
				return endpoints.get(currentIndex);
			}
		}
	}

	/**
	 * GCD方法 用于计算服务器权值的最大公约数 以作为服务器降低权值的步长
	 * 
	 * @param servers
	 * @return
	 */
	public int gcd(List<Endpoint> servers) {
		
		if(servers.isEmpty()) {
			return 0;
		}

		int min = servers.get(0).getWeight();

		for (int i = 0; i < servers.size(); i++) {
			if (servers.get(i).getWeight() < min) {
				min = servers.get(i).getWeight();
			}
		}
		while (min >= 1) {
			boolean isCommon = true;
			for (int i = 0; i < servers.size(); i++) {
				if (servers.get(i).getWeight() % min != 0) {
					isCommon = false;
					break;
				}
			}
			if (isCommon) {
				break;
			}
			min--;
		}
		return min;
	}

	/**
	 * max 最大权值搜索方法
	 * 
	 * @param servers
	 *            Server列表
	 * @return 最大的权值
	 */
	public int max(List<Endpoint> servers) {
		int max = 0;
		for (int i = 0; i < servers.size(); i++) {
			if (max < servers.get(i).getWeight()) {
				max = servers.get(i).getWeight();
			}
		}
		return max;
	}

}