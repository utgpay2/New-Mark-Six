package com.central.marksix.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.QuizOrders;
import com.central.db.mapper.SuperMapper;
import com.central.marksix.entity.vo.StatiQuizOrdersVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuizOrdersMapper extends SuperMapper<QuizOrders> {
    List<QuizOrders> findList(Page<QuizOrders> page,@Param("p") Map<String, Object> params);

    StatiQuizOrdersVo statiOrders(@Param("p") Map<String, Object> params);

    List<QuizOrders> findPage(Page<QuizOrders> page,@Param("p") Map<String, Object> params);
}
