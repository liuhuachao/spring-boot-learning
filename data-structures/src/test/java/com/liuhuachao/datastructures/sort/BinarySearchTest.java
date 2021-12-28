package com.liuhuachao.datastructures.sort; 

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* BinarySearch Tester. 
* 
* @author <liuhuachao> 
* @since <pre>12/28/2021</pre> 
* @version 1.0 
*/ 
public class BinarySearchTest {

	int[] originArr = {3,1,2,6,4,5,7};

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: binarySearch(int[] arr, int a)
	 */
	@Test
	public void testBinarySearch() throws Exception {
		int expectedIndex = 3;
		int actualIndex =  BinarySearch.binarySearch(originArr,6);
		Assert.assertEquals(expectedIndex,actualIndex);
	}
} 
