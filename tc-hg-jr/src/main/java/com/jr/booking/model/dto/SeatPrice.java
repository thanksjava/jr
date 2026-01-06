package com.jr.booking.model.dto;

import com.jr.booking.model.constant.PassengerType;
import com.jr.booking.model.constant.SeatClass;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @author chenzhijie
 * @version Id: SeatPrice, v 1.0.0 2025/12/26 15:30 chenzhijie Exp $
 */
@Data
@Builder
public class SeatPrice {
    private SeatClass seatClass;    // 坐席等级
    private boolean inventoryStatus; // 是否有票
    // Key 为乘客类型，Value 为价格明细
    private Map<PassengerType, FareDetail> passengerPrices;
}