package com.central.marksix.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.constant.RedisConstants;
import com.central.common.model.NumberAttributes;
import com.central.common.model.PageResult;
import com.central.common.model.WnData;
import com.central.common.model.enums.SortEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.entity.vo.WnDataVo;
import com.central.marksix.mapper.WnDataMapper;
import com.central.marksix.service.INumberAttributesService;
import com.central.marksix.service.IWnDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 开奖数据
 *
 * @author zlt
 * @date 2023-05-09 18:39:54
 */
@Slf4j
@Service
public class WnDataServiceImpl extends SuperServiceImpl<WnDataMapper, WnData> implements IWnDataService {
    @Autowired
    private INumberAttributesService numberAttributesService;
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<WnDataVo> findList(Map<String, Object> params){
        Page<WnData> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        String redisKey = StrUtil.format(RedisConstants.WNDATA_LIST_PAGE_KEY, MapUtils.getInteger(params,"lotteryId"),
                true==ObjectUtil.isEmpty(params.get("sortBy"))? SortEnum.ASC.getCode():MapUtils.getInteger(params,"sortBy"),
                MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<WnDataVo> wnDataVoList  =  (List<WnDataVo>)RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(wnDataVoList)) {
            List<WnData> wnDataList  =  baseMapper.findList(page, params);
            wnDataVoList = new ArrayList<>();
            WnDataVo wnDataVo = new WnDataVo();
            for (WnData wnData :wnDataList) {
                BeanUtils.copyProperties(wnData, wnDataVo);
                wnDataVo = this.setWnDataVo(wnDataVo, wnData.getNumbers(),wnData.getYear());
                wnDataVoList.add(wnDataVo);
            }
            RedisRepository.setExpire(redisKey, wnDataVoList, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return PageResult.<WnDataVo>builder().data(wnDataVoList).count(page.getTotal()).build();
    }
    @Override
    public WnDataVo lastOneWnData(Integer lotteryId){
        String redisKey = StrUtil.format(RedisConstants.LASTONE_WNDATA_KEY, lotteryId);
        WnDataVo wnDataVo = (WnDataVo)RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(wnDataVo)) {
            wnDataVo = new WnDataVo();
            WnData wnData = baseMapper.lastOneWnData(lotteryId);
            BeanUtils.copyProperties(wnData, wnDataVo);
            wnDataVo = this.setWnDataVo(wnDataVo, wnData.getNumbers(),wnData.getYear());
            RedisRepository.setExpire(redisKey, wnDataVo, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return wnDataVo;
    }

    public WnDataVo setWnDataVo(WnDataVo wnDataVo, String numbers,Integer year) {
        List<NumberAttributes> numberAttributesList = numberAttributesService.findList(null,year);
        if(null!=numbers&&!"".equals(numbers)) {
            String[] wnNumbers = numbers.split(",");
            List<NumberAttributes> numberList = new ArrayList<>();
            for (String wnNumber : wnNumbers) {
                for (NumberAttributes numberAttributes : numberAttributesList) {
                    if (wnNumber.equals(numberAttributes.getNumber())) {
                        numberList.add(numberAttributes);
                    }
                }
                wnDataVo.setNumberList(numberList);
            }

        }
        return  wnDataVo;
    }
}
