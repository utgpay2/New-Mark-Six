package com.central.oauth.granter;

import com.central.common.model.SysUser;
import com.central.oauth.service.ProcessLoginInfoService;
import com.central.oauth.service.impl.UserDetailServiceFactory;
import com.central.oauth2.common.token.GuestUserAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * guest 游客授权模式
 */
@Slf4j
public class GuestGranter extends AbstractTokenGranter {
    private static final String GRANT_TYPE = "guest";

    private final AuthenticationManager authenticationManager;

    private UserDetailServiceFactory userDetailsServiceFactory;

    private ProcessLoginInfoService processLoginInfoService;

    public GuestGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices
            , ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory,ProcessLoginInfoService processLoginInfoService) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.authenticationManager = authenticationManager;
        this.processLoginInfoService = processLoginInfoService;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        // Client认证通过,来到这里
        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());

        Authentication userAuth = new GuestUserAuthenticationToken();
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        userAuth = authenticationManager.authenticate(userAuth);
        if (userAuth == null || !userAuth.isAuthenticated()) {
            logger.info("player is not exsit");
            throw new InvalidGrantException("Could not authenticate guest");
        }
        log.info("{}",userAuth.getPrincipal());
        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);

    }
}
