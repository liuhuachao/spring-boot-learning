package com.liuhuachao.javautilconcurrent.thread; 

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

/**
 * MyExecutors Tester.
 * @author <liuhuachao>
 * @version 1.0
 * @since <pre>01/21/2022</pre>
 */
public class MyExecutorsTest {

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: run()
	 */
	@Test
	public void testRun() throws Exception {

		//region 创建线程池
		/**
		 * 创建只有一个线程的线程池，使用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
		 */
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		/**
		 * 创建可重用固定线程数的线程池，可控制线程最大并发数，超出的线程会在队列中等待。
		 */
		// ExecutorService executorService = Executors.newFixedThreadPool(5);

		/**
		 * 创建一个可缓存的线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
		 * 适合于发起许多短时任务的应用程序。
		 */
		// ExecutorService executorService = Executors.newCachedThreadPool();

		/**
		 * 创建一个定长线程池，支持定时及周期性任务执行。
		 */
		// ExecutorService executorService = Executors.newScheduledThreadPool(5);

		/**
		 * 基于 work-stealing 算法的，并行地处理任务，不保证处理顺序。Java 8 才加入这个创建方法
		 * 其中一个任务可以产生其他较小的任务，这些任务被添加到并行处理线程的队列中。如果一个线程完成了工作并且无事可做，则可以从另一线程的队列中"窃取"工作。
		 */
		// ExecutorService executorService = Executors.newWorkStealingPool(5);
		//endregion

		MyExecutors myExecutors = new MyExecutors();
		for (int i = 0; i < 5; i++) {
			executorService.execute(myExecutors);

			// Future<Integer> future = executorService.submit(new MyCallable());
			// Integer result = future.get();
		}

		System.out.println(Thread.currentThread().getName() + " 线程的 execute() 方法开始执行");
		executorService.shutdown();

	}

} 
