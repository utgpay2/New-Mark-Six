package com.central.marksix.service;

import com.central.common.model.NumberAttributes;
import com.central.common.service.ISuperService;
import com.central.marksix.entity.vo.NumberAttributesVo;

import java.util.List;

/**
 * 号码属性表
 *
 * @author zlt
 * @date 2023-05-08 15:05:53
 */
public interface INumberAttributesService extends ISuperService<NumberAttributes> {
    /**
     * 列表
     * @return
     */
    List<NumberAttributes> findList(NumberAttributesVo numberAttributesVo);

}

