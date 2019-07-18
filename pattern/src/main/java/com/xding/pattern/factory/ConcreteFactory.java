package com.xding.pattern.factory;

/**
 * @author xding
 * @version 0.1 2017/9/25
 */
public class ConcreteFactory extends Factory {
    @Override
    public <T extends Product> T getProduct(Class<T> c) {
        T product = null;
        try {
            product = (T) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
}
