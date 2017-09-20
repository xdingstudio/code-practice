package com.xding.practice.designPattern.Builder;

/**
 * @author xding
 * @version 0.1 2017/9/20
 */
public class Director {
    private Builder<Product> builder = new ConcreteProduct();

    public Product getAProduct() {
        builder.setPart();
        return builder.build();
    }
}
