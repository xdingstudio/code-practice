package com.xding.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 链接：https://leetcode-cn.com/problems/3sum
 *
 * @author xding
 * @date 2020/4/8 15:42
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int k = 0; k < nums.length; k++) {
            if (nums[k] > 0) {
                break;
            }
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            int i = k + 1;
            int j = nums.length - 1;
            int s;
            while (i < j) {
                if (i > k + 1 && nums[i] == nums[i - 1]) {
                    i++;
                    continue;
                }
                if (j < nums.length - 1 && nums[j] == nums[j + 1]) {
                    j--;
                    continue;
                }
                s = nums[k] + nums[i] + nums[j];
                if (s > 0) {
                    j--;
                } else if (s < 0) {
                    i++;
                } else {
                    result.add(Arrays.asList(nums[k], nums[i], nums[j]));
                    i++;
                    j--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};

        List<List<Integer>> result = new ThreeSum().threeSum(nums);

        System.out.println(result);
    }
}
