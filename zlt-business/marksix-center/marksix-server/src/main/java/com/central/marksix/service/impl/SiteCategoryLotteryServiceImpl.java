package com.central.marksix.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.RedisConstants;
import com.central.common.model.SiteCategoryLottery;
import com.central.common.model.enums.SortEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.common.vo.CategoryVo;
import com.central.marksix.mapper.SiteCategoryLotteryMapper;
import com.central.marksix.service.ISiteCategoryLotteryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 彩种下注分类(一类)
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
    public List<CategoryVo> findList(Map<String, Object> params){
        String redisKey = StrUtil.format(RedisConstants.SITE_LOTTERY_CATEGORY_LIST_KEY,
                MapUtils.getInteger(params,"siteId"),
                MapUtils.getInteger(params,"siteLotteryId"));
        List<CategoryVo> list = (List<CategoryVo>)RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(list)) {
            list = baseMapper.findList( params);
            RedisRepository.setExpire(redisKey, list, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        Comparator<CategoryVo> comparator;
        if(ObjectUtil.isEmpty(params.get("sortBy"))||SortEnum.DESC.getCode() != MapUtils.getInteger(params,"sortBy")){
            comparator = Comparator.comparing(CategoryVo::getSort);//正序
        }else {
            comparator = Comparator.comparing(CategoryVo::getSort).reversed();//倒序
        }
        return list.stream().sorted(comparator).collect(Collectors.toList());
    }
}
