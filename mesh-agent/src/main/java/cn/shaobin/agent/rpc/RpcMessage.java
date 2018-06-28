package cn.shaobin.agent.rpc;

public class RpcMessage {
	
	public static final byte FLAG_REQUEST= 0;
	public static final byte FLAG_RESPONSE = 1;
	
	public static final byte STATUS_REQUEST = 0x00;
	public static final byte STATUS_RESPONSE_OK = 0x01;
	public static final byte STATUS_RESPONSE_EXCEPTION = 0x02;
	
	public static final byte STATUS_LATESET_VERSION = 0x01;

	private long seqId = 0;
	private byte flag = 0;
	private byte version = STATUS_LATESET_VERSION;
	private byte statusCode = 0;
	private byte[] data;
	private int dataLength = -1;

	public long getSeqId() {
		return seqId;
	}

	public void setSeqId(long seqId) {
		this.seqId = seqId;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public byte getVersion() {
		return version;
	}

	public void setVersion(byte version) {
		this.version = version;
	}

	public byte getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(byte statusCode) {
		this.statusCode = statusCode;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
		this.dataLength = data.length;
	}

	public int getDataLength() {
		if(this.dataLength==-1 && this.data!=null) {
			return this.data.length;
		}
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

}
