package com.proxy.center.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.proxy.center.mapper.KillOddsMapper;
import com.proxy.center.service.IKillOddsService;
import com.central.common.constant.RedisConstants;
import com.central.common.model.KillOdds;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 设置杀率
 *
 * @author yixiu
 * @date 2023-02-03 16:32:41
 */
@Slf4j
@Service
public class KillOddsServiceImpl extends SuperServiceImpl<KillOddsMapper, KillOdds> implements IKillOddsService {
    @Override
    public List<KillOdds> findList(){
        String redisKey = StrUtil.format(RedisConstants.SITE_KILLODDS_KEY);
        List<KillOdds> list = (List<KillOdds>)RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(list)) {
            list = baseMapper.findList();
            RedisRepository.setExpire(redisKey, list, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return list;
    }

    @Override
    public Result saveOrUpdateKillOdds(KillOdds killOdds, SysUser user) {
        if (null != killOdds.getId() && 0 != killOdds.getId()) {
            killOdds.setUpdateTime(new Date());
            killOdds.setUpdateBy(null != user ? user.getUsername() : killOdds.getUpdateBy());
        } else {
            List<KillOdds> list = this.findList();
            if(null!=list && list.size()>0){
                list = list.stream().filter(killOdds1 -> killOdds.getLotteryId()==killOdds1.getLotteryId())
                        .collect(Collectors.toList());
                if(null!=list && list.size()>0) {
                    return Result.failed("杀率已经存在");
                }
            }
            killOdds.setCreateBy(null != user ? user.getUsername() : killOdds.getCreateBy());
            killOdds.setCreateTime(new Date());
            killOdds.setUpdateTime(new Date());
            killOdds.setUpdateBy(null != user ? user.getUsername() : killOdds.getCreateBy());
        }
        this.saveOrUpdate(killOdds);
        RedisRepository.delete(RedisConstants.SITE_KILLODDS_KEY);
        return Result.succeed("保存成功");
    }
}
