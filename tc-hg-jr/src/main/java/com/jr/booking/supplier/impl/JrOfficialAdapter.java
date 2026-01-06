package com.jr.booking.supplier.impl;

import com.jr.booking.model.dto.*;
import com.jr.booking.supplier.TripSupplierAdapter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class JrOfficialAdapter implements TripSupplierAdapter {
    @Override
    public String getSupplierCode() {
        return "JR_OFFICIAL";
    }

    @Override
    public List<TripInfo> fetchStandardTrips(String depCode, String arrCode, String date) {
        // 模拟调用官方API，直接返回了标准的格式（假设）
        return List.of(TripInfo.builder()
                .tripId("JR_OFF_123")
                .merchantId(getSupplierCode())
                .rawPrice(new BigDecimal("14000"))
                .onSale(true)
                .build());
    }
}

