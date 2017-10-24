package com.xding.practice.pattern.singleton;

/**
 * @author xding
 * @version : v1.0
 */
public class DoubleCheckedSingleton {
    // 使用 volatile 的主要原因：禁止指令重排序优化
    private volatile static DoubleCheckedSingleton instance;

    private DoubleCheckedSingleton() {
    }

    public static DoubleCheckedSingleton getInstance() {
        //Single Checked
        if (instance == null) {
            synchronized (DoubleCheckedSingleton.class) {
                //Double Checked
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }
        return instance;
    }
}
