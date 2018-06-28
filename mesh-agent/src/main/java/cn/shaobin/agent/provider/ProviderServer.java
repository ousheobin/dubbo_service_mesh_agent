package cn.shaobin.agent.provider;

import cn.shaobin.agent.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.shaobin.agent.rpc.RpcDecoder;
import cn.shaobin.agent.rpc.RpcEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;

public class ProviderServer implements Server {

	private EventLoopGroup boss = new NioEventLoopGroup(1, new DefaultThreadFactory("NettyServerBoss", true));
	private EventLoopGroup worker = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors()*2, new DefaultThreadFactory("NettyServerWorker", true));
	private Logger logger = LoggerFactory.getLogger(ProviderServer.class);

	public void startUp() {
		
		try {
			long beginTime = System.currentTimeMillis();
			logger.info("Dubbo Service Mesh Agent [Mode: Provider]");
			// Start the agent.
			logger.info("Start the provider agent.");
			ChannelFuture future =  startServer();
			// Regisiter The Server.
			logger.info("Regisiter to the etcd.");
			try {
				int port = Integer.valueOf(System.getProperty("server.port"));
				ProviderRegistryHolder.getRegisitry().register("com.alibaba.dubbo.performance.demo.provider.IHelloService", port);
			} catch (Exception e) {
				logger.error("Catch exception while regisiter a provider.",e);
				throw e;
			}
			logger.info("The provider server has started successfully in "+(System.currentTimeMillis()-beginTime)/1000+" senconds");
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			logger.error("Catch exception while start the agent:",e);
		} finally {
			boss.shutdownGracefully();
            worker.shutdownGracefully();
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
						pipeline.addLast(new RpcDecoder())
									 .addLast(new RpcEncoder())
								     .addLast(new RpcServerHandler());
					}
				});
		 bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
		 				 .childOption(ChannelOption.SO_KEEPALIVE,true)
		 				 .childOption(ChannelOption.TCP_NODELAY,true)
		 				 .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
		 int port = Integer.valueOf(System.getProperty("server.port"));
		 ChannelFuture future =  bootstrap.bind(port).sync();
		 logger.info("Agent has started and listened port:"+port);
		 return future;
	}

}
