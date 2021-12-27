package com.liuhuachao.datastructures.sort;

/**
 * 基数排序
 * 基数排序（Radix Sort）算法是桶排序算法的扩展，它将数据按位切割为不同的数字，位数不够的补0，然后在每个位数上分别进行比较，最终得到排好序的序列。
 * @author liuhuachao
 * @date 2021/12/9
 */
public class RadixSort {

	/**
	 * 基数排序算法的原理是将所有待比较数据统一为同一长度，
	 * 在位数不够时前面补零，然后从低位到高位根据每个位上整数的大小依次对数据进行排序，最终得到一个有序序列。
	 * 具体做法:
	 * 在while循环中先取出当前位的数据放入排序桶中，
	 * 然后将排序桶的数据覆盖到原数组中用于保存这一位的排序结果，
	 * 接着从上到下遍历这个桶并将数据保存到原数组中，这样便完成了当前位的排序。
	 * 假设数组最大有N位，则进行N+1次while循环便完成了所有位数（个位、十位、百位……）上的排序。
	 * @param arr 带排序数组
	 * @param r 基数
	 * @param d 元素的位数
	 * @param n 待排序元素个数
	 * @return
	 */
	public static int[] radixSort(int[] arr,int r,int d,int n){

		if (arr != null && arr.length != 1 ) {

			// 一共有基数r个桶，每个桶最多放n个元素
			int[][] bucket = new int[r][n];

			//  // 获取元素对应位上的数字，即装入那个桶
			int digit;
			// 定义每一轮的除数，1, 10, 100, ...
			int divisor = 1;

			// 统计每个桶中实际存放元素的个数
			int[] count = new int[r];

			// d 位的元素，需要经过分配、收集d次即可完成排序
			for (int i = 0; i < d; i++) {

				for (int ele : arr) {
					// 获取元素对应位上的数字
					digit = (ele/divisor) % 10;

					// 将元素放入对应桶，桶中元素数目加1
					bucket[digit][count[digit]++] = ele;
				}

				int index = 0;
				for (int j = 0; j < r; j++) {
					int k = 0;
					while(k < count[j]){
						// 按照先进先出依次取出桶中的元素
						arr[index++] = bucket[j][k++];
					}
					count[j] = 0;
				}
				// 用于获取元素对应位数字
				divisor *= 10;
			}
		}
		return arr;
	}

}
