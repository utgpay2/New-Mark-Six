//package com.central.backend.controller;
//
//import cn.hutool.core.util.ObjectUtil;
//import com.central.backend.service.IAsyncService;
//import com.central.backend.service.ISitePlatformService;
//import com.central.common.annotation.LoginUser;
//import com.central.common.model.SitePlatform;
//import com.central.common.model.Result;
//import com.central.common.model.SysUser;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//@Slf4j
//@RestController
//@Api(tags = "平台配置api")
//@Validated
//@RequestMapping("/platform")
//public class KpnSitePlatformController {
//
//    @Autowired
//    private ISitePlatformService sitePlatformService;
//
//    @Autowired
//    private IAsyncService asyncService;
//
//
//    /* 查询平台配置
//     * @Author: Lulu
//     * @Date: 2023/2/24
//     */
//    @ApiOperation("查询平台配置")
//    @ResponseBody
//    @GetMapping("/findPlatformInfo")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "siteId", value = "商户id", required = true, dataType = "Long"),
//    })
//    public Result<SitePlatform> findPlatformInfo(@RequestParam("siteId") Long siteId) {
//        SitePlatform promotion = sitePlatformService.findPromotionInfo(siteId);
//        return Result.succeed(promotion);
//    }
//
//
//
//
//    @ApiOperation(value = "保存配置")
//    @PostMapping("/saveOrUpdatePlatform")
//    public Result saveOrUpdatePlatform(@RequestBody SitePlatform info, @LoginUser SysUser sysUser) {
//        if (sysUser!=null) {
//            if (info.getId() == null) {
//                info.setUpdateBy(sysUser.getUsername());
//                info.setCreateBy(sysUser.getUsername());
//            } else {
//                info.setUpdateBy(sysUser.getUsername());
//            }
//        }
//        if (ObjectUtil.isEmpty(info.getSiteId())) {
//            return Result.failed("商户id不能为空");
//        }
//        Boolean aBoolean = sitePlatformService.saveOrUpdatePlatform(info);
//
//        //add by year 删除商户平台配置缓存
//        if(aBoolean){
//            asyncService.deleteSitePlatformCache(info.getSiteId());
//        }
//        return aBoolean ? Result.succeed("操作成功") : Result.failed("操作失败");
//    }
//}
