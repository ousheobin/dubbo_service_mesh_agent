package cn.shaobin.agent.dubbo;

import cn.shaobin.agent.utils.JsonUtils;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DubboRpcClient {
    public static final AttributeKey<Map<Long,Long>> SEQ_MAP_KEY = AttributeKey.valueOf("netty.seqmap");
    private Logger logger = LoggerFactory.getLogger(DubboRpcClient.class);

    private ConnectManager connectManager;

    public DubboRpcClient(){
        try {
			this.connectManager = new ConnectManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void invoke(String method, String interfaceName, String parameterTypesString, String parameter,
                       long consumerSeq, Channel consumerChannel) throws Exception {

        Channel channel = connectManager.getChannel();

        DubboRpcInvocation invocation = new DubboRpcInvocation();
        invocation.setMethodName(method);
        invocation.setAttachment("path", interfaceName);
        invocation.setParameterTypes(parameterTypesString);    // Dubbo内部用"Ljava/lang/String"来表示参数类型是String

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(out));
        JsonUtils.writeObject(parameter, writer);
        invocation.setArguments(out.toByteArray());

        DubboRequest request = new DubboRequest();
        request.setVersion("2.0.0");
        request.setTwoWay(true);
        request.setData(invocation);

        logger.info("requestId=" + request.getId());

        Attribute<Map<Long,Long>> attr = consumerChannel.attr(SEQ_MAP_KEY);
        Map<Long,Long> map = attr.get();
        if(map == null){
            attr.setIfAbsent(new ConcurrentHashMap<>());
            map = attr.get();
        }
        long id = request.getId();
        map.put(id,consumerSeq);
        DubboRpcRequestHolder.put(id,consumerChannel);

        channel.writeAndFlush(request);
    }
}
