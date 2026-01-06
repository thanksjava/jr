package com.jr.booking.supplier.impl;

import com.jr.booking.model.constant.PassengerType;
import com.jr.booking.model.constant.SeatClass;
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
        // 模拟搜索结果，关联该行程的多种坐席报价
        TripInfo trip = TripInfo.builder()
                .tripId("JR_OFF_123")
                .merchantId(getSupplierCode())
                .trainNumber("Nozomi 21")
                .rawPrice(new BigDecimal("14000"))
                .onSale(true)
                .build();

        return List.of(trip);
    }

    @Override
    public List<SeatPrice> fetchSeatDetails(String tripId) {
        // 模拟从官方 API 获取实时坐席分布与价格
        return List.of(
                createSeatPrice(SeatClass.NON_RESERVED, true, "13000"), // 自由席：有票
                createSeatPrice(SeatClass.RESERVED, true, "14500"),    // 指定席：有票
                createSeatPrice(SeatClass.GREEN, false, "20000")      // 绿色车厢：售罄
        );
    }

    @Override
    public boolean checkInventory(String tripId, String seatClass, int count) {
        // 模拟下单前的实时库存同步逻辑
        // 实际业务中此处会发起一个轻量级的 HEAD 请求或库存占位请求
        return "RESERVED".equals(seatClass) && count <= 5;
    }

    private SeatPrice createSeatPrice(SeatClass clz, boolean status, String price) {
        // 构建不同人群的运费明细
        FareDetail adultFare = FareDetail.builder()
                .totalFare(new BigDecimal(price))
                .currency("JPY")
                .build();

        return SeatPrice.builder()
                .seatClass(clz)
                .inventoryStatus(status)
                .passengerPrices(Map.of(PassengerType.ADULT, adultFare))
                .build();
    }
}