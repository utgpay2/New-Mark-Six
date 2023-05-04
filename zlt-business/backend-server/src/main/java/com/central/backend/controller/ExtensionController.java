package com.central.backend.controller;

import com.central.backend.co.SysUserExtensionCo;
import com.central.backend.service.ISysUserService;
import com.central.backend.vo.UserExtensionListInfoVo;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@Api(tags = "推广报表api")
@Validated
@RequestMapping("/extension")
public class ExtensionController {


    @Autowired
    private ISysUserService userService;

    @ApiOperation("查询推广列表")
    @ResponseBody
    @GetMapping("/findUserExtensionList")
    public Result<PageResult<UserExtensionListInfoVo>> findUserExtensionList(@ModelAttribute SysUserExtensionCo params) {
        PageResult<UserExtensionListInfoVo> userList = userService.findUserExtensionList(params);
        return Result.succeed(userList);
    }
}
