package com.jr.booking.model.entity;

import com.jr.booking.model.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "jr_orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNo;       // 业务订单号
    private String tripId;        // 关联行程ID
    private String userId;        // 用户ID
    private BigDecimal amount;    // 支付金额

    @Enumerated(EnumType.STRING)
    private OrderStatus status;   // 当前状态

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}