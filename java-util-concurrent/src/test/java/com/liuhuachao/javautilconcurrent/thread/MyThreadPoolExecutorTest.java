package com.liuhuachao.javautilconcurrent.thread;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * MyThreadPoolExecutor Tester.
 * @author <liuhuachao>
 * @version 1.0
 * @since <pre>01/22/2022</pre>
 */
public class MyThreadPoolExecutorTest {

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: createThreadPool()
	 */
	@Test
	public void testCreateThreadPool() throws Exception {
		MyThreadPoolExecutor poolExecutor = new MyThreadPoolExecutor();
		poolExecutor.createThreadPool();
	}

} 
