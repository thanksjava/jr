package com.jr.booking.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author chenzhijie
 * @version Id: FareQuote, v 1.0.0 2025/12/26 15:28 chenzhijie Exp $
 */
@Data
@Builder
public class FareQuote {
    private String merchantId;      // 供应商ID: JR_OFFICIAL, KLOOK, JTB
    private String merchantName;    // 供应商名称
    private List<SeatPrice> seatPrices; // 该供应商下的多坐席报价
}