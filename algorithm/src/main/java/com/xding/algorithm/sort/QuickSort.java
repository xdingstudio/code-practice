package com.xding.algorithm.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class Description
 *
 * @author xding
 * @date 2020/5/6 17:11
 */
public class QuickSort {

    private static final Logger log = LoggerFactory.getLogger(QuickSort.class);

    /**
     * 核心思想：
     * 从数组中取一个数据作为 pivot（分区点），将数组按大于或小于 pivot 调整，将小于 pivot 的放到一侧，大于 pivot 的放到另一侧，pivot 放到中间。
     * 再分别对两侧的数据做相同的处理，直至全部数据区间缩小为 1 时，完成数组排序。
     * <p>
     * 原地分区方法思想：
     * 取数组 A[] 末尾数据为 pivot，在数组头部设置指针 i、j，i 用来标记小于 povit 的数据下一次应插入的位置（遍历结束后即为 pivot 的最终位置），
     * j 用来遍历数据，当 A[j] 小于 pivot 时，交换 A[i] 与 A[j]，并将 i 往下移一位，直至 j 到达数组末尾时，交换  A[i] 与 pivot，即实现了数组的分区。
     *
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int begin, int end) {
        if (begin < 0 || end >= nums.length || begin >= end) {
            return;
        }
        int p = partition(nums, begin, end);
        log.info("begin:{},end:{},p:{},nums:{}", begin, end, p, nums);
        quickSort(nums, begin, p - 1);
        quickSort(nums, p + 1, end);
    }

    private int partition(int[] nums, int begin, int end) {
        if (begin == end) {
            return begin;
        }
        int pivot = nums[end];
        int i = begin;
        for (int j = begin; j < end; j++) {
            if (nums[j] < pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, end);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] nums = {5, 2, 3, 1};
        quickSort.sortArray(nums);
        log.info("sort finish! nums:{}", nums);
    }
}
