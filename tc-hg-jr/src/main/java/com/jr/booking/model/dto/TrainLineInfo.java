package com.jr.booking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author chenzhijie
 * @version Id: TrainLineInfo, v 1.0.0 2025/12/25 17:50 chenzhijie Exp $
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainLineInfo {
    private String lineCode;
    private String trainCode;
    private String trainNo;
    private String trainType; // 1-SHINKANSEN, 2-LIMITED_EXPRESS...
    private List<String> railwayCompanyCodes;
    private String depStationCode;
    private String arrStationCode;
    private String depPlatform;
    private String arrPlatform;
    private String depTime;
    private String arrTime;
    private int arrCrossDay;
    private int duration;
    private String direction;
    private int stopCount;
    private String vehicleType;
}