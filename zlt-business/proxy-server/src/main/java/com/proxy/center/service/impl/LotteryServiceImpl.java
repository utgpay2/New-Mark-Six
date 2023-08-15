package com.proxy.center.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.common.model.enums.SortEnum;
import com.central.common.model.enums.StatusEnum;
import com.central.common.vo.SiteLotteryVo;
import com.proxy.center.mapper.LotteryMapper;
import com.proxy.center.service.ILotteryService;
import com.proxy.center.service.ISiteLotteryService;
import com.central.common.constant.RedisConstants;
import com.central.common.model.Lottery;
import com.central.common.model.Result;
import com.central.common.model.SiteLottery;
import com.central.common.model.SysUser;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 彩种表
 *
 * @author zlt
 * @date 2023-05-09 19:57:30
 */
@Slf4j
@Service
public class LotteryServiceImpl extends SuperServiceImpl<LotteryMapper, Lottery> implements ILotteryService {
    @Autowired
    private ISiteLotteryService siteLotteryService;
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public List<Lottery> findList(Map<String, Object> params){
        String redisKey = StrUtil.format(RedisConstants.SITE_LOTTERY_KEY);
        List<Lottery> list = (List<Lottery>)RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(list)) {
            list = baseMapper.findList(params);
            RedisRepository.setExpire(redisKey, list, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return list;
    }


    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public List<SiteLotteryVo> findListBySiteId(Map<String, Object> params){
        String redisKey = StrUtil.format(RedisConstants.SITE_LOTTERY_LIST_KEY, MapUtils.getInteger(params,"siteId"));
        List<SiteLotteryVo> list = (List<SiteLotteryVo>)RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(list)) {
            list = baseMapper.findListBySiteId( params);
            RedisRepository.setExpire(redisKey, list, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        Comparator<SiteLotteryVo> comparator;
        if(ObjectUtil.isEmpty(params.get("sortBy"))|| SortEnum.DESC.getCode() != MapUtils.getInteger(params,"sortBy")){
            comparator = Comparator.comparing(SiteLotteryVo::getSort);//正序
        }else {
            comparator = Comparator.comparing(SiteLotteryVo::getSort).reversed();//倒序
        }
        return list.stream().filter(siteLotteryVo -> StatusEnum.ONE_TRUE.getStatus()==siteLotteryVo.getIsDisplay())
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public Result deleteLottery(Long id){
        LambdaQueryWrapper<SiteLottery> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(SiteLottery::getLotteryId,id);
        List<SiteLottery> list = siteLotteryService.getBaseMapper().selectList(wrapper);
        if(null!=list && list.size()>0){
            return Result.failed("请删除商户下彩种，再删除彩种");
        }else {
            this.removeById(id);
            String redisKeyStr = StrUtil.format(RedisConstants.SITE_LIST_KEY);
            Set<String> redisKeys = RedisRepository.keys(redisKeyStr);
            for(String redisKey:redisKeys) {
                RedisRepository.delete(redisKey);
            }
            return Result.succeed("删除成功");
        }
    }

    @Override
    public Result saveOrUpdateLottery(Lottery lottery, SysUser user) {
        if (null != lottery.getId() && 0 != lottery.getId()) {
            lottery.setUpdateTime(new Date());
            lottery.setUpdateBy(null != user ? user.getUsername() : lottery.getUpdateBy());
        } else {
            lottery.setCreateBy(null != user ? user.getUsername() : lottery.getCreateBy());
            lottery.setCreateTime(new Date());
            lottery.setUpdateTime(new Date());
            lottery.setUpdateBy(null != user ? user.getUsername() : lottery.getCreateBy());
        }
        this.saveOrUpdate(lottery);
        String redisKeyStr = StrUtil.format(RedisConstants.SITE_LIST_KEY);
        Set<String> redisKeys = RedisRepository.keys(redisKeyStr);
        for(String redisKey:redisKeys) {
            RedisRepository.delete(redisKey);
        }
        return Result.succeed("保存成功");
    }
}
