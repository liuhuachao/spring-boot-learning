package com.liuhuachao.datastructuresandalgorithms.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 归并排序
 * 基于归并（Merge）操作的一种有效排序算法，是采用分治法（Divide and Conquer）的典型应用。
 * 归并排序算法将待排序序列分为若干个子序列，先对每个子序列进行排序，等每个子序列都有序后，再将有序子序列合并为整体的有序序列。若将两个有序表合并成一个有序表，则称之为二路归并。
 * @author liuhuachao
 * @date 2021/12/9
 */
public class MergeSort {

	/**
	 * 归并排序
	 * 把长度为n的输入序列分成两个长度为n/2的子序列；
	 * 对这两个子序列分别采用归并排序；
	 * 将两个排序好的子序列合并成一个最终的排序序列。
	 * @param list
	 * @return
	 */
	public static <T extends Comparable<T>> List<T> mergeSort(List<T> list) {
		int len = list.size();
		if (len < 2) {
			return list;
		}

		int middle = (int) Math.floor(len / 2);
		List<T> left = list.subList(0, middle - 1);
		List<T> right = list.subList(middle, len - 1);
		left = mergeSort(left);
		right = mergeSort(right);
		List<T> mergeList = merge(left, right);

		return mergeList;
	}

	/**
	 * 归并
	 * @param left
	 * @param right
	 * @param <T>
	 * @return
	 */
	public static <T extends Comparable<T>> List<T> merge(List<T> left, List<T> right) {
		List result = new ArrayList();

		while (left.size() > 0 && right.size() > 0) {
			if (left.get(0).compareTo(right.get(0)) <= 0) {
				result.add(left.get(0));
				left.remove(0);
			}
			else {
				result.add(right.get(0));
				right.remove(0);
			}
		}

		while (left.size() > 0) {
			result.add(left.get(0));
			left.remove(0);
		}

		while (right.size() > 0) {
			result.add(right.get(0));
			right.remove(0);
		}

		return result;
	}


}
