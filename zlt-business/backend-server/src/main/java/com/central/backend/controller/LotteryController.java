package com.central.backend.controller;

import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.model.vo.SiteLotteryVO;
import com.central.backend.service.ILotteryService;
import com.central.backend.service.ISiteLotteryService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import springfox.documentation.annotations.ApiIgnore;

/**
 * 彩种表
 *
 * @author zlt
 * @date 2023-05-09 19:57:30
 */
@Slf4j
@RestController
@RequestMapping("/lottery")
@Api(tags = "彩种表")
public class LotteryController {
    @Autowired
    private ILotteryService lotteryService;
    @Autowired
    private ISiteLotteryService siteLotteryService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询彩种列表（超级管理员权限）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping
    public PageResult list(@RequestParam Map<String, Object> params) {
        return lotteryService.findList(params);
    }


    /**
     * 新增or更新
     */
    @ApiOperation(value = "新增or更新彩种（超级管理员权限）")
    @PostMapping
    public Result save(@RequestBody Lottery lottery) {
        if (ObjectUtil.isEmpty(lottery)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(lottery.getLotteryName())) {
            return Result.failed("彩种名称不能为空");
        }
        if (ObjectUtil.isEmpty(lottery.getBetDeadlineTime())) {
            return Result.failed("下注截止时间不能为空");
        }
        if (ObjectUtil.isEmpty(lottery.getBetSettlementTime())) {
            return Result.failed("结算完成时间不能为空");
        }
        lotteryService.saveOrUpdate(lottery);
        return Result.succeed("保存成功");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除彩种（超级管理员权限）")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.failed("ID不能为空");
        }
        lotteryService.removeById(id);
        return Result.succeed("删除成功");
    }

    @ApiOperation(value = "查询站点彩种（系统管理员权限）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "分类名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "siteId", value = "站点id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "sortBy", value = "排序方式：1正序(默认)、2倒叙", required = false, dataType = "Integer")
    })
    @GetMapping("/listsitelottery")
    public Result<List<SiteLotteryVO>> listSiteLottery(@RequestParam Map<String, Object> params) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("siteId"))) {
            return Result.failed("站点id不能为空");
        }
        return Result.succeed(siteLotteryService.findList(params));
    }
    /**
     * 新增or更新
     */
    @ApiOperation(value = "新增or更新站点彩种（系统管理员权限）")
    @PostMapping("/saveorupdatesitelottery")
    public Result saveOrUpdateSiteLottery(@RequestBody SiteLottery lottery, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(lottery)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(lottery.getSiteId())) {
            return Result.failed("站点id不能为空");
        }
        if (ObjectUtil.isEmpty(lottery.getSiteName())) {
            return Result.failed("站点名称不能为空");
        }
        if (ObjectUtil.isEmpty(lottery.getSiteCode())) {
            return Result.failed("站点编码不能为空");
        }
        if (ObjectUtil.isEmpty(lottery.getLotteryId())) {
            return Result.failed("彩种ID不能为空");
        }
        return siteLotteryService.saveOrUpdateSiteLottery(lottery,user);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除站点彩种（系统管理员权限）")
    @DeleteMapping("/deletesitelottery/{id}")
    public Result deleteSiteLottery(@PathVariable Long id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.failed("ID不能为空");
        }
        return siteLotteryService.deleteSiteLottery(id);
    }
}
