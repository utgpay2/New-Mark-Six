package com.central.backend.service.pay;

import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.model.pay.KpnSiteBankCard;
import com.central.common.service.ISuperService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * 收款银行卡配置
 *
 * @author yixiu
 * @date 2023-02-03 19:35:22
 */
public interface IKpnSiteBankCardService extends ISuperService<KpnSiteBankCard> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<KpnSiteBankCard> findListPage(Map<String, Object> params, SysUser user);
    List<KpnSiteBankCard> findList(Map<String, Object> params, SysUser user);
    Result saveOrUpdateKpnSiteBankCard(KpnSiteBankCard kpnSiteBankCard, SysUser user);
    Result deleteKpnSiteBankCard(@PathVariable Long id);
}

