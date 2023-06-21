package com.central.backend.controller;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.co.SiteCo;
import com.central.backend.co.SiteUpdateCo;
import com.central.backend.service.ISiteService;
import com.central.backend.vo.SiteListVo;
import com.central.backend.vo.SiteVo;
import com.central.common.annotation.LoginUser;
import com.central.common.constant.MarksixConstants;
import com.central.common.model.Site;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@Api(tags = "站点管理api")
@Validated
@RequestMapping("/site")
public class SiteController {

    @Autowired
    private ISiteService siteService;


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


    @ApiOperation("查询站点下拉框数据")
    @ResponseBody
    @GetMapping("/findSiteBoxList")
    public Result<List<SiteListVo>> findSiteBoxList(@ApiIgnore @LoginUser SysUser sysUser) {
        //根据token判断当前账号是什么角色
        Integer roleId=0;
        if (sysUser !=null ){
            roleId= Integer.valueOf(sysUser.getRoleId());
        }
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
