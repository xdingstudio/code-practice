package com.xding.algorithm;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SearchInDyadicArrayTest {
    @Test
    public void find1() throws Exception {
        SearchInDyadicArray solution = new SearchInDyadicArray();
        int target1 = 1; // 数组最小值
        int target2 = 7; // 数组中的中间值
        int target3 = 15; // 数组最大值
        int[][] array = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        boolean result1 = solution.Find(target1, array);
        boolean result2 = solution.Find(target2, array);
        boolean result3 = solution.Find(target3, array);
        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);
    }

    @Test
    public void find2() throws Exception {
        SearchInDyadicArray solution = new SearchInDyadicArray();
        int target1 = 0; // 小于数组最小值
        int target2 = 3; // 介于数组范围但无此值
        int target3 = 18; // 大于数组最大值
        int target4 = -2; // 负数
        int[][] array = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        boolean result1 = solution.Find(target1, array);
        boolean result2 = solution.Find(target2, array);
        boolean result3 = solution.Find(target3, array);
        boolean result4 = solution.Find(target4, array);
        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
        assertFalse(result4);
    }

    @Test
    public void find3() throws Exception {
        SearchInDyadicArray solution = new SearchInDyadicArray();
        int target = 7;
        int[][] array = null; // 数组为空
        boolean result = solution.Find(target, array);
        assertFalse(result);
    }

}