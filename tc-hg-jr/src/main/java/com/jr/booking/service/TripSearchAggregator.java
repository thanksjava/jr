package com.jr.booking.service;

import com.jr.booking.model.dto.TripInfo;
import com.jr.booking.supplier.TripSupplierAdapter;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TripSearchAggregator {

    // 自动注入所有实现了 TripSupplierAdapter 的 Bean
    private final List<TripSupplierAdapter> supplierAdapters;

    public TripSearchAggregator(List<TripSupplierAdapter> supplierAdapters) {
        this.supplierAdapters = supplierAdapters;
    }

    public List<TripInfo> searchAll(String dep, String arr, String date) {
        // 使用 Java 虚拟线程并行调用，屏蔽接口响应时间差异
        return supplierAdapters.parallelStream()
                .flatMap(adapter -> {
                    try {
                        return adapter.fetchStandardTrips(dep, arr, date).stream();
                    } catch (Exception e) {
                        // 错误隔离：一个供应商挂了，不影响其他供应商展示
                        System.err.println("供应商 " + adapter.getSupplierCode() + " 异常");
                        return Collections.<TripInfo>emptyList().stream();
                    }
                })
                .collect(Collectors.toList());
    }
}