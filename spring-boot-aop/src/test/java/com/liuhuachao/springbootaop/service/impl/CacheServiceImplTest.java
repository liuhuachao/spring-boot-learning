package com.liuhuachao.springbootaop.service.impl;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * CacheServiceImpl Tester.
 * @author <liuhuachao>
 * @version 1.0
 * @since <pre>12/27/2021</pre>
 */
public class CacheServiceImplTest {
	CacheServiceImpl service;

	@Before
	public void before() throws Exception {
		service = new CacheServiceImpl();
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: m1()
	 */
	@Test
	public void testM1() throws Exception {
		service.m1();
	}

	/**
	 * Method: m2()
	 */
	@Test
	public void testM2() throws Exception {
		//TODO: Test goes here...
	}

} 
