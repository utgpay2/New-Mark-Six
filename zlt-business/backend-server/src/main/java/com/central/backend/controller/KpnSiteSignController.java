package com.central.backend.controller;

import cn.hutool.core.collection.CollUtil;
import com.central.backend.service.IAsyncService;
import com.central.backend.service.IKpnSiteSignService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.KpnSiteSign;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Api(tags = "签到配置api")
@Validated
@RequestMapping("/sign")
public class KpnSiteSignController {

    @Autowired
    private IKpnSiteSignService signService;

    @Autowired
    private IAsyncService asyncService;


    /* 查询签到列表
     * @Author: Lulu
     * @Date: 2023/2/2
     */
    @ApiOperation("查询签到列表")
    @ResponseBody
    @GetMapping("/findSignList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "siteId", value = "站点id", required = true, dataType = "Long"),
    })
    public Result< List<KpnSiteSign>> findSignList(@RequestParam("siteId") Long siteId) {
        List<KpnSiteSign> signList = signService.findSignList(siteId);
        return Result.succeed(signList);
    }


    @ApiOperation(value = "保存签到配置")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody List<KpnSiteSign> list, @LoginUser SysUser sysUser) {
        if (sysUser!=null) {
            list.forEach(info ->{
                if (info.getId()==null){
                    info.setUpdateBy(sysUser.getUsername());
                    info.setCreateBy(sysUser.getUsername());
                } else {
                    info.setUpdateBy(sysUser.getUsername());
                }
            });
        }
        Boolean aBoolean = signService.saveOrUpdateSign(list);

        // add by year 删除站点签到配置缓存
        if (CollUtil.isNotEmpty(list)) {
            KpnSiteSign kpnSiteSign = list.get(0);
            asyncService.deleteSiteSignConfigCache(kpnSiteSign.getSiteId());
        }
        return aBoolean ? Result.succeed("操作成功") : Result.failed("操作失败");
    }



}
