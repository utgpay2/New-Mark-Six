package com.central.backend.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.co.KpnSiteCo;
import com.central.backend.co.KpnSiteUpdateCo;
import com.central.backend.mapper.KpnSiteMapper;
import com.central.backend.service.IAsyncService;
import com.central.backend.service.IKpnSiteChannelService;
import com.central.backend.service.IKpnSiteService;
import com.central.backend.vo.KpnSiteListVo;
import com.central.backend.vo.KpnSiteVo;
import com.central.common.constant.PornConstants;
import com.central.common.model.KpnSite;
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
public class KpnSiteServiceImpl extends SuperServiceImpl<KpnSiteMapper, KpnSite> implements IKpnSiteService {


    @Autowired
    private IKpnSiteChannelService siteChannelService;

    @Autowired
    private IAsyncService asyncService;

    @Override
    public List<KpnSite> getList() {
        String redisKey = PornConstants.RedisKey.KPN_SITE_LIST_KEY;
        List<KpnSite> kpnSites = (ArrayList) RedisRepository.get(redisKey);
        if (CollectionUtil.isEmpty(kpnSites)) {
            kpnSites = this.lambdaQuery().eq(KpnSite::getStatus, true).list();
            if (CollectionUtil.isNotEmpty(kpnSites)) {
                RedisRepository.setExpire(redisKey, kpnSites, PornConstants.RedisKey.EXPIRE_TIME_30_DAYS);
            }
        }
        return kpnSites;
    }


    @Override
    public PageResult<KpnSite> findSiteList(KpnSiteCo params) {
        Page<KpnSite> page = new Page<>(params.getPage(), params.getLimit());
        List<KpnSite> list = baseMapper.findList(page, params);
        return PageResult.<KpnSite>builder().data(list).count(page.getTotal()).build();
    }

    @Override
    public List<KpnSite> findKpnSiteList(Map<String, Object> params) {
        return baseMapper.findKpnSiteList(params);
    }

    @Override
    public KpnSiteVo findSiteTotal() {
        return baseMapper.findSiteTotal();
    }

    @Override
    public Result saveOrUpdateSite(KpnSite kpnSite) {
        boolean insert = false;
        //新增
        if (kpnSite.getId() == null) {
            insert = super.save(kpnSite);
            //生成站点对应的频道栏目配置
            siteChannelService.saveSiteChannelList(kpnSite.getId(), kpnSite.getCode(), kpnSite.getName(), kpnSite.getCreateBy());
        } else {
            KpnSite info = baseMapper.selectById(kpnSite.getId());
            if (info == null) {
                return Result.failed("数据不存在");
            }
            //修改
            insert = super.updateById(kpnSite);
        }
        if (insert) {
            // add by year 删除站点信息缓存
            asyncService.deleteSiteInfoCache(kpnSite.getId());
            return Result.succeed(kpnSite, "操作成功");
        }
        return Result.failed("操作失败");
    }

    @Override
    public Result updateEnabledSite(KpnSiteUpdateCo params) {
        Long id = params.getId();
        KpnSite siteInfo = baseMapper.selectById(id);
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
    public List<KpnSiteListVo> findSiteBoxList(Integer roleId ) {
        return baseMapper.findSiteBoxList(roleId);
    }

}