package com.xding.algorithm.leetcode;

import java.util.ArrayDeque;

/**
 * 滑动窗口最大值
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * <p>
 * 进阶：
 * 你能在线性时间复杂度内解决此题吗？
 * <p>
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 *  
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 * <p>
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 *
 * @author xding
 * @date 2020/5/3 23:44
 */
public class MaxSlidingWindow {

    /**
     * 递归，用一个二维数组存储每次递归的最大值，递归公式：
     * 1.max[1][j] = nums[j]
     * 2.max[i][j] = max[i-1][j] > nums[j+i-1] ? max[i-1][j] : nums[j+i-1];
     * 1 <= i <= k; 0 <= j <= length - i;
     * 可用循环实现
     * max[0][j] = nums[j]时，1 公式可用 2 代替
     * <p>
     * 二维数组空间复杂度O(k*n)，改用一维数组
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] max = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            max[i] = nums[i];
        }
        for (int n = 1; n <= k; n++) {
            for (int i = 0; i <= nums.length - n; i++) {
                max[i] = max[i] > nums[i + n - 1] ? max[i] : nums[i + n - 1];
            }
        }
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            result[i] = max[i];
        }
        return result;
    }

    /**
     * 可以利用一个双端队列来表示这个窗口。这个双端队列保存当前窗口中最大那个数的下标，双端队列新的头总是当前窗口中最大的那个数。
     * 同时，有了这个下标，我们可以很快地知道新的窗口是否已经不再包含原来那个最大的数，如果不再包含，我们就把旧的数从双端队列的头删除。
     * 按照这样的操作，不管窗口的长度是多长，因为数组里的每个数都分别被压入和弹出双端队列一次，所以我们可以在 O(n) 的时间里完成任务。
     * <p>
     * 这个维护逻辑就是单调栈（Monotonous Stack，也可以说是单调队列）。本题根据题设，是要求栈上元素只能单调递减，即[5,4,3,1]是一个有效的单调栈状态，
     * 当遇到了新元素2，则不能直接压栈（否则就不单调递减了），必须先把比2小的元素（即末尾的1）弹出，再压栈——[5,4,3,2]。
     * 在题目中，实际上栈中的元素总是滑动窗口元素的子集（因为可能有弹出操作），所以栈顶元素必然是窗口的最大值。
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        ArrayDeque<Integer> deq = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];

        int n = nums.length;
        if (n == 0 || k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }

        /*
         * 处理前 k 个元素，初始化双向队列。
         * 遍历整个数组。在每一步 :
         * 清理双向队列 :
         *   - 只保留当前滑动窗口中有的元素的索引。
         *   - 移除比当前元素小的所有元素，它们不可能是最大的。
         * 将当前元素添加到双向队列中。
         * 将 deque[0] 添加到输出中。
         * 返回输出数组。
         */
        int index;
        for (int i = 0; i < nums.length; i++) {
            cleanQueue(nums, deq, i, k);
            deq.add(i);
            if (i < k) {
                index = 0;
            } else {
                index = i - k + 1;
            }
            result[index] = nums[deq.getFirst()];
        }
        return result;
    }

    private void cleanQueue(int[] nums, ArrayDeque<Integer> deq, int i, int k) {
        if (!deq.isEmpty() && deq.getFirst() + k == i) {
            deq.removeFirst();
        }
        while (!deq.isEmpty() && nums[deq.getLast()] < nums[i]) {
            deq.removeLast();
        }
    }
}
