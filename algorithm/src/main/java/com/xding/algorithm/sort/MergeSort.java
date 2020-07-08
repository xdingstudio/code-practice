package com.xding.algorithm.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class Description
 *
 * @author xding
 * @date 2020/5/7 00:38
 */
public class MergeSort {

    private static final Logger log = LoggerFactory.getLogger(MergeSort.class);

    /**
     * 核心思想：
     * 将一个数组从中间分成两部分，对前后两部分分别排序，再将排好序的两部分合并在一起，完成数组排序。
     * 空间复杂度：O(n)
     * 稳定性：稳定
     * 时间复杂度：最好：O(nlogn)，最坏：O(nlogn)，平均：O(nlogn)
     *
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    public void mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int middle = start + (end - start) / 2;
        mergeSort(nums, start, middle);
        mergeSort(nums, middle + 1, end);
        merge(nums, start, middle, middle + 1, end);
    }

    private void merge(int[] nums, int start1, int end1, int start2, int end2) {
        // 合并两个有序数组，可以不借用额外空间，从尾部进行合并，避免移动数据
        int[] temp = new int[end2 - start1 + 1];
        int i = 0;
        int left = start1;
        while (start1 <= end1 && start2 <= end2) {
            if (nums[start1] <= nums[start2]) {
                temp[i++] = nums[start1++];
            } else {
                temp[i++] = nums[start2++];
            }
        }
        while (start1 <= end1) {
            temp[i++] = nums[start1++];
        }
        while (start2 <= end2) {
            temp[i++] = nums[start2++];
        }
        for (int j = 0; j < temp.length; j++) {
            nums[left + j] = temp[j];
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] nums = {5, 2, 3, 1};
        mergeSort.sortArray(nums);
        log.info("sort finish! nums:{}", nums);
    }
}
