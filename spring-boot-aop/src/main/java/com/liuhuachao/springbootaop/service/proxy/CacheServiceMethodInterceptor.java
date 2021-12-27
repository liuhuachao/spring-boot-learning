package com.liuhuachao.springbootaop.service.proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * 通过 Cglib 动态代理实现 AOP
 * @author liuhuachao
 * @date 2021/12/27
 */
public class CacheServiceMethodInterceptor implements MethodInterceptor {

	private Object target;

	public CacheServiceMethodInterceptor(Object target) {
		this.target = target;
	}

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

		System.out.println("使用 Cglib 动态代理方法调用前");

		Object result = method.invoke(target,objects);

		System.out.println("使用 Cglib 动态代理方法调用后");

		return result;
	}

	/**
	 * 创建代理
	 * @param target
	 * @param <T>
	 * @return
	 */
	public static <T> T createProxy(T target){
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback(new CacheServiceMethodInterceptor(target));
		return (T) enhancer.create();
	}

}
