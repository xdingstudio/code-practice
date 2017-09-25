package com.xding.practice.designPattern.FactoryMethod;

/**
 * @author xding
 * @version 0.1 2017/9/25
 */
public abstract class Factory {
    public abstract <T extends Product> T getProduct(Class<T> c);
}
