package cn.shaobin.agent.rpc;

public class RpcException extends Exception {

	private static final long serialVersionUID = -5575498466109396182L;
	
	RpcException(String content){
		super(content);
	}

}
