package com.central.backend.controller;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.co.KpnSiteCo;
import com.central.backend.co.KpnSiteUpdateCo;
import com.central.backend.service.IKpnSiteService;
import com.central.backend.vo.KpnSiteListVo;
import com.central.backend.vo.KpnSiteVo;
import com.central.common.annotation.LoginUser;
import com.central.common.constant.PornConstants;
import com.central.common.model.KpnSite;
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

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@Api(tags = "站点管理api")
@Validated
@RequestMapping("/site")
public class KpnSiteController {

    @Autowired
    private IKpnSiteService siteService;


    @Value("${zlt.minio.externalEndpoint}")
    private String externalEndpoint;

    @ApiOperation("查询站点列表")
    @ResponseBody
    @GetMapping("/findSiteList")
    public Result<PageResult<KpnSite>> findSiteList(@ModelAttribute KpnSiteCo params) {
        PageResult<KpnSite> siteList = siteService.findSiteList(params);
        siteList.getData().stream().forEach(info->{
            if (info.getLogoUrl()!=null){
                info.setLogoUrl(externalEndpoint + PornConstants.Symbol.FORWARD_SLASH + info.getLogoUrl());
            }
        });
        return Result.succeed(siteList);
    }


    @ApiOperation("查询站点下拉框数据")
    @ResponseBody
    @GetMapping("/findSiteBoxList")
    public Result<List<KpnSiteListVo>> findSiteBoxList(@LoginUser SysUser sysUser) {
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
    public Result<KpnSiteVo> findSiteTotal() {
        KpnSiteVo siteTotal = siteService.findSiteTotal();
        return Result.succeed(siteTotal);
    }



    @ApiOperation(value = "新增or更新站点")
    @PostMapping(value = "/saveOrUpdateSite")
    public Result saveOrUpdateSite(@RequestBody  KpnSite kpnSite, @LoginUser SysUser sysUser) {
        if (sysUser!=null) {
            if (kpnSite.getId() == null) {
                kpnSite.setUpdateBy(sysUser.getUsername());
                kpnSite.setCreateBy(sysUser.getUsername());
            } else {
                kpnSite.setUpdateBy(sysUser.getUsername());
            }
        }
        if (ObjectUtil.isEmpty(kpnSite.getName())) {
            return Result.failed("站点名称不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSite.getCode())) {
            return Result.failed("站点编码不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSite.getLogoUrl())) {
            return Result.failed("站点logo不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSite.getCurrencyCode())) {
            return Result.failed("币种不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSite.getDomains())) {
            return Result.failed("域名不能为空");
        }
        return  siteService.saveOrUpdateSite(kpnSite);
    }


    @ApiOperation(value = "修改站点状态")
    @GetMapping("/updateEnabledSite")
    public Result updateEnabledSite(@Valid @ModelAttribute KpnSiteUpdateCo params, @LoginUser SysUser sysUser) {
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
