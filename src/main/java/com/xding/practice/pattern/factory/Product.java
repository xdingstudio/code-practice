package com.xding.practice.pattern.factory;

/**
 * @author xding
 * @version 0.1 2017/9/25
 */
public abstract class Product {
    public void method1(){
        System.out.println("执行抽象类" + Product.class.getName() + "的 method1() 方法");
    }
    public abstract void method2();
}
