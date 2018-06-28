package cn.shaobin.agent.dubbo;

import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;

public class DubboRpcRequestHolder {

    // key: requestId     value: Channel
    private static ConcurrentHashMap<Long,Channel> processingRpc = new ConcurrentHashMap<>();

    public static void put(long requestId,Channel channel){
        processingRpc.put(requestId,channel);
    }

    public static Channel get(long requestId){
        return processingRpc.get(requestId);
    }

    public static void remove(long requestId){
        processingRpc.remove(requestId);
    }
}
