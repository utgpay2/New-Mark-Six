package com.central.backend.service.impl;

import cn.hutool.core.util.StrUtil;
import com.central.backend.mapper.KpnFrontpageCountMapper;
import com.central.backend.model.vo.KpnFrontpageCountVO;
import com.central.backend.service.IKpnFrontpageCountService;
import com.central.backend.service.IKpnMoneyLogService;
import com.central.backend.service.IKpnSiteService;
import com.central.backend.service.ISysUserService;
import com.central.common.constant.PornConstants;
import com.central.common.model.KpnSite;
import com.central.common.model.RptSiteSummary;
import com.central.common.model.SysUser;
import com.central.common.model.enums.DateEnum;
import com.central.common.model.enums.KbChangeTypeEnum;
import com.central.common.model.enums.KpnMoneyLogEnum;
import com.central.common.model.enums.UserTypeEnum;
import com.central.common.redis.template.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.backend.model.vo.KpnMoneyLogVO;
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
public class KpnFrontpageCountServiceImpl extends SuperServiceImpl<KpnFrontpageCountMapper, RptSiteSummary> implements IKpnFrontpageCountService {
    @Autowired
    private ISysUserService userService;
    @Autowired
    private IKpnMoneyLogService moneyLogService;

    @Autowired
    private IKpnSiteService iKpnSiteService;
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public KpnFrontpageCountVO findSummaryData(Map<String, Object> params,SysUser user){
        //1：今日 2：昨日 3：本月 4：总计
        Integer status = MapUtils.getInteger(params, "status");
        KpnFrontpageCountVO kpnFrontpageCountVO = null;
//        Long pv = 0L;//访问量
//        Long uv = 0L;//独立访客数
        //实时在线人数
        Long onlineUsers = 0L;
        String onlineUsersKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_ONLINE_COUNT);
        if(null!=user&&null!=user.getSiteId()&&0!=user.getSiteId()) {
            Object obj = RedisRepository.get(StrUtil.format(onlineUsersKey , user.getSiteId()));//实时在线人数
            onlineUsers = null!=obj?(Long) obj:0L;
        }else {//所有站点在线人数
            Map<String, Object> siteparams = new HashMap<>();
            List<KpnSite> kpnSiteList = iKpnSiteService.findKpnSiteList(siteparams);
            for (KpnSite kpnSite:kpnSiteList){
                Object obj = RedisRepository.get(StrUtil.format(onlineUsersKey , kpnSite.getId()));
                Long onlineUser = null!=obj?(Long) obj:0L;
                onlineUsers = onlineUsers + onlineUser;//实时在线人数
            }

        }
        Map<String, Object> moneyparams = new HashMap<>();
        moneyparams.put("orderType", KbChangeTypeEnum.OPEN_VIP.getType());
        moneyparams.put("status",DateEnum.TODAY.getStatus());
        moneyparams.put("transferStatus", KpnMoneyLogEnum.TRANSFER_STATUS_SUCCESS.getStatus());
        KpnMoneyLogVO moneyLogVO = moneyLogService.totalNumber(moneyparams,user);
        Long rechargeNumber = null!=moneyLogVO.getTotalNumber()?moneyLogVO.getTotalNumber():0L;//充值单数
        BigDecimal rechargeAmount = null!=moneyLogVO.getMoney()?moneyLogVO.getMoney():BigDecimal.ZERO;//充值金额
        Map<String, Object> userparams = new HashMap<>();
        userparams.put("startTime",new Date());
        Integer addUsers = userService.findUserNum(userparams);//每日新增会员人数
        if(DateEnum.TODAY.getStatus()==status){//今天
            kpnFrontpageCountVO = new KpnFrontpageCountVO();
//            kpnFrontpageCountVO.setPvCount(pv);
//            kpnFrontpageCountVO.setUvCount(uv);
            kpnFrontpageCountVO.setOnlineUsers(onlineUsers);
            kpnFrontpageCountVO.setRechargeNumber(rechargeNumber);
            kpnFrontpageCountVO.setRechargeAmount(rechargeAmount);
            kpnFrontpageCountVO.setAddUsers(Long.valueOf(addUsers));
            return kpnFrontpageCountVO;
        }else{
            kpnFrontpageCountVO  =  baseMapper.findSummaryData(params);
            if(DateEnum.YESTERDAY.getStatus()==status){//昨天
                return kpnFrontpageCountVO;
            }else {
                //加上今日缓存数据
//                kpnFrontpageCountVO.setPvCount(pv+kpnFrontpageCountVO.getPvCount());
//                kpnFrontpageCountVO.setUvCount(uv+kpnFrontpageCountVO.getUvCount());
                kpnFrontpageCountVO.setOnlineUsers(Long.valueOf(onlineUsers));
                kpnFrontpageCountVO.setRechargeNumber(rechargeNumber+kpnFrontpageCountVO.getRechargeNumber());
                kpnFrontpageCountVO.setRechargeAmount(rechargeAmount.add(kpnFrontpageCountVO.getRechargeAmount()));
                kpnFrontpageCountVO.setAddUsers(addUsers+kpnFrontpageCountVO.getAddUsers());
            }
        }

        return kpnFrontpageCountVO;
    }

    @Override
    public List<KpnFrontpageCountVO> dataTrend(Map<String, Object> params,SysUser user){
        if(null!=user && user.getSiteId()!=null && user.getSiteId()!=0){
            params.put("siteId",user.getSiteId());
        }
        List<KpnFrontpageCountVO> kpnFrontpageCountList =  baseMapper.dataTrend(params);

        return kpnFrontpageCountList;
    }
}
