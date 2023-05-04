package com.central.common.resolver;

import cn.hutool.core.util.StrUtil;
import com.central.common.annotation.LoginUser;
import com.central.common.constant.PornConstants;
import com.central.common.constant.SecurityConstants;
import com.central.common.model.SysRole;
import com.central.common.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Token转化SysUser
 */
@Slf4j
public class TokenArgumentResolver implements HandlerMethodArgumentResolver {
    //    private UserService userService;
//
//    public TokenArgumentResolver(UserService userService) {
//        this.userService = userService;
//    }
    public TokenArgumentResolver() {
    }

    /**
     * 入参筛选
     *
     * @param methodParameter 参数集合
     * @return 格式化后的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(LoginUser.class) && methodParameter.getParameterType().equals(SysUser.class);
    }

    /**
     * 处理 @LoginUser 参数注解
     *
     * @param methodParameter       入参集合
     * @param modelAndViewContainer model 和 view
     * @param nativeWebRequest      web相关
     * @param webDataBinderFactory  入参解析
     * @return 包装对象
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) {
        LoginUser loginUser = methodParameter.getParameterAnnotation(LoginUser.class);
        // TODO 移除了 isFull去查用用户的功能 待清理内容
//        boolean isFull = loginUser.isFull();
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String userId = request.getHeader(SecurityConstants.USER_ID_HEADER);
        String username = request.getHeader(SecurityConstants.USER_HEADER);
        String type = request.getHeader(SecurityConstants.USER_TYPE_HEADER);
        String roles = request.getHeader(SecurityConstants.ROLE_HEADER);
        String siteId = request.getHeader(SecurityConstants.USER_SITE_ID_HEADER);
        //账号类型
        String accountType = request.getHeader(SecurityConstants.ACCOUNT_TYPE_HEADER);

//        String parent = request.getHeader(SecurityConstants.USER_PARENT_HEADER);

        if (StrUtil.isBlank(username)) {
            log.warn("resolveArgument error username is empty");
            return null;
        }
        SysUser user;
//        if (isFull) {
//            user = userService.selectByUsername(username);
//        } else {
        user = new SysUser();
        user.setId(Long.valueOf(userId));
        user.setUsername(username);
        user.setType(type);
        user.setSiteId(StrUtil.isBlank(siteId) ? null : Long.valueOf(siteId));
//        }
        List<SysRole> sysRoleList = new ArrayList<>();
        Arrays.stream(roles.split(",")).forEach(role -> {
            SysRole sysRole = new SysRole();
            sysRole.setCode(role);
            sysRoleList.add(sysRole);
        });
        user.setRoles(sysRoleList);
        return user;
    }
}
