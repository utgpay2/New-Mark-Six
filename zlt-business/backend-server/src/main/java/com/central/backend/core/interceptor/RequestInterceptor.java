package com.central.backend.core.interceptor;


import cn.hutool.extra.servlet.ServletUtil;
import com.central.common.constant.MarksixConstants;
import com.central.common.exception.TokenNotValidException;
import com.central.common.language.LanguageThreadLocal;
import com.central.common.redis.template.RedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.Optional;

/**
 * 拦截器
 */
@Slf4j
@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisRepository redisRepository;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String language = ServletUtil.getHeader(request, MarksixConstants.Str.LANGUAGE, Charset.defaultCharset());
        LanguageThreadLocal.setLanguage(language);

        String token = request.getHeader("token");
        Optional.ofNullable(token).orElseThrow(() -> new TokenNotValidException("Token 认证失败"));
        if(!redisRepository.exists("token:"+token)){
            throw new TokenNotValidException("Token已过期，请重新登录");
        }


        return true;
    }
}