package com.liuhuachao.springbootaop.service.proxy;

import com.liuhuachao.springbootaop.service.CacheService;
import com.liuhuachao.springbootaop.service.impl.CacheServiceImpl;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * CacheServiceAspectJ Tester.
 * @author <liuhuachao>
 * @version 1.0
 * @since <pre>12/27/2021</pre>
 */
public class CacheServiceAspectJTest {

	CacheServiceImpl service;

	@Before
	public void before() throws Exception {
		service = new CacheServiceImpl();
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: webLog()
	 */
	@Test
	public void testWebLog() throws Exception {
		//TODO: Test goes here...
	}

	/**
	 * Method: doBefore(JoinPoint joinPoint)
	 */
	@Test
	public void testDoBefore() throws Exception {
		service.m1();
	}

	/**
	 * Method: doAfterReturning(Object ret)
	 */
	@Test
	public void testDoAfterReturning() throws Exception {
		service.m1();
	}

	/**
	 * Method: doAround(JoinPoint joinPoint)
	 */
	@Test
	public void testDoAround() throws Exception {
		service.m1();
	}

	/**
	 * Method: doThrowing(JoinPoint point, Throwable ex)
	 */
	@Test
	public void testDoThrowing() throws Exception {
		//TODO: Test goes here...
	}

} 
