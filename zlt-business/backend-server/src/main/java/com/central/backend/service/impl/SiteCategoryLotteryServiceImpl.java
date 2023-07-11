package com.central.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.mapper.SiteCategoryLotteryMapper;
import com.central.backend.model.vo.CategoryVo;
import com.central.backend.service.IQuizService;
import com.central.backend.service.ISiteCategoryLotteryService;
import com.central.common.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;


/**
 * 彩种下注分类
 *
 * @author zlt
 * @date 2023-05-11 18:50:09
 */
@Slf4j
@Service
public class SiteCategoryLotteryServiceImpl extends SuperServiceImpl<SiteCategoryLotteryMapper, SiteCategoryLottery> implements ISiteCategoryLotteryService {
    @Autowired
    private IQuizService quizService;
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public List<CategoryVo> findList(Map<String, Object> params){
        return baseMapper.findList( params);
    }
    @Override
    public Result deleteSiteCategory(Long id){
        LambdaQueryWrapper<Quiz> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Quiz::getSiteCategoryId,id);
        List<Quiz> list = quizService.getBaseMapper().selectList(wrapper);
        if(null!=list && list.size()>0){
            return Result.failed("请删除站点分类下规则主表，再删除站点分类");
        }else {
            this.removeById(id);
            return Result.succeed("删除成功");
        }
    }

    @Override
    public Result saveOrUpdateSiteCategory(SiteCategoryLottery category, SysUser user) {
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
