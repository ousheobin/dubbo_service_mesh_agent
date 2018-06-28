package cn.shaobin.agent.consumer;

import cn.shaobin.agent.Server;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsumerServer implements Server {

    private EventLoopGroup boss = new NioEventLoopGroup(1, new DefaultThreadFactory("ConsumerServerBoss", true));
    private EventLoopGroup worker = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors()*2, new DefaultThreadFactory("ConsumerverWorker", true));

    @Autowired
    ConsumerService consumerService;

    @Override
    public void startUp() {
        try {
            ChannelFuture future =  startServer();
        }catch (Exception ex){

        }
    }

    private ChannelFuture startServer() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 获取管道
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        // 字符串解码器
                        pipeline.addLast(new HttpServerCodec())
                                .addLast(new HttpObjectAggregator(65536))
                                .addLast(new ChunkedWriteHandler())
                                .addLast(new AgentController(consumerService));
                    }
                });
        bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childOption(ChannelOption.TCP_NODELAY,true)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        int port = Integer.valueOf(System.getProperty("server.port"));
        ChannelFuture future =  bootstrap.bind(port).sync();
        return future;
    }
}
