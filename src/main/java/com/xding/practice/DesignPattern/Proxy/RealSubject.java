package com.xding.practice.DesignPattern.Proxy;

/**
 * @author xding
 * @version v1.0
 */
public class RealSubject implements Subject{
    @Override
    public void doSomething(String str) {
        System.out.println("do something!----->" + str);
    }
}
