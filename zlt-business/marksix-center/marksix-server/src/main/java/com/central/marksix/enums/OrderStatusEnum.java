package com.central.marksix.enums;

import com.central.common.language.LanguageEnum;
import com.central.common.language.LanguageThreadLocal;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum OrderStatusEnum {
    ORDER_ONE(1, "待开奖"),
    ORDER_TWO(2, "已取消"),
    ORDER_THREE(3, "中奖"),
    ORDER_FOUR(4, "未中奖");
    private final Integer status;
    private final String remark;

    OrderStatusEnum(Integer status, String remark) {
        this.status = status;
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }
    public static Map<Integer, String> getOptions() {

        Stream<OrderStatusEnum> stream = Arrays.stream(values());

        return stream.collect(Collectors.toMap(o -> o.status, o -> o.remark));
    }
}
