package com.xding.codepractice.DesignPattern.Singleton;

public class LazyUnsafeSingleton{
    private static LazyUnsafeSingleton instance;

    private LazyUnsafeSingleton() {
    }

    public LazyUnsafeSingleton getInstance() {
        if (instance == null) {
            instance = new LazyUnsafeSingleton();
        }
        return instance;
    }
}
