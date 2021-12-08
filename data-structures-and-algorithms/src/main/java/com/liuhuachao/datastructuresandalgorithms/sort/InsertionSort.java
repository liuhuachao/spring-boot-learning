package com.liuhuachao.datastructuresandalgorithms.sort;

import java.util.Comparator;

/**
 * 插入排序
 * 基本思路是将一个数据插入已经排好序的序列中，从而得到一个新的有序数据，该算法适用于少量数据的排序，是稳定的排序方法。
 * 从第一个元素开始，该元素可以认为已经被排序；
 * 取出下一个元素，在已经排序的元素序列中从后向前扫描；
 * 如果该元素（已排序）大于新元素，将该元素移到下一位置；
 * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
 * 将新元素插入到该位置后；
 * 重复步骤
 * @author liuhuachao
 * @date 2021/12/8
 */
public class InsertionSort {

	/**
	 * 插入排序
	 * @param arr
	 * @return
	 */
	public static int[] insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			// 要插入的值
			int insertVal = arr[i];

			// 被插入的位置
			int index = i - 1;

			// 如果要插入的数比被插入的数小
			if (index >= 0 && insertVal < arr[index]) {

				// 则将被插入的数向后移动
				arr[index + 1] = arr[index];

				// 被插入位置向前移动
				index--;
			}

			// 将要插入的值放入合适位置
			arr[index + 1] = insertVal;
		}
		return arr;
	}

	/**
	 * 插入排序
	 * @param arr
	 * @return
	 */
	public static <T extends Comparable<T>> T[] insertionSort2(T[] arr) {
		for (int i = 1; i < arr.length; i++) {
			// 从后向前比较
			for (int j = i; j > 0; j--) {
				// 要插入的数
				T insertVal = arr[j];

				// 被插入的数
				T preInsertVal = arr[j - 1];

				// 如果要插入的数小于被插入的数
				if (insertVal.compareTo(preInsertVal) < 0) {

					// 则将被插入的数移到下一位置
					arr[j] = preInsertVal;

					// 将要插入的数放到被插入的数之前的位置
					arr[j - 1] = insertVal;
				}
				else {
					break;
				}
			}
		}
		return arr;
	}

}
