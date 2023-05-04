package com.central.oauth.config;

import com.central.oauth.granter.*;
import com.central.oauth.service.IValidateCodeService;
import com.central.oauth.service.ProcessLoginInfoService;
import com.central.oauth.service.impl.CustomTokenServices;
import com.central.oauth.service.impl.UserDetailServiceFactory;
import com.central.oauth.service.impl.UserDetailsByNameServiceFactoryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * token授权模式配置类
 *
 * @author zlt
 * @date 2020/7/11
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@Slf4j
@Configuration
public class TokenGranterConfig {
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Resource
    private UserDetailServiceFactory userDetailsServiceFactory;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    @Autowired(required = false)
    private List<TokenEnhancer> tokenEnhancer;

    @Autowired
    private IValidateCodeService validateCodeService;

    @Autowired
    private RandomValueAuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private ProcessLoginInfoService processLoginInfoService;

    private boolean reuseRefreshToken = true;

    private AuthorizationServerTokenServices tokenServices;

    private TokenGranter tokenGranter;

    /**
     * 是否登录同应用同账号互踢
     * isSingleLogin本地正常，测试环境一直获取不到值，这里先默认开启登录时同应用同账号互踢
     */
    @Value("${zlt.uaa.isSingleLogin:false}")
    private boolean isSingleLogin;

    /**
     * 授权模式
     */
    @Bean
    @ConditionalOnMissingBean
    public TokenGranter tokenGranter(DefaultTokenServices tokenServices) {
        if (tokenGranter == null) {
            tokenGranter = new TokenGranter() {
                private CompositeTokenGranter delegate;

                @Override
                public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
                    if (delegate == null) {
                        delegate = new CompositeTokenGranter(getAllTokenGranters(tokenServices));
                    }
                    return delegate.grant(grantType, tokenRequest);
                }
            };
        }
        return tokenGranter;
    }

    /**
     * 所有授权模式：默认的5种模式 + 自定义的模式
     */
    protected List<TokenGranter> getAllTokenGranters(DefaultTokenServices tokenServices) {
        AuthorizationCodeServices authorizationCodeServices = authorizationCodeServices();
        OAuth2RequestFactory requestFactory = requestFactory();
        //获取默认的授权模式
        List<TokenGranter> tokenGranters = getDefaultTokenGranters(tokenServices, authorizationCodeServices, requestFactory);
        if (authenticationManager != null) {
            // 添加密码加图形验证码模式
            tokenGranters.add(new PwdImgCodeGranter(authenticationManager, tokenServices, clientDetailsService, requestFactory, validateCodeService));
            // 添加openId模式
            tokenGranters.add(new OpenIdGranter(authenticationManager, tokenServices, clientDetailsService, requestFactory));
            // 添加guest游客授权模式
            tokenGranters.add(new GuestGranter(authenticationManager, tokenServices, clientDetailsService, requestFactory,processLoginInfoService));
            // 添加手机号加密码授权模式
            tokenGranters.add(new MobilePwdGranter(authenticationManager, tokenServices, clientDetailsService, requestFactory));
            // 添加密码+Google身份验证码
            tokenGranters.add(new PwdGoogleGranter(authenticationManager, tokenServices, clientDetailsService, requestFactory, validateCodeService));
        }
        return tokenGranters;
    }

    /**
     * 默认的授权模式
     */
    private List<TokenGranter> getDefaultTokenGranters(AuthorizationServerTokenServices tokenServices
            , AuthorizationCodeServices authorizationCodeServices, OAuth2RequestFactory requestFactory) {
        List<TokenGranter> tokenGranters = new ArrayList<>();
        // 添加授权码模式
        tokenGranters.add(new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, clientDetailsService, requestFactory));
        // 添加刷新令牌的模式
        tokenGranters.add(new RefreshTokenGranter(tokenServices, clientDetailsService, requestFactory));
        // 添加隐士授权模式
        tokenGranters.add(new ImplicitTokenGranter(tokenServices, clientDetailsService, requestFactory));
        // 添加客户端模式
        tokenGranters.add(new ClientCredentialsTokenGranter(tokenServices, clientDetailsService, requestFactory));
        if (authenticationManager != null) {
            // 添加密码模式
            tokenGranters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices, clientDetailsService, requestFactory));
        }
        return tokenGranters;
    }

    private AuthorizationServerTokenServices tokenServices() {
        if (tokenServices != null) {
            return tokenServices;
        }
        this.tokenServices = createDefaultTokenServices();
        return tokenServices;
    }

    private AuthorizationCodeServices authorizationCodeServices() {
        if (authorizationCodeServices == null) {
            authorizationCodeServices = new InMemoryAuthorizationCodeServices();
        }
        return authorizationCodeServices;
    }

    private OAuth2RequestFactory requestFactory() {
        return new DefaultOAuth2RequestFactory(clientDetailsService);
    }

    @Bean
    @ConditionalOnMissingBean
    protected DefaultTokenServices createDefaultTokenServices() {
        log.info("isSingleLogin:{}",isSingleLogin);
        DefaultTokenServices tokenServices = new CustomTokenServices(isSingleLogin);
        tokenServices.setTokenStore(tokenStore); // 存储令牌策略
        tokenServices.setSupportRefreshToken(true); // 运行令牌自动刷新
        tokenServices.setReuseRefreshToken(reuseRefreshToken);
        tokenServices.setClientDetailsService(clientDetailsService); // 客户端详情服务
        tokenServices.setTokenEnhancer(tokenEnhancer());
        addUserDetailsService(tokenServices);
        return tokenServices;
    }

    private TokenEnhancer tokenEnhancer() {
        if (tokenEnhancer != null) {
            TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
            tokenEnhancerChain.setTokenEnhancers(tokenEnhancer);
            return tokenEnhancerChain;
        }
        return null;
    }

    private void addUserDetailsService(DefaultTokenServices tokenServices) {
        if (this.userDetailsServiceFactory != null) {
            PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
            provider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceFactoryWrapper<>(this.userDetailsServiceFactory));
            tokenServices.setAuthenticationManager(new ProviderManager(Collections.singletonList(provider)));
        }
    }
}
