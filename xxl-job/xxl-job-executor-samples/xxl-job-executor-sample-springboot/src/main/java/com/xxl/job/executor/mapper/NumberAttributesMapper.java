package com.xxl.job.executor.mapper;


import com.central.common.model.NumberAttributes;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 号码属性表
 * 
 * @author zlt
 * @date 2023-05-08 15:05:53
 */
@Mapper
public interface NumberAttributesMapper extends SuperMapper<NumberAttributes> {
}
