package com.central.push.config;

import com.central.common.model.enums.CodeEnum;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.push.feign.UaaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * token认证
 */
@Slf4j
@Component
public class CustomReactiveAuthentication {

    @Autowired
    private UaaService uaaService;

    public String authentication(String token) {
        Result<SysUser> userResult = uaaService.getUserInfoByToken(token);
        if (userResult.getResp_code() != CodeEnum.SUCCESS.getCode()) {
            log.error("根据token查询用户信息失败，token={}", token);
            return null;
        }
        SysUser sysUser = userResult.getDatas();
        if (sysUser == null) {
            log.error("根据token查询用户信息失败，token={}", token);
            return null;
        }
        String userName = sysUser.getUsername();
        return userName;
    }
}
