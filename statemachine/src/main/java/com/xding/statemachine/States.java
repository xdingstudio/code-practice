package com.xding.statemachine;

/**
 * Class Description
 *
 * @author xding
 * @date 2019-07-18 15:00
 */
public enum States {
    /**
     * 状态枚举
     */
    UNPAID,                 // 待支付
    WAITING_FOR_RECEIVE,    // 待收货
    DONE                    // 结束
}
