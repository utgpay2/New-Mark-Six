package com.central.backend.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.co.MoneyLogCo;
import com.central.backend.model.vo.MoneyLogVO;
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

    MoneyLogVO totalNumber(@Param("p") Map<String, Object> params);

}
