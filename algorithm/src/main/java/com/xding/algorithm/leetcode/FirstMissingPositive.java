package com.xding.algorithm.leetcode;

/**
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 * <p>
 * 输入: [7,8,9,11,12]
 * 输出: 1
 * <p>
 * 提示：
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
 * <p>
 * 链接：https://leetcode-cn.com/problems/first-missing-positive
 *
 * @author xding
 * @date 2020/4/24 20:50
 */
public class FirstMissingPositive {
    /**
     * 整数数组中，没有出现的最小的正整数，最大值为 n+1。
     * 可用原始数组标记 1-n 是否出现过，若正整数 x 出现过，为了不影响遍历，可将 num[x-1] 位置的数标记为负数，表示该数出现过。
     */
    public int firstMissingPositive(int[] nums) {
        // 判断数组中是否有 1，没有则返回 1
        boolean contain1 = false;
        for (int num : nums) {
            if (num == 1) {
                contain1 = true;
                break;
            }
        }
        if (!contain1) {
            return 1;
        }
        // 对数组进行预处理，将负数、0 和大于 n 的值置为 1
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > nums.length) {
                nums[i] = 1;
            }
        }
        // 遍历数组，将数值 x 对应的 num[x-1] 标记为负数（判断是否已经为负数）（负值时取正数的位置）
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (x < 0) {
                x = -x;
            }
            if (nums[x - 1] > 0) {
                nums[x - 1] = -nums[x - 1];
            }
        }
        // 再从 1 开始遍历一遍数组，第一次出现正值的位置 i，则返回 i+1。
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        // 若遍历完数组都没有正值，则返回 n+1
        return nums.length + 1;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 0};
        int[] nums2 = {3, 4, -1, 1};
        int[] nums3 = {7, 8, 9, 11, 12};

        FirstMissingPositive solution = new FirstMissingPositive();

        System.out.println(solution.firstMissingPositive(nums1));
        System.out.println(solution.firstMissingPositive(nums2));
        System.out.println(solution.firstMissingPositive(nums3));

    }
}
