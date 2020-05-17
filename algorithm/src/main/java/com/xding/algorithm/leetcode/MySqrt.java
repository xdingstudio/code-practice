package com.xding.algorithm.leetcode;

/**
 * x 的平方根
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * 输入: 4
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 * <p>
 * 链接：https://leetcode-cn.com/problems/sqrtx
 *
 * @author xding
 * @date 2020/5/17 17:50
 */
public class MySqrt {

    /**
     * 从 0 开始求 x^2，若结果小于等于 n，则 +1 并重复计算，结果大于 n 时，返回 x-1
     * 特殊值：n=0时：0，n=1时：1
     */
    public int mySqrt(int n) {
        if (n == 0) {
            return 0;
        }
        int i = 1;
        while (true) {
            if (i * i > n) {
                break;
            }
            i++;
        }
        return i - 1;
    }

    /**
     * 从 0 到 n 进行二分查找，每次取中间值 mid，若 mid^2 > n，right = mid-1，若  mid^2 <= n，left = mid+1;
     *
     * @param n
     * @return
     */
    public int mySqrt2(int n) {
        if (n == 0) {
            return 0;
        }
        int left = 0;
        int right = n;
        int mid;
        int ans = -1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if ((long) mid * mid <= n) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
}
