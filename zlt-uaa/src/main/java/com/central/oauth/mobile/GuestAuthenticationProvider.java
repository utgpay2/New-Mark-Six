package com.central.oauth.mobile;

import com.central.oauth.exception.CustomOAuth2Exception;
import com.central.oauth.modle.CodeErrorAuthEnum;
import com.central.oauth.service.impl.UserDetailServiceFactory;
import com.central.oauth2.common.token.GuestUserAuthenticationToken;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 游客授权
 */
@Setter
@Getter
public class GuestAuthenticationProvider implements AuthenticationProvider {
    private UserDetailServiceFactory userDetailsServiceFactory;

    @Override
    public Authentication authenticate(Authentication authentication) {
        GuestUserAuthenticationToken authenticationToken = (GuestUserAuthenticationToken) authentication;
        UserDetails user = userDetailsServiceFactory.getService(authenticationToken).loadGuestUser();
        if (user == null) {
            throw new CustomOAuth2Exception(CodeErrorAuthEnum.ERROR_AUTH_GUEST_EMPTY.getCode(), "获取游客失败");
        }
        GuestUserAuthenticationToken authenticationResult = new GuestUserAuthenticationToken(user, user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return GuestUserAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
