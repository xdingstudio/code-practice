package com.xding.pattern.builder;

/**
 * @author xding
 * @version 0.1 2017/9/20
 */
public abstract class Builder<T> {
    public abstract void setPart();
    public abstract T build();
}
