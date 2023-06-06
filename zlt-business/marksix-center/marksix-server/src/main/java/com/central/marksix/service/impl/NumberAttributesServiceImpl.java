package com.central.marksix.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.common.constant.RedisConstants;
import com.central.common.model.NumberAttributes;
import com.central.common.model.enums.SortEnum;
import com.central.common.model.enums.StatusEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.common.utils.DateUtil;
import com.central.marksix.entity.dto.NumberAttributesDto;
import com.central.marksix.mapper.NumberAttributesMapper;
import com.central.marksix.service.INumberAttributesService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.List;


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
     * @return
     */
    @Override
    public List<NumberAttributes> findList(NumberAttributesDto numberAttributesDto){
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
        wrapper.eq(NumberAttributes::getYear, DateUtil.getYear());
        wrapper.orderByAsc(NumberAttributes::getNumber);
        String redisKey = StrUtil.format(RedisConstants.NUMBERATTRIBUTES_LIST_KEY, DateUtil.getYear(),str);
        List<NumberAttributes> list = (List<NumberAttributes>)RedisRepository.get(redisKey);
        if (ObjectUtil.isNotEmpty(list)) {
            list = baseMapper.selectList(wrapper);
            RedisRepository.setExpire(redisKey, list, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return list;
    }
}
