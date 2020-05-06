package com.xding.algorithm.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class Description
 *
 * @author xding
 * @date 2020/5/6 22:56
 */
public class InsertSort {

    private static final Logger log = LoggerFactory.getLogger(InsertSort.class);

    /**
     * 核心思想：
     * 取未排序区间中的元素，在已排序区间中找到合适的插入位置将其插入，并保证已排序区间数据一直有序。
     *
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        int temp;
        for (int i = 0; i < nums.length; i++) {
            temp = nums[i];
            int v = search(nums, i, nums[i]);
            for (int j = i; j > v; j--) {
                nums[j] = nums[j - 1];
            }
            nums[v] = temp;
        }
        return nums;
    }

    private int search(int[] nums, int end, int value) {
        if (end <= 0) {
            return 0;
        }
        int i = 0;
        while (nums[i] <= value && i < end) {
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        InsertSort insertSort = new InsertSort();
        int[] nums = {5, 2, 3, 1};
        insertSort.sortArray(nums);
        log.info("sort finish! nums:{}", nums);
    }
}
