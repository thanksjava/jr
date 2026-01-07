package com.jr.booking.pipline.context;

import com.jr.booking.model.constant.PassengerType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 强类型 Pipeline 上下文，替代原有的 Map 结构
 */
@Data
@Builder
public class PipelineContext {
    private String userLevel;          // VIP, NORMAL
    private PassengerType passengerType; // 成人, 儿童
    private boolean hasOversizedBaggage; // 是否携带大件行李
    private String lang;
    private LocalDateTime currentTime;// 语言：zh_CN, ja_JP

    // 预留可扩展的自定义属性存储（仅用于极特殊情况）
    private java.util.Map<String, Object> extensions;
}