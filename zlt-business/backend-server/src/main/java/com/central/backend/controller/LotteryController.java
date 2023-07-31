package com.central.backend.controller;

import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.central.common.vo.SiteLotteryVo;
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
    @GetMapping
    public Result<List<Lottery>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        return Result.succeed(lotteryService.findList(params));
    }


    /**
     * 新增or更新
     */
    @ApiOperation(value = "新增or更新彩种（超级管理员权限）")
    @PostMapping("/saveOrUpdateLottery")
    public Result saveOrUpdateLottery(@RequestBody Lottery lottery, @ApiIgnore @LoginUser SysUser user) {
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
        if (ObjectUtil.isEmpty(lottery.getSort())) {
            return Result.failed("顺序不能为空");
        }
        return lotteryService.saveOrUpdateLottery(lottery,user);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除彩种（超级管理员权限）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "彩种id", required = true, dataType = "Integer")
    })
    @PostMapping("/deletelottery")
    public Result delete(@RequestParam(value = "id", required = true)  Long id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.failed("ID不能为空");
        }
        return lotteryService.deleteLottery(id);
    }

    @ApiOperation(value = "查询站点彩种（系统管理员权限）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "siteId", value = "站点id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "sortBy", value = "排序方式：1正序(默认)、2倒叙", required = false, dataType = "Integer")
    })
    @GetMapping("/listsitelottery")
    public Result<List<SiteLotteryVo>> listSiteLottery(@ApiIgnore @RequestParam Map<String, Object> params) {
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
    @PostMapping("/deletesitelottery")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "站点彩种id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "siteId", value = "站点id", required = true, dataType = "Integer")
    })
    public Result deleteSiteLottery(@RequestParam(value = "id", required = true)  Long id,@RequestParam(value = "siteId", required = true)  Integer siteId) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.failed("站点彩种ID不能为空");
        }
        if (ObjectUtil.isEmpty(siteId)) {
            return Result.failed("站点ID不能为空");
        }
        return siteLotteryService.deleteSiteLottery(id,siteId);
    }
}
