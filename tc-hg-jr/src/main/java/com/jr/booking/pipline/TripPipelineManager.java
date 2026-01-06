package com.jr.booking.pipline;

import com.jr.booking.model.dto.TripInfo;

import java.util.List;
import java.util.Map;

public interface TripPipelineManager {

    List<TripInfo> run(List<TripInfo> data, Map<String, Object> context);
}