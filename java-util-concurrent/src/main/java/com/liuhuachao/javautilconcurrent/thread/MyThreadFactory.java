package com.liuhuachao.javautilconcurrent.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程工厂
 * @author liuhuachao
 * @date 2022/1/22
 */
public class MyThreadFactory implements ThreadFactory {

	private final ThreadGroup threadGroup;
	private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
	private final AtomicInteger threadNumber = new AtomicInteger(1);
	private final String namePrefix;
	private final Integer priority;
	private final boolean daemon;

	//region 构造函数

	public MyThreadFactory(){
		this("pool-" + POOL_NUMBER.getAndIncrement());
	}

	public MyThreadFactory(String namePrefix){
		this(namePrefix, Thread.NORM_PRIORITY, false);
	}

	public MyThreadFactory(String namePrefix, Integer priority){
		this(namePrefix, priority, false);
	}

	public MyThreadFactory(String namePrefix, Integer priority, boolean daemon) {
		this.namePrefix = namePrefix;
		this.priority = priority;
		this.daemon = daemon;

		SecurityManager s = System.getSecurityManager();
		this.threadGroup = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
	}

	//endregion

	@Override
	public Thread newThread(Runnable runnable) {
		String threadName = this.namePrefix + "-thread-" + threadNumber.getAndIncrement();

		Thread thread = new Thread(threadGroup, runnable, threadName, 0);

		thread.setPriority(priority);

		thread.setDaemon(daemon);

		return thread;
	}

}
