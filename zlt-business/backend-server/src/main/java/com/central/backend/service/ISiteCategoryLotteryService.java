package com.central.backend.service;

import com.central.backend.model.vo.CategoryVo;
import com.central.common.model.*;
import com.central.common.service.ISuperService;

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
    Result deleteSiteCategory(Long id);

    Result saveOrUpdateSiteCategory(SiteCategoryLottery category, SysUser user);
}

