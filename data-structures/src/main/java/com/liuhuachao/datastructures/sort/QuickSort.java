package com.liuhuachao.datastructures.sort;

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
	 * 实现原理：选择一个关键值作为基准值，将比基准值大的都放在右边的序列中，将比基准值小的都放在左边的序列中。
	 * 具体的过程如下：
	 *（1）从后向前比较，用基准值和最后一个值进行比较。如果比基准值小，则交换位置；如果比基准值大，则继续比较下一个值，直到找到第1个比基准值小的值才交换位置；
	 *（2）在从后向前找到第1个比基准值小的值并交换位置后，从前向后开始比较。如果有比基准值大的，则交换位置；如果没有，则继续比较下一个，直到找到第1个比基准值大的值才交换位置；
	 *（3）重复执行以上过程，直到从前向后比较的索引大于等于从后向前比较的索引，则结束一次循环。这时对于基准值来说，左右两边都是有序的数据序列；
	 *（4）重复循环以上过程，分别比较左右两边的序列，直到整个数据序列有序。
	 * @param arr
	 * @param left
	 * @param right
	 * @return
	 */
	public static int[] quickSort(int[] arr, int left, int right) {
		// 从前往后比较的索引
		int start = left;

		// 从后往前比较的索引
		int end = right;

		// 基准值，一般选择第1个元素为基准值
		int key = arr[left];

		// 将比基准值大的都放在右边的序列中，将比基准值小的都放在左边的序列中
		while (end > start) {

			// 从后向前比较
			while (end > start && arr[end] > key) {
				end--;
			}
			if (arr[end] <= key) {
				key = swap(arr, end, key);
			}

			// 从前往后比较
			while (start < end && arr[start] < key) {
				start++;
			}
			if (arr[start] >= key) {
				key = swap(arr, start, key);
			}
		}

		// 左边序列：递归调用快速排序，不包含基准值
		if (start > left) {
			quickSort(arr, left, start - 1);
		}

		// 右边序列：递归调用快速排序，不包含基准值
		if (end < right) {
			quickSort(arr, end + 1, right);
		}

		return arr;
	}

	/**
	 * 交换
	 * @param arr
	 * @param index
	 * @param key
	 * @return
	 */
	public static int swap(int[] arr, int index, int key) {
		int temp = arr[index];
		arr[index] = key;
		key = temp;
		return key;
	}

}
