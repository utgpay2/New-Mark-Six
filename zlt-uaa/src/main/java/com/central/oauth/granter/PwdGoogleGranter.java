package com.central.oauth.granter;

import com.central.oauth.service.IValidateCodeService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * password + Google身份验证码 授权模式
 */
public class PwdGoogleGranter extends ResourceOwnerPasswordTokenGranter {
    private static final String GRANT_TYPE = "password_google";

    private final IValidateCodeService validateCodeService;

    public PwdGoogleGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices
            , ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, IValidateCodeService validateCodeService) {
        super(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.validateCodeService = validateCodeService;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        String googleCode = parameters.get("googleCode");
        String username = parameters.get("username");
        //校验图形验证码
        validateCodeService.validateGoogleCode(googleCode,username);
        return super.getOAuth2Authentication(client, tokenRequest);
    }
}
