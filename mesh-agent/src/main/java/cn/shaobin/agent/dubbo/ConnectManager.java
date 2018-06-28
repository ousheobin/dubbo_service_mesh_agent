package cn.shaobin.agent.dubbo;

import cn.shaobin.agent.provider.ProviderServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;

public class ConnectManager {
	private Bootstrap bootstrap;

	private Channel channel;
	private Object lock = new Object();
	private EventLoopGroup group;

	public ConnectManager() throws Exception {
		if( null == group){
			synchronized (lock) {
				if( null == group){
					group = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors()*2, new DefaultThreadFactory("NettyDubboClient", true));
				}
			}
		}
		if (null == bootstrap) {
			synchronized (lock) {
				if (null == bootstrap) {
					initBootstrap();
				}
			}
		}
	}

	public Channel getChannel() throws Exception {
		if (null == channel || !channel.isOpen() || !channel.isActive()) {
			synchronized (lock) {
				if (null == channel || !channel.isOpen() || !channel.isActive()) {
					int port = Integer.valueOf(System.getProperty("dubbo.protocol.port"));
					channel = bootstrap.connect("127.0.0.1", port).sync().channel();
				}
			}
		}
		return channel;
	}

	public void initBootstrap() {
		bootstrap = new Bootstrap().group(group)
				.option(ChannelOption.SO_KEEPALIVE, true)
				.option(ChannelOption.TCP_NODELAY, true)
				.option(ChannelOption.ALLOCATOR, UnpooledByteBufAllocator.DEFAULT)
				.channel(NioSocketChannel.class)
				.handler(new DubboRpcClientInitializer());
	}
}
