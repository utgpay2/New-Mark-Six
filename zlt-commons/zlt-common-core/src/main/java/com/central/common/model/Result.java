package com.central.common.model;

import com.central.common.model.enums.CodeEnum;
import com.central.common.utils.I18nUtil;
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
public class Result<T> implements Serializable {

    private T datas;
    private Integer resp_code;
    private String resp_msg;

    public static <T> Result<T> succeed() {
        return of(null, CodeEnum.SUCCESS.getCode(),"操作成功");
    }

    public static <T> Result<T> succeed(String msg) {
        return of(null, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeedDynamic(String msg, Object dynamic) {
        return of(null, CodeEnum.SUCCESS.getCode(), msg, dynamic);
    }

    public static <T> Result<T> succeed(T model, String msg) {
        return of(model, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeed(T model) {
        return of(model, CodeEnum.SUCCESS.getCode(), "");
    }

    public static <T> Result<T> of(T datas, Integer code, String msg) {
        Result<T> result = new Result<>();
        result.setDatas(datas);
        result.setResp_code(code);
        result.setResp_msg(I18nUtil.t(msg));
        return result;
    }

    public static <T> Result<T> of(T datas, Integer code, String msg, Object dynamicMsg) {
        Result<T> result = of(datas, code, msg);
        result.setResp_msg(result.getResp_msg() + dynamicMsg);
        return result;
    }

    public static <T> Result<T> failed(String msg) {
        return of(null, CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failedDynamic(String msg, Object dynamicMsg) {
        return of(null, CodeEnum.ERROR.getCode(), msg, dynamicMsg);
    }

    public static <T> Result<T> failed(int codeEnum, String msg, T datas) {
        return of(datas, codeEnum, msg);
    }

    public static <T> Result<T> failed(T model, String msg) {
        return of(model, CodeEnum.ERROR.getCode(), msg);
    }
}
