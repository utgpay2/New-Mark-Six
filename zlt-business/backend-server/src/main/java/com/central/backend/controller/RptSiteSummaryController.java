package com.central.backend.controller;

import com.central.backend.co.RptSiteSummaryCo;
import com.central.backend.service.IRptSiteSummaryService;
import com.central.backend.vo.RptSiteSummaryVo;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.RptSiteSummary;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@Api(tags = "运营报表api")
@Validated
@RequestMapping("/summary")
public class RptSiteSummaryController {

    @Autowired
    private IRptSiteSummaryService summaryService;



    @ApiOperation("查询运营报表")
    @ResponseBody
    @GetMapping("/findSummaryList")
    public Result<PageResult<RptSiteSummary>> findSummaryList(@ModelAttribute RptSiteSummaryCo params) {
        PageResult<RptSiteSummary> list = summaryService.findSummaryList(params);
        return Result.succeed(list);
    }


    @ApiOperation(value = "总计")
    @GetMapping("/findSummaryTotal")
    public Result<RptSiteSummaryVo> findSummaryTotal(@ModelAttribute RptSiteSummaryCo params) {
        RptSiteSummaryVo summaryVo = summaryService.findSummaryTotal(params);
        return Result.succeed(summaryVo);
    }


}
