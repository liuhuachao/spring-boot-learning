package com.liuhuachao.springbootaop.service.proxy;

import com.liuhuachao.springbootaop.service.CacheService;
import com.liuhuachao.springbootaop.service.impl.CacheServiceImpl;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * CacheServiceInvocationHandler Tester.
 * @author <liuhuachao>
 * @version 1.0
 * @since <pre>12/27/2021</pre>
 */
public class CacheServiceInvocationHandlerTest {

	CacheService service;

	@Before
	public void before() throws Exception {
		service = new CacheServiceImpl();
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: invoke(Object proxy, Method method, Object[] args)
	 */
	@Test
	public void testInvoke() throws Exception {
		//TODO: Test goes here...
	}

	/**
	 * Method: createProxy(Object target, Class<T> targetInterface)
	 */
	@Test
	public void testCreateProxy() throws Exception {
		service = CacheServiceInvocationHandler.createProxy(new CacheServiceImpl(), CacheService.class);
		service.m1();
	}

} 
