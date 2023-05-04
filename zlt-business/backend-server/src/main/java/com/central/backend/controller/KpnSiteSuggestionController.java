package com.central.backend.controller;

import com.central.backend.co.KpnSiteSuggestionCo;
import com.central.backend.co.KpnSiteSuggestionUpdateCo;
import com.central.backend.service.IKpnSiteSuggestionService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@Api(tags = "意见反馈api")
@Validated
@RequestMapping("/suggestion")
public class KpnSiteSuggestionController {

    @Autowired
    private IKpnSiteSuggestionService suggestionService;



    /* 查询意见反馈列表
     * @Author: Lulu
     * @Date: 2023/2/1
     */
    @ApiOperation("查询意见反馈列表")
    @ResponseBody
    @GetMapping("/findSuggestionList")
    public Result<PageResult<KpnSiteSuggestion>> findSuggestionList(@ModelAttribute KpnSiteSuggestionCo params) {
        PageResult<KpnSiteSuggestion> noticeList = suggestionService.findSuggestionList(params);
        return Result.succeed(noticeList);
    }


    @ApiOperation(value = "修改处理结果")
    @GetMapping("/updateSuggestionStatus")
    public Result updateSuggestionStatus(@Valid @ModelAttribute KpnSiteSuggestionUpdateCo params, @LoginUser SysUser sysUser) {
        if (sysUser!=null) {
            params.setUpdateBy(sysUser.getUsername());
        }
        Result result = suggestionService.updateSuggestionStatus(params);
        return result;
    }

}
