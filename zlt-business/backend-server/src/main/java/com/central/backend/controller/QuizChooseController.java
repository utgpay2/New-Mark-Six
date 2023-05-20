package com.central.backend.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.service.IQuizChooseService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.QuizChoose;
import com.central.common.model.SysUser;
import com.central.common.utils.StringUtils;
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
 * 竞猜奖项详情
 *
 * @author zlt
 * @date 2023-05-09 18:42:17
 */
@Slf4j
@RestController
@RequestMapping("/quizchoose")
@Api(tags = "竞猜奖项详情")
public class QuizChooseController {
    @Autowired
    private IQuizChooseService quizChooseService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "quizId", value = "开奖规则主表ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "sortBy", value = "排序方式：1正序(默认)、2倒叙", required = false, dataType = "Integer")
    })
    @GetMapping
    public Result<List<QuizChoose>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("quizId"))) {
            return Result.failed("开奖规则主表ID");
        }
        return Result.succeed(quizChooseService.findList(params));
    }


    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存")
    @PostMapping
    public Result save(@RequestBody QuizChoose quizChoose, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(quizChoose)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(quizChoose.getQuizId())) {
            return Result.failed("开奖规则主表ID不能为空");
        }
        if (ObjectUtil.isEmpty(quizChoose.getIntroduce())) {
            return Result.failed("标题不能为空");
        }
        if (ObjectUtil.isEmpty(quizChoose.getSort())) {
            return Result.failed("顺序不能为空");
        }
        if (ObjectUtil.isEmpty(quizChoose.getOdds())) {
            return Result.failed("赔率不能为空");
        }
        if(StringUtils.isNotNull(quizChoose.getId())){
            quizChoose.setUpdateBy(user.getUsername());
            quizChoose.setUpdateTime(new Date());
        }else {
            quizChoose.setCreateBy(user.getUsername());
            quizChoose.setCreateTime(new Date());
            quizChoose.setUpdateBy(user.getUsername());
            quizChoose.setUpdateTime(new Date());
        }
        quizChooseService.saveOrUpdate(quizChoose);
        return Result.succeed("保存成功");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        quizChooseService.removeById(id);
        return Result.succeed("删除成功");
    }
}
