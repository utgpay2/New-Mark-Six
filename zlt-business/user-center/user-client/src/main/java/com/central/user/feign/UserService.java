package com.central.user.feign;

import com.central.common.constant.ServiceNameConstants;
import com.central.common.dto.LoginLogPageDto;
//import com.central.common.model.*;
import com.central.common.model.*;
import com.central.user.model.co.*;
import com.central.user.feign.callback.UserServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author zlt
 */
@FeignClient(name = ServiceNameConstants.USER_SERVICE, fallbackFactory = UserServiceFallbackFactory.class, decode404 = true)
public interface UserService {
    /**
     * feign rpc访问远程/users/{username}接口
     * 查询用户实体对象SysUser
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/users/name/{username}")
    SysUser selectByUsername(@PathVariable("username") String username);


    @GetMapping("/users/current")
    Result<LoginAppUser> getLoginAppUser() ;
    /**
     * feign rpc访问远程/users-anon/login接口
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/users-anon/login", params = "username")
    LoginAppUser findByUsername(@RequestParam("username") String username);

    @PostMapping("/users-anon/bindGoogleCode")
    Result<String> bindGoogleCode(@RequestBody SysUserGoogleBindCoCo params);

    @PostMapping("/users-anon/getGoogleCodeLink")
    Result<String> getGoogleCodeLink(@RequestBody SysUserParamsCo params);

    /**
     * feign rpc访问远程/users-anon/login1接口
     *
     * @return
     */
    @GetMapping(value = "/users-anon/findGuest")
    Result<LoginAppUser> findGuest();

    /**
     * feign rpc访问远程/users/loginSuc接口
     * 处理登录成功
     * @return
     */
    @PostMapping(value = "/user/loginSuc",params = "loginAppUser")
    Boolean processLoginSuccess(@RequestBody LoginAppUser loginAppUser);

    /**
     * 推送APP在线人数
     * @return
     */
    @GetMapping(value = "/user/pushOnlineNum")
    Result pushOnlineNum(@RequestParam("changeNum") Integer changeNum);

    /**
     * 通过手机号查询用户、角色信息
     *
     * @param mobile 手机号
     */
    @GetMapping(value = "/users-anon/mobile", params = "mobile")
    LoginAppUser findByMobile(@RequestParam("mobile") String mobile);

    /**
     * 根据OpenId查询用户信息
     *
     * @param openId openId
     */
    @GetMapping(value = "/users-anon/openId", params = "openId")
    LoginAppUser findByOpenId(@RequestParam("openId") String openId);

    /**
     * 用户查询列表
     *
     * @param params
     * @return
     */
    @GetMapping(value = "/users", params = "params")
    PageResult<SysUser> findSysUserList(@SpringQueryMap SysUserListCo params);

    /**
     * 查询会员日志列表
     * @return
     */
    @GetMapping(value = "/loginLog/findUserLoginLogList", params = "params")
    PageResult<LoginLogPageDto> findUserLoginLogList(@SpringQueryMap UserLoginLogPageCo params) ;


    /**
     * 更新或添加用户信息
     * @return
     */
    @PostMapping(value = "/users/saveOrUpdate", params = "sysUser")
    Result saveOrUpdate(@RequestBody SysUserCo sysUser);

    /**
     * 根据ID物理删除用户
     *
     * @param openId openId
     */
    @DeleteMapping(value = "/users/{id}", params = "id")
    Result delete(@PathVariable("id") Long openId);

    @PutMapping(value = "/users/{id}/password", params = "SysUser")
    Result resetPassword(@PathVariable("id") Long id) ;

    @PutMapping(value = "/users/password", params = "SysUser")
    Result updatePassword(@RequestBody SysUser sysUser) ;

    @PutMapping(value = "/users/{id}/updateVerify", params = "id")
    Result updateVerify(@PathVariable("id") Long id) ;

    @GetMapping(value ="/users/updateGaKey", params = "SysUser")
    Result updateGaKey(@RequestParam Map<String, Object> params);

    @GetMapping(value ="/users/updateGaBind", params = "SysUser")
    Result updateGaBind(@SpringQueryMap GaBindCo params);


    @PostMapping(value = "/loginLog/addLog", params = "LoginLog")
    Result<Boolean> addSiteLoginlog(@RequestBody SiteLoginLog siteLoginLog);
}
