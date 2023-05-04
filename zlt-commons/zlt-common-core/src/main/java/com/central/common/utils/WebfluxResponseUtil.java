package com.central.common.utils;

import cn.hutool.core.collection.CollUtil;
import com.central.common.constant.I18nKeys;
import com.central.common.constant.PornConstants;
import com.central.common.language.LanguageEnum;
import com.central.common.model.Result;
import com.central.common.model.enums.CodeEnum;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 网关webflux的response返回工具类
 */
public class WebfluxResponseUtil {
    /**
     * webflux的response返回json对象
     */
    public static Mono<Void> responseFailed(ServerWebExchange exchange, String msg) {
        Result result = Result.failed(msg);
        return responseWrite(exchange, HttpStatus.INTERNAL_SERVER_ERROR.value(), result);
    }

    public static Mono<Void> responseFailed(ServerWebExchange exchange, int httpStatus, String msg) {
        List<String> headerList = exchange.getRequest().getHeaders().get(PornConstants.Str.LANGUAGE);
//        String language = CollUtil.isEmpty(headerList) ? I18nKeys.Locale.EN : headerList.get(0);
        String language = CollUtil.isEmpty(headerList) ? LanguageEnum.EN.getValue() : headerList.get(0);
        String originalMsg = I18nUtil.translate(language, "请求地址或参数异常",null);
        String dataMsg = I18nUtil.translate(language, msg,null);
        Result result = Result.failed(CodeEnum.ERROR_AUTH_SECURITY.getCode(), originalMsg, dataMsg);
        return responseWrite(exchange, httpStatus, result);
    }

    public static Mono<Void> responseWrite(ServerWebExchange exchange, int httpStatus, Result result) {
        if (httpStatus == 0) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setAccessControlAllowCredentials(true);
        response.getHeaders().setAccessControlAllowOrigin("*");
        response.setStatusCode(HttpStatus.valueOf(httpStatus));
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        DataBufferFactory dataBufferFactory = response.bufferFactory();
        DataBuffer buffer = dataBufferFactory.wrap(JsonUtil.toJSONString(result).getBytes(Charset.defaultCharset()));
        return response.writeWith(Mono.just(buffer)).doOnError((error) -> {
            DataBufferUtils.release(buffer);
        });
    }
}
