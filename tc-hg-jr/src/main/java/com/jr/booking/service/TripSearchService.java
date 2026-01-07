package com.jr.booking.service;

import com.jr.booking.model.dto.TripInfo;
import com.jr.booking.pipline.TripPipelineManager;
import com.jr.booking.pipline.context.PipelineContext;
import com.jr.booking.supplier.TripSupplierAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        List<TripInfo> rawData = adapters.parallelStream().flatMap(adapter -> adapter.fetchStandardTrips("TYO", "OSA", "2025-01-01").stream()).collect(Collectors.toList());

        // 构造强类型上下文，注入当前时间
        PipelineContext context = PipelineContext.builder().userLevel(userLevel).currentTime(LocalDateTime.now()).build();

        return pipelineManager.run(rawData, context);
    }

}