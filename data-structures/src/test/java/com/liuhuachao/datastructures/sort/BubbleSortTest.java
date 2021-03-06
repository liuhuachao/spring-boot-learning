package com.liuhuachao.datastructures.sort;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * BubbleSort Tester.
 * @author <liuhuachao>
 * @version 1.0
 * @since <pre>12/08/2021</pre>
 */
public class BubbleSortTest {

	private int[] OriginArr = {3,1,2,6,4,5,7};
	private int[] expectedArr = {1,2,3,4,5,6,7};

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: bubbleSort(int[] arr)
	 */
	@Test
	public void testBubbleSort() throws Exception {
		int [] actualArr = BubbleSort.bubbleSort(OriginArr);
		boolean equals = Arrays.equals(expectedArr, actualArr);
		Assert.assertTrue(equals);
	}

} 
