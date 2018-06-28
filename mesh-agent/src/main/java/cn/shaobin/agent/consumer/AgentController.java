package cn.shaobin.agent.consumer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class AgentController extends SimpleChannelInboundHandler<FullHttpRequest> {

    private Logger logger = LoggerFactory.getLogger(AgentController.class);

    ConsumerService consumerService;

    public AgentController(ConsumerService consumerService){
        this.consumerService = consumerService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        ByteBuf byteBuf = msg.content();
        byte[] content = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(content);
        ctx.channel().eventLoop().execute(()->{
            try{
                String contentStr = URLDecoder.decode(new String(content), "utf-8");
                String[] parameters = contentStr.split("&");
                Map<String,Object> paramMap = new HashMap<String,Object>();
                for(String parameter : parameters){
                    int eqIndex = parameter.indexOf("=");
                    if( eqIndex > 0 ){
                        paramMap.put(parameter.substring(0,eqIndex),parameter.substring(eqIndex+1));
                    }
                }
                consumerService.invoke(paramMap,ctx.channel());
            }catch (Exception ex){

            }
        });
    }

}