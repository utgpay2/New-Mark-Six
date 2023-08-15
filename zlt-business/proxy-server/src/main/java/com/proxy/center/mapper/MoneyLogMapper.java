package com.proxy.center.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.proxy.center.co.MoneyLogCo;
import com.proxy.center.model.vo.MoneyLogVo;
import com.central.common.model.MoneyLog;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/*
 * @Author: Lulu
 * @Date: 2023/2/8
 */
@Mapper
public interface MoneyLogMapper extends SuperMapper<MoneyLog> {


    List<MoneyLog> findList(Page<MoneyLog> page, @Param("r") MoneyLogCo params);

    MoneyLogVo totalNumber(@Param("p") Map<String, Object> params);

}
