package com.central.oauth.service.impl;

import com.central.common.constant.SecurityConstants;
import com.central.common.model.enums.CodeEnum;
import com.central.common.model.LoginAppUser;
import com.central.common.model.Result;
import com.central.common.redis.template.RedisRepository;
import com.central.oauth.exception.CustomOAuth2Exception;
import com.central.oauth.modle.CodeErrorAuthEnum;
import com.central.oauth.service.ITokensService;
import com.central.oauth.service.ZltUserDetailsService;
import com.central.oauth.utils.ConstantPlayer;
import com.central.user.feign.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zlt
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements ZltUserDetailsService {
    private static final String ACCOUNT_TYPE = SecurityConstants.DEF_ACCOUNT_TYPE;

    @Resource
    private UserService userService;

    @Resource
    private ITokensService tokensService;

    @Resource
    private RedisRepository redisRepository;

    @Override
    public boolean supports(String accountType) {
        return ACCOUNT_TYPE.equals(accountType);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("+++++++++++++username:{}",username);
        LoginAppUser loginAppUser = userService.findByUsername(username);
        if (loginAppUser == null) {
            throw new CustomOAuth2Exception(CodeErrorAuthEnum.ERROR_AUTH_USERNAME_PASSWORD.getCode(), "用户名或密码错误");
        }
        log.info("+++++++++++++++++++登录校验密码结束");
        return checkUser(loginAppUser);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String openId) {
        LoginAppUser loginAppUser = userService.findByOpenId(openId);
        return checkUser(loginAppUser);
    }

    @Override
    public UserDetails loadUserByMobile(String mobile) {
        LoginAppUser loginAppUser = userService.findByMobile(mobile);
        return checkUser(loginAppUser);
    }

    @Override
    public UserDetails loadGuestUser() {
        Result<LoginAppUser> appUserResult = userService.findGuest();
        if (appUserResult.getResp_code()!= CodeEnum.SUCCESS.getCode()){
            throw new CustomOAuth2Exception(CodeErrorAuthEnum.ERROR_AUTH.getCode(),appUserResult.getResp_msg());
        }
        LoginAppUser loginAppUser = appUserResult.getDatas();
        return checkUser(loginAppUser);
    }

    private String getUserNameFrom(){
        if(!redisRepository.exists(ConstantPlayer.PLAYER_ACCOUNT_QUEUE))
            throw new InternalAuthenticationServiceException("未创建游客");
        else {
            Object playName = redisRepository.rightPop(ConstantPlayer.PLAYER_ACCOUNT_QUEUE);
            if(playName !=null)
                return playName.toString();
        }
        throw new InternalAuthenticationServiceException("游客已满");
    }

    private LoginAppUser checkUser(LoginAppUser loginAppUser) {
        if (loginAppUser != null && !loginAppUser.isEnabled()) {
            throw new DisabledException("当前用户已被禁用");
        }
        return loginAppUser;
    }
}
