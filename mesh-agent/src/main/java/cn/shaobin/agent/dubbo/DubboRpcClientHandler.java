package cn.shaobin.agent.dubbo;

import cn.shaobin.agent.provider.RpcServerHandler;
import cn.shaobin.agent.rpc.RpcMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;

public class DubboRpcClientHandler extends SimpleChannelInboundHandler<DubboRpcResponse> {

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, DubboRpcResponse response) {
		Channel channel = DubboRpcRequestHolder.get(response.getRequestId());
		channel.eventLoop().execute(()->{
			Map<Long,Long> idMap = channel.attr(RpcServerHandler.SEQ_MAP_KEY).get();
			RpcMessage resultMessage = new RpcMessage();
			byte[] res = response.getBytes();
			resultMessage.setData(response.getBytes());
			resultMessage.setFlag(RpcMessage.FLAG_RESPONSE);
			resultMessage.setSeqId(idMap.get(response.getRequestId()));
			resultMessage.setStatusCode(RpcMessage.STATUS_RESPONSE_OK);
			resultMessage.setDataLength(res.length);
			resultMessage.setVersion(RpcMessage.STATUS_LATESET_VERSION);
			channel.writeAndFlush(resultMessage);
		});
	}
}
