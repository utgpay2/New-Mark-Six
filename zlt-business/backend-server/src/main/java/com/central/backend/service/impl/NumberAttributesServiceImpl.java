package com.central.backend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.mapper.NumberAttributesMapper;
import com.central.backend.service.INumberAttributesService;
import com.central.common.constant.RedisConstants;
import com.central.common.dto.NumberAttributesDto;
import com.central.common.model.*;
import com.central.common.redis.template.RedisRepository;
import com.central.common.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;


/**
 * 号码属性表
 *
 * @author zlt
 * @date 2023-05-08 15:05:53
 */
@Slf4j
@Service
public class NumberAttributesServiceImpl extends SuperServiceImpl<NumberAttributesMapper, NumberAttributes> implements INumberAttributesService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public List<NumberAttributes> findList(Map<String, Object> params){
        return baseMapper.findList( params);
    }
    @Override
    public List<NumberAttributes> findList(NumberAttributesDto numberAttributesDto, Integer year){
        LambdaQueryWrapper<NumberAttributes> wrapper=new LambdaQueryWrapper<>();
        String str = "number";
        if(null!= numberAttributesDto){
            if(numberAttributesDto.getZodiac()!=null&&!"".equals(numberAttributesDto.getZodiac())) {
                wrapper.eq(NumberAttributes::getZodiac, numberAttributesDto.getZodiac());
                str = numberAttributesDto.getZodiac();
            }else if(numberAttributesDto.getPoultryandbeast()!=null&&!"".equals(numberAttributesDto.getPoultryandbeast())) {
                wrapper.eq(NumberAttributes::getPoultryandbeast, numberAttributesDto.getPoultryandbeast());
                str = numberAttributesDto.getPoultryandbeast();
            }else if(numberAttributesDto.getColor()!=null&&!"".equals(numberAttributesDto.getColor())) {
                wrapper.eq(NumberAttributes::getColor, numberAttributesDto.getColor());
                str = numberAttributesDto.getColor();
            }else if(numberAttributesDto.getFiveElements()!=null&&!"".equals(numberAttributesDto.getFiveElements())) {
                wrapper.eq(NumberAttributes::getFiveElements, numberAttributesDto.getFiveElements());
                str = numberAttributesDto.getFiveElements();
            }
        }
        wrapper.eq(NumberAttributes::getYear, year);
        wrapper.orderByAsc(NumberAttributes::getNumber);
        String redisKey = StrUtil.format(RedisConstants.NUMBERATTRIBUTES_LIST_KEY, year,str);
        List<NumberAttributes> list = (List<NumberAttributes>) RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(list)) {
            list = baseMapper.selectList(wrapper);
            RedisRepository.setExpire(redisKey, list, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return list;
    }
    @Override
    public Result deleteNumberAttributes(Long id,Integer year){
        this.removeById(id);
        String redisKey = StrUtil.format(RedisConstants.NUMBERATTRIBUTES_LIST_KEY, year,"*");
        RedisRepository.delete(redisKey);
        return Result.succeed("删除成功");
    }
    @Override
    public Result saveOrUpdateNumberAttributes(NumberAttributesDto numberAttributesDto, SysUser user) {
        NumberAttributes numberAttributes = new NumberAttributes();
        BeanUtils.copyProperties(numberAttributesDto,numberAttributes);
        if (null != numberAttributes.getId() && 0 != numberAttributes.getId()) {
            numberAttributes.setUpdateTime(new Date());
            numberAttributes.setUpdateBy(null != user ? user.getUsername() : numberAttributes.getUpdateBy());
        } else {
            numberAttributes.setCreateBy(null != user ? user.getUsername() : numberAttributes.getCreateBy());
            numberAttributes.setCreateTime(new Date());
            numberAttributes.setUpdateTime(new Date());
            numberAttributes.setUpdateBy(null != user ? user.getUsername() : numberAttributes.getCreateBy());
        }
        this.saveOrUpdate(numberAttributes);
        String redisKey = StrUtil.format(RedisConstants.SITE_QUIZCHOOSE_LIST_KEY, numberAttributes.getYear(),"*");
        RedisRepository.delete(redisKey);
        return Result.succeed("保存成功");
    }
}
