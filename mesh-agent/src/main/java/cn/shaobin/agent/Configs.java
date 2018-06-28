package cn.shaobin.agent;

import cn.shaobin.agent.consumer.*;
import cn.shaobin.agent.provider.ProviderServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.shaobin.agent.registry.EndPointsMonitor;

@Configuration
public class Configs {

	@Bean
	public ConsumerService getConsumerService() {
		return new ConsumerService();
	}
	
	@Bean
	public EndPointsMonitor getEndpointsMonitor() {
		return new EndPointsMonitor();
	}
	
	@Bean
	public ConsumerClient getConsumerClient() throws Exception {
		return new ConsumerClient();
	}

	@Bean
	public Server getServerRunner(){
		String type = System.getProperty("type");
		if ("consumer".equals(type)) {
			return new ConsumerServer();
		} else if ("provider".equals(type)) {
			return new ProviderServer();
		} else {
			throw new RuntimeException("Environment variable type is needed to set to provider or consumer.");
		}
	}


}
