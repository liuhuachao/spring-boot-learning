package com.liuhuachao.datastructuresandalgorithms.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 快速排序（Quick Sort）是对冒泡排序的一种改进，通过一趟排序将要排序的数据序列分成独立的两部分，
 * 其中一部分的所有数据比另一部分的所有数据都要小，然后按此方法对两部分数据分别进行快速排序，整个排序过程递归进行，最终使整个数据序列变成有序的数据序列。
 * @author liuhuachao
 * @date 2021/12/8
 */
public class QuickSort {

	/**
	 * 快速排序
	 * @param arr
	 * @param left
	 * @param right
	 * @return
	 */
	public static int[] quickSort(int[] arr, int left, int right) {
		int start = left;
		int end = right;

		// 基准值，选择第1个元素为基准元素
		int key = arr[left];

		while (start < end) {

			// 从前往后比较
			while (start < end && arr[start] < key) {
				start++;
			}
			if (arr[start] >= key) {
				int temp = arr[start];
				arr[start] = key;
				key = temp;
			}

			// 从后向前比较
			while (end > start && arr[end] > key) {
				end--;
			}
			if (arr[end] <= key) {
				int temp = arr[end];
				arr[end] = key;
				key = temp;
			}
		}

		// 上面排序完成后，左边的值都比关键值小，右边的都比关键值大，但左右两边内部还未排序。
		// 递归调用快速排序，不包含关键值
		if (start > left) {
			quickSort(arr, left, start - 1);
		}

		if (end < right) {
			quickSort(arr, end + 1, right);
		}

		return arr;
	}

	/**
	 * 快速排序
	 * @param arr
	 * @param left
	 * @param right
	 * @return
	 */
	public static int[] quickSort2(int[] arr, int left, int right) {
		if (right > left) {
			// 原序列划分后基准元素的位置
			int base_index = partition(arr, left, right);

			// 对第一个子序列快速排序，不包含基准元素
			quickSort2(arr, left, base_index - 1);

			// 对第二个子序列快速排序，不包含基准元素
			quickSort2(arr, base_index + 1, right);
		}
		return arr;
	}

	/**
	 * 序列划分
	 * @param arr
	 * @param left
	 * @param right
	 * @return
	 */
	public static int partition(int[] arr, int left, int right) {
		// 基准元素的值
		int base = arr[left];
		// 基准元素最终应该在的位置
		int base_index = left;

		// 从基准元素的下一个元素开始
		for (int i = left + 1; i <= right; i++) {
			if (arr[i] < base) {
				// 若其小于基准元素,则基准元素最终位置后移；否则不用移动
				base_index++;

				if (base_index != i) {
					int temp = arr[base_index];
					arr[base_index] = arr[i];
					arr[i] = temp;
				}
			}
		}

		// 将基准元素就位
		arr[left] = arr[base_index];
		arr[base_index] = base;

		return base_index;
	}

}
