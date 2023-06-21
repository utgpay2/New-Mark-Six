package com.central.backend.controller;

import java.util.Map;

import com.central.backend.service.ILotteryKjTimeService;
import com.central.common.model.LotteryKjTime;
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
 * 开奖时间
 *
 * @author zlt
 * @date 2023-05-09 19:59:03
 */
@Slf4j
@RestController
@RequestMapping("/lotterykjtime")
@Api(tags = "开奖时间")
public class LotteryKjTimeController {
    @Autowired
    private ILotteryKjTimeService lotteryKjTimeService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping
    public PageResult list(@ApiIgnore @RequestParam Map<String, Object> params) {
        return lotteryKjTimeService.findList(params);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("/{id}")
    public Result findUserById(@PathVariable Long id) {
        LotteryKjTime model = lotteryKjTimeService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存")
    @PostMapping
    public Result save(@RequestBody LotteryKjTime lotteryKjTime) {
        lotteryKjTimeService.saveOrUpdate(lotteryKjTime);
        return Result.succeed("保存成功");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        lotteryKjTimeService.removeById(id);
        return Result.succeed("删除成功");
    }
}
