package com.jr.booking.pipline;

import com.jr.booking.model.dto.TripInfo;
import com.jr.booking.pipline.context.PipelineContext;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 过期车次过滤器：过滤出发时间早于当前时间的行程
 */
@Component
public class TimeFilterPipe implements TripPipe {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void process(TripInfo trip, PipelineContext context) {
        if (!trip.isAvailable()) return; // 如果已经被之前的 Pipe 过滤，直接跳过

        try {
            // 假设 depTime 格式为 "yyyy-MM-dd HH:mm:ss"
            LocalDateTime depTime = LocalDateTime.parse(trip.getDepTime(), FORMATTER);

            // 如果出发时间早于当前基准时间，标记为不可用
            if (depTime.isBefore(context.getCurrentTime())) {
                trip.setAvailable(false);
            }
        } catch (Exception e) {
            // 时间解析失败时，默认保留或根据业务逻辑处理
            trip.setAvailable(false);
        }
    }

    @Override
    public int getOrder() {
        return 5; // 过滤逻辑应该尽早执行，建议排在价格计算（Order 10）之前
    }
}