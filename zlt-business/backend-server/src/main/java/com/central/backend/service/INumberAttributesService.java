package com.central.backend.service;

import com.central.common.dto.NumberAttributesDto;
import com.central.common.model.NumberAttributes;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;

/**
 * 号码属性表
 *
 * @author zlt
 * @date 2023-05-08 15:05:53
 */
public interface INumberAttributesService extends ISuperService<NumberAttributes> {
    /**
     * 列表
     * @param params
     * @return
     */
    List<NumberAttributes> findList(Map<String, Object> params);
    List<NumberAttributes> findList(NumberAttributesDto numberAttributesDto, Integer year);
    Result deleteNumberAttributes(Long id,Integer year);
    Result saveOrUpdateNumberAttributes(NumberAttributesDto numberAttributesDto, SysUser user);
}

