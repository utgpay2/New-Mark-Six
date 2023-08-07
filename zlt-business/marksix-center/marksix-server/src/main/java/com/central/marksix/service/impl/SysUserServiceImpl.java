package com.central.marksix.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.common.constant.CommonConstant;
import com.central.common.constant.MarksixConstants;
import com.central.common.constant.RedisConstants;
import com.central.common.model.*;
import com.central.common.model.enums.*;
import com.central.common.redis.lock.RedissLockUtil;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.common.utils.SnowflakeIdWorker;
import com.central.marksix.dto.TransferAccountsDto;
import com.central.marksix.mapper.SysRoleMenuMapper;
import com.central.marksix.mapper.SysUserMapper;
import com.central.marksix.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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


    @Resource
    private ISysRoleUserService roleUserService;

    @Resource
    private SysRoleMenuMapper roleMenuMapper;

    @Autowired
    private IMoneyLogService moneyLogService;

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
        String redisKey = StrUtil.format(RedisConstants.SITE_SYSUSER_KEY, sysUser.getId());
        RedisRepository.delete(redisKey);
    }

    @Override
    public void addRewardTestMb(SysUser sysUser, BigDecimal rewardTestMb) {
        this.lambdaUpdate().eq(SysUser::getId, sysUser.getId())
                .setSql("`m_test_balance` = `m_test_balance` + " + rewardTestMb)
                .update();
        String redisKey = StrUtil.format(RedisConstants.SITE_SYSUSER_KEY, sysUser.getId());
        RedisRepository.delete(redisKey);
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


        //增加商户统计-新增会员数
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
    public SysUser getSysUserById(Long memberId) {
        String redisKey = StrUtil.format(RedisConstants.SITE_SYSUSER_KEY, memberId);
        SysUser sysUser = (SysUser) RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(sysUser)) {
            sysUser = this.getById(memberId);
            RedisRepository.setExpire(redisKey, sysUser, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        return sysUser;
    }

    /**
     * 添加会员
     *
     * @param user
     * @return
     * @Author: Lulu
     * @Date: 2023/2/7
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result saveOrUpdateUserInfo(SysUser user) {
        boolean insert = false;
        //新增
        if (user.getId() == null) {
            LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
            if (StringUtils.isNotBlank(user.getUsername())) {
                wrapper.eq(SysUser::getUsername, user.getUsername());
            }
            Integer integer = baseMapper.selectCount(wrapper);
            if (integer > 0) {
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
            siteSummaryService.addNewUserNum(user.getSiteId(), user.getSiteCode(), user.getSiteName());
        } else {
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

    @Override
    public LoginAppUser getLoginAppUser(SysUser sysUser) {
        if (sysUser != null) {
            LoginAppUser loginAppUser = new LoginAppUser();
            BeanUtils.copyProperties(sysUser, loginAppUser);

            List<SysRole> sysRoles = roleUserService.findRolesByUserId(sysUser.getId());
            // 设置角色
            loginAppUser.setRoles(sysRoles);

            if (!CollectionUtils.isEmpty(sysRoles)) {
                Set<Long> roleIds = sysRoles.parallelStream().map(SuperEntity::getId).collect(Collectors.toSet());
                List<SysMenu> menus = roleMenuMapper.findMenusByRoleIds(roleIds, CommonConstant.PERMISSION);
                if (!CollectionUtils.isEmpty(menus)) {
                    Set<String> permissions = menus.parallelStream().map(p -> p.getPath())
                            .collect(Collectors.toSet());
                    // 设置权限集合
                    loginAppUser.setPermissions(permissions);
                }
            }
            return loginAppUser;
        }
        return null;
    }

    @Transactional
    @Override
    public Result transfer(TransferAccountsDto transferAccountsDto) {
        SysUser user = getById(transferAccountsDto.getUserId());
        SysUser parentUser = getById(user.getParentId());

        String lockKey = StrUtil.format(MarksixConstants.Lock.USER_TRANSFER_LOCK, user.getId());
        try {
            boolean lockedSuccess = RedissLockUtil.tryLock(lockKey, MarksixConstants.Lock.WAIT_TIME, MarksixConstants.Lock.LEASE_TIME);
            if (!lockedSuccess) {
                return Result.failed("加锁失败");
            }
            BigDecimal amount = transferAccountsDto.getAmount();//转账金额
            BigDecimal currentBalance = BigDecimal.ZERO;
            BigDecimal parentCurrentBalance = BigDecimal.ZERO;
            if(StatusEnum.ZERO_FALSE.getStatus()==user.getIsTestAccount()){//正式账号
                currentBalance = user.getMBalance();//用户当前余额
                parentCurrentBalance = parentUser.getMBalance();//上级用户测试余额
            }else {
                currentBalance = user.getMTestBalance();//测试余额
                parentCurrentBalance = parentUser.getMBalance();//上级用户测试余额
            }
            BigDecimal afterMoney;
            BigDecimal parentAfterMoney;
            if(transferAccountsDto.getType()==0){//0给会员下分
                if(currentBalance.compareTo(amount)==-1){
                    return Result.failed(CodeEnum.MB_NOT_ENOUGH.getCode(), "会员余额不足", null);
                }
                afterMoney=currentBalance.subtract(amount);
                parentAfterMoney=parentCurrentBalance.add(amount);
            }else {//1给会员上分
                if (parentCurrentBalance.compareTo(amount) == -1) {
                    return Result.failed(CodeEnum.MB_NOT_ENOUGH.getCode(), "代理余额不足", null);
                }
                parentAfterMoney = parentCurrentBalance.subtract(amount);
                afterMoney = currentBalance.add(amount);
            }
            List<MoneyLog> moneyLogList = new ArrayList<>();
            Date date=new Date();

            //用户金额日志
            String orderSn = SnowflakeIdWorker.createOrderSn();//订单号
            MoneyLog moneyLog = new MoneyLog();
            moneyLog.setUserId(user.getId());
            moneyLog.setUserName(user.getUsername());
            moneyLog.setOrderNo(orderSn);
            if(transferAccountsDto.getType()==0){
                moneyLog.setOrderType(MbChangeTypeEnum.WITHDRAWAL.getType());//账变类型
                moneyLog.setOrderTypeName(MbChangeTypeEnum.WITHDRAWAL.getName());//账变类型名称
                moneyLog.setMoney(amount.negate());//账变金额
            }else {
                moneyLog.setOrderType(MbChangeTypeEnum.RECHARGE.getType());//账变类型
                moneyLog.setOrderTypeName(MbChangeTypeEnum.RECHARGE.getName());//账变类型名称
                moneyLog.setMoney(amount);//账变金额
            }
            moneyLog.setBeforeMoney(currentBalance);//账变前金额
            moneyLog.setAfterMoney(afterMoney);//账变后金额
            moneyLog.setCreateTime(date);
            moneyLog.setCreateBy(user.getUsername());
            moneyLog.setUpdateTime(date);
            moneyLog.setUpdateBy(user.getUsername());
            moneyLogList.add(moneyLog);

            //代理或开户商金额日志
            MoneyLog moneyLog1 = new MoneyLog();
            moneyLog1.setUserId(parentUser.getId());
            moneyLog1.setUserName(parentUser.getUsername());
            moneyLog1.setOrderNo(orderSn);
            if(transferAccountsDto.getType()==0){
                moneyLog1.setOrderType(MbChangeTypeEnum.USER_WITHDRAWAL.getType());//账变类型
                moneyLog1.setOrderTypeName(MbChangeTypeEnum.USER_WITHDRAWAL.getName());//账变类型名称
                moneyLog1.setMoney(amount);//账变金额
            }else {
                moneyLog1.setOrderType(MbChangeTypeEnum.USER_RECHARGE.getType());//账变类型
                moneyLog1.setOrderTypeName(MbChangeTypeEnum.USER_RECHARGE.getName());//账变类型名称
                moneyLog1.setMoney(amount.negate());//账变金额
            }
            moneyLog1.setBeforeMoney(parentCurrentBalance);//账变前金额
            moneyLog1.setAfterMoney(parentAfterMoney);//账变后金额
            moneyLog1.setCreateTime(date);
            moneyLog1.setCreateBy(user.getUsername());
            moneyLog1.setUpdateTime(date);
            moneyLog1.setUpdateBy(user.getUsername());
            moneyLogList.add(moneyLog1);

            if(transferAccountsDto.getType()==0){
                if(StatusEnum.ZERO_FALSE.getStatus()==user.getIsTestAccount()) {//正式账号
                    addRewardMb(user, amount.negate());
                    addRewardMb(parentUser, amount);
                }else {
                    addRewardMb(user, amount);
                    addRewardTestMb(parentUser, amount.negate());
                }
            }else {
                if(StatusEnum.ZERO_FALSE.getStatus()==user.getIsTestAccount()){//正式账号
                    addRewardMb(user, amount);
                    addRewardMb(parentUser, amount.negate());
                }else {
                    addRewardMb(user, amount);
                    addRewardTestMb(parentUser, amount.negate());
                }

            }

            //账变记录
            moneyLogService.saveBatch(moneyLogList);
            return Result.succeed("转换成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        } finally {
            RedissLockUtil.unlock(lockKey);
        }
    }

    //根据代理用户获取下级用户id
    @Override
    public Integer[] getUserIdsByUserName(String username, Integer[] userIds) {


        Integer[] Ids = this.baseMapper.getUserIdsByUserName(username, userIds);

        return Ids;
    }


}

























