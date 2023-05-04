package com.central.common.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @description 小数保留2位返回给前端序列化器
 * 自定义序列器，使用  @JsonSerialize(using = 自定义序列化器类.class)去序列化指定的属性
 */
public class Decimal2Serializer extends JsonSerializer<Object> {

    /**
     * 将返回的BigDecimal四舍五入保留两位小数，再返回给前端
     *
     * @param value
     * @param jsonGenerator
     * @param serializerProvider
     * @throws IOException
     * @throws JsonProcessingException
     */
    @Override
    public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if (ObjectUtils.isEmpty(value)) {
            value = BigDecimal.ZERO;
        }
        BigDecimal bigDecimal = new BigDecimal(value.toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
        jsonGenerator.writeString(bigDecimal.toString());
    }

    public static int getNumberOfDecimalPlace(String value){
        final BigDecimal bigDecimal = new BigDecimal(value);
        final String s = bigDecimal.toPlainString();
        final int index = s.indexOf(".");
        if(index < 0){
            return 0;
        }
        return s.length() -1 -index;
    }
}
