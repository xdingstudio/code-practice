package com.xding.algorithm.leetcode;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例:
 * <p>
 * 现有矩阵 matrix 如下：
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * <p>
 * 给定 target = 20，返回 false。
 * <p>
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
 *
 * @author xding
 * @date 2020/4/4 18:54
 */
public class SearchMatrix {

    public boolean solution1(int[][] matrix, int target) {
        // start our "pointer" in the bottom-left
        int row = matrix.length - 1;
        int col = 0;

        while (row >= 0 && col <= (matrix[0].length - 1)) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            }
        }
        return false;
    }

    public boolean solution2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        return searchRec(matrix, target, 0, 0, matrix[0].length - 1, matrix.length - 1);

    }

    private boolean searchRec(int[][] matrix, int target, int left, int up, int right, int down) {

        if (left > right || up > down) {
            // 越界判断
            return false;
        } else if (matrix[left][up] > target || matrix[down][right] < target) {
            // 最小值大于目标值，或最大值小于目标值
            return false;
        }

        int mid = left + (right - left) / 2;

        // Locate `row` such that matrix[row-1][mid] < target < matrix[row][mid]
        int row = up;
        while (row <= down && matrix[row][mid] <= target) {
            if (matrix[row][mid] == target) {
                return true;
            }
            row++;
        }

        return searchRec(matrix, target, mid + 1, up, right, row - 1) ||
            searchRec(matrix, target, left, row, mid - 1, down);
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        int target = 5;

        SearchMatrix searchMatrix = new SearchMatrix();

        System.out.println("solution1 result:" + searchMatrix.solution1(matrix, target));
        System.out.println("solution2 result:" + searchMatrix.solution2(matrix, target));

    }
}
