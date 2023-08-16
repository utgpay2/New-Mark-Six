package com.proxy.center.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.constant.CommonConstant;
import com.central.common.constant.RedisConstants;
import com.central.common.model.*;
import com.central.common.model.enums.SortEnum;
import com.central.common.model.enums.StatusEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.proxy.center.mapper.NumberAttributesMapper;
import com.proxy.center.mapper.WnDataMapper;
import com.proxy.center.service.INumberAttributesService;
import com.proxy.center.service.IWnDataService;
import com.proxy.center.vo.WnDataVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


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

    @Autowired
    private WnDataMapper wnDataMapper;

    @Autowired
    private NumberAttributesMapper numberAttributesMapper;

    /**
     * 列表
     *
     * @param params
     * @return
     */
    @Override
    public PageResult<WnData> findList(Map<String, Object> params) {
        Page<WnData> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<WnData> list = baseMapper.findList(page, params);
        return PageResult.<WnData>builder().data(list).count(page.getTotal()).build();
    }

    @Override
    public Result saveOrUpdateWnData(WnData wnData, SysUser user) {
        if (null != wnData.getId() && 0 != wnData.getId()) {
            wnData.setUpdateTime(new Date());
            wnData.setUpdateBy(null != user ? user.getUsername() : wnData.getUpdateBy());
        } else {
            LambdaQueryWrapper<WnData> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(WnData::getQihao, wnData.getQihao());
            wrapper.eq(WnData::getLotteryId, wnData.getLotteryId());
            List<WnData> list = this.getBaseMapper().selectList(wrapper);
            if (null != list && list.size() > 0) {
                return Result.failed("该期号开奖号码已经存在");
            }
            wnData.setCreateBy(null != user ? user.getUsername() : wnData.getCreateBy());
            wnData.setStatus(StatusEnum.ZERO_FALSE.getStatus());
            wnData.setCreateTime(new Date());
            wnData.setUpdateTime(new Date());
            wnData.setUpdateBy(null != user ? user.getUsername() : wnData.getCreateBy());
        }
        this.saveOrUpdate(wnData);
        String redisKeyStr = StrUtil.format(RedisConstants.LASTONE_WNDATA_KEY, wnData.getLotteryId());
        Set<String> redisKeys = RedisRepository.keys(redisKeyStr);
        for (String redisKey : redisKeys) {
            RedisRepository.delete(redisKey);
        }
        return Result.succeed("保存成功");
    }

    @Override
    public Result deleteWnData(Long id, Integer lotteryId) {
        WnData wnData = this.getById(id);
        if (null == wnData) {
            return Result.failed("id错误");
        } else {
            if (StatusEnum.ONE_TRUE.getStatus() == wnData.getStatus()) {
                return Result.failed("结算完成，不能删除");
            }
        }
        this.removeById(id);
        String redisKeyStr = StrUtil.format(RedisConstants.LASTONE_WNDATA_KEY, lotteryId);
        Set<String> redisKeys = RedisRepository.keys(redisKeyStr);
        for (String redisKey : redisKeys) {
            RedisRepository.delete(redisKey);
        }
        return Result.succeed("删除成功");
    }

    @Override
    public PageResult<WnDataVo> wnDatalist(Map<String, Object> params) {

        Page<WnData> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        String redisKey = StrUtil.format(RedisConstants.WNDATA_LIST_PAGE_KEY, MapUtils.getInteger(params, "lotteryId"),
                MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        //List<WnDataVo> wnDataVoList  =  (List<WnDataVo>)RedisRepository.get(redisKey);
        List<WnDataVo> wnDataVoList = null;
        if (ObjectUtil.isEmpty(wnDataVoList)) {
            List<WnData> wnDataList = wnDataMapper.findList(page, params);
            wnDataVoList = new ArrayList<>();

            for (WnData wnData : wnDataList) {
                WnDataVo wnDataVo = new WnDataVo();
                BeanUtils.copyProperties(wnData, wnDataVo);
                wnDataVo = this.setWnDataVo(wnDataVo, wnData.getNumbers(), wnData.getYear());
                wnDataVoList.add(wnDataVo);
            }
            //RedisRepository.setExpire(redisKey, wnDataVoList, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        Comparator<WnDataVo> comparator;
        if (ObjectUtil.isEmpty(params.get("sortBy")) || SortEnum.DESC.getCode() != MapUtils.getInteger(params, "sortBy")) {
            comparator = Comparator.comparing(WnDataVo::getQihao);//正序
        } else {
            comparator = Comparator.comparing(WnDataVo::getQihao).reversed();//倒序
        }


        return PageResult.<WnDataVo>builder().data(wnDataVoList.stream()
                .sorted(comparator)
                .collect(Collectors.toList())).count(page.getTotal()).build();

    }


    public WnDataVo setWnDataVo(WnDataVo wnDataVo, String numbers, Integer year) {
        String redisKey = StrUtil.format(RedisConstants.NUMBER_ATTRIBUTES_LOTTERYID_YEAR_KEY, wnDataVo.getLotteryId(), year);
        WnDataVo redisWnDataVo = (WnDataVo) RedisRepository.getHashValues(redisKey, wnDataVo.getQihao() + "");
        if (redisWnDataVo != null) {
            return redisWnDataVo;
        }
        if (null != numbers && !"".equals(numbers)) {
            String[] wnNumbers = numbers.split(",");
            List<NumberAttributes> numberList = new ArrayList<>();
            //总分
            Integer count = 0;
            for (String wnNumber : wnNumbers) {
                count = count+Integer.parseInt(wnNumber);
                QueryWrapper<NumberAttributes> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(NumberAttributes::getYear, year).eq(NumberAttributes::getNumber, wnNumber);
                NumberAttributes numberAttributes = numberAttributesMapper.selectOne(queryWrapper);
                numberList.add(numberAttributes);
            }

            NumberAttributes numberAttributes = numberList.get(numberList.size() - 1);
            Integer intNumber = Integer.parseInt(numberAttributes.getNumber());
            //特大小
            Integer isSmallOrBig;
            //特单双
            Integer isOneOrTwo;
            //特尾大小
            Integer particularTailIsSmallOrBig;
            //特和单双
            Integer particularGentleIsOneOrTwo;
            //特和大小
            Integer particularGentleIsSmallOrBig;

            //计算大小
            if (intNumber == 49) {
                isSmallOrBig = CommonConstant.GENTLE;
            } else if (intNumber <= 24) {
                isSmallOrBig = CommonConstant.SMALL;
            } else {
                isSmallOrBig = CommonConstant.BIG;
            }
            //计算单双
            if (intNumber == 49) {
                isOneOrTwo = CommonConstant.GENTLE;
            } else if (intNumber % 2 == 0) {
                isOneOrTwo = CommonConstant.TWO;
            } else {
                isOneOrTwo = CommonConstant.ONE;
            }

            String[] number = numberAttributes.getNumber().split("");
            Integer particularTail = Integer.parseInt(number[1]);
            //计算特尾大小
            if (intNumber == 49) {
                particularTailIsSmallOrBig = CommonConstant.GENTLE;
            } else if (particularTail <= 4) {
                particularTailIsSmallOrBig = CommonConstant.SMALL;
            } else {
                particularTailIsSmallOrBig = CommonConstant.BIG;
            }

            Integer particularGentle = Integer.parseInt(number[0]) + Integer.parseInt(number[1]);

            //计算特和单双
            if (intNumber == 49) {
                particularGentleIsOneOrTwo = CommonConstant.GENTLE;
            } else if (particularGentle % 2 == 0) {
                particularGentleIsOneOrTwo = CommonConstant.TWO;
            } else {
                particularGentleIsOneOrTwo = CommonConstant.ONE;
            }

            //计算特和大小
            if (intNumber == 49) {
                particularGentleIsSmallOrBig = CommonConstant.GENTLE;
            } else if (particularGentle <= 6) {
                particularGentleIsSmallOrBig = CommonConstant.SMALL;
            } else {
                particularGentleIsSmallOrBig = CommonConstant.BIG;
            }
            wnDataVo.setIsOneOrTwo(isOneOrTwo);
            wnDataVo.setIsSmallOrBig(isSmallOrBig);
            wnDataVo.setParticularTailIsSmallOrBig(particularTailIsSmallOrBig);
            wnDataVo.setParticularGentleIsOneOrTwo(particularGentleIsOneOrTwo);
            wnDataVo.setParticularGentleIsSmallOrBig(particularGentleIsSmallOrBig);
            wnDataVo.setCount(count);
            wnDataVo.setColor(numberAttributes.getColor());
            wnDataVo.setNumberList(numberList);
        }
        RedisRepository.putHashValue(redisKey, wnDataVo.getQihao() + "",wnDataVo);
        return wnDataVo;
    }


}
