package com.proxy.center.service;

import com.proxy.center.co.SiteAdvertiseCo;
import com.proxy.center.co.SiteAdvertiseUpdateCo;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SiteAdvertise;
import com.central.common.service.ISuperService;


/*
 * @Author: Lulu
 * @Date: 2023/2/3
 */
public interface ISiteAdvertiseService extends ISuperService<SiteAdvertise> {

    PageResult<SiteAdvertise> findAdvertiseList(SiteAdvertiseCo params);

    Result saveOrUpdateAdvertise(SiteAdvertise advertise);

    Result updateEnabledAdvertise(SiteAdvertiseUpdateCo params);


    Boolean deleteAdvertiseId(Long id);

}
