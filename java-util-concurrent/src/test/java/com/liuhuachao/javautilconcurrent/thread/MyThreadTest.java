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

		/**
		 * 设置线程名称
		 */
		myThread.setName("线程一");

		/**
		 * 设置线程优先级
		 * java 中的线程优先级的范围是1～10，默认的优先级是5,10极最高。
		 */
		myThread.setPriority(3);
		// myThread.setPriority(Thread.MIN_PRIORITY);
		// myThread.setPriority(Thread.NORM_PRIORITY);
		// myThread.setPriority(Thread.MAX_PRIORITY);

		/**
		 * 设置是否为守护线程
		 * 默认为 false，表示是普通的用户线程，而不是守护线程。
		 * java中的线程分为两种：守护线程（Daemon）和用户线程（User）。
		 * 守护线程是一种特殊的线程，在后台默默地完成一些系统性的服务，比如垃圾回收线程、JIT线程都是守护线程。
		 * 如果用户线程全部结束了，系统只剩下守护进程的时候，java虚拟机会自动退出。
		 */
		myThread.setDaemon(false);

		myThread.start();

		System.out.println(myThread.getName() + " start() 方法启动");
		System.out.println("线程Id：" + myThread.getId() + " 线程名称：" + myThread.getName()
				+ " 线程状态：" + myThread.getState() + " 线程优先级：" + myThread.getPriority() );
		System.out.println(Thread.currentThread().getName() + " 单元测试执行完成");
	}

} 
