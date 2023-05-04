package com.central.oauth.config;

import com.central.oauth.exception.CustomOAuth2Exception;
import com.central.oauth.handler.OauthLogoutHandler;
import com.central.oauth.handler.OauthLogoutSuccessHandler;
import com.central.oauth.modle.CodeErrorAuthEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.common.exceptions.UnsupportedResponseTypeException;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证错误处理
 */
@Slf4j
@Configuration
public class SecurityHandlerConfig {
    @Bean
    public LogoutHandler logoutHandler() {
        return new OauthLogoutHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new OauthLogoutSuccessHandler();
    }

    @Bean
    public WebResponseExceptionTranslator webResponseExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator() {

            @Override
            public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {

                OAuth2Exception oAuth2Exception;
                if (e instanceof InternalAuthenticationServiceException) { // TODO 待游客逻辑移除后处理 InternalAuthenticationServiceException
                    oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
                } else if(e instanceof CustomOAuth2Exception){
                    // 对于OAuth2接口逻辑处理, 遇到逻辑验证异常, 都 throw CustomExtendOAuth2Exception
                    // 处理自定义异常, 返回客户端http status code = 200, 统一的格式化异常处理.
                    CustomOAuth2Exception err = (CustomOAuth2Exception) e;
//                    log.info("自定义异常信息: httpStatusCode: {}, message: {}, errorCode: {}", err.getHttpErrorCode(), err.getMessage(), err.getResp_code());
                    return new ResponseEntity<>(err, null, err.getHttpErrorCode());
                } else if (e instanceof OAuth2Exception) {
                    oAuth2Exception = (OAuth2Exception) e;
                } else {
                    oAuth2Exception = new UnsupportedResponseTypeException("未知错误", e);
                }
                /*
                    比如：
                        HttpRequestMethodNotSupportedException
                        http-status = 405 , error = "method_not_allowed", error_description = "Request method 'GET' not supported"
                    返回：
                        {
                            "error": "method_not_allowed",
                            "error_description": "Request method 'GET' not supported"
                        }
                */
                ResponseEntity<OAuth2Exception> response = super.translate(oAuth2Exception);
                Map<String, String> errorDataMap = new HashMap<>();
                errorDataMap.put("http_status_code", response.getBody().getHttpErrorCode()+"");
                errorDataMap.put("error", response.getBody().getOAuth2ErrorCode());
                errorDataMap.put("error_description", response.getBody().getMessage());
                CustomOAuth2Exception customOAuth2Exception = new CustomOAuth2Exception(CodeErrorAuthEnum.ERROR_AUTH.getCode(), "认证错误", errorDataMap);
                return new ResponseEntity<>(customOAuth2Exception, null, customOAuth2Exception.getHttpErrorCode());
            }

        };
    }

    /**
     * 登陆成功
     */
    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }
}
