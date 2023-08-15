package com.proxy.center.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.proxy.center.mapper.SiteCategoryLotteryMapper;
import com.proxy.center.service.IQuizService;
import com.proxy.center.service.ISiteCategoryLotteryService;
import com.central.common.constant.RedisConstants;
import com.central.common.model.Quiz;
import com.central.common.model.Result;
import com.central.common.model.SiteCategoryLottery;
import com.central.common.model.SysUser;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.common.vo.CategoryVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;


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
        String redisKey = StrUtil.format(RedisConstants.SITE_LOTTERY_CATEGORY_LIST_KEY, MapUtils.getInteger(params,"siteId"),MapUtils.getInteger(params,"siteLotteryId"));
        List<CategoryVo> list  =  (List<CategoryVo>) RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(list)) {
            list = baseMapper.findList( params);
            RedisRepository.setExpire(redisKey, list, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return list;
    }
    @Override
    public Result deleteSiteCategory(Long id,Long siteId,Long siteLotteryId){
        LambdaQueryWrapper<Quiz> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Quiz::getSiteCategoryId,id);
        List<Quiz> list = quizService.getBaseMapper().selectList(wrapper);
        if(null!=list && list.size()>0){
            return Result.failed("请删除商户彩种分类(二类)，再删除商户分类(一类)");
        }else {
            this.removeById(id);
            String redisKeyStr = StrUtil.format(RedisConstants.SITE_LOTTERY_CATEGORY_LIST_KEY, siteId,siteLotteryId);
            Set<String> redisKeys = RedisRepository.keys(redisKeyStr);
            for(String redisKey:redisKeys) {
                RedisRepository.delete(redisKey);
            }
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
        String redisKeyStr = StrUtil.format(RedisConstants.SITE_LOTTERY_CATEGORY_LIST_KEY, category.getSiteId(),category.getSiteLotteryId());
        Set<String> redisKeys = RedisRepository.keys(redisKeyStr);
        for(String redisKey:redisKeys) {
            RedisRepository.delete(redisKey);
        }
        return Result.succeed("保存成功");
    }
}
