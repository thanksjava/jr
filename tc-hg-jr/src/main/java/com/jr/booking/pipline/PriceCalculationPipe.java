package com.jr.booking.pipline;

import com.jr.booking.model.constant.PassengerType;
import com.jr.booking.model.dto.TripInfo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@Component
public class PriceCalculationPipe implements TripPipe {

    @Override
    public List<TripInfo> process(List<TripInfo> trips, Map<String, Object> context) {
        // 从上下文中获取用户信息
        String userLevel = (String) context.getOrDefault("userLevel", "NORMAL");
        PassengerType pType = (PassengerType) context.getOrDefault("passengerType", PassengerType.ADULT);

        trips.forEach(trip -> {
            // 1. 获取供应商原始价格（假设原始价为成人、基础席位的价格）
            BigDecimal rawBase = trip.getRawPrice();

            // 2. 计算人群倍率：儿童 0.5，成人 1.0
            BigDecimal personFactor = (pType == PassengerType.CHILD) ? new BigDecimal("0.5") : BigDecimal.ONE;

            // 3. 模拟 JR 计费拆解（实际业务中应从数据库或供应商获取精确分段）
            // 假设特急券约占总价的 40%
            BigDecimal baseFare = rawBase.multiply(new BigDecimal("0.6")).multiply(personFactor);
            BigDecimal expressFee = rawBase.multiply(new BigDecimal("0.4")).multiply(personFactor);

            // 4. 处理席位加成逻辑
            // 这里简单演示：绿色车厢加价 2000 JPY
            // 注意：真实业务中需根据具体的 SeatClass 枚举判断

            // 5. 处理 VIP 折扣
            BigDecimal vipFactor = "VIP".equals(userLevel) ? new BigDecimal("0.8") : BigDecimal.ONE;

            // 6. 汇总计算并舍入（JR 票价通常取整）
            BigDecimal finalBase = baseFare.multiply(vipFactor).setScale(0, RoundingMode.DOWN);
            BigDecimal finalExpress = expressFee.multiply(vipFactor).setScale(0, RoundingMode.DOWN);

            // 7. 更新 TripInfo 中的最终价格与明细
            trip.setFinalPrice(finalBase.add(finalExpress));
            // 这里可以在 TripInfo 中增加一个字段存放 FareDetail，或直接更新 tags 描述
        });

        return trips;
    }

    @Override
    public int getOrder() {
        return 10; // 执行顺序：在所有筛选逻辑之后
    }
}