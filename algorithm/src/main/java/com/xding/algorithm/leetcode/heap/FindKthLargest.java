package com.xding.algorithm.leetcode.heap;

import java.util.PriorityQueue;

/**
 * 215. 数组中的第K个最大元素
 * <p>
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * <p>
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * <p>
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 * <p>
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 *
 * @author xding
 * @date 2020/6/4 17:04
 */
public class FindKthLargest {

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
            return -1;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(k, ((o1, o2) -> o1 - o2));
        for (int i = 0; i < nums.length; i++) {
            heap.add(nums[i]);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        Integer result = heap.poll();
        if (result != null) {
            return result;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        FindKthLargest obj = new FindKthLargest();
        int[] arry = {3, 2, 1, 5, 6, 4};
        System.out.println(obj.findKthLargest(arry, 2));
    }
}
