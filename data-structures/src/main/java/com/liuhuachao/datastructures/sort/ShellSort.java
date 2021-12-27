package com.liuhuachao.datastructures.sort;

/**
 * 希尔排序
 * 是插入排序算法的一种，又叫作缩小增量排序算法，是插入排序算法的一种更高效的改进版本，也是非稳定排序算法。
 * 希尔排序算法将数据序列按下标的一定增量进行分组，对每组使用插入排序算法排序，随着增量逐渐减少，每组包含的关键词越来越多，在增量减至1时，整个文件被分为一组，算法终止。
 * @author liuhuachao
 * @date 2021/12/8
 */
public class ShellSort {

	/**
	 * 希尔排序
	 * @param arr
	 * @return
	 */
	public static int[] shellSort(int[] arr){
		int increment = 1;
		while (increment <= arr.length / 3) {
			increment = increment * 3 + 1;
		}

		while (increment > 0) {
			// 希尔插入排序
			shellInsertionSort(arr,increment);

			// 计算出下一个增量值
			increment = (increment - 1) / 3;
		}
		return arr;
	}

	/**
	 * 希尔插入排序
	 * @param arr
	 * @param increment
	 * @return
	 */
	public static int[] shellInsertionSort(int[] arr, int increment) {
		for (int i = increment; i < arr.length; i += increment) {
			if (arr[i] < arr[i - increment]) {
				int j;
				int insertVal = arr[i];
				arr[i] = arr[i - increment];

				for (j = i - increment; j > 0 && insertVal < arr[j]; j -= increment) {
					arr[j + increment] = arr[j];
				}
				arr[j + increment] = arr[j];
			}
		}
		return arr;
	}

}
