package com.central.oauth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义异常类, 优雅的返回给前端相关的逻辑错误异常
 */
@JsonSerialize(using = CustomOAuth2ExceptionSerializer.class)
public class CustomOAuth2Exception extends OAuth2Exception {
    /**
     * 返回结构参照 com.central.common.model.Result<T>
     */
    @Getter
    private Object datas;
    @Getter
    private Integer resp_code;
    @Getter
    private String resp_msg;

    public CustomOAuth2Exception(int resp_code, String msg) {
        super(msg);
        this.resp_code = resp_code;
        this.resp_msg = msg;
        this.datas = null;
    }

    public CustomOAuth2Exception(int resp_code, String msg, Object datas) {
        super(msg);
        this.resp_code = resp_code;
        this.resp_msg = msg;
        this.datas = datas;
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.OK.value();
    }

}

