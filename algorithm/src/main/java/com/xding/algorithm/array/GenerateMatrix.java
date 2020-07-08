package com.xding.algorithm.array;

import com.alibaba.fastjson.JSON;

/**
 * 59. 螺旋矩阵 II
 * <p>
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * <p>
 * 示例:
 * 输入: 3
 * 输出:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 * <p>
 * 链接：https://leetcode-cn.com/problems/spiral-matrix-ii
 *
 * @author xding
 * @date 2020/7/8 22:04
 */
public class GenerateMatrix {

    /**
     * 当 num <= tar 时，始终按照 从左到右 从上到下 从右到左 从下到上 填入顺序循环，
     * 每次填完一个循环后，更新边界，将对应的边界向内缩 1。
     * <p>
     * 使用数值作为迭代条件，是为了解决当n为奇数时，矩阵中心数字无法在迭代过程中被填充的问题。
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] arr = new int[n][n];
        int v = 1;
        int x1 = 0; int y1 = 0;
        int x2 = n - 1; int y2 = n - 1;
        while (v <= n * n) {
            for (int j = y1; j <= y2; j++) {
                arr[x1][j] = v++;
            }
            x1++;
            for (int i = x1; i <= x2; i++) {
                arr[i][y2] = v++;
            }
            y2--;
            for (int j = y2; j >= y1; j--) {
                arr[x2][j] = v++;
            }
            x2--;
            for (int i = x2; i >= x1; i--) {
                arr[i][y1] = v++;
            }
            y1++;
        }
        return arr;
    }

    public static void main(String[] args) {
        GenerateMatrix generateMatrix = new GenerateMatrix();
        int[][] ints = generateMatrix.generateMatrix(3);
        System.out.println(JSON.toJSONString(ints));
    }
}
