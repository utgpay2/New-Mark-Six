package com.central.porn.service;

import com.central.common.model.RptSiteSearchDate;
import com.central.common.service.ISuperService;
import com.central.porn.entity.vo.KpnSiteSearchVo;

import java.util.List;


public interface IRptSiteSearchDateService extends ISuperService<RptSiteSearchDate> {

    /**
     * 增加日搜索量
     *
     * @param sid      站点id
     * @param keywords 关键词
     */
    void saveRptSiteSearchDateNumber(Long sid, String keywords);

    /**
     * 站点周搜索排行榜-TOP10
     *
     * @param sid 站点id
     * @return
     */
    List<KpnSiteSearchVo> getSiteSearchWeek(Long sid);
}
