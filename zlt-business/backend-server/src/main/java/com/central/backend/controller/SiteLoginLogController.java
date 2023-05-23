package com.central.backend.controller;

import com.central.backend.co.SiteLoginLogCo;
import com.central.backend.service.ISiteLoginLogService;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SiteLoginLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@Api(tags = "会员登录日志api")
@Validated
@RequestMapping("/siteLoginLog")
public class SiteLoginLogController {

    @Autowired
    private ISiteLoginLogService siteLoginLogService;



    @ApiOperation("查询会员登录日志列表")
    @ResponseBody
    @GetMapping("/findUserLoginLogList")
    public Result<PageResult<SiteLoginLog>> findUserLoginLogList(@ModelAttribute SiteLoginLogCo params) {
        PageResult<SiteLoginLog> siteLoginLog = siteLoginLogService.findUserLoginLogList(params);
        return Result.succeed(siteLoginLog);
    }




}
