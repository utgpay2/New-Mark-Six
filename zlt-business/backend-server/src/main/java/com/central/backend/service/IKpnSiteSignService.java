package com.central.backend.service;

import com.central.common.model.KpnSiteSign;
import com.central.common.service.ISuperService;

import java.util.List;

public interface IKpnSiteSignService extends ISuperService<KpnSiteSign> {


    List<KpnSiteSign> findSignList(Long siteId);


    Boolean saveOrUpdateSign(List<KpnSiteSign> list);

}
