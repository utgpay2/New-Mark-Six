package com.central.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.co.KpnSiteAnnouncementCo;
import com.central.backend.co.KpnSiteAnnouncementUpdateCo;
import com.central.backend.mapper.KpnSiteAnnouncementMapper;
import com.central.backend.model.SysOperationLog;
import com.central.backend.service.IKpnSiteAnnouncementService;
import com.central.common.model.KpnSiteAnnouncement;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Slf4j
@Service
public class KpnSiteAnnouncementServiceImpl extends SuperServiceImpl<KpnSiteAnnouncementMapper, KpnSiteAnnouncement> implements IKpnSiteAnnouncementService {


    @Override
    public PageResult<KpnSiteAnnouncement> findAnnouncementList(KpnSiteAnnouncementCo params) {
        Page<KpnSiteAnnouncement> page = new Page<>(params.getPage(), params.getLimit());
        LambdaQueryWrapper<KpnSiteAnnouncement> wrapper=new LambdaQueryWrapper<>();
        if (params.getSiteId()!=null){
            wrapper.eq(KpnSiteAnnouncement::getSiteId, params.getSiteId());
        }

        if (StringUtils.isNotBlank(params.getOperator())){
            wrapper.eq(KpnSiteAnnouncement::getUpdateBy, params.getOperator());
        }

        if (StringUtils.isNotBlank(params.getTitle())){
            wrapper.like(KpnSiteAnnouncement::getTitleZh, params.getTitle())
                    .or().like(KpnSiteAnnouncement::getTitleEn,params.getTitle()).or().like(KpnSiteAnnouncement::getTitleKh, params.getTitle());
        }

        if (StringUtils.isNotBlank(params.getStartTime())) {
            wrapper.ge(KpnSiteAnnouncement::getCreateTime, params.getStartTime());
        }
        if (StringUtils.isNotBlank(params.getEndTime())) {
            wrapper.le(KpnSiteAnnouncement::getCreateTime, params.getEndTime());
        }

        wrapper.orderByDesc(KpnSiteAnnouncement::getCreateTime);
        Page<KpnSiteAnnouncement> list = baseMapper.selectPage(page, wrapper);
        long total = page.getTotal();
        return PageResult.<KpnSiteAnnouncement>builder().data(list.getRecords()).count(total).build();
    }

    @Override
    public Result saveOrUpdateAnnouncement(KpnSiteAnnouncement announcement) {
        boolean insert =false;
        String moduleName=null;
        //新增
        if (announcement.getId() == null) {
            insert = super.save(announcement);
        }else {
            KpnSiteAnnouncement sysNotice = baseMapper.selectById(announcement.getId());
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
    public KpnSiteAnnouncement selectById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public boolean delAnnouncementId(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    @Override
    public Result updateEnabled(KpnSiteAnnouncementUpdateCo params) {
        Long id = params.getId();
        Integer state = params.getStatus();
        KpnSiteAnnouncement announcement = baseMapper.selectById(id);
        if (announcement == null) {
            return Result.failed("此公告不存在");
        }
        announcement.setStatus(state);
        announcement.setUpdateBy(params.getUpdateBy());
        int i = baseMapper.updateById(announcement);
        return i>0 ? Result.succeed(announcement, "更新成功"): Result.failed("更新失败");
    }
}