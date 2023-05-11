package com.central.marksix.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.QuizChoose;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 竞猜奖项详情
 * 
 * @author zlt
 * @date 2023-05-09 18:42:17
 */
@Mapper
public interface QuizChooseMapper extends SuperMapper<QuizChoose> {
    /**
     * 分页查询用户列表
     * @param params
     * @return
     */
    List<QuizChoose> findList( @Param("p") Map<String, Object> params);
}
