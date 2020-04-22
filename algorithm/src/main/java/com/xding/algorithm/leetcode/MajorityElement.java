package com.xding.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 求众数
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 * <p>
 * 链接：https://leetcode-cn.com/problems/majority-element
 *
 * @author xding
 * @date 2020/4/22 22:32
 */
public class MajorityElement {

    /**
     * 哈希法，记录每个数值的次数
     *
     * @param nums
     * @return
     */
    public int hashMapCount(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int count = 1;
        int result = nums[0];
        for (int num : nums) {
            Integer value = countMap.get(num);
            value = value == null ? 1 : value + 1;
            countMap.put(num, value);
            if (value > count) {
                count = value;
                result = num;
            }
        }
        return result;
    }

    /**
     * 先排序，再取 n/2 的值，即为众数
     *
     * @param nums
     * @return
     */
    public int sortArray(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 摩尔投票法思想：将数组分割成多段，每一段中第一个数在该段中出现的次数与其他数的次数相同，剩下最后无法拆分的一段，第一个值一定是众数。
     * 原理：因为众数的出场次数一定大于其他数的出场次数之和，若一段的第一个值不是众数，则遍历时一定会找到一个位置结束这一段（其他数的次数与第一个值的次数相同），剩下的数据中一定还存在众数，一直如此分割，直至无法分割，最后剩的一段一定是众数开头，且数量大于其他数。
     */
    public int boyerMoore(int[] nums) {
        int count = 0;
        int k = nums[0];
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == k) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                k = nums[i + 1];
            }
        }
        return k;
    }
}
