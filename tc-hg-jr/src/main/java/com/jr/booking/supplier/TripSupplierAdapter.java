package com.jr.booking.supplier;

import com.jr.booking.model.dto.TripInfo;
import java.util.List;

/**
 * 供应商适配器契约：所有接入的底层供应商必须实现此接口
 */
public interface TripSupplierAdapter {
    // 标识供应商：如 JR_OFFICIAL, KLOOK, JTB
    String getSupplierCode();

    // 标准化搜索接口
    List<TripInfo> fetchStandardTrips(String depCode, String arrCode, String date);
}