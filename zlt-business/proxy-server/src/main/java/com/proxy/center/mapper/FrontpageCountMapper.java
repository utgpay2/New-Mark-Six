package com.proxy.center.mapper;

import com.proxy.center.model.vo.FrontpageCountVo;
import com.central.common.model.RptSiteSummary;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    FrontpageCountVo findSummaryData(@Param("p") Map<String, Object> params);

    List<FrontpageCountVo> dataTrend(@Param("p") Map<String, Object> params);
}
