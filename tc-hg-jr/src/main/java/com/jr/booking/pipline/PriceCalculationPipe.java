package com.jr.booking.pipline;

import com.jr.booking.model.dto.TripInfo;
import com.jr.booking.pipline.context.PipelineContext;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class PriceCalculationPipe implements TripPipe {

    @Override
    public void process(TripInfo trip, PipelineContext context) {
        // 仅处理传入的单条数据
        BigDecimal factor = "VIP".equals(context.getUserLevel()) ? new BigDecimal("0.8") : BigDecimal.ONE;

        if (trip.getRawPrice() != null) {
            trip.setFinalPrice(trip.getRawPrice().multiply(factor));
        }
    }

    @Override
    public int getOrder() {
        return 10;
    }
}