package com.central.backend.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.mapper.CategoryMapper;
import com.central.backend.model.vo.CategoryVO;
import com.central.backend.service.ICategoryService;
import com.central.common.KpnMovieTag;
import com.central.common.model.Category;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 下注分类
 *
 * @author yixiu
 * @date 2023-02-03 16:32:41
 */
@Slf4j
@Service
public class CategoryServiceImpl extends SuperServiceImpl<CategoryMapper, Category> implements ICategoryService {
    @Override
    public List<Category> findList(Map<String, Object> params){
        List<Category> list  =  baseMapper.findList(params);
        return list;
    }
    @Override
    public Result deleteCategory(Long id){
        this.removeById(id);
        return Result.succeed("删除成功");
    }

    @Override
    public Result saveOrUpdateCategory(Category category, SysUser user) {
        if (null != category.getId() && 0 != category.getId()) {
            category.setUpdateBy(null != user ? user.getUsername() : category.getUpdateBy());
        } else {
            category.setCreateBy(null != user ? user.getUsername() : category.getCreateBy());
            category.setUpdateBy(null != user ? user.getUsername() : category.getCreateBy());
        }
        this.saveOrUpdate(category);
        return Result.succeed("保存成功");
    }
}
