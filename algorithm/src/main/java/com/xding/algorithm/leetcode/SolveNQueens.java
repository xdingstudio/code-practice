package com.xding.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * N皇后
 * <p>
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 示例:
 * 输入: 4
 * 输出: [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 * <p>
 * 提示：
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或七步，可进可退。（引用自 百度百科 - 皇后 ）
 * <p>
 * 链接：https://leetcode-cn.com/problems/n-queens
 *
 * @author xding
 * @date 2020/5/10 18:27
 */
public class SolveNQueens {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> resultList = new ArrayList<>();
        int[] result = new int[n];
        solve(0, n, result, resultList);
        return resultList;
    }

    private void solve(int row, int n, int[] result, List<List<String>> resultList) {
        if (row < 0 || row >= n) {
            // 找到符合条件的组合，输出结果
            resultList.add(printQueens(result));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isOk(row, col, result, n)) {
                // 保存结果
                result[row] = col;
                // 考察下一行
                solve(row + 1, n, result, resultList);
            }
        }
    }

    private boolean isOk(int row, int col, int[] result, int n) {
        int leftUpCol = col - 1;
        int rightUpCol = col + 1;

        for (int i = row - 1; i >= 0; i--) {
            if (result[i] == col) {
                return false;
            }
            if (leftUpCol >= 0 && result[i] == leftUpCol) {
                return false;
            }
            if (rightUpCol < n && result[i] == rightUpCol) {
                return false;
            }
            leftUpCol--;
            rightUpCol++;
        }

        return true;
    }

    public List<String> printQueens(int[] result) {
        // 打印出一个二维矩阵
        List<String> resultList = new ArrayList<>(result.length);
        for (int row = 0; row < result.length; ++row) {
            String rowString = "";
            for (int column = 0; column < result.length; ++column) {
                if (result[row] == column) {
                    rowString += "Q";
                } else {
                    rowString += ".";
                }
            }
            resultList.add(rowString);
        }
        return resultList;
    }

    public static void main(String[] args) {
        SolveNQueens solveNQueens = new SolveNQueens();
        List<List<String>> resultList = solveNQueens.solveNQueens(8);
        for (List<String> result : resultList) {
            System.out.println(result);
        }

    }
}
