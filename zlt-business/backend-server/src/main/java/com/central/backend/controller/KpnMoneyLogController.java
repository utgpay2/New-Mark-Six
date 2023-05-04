package com.central.backend.controller;

import com.central.backend.co.KpnMoneyLogCo;
import com.central.backend.service.IKpnMoneyLogService;
import com.central.common.model.KpnMoneyLog;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.enums.KbChangeTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(tags = "K币账变记录api")
@Validated
@RequestMapping("/moneyLog")
public class KpnMoneyLogController {

    @Autowired
    private IKpnMoneyLogService moneyLogService;

    @ApiOperation("查询K币账变记录列表")
    @ResponseBody
    @GetMapping("/findMoneyLogList")
    public Result<PageResult<KpnMoneyLog>> findMoneyLogList(@ModelAttribute KpnMoneyLogCo params) {
        PageResult<KpnMoneyLog> moneyLog = moneyLogService.findMoneyLogList(params);
        moneyLog.getData().stream().forEach(info ->{
            info.setOrderTypeName(KbChangeTypeEnum.getTypeName(info.getOrderType()));
        });
        return Result.succeed(moneyLog);
    }




}
