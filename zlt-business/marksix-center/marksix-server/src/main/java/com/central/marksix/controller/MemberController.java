package com.central.marksix.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
//import com.central.common.utils.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.annotation.LoginUser;
import com.central.common.constant.MarksixConstants;
import com.central.common.model.*;
import com.central.common.model.enums.CodeEnum;
import com.central.common.model.enums.StatusEnum;
import com.central.common.model.pay.SiteBankCard;
import com.central.common.model.pay.SiteProduct;
import com.central.common.redis.lock.RedissLockUtil;
import com.central.oss.model.ObjectInfo;
import com.central.oss.template.MinioTemplate;
import com.central.marksix.entity.PornPageResult;
import com.central.marksix.entity.dto.KpnSiteAnnouncementUserDto;
import com.central.marksix.entity.vo.*;
import com.central.marksix.service.*;
import com.central.marksix.utils.PornUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 会员相关
 */
@Slf4j
@RefreshScope
@RestController
@RequestMapping("/v1/member")
@Api(tags = "会员相关api接口")
public class MemberController {

    @Resource
    private MinioTemplate minioTemplate;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISiteSignDetailService siteSignDetailService;

    @Value("${zlt.minio.externalEndpoint}")
    private String externalEndpoint;

    @Autowired
    private ISiteSuggestionService siteSuggestionService;

    @Autowired
    private ISiteAnnouncementService siteAnnouncementService;

    @Autowired
    private ISiteUserVipLogService siteUserVipLogService;

    @Autowired
    private IMoneyLogService moneyLogService;

    @Autowired
    private ISiteProductService siteProductService;

    @Autowired
    private ISiteBankCardService siteBankCardService;

    @Autowired
    private ISiteService siteService;

    @Autowired
    private ISitePlatformService sitePlatformService;

    @Autowired
    private ISiteOrderService siteOrderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISiteSignService iSiteSignService;
    @Autowired
    private ISiteAnnouncementUserService iSiteAnnouncementUserService;

    /**
     * 上传头像
     *
     * @param file
     * @return
     */
    @ApiOperation(value = "上传头像")
    @PostMapping("/upload/header")
    public Result<String> upload(@ApiIgnore @LoginUser SysUser user, @RequestParam("file") MultipartFile file) {
        ObjectInfo upload = minioTemplate.upload(file);
        String headerImgUrl = upload.getObjectPath();

        userService.lambdaUpdate().eq(SysUser::getId, user.getId()).set(SysUser::getHeadImgUrl, headerImgUrl).update();
        return Result.succeed("操作成功");
    }

