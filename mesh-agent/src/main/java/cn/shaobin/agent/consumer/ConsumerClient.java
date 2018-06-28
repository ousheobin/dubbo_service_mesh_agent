package cn.shaobin.agent.consumer;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import cn.shaobin.agent.registry.Endpoint;
import cn.shaobin.agent.rpc.RpcDecoder;
import cn.shaobin.agent.rpc.RpcEncoder;
import cn.shaobin.agent.rpc.RpcMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsumerClient {
	
	private AtomicLong sequenceId;
	private Bootstrap bootstrap;
	private Object lock = new Object();
	private ConcurrentHashMap<InetSocketAddress,Channel> channelMap = new ConcurrentHashMap<InetSocketAddress,Channel>();

	@Autowired
	ConsumerService consumerService;

	public static final AttributeKey<Map<Long,Channel>> CHANNEL_MAP_KEY = AttributeKey.valueOf("netty.channelmap");


	public ConsumerClient() {
		sequenceId = new AtomicLong();
		bootstrap = new Bootstrap().group(new NioEventLoopGroup(Runtime.getRuntime().availableProcessors()*2,new DefaultThreadFactory("ConsumerClient", true)))
				.option(ChannelOption.SO_KEEPALIVE, true)
				.option(ChannelOption.TCP_NODELAY, true)
				.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
				.channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new RpcEncoder()).addLast(new RpcDecoder()).addLast(new ConsumerClientHandler());
					}
					
				});
	}
	
	public void sendToProvider(RpcMessage message , Endpoint endpoint, Channel clientChannel) throws InterruptedException {
		long seqId = sequenceId.getAndIncrement();
		InetSocketAddress address = endpoint.getInetSocketAddress();
		Channel channel = channelMap.get(address);
		if( channel == null ) {
			synchronized(lock) {
				if(!channelMap.containsKey(address)) {
					channel = bootstrap.connect(address).sync().channel();
					channelMap.put(address, channel);
				}else {
					channel = channelMap.get(address);
				}
			}
		}
		Attribute<Map<Long,Channel>> attr = channel.attr(CHANNEL_MAP_KEY);
		Map<Long,Channel> map = attr.get();
		if( map == null ){
			attr.setIfAbsent(new ConcurrentHashMap<Long,Channel>());
			map = attr.get();
		}
		message.setSeqId(seqId);
		map.put(seqId,clientChannel);
		channel.writeAndFlush(message);
	}

}