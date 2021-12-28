package com.liuhuachao.datastructures.sort;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * QuickSort Tester.
 * @author <liuhuachao>
 * @version 1.0
 * @since <pre>12/27/2021</pre>
 */
public class QuickSortTest {

	int[] originArr;

	@Before
	public void before() throws Exception {
		originArr = new int[]{5, 7, 2, 6, 4, 3, 1};
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: quickSort2(int[] arr, int left, int right)
	 */
	@Test
	public void testQuickSort() throws Exception {
		int[] expectedArr = {1, 2, 3, 4, 5, 6, 7};
		int[] actualArr = QuickSort.quickSort(originArr, 0, originArr.length - 1);
		boolean equals = Arrays.equals(expectedArr, actualArr);
		Assert.assertTrue(equals);
	}

} 
