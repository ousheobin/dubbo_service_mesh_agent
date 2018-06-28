package cn.shaobin.agent.provider;

import cn.shaobin.agent.dubbo.DubboRpcClient;
import cn.shaobin.agent.rpc.RpcContentProtos;
import io.netty.channel.Channel;

import java.util.Map;

public class ProviderService {
	
    private DubboRpcClient rpcClient;
    
    public ProviderService() {
    	this.rpcClient = new DubboRpcClient();
    }
    
    public void invoke(byte[] data, Channel clientChannel, long consumerId) {
    	try {
			RpcContentProtos.RpcContent content = RpcContentProtos.RpcContent.parseFrom(data);
			rpcClient.invoke(content.getMethod(),content.getInterface(),content.getParameterTypesString(),
					content.getParameter(),consumerId,clientChannel);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}