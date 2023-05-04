package com.central.user.feign.callback;

import com.central.common.dto.LoginLogPageDto;
import com.central.common.model.*;
import com.central.user.feign.UserService;
import com.central.user.model.co.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * userService降级工场
 */
@Slf4j
public class UserServiceFallbackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable throwable) {
        /**
         * TODO 等待验证所有FallbackFactory是否完成
         */
        return new UserService() {
//            @Override
//            public SysUser selectByUsername(String username) {
//                log.error("通过用户名查询用户异常:{}", username, throwable);
//                return new SysUser();
//            }

            @Override
            public Result<Boolean> addSiteLoginlog(SiteLoginLog siteLoginLog) {
                log.error("新增登录日志失败:{}", siteLoginLog, throwable);
                return Result.failed("新增登录日志失败");
            }

            @Override
            public SysUser selectByUsername(String username) {
                log.error("服务器异常，selectByUsername通过用户名查询用户异常:{}", username);
                return null;
            }

            @Override
            public Result<LoginAppUser> getLoginAppUser() {
                log.error("服务器异常，getLoginAppUser根据access_token当前登录用户异常", throwable);
                return Result.failed("查询当前登录用户失败");
            }

            @GetMapping(value = "/users-anon/login", params = "username")
            @Override
            public LoginAppUser findByUsername(String username) {
//                log.error("通过用户名查询用户异常:{}", username, throwable);
                log.error("服务器异常，findByUsername通过用户名查询用户异常:{}", username);
                return null;
            }

            @Override
            public Result<String> bindGoogleCode(SysUserGoogleBindCoCo params) {
                log.error("服务器异常，bindGoogleCode异常:{}", params);
                return null;
            }

            @Override
            public Result<String> getGoogleCodeLink(SysUserParamsCo params) {
                log.error("服务器异常，getGoogleCodeLink异常:{}", params);
                return null;
            }

            @Override
            public Result<LoginAppUser> findGuest(){
                log.error("通过游客用户异常:{}", throwable.getMessage());
                return Result.failed("获取游客失败");
            }

            @Override
            public Boolean processLoginSuccess(LoginAppUser loginAppUser) {
                log.error("处理登录成功失败.",throwable);
                return Boolean.FALSE;
            }

            @Override
            public Result pushOnlineNum(Integer changeNum) {
                log.error("pushOnlineNum推送APP在线人数失败,changeNum={}",changeNum);
                return Result.failed("推送APP在线人数失败");
            }

            @Override
            public LoginAppUser findByMobile(String mobile) {
                log.error("通过手机号查询用户异常:{}", mobile, throwable);
                return new LoginAppUser();
            }

            @Override
            public LoginAppUser findByOpenId(String openId) {
                log.error("通过openId查询用户异常:{}", openId, throwable);
                return new LoginAppUser();
            }

            @Override
            public PageResult<SysUser> findSysUserList(SysUserListCo params) {
                log.error("findSysUserList查询用户异常:{}", params, throwable);
                return new PageResult();
            }

            @Override
            public PageResult<LoginLogPageDto> findUserLoginLogList(UserLoginLogPageCo params) {
                log.error("findUserLoginLogList查询会员日志异常:{}", params, throwable);
                return new PageResult();
            }

            @Override
            public Result saveOrUpdate(SysUserCo sysUser) {
                log.error("saveOrUpdate新增或修改用户数据异常:{}", sysUser, throwable);
                return Result.failed("更新失败");
            }

            @Override
            public Result delete(Long openId) {
                log.error("delete删除用户异常:{}", openId, throwable);
                return Result.failed("删除用户失败");
            }

            @Override
            public  Result resetPassword(Long id) {
                log.error("resetPassword修改密码异常:{}", id, throwable);
                return Result.failed("修改密码失败");
            }

            @Override
            public Result updatePassword(SysUser sysUser) {
                log.error("updatePassword修改密码异常:{}", sysUser, throwable);
                return Result.failed("修改密码失败");
            }

            @Override
            public  Result updateVerify(Long id) {
                log.error("updateVerify修改Verify:{}", id, throwable);
                return Result.failed("修改Verify失败");
            }

            @Override
            public Result updateGaKey(Map<String, Object> params) {
                log.error("updateGaKey修改二维码key异常:{}", params, throwable);
                return Result.failed("修改二维码key失败");
            }

            @Override
            public Result updateGaBind(GaBindCo params) {
                log.error("updateGaBind修改绑定二维码状态异常:{}", params, throwable);
                return Result.failed("修改绑定二维码状态失败");
            }

        };
    }
}
