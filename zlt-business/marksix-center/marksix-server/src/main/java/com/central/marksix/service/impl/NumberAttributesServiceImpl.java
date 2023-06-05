package com.central.marksix.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.common.model.NumberAttributes;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.common.utils.DateUtil;
import com.central.marksix.entity.vo.NumberAttributesVo;
import com.central.marksix.mapper.NumberAttributesMapper;
import com.central.marksix.service.INumberAttributesService;
import lombok.extern.slf4j.Slf4j;
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
    public List<NumberAttributes> findList(NumberAttributesVo numberAttributesVo){
        LambdaQueryWrapper<NumberAttributes> wrapper=new LambdaQueryWrapper<>();
        if(null!=numberAttributesVo){
            if(numberAttributesVo.getZodiac()!=null&&!"".equals(numberAttributesVo.getZodiac()))
                wrapper.eq(NumberAttributes::getZodiac, numberAttributesVo.getZodiac());
            if(numberAttributesVo.getPoultryandbeast()!=null&&!"".equals(numberAttributesVo.getPoultryandbeast()))
                wrapper.eq(NumberAttributes::getPoultryandbeast, numberAttributesVo.getPoultryandbeast());
            if(numberAttributesVo.getColor()!=null&&!"".equals(numberAttributesVo.getColor()))
                wrapper.eq(NumberAttributes::getColor, numberAttributesVo.getColor());
            if(numberAttributesVo.getFiveElements()!=null&&!"".equals(numberAttributesVo.getFiveElements()))
                wrapper.eq(NumberAttributes::getFiveElements, numberAttributesVo.getFiveElements());
        }
        wrapper.eq(NumberAttributes::getYear, DateUtil.getYear());
        wrapper.orderByAsc(NumberAttributes::getNumber);
        return baseMapper.selectList(wrapper);
    }
}
