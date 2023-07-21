package com.central.backend.controller;

import com.central.backend.model.vo.UserBettingDetailedReportFormsVo;
import com.central.backend.model.vo.UserMoneyDetailedReportFormsVo;
import com.central.backend.model.vo.UserReportFormsVo;
import com.central.backend.service.IQuizOrdersService;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RestController
@Api(tags = "运营报表api")
@Validated
@RequestMapping("/summary")
public class RptSiteSummaryController {


    @Autowired
    private IQuizOrdersService quizOrderSonService;

    /**
     * 列表
     */
    @ApiOperation(value = "会员报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "siteId", value = "站点id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = " Date"),
            @ApiImplicitParam(name = "endTime", value = "开始时间", required = true, dataType = " Date")

    })
    @GetMapping("/user")
    public Result<PageResult<UserReportFormsVo>> userReportForms(@RequestParam Map<String, Object> params) {

        return Result.succeed(quizOrderSonService.userReportForms(params));
    }

    /**
     * 列表
     */
    @ApiOperation(value = "会员报表导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "siteId", value = "站点id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = " Date"),
            @ApiImplicitParam(name = "endTime", value = "开始时间", required = true, dataType = " Date")

    })
    @GetMapping("/user/export")
    public Result userReportFormsExport(@RequestParam Map<String, Object> params, HttpServletResponse response) {

        quizOrderSonService.userReportFormsExport(params, response);
        return Result.succeed();
    }


    /**
     * 列表
     */
    @ApiOperation(value = "会员资金明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "siteId", value = "站点id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = " Date"),
            @ApiImplicitParam(name = "endTime", value = "开始时间", required = true, dataType = " Date"),
            @ApiImplicitParam(name = "username", value = "用户名", required = false, dataType = " String"),
    })
    @GetMapping("/userMoneyDetailed")
    public Result<PageResult<UserMoneyDetailedReportFormsVo>> userMoneyDetailed(@RequestParam Map<String, Object> params) {

        return Result.succeed(quizOrderSonService.userMoneyDetailed(params));
    }

    /**
     * 列表
     */
    @ApiOperation(value = "会员资金明细报表导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "siteId", value = "站点id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = " Date"),
            @ApiImplicitParam(name = "endTime", value = "开始时间", required = true, dataType = " Date"),
            @ApiImplicitParam(name = "username", value = "用户名", required = false, dataType = " String"),
    })
    @GetMapping("/userMoneyDetailed/export")
    public Result userMoneyDetailedExport(@RequestParam Map<String, Object> params, HttpServletResponse httpServletResponse) {

        quizOrderSonService.userMoneyDetailedExport(params, httpServletResponse);
        return Result.succeed();
    }


    /**
     * 列表
     */
    @ApiOperation(value = "会员投注记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "siteId", value = "站点id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = " Date"),
            @ApiImplicitParam(name = "endTime", value = "开始时间", required = true, dataType = " Date"),
            @ApiImplicitParam(name = "username", value = "用户名", required = false, dataType = " String"),
    })
    @GetMapping("/userBettingDetailed")
    public Result<PageResult<UserBettingDetailedReportFormsVo>> userBettingDetailed(@RequestParam Map<String, Object> params) {

        return Result.succeed(quizOrderSonService.userBettingDetailed(params));

    }

    /**
     * 列表
     */
    @ApiOperation(value = "会员投注记录报表导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "siteId", value = "站点id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = " Date"),
            @ApiImplicitParam(name = "endTime", value = "开始时间", required = true, dataType = " Date"),
            @ApiImplicitParam(name = "username", value = "用户名", required = false, dataType = " String"),
    })
    @GetMapping("/userBettingDetailed/export")
    public Result userBettingDetailedExport(@RequestParam Map<String, Object> params, HttpServletResponse httpServletResponse) {

        quizOrderSonService.userBettingDetailedExport(params, httpServletResponse);
        return Result.succeed();
    }

}
