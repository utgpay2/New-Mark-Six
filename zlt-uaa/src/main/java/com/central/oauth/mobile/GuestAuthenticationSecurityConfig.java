package com.central.oauth.mobile;

import com.central.oauth.service.impl.UserDetailServiceFactory;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 游客guest的相关处理配置
 */
@Component
public class GuestAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Resource
    private UserDetailServiceFactory userDetailsServiceFactory;

    @Override
    public void configure(HttpSecurity http) {
        //guest provider
        GuestAuthenticationProvider provider = new GuestAuthenticationProvider();
        provider.setUserDetailsServiceFactory(userDetailsServiceFactory);
        http.authenticationProvider(provider);
    }
}
