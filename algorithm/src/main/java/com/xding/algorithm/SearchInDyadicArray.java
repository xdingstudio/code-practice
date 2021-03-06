package com.xding.algorithm;

public class SearchInDyadicArray {
    public boolean Find(int target, int[][] array) {
        if (array == null) {
            return false;
        }

        int row = 0;
        int col = array[0].length - 1;
        boolean found = false;

        while (row < array.length && col >= 0) {
            if (array[row][col] > target) {
                col--;
            } else if (array[row][col] < target) {
                row++;
            } else if (array[row][col] == target) {
                // 统一输出处理
                found = true;
                break;
            }
        }

        return found;
    }
}
