package com.central.porn.service;

import com.central.common.model.KpnSiteTopic;
import com.central.common.service.ISuperService;
import com.central.porn.entity.vo.KpnSiteTopicVo;

import java.util.List;


public interface IKpnSiteTopicService extends ISuperService<KpnSiteTopic> {

    /**
     * 获取站点专题id
     * @param sid 站点id
     * @return
     */
    List<Long> getTopicIdsBySiteId(Long sid);

    /**
     * 获取站点专题信息(包含影片信息)
     *
     * @param id
     * @return
     */
    List<KpnSiteTopicVo> getBySiteId(Long id);

}
