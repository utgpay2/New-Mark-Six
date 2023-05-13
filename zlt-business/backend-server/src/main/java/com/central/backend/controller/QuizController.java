package com.central.backend.controller;

import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.service.IQuizService;
import com.central.common.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import com.central.common.model.PageResult;
import com.central.common.model.Result;

/**
 * 竞猜分类
 *
 * @author zlt
 * @date 2023-05-09 18:41:24
 */
@Slf4j
@RestController
@RequestMapping("/quiz")
@Api(tags = "竞猜分类")
public class QuizController {
    @Autowired
    private IQuizService quizService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "siteCategoryId", value = "站点分类ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping
    public Result<List<Quiz>> list(@RequestParam Map<String, Object> params) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("siteCategoryId"))) {
            return Result.failed("站点分类ID");
        }
        return Result.succeed(quizService.findList(params));
    }


    /**
     * 新增or更新
     */
    @ApiOperation(value = "新增or更新")
    @PostMapping
    public Result save(@RequestBody Quiz quiz) {
        if (ObjectUtil.isEmpty(quiz)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(quiz.getTitle())) {
            return Result.failed("标题不能为空");
        }
        if (ObjectUtil.isEmpty(quiz.getSiteCategoryId())) {
            return Result.failed("站点分类ID不能为空");
        }
        if (ObjectUtil.isEmpty(quiz.getSort())) {
            return Result.failed("顺序不能为空");
        }
        quizService.saveOrUpdate(quiz);
        return Result.succeed("保存成功");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public Result deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return Result.succeed("删除成功");
    }
}
