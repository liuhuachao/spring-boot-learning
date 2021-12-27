package com.liuhuachao.springbootaop.service.proxy;

import com.liuhuachao.springbootaop.service.impl.CacheServiceImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * CacheServiceProxy Tester.
 * @author <liuhuachao>
 * @version 1.0
 * @since <pre>12/27/2021</pre>
 */
public class CacheServiceProxyTest {

	private CacheServiceProxy proxy;

	@Before
	public void before() throws Exception {
		proxy = new CacheServiceProxy(new CacheServiceImpl());
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: m1()
	 */
	@Test
	public void testM1() throws Exception {
		proxy.m1();
	}

	/**
	 * Method: m2()
	 */
	@Test
	public void testM2() throws Exception {
		proxy.m2();
	}

} 
