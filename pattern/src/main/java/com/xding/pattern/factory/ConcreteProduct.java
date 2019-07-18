package com.xding.pattern.factory;

/**
 * @author xding
 * @version 0.1 2017/9/25
 */
public class ConcreteProduct extends Product {
    @Override
    public void method2() {
        System.out.println("执行子类" + this.getClass().getName() + "的 method2() 方法");
    }
}
