package com.liuhuachao.javautilconcurrent.thread;

/**
 * 多线程实现方式二
 * 实现 Runnable 接口
 * 步骤
 * 1.定义 Runnable 接口实现类 MyRunnable，并重写 run()方法
 * 2.创建 MyRunnable 实例 myRunnable，以 myRunnable 作为 target 创建 Thead 对象，该 Thread 对象才是真正的线程对象
 * 3.调用线程对象的 start() 方法
 * @author liuhuachao
 * @date 2022/1/21
 */
public class MyRunnable implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " MyRunnable 的 run() 方法正在执行……");
	}
}
