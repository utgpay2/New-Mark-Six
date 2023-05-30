package com.central.backend.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.central.backend.model.dto.ProxyAdminDto;
import com.central.backend.model.dto.SysAdminUserDto;
import com.central.backend.model.dto.UserDto;
import com.central.backend.service.IAdminUserService;
import com.central.backend.service.ISiteService;
import com.central.backend.service.ISysUserService;
import com.central.backend.service.IThirdPartyService;
import com.central.backend.util.MD5;
import com.central.backend.vo.UserInfoVo;
import com.central.common.model.Result;
import com.central.common.model.Site;
import com.central.common.model.SysUser;
import com.central.common.model.ThirdParty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@RestController
@Api(tags = "第三方api")
@Validated
@RequestMapping("/api/thirdParty")
public class ThirdPartyController {


    @Autowired
    private IAdminUserService iAdminUserService;

    @Autowired
    private ISiteService iSiteService;

    @Autowired
    IThirdPartyService iThirdPartyService;


    @Autowired
    ISysUserService sysUserService;

    /**
     * 新增
     *
     * @param ProxyAdminDto
     * @return
     */
    @ApiOperation("新增代理")
    @PostMapping("/saveProxy")
    public Result saveProxy(@RequestBody ProxyAdminDto proxyAdminDto) {

        if (ObjectUtil.isEmpty(proxyAdminDto)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(proxyAdminDto.getUsername())) {
            return Result.failed("用户名不能为空");
        }

        if (ObjectUtil.isEmpty(proxyAdminDto.getPassword())) {
            return Result.failed("密码不能为空");
        }

        if (ObjectUtil.isEmpty(proxyAdminDto.getRandom())) {
            return Result.failed("随机字符不能为空");
        }
        if (ObjectUtil.isEmpty(proxyAdminDto.getSiteCode())) {
            return Result.failed("商户编码不能为空");
        }

        if (ObjectUtil.isEmpty(proxyAdminDto.getSign())) {
            return Result.failed("签名摘要不能为空");
        }
        Site site=iSiteService.getOne(new QueryWrapper<Site>().eq("code",proxyAdminDto.getSiteCode()));
        if (ObjectUtil.isEmpty(site)) {
            return Result.failed("商户不存在");
        }

        ThirdParty thirdParty= iThirdPartyService.getOne(new QueryWrapper<ThirdParty>().eq("site_code",proxyAdminDto.getSiteCode()));
        String secretKey=thirdParty.getSecretKey();
        String sign= MD5.encrypt(proxyAdminDto.getRandom()+proxyAdminDto.getSiteCode()+proxyAdminDto.getUsername()+secretKey);
        if (!proxyAdminDto.getSign().equals(sign)) {
            return Result.failed("签名摘要错误");
        }


        SysUser sysUser=iAdminUserService.getMerchantAdministrator(proxyAdminDto.getSiteCode());
        if (ObjectUtil.isEmpty(sysUser)) {
            return Result.failed("请联系后台添加商户管理员");
        }

        SysAdminUserDto sysAdminUserDto=new SysAdminUserDto();

        sysAdminUserDto.setEnabled(true);
        sysAdminUserDto.setPassword(proxyAdminDto.getPassword());
        sysAdminUserDto.setUsername(proxyAdminDto.getUsername());
        Set set=new HashSet<Long>();
        set.add(11L);
        sysAdminUserDto.setRoleIds(set);
        sysAdminUserDto.setSiteCode(proxyAdminDto.getSiteCode());
        sysAdminUserDto.setSiteId(site.getId());
        sysAdminUserDto.setSiteName(site.getName());

        return iAdminUserService.saveOrUpdateAdminInfo(sysAdminUserDto,sysUser);
    }


