package com.central.oauth.service;


import com.central.common.model.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface ProcessLoginInfoService {

    void processLoginInfo(UserDetails userDetails,String loginIp);

}
