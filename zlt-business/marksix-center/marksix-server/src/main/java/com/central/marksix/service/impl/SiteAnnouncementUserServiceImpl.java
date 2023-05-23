package com.central.marksix.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.common.model.SiteAnnouncementUser;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.entity.dto.SiteAnnouncementUserDto;
import com.central.marksix.mapper.SiteAnnouncementUserMapper;
import com.central.marksix.service.ISiteAnnouncementUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class SiteAnnouncementUserServiceImpl extends SuperServiceImpl<SiteAnnouncementUserMapper, SiteAnnouncementUser> implements ISiteAnnouncementUserService {
    @Override
    public Result saveOrUpdateAnnUser(SiteAnnouncementUserDto dto, SysUser user) {
        LambdaQueryWrapper<SiteAnnouncementUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SiteAnnouncementUser::getUserId,user.getId());
        queryWrapper.eq(SiteAnnouncementUser::getAnnId,dto.getAnnId());
        SiteAnnouncementUser announcementUser = baseMapper.selectOne(queryWrapper);
        boolean insert = false;
        //新增
        if (announcementUser == null) {
            announcementUser = new SiteAnnouncementUser();
            BeanUtils.copyProperties(dto, announcementUser);
            announcementUser.setUserId(user.getId());
            announcementUser.setCreateTime(new Date());
            announcementUser.setUpdateTime(new Date());
            announcementUser.setCreateBy(null!=user?user.getUsername():"");
            announcementUser.setUpdateBy(null!=user?user.getUsername():"");
            super.save(announcementUser);
        }else {
            announcementUser.setUpdateTime(new Date());
            announcementUser.setIsRead(dto.getIsRead());
            announcementUser.setUpdateBy(null!=user?user.getUsername():"");
            super.updateById(announcementUser);
        }
        return Result.succeed(announcementUser, "操作成功");
    }
}