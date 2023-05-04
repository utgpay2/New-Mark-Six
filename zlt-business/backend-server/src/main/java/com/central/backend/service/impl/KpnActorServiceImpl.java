package com.central.backend.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.mapper.KpnActorMapper;
import com.central.backend.model.vo.KpnActorVO;
import com.central.backend.service.IAsyncService;
import com.central.backend.service.IKpnActorService;
import com.central.backend.service.IKpnMovieService;
import com.central.common.model.*;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 演员列表
 *
 * @author yixiu
 * @date 2023-02-03 16:31:09
 */
@Slf4j
@Service
public class KpnActorServiceImpl extends SuperServiceImpl<KpnActorMapper, KpnActor> implements IKpnActorService {
    @Autowired
    private IKpnMovieService iKpnMovieService;

    @Autowired
    private IAsyncService asyncService;
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<KpnActorVO> findList(Map<String, Object> params, SysUser user){
        if(null!=user && null!=user.getSiteId() && user.getSiteId()!=0){//
            params.put("siteId",user.getSiteId());
        }
        Page<KpnActorVO> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<KpnActorVO> list  =  baseMapper.findList(page, params);
        return PageResult.<KpnActorVO>builder().data(list).count(page.getTotal()).build();
    }
    @Override
    public Result deleteKpnActor(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("actorId", id);//演员ID
        List<KpnMovie> kpnMovieList = iKpnMovieService.getKpnMovie(params);
        if (null != kpnMovieList && kpnMovieList.size() > 0) {
            return Result.failed("该演员名下有关联影片，禁止删除");
        }
        this.removeById(id);

        // add by year 删除演员信息缓存
        asyncService.delActorCache(id);
        return Result.succeed("删除成功");
    }

    @Override
    public Result saveOrUpdateKpnActor(KpnActor kpnActor, SysUser user) {
        if (null != kpnActor.getId() && 0 != kpnActor.getId()) {
            kpnActor.setUpdateBy(null != user ? user.getUsername() : kpnActor.getUpdateBy());

            // add by year 删除演员信息缓存
            asyncService.delActorCache(kpnActor.getId());
        } else {
            kpnActor.setCreateBy(null != user ? user.getUsername() : kpnActor.getCreateBy());
            kpnActor.setUpdateBy(null != user ? user.getUsername() : kpnActor.getCreateBy());
        }
        this.saveOrUpdate(kpnActor);
        return Result.succeed("保存成功");
    }
}
