package com.central.backend.service;


import com.central.backend.model.vo.KpnTagCategoryVO;
import com.central.common.model.KpnTagCategory;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;

/**
 * 影片标签分类
 *
 * @author yixiu
 * @date 2023-02-03 16:32:41
 */
public interface IKpnTagCategoryService extends ISuperService<KpnTagCategory> {
    /**
     * 列表
     *
     * @param params
     * @return
     */
    PageResult<KpnTagCategoryVO> findList(Map<String, Object> params, SysUser user);

    List<KpnTagCategory> findList();

    Result deleteKpnTagCategory(Long id);

    Result saveOrUpdateKpnTagCategory(KpnTagCategory kpnTagCategory, SysUser user);

    /**
     * 获取下拉框
     * @return
     */
    List<KpnTagCategory> getOptions();

}

