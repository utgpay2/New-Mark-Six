package com.central.backend.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.co.SiteCo;
import com.central.backend.co.SiteUpdateCo;
import com.central.backend.mapper.SiteMapper;
import com.central.backend.service.IAsyncService;
import com.central.backend.service.ISiteService;
import com.central.backend.vo.SiteListVo;
import com.central.backend.vo.SiteVo;
import com.central.common.constant.MarksixConstants;
import com.central.common.model.Site;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Slf4j
@Service
public class SiteServiceImpl extends SuperServiceImpl<SiteMapper, Site> implements ISiteService {



    @Autowired
    private IAsyncService asyncService;

    @Override
    public List<Site> getList() {
        String redisKey = MarksixConstants.RedisKey.KPN_SITE_LIST_KEY;
        List<Site> sites = (ArrayList) RedisRepository.get(redisKey);
        if (CollectionUtil.isEmpty(sites)) {
            sites = this.lambdaQuery().eq(Site::getStatus, true).list();
            if (CollectionUtil.isNotEmpty(sites)) {
                RedisRepository.setExpire(redisKey, sites, MarksixConstants.RedisKey.EXPIRE_TIME_30_DAYS);
            }
        }
        return sites;
    }


    @Override
    public PageResult<Site> findSiteList(SiteCo params) {
        Page<Site> page = new Page<>(params.getPage(), params.getLimit());
        List<Site> list = baseMapper.findList(page, params);
        return PageResult.<Site>builder().data(list).count(page.getTotal()).build();
    }

    @Override
    public List<Site> findKpnSiteList(Map<String, Object> params) {
        return baseMapper.findKpnSiteList(params);
    }

    @Override
    public SiteVo findSiteTotal() {
        return baseMapper.findSiteTotal();
    }

    @Override
    public Result saveOrUpdateSite(Site site) {
        boolean insert = false;
        //新增
        if (site.getId() == null) {
            insert = super.save(site);
        } else {
            Site info = baseMapper.selectById(site.getId());
            if (info == null) {
                return Result.failed("数据不存在");
            }
            //修改
            insert = super.updateById(site);
        }
        if (insert) {
            // add by year 删除站点信息缓存
            asyncService.deleteSiteInfoCache(site.getId());
            return Result.succeed(site, "操作成功");
        }
        return Result.failed("操作失败");
    }

    @Override
    public Result updateEnabledSite(SiteUpdateCo params) {
        Long id = params.getId();
        Site siteInfo = baseMapper.selectById(id);
        if (siteInfo == null) {
            return Result.failed("此站点不存在");
        }
        if (params.getStatus() != null) {
            siteInfo.setStatus(params.getStatus());
        }
        if (params.getRepairStatus() != null) {
            siteInfo.setRepairStatus(params.getRepairStatus());
        }
        siteInfo.setUpdateBy(params.getUpdateBy());
        int i = baseMapper.updateById(siteInfo);

        // add by year 删除站点信息缓存
        if (i > 0) {
            asyncService.deleteSiteInfoCache(siteInfo.getId());
        }
        return i > 0 ? Result.succeed(siteInfo, "更新成功") : Result.failed("更新失败");
    }



    @Override
    public  String getStringRandom(int length) {
        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    @Override
    public List<SiteListVo> findSiteBoxList(Integer roleId ) {
        return baseMapper.findSiteBoxList(roleId);
    }

}