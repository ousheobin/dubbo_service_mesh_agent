package cn.shaobin.agent.rpc;

import java.util.Arrays;
import java.util.List;

import cn.shaobin.agent.utils.Bytes;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class RpcDecoder extends ByteToMessageDecoder {

	protected static final int TOTAL_HEAD_LENGTH = 16; // 协议首部 16字节
	protected static final byte HEADER_VALIDATE_HIGH = 0x05; // 高位校验码
	protected static final byte HEADER_VALIDATE_LOW = 0x08; // 低位校验码

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
		try {
			do {
				int savedReaderIndex = byteBuf.readerIndex();
				Object msg = null;
				try {
					msg = handleDecode(byteBuf);
				} catch (Exception e) {
					throw e;
				}
				if (msg == Result.NOT_FINISH) {
					byteBuf.readerIndex(savedReaderIndex);
					break;
				}

				out.add(msg);
			} while (byteBuf.isReadable());
		} finally {
			if (byteBuf.isReadable()) {
				byteBuf.discardReadBytes();
			}
		}
	}

	private enum Result {
		NOT_FINISH
	}

	private Object handleDecode(ByteBuf byteBuf) throws RpcException {
		int beginIndex = byteBuf.readerIndex();
		int readableBytes = byteBuf.readableBytes();

		if (readableBytes < TOTAL_HEAD_LENGTH) {
			return Result.NOT_FINISH;
		}

		byte[] headers = new byte[TOTAL_HEAD_LENGTH];
		byteBuf.readBytes(headers);
		if (headers[0] != HEADER_VALIDATE_HIGH || headers[1] != HEADER_VALIDATE_LOW) {
			// 头部校验不通过
			throw new RpcException("Header Validate Failed: Not matched vealidat high or validate low");
		}
		int length = Bytes.bytes2int(Arrays.copyOfRange(headers, 4, 8), 0);
		int totalLength = length + TOTAL_HEAD_LENGTH;
		
		byteBuf.readerIndex(beginIndex);
		if(byteBuf.readableBytes() < totalLength) {
			return Result.NOT_FINISH;
		}

		byte[] data = new byte[totalLength];
		byteBuf.readBytes(data);

		long seqId = Bytes.bytes2long(Arrays.copyOfRange(headers, 8, 16), 0);

		RpcMessage message = new RpcMessage();
		message.setDataLength(length);
		message.setFlag(getFlag(data[2]));
		message.setVersion(getVersion(data[2]));
		message.setSeqId(seqId);
		message.setStatusCode(data[3]);
		message.setData(Arrays.copyOfRange(data, TOTAL_HEAD_LENGTH, data.length));
		return message;
	}

	public byte getFlag(byte content) {
		return (byte) (content & 0x80);
	}

	public byte getVersion(byte content) {
		return (byte) (content & 0x7f);
	}

}