    /**
     * 新增
     *
     * @param UserDto
     * @return
     */
    @ApiOperation("新增用户")
    @PostMapping("/saveUser")
    public Result saveUser(@RequestBody UserDto userDto) {

        if (ObjectUtil.isEmpty(userDto)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(userDto.getUsername())) {
            return Result.failed("用户名不能为空");
        }

        if (ObjectUtil.isEmpty(userDto.getPassword())) {
            return Result.failed("密码不能为空");
        }

        if (ObjectUtil.isEmpty(userDto.getRandom())) {
            return Result.failed("随机字符不能为空");
        }
        if (ObjectUtil.isEmpty(userDto.getSiteCode())) {
            return Result.failed("商户编码不能为空");
        }

        if (ObjectUtil.isEmpty(userDto.getSign())) {
            return Result.failed("签名摘要不能为空");
        }
        Site site=iSiteService.getOne(new QueryWrapper<Site>().eq("code",userDto.getSiteCode()));
        if (ObjectUtil.isEmpty(site)) {
            return Result.failed("商户不存在");
        }

        ThirdParty thirdParty= iThirdPartyService.getOne(new QueryWrapper<ThirdParty>().eq("site_code",userDto.getSiteCode()));
        String secretKey=thirdParty.getSecretKey();
        String sign= MD5.encrypt(userDto.getRandom()+userDto.getSiteCode()+secretKey);
        if (!userDto.getSign().equals(sign)) {
            return Result.failed("签名摘要错误");
        }
        SysUser proxyUser;
        if(ObjectUtil.isEmpty(userDto.getProxyUsername())){
            proxyUser=iAdminUserService.getMerchantAdministrator(userDto.getSiteCode());
            if (ObjectUtil.isEmpty(proxyUser)) {
                return Result.failed("请联系后台添加商户管理员");
            }
        }else {
            proxyUser=iAdminUserService.findByUsername(userDto.getProxyUsername());
        }


        SysUser user=new SysUser();

        user.setEnabled(true);
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        user.setParentId(proxyUser.getId());
        user.setParentName(proxyUser.getUsername());
        user.setSiteCode(site.getCode());
        user.setSiteId(site.getId());
        user.setSiteName(site.getName());
        user.setCreateBy(proxyUser.getUsername());
        user.setUpdateBy(proxyUser.getUsername());
        return sysUserService.saveOrUpdateUserInfo(user);
    }


    /**
     * 新增
     *
     * @param ProxyAdminDto
     * @return
     */
    @ApiOperation("查询会员信息")
    @GetMapping("/userInfo")
    public Result userInfo(
                            @ApiParam(value = "随机字符串", required = true) String random,
                            @ApiParam(value = "签名摘要", required = true) String sign,
                            @ApiParam(value = "登录账号", required = true) String siteCode,
                            @ApiParam(value = "用户名", required = true) String username,
                            @ApiParam(value = "会员id", required = true) Integer userId) {


        if (ObjectUtil.isEmpty(username)) {
            return Result.failed("用户名不能为空");
        }

        if (ObjectUtil.isEmpty(random)) {
            return Result.failed("随机字符不能为空");
        }
        if (ObjectUtil.isEmpty(siteCode)) {
            return Result.failed("商户编码不能为空");
        }

        if (ObjectUtil.isEmpty(sign)) {
            return Result.failed("签名摘要不能为空");
        }

        if (ObjectUtil.isEmpty(userId)) {
            return Result.failed("会员id不能为空");
        }
        Site site=iSiteService.getOne(new QueryWrapper<Site>().eq("code",siteCode));
        if (ObjectUtil.isEmpty(site)) {
            return Result.failed("商户不存在");
        }

        ThirdParty thirdParty= iThirdPartyService.getOne(new QueryWrapper<ThirdParty>().eq("site_code",siteCode));
        String secretKey=thirdParty.getSecretKey();
        String signGet= MD5.encrypt(random+siteCode+username+secretKey);
        if (!signGet.equals(sign)) {
            return Result.failed("签名摘要错误");
        }


        SysUser stationOwner=iAdminUserService.getMerchantAdministrator(siteCode);
        if (ObjectUtil.isEmpty(stationOwner)) {
            return Result.failed("请联系后台添加商户管理员");
        }
        QueryWrapper<SysUser> queryWrapper;
        if(stationOwner.getUsername().equals(username)){
            //商户管理员查询会员，直接使用商户代码
            queryWrapper=new QueryWrapper<SysUser>().eq("site_code",siteCode).eq("id",userId);
        }else {
            //代理管理员查询会员，查询代理所属的会员
            queryWrapper=new QueryWrapper<SysUser>().eq("parent_name",username).eq("id",userId);
        }

        SysUser user=iAdminUserService.getOne(queryWrapper);

        if(ObjectUtil.isEmpty(user)){
            return Result.failed("会员不存在");
        }

        return Result.succeed(user);
    }

