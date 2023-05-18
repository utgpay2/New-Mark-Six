package com.central.backend.mapper;

import com.central.backend.co.SiteOrderTotalCo;
import com.central.common.model.SiteUserOrder;
import com.central.common.model.SiteUserVipLog;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;


/*
 * @Author: Lulu
 * @Date: 2023/2/8
 */
@Mapper
public interface SiteOrderMapper extends SuperMapper<SiteUserOrder> {

    BigDecimal findOrderTotal( @Param("r") SiteOrderTotalCo params);

    List<SiteUserOrder> findOrderMobileList(@Param("userIds") List<Long> userIds);


    SiteUserVipLog findKpnSiteOrderInfo(@Param("id") Long id);

}
