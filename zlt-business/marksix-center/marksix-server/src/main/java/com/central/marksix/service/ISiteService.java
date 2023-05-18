package com.central.marksix.service;

import com.central.common.model.Site;
import com.central.common.service.ISuperService;

import java.util.List;


public interface ISiteService extends ISuperService<Site> {

    /**
     * 查询所有启用站点
     * @return
     */
    List<Site> getList();

    /**
     * 根据referer查询
     * @param referer
     * @return
     */
    Site getInfoByReferer(String referer);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Site getInfoById(Long id);

}
