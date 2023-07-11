package com.central.backend.service;


import com.central.common.model.Category;
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
public interface ICategoryService extends ISuperService<Category> {

    List<Category> findList(Map<String, Object> params);

    Result deleteCategory(Long id);

    Result saveOrUpdateCategory(Category category, SysUser user);


}

