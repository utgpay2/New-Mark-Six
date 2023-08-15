package com.proxy.center.controller;

import com.central.common.model.Result;
import com.central.common.model.SiteAnnouncement;
import com.proxy.center.service.ISiteAnnouncementService;
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

import java.util.List;

@Slf4j
@RestController
@Api(tags = "公告模块api")
@Validated
@RequestMapping("/announcement")
public class SiteAnnouncementController {

    @Autowired
    private ISiteAnnouncementService announcementService;



    /* 查询公告管理列表
     * @Author: Lulu
     * @Date: 2023/2/1
     */
    @ApiOperation("查询公告管理列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "siteId", value = "商户id", required = true, dataType = "Long")

    })
    @GetMapping("/findAnnouncementList")
    public Result<List<SiteAnnouncement>> findAnnouncementList(@RequestParam Long siteId) {

        return Result.succeed(announcementService.findAnnouncementList(siteId));

    }

}
