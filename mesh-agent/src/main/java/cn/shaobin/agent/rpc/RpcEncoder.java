package cn.shaobin.agent.rpc;

import cn.shaobin.agent.utils.Bytes;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class RpcEncoder extends MessageToByteEncoder<RpcMessage> {

	protected static final int TOTAL_HEAD_LENGTH = 16; // 协议首部 16字节
	protected static final byte HEADER_VALIDATE_HIGH = 0x05; // 高位校验码
	protected static final byte HEADER_VALIDATE_LOW = 0x08; // 低位校验码

	@Override
	protected void encode(ChannelHandlerContext ctx, RpcMessage msg, ByteBuf buffer) throws Exception {
		if( msg != null && msg.getData()!=null) {
			byte[] header = new byte[TOTAL_HEAD_LENGTH];
			header[0] = HEADER_VALIDATE_HIGH;
			header[1] = HEADER_VALIDATE_LOW;
			header[2] = (byte) ((msg.getFlag() << 7) + (msg.getVersion() & 0x7f));
			header[3] = msg.getStatusCode();
			Bytes.int2bytes(msg.getDataLength(), header, 4);
			Bytes.long2bytes(msg.getSeqId(), header, 8);
			buffer.writeBytes(header);
			buffer.writeBytes(msg.getData());
		}
	}

}
