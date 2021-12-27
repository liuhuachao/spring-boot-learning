package com.liuhuachao.springbootaop.service.proxy;

import java.lang.reflect.Method;

import com.liuhuachao.springbootaop.service.impl.CacheServiceImpl;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.CallbackHelper;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.FixedValue;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * CacheServiceMethodInterceptor Tester.
 * @author <liuhuachao>
 * @version 1.0
 * @since <pre>12/27/2021</pre>
 */
public class CacheServiceMethodInterceptorTest {

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
	 */
	@Test
	public void testIntercept() throws Exception {
		//TODO: Test goes here...
	}

	/**
	 * Method: createProxy(T target)
	 */
	@Test
	public void testCreateProxy() throws Exception {
		CacheServiceImpl proxy = CacheServiceMethodInterceptor.createProxy(new CacheServiceImpl());
		proxy.m1();
	}

	@Test
	public void testCglibProxy() throws Exception {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(CacheServiceImpl.class);

		Callback insertCallback = (MethodInterceptor) (Object o, Method method, Object[] objects, MethodProxy methodProxy) -> {
			System.out.println("使用 Cglib MethodInterceptor 动态代理方法调用前");
			Object result = methodProxy.invokeSuper(o, objects);
			System.out.println("使用 Cglib MethodInterceptor 动态代理方法调用后");
			return result;
		};

		Callback getCallback = (FixedValue)()->{
			System.out.println("返回固定值");
			return "Hello World";
		};

		CallbackHelper callbackHelper = new CallbackHelper(CacheServiceImpl.class, null) {
			@Override
			protected Object getCallback(Method method) {
				return method.getName().startsWith("m1") ? insertCallback : getCallback;
			}
		};

		enhancer.setCallbacks(callbackHelper.getCallbacks());
		enhancer.setCallbackFilter(callbackHelper);
		CacheServiceImpl cacheService = (CacheServiceImpl) enhancer.create();
		cacheService.m1();
		cacheService.m2();
	}

} 
