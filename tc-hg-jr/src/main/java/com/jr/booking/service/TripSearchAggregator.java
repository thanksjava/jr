package com.jr.booking.service;

import com.jr.booking.model.dto.TripInfo;
import com.jr.booking.supplier.TripSupplierAdapter;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class TripSearchAggregator {

    private final List<TripSupplierAdapter> supplierAdapters;
    // 使用虚拟线程池（Spring Boot 4.0 已配置开启）
    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    public TripSearchAggregator(List<TripSupplierAdapter> supplierAdapters) {
        this.supplierAdapters = supplierAdapters;
    }

    public List<TripInfo> searchAll(String dep, String arr, String date) {
        List<CompletableFuture<List<TripInfo>>> futures = supplierAdapters.stream()
                .map(adapter -> CompletableFuture.supplyAsync(() -> {
                    try {
                        // 每个供应商限时 3 秒返回结果
                        return adapter.fetchStandardTrips(dep, arr, date);
                    } catch (Exception e) {
                        System.err.println("供应商 " + adapter.getSupplierCode() + " 响应超时或异常");
                        return Collections.<TripInfo>emptyList();
                    }
                }, executor).completeOnTimeout(Collections.emptyList(), 3, TimeUnit.SECONDS))
                .toList();

        return futures.stream()
                .map(CompletableFuture::join) // 合并结果
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}