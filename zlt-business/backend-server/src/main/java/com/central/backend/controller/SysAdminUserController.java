package com.central.backend.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.backend.co.GaBindCo;
import com.central.backend.model.dto.SysAdminUserDto;
import com.central.backend.model.dto.SysAdminUserEnabledDto;
import com.central.backend.model.dto.SysAdminUserPasswordDto;
import com.central.backend.service.IAdminUserService;
import com.central.backend.service.IKpnSiteService;
import com.central.backend.vo.UserInfoVo;
import com.central.common.annotation.LoginUser;
import com.central.common.constant.MarksixConstants;
import com.central.common.model.*;
import com.central.common.model.enums.CodeEnum;
import com.central.common.model.enums.UserTypeEnum;
import com.central.user.feign.UaaService;
import com.central.user.feign.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yixiu
 */
@Slf4j
@RestController
@Api(tags = "管理员api")
@Validated
@RequestMapping("/adminuser")
public class SysAdminUserController {
    private static final String ADMIN_CHANGE_MSG = "超级管理员不给予修改";
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private UaaService uaaService;
    @Autowired
    private IKpnSiteService siteService;
    @Autowired
    private IAdminUserService iAdminUserService;
    @Value("${marksix.business.authorization:Basic d2ViQXBwOndlYkFwcA==}")
    private String authorization;
    public static final String AUTHENTICATION_MODE = "password_code";

