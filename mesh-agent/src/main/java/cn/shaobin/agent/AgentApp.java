package cn.shaobin.agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.shaobin.agent.provider.ProviderServer;

@SpringBootApplication
public class AgentApp {

	/*
	 * Consumer Agent 启动参数 -XX:+PrintGCDetails -Djava.net.preferIPv4Stack=true
	 * -Dtype=consumer -Dserver.port=20000 -Detcd.url=http://localhost:2379
	 * -Dlogs.dir=/Users/ousheobin/workspace/logs
	 * 
	 * Provider Agent 启动参数 -Xms512M -Xmx512M -Djava.net.preferIPv4Stack=true
	 * -Dtype=provider -Ddubbo.protocol.port=20880 -Dserver.port=30000
	 * -Detcd.url=http://localhost:2379 -Dlogs.dir=/Users/ousheobin/workspace/logs
	 */

	public static void main(String[] args) {
		SpringApplication.run(AgentApp.class, args);
	}
}
