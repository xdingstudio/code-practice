package com.xding.pattern.singleton;

/**
 * @author xding
 * @version : v1.0
 */
public class EnumSingleton {
    public enum EasySingleton {
        /**
         * 采用枚举类实现单例
         */
        INSTANCE;

        public void doSomething() {
        }
    }

    public void testEnumSingleton(){
        EasySingleton.INSTANCE.doSomething();
    }

}
