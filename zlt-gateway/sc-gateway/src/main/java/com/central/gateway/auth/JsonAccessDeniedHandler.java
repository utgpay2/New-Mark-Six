package com.central.gateway.auth;

import com.central.common.utils.WebfluxResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 403拒绝访问异常处理，转换为JSON
 *
 * 备注：返回403修改为返回200
 */
@Slf4j
public class JsonAccessDeniedHandler implements ServerAccessDeniedHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException e) {
        //HttpStatus.FORBIDDEN.value()
        return WebfluxResponseUtil.responseFailed(exchange, HttpStatus.OK.value(), e.getMessage());
    }
}
