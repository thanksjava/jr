package com.jr.booking.listener;

import com.jr.booking.model.constant.OrderStatus;
import com.jr.booking.model.entity.OrderEntity;
import com.jr.booking.model.event.OrderEvent;
import com.jr.booking.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class IssueListener {

    private final OrderRepository orderRepository;

    /**
     * 监听支付成功事件，执行异步出票
     *
     * @Async 会在独立的虚拟线程中运行
     */
    @Async
    @EventListener(condition = "#event.eventType == 'PAY_SUCCESS'")
    public void handleIssueTask(OrderEvent event) {
        OrderEntity order = event.getOrder();
        log.info("监听到支付成功，开始异步出票逻辑: {}", order.getOrderNo());

        try {
            // 1. 修改状态为出票中
            order.setStatus(OrderStatus.ISSUING);
            orderRepository.save(order);

            // 2. 模拟请求 JR 官方 API 耗时
            Thread.sleep(3000);

            // 3. 修改状态为出票成功
            order.setStatus(OrderStatus.ISSUE_SUCCESS);
            order.setUpdateTime(LocalDateTime.now());
            orderRepository.save(order);
            log.info("订单出票成功完成: {}", order.getOrderNo());

        } catch (Exception e) {
            log.error("订单出票异常: {}", order.getOrderNo(), e);
            order.setStatus(OrderStatus.ISSUE_FAILED);
            orderRepository.save(order);
        }
    }
}