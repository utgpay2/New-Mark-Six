package com.proxy.center.interceptor;


import com.proxy.center.service.ipmanage.ISysSysIpSwitchButtonService;
import com.proxy.center.service.ipmanage.ISysWhiteIpService;
import com.central.common.model.enums.StatusEnum;
import com.central.common.model.ipmanage.SysIpSwitchButton;
import com.central.common.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 拦截器
 */
@Slf4j
@Component
public class IpBlackCheckInterceptor implements HandlerInterceptor {
    @Autowired
    private ISysWhiteIpService iSysWhiteIpService;
    @Autowired
    ISysSysIpSwitchButtonService iSysSysIpSwitchButtonService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<SysIpSwitchButton> buttonList = iSysSysIpSwitchButtonService.findList();
        for(SysIpSwitchButton switchButton : buttonList){
            if(StatusEnum.ONE_TRUE.getStatus()==switchButton.getWhiteipSwithcButton()){//白名单开关为1
                String ip = IpUtil.getIpAddr(request);
                    if(!iSysWhiteIpService.ipcheck(ip)){
                        response.sendRedirect(request.getContextPath()+"/syswhiteip/iperror");
                        return false;
                    }
            }
        }
        return true;
    }
}