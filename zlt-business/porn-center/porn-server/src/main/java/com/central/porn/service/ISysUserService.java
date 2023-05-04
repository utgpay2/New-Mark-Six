package com.central.porn.service;

import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;
import com.central.porn.entity.PornPageResult;
import com.central.porn.entity.vo.KpnSiteMovieBaseVo;

import java.math.BigDecimal;

/**
 * @author zlt
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
public interface ISysUserService extends ISuperService<SysUser> {

    /**
     * 根据邀请码获取邀请人
     *
     * @param inviteCode 邀请码
     * @return
     */
    SysUser getByInviteCode(String inviteCode);

    /**
     * 增加奖励vip天数
     *
     * @param sysUser 登录会员
     * @param vipDays 新增vip天数
     */
    void addRewardVipDays(SysUser sysUser, Integer vipDays);

    /**
     * 增加奖励K币数
     *
     * @param sysUser  登录会员
     * @param rewardKb 奖励K币数
     */
    void addRewardKb(SysUser sysUser, BigDecimal rewardKb);

    /**
     * 获取总推广人数
     *
     * @param promotionCode 推广码
     * @return
     */
    Integer getPromotionMemberCount(String promotionCode);

    /**
     * 注册
     *
     * @param sid         站点id
     * @param promoteUser 推广人
     * @param username    账号
     * @param password    密码
     */
    void register(Long sid, SysUser promoteUser, String nickName, String username, String password);

    /**
     * 填写邀请码
     *
     * @param sid         站点id
     * @param userId      会员id
     * @param promoteUser 推广会员
     * @param inviteCode  邀请码
     */
    void saveInviteCode(Long sid, Long userId, SysUser promoteUser, String inviteCode);

    /**
     * 更新vip过期状态
     *
     * @param userId 会员id
     */
    void updateVipExpire(Long userId);

}
