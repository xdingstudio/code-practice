package com.xding.algorithm.leetcode.tree;

/**
 * 96. 不同的二叉搜索树
 * <p>
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 示例:
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * <p>
 * *    1         3     3      2      1
 * *     \       /     /      / \      \
 * *      3     2     1      1   3      2
 * *     /     /       \                 \
 * *    2     1         2                 3
 * <p>
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 *
 * @author xding
 * @date 2020/6/2 11:23
 */
public class NumTrees {

    private int[][] sums;

    /**
     * 回溯+存储计算结果，可改成动态规划
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        sums = new int[n + 2][n + 2];
        return helper(1, n);
    }

    private int helper(int start, int end) {
        if (sums[start][end] != 0) {
            return sums[start][end];
        }
        if (start >= end) {
            return 1;
        }
        int s = 0;
        for (int i = start; i <= end; i++) {
            s += helper(start, i - 1) * helper(i + 1, end);
        }
        sums[start][end] = s;
        return s;
    }

    public static void main(String[] args) {
        NumTrees numTrees = new NumTrees();
        System.out.println(numTrees.numTrees(3));
    }
}
