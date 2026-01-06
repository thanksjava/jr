package com.jr.booking.pipline;

import com.jr.booking.model.dto.TripInfo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author chenzhijie
 * @version Id: PriceCalculationPipe, v 1.0.0 2025/12/29 17:52 chenzhijie Exp $
 */
@Component
public class PriceCalculationPipe implements TripPipe {
    @Override
    public List<TripInfo> process(List<TripInfo> trips, Map<String, Object> context) {
        String userLevel = (String) context.getOrDefault("userLevel", "NORMAL");
        BigDecimal factor = "VIP".equals(userLevel) ? new BigDecimal("0.8") : BigDecimal.ONE;

        trips.forEach(t -> t.setFinalPrice(t.getRawPrice().multiply(factor)));
        return trips;
    }

    @Override
    public int getOrder() {
        return 10;
    }
}