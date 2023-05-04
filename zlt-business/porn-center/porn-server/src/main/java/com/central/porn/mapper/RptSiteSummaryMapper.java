package com.central.porn.mapper;

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

    /**
     * 增加vip数
     * @param sid 站点id
     * @param siteCode 站点编码
     * @param siteName 站点名称
     */
    void addNewVipNum(@Param("sid") Long sid, @Param("siteCode") String siteCode, @Param("siteName") String siteName, @Param("today") String today);
}