    /**
     * 启用会员账号
     *
     * @param
     * @return
     */
    @ApiOperation("启用会员账号")
    @GetMapping("/user/enable")
    public Result userEnable(
            @ApiParam(value = "随机字符串", required = true) String random,
            @ApiParam(value = "签名摘要", required = true) String sign,
            @ApiParam(value = "登录账号", required = true) String siteCode,
            @ApiParam(value = "用户名", required = true) String username,
            @ApiParam(value = "会员id", required = true) Integer userId) {


        if (ObjectUtil.isEmpty(username)) {
            return Result.failed("用户名不能为空");
        }

        if (ObjectUtil.isEmpty(random)) {
            return Result.failed("随机字符不能为空");
        }
        if (ObjectUtil.isEmpty(siteCode)) {
            return Result.failed("商户编码不能为空");
        }

        if (ObjectUtil.isEmpty(sign)) {
            return Result.failed("签名摘要不能为空");
        }

        if (ObjectUtil.isEmpty(userId)) {
            return Result.failed("会员id不能为空");
        }
        Site site=iSiteService.getOne(new QueryWrapper<Site>().eq("code",siteCode));
        if (ObjectUtil.isEmpty(site)) {
            return Result.failed("商户不存在");
        }

        ThirdParty thirdParty= iThirdPartyService.getOne(new QueryWrapper<ThirdParty>().eq("site_code",siteCode));
        String secretKey=thirdParty.getSecretKey();
        String signGet= MD5.encrypt(random+siteCode+username+secretKey);
        if (!signGet.equals(sign)) {
            return Result.failed("签名摘要错误");
        }


        SysUser stationOwner=iAdminUserService.getMerchantAdministrator(siteCode);
        if (ObjectUtil.isEmpty(stationOwner)) {
            return Result.failed("请联系后台添加商户管理员");
        }


        QueryWrapper<SysUser> queryWrapper;
        if(stationOwner.getUsername().equals(username)){
            //商户管理员查询会员，直接使用商户代码
            queryWrapper=new QueryWrapper<SysUser>().eq("site_code",siteCode).eq("id",userId);
        }else {
            //代理管理员查询会员，查询代理所属的会员
            queryWrapper=new QueryWrapper<SysUser>().eq("parent_name",username).eq("id",userId);
        }

        SysUser user=iAdminUserService.getOne(queryWrapper);

        if(ObjectUtil.isEmpty(user)){
            return Result.failed("会员不存在");
        }
        user.setEnabled(true);

        user.setUpdateBy(username);

        user.setUpdateTime(new Date());

        return sysUserService.updateById(user)? Result.succeed(user, "更新成功") : Result.failed("更新失败");
    }

    /**
     * 停用会员账号
     *
     * @param
     * @return
     */
    @ApiOperation("停用会员账号")
    @GetMapping("/user/disable")
    public Result userDisable(
            @ApiParam(value = "随机字符串", required = true) String random,
            @ApiParam(value = "签名摘要", required = true) String sign,
            @ApiParam(value = "登录账号", required = true) String siteCode,
            @ApiParam(value = "用户名", required = true) String username,
            @ApiParam(value = "会员id", required = true) Integer userId) {


        if (ObjectUtil.isEmpty(username)) {
            return Result.failed("用户名不能为空");
        }

        if (ObjectUtil.isEmpty(random)) {
            return Result.failed("随机字符不能为空");
        }
        if (ObjectUtil.isEmpty(siteCode)) {
            return Result.failed("商户编码不能为空");
        }

        if (ObjectUtil.isEmpty(sign)) {
            return Result.failed("签名摘要不能为空");
        }

        if (ObjectUtil.isEmpty(userId)) {
            return Result.failed("会员id不能为空");
        }
        Site site=iSiteService.getOne(new QueryWrapper<Site>().eq("code",siteCode));
        if (ObjectUtil.isEmpty(site)) {
            return Result.failed("商户不存在");
        }

        ThirdParty thirdParty= iThirdPartyService.getOne(new QueryWrapper<ThirdParty>().eq("site_code",siteCode));
        String secretKey=thirdParty.getSecretKey();
        String signGet= MD5.encrypt(random+siteCode+username+secretKey);
        if (!signGet.equals(sign)) {
            return Result.failed("签名摘要错误");
        }


        SysUser stationOwner=iAdminUserService.getMerchantAdministrator(siteCode);
        if (ObjectUtil.isEmpty(stationOwner)) {
            return Result.failed("请联系后台添加商户管理员");
        }
        QueryWrapper<SysUser> queryWrapper;
        if(stationOwner.getUsername().equals(username)){
            //商户管理员查询会员，直接使用商户代码
            queryWrapper=new QueryWrapper<SysUser>().eq("site_code",siteCode).eq("id",userId);
        }else {
            //代理管理员查询会员，查询代理所属的会员
            queryWrapper=new QueryWrapper<SysUser>().eq("parent_name",username).eq("id",userId);
        }

        SysUser user=iAdminUserService.getOne(queryWrapper);

        if(ObjectUtil.isEmpty(user)){
            return Result.failed("会员不存在");
        }
        user.setEnabled(false);

        user.setUpdateBy(username);

        user.setUpdateTime(new Date());

        return sysUserService.updateById(user)? Result.succeed(user, "更新成功") : Result.failed("更新失败");
    }





}
