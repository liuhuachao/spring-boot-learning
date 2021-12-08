package com.liuhuachao.datastructuresandalgorithms.sort;

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

	private int[] OriginArr = new int[]{3,2,4,1,5};
	private int[] expectedArr = OriginArr;

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
		Arrays.sort(expectedArr);
		Assert.assertEquals(expectedArr,actualArr);
	}

	/**
	 * Method: bubbleSort1(int[] arr)
	 */
	@Test
	public void testBubbleSort1() throws Exception {
		//TODO: Test goes here...
	}

	/**
	 * Method: bubbleSort2(int[] arr)
	 */
	@Test
	public void testBubbleSort2() throws Exception {
		//TODO: Test goes here...
	}

} 
