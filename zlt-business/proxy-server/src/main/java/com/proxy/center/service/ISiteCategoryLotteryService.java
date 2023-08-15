package com.proxy.center.service;

import com.central.common.model.Result;
import com.central.common.model.SiteCategoryLottery;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;
import com.central.common.vo.CategoryVo;

import java.util.List;
import java.util.Map;

/**
 * 彩种下注分类
 *
 * @author zlt
 * @date 2023-05-11 18:50:09
 */
public interface ISiteCategoryLotteryService extends ISuperService<SiteCategoryLottery> {
    /**
     * 列表
     * @param params
     * @return
     */
    List<CategoryVo> findList(Map<String, Object> params);
    Result deleteSiteCategory(Long id, Long siteId, Long siteLotteryId);
    Result saveOrUpdateSiteCategory(SiteCategoryLottery category, SysUser user);
}

