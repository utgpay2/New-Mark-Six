package com.central.porn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.common.model.KpnSiteAnnouncementUser;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.porn.entity.dto.KpnSiteAnnouncementUserDto;
import com.central.porn.mapper.KpnSiteAnnouncementUserMapper;
import com.central.porn.service.IKpnSiteAnnouncementUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class KpnSiteAnnouncementUserServiceImpl extends SuperServiceImpl<KpnSiteAnnouncementUserMapper, KpnSiteAnnouncementUser> implements IKpnSiteAnnouncementUserService {
    @Override
    public Result saveOrUpdateAnnUser(KpnSiteAnnouncementUserDto dto, SysUser user) {
        LambdaQueryWrapper<KpnSiteAnnouncementUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(KpnSiteAnnouncementUser::getUserId,user.getId());
        queryWrapper.eq(KpnSiteAnnouncementUser::getAnnId,dto.getAnnId());
        KpnSiteAnnouncementUser announcementUser = baseMapper.selectOne(queryWrapper);
        boolean insert = false;
        //新增
        if (announcementUser == null) {
            announcementUser = new KpnSiteAnnouncementUser();
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