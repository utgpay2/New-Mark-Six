package com.proxy.center.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.proxy.center.co.SiteAdvertiseCo;
import com.proxy.center.co.SiteAdvertiseUpdateCo;
import com.proxy.center.mapper.SiteAdvertiseMapper;
import com.proxy.center.service.ISiteAdvertiseService;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SiteAdvertise;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.oss.template.MinioTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SiteAdvertiseServiceImpl extends SuperServiceImpl<SiteAdvertiseMapper, SiteAdvertise> implements ISiteAdvertiseService {

   @Autowired
   private MinioTemplate minioTemplate;

   @Override
   public PageResult<SiteAdvertise> findAdvertiseList(SiteAdvertiseCo params) {
      Page<SiteAdvertise> page = new Page<>(params.getPage(), params.getLimit());
      LambdaQueryWrapper<SiteAdvertise> wrapper=new LambdaQueryWrapper<>();
      if (params.getSiteId()!=null){
         wrapper.eq(SiteAdvertise::getSiteId, params.getSiteId());
      }

      if (StringUtils.isNotBlank(params.getName())){
         wrapper.like(SiteAdvertise::getNameZh, params.getName())
                 .or().like(SiteAdvertise::getNameEn,params.getName()).or().like(SiteAdvertise::getNameKh, params.getName());
      }

      if (params.getPosition()!=null){
         wrapper.eq(SiteAdvertise::getPosition, params.getPosition());
      }

      if (params.getStatus()!=null){
         wrapper.eq(SiteAdvertise::getStatus, params.getStatus());
      }

      if (StringUtils.isNotBlank(params.getStartTime())) {
         wrapper.ge(SiteAdvertise::getCreateTime, params.getStartTime());
      }
      if (StringUtils.isNotBlank(params.getEndTime())) {
         wrapper.le(SiteAdvertise::getCreateTime, params.getEndTime());
      }
      if(StringUtils.isNotBlank(params.getSortBy())){
         if (params.getSortBy().equals("1")){
            wrapper.orderByAsc(SiteAdvertise::getHits);
         }
         if (params.getSortBy().equals("2")){
            wrapper.orderByDesc(SiteAdvertise::getHits);
         }
      }else {
         wrapper.orderByDesc(SiteAdvertise::getCreateTime);
      }
      Page<SiteAdvertise> list = baseMapper.selectPage(page, wrapper);
      long total = page.getTotal();
      return PageResult.<SiteAdvertise>builder().data(list.getRecords()).count(total).build();
   }

   @Override
   public Result saveOrUpdateAdvertise(SiteAdvertise advertise) {
      boolean insert =false;
      //新增
      if (advertise.getId() == null) {
         insert = super.save(advertise);
      }else {
         SiteAdvertise advertiseInfo = baseMapper.selectById(advertise.getId());
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
   public Result updateEnabledAdvertise(SiteAdvertiseUpdateCo params) {
      Long id = params.getId();
      SiteAdvertise advertise = baseMapper.selectById(id);
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