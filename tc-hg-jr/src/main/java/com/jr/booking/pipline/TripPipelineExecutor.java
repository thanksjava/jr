package com.jr.booking.pipline;

import com.jr.booking.model.dto.TripInfo;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chenzhijie
 * @version Id: TripPipelineExecutor, v 1.0.0 2025/12/29 18:04 chenzhijie Exp $
 */
@Component
class TripPipelineExecutor implements TripPipelineManager {
    private final List<TripPipe> pipes;

    // Spring IOC: 自动注入所有 TripPipe 接口实现类
    public TripPipelineExecutor(List<TripPipe> pipes) {
        this.pipes = pipes.stream().sorted(Comparator.comparingInt(TripPipe::getOrder)).collect(Collectors.toList());
    }

    @Override
    public List<TripInfo> run(List<TripInfo> data, Map<String, Object> context) {
        List<TripInfo> currentData = data;
        for (TripPipe pipe : pipes) {
            currentData = pipe.process(currentData, context);
        }
        return currentData;
    }
}