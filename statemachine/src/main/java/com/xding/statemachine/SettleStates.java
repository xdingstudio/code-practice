package com.xding.statemachine;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liyunzheng
 * @description 结算单状态
 * @date 2018/7/11
 */
@Getter
@AllArgsConstructor
public enum SettleStates {

    /**
     * 结算单状态
     */
    WAIT_BUSINESS_SUBMIT(11, "待业务提交"),
    WAIT_FINANCE_SUBMIT(12, "待财务提交"),
    BUSINESS_COMMITTED(13, "业务提交"),
    BUSINESS_REJECTED(15, "业务驳回"),
    BUSINESS_CLOSED(14, "已关闭"),
    APPROVING(21, "审批中"),
    REJECT(22, "审批驳回"),
    WAIT_SUBMIT_PAY(31, "待提交付款"),
    WAIT_PAY(32, "待支付"),
    PAYING(33, "支付中"),
    PAY_SUCCESS(34, "付款成功"),
    PAY_FAILED(35, "付款失败"),
    RECEIVE_SUCCESS(36, "收款成功");

    private Integer code;
    private String desc;

}
