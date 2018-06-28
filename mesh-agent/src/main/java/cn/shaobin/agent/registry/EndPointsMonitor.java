package cn.shaobin.agent.registry;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import cn.shaobin.agent.schedule.Scheduling;
import cn.shaobin.agent.schedule.WeightRoundRobin;

public class EndPointsMonitor {

	private IRegistry registry = new EtcdRegistry(System.getProperty("etcd.url"));
	
	private Object lock = new Object();

	private ConcurrentHashMap<String, Scheduling> operations = new ConcurrentHashMap<String, Scheduling>();

	public Endpoint getEndpoint(String service) throws Exception {
		if(!operations.containsKey(service)) {
			synchronized(lock){
				if(!operations.containsKey(service)) {
					List<Endpoint> endpoints = registry.find(service);
					Scheduling sheduler = new WeightRoundRobin(endpoints);
					operations.put(service, sheduler);
				}
			}
		}
		Endpoint endpoint = operations.get(service).getEndpoint();
		return endpoint;
	}

}
