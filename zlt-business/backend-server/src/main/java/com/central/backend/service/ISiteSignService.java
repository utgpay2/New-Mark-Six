package com.central.backend.service;

import com.central.common.model.SiteSign;
import com.central.common.service.ISuperService;

import java.util.List;

public interface ISiteSignService extends ISuperService<SiteSign> {


    List<SiteSign> findSignList(Long siteId);


    Boolean saveOrUpdateSign(List<SiteSign> list);

}
