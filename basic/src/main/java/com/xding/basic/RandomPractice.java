package com.xding.basic;

import java.util.Random;

/**
 * @author xding
 * @version 0.1 2017/9/18
 */
public class RandomPractice {
    public int[] random(int k, int n) {
        Random random = new Random();
        int[] intArray = new int[k];
        // 因为虚拟机类型擦除，参数类型只能为对象，不能用基本类型
        // List<int> list = new ArrayList(n);  error!
        for (int i = 0; i < k; i++) {
            intArray[i] = random.nextInt(n);
        }
        return intArray;
    }
}
