package com.jr.booking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


/**
 * @author chenzhijie
 * @version Id: TripInfo, v 1.0.0 2025/12/25 17:48 chenzhijie Exp $
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripInfo {

    private String tripId;         // 行程ID


    private String tripType; // SINGLE_LINE, TRANSFER, CROSSLINE_DIRECT
    private String depTime;
    private String arrTime;
    private int arrCrossDay;
    private int duration;
    private String depStationCode;
    private String arrStationCode;
    private Integer transferTime;
    private int transferCount;
    private List<Integer> transferFlag;
    private boolean onSale;
    private String merchantId;     // 供应商ID
    private String trainNumber;    // 车次号 (用于去重)
    private BigDecimal rawPrice;   // 供应商原始价
    private BigDecimal finalPrice; // 计算后的最终售卖价
    private List<String> tags;     // 业务标签
    private boolean isAvailable;   // 是否可用

    private List<TrainLineInfo> subLines;
}