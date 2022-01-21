package com.liuhuachao.javautilconcurrent.thread;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * MyThread Tester.
 * @author <liuhuachao>
 * @version 1.0
 * @since <pre>01/21/2022</pre>
 */
public class MyThreadTest {

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
		MyThread myThread = new MyThread();
		myThread.setName("线程一");
		myThread.start();
		System.out.println(myThread.getName() + " start() 方法启动");
		System.out.println(Thread.currentThread().getName() + " 单元测试执行完成");
	}

} 
