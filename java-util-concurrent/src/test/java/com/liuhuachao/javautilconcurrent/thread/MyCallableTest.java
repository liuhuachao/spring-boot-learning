package com.liuhuachao.javautilconcurrent.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * MyCallable Tester.
 * @author <liuhuachao>
 * @version 1.0
 * @since <pre>01/21/2022</pre>
 */
public class MyCallableTest {

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: call()
	 */
	@Test
	public void testCall() throws Exception {
		MyCallable myCallable = new MyCallable();
		FutureTask<Integer> myFutureTask = new FutureTask<>(myCallable);
		Thread myThread = new Thread(myFutureTask);
		myThread.start();

		try {
			System.out.println("MyCallable 的返回结果：" + myFutureTask.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getName() + " 单元测试完成");
	}

} 
