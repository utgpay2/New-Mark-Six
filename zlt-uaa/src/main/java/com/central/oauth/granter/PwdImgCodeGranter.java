package com.central.oauth.granter;

import com.central.oauth.service.IValidateCodeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.*;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * password + 图像验证码 授权模式
 * 资源所有者密码令牌授予者
 */
@Slf4j
public class PwdImgCodeGranter extends ResourceOwnerPasswordTokenGranter {
    private static final String GRANT_TYPE = "password_code";

    private final IValidateCodeService validateCodeService;

    public PwdImgCodeGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices
            , ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, IValidateCodeService validateCodeService) {
        super(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.validateCodeService = validateCodeService;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        String username = parameters.get("username");
        //校验图形验证码
        validateCodeService.validateAppUserType(username);
        return super.getOAuth2Authentication(client, tokenRequest);
    }
}
