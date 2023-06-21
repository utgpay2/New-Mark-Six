package com.central.marksix.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import com.central.common.model.*;
import com.central.marksix.dto.ProxyAdminDto;
import com.central.marksix.dto.SysAdminUserDto;
import com.central.marksix.dto.TransferAccountsDto;
import com.central.marksix.dto.UserDto;
import com.central.marksix.entity.PornPageResult;
import com.central.marksix.entity.vo.MbChangeRecordDetailsVo;
import com.central.marksix.service.*;
import com.central.marksix.utils.MD5;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@Api(tags = "第三方api")
@Validated
@RequestMapping("/v1/api/thirdParty")
public class ThirdPartyController {


    @Autowired
    private IAdminUserService iAdminUserService;

    @Autowired
    private ISiteService iSiteService;

    @Autowired
    private IThirdPartyService iThirdPartyService;


    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IQuizOrdersService quizOrdersService;


    @Autowired
    private IMoneyLogService moneyLogService;
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
        String sign= MD5.encrypt(userDto.getRandom()+userDto.getSiteCode()+userDto.getProxyUsername()+secretKey);
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


    /**
     * 查询投注记录(商务查询商户下所有用户，代理可以查询代理下所有用户)
     */
    @ApiOperation(value = "查询投注记录")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "随机字符串", required = true ,name = "random",dataType = "String"),
            @ApiImplicitParam(value = "签名摘要", required = true ,name = "sign",dataType = "String"),
            @ApiImplicitParam(value = "登录账号", required = true ,name = "siteCode",dataType = "String"),
            @ApiImplicitParam(value = "用户名", required = true ,name = "username",dataType = "String"),
            @ApiImplicitParam(value = "会员id集合", required = false ,name = "userIds",dataType = "Integer[]"),
            @ApiImplicitParam(name = "sortBy", value = "排序方式：1正序、2倒叙(默认)", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "days", value = "1 今天,2 昨天,3 近七天", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "status", value = "1 待开奖,2 已取消,3 中奖,4 未中奖", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "periods", value = "期数", required = false, dataType = "String"),
            @ApiImplicitParam(name = "siteLotteryId", value = "站点彩种ID", required = false, dataType = "Integer")
    })
    @GetMapping("/queryorders")
    public Result<PageResult<QuizOrders>> queryBettingOrders(@ApiIgnore @RequestParam Map<String, Object> params) {

        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("page"))) {
            return Result.failed("分页起始位置不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("limit"))) {
            return Result.failed("分页结束位置不能为空");
        }

        if (ObjectUtil.isEmpty(params.get("username"))) {
            return Result.failed("用户名不能为空");
        }

        if (ObjectUtil.isEmpty(params.get("random"))) {
            return Result.failed("随机字符不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("siteCode"))) {
            return Result.failed("商户编码不能为空");
        }

        if (ObjectUtil.isEmpty(params.get("sign"))) {
            return Result.failed("签名摘要不能为空");
        }

        Site site=iSiteService.getOne(new QueryWrapper<Site>().eq("code",params.get("siteCode").toString()));
        if (ObjectUtil.isEmpty(site)) {
            return Result.failed("商户不存在");
        }

        ThirdParty thirdParty= iThirdPartyService.getOne(new QueryWrapper<ThirdParty>().eq("site_code",params.get("siteCode").toString()));
        String secretKey=thirdParty.getSecretKey();
        String signGet= MD5.encrypt(params.get("random").toString()+params.get("siteCode").toString()+params.get("username").toString()+secretKey);
        if (!signGet.equals(params.get("sign").toString())) {
            return Result.failed("签名摘要错误");
        }


        SysUser stationOwner=iAdminUserService.getMerchantAdministrator(params.get("siteCode").toString());
        if (ObjectUtil.isEmpty(stationOwner)) {
            return Result.failed("请联系后台添加商户管理员");
        }

        if(!stationOwner.getUsername().equals(params.get("username").toString())){
            //当前用户不是商户管理 是代理
            params.put("parentName",params.get("username").toString());
        }
        return Result.succeed(quizOrdersService.findList(params));
    }



    /**
     * 额度转换
     *
     * @param TransferAccountsDto
     * @return
     */
    @ApiOperation("额度转换（自动转换给上级  会员和代理之间转换，代理和平台商户之间转换）")
    @PostMapping("/transfer")
    public Result transfer(@RequestBody TransferAccountsDto userDto) {

        if (ObjectUtil.isEmpty(userDto)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(userDto.getUsername())) {
            return Result.failed("用户名不能为空");
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
        String sign= MD5.encrypt(userDto.getRandom()+userDto.getSiteCode()+userDto.getUsername()+secretKey);
        if (!userDto.getSign().equals(sign)) {
            return Result.failed("签名摘要错误");
        }
        SysUser proxyUser;
        if(ObjectUtil.isEmpty(userDto.getUsername())){
            proxyUser=iAdminUserService.getMerchantAdministrator(userDto.getSiteCode());
            if (ObjectUtil.isEmpty(proxyUser)) {
                return Result.failed("请联系后台添加商户管理员");
            }
        }
        return sysUserService.transfer(userDto);
    }


    /**
     * 停用会员账号
     *
     * @param
     * @return
     */
    @ApiOperation("获取会员余额")
    @GetMapping("/user/balance")
    public Result userBalance(
            @ApiParam(value = "随机字符串", required = true) String random,
            @ApiParam(value = "签名摘要", required = true) String sign,
            @ApiParam(value = "登录账号", required = true) String siteCode,
            @ApiParam(value = "代理用户名", required = true) String username,
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


        return Result.succeed(user.getMBalance());
    }


    /**
     * 会员上下分记录查询
     *
     * @param
     * @return
     */
    @ApiOperation("会员上下分记录查询")
    @GetMapping("/user/transferRecords")
    public Result transferRecords(
            @ApiParam(value = "随机字符串", required = true) String random,
            @ApiParam(value = "签名摘要", required = true) String sign,
            @ApiParam(value = "登录账号", required = true) String siteCode,
            @ApiParam(value = "代理用户名", required = true) String username,
            @ApiParam(value = "会员id", required = false) Integer[] userIds,
            @ApiParam(value = "转账类型(5上分   6下分)", required = false) Integer type,
            @ApiParam( value = "排序方式：1正序、2倒叙(默认)",required = true ,defaultValue = "1") Integer sort,
            @ApiParam( value = "分页起始位置", required = true,defaultValue = "1" ) Integer page,
            @ApiParam( value = "分页结束位置", required = true,defaultValue = "10")Integer limit) {


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
        //过滤用户，判断是否当前代理下的用户
        userIds= sysUserService.getUserIdsByUserName(username,userIds);
        if(userIds.length==0){
            return Result.succeed(
                    PornPageResult.<MbChangeRecordDetailsVo>builder()
                    .currPage(page)
                    .pageSize(limit)
                    .count(0L)
                    .totalPage(0)
                    .build()
            );

        }



        return Result.succeed(moneyLogService.transferRecords(username,userIds,type,sort,page,limit));
    }
}
