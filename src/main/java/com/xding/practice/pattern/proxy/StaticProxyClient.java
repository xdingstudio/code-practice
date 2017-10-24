package com.xding.practice.pattern.proxy;

/**
 * @author xding
 * @version 0.1
 */
public class StaticProxyClient {
    public static void main(String[] args) {
        StaticProxy sp = new StaticProxy();
        sp.doSomething("go!");
    }
}