    @ApiOperation(value = "分页查询管理员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "enabled", value = "状态：0禁用，1启用", required = false, dataType = "String"),
            @ApiImplicitParam(name = "username", value = "管理员账号", required = false, dataType = "String"),
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping
    public Result<PageResult<SysUser>> list(@RequestParam Map<String, Object> params, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("page"))) {
            return Result.failed("分页起始位置不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("limit"))) {
            return Result.failed("分页结束位置不能为空");
        }
        return Result.succeed(iAdminUserService.findList(params,user));
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询管理员详情")
    @GetMapping("/{id}")
    public Result findUserById(@PathVariable Long id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.failed("ID不能为空");
        }
        SysUser sysUser = iAdminUserService.selectById(id);
        return Result.succeed(sysUser, "查询成功");
    }
    /**
     * 管理后台，给用户重置密码
     *
     * @param id
     */
    @PutMapping(value = "/{id}/password")
    @ApiOperation("管理后台，给用户重置密码")
    public Result resetPassword(@PathVariable Long id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.failed("ID不能为空");
        }
        if (checkAdmin(id)) {
            return Result.failed(ADMIN_CHANGE_MSG);
        }
        String password = iAdminUserService.resetUpdatePassword(id);
        return Result.succeed(password, "重置成功");
    }

    /**
     * 是否超级管理员
     */
    private boolean checkAdmin(long id) {
        return id == 1L;
    }
    /**
     * 管理员用户自己修改密码
     */
    @PutMapping(value = "/password")
    @ApiOperation("管理员用户自己修改密码")
    public Result updatePassword(@RequestBody SysAdminUserPasswordDto passwordDto) {
        if (ObjectUtil.isEmpty(passwordDto)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(passwordDto.getId())) {
            return Result.failed("管理员ID不能为空");
        }
        if (ObjectUtil.isEmpty(passwordDto.getOldPassword())) {
            return Result.failed("旧密码不能为空");
        }
        if (ObjectUtil.isEmpty(passwordDto.getNewPassword())) {
            return Result.failed("新密码不能为空");
        }
        if (checkAdmin(passwordDto.getId())) {
            return Result.failed(ADMIN_CHANGE_MSG);
        }
        iAdminUserService.updatePassword(passwordDto.getId(), passwordDto.getOldPassword(), passwordDto.getNewPassword());
        return Result.succeed("重置成功");
    }
    @PutMapping(value = "/enabled")
    @ApiOperation("管理员用户状态，启用或者禁用")
    public Result updateEnabled(@RequestBody SysAdminUserEnabledDto enabledDto) {
        if (ObjectUtil.isEmpty(enabledDto)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(enabledDto.getId())) {
            return Result.failed("管理员ID不能为空");
        }
        if (ObjectUtil.isEmpty(enabledDto.getEnabled())) {
            return Result.failed("状态不能为空");
        }
        if (checkAdmin(enabledDto.getId())) {
            return Result.failed(ADMIN_CHANGE_MSG);
        }
        iAdminUserService.updateEnabled(enabledDto);
        return Result.succeed("重置成功");
    }

    /**
     * 新增or更新
     *
     * @param sysUser
     * @return
     */
    @ApiOperation("新增or更新")
    @PostMapping("/saveOrUpdateAdminInfo")
    public Result saveOrUpdateAdminInfo(@RequestBody SysAdminUserDto adminUserVo, @ApiIgnore @LoginUser SysUser sysUser) {
        if (ObjectUtil.isEmpty(adminUserVo)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(adminUserVo.getUsername())) {
            return Result.failed("用户名不能为空");
        }
        if (ObjectUtil.isEmpty(adminUserVo.getId())) {
            if (ObjectUtil.isEmpty(adminUserVo.getPassword())) {
                return Result.failed("密码不能为空");
            }
        }
        if (ObjectUtil.isEmpty(adminUserVo.getSiteIds())) {
            return Result.failed("站点权限id不能为空");
        }
        if (ObjectUtil.isEmpty(adminUserVo.getRoleIds())) {
            return Result.failed("角色id不能为空");
        }
        if (ObjectUtil.isEmpty(adminUserVo.getEnabled())) {
            return Result.failed("状态不能为空");
        }
        if (ObjectUtil.isNotNull(adminUserVo.getRemark())) {
            if (adminUserVo.getRemark().length() > 100) {
                return Result.failed("备注长度不能超过100");
            }
        }
        return iAdminUserService.saveOrUpdateAdminInfo(adminUserVo,sysUser);
    }
    /**
     * 谷歌验证码是否校验状态修改
     */
    @ApiOperation(value = "谷歌验证码是否校验状态修改")
    @PutMapping(value = "/users/{id}/updateVerify")
    public Result updateVerify(@PathVariable Long id) {
        if (checkAdmin(id)) {
            return Result.failed(ADMIN_CHANGE_MSG);
        }
//        cacheEvictUser(id);
        return iAdminUserService.updateVerify(id);
    }

    /**
     * 重置谷歌验证码
     */
    @ApiOperation(value = "重置谷歌验证码")
    @PutMapping(value = "/users/{id}/resetGoogleCode")
    public Result resetGoogleCode(@PathVariable Long id) {
        GaBindCo param = new GaBindCo();
        param.setId(id);
        param.setGaBind(2);

//        cacheEvictUser(param.getId());

        Result result = iAdminUserService.updateGaBind(param);
        if (result != null && result.getResp_code() == 0) {
            return Result.succeed();
        }
        return Result.failed("重置失败");
    }
    /**
     * 删除管理员用户
     *
     * @param id
     */
    @DeleteMapping(value = "/users/delete/{id}")
    @ApiOperation(value = "删除用户")
    public Result delete(@PathVariable Long id) {
        if (checkAdmin(id)) {
            return Result.failed(ADMIN_CHANGE_MSG);
        }
//        cacheEvictUser(id);
        iAdminUserService.delUser(id);
        return Result.succeed("删除成功");
    }

    @GetMapping("/users/info")
    @ApiOperation(value = "查询登录用户基本信息")
    public Result<UserInfoVo> findUserInfoById(@LoginUser SysUser user) {
        SysUser sysUser = iAdminUserService.selectById(user.getId());
        UserInfoVo vo = new UserInfoVo();
        BeanUtil.copyProperties(sysUser, vo);
        vo.setIsAutoBet(vo.getIsAutoBet() == null ? false : vo.getIsAutoBet());
        return Result.succeed(vo);
    }


    /**
     * 管理后台给用户分配角色
     *
     * @param id
     * @param roleIds
     */
    @PostMapping("/users/{id}/roles")
    @ApiOperation(value = "管理后台给用户分配角色")
    public void setRoleToUser(@PathVariable Long id, @RequestBody Set<Long> roleIds) {
        iAdminUserService.setRoleToUser(id, roleIds);
    }

    /**
     * 获取用户的角色
     *
     * @param
     * @return
     */
    @GetMapping("/users/{id}/roles")
    @ApiOperation(value = "获取用户的角色")
    public List<SysRole> findRolesByUserId(@PathVariable Long id) {
        return iAdminUserService.findRolesByUserId(id);
    }
    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<String> login(@ApiParam(value = "站点id", required = true) @RequestHeader("sid") Long sid,
//                                @ApiParam(value = "图形验证码id", required = true) String verifyCodeId,
//                                @ApiParam(value = "验证码", required = true) String verifyCode,
                                @ApiParam(value = "登录账号", required = true) String username,
                                @ApiParam(value = "密码", required = true) String password) {
        //校验账号
        if (StrUtil.isBlank(username)) {
            return Result.failed("账号不能为空");
        }
        if (StrUtil.isBlank(password)) {
            return Result.failed("密码不能为空");
        }

//        //校验验证码
//        if (StrUtil.isBlank(verifyCodeId) || StrUtil.isBlank(verifyCode)) {
//            return Result.failed("验证码不能为空");
//        }
//        String cachedCode = (String) RedisRepository.get(verifyCodeId);
//        if (StrUtil.isBlank(cachedCode)) {
//            return Result.failed("验证码已过期");
//        }
//
//        if (!cachedCode.equalsIgnoreCase(verifyCode)) {
//            return Result.failed("验证码错误");
//        }

        KpnSite kpnSite = siteService.getById(sid);

        username = kpnSite.getCode() + "_" + username;
        LoginAppUser sysUser = userService.findByUsername(username);
        if (sysUser == null || !sysUser.getEnabled()) {
            return Result.failed("用户名或密码错误");
        }
        if(!UserTypeEnum.BACKEND.equals(sysUser.getType())){
            return Result.failed("非管理员用户");
        }


        Result tokenResult = uaaService.login(authorization, username, password, AUTHENTICATION_MODE);
        if (tokenResult == null || !tokenResult.getResp_code().equals(CodeEnum.SUCCESS.getCode())) {
            log.error("登录失败: username={}, msg={}", username, tokenResult.getResp_msg());
            return Result.failed(tokenResult.getResp_msg());
        }
        String accessToken = (String) (((LinkedHashMap) tokenResult.getDatas()).get(MarksixConstants.Str.ACCESS_TOKEN));

        return Result.succeed(accessToken, "succeed");
    }
}
