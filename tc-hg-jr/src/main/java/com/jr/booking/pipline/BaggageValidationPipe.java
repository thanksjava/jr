package com.jr.booking.pipline;

import com.jr.booking.model.dto.TripInfo;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 大件行李预约校验流水线
 */
@Component
public class BaggageValidationPipe implements TripPipe {

    // 模拟需要强制预约大件行李的车站区间（例如：东京、名古屋、新大阪等）
    private static final Set<String> OVERSIZED_STATIONS = Set.of("TYO", "NGO", "OSA");

    @Override
    public List<TripInfo> process(List<TripInfo> trips, Map<String, Object> context) {
        boolean hasOversizedBaggage = (boolean) context.getOrDefault("hasOversizedBaggage", false);

        trips.forEach(trip -> {
            // 如果用户携带了大件行李，且该行程属于新干线区间
            if (hasOversizedBaggage && "1".equals(trip.getTripType())) {
                // 校验行程是否涉及特定车站
                if (OVERSIZED_STATIONS.contains(trip.getDepStationCode()) ||
                        OVERSIZED_STATIONS.contains(trip.getArrStationCode())) {
                    trip.getTags().add("REQUIRED_BAGGAGE_RESERVATION"); // 注入业务标签
                }
            }
        });
        return trips;
    }

    @Override
    public int getOrder() {
        return 20; // 在价格计算之后执行
    }
}