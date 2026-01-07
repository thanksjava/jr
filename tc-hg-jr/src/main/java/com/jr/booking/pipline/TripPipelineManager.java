package com.jr.booking.pipline;

import com.jr.booking.model.dto.TripInfo;
import com.jr.booking.pipline.context.PipelineContext;

import java.util.List;

public interface TripPipelineManager {

    List<TripInfo> run(List<TripInfo> data, PipelineContext context);
}