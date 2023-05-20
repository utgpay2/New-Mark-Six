package com.central.backend.mapper;

import com.central.backend.model.vo.FrontpageCountVO;
import com.central.common.model.RptSiteSummary;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 首页访问量统计
 * 
 * @author yixiu
 * @date 2023-02-09 19:41:45
 */
@Mapper
public interface FrontpageCountMapper extends SuperMapper<RptSiteSummary> {
    /**
     * 分页查询用户列表
     * @param params
     * @return
     */
    FrontpageCountVO findSummaryData(@Param("p") Map<String, Object> params);

    List<FrontpageCountVO> dataTrend(@Param("p") Map<String, Object> params);
}