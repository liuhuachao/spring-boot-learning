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
		// ExecutorService executorService = Executors.newSingleThreadExecutor();
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		// ExecutorService executorService = Executors.newScheduledThreadPool(5);
		// ExecutorService executorService = Executors.newCachedThreadPool();
		// ExecutorService executorService = Executors.newWorkStealingPool(5);

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
