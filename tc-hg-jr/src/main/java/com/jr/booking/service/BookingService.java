package com.jr.booking.service;

import com.jr.booking.model.constant.OrderStatus;
import com.jr.booking.model.entity.OrderEntity;
import com.jr.booking.model.event.OrderEvent;
import com.jr.booking.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher eventPublisher;

    /**
     * 创建订单：状态流转到 PENDING_PAYMENT
     */
    @Transactional
    public OrderEntity createOrder(String tripId, String userId) {
        OrderEntity order = OrderEntity.builder()
                .orderNo("JR" + UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase())
                .tripId(tripId)
                .userId(userId)
                .status(OrderStatus.PENDING_PAYMENT)
                .createTime(LocalDateTime.now())
                .build();

        OrderEntity savedOrder = orderRepository.save(order);
        log.info("订单已创建: {}", savedOrder.getOrderNo());
        return savedOrder;
    }

    /**
     * 支付订单：状态机校验并驱动事件
     */
    @Transactional
    public void payOrder(String orderNo) {
        OrderEntity order = orderRepository.findByOrderNo(orderNo)
                .orElseThrow(() -> new RuntimeException("订单不存在: " + orderNo));

        // 状态机校验：仅 PENDING_PAYMENT 允许支付
        if (order.getStatus() != OrderStatus.PENDING_PAYMENT) {
            log.error("订单状态异常，无法支付: {}, 当前状态: {}", orderNo, order.getStatus());
            return;
        }

        // 更新状态
        order.setStatus(OrderStatus.PAYMENT_SUCCESS);
        order.setUpdateTime(LocalDateTime.now());
        orderRepository.save(order);

        // 发布支付成功事件，触发后续解耦逻辑
        eventPublisher.publishEvent(new OrderEvent(this, order, "PAY_SUCCESS"));
        log.info("订单支付成功并已发布事件: {}", orderNo);
    }
}