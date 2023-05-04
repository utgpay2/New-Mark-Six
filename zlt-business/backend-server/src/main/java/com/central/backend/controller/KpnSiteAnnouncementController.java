package com.central.backend.controller;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.co.KpnSiteAnnouncementCo;
import com.central.backend.co.KpnSiteAnnouncementUpdateCo;
import com.central.backend.service.IKpnSiteAnnouncementService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.KpnSiteAnnouncement;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@Api(tags = "公告模块api")
@Validated
@RequestMapping("/announcement")
public class KpnSiteAnnouncementController {

    @Autowired
    private IKpnSiteAnnouncementService announcementService;



    /* 查询公告管理列表
     * @Author: Lulu
     * @Date: 2023/2/1
     */
    @ApiOperation("查询公告管理列表")
    @ResponseBody
    @GetMapping("/findAnnouncementList")
    public Result<PageResult<KpnSiteAnnouncement>> findAnnouncementList(@ModelAttribute KpnSiteAnnouncementCo params) {
        PageResult<KpnSiteAnnouncement> noticeList = announcementService.findAnnouncementList(params);
        return Result.succeed(noticeList);
    }


    /* 新增or更新公告
     * @Author: Lulu
     * @Date: 2023/2/1
     */
    @ApiOperation(value = "新增or更新公告")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody KpnSiteAnnouncement announcement, @LoginUser SysUser sysUser)  {
        if (sysUser!=null) {
            if (announcement.getId() == null) {
                announcement.setUpdateBy(sysUser.getUsername());
                announcement.setCreateBy(sysUser.getUsername());
            } else {
                announcement.setUpdateBy(sysUser.getUsername());
            }
        }
        if (ObjectUtil.isEmpty(announcement.getTitleZh()) || ObjectUtil.isEmpty(announcement.getTitleEn()) || ObjectUtil.isEmpty(announcement.getTitleKh())) {
            return Result.failed("标题不能为空");
        }
        if (ObjectUtil.isEmpty(announcement.getContentZh()) || ObjectUtil.isEmpty(announcement.getContentEn()) || ObjectUtil.isEmpty(announcement.getContentKh())) {
            return Result.failed("内容不能为空");
        }
        if (ObjectUtil.isEmpty(announcement.getSort())) {
            return Result.failed("排序不能为空");
        }
        Result result = announcementService.saveOrUpdateAnnouncement(announcement);
        return result;
    }


    /* "删除公告"
     * @Author: Lulu
     * @Date: 2023/2/1
     */
    @ApiOperation("删除公告")
    @DeleteMapping(value = "/deleteAnnouncementId/{id}")
    public Result deleteAnnouncementId(@PathVariable Long id) {
        // 查询公告是否存在
        KpnSiteAnnouncement sysNotice = announcementService.selectById(id);
        if (sysNotice == null) {
            return Result.failed("此公告不存在");
        }
        boolean b = announcementService.delAnnouncementId(id);
        return b ? Result.succeed("删除成功") : Result.failed("删除失败");
    }


    @ApiOperation(value = "修改公告状态")
    @GetMapping("/updateEnabled")
    public Result updateEnabled(@Valid @ModelAttribute KpnSiteAnnouncementUpdateCo params, @LoginUser SysUser sysUser) {
        if (sysUser!=null) {
            params.setUpdateBy(sysUser.getUsername());
        }
        Result result = announcementService.updateEnabled(params);
        return result;
    }

}
