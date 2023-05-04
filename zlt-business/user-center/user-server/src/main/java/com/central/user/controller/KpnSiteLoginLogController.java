package com.central.user.controller;

import com.central.common.model.Result;
import com.central.common.model.SiteLoginLog;
import com.central.user.service.ISiteLoginLogService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@Api(tags = "会员登录日志")
@RequestMapping("/loginLog")
public class KpnSiteLoginLogController {
    @Autowired
    private ISiteLoginLogService siteLoginLogService;

    @PostMapping("/addLog")
    public Result<Boolean> addLoginlog(@RequestBody SiteLoginLog siteLoginLog){
        Boolean result = siteLoginLogService.save(siteLoginLog);
        return result? Result.succeed(Boolean.TRUE):Result.succeed(Boolean.FALSE);
    }
}
