package com.liuhuachao.datastructures.sort;

/**
 * 冒泡排序算法
 * 分为外层循环和内层循环，外层循环控制排序的次数，内层循环控制每一趟排序多少次。
 * 在内层循环中比较当前数据和下一个数据的大小，如果当前数据大于下一个数据，就交换二者的位置，这样重复进行判断，直至整个排序完成，最终返回排序后的数组。
 * @author liuhuachao
 * @date 2021/12/8
 */
public class BubbleSort {

	/**
	 * 冒泡排序 原始实现
	 * @param arr
	 * @return
	 */
	public static int[] bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		return arr;
	}

	/**
	 * 冒泡排序 优化1
	 * 冒泡排序过程中，可以检测到整个序列是否已经排序完成，进而可以避免掉后续的循环
	 * @param arr
	 * @return
	 */
	public static int[] bubbleSort1(int[] arr) {
		if (null == arr || arr.length == 1) {
			return arr;
		}

		int n = arr.length - 1;

		for (int i = 0; i < n; i++) {
			boolean isSwap = false;
			for (int j = 0; j < n - i; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					isSwap = true;
				}
			}

			// 已经没有交换了,跳出循环
			if (!isSwap) {
				break;
			}
		}
		return arr;
	}

	/**
	 * 冒泡排序 优化2
	 * 利用一个标志位，记录一下当前第 i 趟所交换的最后一个位置的下标，在进行第 i+1 趟的时候，只需要内循环到这个下标的位置就可以了。
	 * @param arr
	 * @return
	 */
	public static int[] bubbleSort2(int[] arr) {
		if (null == arr || arr.length == 1) {
			return arr;
		}

		int n = arr.length - 1;
		int k = n;
		// 最后一次交换的位置
		int tempPosition = 0;

		for (int i = 0; i < n; i++) {
			boolean isSwap = false;
			for (int j = 0; j < k; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					isSwap = true;
					tempPosition = j;
				}
			}

			// 已经没有交换了,跳出循环
			if (!isSwap) {
				break;
			}

			// 下一次排序从第一个比较到上次记录的位置结束即可
			k = tempPosition;
		}
		return arr;
	}

	public static int[] bubbleSort3(int[] arr) {
		if (null == arr || arr.length == 1) {
			return arr;
		}

		for (int i = 0; i < arr.length - 1; i++) {
			boolean hasSwap = false;
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
					hasSwap = true;
				}
			}
			if (!hasSwap) {
				break;
			}
		}

		return arr;
	}

	/**
	 * 交换
	 * @param arr 原始数组
	 * @param i 索引 i
	 * @param j 索引 j
	 */
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j] ;
		arr[j] = temp;
	}
}
