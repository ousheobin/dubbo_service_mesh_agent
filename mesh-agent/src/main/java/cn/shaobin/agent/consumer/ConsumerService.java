package cn.shaobin.agent.consumer;

import cn.shaobin.agent.rpc.RpcContentProtos;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;
import cn.shaobin.agent.registry.EndPointsMonitor;
import cn.shaobin.agent.registry.Endpoint;
import cn.shaobin.agent.rpc.RpcMessage;

import java.util.Map;

public class ConsumerService {

	@Autowired
	private EndPointsMonitor endpointsMonitor;
	
	@Autowired
	private ConsumerClient consumerClient;

	public void invoke(Map<String,Object> parameters, Channel clientChannel) throws Exception {
		Endpoint endpoint = endpointsMonitor.getEndpoint("com.alibaba.dubbo.performance.demo.provider.IHelloService");
		byte[] data = RpcContentProtos.getContent(parameters).toByteArray();
		RpcMessage request = new RpcMessage();
		request.setData(data);
		request.setDataLength(data.length);
		request.setFlag(RpcMessage.FLAG_REQUEST);
		request.setStatusCode(RpcMessage.STATUS_REQUEST);
		request.setVersion(RpcMessage.STATUS_LATESET_VERSION);
		consumerClient.sendToProvider(request,endpoint,clientChannel);
	}
	
}