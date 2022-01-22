package com.liuhuachao.javautilconcurrent.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 多线程实现方式五
 * 通过调用 ThreadPoolExecutor（定时任务使用 ScheduledThreadPoolExecutor） 的构造方法创建线程池
 * 继承关系：Executor -> ExecutorService -> AbstractExecutorService -> ThreadPoolExecutor
 * @author liuhuachao
 * @date 2022/1/22
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor {

	/**
	 * 构造函数
	 */
	public MyThreadPoolExecutor() {
		this(5, 10, 100, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new AbortPolicy());
	}

	/**
	 * 构造函数
	 * @param namePrefix 线程名前缀
	 */
	public MyThreadPoolExecutor(String namePrefix) {
		this(5, 10, 100, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<>(10), new MyThreadFactory(namePrefix), new AbortPolicy());
	}

	/**
	 * 构造函数
	 * @param corePoolSize    核心线程数
	 * @param maximumPoolSize 最大线程数
	 * @param keepAliveTime   空闲线程存活时间
	 * @param unit            空闲线程存活时间单位
	 * @param workQueue       工作队列
	 * @param threadFactory   线程工厂
	 * @param handler         拒绝策略
	 * @return null
	 * @author liuhuachao
	 * @date 2022/1/22 10:11
	 */
	public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
	}

	/**
	 * 创建线程池
	 * @author liuhuachao
	 * @date 2022/1/22 13:52
	 */
	public void createThreadPool() {
		for (int i = 0; i < 5; i++) {
			// this.execute(new MyRunnable());

			try {
				Future future = this.submit(new MyCallable());
				Object result = future.get();
				System.out.println(Thread.currentThread().getName() + " 的 返回值: " + result.toString());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}
