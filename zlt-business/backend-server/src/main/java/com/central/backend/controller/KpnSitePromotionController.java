package com.central.backend.controller;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.service.IKpnSitePromotionService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.KpnSitePromotion;
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

@Slf4j
@RestController
@Api(tags = "推广好友配置api")
@Validated
@RequestMapping("/promotion")
public class KpnSitePromotionController {

    @Autowired
    private IKpnSitePromotionService promotionService;



    /* 查询推广好友信息
     * @Author: Lulu
     * @Date: 2023/2/2
     */
    @ApiOperation("查询推广好友信息")
    @ResponseBody
    @GetMapping("/findPromotionInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "siteId", value = "站点id", required = true, dataType = "Long"),
    })
    public Result<KpnSitePromotion> findPromotionInfo(@RequestParam("siteId") Long siteId) {
        KpnSitePromotion promotion = promotionService.findPromotionInfo(siteId);
        return Result.succeed(promotion);
    }


    @ApiOperation(value = "保存配置")
    @PostMapping("/saveOrUpdatePromotion")
    public Result saveOrUpdatePromotion(@RequestBody KpnSitePromotion info, @LoginUser SysUser sysUser) {
        if (sysUser!=null) {
            if (info.getId() == null) {
                info.setUpdateBy(sysUser.getUsername());
                info.setCreateBy(sysUser.getUsername());
            } else {
                info.setUpdateBy(sysUser.getUsername());
            }
        }

        if (ObjectUtil.isEmpty(info.getSiteId())) {
            return Result.failed("站点id不能为空");
        }
        Boolean aBoolean = promotionService.saveOrUpdatePromotion(info);
        return aBoolean ? Result.succeed("操作成功") : Result.failed("操作失败");
    }



}
