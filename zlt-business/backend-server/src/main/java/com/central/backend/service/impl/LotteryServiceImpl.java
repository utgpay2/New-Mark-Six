package com.central.backend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.mapper.LotteryMapper;
import com.central.backend.service.ILotteryService;
import com.central.backend.service.ISiteLotteryService;
import com.central.common.constant.RedisConstants;
import com.central.common.model.*;
import com.central.common.model.enums.SortEnum;
import com.central.common.model.enums.StatusEnum;
import com.central.common.redis.template.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;


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
