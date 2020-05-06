package com.xding.algorithm.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class Description
 *
 * @author xding
 * @date 2020/5/7 00:21
 */
public class BubbleSort {

    private static final Logger log = LoggerFactory.getLogger(BubbleSort.class);

    public int[] sortArray(int[] nums) {
        int temp;
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[j - 1] > nums[j]) {
                    temp = nums[j - 1];
                    nums[j - 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] nums = {5, 2, 3, 1};
        bubbleSort.sortArray(nums);
        log.info("sort finish! nums:{}", nums);
    }
}
