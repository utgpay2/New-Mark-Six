package com.central.backend.controller;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.service.ICategoryService;
import com.central.backend.service.IKillOddsService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.Category;
import com.central.common.model.KillOdds;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 设置杀率
 *
 * @author yixiu
 * @date 2023-02-03 16:32:41
 */
@Slf4j
@RestController
@RequestMapping("/killodds")
@Api(tags = "设置杀率")
public class KillOddsController {
    @Autowired
    private IKillOddsService killOddsService;

    /**
     * 查询所有分类
     */
    @ApiOperation(value = "查询杀率")
    @GetMapping("/list")
    public Result<List<KillOdds>> listAll(@ApiIgnore @RequestParam Map<String, Object> params) {
        return Result.succeed(killOddsService.findList(params));
    }


    /**
     * 新增or更新
     */
    @ApiOperation(value = "新增or更新杀率（系统管理员权限）")
    @PostMapping("/saveorupdatekillodds")
    public Result saveOrUpdateKillOdds(@RequestBody KillOdds killOdds, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(killOdds)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(killOdds.getKillOdds())) {
            return Result.failed("杀率不能为空");
        }
        if (ObjectUtil.isEmpty(killOdds.getLotteryId())) {
            return Result.failed("彩种ID不能为空");
        }
        return killOddsService.saveOrUpdateKillOdds(killOdds,user);
    }

}