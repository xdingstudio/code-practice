package com.xding.pattern.builder;


/**
 * @author xding
 * @version 0.1 2017/9/19
 */
public class ConcreteProduct extends Builder<Product> {
    private Product product = new Product();

    @Override
    public void setPart() {

    }

    @Override
    public Product build() {
        return product;
    }
}
