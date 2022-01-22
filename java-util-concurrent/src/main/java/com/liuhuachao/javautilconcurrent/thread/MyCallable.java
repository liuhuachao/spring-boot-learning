package com.liuhuachao.javautilconcurrent.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadFactory;

/**
 * 多线程实现方式三
 * 实现 Callable 接口
 * 步骤
 * 1.创建实现 Callable 接口的类 myCallable
 * 2.以 myCallable 为参数创建 FutureTask 对象
 * 3.将 FutureTask 作为参数创建 Thread 对象
 * 4.调用线程对象的 start() 方法
 * @author liuhuachao
 * @date 2022/1/21
 */
public class MyCallable implements Callable {
	@Override
	public Object call() throws Exception {
		System.out.println(Thread.currentThread().getName() + " 的 call() 方法正在执行……");
		long threadId = Thread.currentThread().getId();
		return threadId;
	}
}
