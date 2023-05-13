package com.central.marksix.service.impl;

import com.central.common.model.Result;
import com.central.common.model.SiteCategoryLottery;
import com.central.common.model.SysUser;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.entity.vo.CategoryVO;
import com.central.marksix.mapper.SiteCategoryLotteryMapper;
import com.central.marksix.service.ISiteCategoryLotteryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 彩种下注分类
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
    public List<CategoryVO> findList(Map<String, Object> params){
        return baseMapper.findList( params);
    }
}
