package com.central.oauth.exception;

import com.central.common.model.Result;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CustomOAuth2ExceptionSerializer extends StdSerializer<CustomOAuth2Exception> {

    public CustomOAuth2ExceptionSerializer() {
        super(CustomOAuth2Exception.class);
    }

    @Override
    public void serialize(CustomOAuth2Exception e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//        System.out.println("Auth返回的状态码："+String.valueOf(e.getHttpErrorCode());
        Result result = Result.failed(e.getResp_code(), e.getResp_msg(), e.getDatas());
        jsonGenerator.writeObject(result);
    }
}