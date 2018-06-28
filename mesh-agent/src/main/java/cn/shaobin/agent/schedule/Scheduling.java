package cn.shaobin.agent.schedule;

import cn.shaobin.agent.registry.Endpoint;

public interface Scheduling {

	/**
	 * 初始化调度器
	 * @throws Exception
	 */
	void initialization() throws Exception;

	/**
	 * 获取负载均衡的Endpoint
	 * @return 终端类
	 * @throws Exception
	 */
	Endpoint getEndpoint() throws Exception;
	
	/**
	 * 重新计算gcdWeight和maxWeight
	 */
	public void recalParams();
	

}