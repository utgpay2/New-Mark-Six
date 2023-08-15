package com.proxy.center.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.proxy.center.co.SiteCo;
import com.proxy.center.co.SiteUpdateCo;
import com.proxy.center.mapper.SiteMapper;
import com.proxy.center.model.dto.ProxyRechargeDto;
import com.proxy.center.model.dto.SiteRechargeDto;
import com.proxy.center.service.IAdminUserService;
import com.proxy.center.service.IAsyncService;
import com.proxy.center.service.IMoneyLogService;
import com.proxy.center.service.ISiteService;
import com.proxy.center.util.MD5;
import com.proxy.center.vo.SiteListVo;
import com.proxy.center.vo.SiteVo;
import com.central.common.constant.MarksixConstants;
import com.central.common.model.*;
import com.central.common.model.enums.CodeEnum;
import com.central.common.model.enums.MbChangeTypeEnum;
import com.central.common.redis.lock.RedissLockUtil;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.common.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;


@Slf4j
@Service
public class SiteServiceImpl extends SuperServiceImpl<SiteMapper, Site> implements ISiteService {



    @Autowired
    private IAsyncService asyncService;

    @Autowired
    private ThirdPartyServiceImpl thirdPartyService;

    @Autowired
    private IMoneyLogService moneyLogService;

    @Autowired
    IAdminUserService adminUserService;

    @Override
    public List<Site> getList() {
        String redisKey = MarksixConstants.RedisKey.KPN_SITE_LIST_KEY;
        List<Site> sites = (ArrayList) RedisRepository.get(redisKey);
        if (CollectionUtil.isEmpty(sites)) {
            sites = this.lambdaQuery().eq(Site::getStatus, true).list();
            if (CollectionUtil.isNotEmpty(sites)) {
                RedisRepository.setExpire(redisKey, sites, MarksixConstants.RedisKey.EXPIRE_TIME_30_DAYS);
            }
        }
        return sites;
    }

    @Override
    @Transactional(rollbackFor={RuntimeException.class,Exception.class})
    public Result recharge(SiteRechargeDto siteRechargeDto, SysUser sysUser) {
        //查询商户户主
        SysUser  user= baseMapper.getStationOwner(siteRechargeDto.getSiteId());
        if (ObjectUtil.isEmpty(user)) {
            return Result.failed("请添加商户管理员");
        }
        String lockKey = StrUtil.format(MarksixConstants.Lock.USER_TRANSFER_LOCK, user.getId());
        try {
            boolean lockedSuccess = RedissLockUtil.tryLock(lockKey, MarksixConstants.Lock.WAIT_TIME, MarksixConstants.Lock.LEASE_TIME);
            if (!lockedSuccess) {
                return Result.failed("加锁失败");
            }
            BigDecimal amount = siteRechargeDto.getAmount();//转账金额
            BigDecimal testAmount = siteRechargeDto.getTestAmount();//转账金额
            if(BigDecimal.ZERO.compareTo(amount)!=0){
                BigDecimal currentBalance = user.getMBalance();//用户当前余额
                BigDecimal afterMoney;
                afterMoney=currentBalance.add(amount);
                Date date=new Date();

                //用户金额日志
                String orderSn = SnowflakeIdWorker.createOrderSn();//订单号
                MoneyLog moneyLog = new MoneyLog();
                moneyLog.setUserId(user.getId());
                moneyLog.setUserName(user.getUsername());
                moneyLog.setOrderNo(orderSn);

                moneyLog.setOrderType(MbChangeTypeEnum.STATION_OWNER_RECHARGE.getType());//账变类型
                moneyLog.setOrderTypeName(MbChangeTypeEnum.STATION_OWNER_RECHARGE.getName());//账变类型名称
                moneyLog.setMoney(amount);//账变金额

                moneyLog.setBeforeMoney(currentBalance);//账变前金额
                moneyLog.setAfterMoney(afterMoney);//账变后金额
                moneyLog.setCreateTime(date);
                moneyLog.setCreateBy(sysUser.getUsername());
                moneyLog.setUpdateTime(date);
                moneyLog.setUpdateBy(sysUser.getUsername());

                //给户主充值
                adminUserService.addRewardMb(user, amount);

                //账变记录
                moneyLogService.save(moneyLog);
            }
            if(BigDecimal.ZERO.compareTo(testAmount)!=0){
                BigDecimal currentTestBalance = user.getMTestBalance();//用户当前测试余额
                BigDecimal afterMoney;
                afterMoney=currentTestBalance.add(testAmount);
                Date date=new Date();

                //用户金额日志
                String orderSn = SnowflakeIdWorker.createOrderSn();//订单号
                MoneyLog moneyLog = new MoneyLog();
                moneyLog.setUserId(user.getId());
                moneyLog.setUserName(user.getUsername());
                moneyLog.setOrderNo(orderSn);

                moneyLog.setOrderType(MbChangeTypeEnum.STATION_OWNER_RECHARGE_TEST.getType());//账变类型
                moneyLog.setOrderTypeName(MbChangeTypeEnum.STATION_OWNER_RECHARGE_TEST.getName());//账变类型名称
                moneyLog.setMoney(testAmount);//账变金额

                moneyLog.setBeforeMoney(currentTestBalance);//账变前金额
                moneyLog.setAfterMoney(afterMoney);//账变后金额
                moneyLog.setCreateTime(date);
                moneyLog.setCreateBy(sysUser.getUsername());
                moneyLog.setUpdateTime(date);
                moneyLog.setUpdateBy(sysUser.getUsername());

                //给户主充值
                adminUserService.addRewardTestMb(user, testAmount);

                //账变记录
                moneyLogService.save(moneyLog);
            }



            return Result.succeed("充值成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        } finally {
            RedissLockUtil.unlock(lockKey);
        }


    }

    @Override
    public PageResult getProxys(Map<String, Object> params, SysUser sysUser) {

        Page<SysUser> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));


