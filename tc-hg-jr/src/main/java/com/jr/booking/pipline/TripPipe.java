package com.jr.booking.pipline;

import com.jr.booking.model.dto.TripInfo;
import com.jr.booking.pipline.context.PipelineContext;

/**
 * 优化后的 Pipe 接口：专注于单体处理
 */
public interface TripPipe {

    /**
     * 处理单个行程数据
     */
    void process(TripInfo trip, PipelineContext context);

    /**
     * 执行顺序
     */
    int getOrder();
}