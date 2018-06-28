package cn.shaobin.agent.provider;

import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.shaobin.agent.rpc.RpcMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;

public class RpcServerHandler extends SimpleChannelInboundHandler<RpcMessage> {
	
	private static Logger logger = LoggerFactory.getLogger(RpcServerHandler.class);

	public static final AttributeKey<Map<Long,Long>> SEQ_MAP_KEY = AttributeKey.valueOf("netty.seqmap");

	private static ProviderService providerService;
	private static Object providerServiceLock = new Object();

	static {
		if (providerService == null) {
			synchronized (providerServiceLock) {
				if (providerService == null) {
					providerService = new ProviderService();
				}
			}
		}
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RpcMessage msg) throws Exception {
		if( msg.getFlag() == RpcMessage.FLAG_REQUEST && msg.getData()!= null && msg.getData().length > 2) {
			Channel channel = ctx.channel();
			channel.eventLoop().execute(()->{
				providerService.invoke(msg.getData(),channel,msg.getSeqId());
			});
		}
	}

}
