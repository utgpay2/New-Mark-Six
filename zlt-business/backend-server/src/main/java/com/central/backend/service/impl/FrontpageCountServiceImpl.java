package com.central.backend.service.impl;

import cn.hutool.core.util.StrUtil;
import com.central.backend.mapper.FrontpageCountMapper;
import com.central.backend.model.vo.FrontpageCountVo;
import com.central.backend.service.IFrontpageCountService;
import com.central.backend.service.IMoneyLogService;
import com.central.backend.service.ISiteService;
import com.central.backend.service.ISysUserService;
import com.central.common.constant.MarksixConstants;
import com.central.common.model.Site;
import com.central.common.model.RptSiteSummary;
import com.central.common.model.SysUser;
import com.central.common.model.enums.DateEnum;
import com.central.common.model.enums.MoneyLogEnum;
import com.central.common.redis.template.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.backend.model.vo.MoneyLogVo;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;


/**
 * 首页访问量统计
 *
 * @author yixiu
 * @date 2023-02-09 19:41:45
 */
@Slf4j
@Service
public class FrontpageCountServiceImpl extends SuperServiceImpl<FrontpageCountMapper, RptSiteSummary> implements IFrontpageCountService {
    @Autowired
    private ISysUserService userService;
    @Autowired
    private IMoneyLogService moneyLogService;

    @Autowired
    private ISiteService iSiteService;
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public FrontpageCountVo findSummaryData(Map<String, Object> params, SysUser user){
        //1：今日 2：昨日 3：本月 4：总计
        Integer status = MapUtils.getInteger(params, "status");
        FrontpageCountVo frontpageCountVO = null;
//        Long pv = 0L;//访问量
//        Long uv = 0L;//独立访客数
        //实时在线人数
        Long onlineUsers = 0L;
        String onlineUsersKey = StrUtil.format(MarksixConstants.RedisKey.KPN_SITE_ONLINE_COUNT);
        if(null!=user&&null!=user.getSiteId()&&0!=user.getSiteId()) {
            Object obj = RedisRepository.get(StrUtil.format(onlineUsersKey , user.getSiteId()));//实时在线人数
            onlineUsers = null!=obj?(Long) obj:0L;
        }else {//所有站点在线人数
            Map<String, Object> siteparams = new HashMap<>();
            List<Site> siteList = iSiteService.findKpnSiteList(siteparams);
            for (Site site : siteList){
                Object obj = RedisRepository.get(StrUtil.format(onlineUsersKey , site.getId()));
                Long onlineUser = null!=obj?(Long) obj:0L;
                onlineUsers = onlineUsers + onlineUser;//实时在线人数
            }

        }
        Map<String, Object> moneyparams = new HashMap<>();
//        moneyparams.put("orderType", MbChangeTypeEnum.OPEN_VIP.getType());
        moneyparams.put("status",DateEnum.TODAY.getStatus());
        moneyparams.put("transferStatus", MoneyLogEnum.TRANSFER_STATUS_SUCCESS.getStatus());
        MoneyLogVo moneyLogVO = moneyLogService.totalNumber(moneyparams,user);
        Long rechargeNumber = null!=moneyLogVO.getTotalNumber()?moneyLogVO.getTotalNumber():0L;//充值单数
        BigDecimal rechargeAmount = null!=moneyLogVO.getMoney()?moneyLogVO.getMoney():BigDecimal.ZERO;//充值金额
        Map<String, Object> userparams = new HashMap<>();
        userparams.put("startTime",new Date());
        Integer addUsers = userService.findUserNum(userparams);//每日新增会员人数
        if(DateEnum.TODAY.getStatus()==status){//今天
            frontpageCountVO = new FrontpageCountVo();
//            frontpageCountVO.setPvCount(pv);
//            frontpageCountVO.setUvCount(uv);
            frontpageCountVO.setOnlineUsers(onlineUsers);
            frontpageCountVO.setRechargeNumber(rechargeNumber);
            frontpageCountVO.setRechargeAmount(rechargeAmount);
            frontpageCountVO.setAddUsers(Long.valueOf(addUsers));
            return frontpageCountVO;
        }else{
            frontpageCountVO =  baseMapper.findSummaryData(params);
            if(DateEnum.YESTERDAY.getStatus()==status){//昨天
                return frontpageCountVO;
            }else {
                //加上今日缓存数据
//                frontpageCountVO.setPvCount(pv+frontpageCountVO.getPvCount());
//                frontpageCountVO.setUvCount(uv+frontpageCountVO.getUvCount());
                frontpageCountVO.setOnlineUsers(Long.valueOf(onlineUsers));
                frontpageCountVO.setRechargeNumber(rechargeNumber+ frontpageCountVO.getRechargeNumber());
                frontpageCountVO.setRechargeAmount(rechargeAmount.add(frontpageCountVO.getRechargeAmount()));
                frontpageCountVO.setAddUsers(addUsers+ frontpageCountVO.getAddUsers());
            }
        }

        return frontpageCountVO;
    }

    @Override
    public List<FrontpageCountVo> dataTrend(Map<String, Object> params, SysUser user){
        if(null!=user && user.getSiteId()!=null && user.getSiteId()!=0){
            params.put("siteId",user.getSiteId());
        }
        List<FrontpageCountVo> kpnFrontpageCountList =  baseMapper.dataTrend(params);

        return kpnFrontpageCountList;
    }
}
