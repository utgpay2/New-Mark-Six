package com.central.backend.controller;

import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.service.IKpnSiteServeService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.KpnSiteServe;
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
 * 站点客服配置
 *
 * @author yixiu
 * @date 2023-02-21 19:46:07
 */
@Slf4j
@RestController
@RequestMapping("/kpnsiteserve")
@Api(tags = "站点客服配置")
public class KpnSiteServeController {
    @Autowired
    private IKpnSiteServeService kpnSiteServeService;

    /**
     * 列表
     */
    @ApiOperation(value = "分页查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping
    public Result<PageResult<KpnSiteServe>> listPage(@RequestParam Map<String, Object> params,@ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("page"))) {
            return Result.failed("分页起始位置不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("limit"))) {
            return Result.failed("分页结束位置不能为空");
        }
        return Result.succeed(kpnSiteServeService.findListPage(params,user));
    }
    @ApiOperation(value = "查询列表")
    @GetMapping("/list")
    public Result<List<KpnSiteServe>> list(@RequestParam Map<String, Object> params, @ApiIgnore @LoginUser SysUser user) {
        return Result.succeed(kpnSiteServeService.findList(params,user));
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
        KpnSiteServe model = kpnSiteServeService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "新增or更新")
    @PostMapping
    public Result save(@RequestBody KpnSiteServe kpnSiteServe,@ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(kpnSiteServe)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSiteServe.getPlatform())) {
            return Result.failed("平台不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSiteServe.getServeAccount())) {
            return Result.failed("账号不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSiteServe.getStatus())) {
            return Result.failed("状态不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSiteServe.getPcIconUrl())) {
            return Result.failed("pc图标地址不能为空");
        }
        if (ObjectUtil.isEmpty(kpnSiteServe.getAppIconUrl())) {
            return Result.failed("app图标地址不能为空");
        }
        return kpnSiteServeService.saveOrUpdateKpnSiteServe(kpnSiteServe,user);
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
        kpnSiteServeService.removeById(id);
        return Result.succeed("删除成功");
    }
}
