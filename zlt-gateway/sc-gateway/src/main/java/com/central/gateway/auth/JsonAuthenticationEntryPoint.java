package com.central.gateway.auth;

import com.central.common.utils.WebfluxResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 401未授权异常处理，转换为JSON
 *
 * 备注：返回401修改为返回200
 */
@Slf4j
public class JsonAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {
    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        //HttpStatus.UNAUTHORIZED.value()
        return WebfluxResponseUtil.responseFailed(exchange, HttpStatus.OK.value(), e.getMessage());
    }
}
