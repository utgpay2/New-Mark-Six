package com.central.backend.controller;

import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.service.IWnDataService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.SysUser;
import com.central.common.model.WnData;
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
 * 开奖数据
 *
 * @author zlt
 * @date 2023-05-09 18:39:54
 */
@Slf4j
@RestController
@RequestMapping("/wndata")
@Api(tags = "开奖数据")
public class WnDataController {
    @Autowired
    private IWnDataService wnDataService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "qihao", value = "期号", required = false, dataType = "Long"),
            @ApiImplicitParam(name = "sortBy", value = "排序方式：1正序、2倒叙(默认)", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "lotteryId", value = "彩种id", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping
    public Result<PageResult>  list(@ApiIgnore @RequestParam Map<String, Object> params) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("page"))) {
            return Result.failed("分页起始位置不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("limit"))) {
            return Result.failed("分页结束位置不能为空");
        }
        return Result.succeed(wnDataService.findList(params));
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("/{id}")
    public Result findUserById(@PathVariable Long id) {
        WnData model = wnDataService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "新增or更新")
    @PostMapping("/saveorupdatewndata")
    public Result saveOrUpdateWnData(@RequestBody WnData wnData, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(wnData)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(wnData.getQihao())) {
            return Result.failed("期号不能为空");
        }
        if (ObjectUtil.isEmpty(wnData.getLotteryId())) {
            return Result.failed("彩种id不能为空");
        }
        if (ObjectUtil.isEmpty(wnData.getNumbers())) {
            return Result.failed("开奖号码不能为空");
        }
        if (ObjectUtil.isEmpty(wnData.getNextTime())) {
            return Result.failed("下一期开奖时间不能为空");
        }
        if (ObjectUtil.isEmpty(wnData.getNextQihao())) {
            return Result.failed("下一期号不能为空");
        }
        if (ObjectUtil.isEmpty(wnData.getYear())) {
            return Result.failed("号码归属年份不能为空");
        }
        return wnDataService.saveOrUpdateWnData(wnData,user);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "开奖ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "lotteryId", value = "彩种id", required = true, dataType = "Integer")
    })
    @PostMapping("/deletewindata")
    public Result delete(@RequestParam(value = "id", required = true)  Long id,@RequestParam(value = "lotteryId", required = true) Integer lotteryId) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.failed("开奖ID不能为空");
        }
        if (ObjectUtil.isEmpty(lotteryId)) {
            return Result.failed("彩种id不能为空");
        }
        return wnDataService.deleteWnData(id,lotteryId);
    }
}
