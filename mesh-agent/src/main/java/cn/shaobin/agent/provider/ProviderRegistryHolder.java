package cn.shaobin.agent.provider;

import cn.shaobin.agent.registry.EtcdRegistry;
import cn.shaobin.agent.registry.IRegistry;

public class ProviderRegistryHolder {
	
	private static volatile IRegistry regisitry;
	private static Object lock = new Object();
	
	public static IRegistry getRegisitry() {
		if(regisitry ==null) {
			synchronized(lock) {
				if(regisitry ==null) {
					regisitry = new EtcdRegistry(System.getProperty("etcd.url"));
				}
			}
		}
		return regisitry;
	}

}
