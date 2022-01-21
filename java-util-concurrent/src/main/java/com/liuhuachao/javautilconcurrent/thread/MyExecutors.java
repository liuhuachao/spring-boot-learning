package com.liuhuachao.javautilconcurrent.thread;

/**
 * 多线程实现方式四
 * 通过线程池（Executors）创建线程
 * Executors 提供了一系列工厂方法用于创建线程池，返回的线程池都实现了 ExecutorService 接口
 * 步骤
 * 1.定义 Runnable 接口实现类 MyExecutors，并重写 run()方法
 * 2.使用 Executors 的静态方法 newXXXThreadPool() 创建线程池 executorService
 * 3.创建 MyExecutors 实例 myExecutors
 * 4.以 myExecutors 作为参数执行 executorService.execute(myExecutors) 方法启动线程
 *
 * @author liuhuachao
 * @date 2022/1/21
 */
public class MyExecutors implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " 线程的 run() 方法正在执行……");
	}
}
