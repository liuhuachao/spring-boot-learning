package com.liuhuachao.datastructures.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 桶排序
 * 原理是先找出数组中的最大值和最小值，并根据最大值和最小值定义桶，然后将数据按照大小放入桶中，最后对每个桶进行排序，在每个桶的内部完成排序后，就得到了完整的排序数组。
 * @author liuhuachao
 * @date 2021/12/9
 */
public class BucketSort {

	/**
	 * 实现步骤：
	 * （1）在待排序数组中找出最大值max和最小值min，并根据“bucketNum=（max-min）/arr.length+1”创建桶；
	 * （2）遍历待排序的数组arr，计算每个元素arr[i]的大小并放入桶中；
	 * （3）对每个桶各自排序，在每个桶的内部排序完成后就得到了完整的排序数组。
	 * @param arr
	 * @return
	 */
	public static Integer[] bucketSort(Integer[] arr) {

		//region 找出最小值、最大值
		int min = Integer.MIN_VALUE;
		int max = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
		}
		//endregion

		//region 创建桶
		int bucketNum = (max - min) / arr.length + 1;
		List<List<Integer>> buckets = new ArrayList<>(bucketNum);

		for (int i = 0; i < bucketNum; i++) {
			buckets.add(new ArrayList<Integer>());
		}
		//endregion

		//region 将每个元素都放入桶中
		for (int i = 0; i < arr.length; i++) {
			int num = (arr[i] - min) / (arr.length);
			buckets.get(num).add(arr[i]);
		}
		//endregion

		//region 对每个桶进行排序后插入新数组
		List<Integer> sortedList = new ArrayList<>();
		for (int i = 0; i < bucketNum; i++) {
			Collections.sort(buckets.get(i));
			for (int j = 0; j < buckets.get(i).size(); j++) {
				if(sortedList.contains(buckets.get(i).get(j))){
					sortedList.add(buckets.get(i).get(j));
				}
			}
		}

		if (sortedList.size() > 0) {
			return sortedList.toArray(new Integer[arr.length]);
		}
		//endregion

		return arr;
	}

}
