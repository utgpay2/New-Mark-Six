package com.central.common.utils;


import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class BigDecimalUtils<T> {

    public static BigDecimal keepDecimal(BigDecimal val) {
        return val == null ? BigDecimal.ZERO.setScale(2) : val.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * BigDecimal四舍五入保留两位小数
     * @param model
     * @param <T>
     * @return
     */
    public static <T> T keepDecimal(T model) {
        Field[] fields = model.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.getType().equals(BigDecimal.class)) {
                    Object val = field.get(model);
                    BigDecimal keepVal = ObjectUtils.isEmpty(val) ? BigDecimal.ZERO.setScale(2) : new BigDecimal(val.toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
                    field.set(model, keepVal);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return model;
    }
}
