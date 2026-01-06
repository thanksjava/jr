package com.jr.booking.converter;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SupplierDataConverter {

    /**
     * 统一的价格处理逻辑，屏蔽各家供应商对“税费”和“手续费”的计算差异
     */
    public BigDecimal normalizePrice(BigDecimal rawPrice, String vendor) {
        if ("VENDOR_A".equals(vendor)) {
            // A供应商不含税，我们需要在这里加上 10% 消费税
            return rawPrice.multiply(new BigDecimal("1.1"));
        }
        return rawPrice;
    }
}