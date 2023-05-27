package com.xxl.job.executor.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.QuizOrders;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuizOrdersMapper extends SuperMapper<QuizOrders> {
    List<QuizOrders> findList(Page<QuizOrders> page,@Param("p") Map<String, Object> params);
}
