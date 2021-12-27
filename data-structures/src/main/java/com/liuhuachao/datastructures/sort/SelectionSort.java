package com.liuhuachao.datastructures.sort;

/**
 * 选择排序（Selection Sort）
 * 工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 * @author liuhuachao
 * @date 2021/12/9
 */
public class SelectionSort {

	/**
	 * n个记录的直接选择排序可经过n-1趟直接选择排序得到有序结果。具体算法描述如下：
	 * 初始状态：无序区为R[1..n]，有序区为空；
	 * 第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。
	 * 该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
	 * n-1趟结束，数组有序化了。
	 * @param arr
	 * @return
	 */
	public static int[] selectionSort(int[] arr) {
		if (arr != null && arr.length != 1) {
			for (int i = 0; i < arr.length; i++) {
				int minIndex = i;

				// 找出无序区最小值的索引
				for (int j = i + 1; j < arr.length; j++) {
					if (arr[minIndex] > arr[j]) {
						minIndex = j;
					}
				}

				// 将最小值和无序区的记录交换
				if (arr[minIndex] != arr[i]) {
					int min = arr[minIndex];
					arr[minIndex] = arr[i];
					arr[i] = min;
				}
			}
		}
		return arr;
	}

}
