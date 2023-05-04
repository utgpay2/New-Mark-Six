package com.central.oauth.component;

import com.central.common.constant.PornConstants;
import com.central.common.model.LoginAppUser;
import com.central.common.utils.ServletUtil;
import com.central.oauth.service.ProcessLoginInfoService;
import com.central.common.utils.IpUtil;
import com.central.user.feign.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class ApplicationListenerAuthencationSuccess implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private ProcessLoginInfoService processLoginInfoService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        log.info("AuthenticationSuccessEvent, {}", event.getSource());
        String uaaLoginIp = ServletUtil.getHttpServletRequest().getHeader(PornConstants.Str.LOGIN_IP);
        log.info("uaa login ip:{}", uaaLoginIp);
        if (null != event.getSource() && event.getSource() instanceof AbstractAuthenticationToken) {
            if (null != ((AbstractAuthenticationToken) event.getSource()).getPrincipal() &&
                    ((AbstractAuthenticationToken) event.getSource()).getPrincipal() instanceof LoginAppUser) {
                LoginAppUser loginAppUser = (LoginAppUser) ((AbstractAuthenticationToken) event.getSource()).getPrincipal();
                log.info("登陆成功 AuthenticationSuccessEvent： {}, {}", loginAppUser.getUsername(), loginAppUser.getType());
                processLoginInfoService.processLoginInfo(loginAppUser, uaaLoginIp);
            }
        }
    }

    public String getLoginIp() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return IpUtil.getIpAddr(request);
    }
}
