package com.central.porn.core.interceptor;


import cn.hutool.extra.servlet.ServletUtil;
import com.central.common.constant.PornConstants;
import com.central.common.constant.SecurityConstants;
import com.central.common.language.LanguageThreadLocal;
import com.central.common.model.enums.StatusEnum;
import com.central.common.model.ipmanage.SysIpSwitchButton;
import com.central.common.utils.IpUtil;
import com.central.porn.service.IKpnBlackIpService;
import com.central.porn.service.ISysSysIpSwitchButtonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.List;

/**
 * 拦截器
 */
@Slf4j
@Component
public class RequestInterceptor implements HandlerInterceptor {
    @Autowired
    private ISysSysIpSwitchButtonService iSysSysIpSwitchButtonService;
    @Autowired
    IKpnBlackIpService iKpnBlackIpService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String language = ServletUtil.getHeader(request, PornConstants.Str.LANGUAGE, Charset.defaultCharset());
        LanguageThreadLocal.setLanguage(language);
        List<SysIpSwitchButton> buttonList = iSysSysIpSwitchButtonService.findList();
        for(SysIpSwitchButton switchButton : buttonList){
            if(StatusEnum.ONE_FALSE.getStatus()==switchButton.getWhiteipSwithcButton()){//白名单开关为1
                String ip = IpUtil.getIpAddr(request);
                String siteId = request.getHeader(SecurityConstants.USER_SITE_ID_HEADER);
                if(iKpnBlackIpService.ipcheck(ip,siteId)){
                    response.sendRedirect(request.getContextPath()+"/kpnblackip/iperror");
                    return false;
                }
            }
        }
        return true;
    }
}