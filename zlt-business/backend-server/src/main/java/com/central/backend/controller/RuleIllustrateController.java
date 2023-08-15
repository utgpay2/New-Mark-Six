package com.central.backend.controller;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.service.IRuleIllustrateService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.Result;
import com.central.common.model.RuleIllustrate;
import com.central.common.model.Statement;
import com.central.common.model.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Slf4j
@RestController
@Api(tags = "彩种玩法说明")
@Validated
@RequestMapping("/rule_illustrate")
public class RuleIllustrateController {

    @Autowired
    IRuleIllustrateService ruleIllustrateService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询玩法")
    @GetMapping("/{lotteryId}")
    public Result<List<RuleIllustrate>> list(@PathVariable Long  lotteryId) {

        return Result.succeed(ruleIllustrateService.findList(lotteryId));
    }


    /**
     * 新增or更新
     */
    @ApiOperation(value = "新增or更新玩法")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdateQuiz(@RequestBody RuleIllustrate ruleIllustrate, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(ruleIllustrate)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(ruleIllustrate.getLotteryId())) {
            return Result.failed("彩种不能为空");
        }
        if (ObjectUtil.isEmpty(ruleIllustrate.getTitle())) {
            return Result.failed("标题不能为空");
        }
        if (ObjectUtil.isEmpty(ruleIllustrate.getIllustrate())) {
            return Result.failed("说明不能为空");
        }

        return ruleIllustrateService.saveOrUpdateQuiz(ruleIllustrate,user);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除玩法")
    @DeleteMapping("/deleteQuiz/{id}")
    public Result deleteQuiz(@PathVariable long id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.failed("请求参数不能为空");
        }
        ruleIllustrateService.removeById(id);
        return Result.succeed("操作成功");
    }
}
