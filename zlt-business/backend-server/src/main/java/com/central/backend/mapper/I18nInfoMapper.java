package com.central.backend.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.model.co.I18nInfoPageMapperCo;
import com.central.common.model.I18nInfo;
import com.central.common.vo.I18nInfoPageVO;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface I18nInfoMapper extends SuperMapper<I18nInfo> {

    List<I18nInfo> findListByZhCn(@Param("fromOf")Integer fromOf, @Param("zhCn") String zhCn);

    List<I18nInfo> findList(Page<I18nInfo> page, @Param("p") Map<String, Object> params);

    List<I18nInfoPageVO> findPage(Page<I18nInfoPageVO> page, @Param("p") I18nInfoPageMapperCo params);
}
