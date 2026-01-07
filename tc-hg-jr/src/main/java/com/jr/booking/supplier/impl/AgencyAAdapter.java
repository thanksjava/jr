package com.jr.booking.supplier.impl;


import com.jr.booking.model.dto.*;
import com.jr.booking.supplier.TripSupplierAdapter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class AgencyAAdapter implements TripSupplierAdapter {
    @Override
    public String getSupplierCode() {
        return "AGENCY_A";
    }

    @Override
    public List<TripInfo> fetchStandardTrips(String depCode, String arrCode, String date) {
        // 模拟调用第三方API，它的数据结构很奇怪（差异点）
        // 比如它返回的价格是 String，日期格式是 Unix 时间戳，且不含消费税
        Map<String, Object> rawData = new HashMap<>();
        rawData.put("cost", "120.5"); // 美金字符串
        rawData.put("vendor_status", "OPEN");

        // 在这里进行【逻辑体会】：屏蔽差异性，转换为我们的标准模型
        BigDecimal priceInJpy = new BigDecimal("120.5").multiply(new BigDecimal("150")); // 汇率换算

        return List.of(TripInfo.builder()
                .tripId("AG_A_XYZ")
                .merchantId(getSupplierCode())
                .rawPrice(priceInJpy) // 屏蔽了原始数据的币种差异
                .onSale("OPEN".equals(rawData.get("vendor_status"))) // 屏蔽了状态码差异
                .build());
    }

    @Override
    public List<SeatPrice> fetchSeatDetails(String tripId) {
        return List.of();
    }

    @Override
    public boolean checkInventory(String tripId, String seatClass, int count) {
        return false;
    }
}