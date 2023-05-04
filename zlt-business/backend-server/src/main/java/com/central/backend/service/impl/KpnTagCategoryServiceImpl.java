package com.central.backend.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.mapper.KpnTagCategoryMapper;
import com.central.backend.model.vo.KpnTagCategoryVO;
import com.central.backend.service.IKpnMovieTagService;
import com.central.backend.service.IKpnTagCategoryService;
import com.central.common.KpnMovieTag;
import com.central.common.model.KpnTagCategory;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 影片标签分类
 *
 * @author yixiu
 * @date 2023-02-03 16:32:41
 */
@Slf4j
@Service
public class KpnTagCategoryServiceImpl extends SuperServiceImpl<KpnTagCategoryMapper, KpnTagCategory> implements IKpnTagCategoryService {
    @Autowired
    private IKpnMovieTagService iKpnMovieTagService;
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<KpnTagCategoryVO> findList(Map<String, Object> params, SysUser user){
        if(null!=user && null!=user.getSiteId() && user.getSiteId()!=0){//
            params.put("siteId",user.getSiteId());
        }
        Page<KpnTagCategoryVO> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<KpnTagCategoryVO> list  =  baseMapper.findList(page, params);
        return PageResult.<KpnTagCategoryVO>builder().data(list).count(page.getTotal()).build();
    }
    @Override
    public List<KpnTagCategory> findList(){
        List<KpnTagCategory> list  =  baseMapper.findListAll();
        return list;
    }
    @Override
    public Result deleteKpnTagCategory(Long id){
        Map<String, Object> params = new HashMap<>();
        params.put("tagCategoryId", id);//标签类型ID
        List<KpnMovieTag> kpnMovieTagList = iKpnMovieTagService.getKpnMovieTag(params);
        if (null != kpnMovieTagList && kpnMovieTagList.size() > 0) {
            return Result.failed("当前分类有关联影片，不可删除");
        }
        this.removeById(id);
        return Result.succeed("删除成功");
    }

    @Override
    public Result saveOrUpdateKpnTagCategory(KpnTagCategory kpnTagCategory, SysUser user) {
        if (null != kpnTagCategory.getId() && 0 != kpnTagCategory.getId()) {
            kpnTagCategory.setUpdateBy(null != user ? user.getUsername() : kpnTagCategory.getUpdateBy());
        } else {
            kpnTagCategory.setCreateBy(null != user ? user.getUsername() : kpnTagCategory.getCreateBy());
            kpnTagCategory.setUpdateBy(null != user ? user.getUsername() : kpnTagCategory.getCreateBy());
        }
        this.saveOrUpdate(kpnTagCategory);
        return Result.succeed("保存成功");
    }

    @Override
    public List<KpnTagCategory> getOptions() {
        return this.lambdaQuery().select(KpnTagCategory::getId, KpnTagCategory::getName).list();
    }
}
