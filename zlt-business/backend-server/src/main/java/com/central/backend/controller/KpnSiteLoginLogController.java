package com.central.backend.controller;

import com.central.backend.co.KpnSiteLoginLogCo;
import com.central.backend.service.IKpnSiteLoginLogService;
import com.central.common.model.KpnSiteLoginLog;
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
@Api(tags = "会员登录日志api")
@Validated
@RequestMapping("/siteLoginLog")
public class KpnSiteLoginLogController {

    @Autowired
    private IKpnSiteLoginLogService siteLoginLogService;



    @ApiOperation("查询会员登录日志列表")
    @ResponseBody
    @GetMapping("/findUserLoginLogList")
    public Result<PageResult<KpnSiteLoginLog>> findUserLoginLogList(@ModelAttribute KpnSiteLoginLogCo params) {
        PageResult<KpnSiteLoginLog> siteLoginLog = siteLoginLogService.findUserLoginLogList(params);
        return Result.succeed(siteLoginLog);
    }




}
