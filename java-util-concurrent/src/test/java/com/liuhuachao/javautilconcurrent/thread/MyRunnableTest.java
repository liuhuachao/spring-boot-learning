package com.liuhuachao.javautilconcurrent.thread; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;

/**
 * MyRunnable Tester.
 * @author <liuhuachao>
 * @version 1.0
 * @since <pre>01/21/2022</pre>
 */
public class MyRunnableTest {

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
		MyRunnable myRunnable = new MyRunnable();
		Thread myThread = new Thread(myRunnable);
		myThread.start();
		System.out.println(Thread.currentThread().getName() + " 单元测试完成");
	}

	/**
	 * Method: run()
	 */
	@Test
	public void testRun2() throws Exception {

		Thread myThread = new Thread(() ->{
			System.out.println(Thread.currentThread().getName() + " run() 方法正在执行……(lambda表达式)");
		},"myRunnable");
		myThread.start();
		System.out.println(Thread.currentThread().getName() + " 单元测试完成");
	}



} 
