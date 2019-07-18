package com.xding.pattern.factory;

/**
 * @author xding
 * @version 0.1 2017/9/25
 */
public class Client {
    public static void main(String[] args) {
        Factory factory = new ConcreteFactory();
        Product product = factory.getProduct(ConcreteProduct.class);
        product.method1();
        product.method2();
    }
}
