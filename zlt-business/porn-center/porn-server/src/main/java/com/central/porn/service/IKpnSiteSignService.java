package com.central.porn.service;

import com.central.common.model.KpnSiteSign;
import com.central.common.service.ISuperService;

import java.util.List;

public interface IKpnSiteSignService extends ISuperService<KpnSiteSign> {

    /**
     * 获取站点签到配置
     *
     * @param sid 站点id
     * @return
     */
    List<KpnSiteSign> getBySiteId(Long sid);

    /**
     * 查询签到列表
     * @param siteId
     * @return
     */
    List<KpnSiteSign> findSignList(Long siteId);

}
