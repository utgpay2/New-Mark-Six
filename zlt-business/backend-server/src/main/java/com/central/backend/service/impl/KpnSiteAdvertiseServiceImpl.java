package com.central.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.co.KpnSiteAdvertiseCo;
import com.central.backend.co.KpnSiteAdvertiseUpdateCo;
import com.central.backend.mapper.KpnSiteAdvertiseMapper;
import com.central.backend.service.IKpnSiteAdvertiseService;
import com.central.backend.util.PictureUtil;
import com.central.common.model.KpnSiteAdvertise;
import com.central.common.model.KpnSiteAnnouncement;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.oss.model.ObjectInfo;
import com.central.oss.template.MinioTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Slf4j
@Service
public class KpnSiteAdvertiseServiceImpl extends SuperServiceImpl<KpnSiteAdvertiseMapper, KpnSiteAdvertise> implements IKpnSiteAdvertiseService {

   @Autowired
   private MinioTemplate minioTemplate;

   @Override
   public PageResult<KpnSiteAdvertise> findAdvertiseList(KpnSiteAdvertiseCo params) {
      Page<KpnSiteAdvertise> page = new Page<>(params.getPage(), params.getLimit());
      LambdaQueryWrapper<KpnSiteAdvertise> wrapper=new LambdaQueryWrapper<>();
      if (params.getSiteId()!=null){
         wrapper.eq(KpnSiteAdvertise::getSiteId, params.getSiteId());
      }

      if (StringUtils.isNotBlank(params.getName())){
         wrapper.like(KpnSiteAdvertise::getNameZh, params.getName())
                 .or().like(KpnSiteAdvertise::getNameEn,params.getName()).or().like(KpnSiteAdvertise::getNameKh, params.getName());
      }

      if (params.getPosition()!=null){
         wrapper.eq(KpnSiteAdvertise::getPosition, params.getPosition());
      }

      if (params.getStatus()!=null){
         wrapper.eq(KpnSiteAdvertise::getStatus, params.getStatus());
      }

      if (StringUtils.isNotBlank(params.getStartTime())) {
         wrapper.ge(KpnSiteAdvertise::getCreateTime, params.getStartTime());
      }
      if (StringUtils.isNotBlank(params.getEndTime())) {
         wrapper.le(KpnSiteAdvertise::getCreateTime, params.getEndTime());
      }
      if(StringUtils.isNotBlank(params.getSortBy())){
         if (params.getSortBy().equals("1")){
            wrapper.orderByAsc(KpnSiteAdvertise::getHits);
         }
         if (params.getSortBy().equals("2")){
            wrapper.orderByDesc(KpnSiteAdvertise::getHits);
         }
      }else {
         wrapper.orderByDesc(KpnSiteAdvertise::getCreateTime);
      }
      Page<KpnSiteAdvertise> list = baseMapper.selectPage(page, wrapper);
      long total = page.getTotal();
      return PageResult.<KpnSiteAdvertise>builder().data(list.getRecords()).count(total).build();
   }

   @Override
   public Result saveOrUpdateAdvertise(KpnSiteAdvertise advertise) {
      boolean insert =false;
      //新增
      if (advertise.getId() == null) {
         insert = super.save(advertise);
      }else {
         KpnSiteAdvertise advertiseInfo = baseMapper.selectById(advertise.getId());
         if (advertiseInfo == null) {
            return Result.failed("数据不存在");
         }
         //修改
         insert = super.updateById(advertise);
      }
      if(insert){
         return  Result.succeed(advertise, "操作成功");
      }
      return Result.failed("操作失败");
   }

   @Override
   public Result updateEnabledAdvertise(KpnSiteAdvertiseUpdateCo params) {
      Long id = params.getId();
      KpnSiteAdvertise advertise = baseMapper.selectById(id);
      if (advertise == null) {
         return Result.failed("此广告不存在");
      }
      advertise.setStatus(params.getStatus());
      advertise.setUpdateBy(params.getUpdateBy());
      int i = baseMapper.updateById(advertise);
      return i>0 ? Result.succeed(advertise, "更新成功"): Result.failed("更新失败");
   }

   @Override
   public Boolean deleteAdvertiseId(Long id) {
      return baseMapper.deleteById(id) > 0;
   }


}