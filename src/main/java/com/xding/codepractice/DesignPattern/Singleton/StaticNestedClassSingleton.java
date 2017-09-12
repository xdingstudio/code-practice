package com.xding.codepractice.DesignPattern.Singleton;

/**
 * @author xding
 * @version : v1.0
 */
public class StaticNestedClassSingleton {
    private static class SingletonHolder {
        private static final StaticNestedClassSingleton INSTANCE = new StaticNestedClassSingleton();
    }

    private StaticNestedClassSingleton() {
    }

    public static final StaticNestedClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
