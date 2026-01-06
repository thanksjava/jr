package com.jr.booking.model.dto;

import com.jr.booking.model.constant.PassengerType;
import com.jr.booking.model.constant.SeatClass;
import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class SeatPrice {
    private SeatClass seatClass;     // 坐席等级
    private boolean inventoryStatus; // 实时是否有票
    private String seatMapUrl;       // 选座图 URL（如有）
    private Map<PassengerType, FareDetail> passengerPrices; // 不同人群价格
}