package com.central.porn.service;

import com.central.common.model.KpnSite;
import com.central.common.service.ISuperService;

import java.util.List;


public interface IKpnSiteService extends ISuperService<KpnSite> {

    /**
     * 查询所有启用站点
     * @return
     */
    List<KpnSite> getList();

    /**
     * 根据referer查询
     * @param referer
     * @return
     */
    KpnSite getInfoByReferer(String referer);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    KpnSite getInfoById(Long id);

}
