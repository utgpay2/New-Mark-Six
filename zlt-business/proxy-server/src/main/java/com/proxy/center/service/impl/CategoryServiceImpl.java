package com.proxy.center.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.proxy.center.mapper.CategoryMapper;
import com.proxy.center.service.ICategoryService;
import com.proxy.center.service.ISiteCategoryLotteryService;
import com.central.common.constant.RedisConstants;
import com.central.common.model.Category;
import com.central.common.model.Result;
import com.central.common.model.SiteCategoryLottery;
import com.central.common.model.SysUser;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


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
        String redisKey = StrUtil.format(RedisConstants.SITE_LOTTERY_CATEGORY_KEY);
        List<Category> list = (List<Category>)RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(list)) {
            list = baseMapper.findList(params);
            RedisRepository.setExpire(redisKey, list, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return list;
    }
    @Override
    public Result deleteCategory(Long id){
        LambdaQueryWrapper<SiteCategoryLottery> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(SiteCategoryLottery::getCategoryId,id);
        List<SiteCategoryLottery> list = categoryLotteryService.getBaseMapper().selectList(wrapper);
        if(null!=list && list.size()>0){
            return Result.failed("请删除商户下分类，再删除分类");
        }else {
            this.removeById(id);
            Set<String> redisKeys = RedisRepository.keys(RedisConstants.SITE_LOTTERY_CATEGORY_KEY);
            for(String redisKey:redisKeys) {
                RedisRepository.delete(redisKey);
            }
            return Result.succeed("删除成功");
        }
    }

    @Override
    public Result saveOrUpdateCategory(Category category, SysUser user) {
        if (null != category.getId() && 0 != category.getId()) {
            category.setUpdateTime(new Date());
            category.setUpdateBy(null != user ? user.getUsername() : category.getUpdateBy());
        } else {
            category.setCreateBy(null != user ? user.getUsername() : category.getCreateBy());
            category.setCreateTime(new Date());
            category.setUpdateTime(new Date());
            category.setUpdateBy(null != user ? user.getUsername() : category.getCreateBy());
        }
        this.saveOrUpdate(category);
        Set<String> redisKeys = RedisRepository.keys(RedisConstants.SITE_LOTTERY_CATEGORY_KEY);
        for(String redisKey:redisKeys) {
            RedisRepository.delete(redisKey);
        }
        return Result.succeed("保存成功");
    }
}
