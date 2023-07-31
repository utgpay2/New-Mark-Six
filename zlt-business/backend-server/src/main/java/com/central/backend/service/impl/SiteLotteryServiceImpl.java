package com.central.backend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.backend.mapper.SiteLotteryMapper;
import com.central.common.vo.SiteLotteryVo;
import com.central.backend.service.ISiteLotteryService;
import com.central.common.constant.RedisConstants;
import com.central.common.model.Result;
import com.central.common.model.SiteLottery;
import com.central.common.model.SysUser;
import com.central.common.model.enums.SortEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 彩种下注分类
 *
 * @author zlt
 * @date 2023-05-11 18:50:09
 */
@Slf4j
@Service
public class SiteLotteryServiceImpl extends SuperServiceImpl<SiteLotteryMapper, SiteLottery> implements ISiteLotteryService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public List<SiteLotteryVo> findList(Map<String, Object> params){
        String redisKey = StrUtil.format(RedisConstants.SITE_LOTTERY_LIST_KEY, MapUtils.getInteger(params,"siteId"));
        List<SiteLotteryVo> list = (List<SiteLotteryVo>) RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(list)) {
            list = baseMapper.findList( params);
            RedisRepository.setExpire(redisKey, list, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        Comparator<SiteLotteryVo> comparator;
        if(ObjectUtil.isEmpty(params.get("sortBy"))||SortEnum.DESC.getCode() != MapUtils.getInteger(params,"sortBy")){
            comparator = Comparator.comparing(SiteLotteryVo::getSort);//正序
        }else {
            comparator = Comparator.comparing(SiteLotteryVo::getSort).reversed();//倒序
        }
        return list.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
    @Override
    public Result deleteSiteLottery(Long id,Integer siteId){
        this.removeById(id);
        String redisKeyStr = StrUtil.format(RedisConstants.SITE_LOTTERY_LIST_KEY, siteId);
        Set<String> redisKeys = RedisRepository.keys(redisKeyStr);
        for(String redisKey:redisKeys) {
            RedisRepository.delete(redisKey);
        }
        return Result.succeed("删除成功");
    }

    @Override
    public Result saveOrUpdateSiteLottery(SiteLottery lottery, SysUser user) {
        if (null != lottery.getId() && 0 != lottery.getId()) {
            lottery.setUpdateBy(null != user ? user.getUsername() : lottery.getUpdateBy());
        } else {
            lottery.setCreateBy(null != user ? user.getUsername() : lottery.getCreateBy());
            lottery.setUpdateBy(null != user ? user.getUsername() : lottery.getCreateBy());
        }
        this.saveOrUpdate(lottery);
        String redisKeyStr = StrUtil.format(RedisConstants.SITE_LOTTERY_LIST_KEY, lottery.getSiteId());
        Set<String> redisKeys = RedisRepository.keys(redisKeyStr);
        for(String redisKey:redisKeys) {
            RedisRepository.delete(redisKey);
        }
        return Result.succeed("保存成功");
    }
}
