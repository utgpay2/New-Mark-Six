package com.central.backend.service.impl;

import com.central.backend.mapper.SiteLotteryMapper;
import com.central.backend.model.vo.CategoryVO;
import com.central.backend.model.vo.SiteLotteryVO;
import com.central.backend.service.ISiteLotteryService;
import com.central.common.model.Result;
import com.central.common.model.SiteLottery;
import com.central.common.model.SysUser;
import com.central.common.service.impl.SuperServiceImpl;
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
public class SiteLotteryServiceImpl extends SuperServiceImpl<SiteLotteryMapper, SiteLottery> implements ISiteLotteryService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public List<SiteLotteryVO> findList(Map<String, Object> params){
        return baseMapper.findList( params);
    }
    @Override
    public Result deleteSiteLottery(Long id){
        this.removeById(id);
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
        return Result.succeed("保存成功");
    }
}
