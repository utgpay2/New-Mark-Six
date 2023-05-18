package com.central.backend.service;

import com.central.backend.co.SiteOrderCo;
import com.central.backend.co.SiteOrderTotalCo;
import com.central.backend.co.SiteOrderUpdateCo;
import com.central.common.model.SiteUserOrder;
import com.central.common.model.SiteUserVipLog;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.service.ISuperService;

import java.math.BigDecimal;
import java.util.List;


/*
 * @Author: Lulu
 * @Date: 2023/2/8
 */
public interface ISiteOrderService extends ISuperService<SiteUserOrder> {

    PageResult<SiteUserOrder> findOrderList(SiteOrderCo params) ;

    BigDecimal findOrderTotal(SiteOrderTotalCo params);

    Result updateStatus(SiteOrderUpdateCo params);

    List<SiteUserOrder> findOrderMobileList(List<Long> userIds) ;

    SiteUserVipLog findKpnSiteOrderInfo(Long id) ;
}
