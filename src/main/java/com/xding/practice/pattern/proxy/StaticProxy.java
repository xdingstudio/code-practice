package com.xding.practice.pattern.proxy;

/**
 * @author xding
 * @version v1.0
 */
public class StaticProxy implements Subject {
    private RealSubject realSubject = null;

    public StaticProxy() {
    }

    @Override
    public void doSomething(String str) {
        //用到时候才加载，懒加载
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        realSubject.doSomething(str);
    }

}
