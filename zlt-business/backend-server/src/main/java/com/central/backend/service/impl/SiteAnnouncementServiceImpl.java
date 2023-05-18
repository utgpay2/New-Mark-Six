package com.central.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.co.SiteAnnouncementCo;
import com.central.backend.co.SiteAnnouncementUpdateCo;
import com.central.backend.mapper.SiteAnnouncementMapper;
import com.central.backend.service.ISiteAnnouncementService;
import com.central.common.model.SiteAnnouncement;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SiteAnnouncementServiceImpl extends SuperServiceImpl<SiteAnnouncementMapper, SiteAnnouncement> implements ISiteAnnouncementService {


    @Override
    public PageResult<SiteAnnouncement> findAnnouncementList(SiteAnnouncementCo params) {
        Page<SiteAnnouncement> page = new Page<>(params.getPage(), params.getLimit());
        LambdaQueryWrapper<SiteAnnouncement> wrapper=new LambdaQueryWrapper<>();
        if (params.getSiteId()!=null){
            wrapper.eq(SiteAnnouncement::getSiteId, params.getSiteId());
        }

        if (StringUtils.isNotBlank(params.getOperator())){
            wrapper.eq(SiteAnnouncement::getUpdateBy, params.getOperator());
        }

        if (StringUtils.isNotBlank(params.getTitle())){
            wrapper.like(SiteAnnouncement::getTitleZh, params.getTitle())
                    .or().like(SiteAnnouncement::getTitleEn,params.getTitle()).or().like(SiteAnnouncement::getTitleKh, params.getTitle());
        }

        if (StringUtils.isNotBlank(params.getStartTime())) {
            wrapper.ge(SiteAnnouncement::getCreateTime, params.getStartTime());
        }
        if (StringUtils.isNotBlank(params.getEndTime())) {
            wrapper.le(SiteAnnouncement::getCreateTime, params.getEndTime());
        }

        wrapper.orderByDesc(SiteAnnouncement::getCreateTime);
        Page<SiteAnnouncement> list = baseMapper.selectPage(page, wrapper);
        long total = page.getTotal();
        return PageResult.<SiteAnnouncement>builder().data(list.getRecords()).count(total).build();
    }

    @Override
    public Result saveOrUpdateAnnouncement(SiteAnnouncement announcement) {
        boolean insert =false;
        String moduleName=null;
        //新增
        if (announcement.getId() == null) {
            insert = super.save(announcement);
        }else {
            SiteAnnouncement sysNotice = baseMapper.selectById(announcement.getId());
            if (sysNotice == null) {
                return Result.failed("此公告不存在");
            }
            //修改
            insert = super.updateById(announcement);
        }
        if(insert){
            return  Result.succeed(announcement, "操作成功");
        }
        return Result.failed("操作失败");
    }

    @Override
    public SiteAnnouncement selectById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public boolean delAnnouncementId(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    @Override
    public Result updateEnabled(SiteAnnouncementUpdateCo params) {
        Long id = params.getId();
        Integer state = params.getStatus();
        SiteAnnouncement announcement = baseMapper.selectById(id);
        if (announcement == null) {
            return Result.failed("此公告不存在");
        }
        announcement.setStatus(state);
        announcement.setUpdateBy(params.getUpdateBy());
        int i = baseMapper.updateById(announcement);
        return i>0 ? Result.succeed(announcement, "更新成功"): Result.failed("更新失败");
    }
}