package com.liuhuachao.datastructures.sort;

/**
 * 二分查找法
 * 又叫作折半查找，要求待查找的序列有序，每次查找都取中间位置的值与待查关键字进行比较，
 * 如果中间位置的值比待查关键字大，则在序列的左半部分继续执行该查找过程，
 * 如果中间位置的值比待查关键字小，则在序列的右半部分继续执行该查找过程，
 * 直到查找到关键字为止，否则在序列中没有待查关键字。
 * @author liuhuachao
 * @date 2021/12/8
 */
public class BinarySearch {

	/**
	 * 二分查找算法
	 * 要求查找的集合是有序的，如果不是有序的集合，则先要通过排序算法排序后再进行查找。
	 * @param arr 数组
	 * @param k 待查值
	 * @return 返回待查值在数组中的地址
	 */
	public static int binarySearch(int[] arr, int k) {
		int left = 0;
		int right = arr.length - 1;

		// 循环包含 left == right，每次查找改变 left 和 right 的指向，可缩小查找范围，并防止进入死循环
		while (left <= right) {

			// 使用移位操作，提升性能，防止溢出，等价于 int mid = (left + right)/2
			int mid = left + ((right - left) >> 1);

			if (arr[mid] == k) {
				return mid;
			}
			// 如果中间数在查找数右边,则查找范围变为最左边到中间数前一位之间
			else if (arr[mid] > k) {
				right = mid - 1;
			}
			// 如果中间数在查找数左边，则查找范围变为中间数后一位到最右边之间
			else {
				left = mid + 1;
			}
		}

		return -1;
	}

}