        List<SysUser> list  =  baseMapper.getProxys(page, params);

        return PageResult.<SysUser>builder().data(list).count(page.getTotal()).build();
    }

    @Override
    @Transactional(rollbackFor={RuntimeException.class,Exception.class})
    public Result rechargeProxy(ProxyRechargeDto proxyRechargeDto, SysUser sysUser) {
        SysUser user = adminUserService.getById(proxyRechargeDto.getUserId());
         sysUser = adminUserService.getById(sysUser.getId());
        if(!user.getParentId().equals(sysUser.getId())){
            return Result.failed("请指定商户内代理！");
        }

        String lockKey = StrUtil.format(MarksixConstants.Lock.USER_TRANSFER_LOCK, user.getId());
        try {
            boolean lockedSuccess = RedissLockUtil.tryLock(lockKey, MarksixConstants.Lock.WAIT_TIME, MarksixConstants.Lock.LEASE_TIME);
            if (!lockedSuccess) {
                return Result.failed("加锁失败");
            }
            BigDecimal amount = proxyRechargeDto.getAmount();//转账金额
            if(BigDecimal.ZERO.compareTo(amount)!=0) {
                BigDecimal currentBalance = user.getMBalance();//用户当前余额
                BigDecimal parentCurrentBalance = sysUser.getMBalance();//用户当前余额

                BigDecimal afterMoney;
                BigDecimal parentAfterMoney;
                if (proxyRechargeDto.getType() == 0) {
                    if (currentBalance.compareTo(amount) != 1) {
                        return Result.failed(CodeEnum.MB_NOT_ENOUGH.getCode(), "代理余额不足", null);
                    }
                    afterMoney = currentBalance.subtract(amount);
                    parentAfterMoney = parentCurrentBalance.add(amount);
                } else {
                    if (parentCurrentBalance.compareTo(amount) != 1) {
                        return Result.failed(CodeEnum.MB_NOT_ENOUGH.getCode(), "站主余额不足", null);
                    }
                    parentAfterMoney = parentCurrentBalance.subtract(amount);
                    afterMoney = currentBalance.add(amount);
                }
                List<MoneyLog> moneyLogList = new ArrayList<>();
                Date date = new Date();

                //代理金额日志
                String orderSn = SnowflakeIdWorker.createOrderSn();//订单号
                MoneyLog moneyLog = new MoneyLog();
                moneyLog.setUserId(user.getId());
                moneyLog.setUserName(user.getUsername());
                moneyLog.setOrderNo(orderSn);
                if (proxyRechargeDto.getType() == 0) {
                    moneyLog.setOrderType(MbChangeTypeEnum.PROXY_WITHDRAWAL.getType());//账变类型
                    moneyLog.setOrderTypeName(MbChangeTypeEnum.PROXY_WITHDRAWAL.getName());//账变类型名称
                    moneyLog.setMoney(amount.negate());//账变金额
                } else {
                    moneyLog.setOrderType(MbChangeTypeEnum.PROXY_RECHARGE.getType());//账变类型
                    moneyLog.setOrderTypeName(MbChangeTypeEnum.PROXY_RECHARGE.getName());//账变类型名称
                    moneyLog.setMoney(amount);//账变金额
                }
                moneyLog.setBeforeMoney(currentBalance);//账变前金额
                moneyLog.setAfterMoney(afterMoney);//账变后金额
                moneyLog.setCreateTime(date);
                moneyLog.setCreateBy(user.getUsername());
                moneyLog.setUpdateTime(date);
                moneyLog.setUpdateBy(user.getUsername());
                moneyLogList.add(moneyLog);

                //开户商金额日志
                MoneyLog moneyLog1 = new MoneyLog();
                moneyLog1.setUserId(sysUser.getId());
                moneyLog1.setUserName(sysUser.getUsername());
                moneyLog1.setOrderNo(orderSn);
                if (proxyRechargeDto.getType() == 0) {
                    moneyLog1.setOrderType(MbChangeTypeEnum.OWNER_WITHDRAWAL.getType());//账变类型
                    moneyLog1.setOrderTypeName(MbChangeTypeEnum.OWNER_WITHDRAWAL.getName());//账变类型名称
                    moneyLog1.setMoney(amount);//账变金额
                } else {
                    moneyLog1.setOrderType(MbChangeTypeEnum.OWNER_RECHARGE.getType());//账变类型
                    moneyLog1.setOrderTypeName(MbChangeTypeEnum.OWNER_RECHARGE.getName());//账变类型名称
                    moneyLog1.setMoney(amount.negate());//账变金额
                }
                moneyLog1.setBeforeMoney(parentCurrentBalance);//账变前金额
                moneyLog1.setAfterMoney(parentAfterMoney);//账变后金额
                moneyLog1.setCreateTime(date);
                moneyLog1.setCreateBy(user.getUsername());
                moneyLog1.setUpdateTime(date);
                moneyLog1.setUpdateBy(user.getUsername());
                moneyLogList.add(moneyLog1);


                if (proxyRechargeDto.getType() == 0) {
                    adminUserService.addRewardMb(user, amount.negate());
                    adminUserService.addRewardMb(sysUser, amount);
                } else {
                    adminUserService.addRewardMb(user, amount);
                    adminUserService.addRewardMb(sysUser, amount.negate());
                }

                //账变记录
                moneyLogService.saveBatch(moneyLogList);
            }

            BigDecimal testAmount = proxyRechargeDto.getTestAmount();//转账测试金额
            if(BigDecimal.ZERO.compareTo(testAmount)!=0) {
                BigDecimal currentTestBalance = user.getMTestBalance();//用户当前余额
                BigDecimal parentCurrentBalance = sysUser.getMBalance();//用户当前余额

                BigDecimal afterMoney;
                BigDecimal parentAfterMoney;
                if (proxyRechargeDto.getType() == 0) {
                    if (currentTestBalance.compareTo(testAmount) != 1) {
                        return Result.failed(CodeEnum.MB_NOT_ENOUGH.getCode(), "代理余额不足", null);
                    }
                    afterMoney = currentTestBalance.subtract(testAmount);
                    parentAfterMoney = parentCurrentBalance.add(testAmount);
                } else {
                    if (parentCurrentBalance.compareTo(testAmount) != 1) {
                        return Result.failed(CodeEnum.MB_NOT_ENOUGH.getCode(), "站主余额不足", null);
                    }
                    parentAfterMoney = parentCurrentBalance.subtract(testAmount);
                    afterMoney = currentTestBalance.add(testAmount);
                }
                List<MoneyLog> moneyLogList = new ArrayList<>();
                Date date = new Date();

                //代理金额日志
                String orderSn = SnowflakeIdWorker.createOrderSn();//订单号
                MoneyLog moneyLog = new MoneyLog();
                moneyLog.setUserId(user.getId());
                moneyLog.setUserName(user.getUsername());
                moneyLog.setOrderNo(orderSn);
                if (proxyRechargeDto.getType() == 0) {
                    moneyLog.setOrderType(MbChangeTypeEnum.PROXY_WITHDRAWAL_TEST.getType());//账变类型
                    moneyLog.setOrderTypeName(MbChangeTypeEnum.PROXY_WITHDRAWAL_TEST.getName());//账变类型名称
                    moneyLog.setMoney(testAmount.negate());//账变金额
                } else {
                    moneyLog.setOrderType(MbChangeTypeEnum.PROXY_RECHARGE_TEST.getType());//账变类型
                    moneyLog.setOrderTypeName(MbChangeTypeEnum.PROXY_RECHARGE_TEST.getName());//账变类型名称
                    moneyLog.setMoney(testAmount);//账变金额
                }
                moneyLog.setBeforeMoney(currentTestBalance);//账变前金额
                moneyLog.setAfterMoney(afterMoney);//账变后金额
                moneyLog.setCreateTime(date);
                moneyLog.setCreateBy(user.getUsername());
                moneyLog.setUpdateTime(date);
                moneyLog.setUpdateBy(user.getUsername());
                moneyLogList.add(moneyLog);

                //开户商金额日志
                MoneyLog moneyLog1 = new MoneyLog();
                moneyLog1.setUserId(sysUser.getId());
                moneyLog1.setUserName(sysUser.getUsername());
                moneyLog1.setOrderNo(orderSn);
                if (proxyRechargeDto.getType() == 0) {
                    moneyLog1.setOrderType(MbChangeTypeEnum.OWNER_WITHDRAWAL_TEST.getType());//账变类型
                    moneyLog1.setOrderTypeName(MbChangeTypeEnum.OWNER_WITHDRAWAL_TEST.getName());//账变类型名称
                    moneyLog1.setMoney(testAmount);//账变金额
                } else {
                    moneyLog1.setOrderType(MbChangeTypeEnum.OWNER_RECHARGE_TEST.getType());//账变类型
                    moneyLog1.setOrderTypeName(MbChangeTypeEnum.OWNER_RECHARGE_TEST.getName());//账变类型名称
                    moneyLog1.setMoney(testAmount.negate());//账变金额
                }
                moneyLog1.setBeforeMoney(parentCurrentBalance);//账变前金额
                moneyLog1.setAfterMoney(parentAfterMoney);//账变后金额
                moneyLog1.setCreateTime(date);
                moneyLog1.setCreateBy(user.getUsername());
                moneyLog1.setUpdateTime(date);
                moneyLog1.setUpdateBy(user.getUsername());
                moneyLogList.add(moneyLog1);


                if (proxyRechargeDto.getType() == 0) {
                    adminUserService.addRewardTestMb(user, testAmount.negate());
                    adminUserService.addRewardTestMb(sysUser, testAmount);
                } else {
                    adminUserService.addRewardTestMb(user, testAmount);
                    adminUserService.addRewardTestMb(sysUser, testAmount.negate());
                }

                //账变记录
                moneyLogService.saveBatch(moneyLogList);
            }

            return Result.succeed("操作成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        } finally {
            RedissLockUtil.unlock(lockKey);
        }


    }


    @Override
    public PageResult<Site> findSiteList(SiteCo params) {
        Page<Site> page = new Page<>(params.getPage(), params.getLimit());
        List<Site> list = baseMapper.findList(page, params);
        return PageResult.<Site>builder().data(list).count(page.getTotal()).build();
    }

    @Override
    public List<Site> findKpnSiteList(Map<String, Object> params) {
        return baseMapper.findKpnSiteList(params);
    }

    @Override
    public SiteVo findSiteTotal() {
        return baseMapper.findSiteTotal();
    }

    @Transactional
    @Override
    public Result saveOrUpdateSite(Site site) {
        boolean insert = false;
        //新增
        if (site.getId() == null || site.getId()==0  ) {
            insert = super.save(site);
            ThirdParty thirdParty=new ThirdParty();
            thirdParty.setSiteId(site.getId());
            thirdParty.setSiteCode(site.getCode());
            thirdParty.setSecretKey(MD5.encrypt(site.getId()+":"+site.getCode()));
            thirdPartyService.save(thirdParty);
        } else {
            Site info = baseMapper.selectById(site.getId());
            if (info == null) {
                return Result.failed("数据不存在");
            }
            //修改
            insert = super.updateById(site);
        }
        if (insert) {
            // add by year 删除商户信息缓存
            asyncService.deleteSiteInfoCache(site.getId());
            return Result.succeed(site, "操作成功");
        }
        return Result.failed("操作失败");
    }

    @Override
    public Result updateEnabledSite(SiteUpdateCo params) {
        Long id = params.getId();
        Site siteInfo = baseMapper.selectById(id);
        if (siteInfo == null) {
            return Result.failed("此商户不存在");
        }
        if (params.getStatus() != null) {
            siteInfo.setStatus(params.getStatus());
        }
        if (params.getRepairStatus() != null) {
            siteInfo.setRepairStatus(params.getRepairStatus());
        }
        siteInfo.setUpdateBy(params.getUpdateBy());
        int i = baseMapper.updateById(siteInfo);

        // add by year 删除商户信息缓存
        if (i > 0) {
            asyncService.deleteSiteInfoCache(siteInfo.getId());
        }
        return i > 0 ? Result.succeed(siteInfo, "更新成功") : Result.failed("更新失败");
    }



    @Override
    public  String getStringRandom(int length) {
        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    @Override
    public List<SiteListVo> findSiteBoxList(Integer roleId ) {
        return baseMapper.findSiteBoxList(roleId);
    }

}