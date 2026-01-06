package com.jr.booking.supplier;

import com.jr.booking.model.dto.TripInfo;
import com.jr.booking.model.dto.SeatPrice;
import java.util.List;

public interface TripSupplierAdapter {
    String getSupplierCode();

    // 基础搜索接口
    List<TripInfo> fetchStandardTrips(String depCode, String arrCode, String date);

    // 新增：获取指定行程的实时坐席与余票详情
    List<SeatPrice> fetchSeatDetails(String tripId);

    // 新增：下单前的最终库存校验
    boolean checkInventory(String tripId, String seatClass, int count);
}