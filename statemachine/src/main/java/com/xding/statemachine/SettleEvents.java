package com.xding.statemachine;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作类型枚举类
 *
 * @author xding
 * @date 2018/8/30 14:54
 */
@Getter
@AllArgsConstructor
public enum SettleEvents {

    /**
     * 操作类型枚举类
     */
    CREATE(1, "创建"),
    SAVE(2, "保存"),
    COMMIT(3, "提交"),
    AGREE(4, "同意"),
    REJECT(5, "驳回"),
    COMMIT_PAY(6, "提交付款"),
    PAY(7, "付款"),
    DELETE(8, "删除"),
    UPDATE(9, "更新"),
    CLOSE(10, "关闭");


    private int code;
    private String desc;

}
