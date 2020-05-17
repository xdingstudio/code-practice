package com.xding.algorithm.search;

/**
 * 二分查找
 *
 * @author xding
 * @date 2020/1/13 17:00
 */
public class BinarySearch {

    /**
     * 二分查找的递归实现
     *
     * @param a
     * @param n
     * @param val
     * @return
     */
    public int binarySearch(int[] a, int n, int val) {
        return binarySearchInternally(a, 0, n - 1, val);
    }

    private int binarySearchInternally(int[] a, int low, int high, int val) {
        if (low > high) {
            return -1;
        }

        // 改进加法，避免数据范围过大时加和溢出
        int mid = low + (high - low) / 2;
        if (a[mid] == val) {
            return mid;
        } else if (a[mid] < val) {
            return binarySearchInternally(a, mid + 1, high, val);
        } else {
            return binarySearchInternally(a, low, mid - 1, val);
        }

    }
}
