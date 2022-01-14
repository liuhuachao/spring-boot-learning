package com.liuhuachao.springbootaop.service.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理实现 AOP
 * 1. 实现接口：InvocationHandler
 * 2. 创建代理: CreateProxy
 * @author liuhuachao
 * @date 2021/12/27
 */
public class CacheServiceInvocationHandler implements InvocationHandler {

	private Object target;

	public CacheServiceInvocationHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("使用 JDK 动态代理方法调用前");

		Object result = method.invoke(target, args);

		System.out.println("使用 JDK 动态代理方法调用后");

		return result;
	}

	/**
	 * 创建代理类
	 * @param target          目标对象，需要实现 targetInterface 接口
	 * @param targetInterface 需要创建代理的接口
	 * @param <T>             返回代理类
	 * @return
	 */
	public static <T> T createProxy(Object target, Class<T> targetInterface) {
		if (!targetInterface.isInterface()) {
			throw new IllegalStateException("targetInterface 必须是接口类型！");
		}
		else if (!targetInterface.isAssignableFrom(target.getClass())) {
			throw new IllegalStateException("target 必须是 targetInterface 接口的实现类！");
		}
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new CacheServiceInvocationHandler(target));
	}

}
