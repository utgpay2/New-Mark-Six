package com.central.gateway.auth;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import reactor.core.publisher.Mono;

/**
 * 自定义 响应式 认证 管理
 */
public class CustomReactiveAuthenticationManager implements ReactiveAuthenticationManager {
    private TokenStore tokenStore;

    public CustomReactiveAuthenticationManager(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    /**
     * 使用redis的token方式处理认证
     * 处理：调用BearerTokenAuthenticationToken的getToken方法
     * @param authentication
     * @return
     */
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication)
                .filter(a -> a instanceof BearerTokenAuthenticationToken)
                .cast(BearerTokenAuthenticationToken.class)
                .map(BearerTokenAuthenticationToken::getToken)
                .flatMap((accessTokenValue -> {
                    OAuth2AccessToken accessToken = tokenStore.readAccessToken(accessTokenValue);
                    if (accessToken == null) { // 没有找到token，无效的token
                        return Mono.error(new InvalidTokenException("Invalid access token: " + accessTokenValue));
                    }
//                    else if (accessToken.isExpired()) { // token已经过期
//                        tokenStore.removeAccessToken(accessToken);
//                        return Mono.error(new InvalidTokenException("Access token expired: " + accessTokenValue));
//                    }
                    // 自动续签的前提是token还为过期
                    OAuth2Authentication result = tokenStore.readAuthentication(accessToken);
                    if (result == null) { // 无效的token
                        return Mono.error(new InvalidTokenException("Invalid access token: " + accessTokenValue));
                    }
                    return Mono.just(result);
                }))
                .cast(Authentication.class);
    }
}
