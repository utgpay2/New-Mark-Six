package com.central.backend.controller;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.co.KpnSiteAdvertiseCo;
import com.central.backend.co.KpnSiteAdvertiseUpdateCo;
import com.central.backend.service.IKpnSiteAdvertiseService;
import com.central.common.annotation.LoginUser;
import com.central.common.constant.PornConstants;
import com.central.common.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Slf4j
@RestController
@Api(tags = "广告配置api")
@Validated
@RefreshScope
@RequestMapping("/advertise")
public class KpnSiteAdvertiseController {

    @Autowired
    private IKpnSiteAdvertiseService advertiseService;


    @Value("${zlt.minio.externalEndpoint}")
    private String externalEndpoint;

    /* 查询广告列表
     * @Author: Lulu
     * @Date: 2023/2/3
     */
    @ApiOperation("查询广告列表")
    @ResponseBody
    @GetMapping("/findAdvertiseList")
    public Result<PageResult<KpnSiteAdvertise>> findAdvertiseList(@ModelAttribute KpnSiteAdvertiseCo params) {
        PageResult<KpnSiteAdvertise> kpnSiteAdvertiseList = advertiseService.findAdvertiseList(params);
        kpnSiteAdvertiseList.getData().stream().forEach(info->{
            if (info.getUrl()!=null){
                info.setUrl(externalEndpoint+ PornConstants.Symbol.FORWARD_SLASH +info.getUrl());
            }
        });
        return Result.succeed(kpnSiteAdvertiseList);
    }


    @ApiOperation(value = "新增or更新广告")
    @PostMapping(value = "/saveOrUpdateAdvertise")
    public Result saveOrUpdateAdvertise(@RequestBody  KpnSiteAdvertise advertise, @LoginUser SysUser sysUser)  {
        if (sysUser!=null){
            if (advertise.getId() == null) {
                advertise.setUpdateBy(sysUser.getUsername());
                advertise.setCreateBy(sysUser.getUsername());
            }else {
                advertise.setUpdateBy(sysUser.getUsername());
            }
        }
        if (ObjectUtil.isEmpty(advertise.getNameZh()) || ObjectUtil.isEmpty(advertise.getNameEn()) || ObjectUtil.isEmpty(advertise.getNameKh())) {
            return Result.failed("广告名称不能为空");
        }
        if (ObjectUtil.isEmpty(advertise.getDevice())) {
            return Result.failed("投放平台不能为空");
        }
        if (ObjectUtil.isEmpty(advertise.getPosition())) {
            return Result.failed("广告位置不能为空");
        }
        if (ObjectUtil.isEmpty(advertise.getSort())) {
            return Result.failed("排序不能为空");
        }
        if (ObjectUtil.isEmpty(advertise.getUrl())) {
            return Result.failed("广告图片不能为空");
        }
        if (ObjectUtil.isEmpty(advertise.getStartTime()) || ObjectUtil.isEmpty(advertise.getEndTime())) {
            return Result.failed("有效时间不能为空");
        }
        return  advertiseService.saveOrUpdateAdvertise(advertise);
    }

    @ApiOperation(value = "修改广告状态")
    @GetMapping("/updateEnabledAdvertise")
    public Result updateEnabledAdvertise(@Valid @ModelAttribute KpnSiteAdvertiseUpdateCo params, @LoginUser SysUser sysUser) {
        if (sysUser!=null) {
            params.setUpdateBy(sysUser.getUsername());
        }
        //Result result = advertiseService.updateEnabledAdvertise(params);
        return Result.succeed("删除成功") ;
    }

    @ApiOperation("删除广告")
    @DeleteMapping(value = "/deleteAdvertiseId/{id}")
    public Result deleteAdvertiseId(@PathVariable Long id) {
        boolean b = advertiseService.deleteAdvertiseId(id);
        return b ? Result.succeed("删除成功") : Result.failed("删除失败");
    }


}
