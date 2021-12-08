package com.liuhuachao.datastructuresandalgorithms.sort;

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
	 * 二分查找算法要求要查找的集合是有序的，如果不是有序的集合，则先要通过排序算法排序后再进行查找。
	 * @param arr
	 * @param a
	 * @return
	 */
	public static int binarySearch(int[] arr,int a){
		int left = 0;
		int right = arr.length -1;
		int mid;

		while(left <= right){
			mid = (left + right)/2;
			if(arr[mid] == a){
				return mid;
			}
			// 如果中间位置的值比待查关键字大，则在序列的左半部分继续执行该查找过程，
			else if(arr[mid] > a){
				right = mid - 1;
			}
			// 如果中间位置的值比待查关键字小，则在序列的右半部分继续执行该查找过程，
			else{
				left = mid + 1;
			}
		}

		return -1;
	}

}
