package cn.shaobin.agent.consumer;


import cn.shaobin.agent.rpc.RpcMessage;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.AttributeKey;

import java.util.Map;

public class ConsumerClientHandler extends SimpleChannelInboundHandler<RpcMessage> {

	public static final AttributeKey<Map<Long,Channel>> CHANNEL_MAP_KEY = AttributeKey.valueOf("netty.channelmap");

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RpcMessage msg) throws Exception {
		Map<Long,Channel> channelMap = ctx.channel().attr(CHANNEL_MAP_KEY).get();
		if(channelMap!=null){
			Channel channel = channelMap.get(msg.getSeqId());
			channelMap.remove(msg.getSeqId());
			if(channel != null){
				channel.eventLoop().execute(()->{
					FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
							HttpResponseStatus.OK, Unpooled.wrappedBuffer(msg.getData()));
					response.headers().set("Content-Type", "text/plain");
					response.headers().setInt("Content-Length", response.content().readableBytes());
					channel.writeAndFlush(response);
				});
			}
		}
	}
}
