package com.central.marksix.service;

import com.central.common.model.SiteSign;
import com.central.common.service.ISuperService;

import java.util.List;

public interface ISiteSignService extends ISuperService<SiteSign> {

    /**
     * 获取站点签到配置
     *
     * @param sid 站点id
     * @return
     */
    List<SiteSign> getBySiteId(Long sid);

    /**
     * 查询签到列表
     * @param siteId
     * @return
     */
    List<SiteSign> findSignList(Long siteId);

}
