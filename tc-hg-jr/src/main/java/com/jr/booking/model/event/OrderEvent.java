package com.jr.booking.model.event;

import com.jr.booking.model.entity.OrderEntity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 订单基础事件
 */
@Getter
public class OrderEvent extends ApplicationEvent {
    private final OrderEntity order;
    private final String eventType; // 如: PAY, ISSUE, CANCEL

    public OrderEvent(Object source, OrderEntity order, String eventType) {
        super(source);
        this.order = order;
        this.eventType = eventType;
    }
}