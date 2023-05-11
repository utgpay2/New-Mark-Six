package com.central.backend.service.impl;

import com.central.backend.mapper.SiteCategoryLotteryMapper;
import com.central.backend.model.vo.CategoryVO;
import com.central.backend.service.ISiteCategoryLotteryService;
import com.central.common.model.*;
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
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public List<CategoryVO> findList(Map<String, Object> params){
        return baseMapper.findList( params);
    }
    @Override
    public Result deleteSiteCategory(Long id){
        this.removeById(id);
        return Result.succeed("删除成功");
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
