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
	 * （1）选择一个基准值（key），以数组的第一个为例
	 * （2）从右边查找比基准值小的值，再从左边查找比基准值大的值，然后交换
	 * （3）当左右两边的值相等时（first）,会退出当前循环
	 * （4）交换 key 和 first 的值，返回 first 所在地址
	 * （5）递归排序左右两边的序列，直到整个序列有序。
	 * @param arr
	 * @param left
	 * @param right
	 * @return
	 */
	public static int[] quickSort(int[] arr, int left, int right) {
		if(left < right){
			int pivotIndex = partition(arr,left,right);
			quickSort(arr, left, pivotIndex - 1);
			quickSort(arr, pivotIndex + 1, right);
		}

		return arr;
	}

	/**
	 * 获取分区地址
	 * @param arr
	 * @param left
	 * @param right
	 * @return
	 */
	private static int partition(int[] arr,int left,int right){
		int i = left;
		int j = right;
		int key = arr[left];

		while (j != i) {
			while (j > i && arr[j] >= key) {
				j--;
			}

			while (j > i && arr[i] <= key) {
				i++;
			}

			if (j > i) {
				swap(arr, i, j);
			}
		}

		arr[left] = arr[i];
		arr[i] = key;

		return j;
	}

	/**
	 * 交换
	 * @param arr 原始数组
	 * @param i   索引 i
	 * @param j   索引 j
	 */
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
