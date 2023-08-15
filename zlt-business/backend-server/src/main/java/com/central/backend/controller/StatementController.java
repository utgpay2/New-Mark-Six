package com.central.backend.controller;


import cn.hutool.core.util.ObjectUtil;
import com.central.backend.model.dto.QuizDto;
import com.central.backend.service.IStatementService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.Quiz;
import com.central.common.model.Result;
import com.central.common.model.Statement;
import com.central.common.model.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Api(tags = "声明api")
@Validated
@RequestMapping("/statement")
public class StatementController {

    @Autowired
    IStatementService statementService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询声明列表")
    @GetMapping
    public Result<List<Statement>> list() {

        return Result.succeed(statementService.findList());
    }


    /**
     * 新增or更新
     */
    @ApiOperation(value = "新增or更新声明")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdateQuiz(@RequestBody Statement statement, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(statement)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(statement.getStatement())) {
            return Result.failed("标题不能为空");
        }

        return statementService.saveOrUpdateQuiz(statement,user);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除声明")
    @DeleteMapping("/deleteQuiz/{id}")
    public Result deleteQuiz(@PathVariable long id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.failed("请求参数不能为空");
        }
        statementService.removeById(id);
        return Result.succeed("操作成功");
    }
}
