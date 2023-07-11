package com.central.backend.controller.ipmanage;

import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.model.vo.BlackIpVo;
import com.central.backend.service.ipmanage.IBlackIpService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.SysUser;
import com.central.common.model.ipmanage.BlackIp;
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
 * @date 2023-02-03 15:50:11
 */
@Slf4j
@RestController
@RequestMapping("/blackip")
@Api(tags = "黑名单IP管理")
public class BlackIpController {
    @Autowired
    private IBlackIpService blackIpService;

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
    public Result<PageResult<BlackIpVo>> list(@RequestParam Map<String, Object> params, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("page"))) {
            return Result.failed("分页起始位置不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("limit"))) {
            return Result.failed("分页结束位置不能为空");
        }
        return Result.succeed(blackIpService.findList(params,user));
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
        BlackIp model = blackIpService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
     * IP黑名单检查
     * @param ip
     * @return
     */
    @ApiOperation(value = "IP黑名单检查")
    @GetMapping("/ipcheck")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ip", value = "IP地址", required = true, dataType = "String")
    })
    public Result ipcheck(@RequestParam String ip, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(ip)) {
            return Result.failed("IP地址不能为空");
        }
        return Result.succeed(blackIpService.ipcheck(ip,user));
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存或修改")
    @PostMapping
    public Result saveOrUpdateBlackIp(@RequestBody BlackIp blackIp, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(blackIp)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(blackIp.getIpSection())) {
            return Result.failed("黑名单IP不能为空");
        }
        return blackIpService.saveOrUpdateBlackIp(blackIp, user);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public Result deleteBlackIp(@PathVariable Long id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.failed("ID不能为空");
        }
        return blackIpService.deleteBlackIp(id);
    }
}
