package com.central.marksix.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.common.constant.CommonConstant;
import com.central.common.constant.MarksixConstants;
import com.central.common.constant.RedisConstants;
import com.central.common.model.Result;
import com.central.common.model.Site;
import com.central.common.model.SysUser;
import com.central.common.model.enums.UserRegTypeEnum;
import com.central.common.model.enums.UserTypeEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.mapper.SysUserMapper;
import com.central.marksix.service.ISiteService;
import com.central.marksix.service.IRptSiteSummaryService;
import com.central.marksix.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * year
 */
@Slf4j
@Service
public class SysUserServiceImpl extends SuperServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private ISiteService siteService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private IRptSiteSummaryService siteSummaryService;



    @Override
    public SysUser getByInviteCode(String inviteCode) {
        return this.lambdaQuery()
                .eq(SysUser::getType, UserTypeEnum.APP.name())
                .eq(SysUser::getPromotionCode, inviteCode)
                .one();
    }


    @Override
    public void addRewardMb(SysUser sysUser, BigDecimal rewardMb) {
        this.lambdaUpdate().eq(SysUser::getId, sysUser.getId())
                .setSql("`m_balance` = `m_balance` + " + rewardMb)
                .update();
    }

    @Override
    public Integer getPromotionMemberCount(String promotionCode) {

        return this.lambdaQuery().eq(SysUser::getInviteCode, promotionCode).count();
    }

    @Override
    @Transactional
    public void register(Long sid, SysUser promoteUser, String nickName, String username, String password) {
        Site siteInfo = siteService.getInfoById(sid);

        SysUser newAppUser = new SysUser();
        newAppUser.setSiteId(sid);
        newAppUser.setSiteCode(siteInfo.getCode());
        newAppUser.setSiteName(siteInfo.getName());
        newAppUser.setUsername(username);
        newAppUser.setNickname(nickName);
        newAppUser.setType(UserTypeEnum.APP.name());
        newAppUser.setPassword(passwordEncoder.encode(password));
        newAppUser.setIsReg(UserRegTypeEnum.SELF_REG.getType());
        newAppUser.setMBalance(BigDecimal.ZERO);

        if (ObjectUtil.isNotEmpty(promoteUser)) {
            newAppUser.setParentId(promoteUser.getId());
            newAppUser.setParentName(promoteUser.getUsername());
            newAppUser.setInviteCode(promoteUser.getPromotionCode());
        }
        boolean succeed = false;
        int tryTimes = 1;
        do {
            String promotionCode = RandomUtil.randomString(MarksixConstants.Str.RANDOM_BASE_STR, MarksixConstants.Numeric.INVITE_CODE_LENGTH);
            try {
                newAppUser.setPromotionCode(promotionCode);
                succeed = save(newAppUser);
            } catch (Exception e) {
                log.error(promotionCode + " : " + e.getMessage(), e);
                if (tryTimes++ >= 3) {
                    throw e;
                }
            }
        } while (!succeed);


        //增加站点统计-新增会员数
        siteSummaryService.addNewUserNum(sid, siteInfo.getCode(), siteInfo.getName());


    }

    @Override
    @Transactional
    public void saveInviteCode(Long sid, Long userId, SysUser promoteUser, String inviteCode) {
        SysUser sysUser = getById(userId);
        sysUser.setInviteCode(inviteCode);
        sysUser.setParentId(promoteUser.getId());
        sysUser.setParentName(promoteUser.getUsername());
        saveOrUpdate(sysUser);


    }
    @Override
    public SysUser getSysUserById(Long memberId){
        String redisKey = StrUtil.format(RedisConstants.SITE_SYSUSER_KEY, memberId);
        SysUser sysUser = (SysUser) RedisRepository.get(redisKey);
        if (ObjectUtil.isNotEmpty(sysUser)) {
            sysUser = this.getById(memberId);
            RedisRepository.setExpire(redisKey, sysUser, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return sysUser;
    }

    /**
     * 添加会员
     * @param user
     * @return
     * @Author: Lulu
     * @Date: 2023/2/7
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result saveOrUpdateUserInfo(SysUser user) {
        boolean insert =false;
        //新增
        if (user.getId() == null) {
            LambdaQueryWrapper<SysUser> wrapper=new LambdaQueryWrapper<>();
            if (StringUtils.isNotBlank(user.getUsername())){
                wrapper.eq(SysUser::getUsername, user.getUsername());
            }
            Integer integer = baseMapper.selectCount(wrapper);
            if (integer>0){
                return Result.failed("会员账号已存在");
            }

            if (StringUtils.isBlank(user.getPassword())) {
                user.setPassword(passwordEncoder.encode(CommonConstant.DEF_USER_PASSWORD));
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            String promotionCode = RandomUtil.randomString(MarksixConstants.Str.RANDOM_BASE_STR, 6);
            user.setPromotionCode(promotionCode);
            user.setNickname(user.getUsername());
            user.setType(UserTypeEnum.APP.name());
            user.setIsReg(UserRegTypeEnum.ADMIN_CREATE.getType());
            user.setEnabled(Boolean.TRUE);
            insert = super.save(user);
            //添加运营报表
            siteSummaryService.addNewUserNum(user.getSiteId(),user.getSiteCode(),user.getSiteName());
        }else {
            SysUser userInfo = baseMapper.selectById(user.getId());
            if (userInfo == null) {
                return Result.failed("会员不存在");
            }
            //userInfo.setMobile(user.getMobile());
            userInfo.setUpdateBy(user.getUpdateBy());
            userInfo.setSex(user.getSex());
            int i = baseMapper.updateById(userInfo);
            insert = i > 0 ? true : false;
        }
        if (insert) {
            return Result.succeed(user, "操作成功");
        }
        return Result.failed("操作失败");
    }
}

























