package com.jr.booking.model.constant;

import lombok.Getter;

/**
 * 订单状态枚举
 */
@Getter
public enum OrderStatus {
    INIT("待下单"),
    PENDING_PAYMENT("待支付"),
    PAYMENT_SUCCESS("支付成功"),
    ISSUING("出票中"),
    ISSUE_SUCCESS("出票成功"),
    ISSUE_FAILED("出票失败"),
    CANCELLED("已取消"),
    REFUNDING("退款中"),
    REFUNDED("已退款");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }
}