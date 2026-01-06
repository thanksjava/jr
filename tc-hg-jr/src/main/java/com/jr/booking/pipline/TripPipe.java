package com.jr.booking.pipline;

import com.jr.booking.model.dto.TripInfo;

import java.util.List;
import java.util.Map;

/**
 * @author chenzhijie
 * @version Id: TripPipe, v 0.1 2025/12/29 17:46 chenzhijie Exp $
 */
public interface TripPipe {

    List<TripInfo> process(List<TripInfo> trips, Map<String, Object> context);

    int getOrder(); // 执行顺序
}
