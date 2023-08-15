package com.proxy.center.controller;


import cn.hutool.core.util.ObjectUtil;

import com.central.common.annotation.LoginUser;
import com.central.common.model.Result;
import com.central.common.model.Statement;
import com.central.common.model.SysUser;
import com.proxy.center.service.IStatementService;
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


}
