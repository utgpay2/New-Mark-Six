package com.central.porn.service;

import com.central.common.model.RptSiteSearchTotal;
import com.central.common.service.ISuperService;
import com.central.porn.entity.vo.KpnSiteSearchVo;

import java.util.List;


public interface IRptSiteSearchTotalService extends ISuperService<RptSiteSearchTotal> {

    /**
     * 增加总搜索量
     *
     * @param sid      站点id
     * @param keywords 关键词
     */
    void saveRptSiteSearchTotalNumber(Long sid, String keywords);

    /**
     * 查询站点总搜索排行TOP10
     *
     * @param sid
     * @return
     */
    List<KpnSiteSearchVo> getSiteSearchTotal(Long sid);
}
