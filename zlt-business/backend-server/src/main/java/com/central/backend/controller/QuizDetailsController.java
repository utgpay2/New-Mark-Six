package com.central.backend.controller;

import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.service.IQuizDetailsService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.QuizDetails;
import com.central.common.model.SysUser;
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
 * 竞猜分类
 *
 * @author zlt
 * @date 2023-05-30 13:00:21
 */
@Slf4j
@RestController
@RequestMapping("/quizdetails")
@Api(tags = "竞猜分类")
public class QuizDetailsController {
    @Autowired
    private IQuizDetailsService quizDetailsService;

    /**
     * 列表
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "quizId", value = "开奖分类二类ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "sortBy", value = "排序方式：1正序(默认)、2倒叙", required = false, dataType = "Integer")
    })
    @GetMapping
    public Result<List<QuizDetails>> list(@RequestParam Map<String, Object> params) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("quizId"))) {
            return Result.failed("开奖分类二类ID");
        }
        return Result.succeed(quizDetailsService.findList(params));
    }

//    /**
//     * 查询
//     */
//    @ApiOperation(value = "查询")
//    @GetMapping("/{id}")
//    public Result findUserById(@PathVariable Long id) {
//        QuizDetails model = quizDetailsService.getById(id);
//        return Result.succeed(model, "查询成功");
//    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存")
    @PostMapping
    public Result saveOrUpdateQuizDetails(@RequestBody QuizDetails quizDetails, @ApiIgnore @LoginUser SysUser user) {
        return quizDetailsService.saveOrUpdateQuizDetails(quizDetails,user);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public Result deleteQuizDetails(@PathVariable Long id) {
        return quizDetailsService.deleteQuizDetails(id);
    }
}
