package cn.shaobin.agent.concurrent;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolService {
	
	private static ExecutorService threadPool;
	
	static {
		threadPool = Executors.newCachedThreadPool(
				new DefaultThreadFactory("gobal-thread-pool", true));
	}
	
	public static void execute(Runnable command) {
		threadPool.execute(command);
	}
	
	public static void submit(Runnable command) {
		threadPool.submit(command);
	}

}