package com.central.common.model;

import com.central.common.model.enums.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: zlt
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PushResult<T> implements Serializable {

    private T datas;
    private Integer resp_code;
    private String resp_msg;
    private String type;

    public static <T> PushResult<T> succeed(T model, String type,String msg) {
        return of(model, CodeEnum.SUCCESS.getCode(),msg, type);
    }

    public static <T> PushResult<T> failed(String msg) {
        return of(null, CodeEnum.ERROR.getCode(), msg, null);
    }

    public static <T> PushResult<T> of(T datas, Integer code, String msg,String type) {
        return new PushResult<>(datas, code, msg, type);
    }
}
