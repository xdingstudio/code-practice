package com.xding.codepractice.DesignPattern.Singleton;

/**
 * @author xding
 * @version : v1.0
 */
public class EagerSingleton {
    //在第一次加载类到内存中时就会初始化
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}
