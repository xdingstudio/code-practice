package com.xding.practice.DesignPattern.Singleton;

/**
 * @author xding
 * @version : v1.0
 */
public class LazySafeSingleton  {
    private static LazySafeSingleton instance;

    private LazySafeSingleton() {
    }

    public static synchronized LazySafeSingleton getInstance() {
        if (instance == null) {
            instance = new LazySafeSingleton();
        }

        return instance;
    }
}
