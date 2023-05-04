package com.central.backend.controller.ipmanage;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.service.ipmanage.ISysWhiteIpService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.SysUser;
import com.central.common.model.ipmanage.SysWhiteIp;
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
 * 
 *
 * @author yixiu
 * @date 2023-02-03 15:07:56
 */
@Slf4j
@RestController
@RequestMapping("/syswhiteip")
@Api(tags = "白名单IP管理")
public class SysWhiteIpController {
    @Autowired
    private ISysWhiteIpService sysWhiteIpService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ip", value = "IP地址", required = false),
            @ApiImplicitParam(name = "updateBy", value = "更新人", required = false),
            @ApiImplicitParam(name = "startTime", value = "起始时间查询", required = false),
            @ApiImplicitParam(name = "endTime", value = "结束时间查询", required = false),
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping
    public Result<PageResult<SysWhiteIp>> list(@RequestParam Map<String, Object> params) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("page"))) {
            return Result.failed("分页起始位置不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("limit"))) {
            return Result.failed("分页结束位置不能为空");
        }
        return Result.succeed(sysWhiteIpService.findList(params));
    }

    /**
     * IP白名单检查
     * @param ip
     * @return
     */
    @ApiOperation(value = "IP白名单检查")
    @GetMapping("/ipcheck")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ip", value = "IP地址", required = true, dataType = "String")
    })
    public Result ipcheck(@RequestParam String ip) {
        if (ObjectUtil.isEmpty(ip)) {
            return Result.failed("IP地址不能为空");
        }
        return Result.succeed(sysWhiteIpService.ipcheck(ip));
    }

    @GetMapping("/iperror")
    public Result iperror() {
        return Result.failed("IP地址未加入白名单");
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
        SysWhiteIp model = sysWhiteIpService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存或修改")
    @PostMapping
    public Result saveOrUpdateSysWhiteIp(@RequestBody SysWhiteIp sysWhiteIp, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(sysWhiteIp)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(sysWhiteIp.getIp())) {
            return Result.failed("白名单IP不能为空");
        }
        return sysWhiteIpService.saveOrUpdateSysWhiteIp(sysWhiteIp,user);
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
        return sysWhiteIpService.deleteSysWhiteIp(id);
    }
}
