package com.jr.booking.model.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

/**
 * 票价明细模型：支持 JR 乘车券与特急券分离逻辑
 */
@Data
@Builder
public class FareDetail {
    private BigDecimal baseFare;    // 乘车券（基础运费）
    private BigDecimal expressFee;  // 特急券（特快费用）
    private BigDecimal totalFare;   // 总价
    private String currency;       // 币种

    /**
     * 静态工厂方法：创建并自动计算总价
     */
    public static FareDetail create(BigDecimal base, BigDecimal express, String currency) {
        return FareDetail.builder()
                .baseFare(base)
                .expressFee(express)
                .totalFare(base.add(express))
                .currency(currency)
                .build();
    }
}