package com.xding.practice.designPattern.Singleton;

/**
 * @author xding
 * @version : v1.0
 */
public class EnumSingleton {
    public enum EasySingleton {
        INSTANCE;

        public void doSomething() {
        }
    }

    public void testEnumSingleton(){
        EasySingleton.INSTANCE.doSomething();
    }

}
