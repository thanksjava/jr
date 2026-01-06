package com.jr.booking.service;

import com.jr.booking.model.dto.TripInfo;
import com.jr.booking.pipline.TripPipelineManager;
import com.jr.booking.supplier.TripSupplierAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chenzhijie
 * @version Id: TripSearchService, v 1.0.0 2025/12/29 17:54 chenzhijie Exp $
 */
@Service
@RequiredArgsConstructor
public class TripSearchService {

    // 面向接口注入：不依赖具体哪一个适配器或哪一个加工逻辑
    private final List<TripSupplierAdapter> adapters;
    private final TripPipelineManager pipelineManager;

    public List<TripInfo> search(String userLevel) {
        // 1. 调用所有适配器并行获取数据
        List<TripInfo> rawData = adapters.parallelStream()
                .flatMap(adapter -> adapter.fetchStandardTrips("TYO", "OSA", "2025-01-01").stream())
                .collect(Collectors.toList());

        // 2. 执行流水线加工
        Map<String, Object> context = Map.of("userLevel", userLevel);
        return pipelineManager.run(rawData, context);
    }

}