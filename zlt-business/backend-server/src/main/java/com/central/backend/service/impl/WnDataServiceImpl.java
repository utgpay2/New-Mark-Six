package com.central.backend.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.mapper.WnDataMapper;
import com.central.backend.service.IWnDataService;
import com.central.common.constant.RedisConstants;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.model.WnData;
import com.central.common.model.enums.StatusEnum;
import com.central.common.redis.template.RedisRepository;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;


/**
 * 开奖数据
 *
 * @author zlt
 * @date 2023-05-09 18:39:54
 */
@Slf4j
@Service
public class WnDataServiceImpl extends SuperServiceImpl<WnDataMapper, WnData> implements IWnDataService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<WnData> findList(Map<String, Object> params){
        Page<WnData> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<WnData> list  =  baseMapper.findList(page, params);
        return PageResult.<WnData>builder().data(list).count(page.getTotal()).build();
    }
    @Override
    public Result saveOrUpdateWnData(WnData wnData, SysUser user) {
        if (null != wnData.getId() && 0 != wnData.getId()) {
            wnData.setUpdateTime(new Date());
            wnData.setUpdateBy(null != user ? user.getUsername() : wnData.getUpdateBy());
        } else {
            LambdaQueryWrapper<WnData> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(WnData::getQihao,wnData.getQihao());
            wrapper.eq(WnData::getLotteryId,wnData.getLotteryId());
            List<WnData> list = this.getBaseMapper().selectList(wrapper);
            if(null!=list && list.size()>0){
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
        for(String redisKey:redisKeys) {
            RedisRepository.delete(redisKey);
        }
        return Result.succeed("保存成功");
    }
    @Override
    public Result deleteWnData(Long id,Integer lotteryId) {
        WnData wnData = this.getById(id);
        if(null ==  wnData){
            return Result.failed("id错误");
        }else {
            if(StatusEnum.ONE_TRUE.getStatus() == wnData.getStatus()){
                return Result.failed("结算完成，不能删除");
            }
        }
        this.removeById(id);
        String redisKeyStr = StrUtil.format(RedisConstants.LASTONE_WNDATA_KEY, lotteryId);
        Set<String> redisKeys = RedisRepository.keys(redisKeyStr);
        for(String redisKey:redisKeys) {
            RedisRepository.delete(redisKey);
        }
        return Result.succeed("删除成功");
    }
}
