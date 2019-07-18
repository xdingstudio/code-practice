package com.xding.pattern.flyweight;

/**
 * @author xding
 * @version 0.1 2017/9/26
 */
public abstract class Flyweight {
    private String intrinsic;
    // 外部状态字段设为 final，避免无意修改导致逻辑混乱
    protected final String extrinsic;

    public Flyweight(String extrinsic) {
        this.extrinsic = extrinsic;
    }

    public abstract void operate();

    public String getIntrinsic() {
        return intrinsic;
    }

    public void setIntrinsic(String intrinsic) {
        this.intrinsic = intrinsic;
    }
}
