package com.central.backend.core.interceptor;


import cn.hutool.extra.servlet.ServletUtil;
import com.central.common.constant.PornConstants;
import com.central.common.language.LanguageThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

/**
 * 拦截器
 */
@Slf4j
@Component
public class RequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String language = ServletUtil.getHeader(request, PornConstants.Str.LANGUAGE, Charset.defaultCharset());
        LanguageThreadLocal.setLanguage(language);
        return true;
    }
}