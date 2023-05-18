package com.central.marksix.service;

import com.central.common.model.SiteSignDetail;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;
import com.central.marksix.entity.vo.SiteUserSignHistoryVo;
import com.central.marksix.entity.vo.SiteUserSignResultVo;

import java.util.List;


public interface ISiteSignDetailService extends ISuperService<SiteSignDetail> {

    /**
     * 签到
     *
     * @param sysUser 签到会员
     * @param date    日期
     * @return
     */
    SiteUserSignResultVo sign(SysUser sysUser, String date);

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
    List<SiteUserSignHistoryVo> getSignHistory(SysUser sysUser, String month);

    /**
     * 获取累计签到天数
     * @param userId 会员id
     * @return
     */
    Integer getUserSignDays(Long userId);
}
