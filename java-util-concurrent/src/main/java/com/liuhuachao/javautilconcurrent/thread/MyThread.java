package com.liuhuachao.javautilconcurrent.thread;

/**
 * 多线程实现方式一
 * 继承 Thread 类
 * 步骤
 * 1.定义一个 Thread 类的子类，重写 run 方法，实现相关逻辑
 * 2.创建自定义的线程子类对象
 * 3.调用子类实例的 start() 方法来启动线程
 * @author liuhuachao
 * @date 2022/1/21
 */
public class MyThread extends Thread {

	@Override
	public void run() {
		System.out.println(this.getName() + " MyThread 的 run() 方法正在执行……");
	}

}
