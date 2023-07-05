package com.central.marksix.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.RedisConstants;
import com.central.common.model.SiteCategoryLottery;
import com.central.common.model.enums.SortEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.entity.vo.CategoryVo;
import com.central.marksix.mapper.SiteCategoryLotteryMapper;
import com.central.marksix.service.ISiteCategoryLotteryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


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
        String redisKey = StrUtil.format(RedisConstants.SITE_CATEGORY_LIST_KEY, MapUtils.getInteger(params,"siteLotteryId"),
                true== ObjectUtil.isEmpty(params.get("sortBy"))? SortEnum.ASC.getCode():MapUtils.getInteger(params,"sortBy"));
        List<CategoryVo> list = (List<CategoryVo>)RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(list)) {
            list = baseMapper.findList( params);
            RedisRepository.setExpire(redisKey, list, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return list;
    }
}
