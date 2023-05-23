package com.central.marksix.mapper;

import com.central.common.model.RptSiteSummary;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RptSiteSummaryMapper extends SuperMapper<RptSiteSummary> {

    /**
     * 增加会员数
     *
     * @param sid   站点id
     * @param today 当日日期
     */
    void addNewUserNum(@Param("sid") Long sid, @Param("siteCode") String siteCode, @Param("siteName") String siteName, @Param("today") String today);

}