    /**
     * 获取用户信息
     */
    @ApiOperation(value = "获取用户信息")
    @GetMapping("/info")
    public Result<SysUserVo> getUserInfo(@ApiIgnore @LoginUser SysUser user) {
        try {
            SysUser userInfo = userService.getById(user.getId());

            SysUserVo sysUserVo = SysUserVo.builder()
                    .username(userInfo.getNickname())
                    .headImgUrl(externalEndpoint + MarksixConstants.Symbol.FORWARD_SLASH + userInfo.getHeadImgUrl())
                    .inviteCode(userInfo.getInviteCode())
                    .promotionCode(userInfo.getPromotionCode())
                    .vip(userInfo.getVip())
                    .vipExpire(DateUtil.formatDate(userInfo.getVipExpire()))
                    .between(DateUtil.betweenDay(new Date(),userInfo.getVipExpire(),true))
                    .kBalance(userInfo.getKBalance().setScale(2, RoundingMode.FLOOR))
                    .build();
            return Result.succeed(sysUserVo, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    @ApiOperation("使用K币开通/续费VIP")
    @PostMapping("/buy/kb")
    public Result<String> buyVipUseKb(@ApiIgnore @LoginUser SysUser user, @ApiParam(value = "产品id", required = true) Long productId) {
        try {
            SysUser sysUser = userService.getById(user.getId());
            SiteProduct product = siteProductService.getById(productId);
            BigDecimal userKBalance = sysUser.getKBalance();
            BigDecimal productPrice = product.getPrice();
            //K币
            if (!PornUtil.isDecimalGeThan(userKBalance, productPrice)) {
                return Result.failed(CodeEnum.KB_NOT_ENOUGH.getCode(), "余额不足",null);
            }

            Boolean isVip = sysUser.getVip();
            siteProductService.buyUseKb(sysUser, product);
            return Result.succeed(isVip?"续期成功":"开通成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    /**
     * 会员K币账变记录
     *
     * @param user     登录会员
     * @param currPage 当前页数
     * @param pageSize 每页条数
     * @return
     */
    @GetMapping("/kb/change/records")
    @ApiOperation(value = "会员K币账变记录")
    public Result<PornPageResult<KbChangeRecordVo>> removeChannel(@ApiIgnore @LoginUser SysUser user,
                                                                  @ApiParam("当前页数") Integer currPage,
                                                                  @ApiParam("每页条数") Integer pageSize) {
        try {
            PornPageResult<KbChangeRecordVo> changeRecordVos = moneyLogService.getByUserId(user.getId(), currPage, pageSize);
            return Result.succeed(changeRecordVos, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    @ApiOperation("获取站点支付银行卡")
    @GetMapping("/bank/cards")
    public Result<SitePayResultVo> getSiteBankCards(@ApiIgnore @LoginUser SysUser user,
                                                    @ApiParam("产品id") Long productId) {
        try {
            SysUser sysUser = userService.getById(user.getId());
            SiteProduct product = siteProductService.getById(productId);
            Site siteInfo = siteService.getInfoById(sysUser.getSiteId());
            SitePlatform sitePlatform = sitePlatformService.getBySiteId(sysUser.getSiteId());

            List<SiteBankCard> siteBankCards = siteBankCardService.getBySiteId(sysUser.getSiteId());
            List<SiteBankCardPayVo> bankCardPayVos = new ArrayList<>();
            for (SiteBankCard siteBankCard : siteBankCards) {
                SiteBankCardPayVo bankCardPayVo = new SiteBankCardPayVo();
                BeanUtil.copyProperties(siteBankCard, bankCardPayVo);
                bankCardPayVo.setAmount(product.getPrice().divide(sitePlatform.getExchange(), 2, RoundingMode.CEILING));
                bankCardPayVo.setCurrency(siteInfo.getCurrencyCode());
                bankCardPayVos.add(bankCardPayVo);
            }

            String orderNo = MarksixConstants.Str.ORDER_NO_PREFIX + RandomUtil.randomNumbers(MarksixConstants.Numeric.ORDER_NO_LENGTH);
            SitePayResultVo result = SitePayResultVo.builder().orderNo(orderNo).bankCardPayVos(bankCardPayVos).build();
            return Result.succeed(result, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    @ApiOperation("使用现金开通/续费VIP,提交订单")
    @PostMapping("/buy/cash")
    public Result<String> buyVipUseCash(@ApiIgnore @LoginUser SysUser user,
                                        @ApiParam(value = "订单号", required = true) String orderNo,
                                        @ApiParam(value = "汇款人姓名", required = true) String remitterName,
                                        @ApiParam(value = "交易号后6位", required = true) String certificate,
                                        @ApiParam(value = "手机号") String mobile,
                                        @ApiParam(value = "银行卡id", required = true) Long bankCardId,
                                        @ApiParam(value = "产品id", required = true) Long productId) {

        String lockKey = StrUtil.format(MarksixConstants.Lock.USER_SITEID_SUBMIT_ORDERNO_LOCK, user.getSiteId(), orderNo);
        try {
            boolean lockedSuccess = RedissLockUtil.tryLock(lockKey, MarksixConstants.Lock.WAIT_TIME, MarksixConstants.Lock.LEASE_TIME);
            if (!lockedSuccess) {
                throw new RuntimeException("加锁失败");
            }
            if (StrUtil.isBlank(orderNo)) {
                return Result.failed("订单号不能为空");
            }
            if (StrUtil.isBlank(remitterName)) {
                return Result.failed("汇款人姓名不能为空");
            }
            if (StrUtil.isBlank(certificate)) {
                return Result.failed("交易号尾号不能为空");
            }
            if (ObjectUtil.isEmpty(bankCardId)) {
                return Result.failed("无法获取银行卡信息");
            }
            if (ObjectUtil.isEmpty(productId)) {
                return Result.failed("无法获取产品信息");
            }

            boolean isOrderNoExists = siteOrderService.isOrderNoExists(user.getSiteId(), orderNo);
            if (isOrderNoExists) {
                return Result.failed("订单已提交,不可重复操作");
            }

            SysUser sysUser = userService.getById(user.getId());
            SiteProduct product = siteProductService.getById(productId);
            Site siteInfo = siteService.getInfoById(sysUser.getSiteId());
            SiteBankCard bankCard = siteBankCardService.getById(bankCardId);
            SitePlatform sitePlatform = sitePlatformService.getBySiteId(sysUser.getSiteId());

            SiteUserOrder siteOrder = new SiteUserOrder();
            siteOrder.setSiteId(siteInfo.getId());
            siteOrder.setSiteCode(siteInfo.getCode());
            siteOrder.setSiteName(siteInfo.getName());
            siteOrder.setOrderNo(orderNo);
            siteOrder.setUserId(sysUser.getId());
            siteOrder.setUserName(sysUser.getUsername());
            siteOrder.setRemitterName(remitterName);
            siteOrder.setBankId(bankCard.getBankId());
            siteOrder.setBankName(bankCard.getBankName());
            siteOrder.setBankCardId(bankCard.getId());
            siteOrder.setBankCard(bankCard.getCard());
            siteOrder.setBankCardAccount(bankCard.getAccount());
            siteOrder.setProductId(productId);
            siteOrder.setProductName(product.getNameZh());
            if (StrUtil.isNotBlank(mobile)) {
                siteOrder.setMobile(mobile);
            }
            siteOrder.setProductPrice(product.getPrice().divide(sitePlatform.getExchange(), 2, RoundingMode.CEILING));
            siteOrder.setCertificate(certificate);

            //K币
            siteOrderService.save(siteOrder);
            return Result.succeed("订单已经提交,正在审核中");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        } finally {
            RedissLockUtil.unlock(lockKey);
        }
    }

    @ApiOperation(value = "查询订单记录")
    @GetMapping("/order/records")
    public Result<PornPageResult<SiteUserOrderVo>> orderRecords(@ApiIgnore @LoginUser SysUser user,
                                                                @ApiParam("当前页") Integer currPage,
                                                                @ApiParam("每页条数") Integer pageSize) {
        try {
            PornPageResult<SiteUserOrderVo> userOrderRecordsPage = siteOrderService.getUserOrderRecords(user.getSiteId(), user.getId(), currPage, pageSize);
            return Result.succeed(userOrderRecordsPage, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }


    /**
     * 保存意见反馈
     */
    @ApiOperation(value = "保存意见反馈")
    @PostMapping("/saveSuggestion")
    public Result<String> saveSuggestion(@ApiIgnore @LoginUser SysUser user, String email, String content) {
        try {
            if (StrUtil.isBlank(content)) {
                return Result.failed("意见内容不能为空");
            }

            if (StrUtil.trim(content).length() > 1000) {
                return Result.failed("内容长度不能超过1000");
            }

            if (StrUtil.isNotBlank(email)) {
                email = StrUtil.trim(email);
                if (email.length() > 100) {
                    return Result.failed("邮箱长度不能超过100");
                }
                if (!email.matches(MarksixConstants.Reg.CHECK_EMAIL)) {
                    return Result.failed("邮箱格式不正确");
                }
            }
            SysUser userInfo = userService.getById(user.getId());

            SiteSuggestion siteSuggestion = new SiteSuggestion();
            siteSuggestion.setSiteId(userInfo.getSiteId());
            siteSuggestion.setSiteCode(userInfo.getSiteCode());
            siteSuggestion.setSiteName(userInfo.getSiteName());
            siteSuggestion.setUserId(userInfo.getId());
            siteSuggestion.setUserName(userInfo.getUsername());
            siteSuggestion.setContent(content);
            if (StrUtil.isNotBlank(email)) {
                siteSuggestion.setEmail(email);
            }
            siteSuggestionService.save(siteSuggestion);
            return Result.succeed("succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    /**
     * 获取邮件消息(公告)
     */
    @ApiOperation(value = "获取公告消息")
    @GetMapping("/getAnnouncements")
    public Result<List<AnnouncementVo>> getAnnouncements(@ApiIgnore @LoginUser SysUser user) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("siteId",user.getSiteId());
            params.put("status", StatusEnum.ONE_FALSE.getStatus());
            params.put("userId",user.getId());
            List<AnnouncementUserVo> kpnSiteAnnouncements = siteAnnouncementService.findList(params);
//                    siteAnnouncementService.lambdaQuery()
//                    .eq(SiteAnnouncement::getSiteId, user.getSiteId())
//                    .eq(SiteAnnouncement::getStatus, true)
//                    .orderByDesc(SiteAnnouncement::getSort, SiteAnnouncement::getCreateTime)
//                    .list();

            List<AnnouncementVo> announcementVos = kpnSiteAnnouncements.stream().map(AnnouncementVo::new).collect(Collectors.toList());

            return Result.succeed(announcementVos, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }
    /**
     * 新增or更新
     */
    @ApiOperation(value = "设置公告已读")
    @PostMapping("/settingIsRead")
    public Result saveOrUpdateSysWhiteIp(@RequestBody KpnSiteAnnouncementUserDto dto, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(dto)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(dto.getAnnId())) {
            return Result.failed("公告id不能为空");
        }
        if (ObjectUtil.isEmpty(dto.getIsRead())) {
            return Result.failed("状态不能为空");
        }
        return iSiteAnnouncementUserService.saveOrUpdateAnnUser(dto,user);
    }
    @ApiOperation("查询签到配置列表")
    @ResponseBody
    @GetMapping("/findSignList")
    public Result< List<SiteSign>> findSignList(@RequestHeader("sid") Long sid) {
        List<SiteSign> signList = iSiteSignService.findSignList(sid);
        return Result.succeed(signList);
    }
    /**
     * 获取月份签到信息
     */
    @ApiOperation(value = "获取月份签到信息")
    @GetMapping("/sign/history")
    public Result<List<SiteUserSignHistoryVo>> getSignHistory(@ApiIgnore @LoginUser SysUser user,
                                                              @ApiParam("当前年月(yyyy-MM),为空时返回当前月份签到数据") String month) {
        try {
            //todo 获取上月后7天签到数据
            SysUser sysUser = userService.getById(user.getId());
            if (StrUtil.isBlank(month)) {
                month = DateUtil.format(new Date(), MarksixConstants.Format.YYYY_MM);
            }

            List<SiteUserSignHistoryVo> resultVos = siteSignDetailService.getSignHistory(sysUser, month);
            return Result.succeed(resultVos, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    /**
     * 获取累计签到天数
     */
    @ApiOperation(value = "获取累计签到天数")
    @GetMapping("/sign/days")
    public Result<Integer> getSignDays(@ApiIgnore @LoginUser SysUser user) {
        try {
            Integer days = siteSignDetailService.getUserSignDays(user.getId());
            return Result.succeed(days, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }
    /**
     * 签到
     */
    @ApiOperation(value = "签到")
    @PostMapping("/sign")
    public Result<SiteUserSignResultVo> sign(@ApiIgnore @LoginUser SysUser user,
                                             @ApiParam("签到日期(yyyy-MM-dd)") String date) {
        if (ObjectUtil.isEmpty(date)) {
            return Result.failed("签到日期不能为空");
        }
        String lockKey = StrUtil.format(MarksixConstants.Lock.USER_SIGN_LOCK, user.getId());
        try {
            boolean lockedSuccess = RedissLockUtil.tryLock(lockKey, MarksixConstants.Lock.WAIT_TIME, MarksixConstants.Lock.LEASE_TIME);
            if (!lockedSuccess) {
                throw new RuntimeException("加锁失败");
            }
            date = DateUtil.formatDate(DateUtil.parseDate(date));
            //只有当天可以签到
            if (!(DateUtil.formatDate(new Date()).equalsIgnoreCase(date))) {
                log.error("签到日期:{}", date);
                return Result.failed("只有今日可以签到");
            }

            //判断当天是否已经签到
            boolean hasSigned = siteSignDetailService.checkHasSigned(user.getId(), date);
            if (hasSigned) {
                return Result.failed("今日已经签到");
            }

            //签到
            SysUser sysUser = userService.getById(user.getId());
            SiteUserSignResultVo resultVo = siteSignDetailService.sign(sysUser, date);

            return Result.succeed(resultVo, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        } finally {
            log.info("释放锁:" + lockKey);
            RedissLockUtil.unlock(lockKey);
        }
    }

    /**
     * 填写邀请码
     */
    @ApiOperation(value = "会员填写邀请码")
    @PostMapping("/inviteCode/save")
    public Result<String> saveInviteCode(@ApiIgnore @LoginUser SysUser user,
                                         @ApiParam(value = "邀请码", required = true) String inviteCode) {
        String lockKey = StrUtil.format(MarksixConstants.Lock.USER_SAVE_INVITE_CODE_LOCK, user.getId());
        try {
            boolean lockedSuccess = RedissLockUtil.tryLock(lockKey, MarksixConstants.Lock.WAIT_TIME, MarksixConstants.Lock.LEASE_TIME);
            if (!lockedSuccess) {
                throw new RuntimeException("加锁失败");
            }
            if (StrUtil.isBlank(inviteCode) || inviteCode.length() != MarksixConstants.Numeric.INVITE_CODE_LENGTH) {
                return Result.failed("邀请码错误");
            }

            SysUser promoteUser = userService.getByInviteCode(inviteCode);
            if (ObjectUtil.isEmpty(promoteUser)) {
                return Result.failed("邀请码错误");
            }

            userService.saveInviteCode(user.getSiteId(), user.getId(), promoteUser, inviteCode);
            return Result.succeed("succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        } finally {
            RedissLockUtil.unlock(lockKey);
        }
    }

    /**
     * 获取我的推广数据
     */
    @ApiOperation(value = "获取我的推广数据")
    @GetMapping("/promotion/info")
    public Result<PromotionInfoVo> getPromotionInfo(@ApiIgnore @LoginUser SysUser user) {
        try {
            SysUser sysUser = userService.getById(user.getId());
            String promotionCode = sysUser.getPromotionCode();
            if (StrUtil.isBlank(promotionCode)) {
                log.error("会员推广码为空,userId: {}", sysUser.getId());
                PromotionInfoVo emptyVo = PromotionInfoVo.builder().promotionCode(promotionCode).members(0).vipDays(0).kbs(BigDecimal.ZERO).build();
                return Result.succeed(emptyVo, "succeed");
            }

            Integer members = userService.getPromotionMemberCount(promotionCode);
            Integer vipDays = siteUserVipLogService.getPromotionRewardVipDays(sysUser.getId());
            BigDecimal kbs = moneyLogService.getPromotionRewardTotalKbs(sysUser.getId()).setScale(2, RoundingMode.FLOOR);

            PromotionInfoVo promotionInfoVo = PromotionInfoVo.builder().promotionCode(promotionCode).members(members).vipDays(vipDays).kbs(kbs).build();
            return Result.succeed(promotionInfoVo, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    /**
     * 会员修改密码
     */
    @ApiOperation(value = "会员修改密码")
    @PostMapping("/modify/password")
    public Result<String> modifyPassword(@ApiIgnore @LoginUser SysUser user,
                                         @ApiParam(value = "旧密码",required = true) String oldPassword,
                                         @ApiParam(value="新密码",required = true) String newPassword,
                                         @ApiParam(value="新密码2",required = true) String newPassword2) {
        try {
            if (StrUtil.isBlank(newPassword)) {
                return Result.failed("新密码不能为空");
            }

            newPassword = StrUtil.trim(newPassword);
            if (newPassword.length() > 20 || newPassword.length() < 6) {
                return Result.failed("密码应为6到20之间的数字与字母组合");
            }

            if (!StrUtil.trim(newPassword2).equals(StrUtil.trim(newPassword))) {
                return Result.failed("新密码不一致");
            }

            SysUser sysUser = sysUserService.getById(user.getId());
            boolean matches = passwordEncoder.matches(oldPassword, sysUser.getPassword());
            if (!matches) {
                return Result.failed("原密码错误");
            }

            sysUser.setPassword(passwordEncoder.encode(newPassword));
            sysUserService.saveOrUpdate(sysUser);
            return Result.succeed("succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

}
