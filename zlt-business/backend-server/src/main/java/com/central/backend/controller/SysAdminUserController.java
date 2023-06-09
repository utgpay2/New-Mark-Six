package com.central.backend.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.backend.co.GaBindCo;
import com.central.backend.co.SysUserParamsCo;
import com.central.backend.model.dto.SysAdminUserDto;
import com.central.backend.model.dto.SysAdminUserEnabledDto;
import com.central.backend.model.dto.SysAdminUserPasswordDto;
import com.central.backend.service.IAdminUserService;
import com.central.backend.service.ISiteService;
import com.central.backend.service.ISysUserService;
import com.central.backend.vo.SysUserInfoMoneyVo;
import com.central.backend.vo.UserInfoVo;
import com.central.common.annotation.LoginUser;
import com.central.common.constant.MarksixConstants;
import com.central.common.model.*;
import com.central.common.model.enums.CodeEnum;
import com.central.common.model.enums.UserTypeEnum;
import com.central.common.utils.GoogleAuthUtil;
import com.central.user.feign.UaaService;
import com.central.user.feign.UserService;
import com.central.user.model.co.SysUserGoogleBindCoCo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import com.central.common.utils.PwdEncoderUtil;
import javax.validation.Valid;
import java.util.*;
import org.apache.commons.lang3.StringUtils;
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
    private ISysUserService appUserService;
    @Autowired
    private UserService userService;
    @Autowired
    private UaaService uaaService;
    @Autowired
    private ISiteService siteService;
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
    @PutMapping(value = "/password/{id}")
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
        return id == 1L || id == 2L;
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
//        if (checkAdmin(passwordDto.getId())) {
//            return Result.failed(ADMIN_CHANGE_MSG);
//        }
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
     * 清除缓存
     */
    public void cacheEvictUser(Long id) {
        SysUser sysUser = appUserService.selectById(id);
        if (sysUser != null) {
            appUserService.cacheEvictUser(sysUser);
        }
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
     * 查询用户登录对象LoginAppUser
     */
    @GetMapping(value = "/users-anon/login", params = "username")
    @ApiOperation(value = "根据用户名查询用户")
    public LoginAppUser findByUsername(String username) {
        return appUserService.findByUsername(username);
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
    @ApiOperation("密码登录")
    @PostMapping("/login/password")
    public Result<String> login(
//            @ApiParam(value = "站点id", required = true) @RequestHeader("sid") Long sid,
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
//        if (null!=sid && sid !=0 ) {
//            Site site = siteService.getById(sid);
//            username = site.getCode() + "_" + username;
//        }

        LoginAppUser sysUser = userService.findByUsername(username);
        if (sysUser == null || null == sysUser.getUsername() || !sysUser.getEnabled()) {
            return Result.failed("用户名或密码错误");
        }
        if(!UserTypeEnum.BACKEND.name().equals(sysUser.getType())){
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

    @ApiOperation("google登录")
    @PostMapping("/login/logingoogle")
    public Result<String> login(
//            @ApiParam(value = "站点id", required = true) @RequestHeader("sid") Long sid,
                                //@ApiParam(value = "图形验证码id", required = false) String verifyCodeId,
                                @ApiParam(value = "验证码", required = false) String verifyCode,
                                @ApiParam(value = "登录账号", required = true) String username,
                                @ApiParam(value = "密码", required = true) String password) {
        //校验账号
        if (StrUtil.isBlank(username)) {
            return Result.failed("账号不能为空");
        }
        if (StrUtil.isBlank(password)) {
            return Result.failed("密码不能为空");
        }

        //校验验证码
        if ( StrUtil.isBlank(verifyCode)) {
            return Result.failed("验证码不能为空");
        }
//        String cachedCode = (String) RedisRepository.get(verifyCodeId);
//        if (StrUtil.isBlank(cachedCode)) {
//            return Result.failed("验证码已过期");
//        }
//
//        if (!cachedCode.equalsIgnoreCase(verifyCode)) {
//            return Result.failed("验证码错误");
//        }
//        if (null!=sid && sid !=0 ) {
//            Site site = siteService.getById(sid);
//            username = site.getCode() + "_" + username;
//        }

        LoginAppUser sysUser = userService.findByUsername(username);
        if (sysUser == null || null == sysUser.getUsername() || !sysUser.getEnabled()) {
            return Result.failed("用户名或密码错误");
        }
        if(!UserTypeEnum.BACKEND.name().equals(sysUser.getType())){
            return Result.failed("非管理员用户");
        }


        Result tokenResult = uaaService.loginGoogle(authorization, username, password, "password_google",verifyCode,null);
        if (tokenResult == null || !tokenResult.getResp_code().equals(CodeEnum.SUCCESS.getCode())) {
            log.error("登录失败: username={}, msg={}", username, tokenResult.getResp_msg());
            return Result.failed(tokenResult.getResp_msg());
        }
        String accessToken = (String) (((LinkedHashMap) tokenResult.getDatas()).get(MarksixConstants.Str.ACCESS_TOKEN));

        return Result.succeed(accessToken, "succeed");
    }

    @ApiOperation(value = "绑定谷歌验证码")
    @PostMapping("/users-anon/bindGoogleCode")
    public Result<String> bindGoogleCode(@Valid @RequestBody SysUserGoogleBindCoCo params) {
        String username = params.getUsername();
        String password = params.getPassword();
        String googleCode = params.getGoogleCode();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(googleCode)) {
            return Result.failed("参数必填");
        }
        LoginAppUser loginAppUser = findByUsername(username);
        if (loginAppUser == null || !loginAppUser.getType().equals("BACKEND")) {
            return Result.failed("用户名或密码错误");
        }
        if (loginAppUser.getGaBind() != null && loginAppUser.getGaBind() == 1) {
            return Result.failed("该账号已经绑定谷歌验证码");
        }
        if (StringUtils.isBlank(loginAppUser.getGaKey())) {
            return Result.failed("请先绑定谷歌身份验证器");
        }
        GaBindCo param = new GaBindCo();
        param.setId(loginAppUser.getId());
        param.setGaBind(1);
        Result result = updateGaBind(param);
        if (result != null && result.getResp_code() == 0) {
            return Result.succeed();
        }
        return Result.failed("绑定失败");
    }

    @ApiOperation(value = "得到谷歌二维码链接")
    @PostMapping("/users-anon/getGoogleCodeLink")
    public Result<String> getGoogleCodeLink(@Valid @RequestBody SysUserParamsCo params) {
        String username = params.getUsername();
        String password = params.getPassword();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return Result.failed("参数必填");
        }
        LoginAppUser loginAppUser = findByUsername(username);
        if (loginAppUser == null || !loginAppUser.getType().equals("BACKEND")) {
            return Result.failed("用户名或密码错误");
        }
        if (!loginAppUser.isEnabled()) {
            return Result.failed("该账号状态异常");
        }
        if (loginAppUser.getGaBind() != null && loginAppUser.getGaBind() == 1) {
            return Result.failed("该账号已经绑定谷歌验证码");
        }
        PasswordEncoder encoder = PwdEncoderUtil.getDelegatingPasswordEncoder("bcrypt");
        Boolean match = encoder.matches(password, loginAppUser.getPassword());
        if (!match) {
            return Result.failed("用户名或密码错误");
        }
        // if (!passwordEncoder.matches(password, loginAppUser.getPassword())) {
        // return Result.failed("用户名或密码错误");
        // }
        String secret = GoogleAuthUtil.generateSecretKey();
        Map<String, Object> param = new HashMap<>();
        param.put("id", loginAppUser.getId());
        param.put("gaKey", secret);
        Result result = updateGaKey(param);
        if (result != null && result.getResp_code() == 0) {
            String qrcode = GoogleAuthUtil.getQcode(username, secret);
            return Result.succeed(qrcode, "");
        }
        return Result.failed("获取谷歌二维码失败");
    }
    /**
     * 二维码code变更
     *
     * @param params
     * @return
     */
    @ApiOperation(value = "二维码code变更")
    @GetMapping("/users/updateGaKey")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "gaKey", value = "谷歌验证码KEY", required = true, dataType = "String")})
    public Result updateGaKey(@RequestParam Map<String, Object> params) {
        Long id = MapUtils.getLong(params, "id");
        cacheEvictUser(id);
        return appUserService.updateGaKey(params);
    }

    /**
     * 二维码绑定状态变更
     *
     * @param params
     * @return
     */
    @ApiOperation(value = "二维码绑定状态变更")
    @GetMapping("/users/updateGaBind")
    public Result updateGaBind(@ModelAttribute GaBindCo params) {
        Long id = params.getId();
        cacheEvictUser(id);
        return appUserService.updateGaBind(params);
    }

    /**
     * 谷歌验证码是否校验状态修改
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "谷歌验证码是否校验状态修改")
    @PutMapping("/users/{id}/updateVerify")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer"),})
    public Result updateVerify(@PathVariable Long id) {
        if (checkAdmin(id)) {
            return Result.failed(ADMIN_CHANGE_MSG);
        }
        cacheEvictUser(id);
        return appUserService.updateVerify(id);
    }


    @ResponseBody
    @ApiOperation(value = "查询注册会员数量")
    @GetMapping("/users/findUserNum")
    @ApiImplicitParams({@ApiImplicitParam(name = "startTime", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false),
            @ApiImplicitParam(name = "type", value = "账号类型：APP：前端app用户，BACKEND：后端管理用户", required = false),
            @ApiImplicitParam(name = "parent", value = "所属平台", required = false),})
    public Result<Integer> findUserNum(@RequestParam Map<String, Object> params) {
        Integer userNum = appUserService.findUserNum(params);
        return Result.succeed(userNum);
    }


    @ApiOperation(value = "根据ids,查询用户")
    @PostMapping("/users/findListByIds")
    @ApiImplicitParams({@ApiImplicitParam(name = "ids", value = "用户id", required = true)})
    public Result<List<SysUser>> findListByIds(@RequestBody List<Long> ids) {
        List<SysUser> listByIds = appUserService.findListByIds(ids);
        return Result.succeed(listByIds);
    }

    @ApiOperation(value = "根据ids,查询用户基本信息及余额", hidden = true)
    @PostMapping("/users/findListByUserIdList")
    @ApiImplicitParam(name = "userIdList", value = "用户userIdList", required = true)
    public Result<List<SysUserInfoMoneyVo>> findListByUserIdList(@RequestBody List<Long> userIdList) {
        List<SysUserInfoMoneyVo> list = appUserService.findListByUserIds(userIdList);
        return Result.succeed(list);
    }

}
