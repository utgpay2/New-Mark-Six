package com.central.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.mapper.CategoryMapper;
import com.central.backend.service.ICategoryService;
import com.central.backend.service.ISiteCategoryLotteryService;
import com.central.common.model.Category;
import com.central.common.model.Result;
import com.central.common.model.SiteCategoryLottery;
import com.central.common.model.SysUser;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private ISiteCategoryLotteryService categoryLotteryService;
    @Override
    public List<Category> findList(Map<String, Object> params){
        List<Category> list  =  baseMapper.findList(params);
        return list;
    }
    @Override
    public Result deleteCategory(Long id){
        LambdaQueryWrapper<SiteCategoryLottery> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(SiteCategoryLottery::getCategoryId,id);
        List<SiteCategoryLottery> list = categoryLotteryService.getBaseMapper().selectList(wrapper);
        if(null!=list && list.size()>0){
            return Result.failed("请删除站点下分类，再删除分类");
        }else {
            this.removeById(id);
            return Result.succeed("删除成功");
        }
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
