package com.jr.booking.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenzhijie
 * @version Id: FareDetail, v 1.0.0 2025/12/26 15:38 chenzhijie Exp $
 */
@Data
@Builder
public class FareDetail {
    private BigDecimal baseFare;    // 乘车券
    private BigDecimal expressFee; // 特急券
    private BigDecimal totalFare;   // 总价
    private String currency;       // 币种: JPY, CNY
}