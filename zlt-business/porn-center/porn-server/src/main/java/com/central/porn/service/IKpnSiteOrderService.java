package com.central.porn.service;

import com.central.common.model.KpnSiteUserOrder;
import com.central.common.service.ISuperService;
import com.central.porn.entity.PornPageResult;
import com.central.porn.entity.vo.KpnSiteUserOrderVo;

public interface IKpnSiteOrderService extends ISuperService<KpnSiteUserOrder> {

    /**
     * 订单是否已经存在
     *
     * @param siteId  站点id
     * @param orderNo 订单号
     * @return
     */
    boolean isOrderNoExists(Long siteId, String orderNo);

    /**
     * 获取会员订单列表
     *
     * @param siteId   站点id
     * @param userId   会员id
     * @param currPage 当前页
     * @param pageSize 每页条数
     * @return
     */
    PornPageResult<KpnSiteUserOrderVo> getUserOrderRecords(Long sid, Long userId, Integer currPage, Integer pageSize) throws Exception;
}
