package com.xding.practice.DesignPattern.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * created at 2017/8/30 14:55
 *
 * @author xding
 * @version 0.1
 */
public class DynamicProxyClient {
    public static void main(String[] args) {
        Subject subject = new RealSubject();
        InvocationHandler handler = new DynamicProxyHandler(subject);
        Subject proxy = (Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(), handler);
        proxy.doSomething("go!");


    }
}
