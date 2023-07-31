package com.central.backend.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.central.backend.co.SiteCo;
import com.central.backend.co.SiteUpdateCo;
import com.central.backend.model.dto.ProxyRechargeDto;
import com.central.backend.model.dto.SiteRechargeDto;
import com.central.backend.model.dto.SysAdminUserDto;
import com.central.backend.service.IAdminUserService;
import com.central.backend.service.ISiteService;
import com.central.backend.service.ISysRoleService;
import com.central.backend.service.ISysRoleUserService;
import com.central.backend.vo.SiteListVo;
import com.central.backend.vo.SiteVo;
import com.central.common.annotation.LoginUser;
import com.central.common.constant.MarksixConstants;
import com.central.common.dto.ProxyAdminDto;
import com.central.common.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@Api(tags = "站点管理api")
@Validated
@RequestMapping("/site")
public class SiteController {

    @Autowired
    private ISiteService siteService;


    @Autowired
    ISysRoleUserService iSysRoleUserService;

    @Autowired
    IAdminUserService iAdminUserService;

    @Value("${zlt.minio.externalEndpoint}")
    private String externalEndpoint;

    @ApiOperation("查询站点列表")
    @ResponseBody
    @GetMapping("/findSiteList")
    public Result<PageResult<Site>> findSiteList(@ModelAttribute SiteCo params) {
        PageResult<Site> siteList = siteService.findSiteList(params);
        siteList.getData().stream().forEach(info->{
            if (info.getLogoUrl()!=null){
                info.setLogoUrl(externalEndpoint + MarksixConstants.Symbol.FORWARD_SLASH + info.getLogoUrl());
            }
        });
        return Result.succeed(siteList);
    }


    @ApiOperation(value = "给站点授信额度（系统管理员权限）")
    @PostMapping(value = "/recharge")
    public Result recharge(@RequestBody SiteRechargeDto siteRechargeDto, @LoginUser SysUser sysUser) {
        if(sysUser.getId()!=1 && sysUser.getId()!=2){
            return Result.failed("需要系统管理员权限");
        }
        return  siteService.recharge(siteRechargeDto,sysUser);
    }



    /**
     * 新增
     *
     * @param ProxyAdminDto
     * @return
     */
    @ApiOperation(value ="新增代理")
    @PostMapping(value ="/saveProxy")
    public Result saveProxy(@RequestBody ProxyAdminDto proxyAdminDto,@LoginUser SysUser sysUser) {

        if (ObjectUtil.isEmpty(proxyAdminDto)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(proxyAdminDto.getUsername())) {
            return Result.failed("用户名不能为空");
        }

        if (ObjectUtil.isEmpty(proxyAdminDto.getPassword())) {
            return Result.failed("密码不能为空");
        }


        if (ObjectUtil.isEmpty(proxyAdminDto.getSiteCode())) {
            return Result.failed("商户编码不能为空");
        }


        Site site=siteService.getOne(new QueryWrapper<Site>().eq("code",proxyAdminDto.getSiteCode()));
        if (ObjectUtil.isEmpty(site)) {
            return Result.failed("商户不存在");
        }

        //查询商户户主
        SysUser user=iSysRoleUserService.getStationOwenrBySiteId( sysUser.getSiteId().intValue());

        if(user==null ||( !user.getId().equals(sysUser.getId()) && sysUser.getId()!=1 && sysUser.getId()!=2)){

            return Result.failed("需要商户户主权限！");
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


    @ApiOperation("获取代理列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "siteId", value = "站点id", required = true, dataType = "Integer")
    })
    @GetMapping("/getProxys")
    public Result<PageResult<SysUser>> getProxys(@RequestParam Map<String, Object> params,@ApiIgnore @LoginUser SysUser sysUser) {
        //查询商户户主
        SysUser user=iSysRoleUserService.getStationOwenrBySiteId((Integer) params.get("siteId"));

        if(user==null ||( !user.getId().equals(sysUser.getId()) && sysUser.getId()!=1 && sysUser.getId()!=2)){
            return Result.failed("需要商户户主权限！");
        }
        return Result.succeed(siteService.getProxys(params,sysUser));
    }


    @ApiOperation(value = "给代理授信额度或收回授信额度（商户户主权限）")
    @PostMapping(value = "/recharge/proxy")
    public Result rechargeProxy(@RequestBody ProxyRechargeDto proxyRechargeDto, @LoginUser SysUser sysUser) {
        //查询商户户主
        SysUser user=iSysRoleUserService.getStationOwenrBySiteId( sysUser.getSiteId().intValue());

        if(user==null ||( !user.getId().equals(sysUser.getId()) && sysUser.getId()!=1 && sysUser.getId()!=2)){

            return Result.failed("需要商户户主权限！");
        }
        return  siteService.rechargeProxy(proxyRechargeDto,sysUser);
    }


    @ApiOperation("查询站点下拉框数据")
    @ResponseBody
    @GetMapping("/findSiteBoxList")
    public Result<List<SiteListVo>> findSiteBoxList(@ApiIgnore @LoginUser SysUser sysUser) {
        //根据token判断当前账号是什么角色
        Integer roleId=0;
       /* if (sysUser !=null ){
            roleId= Integer.valueOf(sysUser.getRoleId());
        }*/
        List list = siteService.findSiteBoxList(roleId);
        return Result.succeed(list);
    }

    @ApiOperation(value = "站点总计")
    @GetMapping("/findSiteTotal")
    public Result<SiteVo> findSiteTotal() {
        SiteVo siteTotal = siteService.findSiteTotal();
        return Result.succeed(siteTotal);
    }



    @ApiOperation(value = "新增or更新站点")
    @PostMapping(value = "/saveOrUpdateSite")
    public Result saveOrUpdateSite(@RequestBody Site site, @LoginUser SysUser sysUser) {
        if (sysUser!=null) {
            if (site.getId() == null) {
                site.setUpdateBy(sysUser.getUsername());
                site.setCreateBy(sysUser.getUsername());
            } else {
                site.setUpdateBy(sysUser.getUsername());
            }
        }
        if (ObjectUtil.isEmpty(site.getName())) {
            return Result.failed("站点名称不能为空");
        }
        if (ObjectUtil.isEmpty(site.getCode())) {
            return Result.failed("站点编码不能为空");
        }
        if (ObjectUtil.isEmpty(site.getLogoUrl())) {
            return Result.failed("站点logo不能为空");
        }
        if (ObjectUtil.isEmpty(site.getCurrencyCode())) {
            return Result.failed("币种不能为空");
        }
        if (ObjectUtil.isEmpty(site.getDomains())) {
            return Result.failed("域名不能为空");
        }
        return  siteService.saveOrUpdateSite(site);
    }


    @ApiOperation(value = "修改站点状态")
    @GetMapping("/updateEnabledSite")
    public Result updateEnabledSite(@Valid @ModelAttribute SiteUpdateCo params, @LoginUser SysUser sysUser) {
        if (sysUser!=null) {
            params.setUpdateBy(sysUser.getUsername());
        }
        Result result = siteService.updateEnabledSite(params);
        return result;
    }




    @ApiOperation(value = "随机生成站点编号")
    @GetMapping("/randomNumber")
    public Result randomNumber() {
        String name =siteService.getStringRandom(6);
        return Result.succeed(name,"操作成功");
    }






}
