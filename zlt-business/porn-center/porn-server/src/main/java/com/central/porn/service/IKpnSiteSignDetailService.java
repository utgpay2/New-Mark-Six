package com.central.porn.service;

import com.central.common.model.KpnSiteSignDetail;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;
import com.central.porn.entity.vo.KpnSiteUserSignHistoryVo;
import com.central.porn.entity.vo.KpnSiteUserSignResultVo;

import java.util.List;


public interface IKpnSiteSignDetailService extends ISuperService<KpnSiteSignDetail> {

    /**
     * 签到
     *
     * @param sysUser 签到会员
     * @param date    日期
     * @return
     */
    KpnSiteUserSignResultVo sign(SysUser sysUser, String date);

    /**
     * 判断是否已经签到过
     *
     * @param userId 会员id
     * @param date   签到日期
     * @return
     */
    boolean checkHasSigned(Long userId, String date);

    /**
     * 获取月份签到数据
     * @param sysUser 会员信息
     * @param month 当前年月
     * @return
     */
    List<KpnSiteUserSignHistoryVo> getSignHistory(SysUser sysUser, String month);

    /**
     * 获取累计签到天数
     * @param userId 会员id
     * @return
     */
    Integer getUserSignDays(Long userId);
}
