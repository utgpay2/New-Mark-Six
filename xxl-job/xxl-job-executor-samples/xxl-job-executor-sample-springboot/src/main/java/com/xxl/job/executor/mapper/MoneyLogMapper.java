package com.xxl.job.executor.mapper;

import com.central.common.model.MoneyLog;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface MoneyLogMapper extends SuperMapper<MoneyLog> {


}
