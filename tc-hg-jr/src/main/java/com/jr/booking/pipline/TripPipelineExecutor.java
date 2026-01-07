package com.jr.booking.pipline;

import com.jr.booking.model.dto.TripInfo;
import com.jr.booking.pipline.context.PipelineContext;
import org.springframework.stereotype.Component;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TripPipelineExecutor implements TripPipelineManager {

    private final List<TripPipe> pipes;

    public TripPipelineExecutor(List<TripPipe> pipes) {
        this.pipes = pipes.stream()
                .sorted(Comparator.comparingInt(TripPipe::getOrder))
                .collect(Collectors.toList());
    }

    @Override
    public List<TripInfo> run(List<TripInfo> data, PipelineContext context) {
        if (data == null || data.isEmpty()) return data;

        // 1. 单次循环：依次执行所有 Pipe 加工逻辑
        data.parallelStream().forEach(trip -> {
            for (TripPipe pipe : pipes) {
                // 如果在中间步骤被标记为不可用，可以视业务情况中断后续 Pipe
                if (!trip.isAvailable()) break;
                pipe.process(trip, context);
            }
        });

        // 2. 统一过滤：剔除所有在 Pipeline 中被标记为不可用的数据
        return data.stream()
                .filter(TripInfo::isAvailable)
                .collect(Collectors.toList());
    }
}