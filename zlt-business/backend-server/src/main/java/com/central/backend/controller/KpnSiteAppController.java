package com.central.backend.controller;

import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.service.IKpnSiteAppService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.KpnSiteApp;
import com.central.common.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import com.central.common.model.PageResult;
import com.central.common.model.Result;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 站点app更新配置
 *
 * @author yixiu
 * @date 2023-02-21 19:46:07
 */
@Slf4j
@RestController
@RequestMapping("/kpnsiteapp")
@Api(tags = "站点app更新配置")
public class KpnSiteAppController {
    @Autowired
    private IKpnSiteAppService kpnSiteAppService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping
    public Result<PageResult<KpnSiteApp>> list(@RequestParam Map<String, Object> params,@ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("page"))) {
            return Result.failed("分页起始位置不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("limit"))) {
            return Result.failed("分页结束位置不能为空");
        }
        return Result.succeed(kpnSiteAppService.findList(params,user));
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("/{id}")
    public Result findUserById(@PathVariable Long id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.failed("ID不能为空");
        }
        KpnSiteApp model = kpnSiteAppService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "新增or更新")
    @PostMapping
    public Result save(@RequestBody KpnSiteApp kpnSiteApp,@ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(kpnSiteApp)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSiteApp.getVersionName())) {
            return Result.failed("版本名称不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSiteApp.getVersionNum())) {
            return Result.failed("版本号不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSiteApp.getDownloadUrl())) {
            return Result.failed("下载地址不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSiteApp.getIsForce())) {
            return Result.failed("是否强制更新不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSiteApp.getType())) {
            return Result.failed("终端类型不能为空");
        }
        if (ObjectUtil.isNotNull(kpnSiteApp.getRemark())) {
            if(kpnSiteApp.getRemark().length()>100){
                return Result.failed("备注长度不能超过100");
            }
        }
        return kpnSiteAppService.saveOrUpdateKpnSiteApp(kpnSiteApp,user);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.failed("ID不能为空");
        }
        kpnSiteAppService.removeById(id);
        return Result.succeed("删除成功");
    }
}
