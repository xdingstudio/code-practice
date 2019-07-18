package com.xding.practice.pattern.singleton;

/**
 * @author xding
 * @version : v1.0
 */
public class StaticNestedClassSingleton {
    private static class SingletonHolder {
        private static StaticNestedClassSingleton INSTANCE = new StaticNestedClassSingleton();
    }

    private StaticNestedClassSingleton() {
    }

    public static final StaticNestedClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
