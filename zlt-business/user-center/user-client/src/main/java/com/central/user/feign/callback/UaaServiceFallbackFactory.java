package com.central.user.feign.callback;

import com.central.common.model.Result;
import com.central.user.feign.UaaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * 降级工场
 */
@Slf4j
public class UaaServiceFallbackFactory implements FallbackFactory<UaaService> {

    @Override
    public UaaService create(Throwable cause) {
        return new UaaService() {
            @Override
            public Result login(String authorization, String userName, String password, String grantType) {
                log.error("登录失败:{}", userName);
                return Result.failed("登录失败");
            }
        };
    }
}
