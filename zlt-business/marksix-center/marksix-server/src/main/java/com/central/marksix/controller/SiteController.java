package com.central.marksix.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.MarksixConstants;
import com.central.common.dto.I18nSourceDTO;
import com.central.common.language.LanguageEnum;
import com.central.common.language.LanguageUtil;
import com.central.common.model.*;
import com.central.common.model.enums.*;
import com.central.common.redis.template.RedisRepository;
import com.central.common.utils.I18nUtil;
import com.central.marksix.entity.vo.*;
import com.central.marksix.service.*;
import com.central.user.feign.UaaService;
import com.central.user.feign.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 站点相关
 */
@Slf4j
@RefreshScope
@RestController
@RequestMapping("/v1/site")
@Api(tags = "站点相关api接口")
public class SiteController {

    @Autowired
    private IKpnSiteService siteService;

    @Autowired
    private IKpnLineService kpnLineService;

    @Autowired
    private IKpnSiteAdvertiseService siteAdvertiseService;

    @Autowired
    private UserService userService;

    @Autowired
    private UaaService uaaService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IKpnSiteService kpnSiteService;

    @Value("${marksix.business.authorization:Basic d2ViQXBwOndlYkFwcA==}")
    private String authorization;

    @Autowired
    private IKpnSiteProductService siteProductService;

    @Value("${zlt.minio.externalEndpoint}")
    private String externalEndpoint;

    @Resource
    private TaskExecutor taskExecutor;

    @Autowired
    private IKpnSitePlatformService sitePlatformService;

    @Autowired
    private IKpnSiteServeService siteServeService;

    public static final String AUTHENTICATION_MODE = "password_code";

    /**
     * 获取站点信息
     *
     * @param request
     * @return
     */
    @GetMapping("/info")
    @ApiOperation(value = "获取站点信息")
    public Result<KpnSiteVo> getSiteInfo(HttpServletRequest request) {
        try {
            String referer = request.getHeader(MarksixConstants.Str.REFERER);
            String host = request.getHeader(MarksixConstants.Str.REHOST);
            String sid = request.getHeader(MarksixConstants.Str.SID);
            log.info("获取站点信息 -> sid: {},referer: {},host: {}", sid, referer, host);

            KpnSite site = null;
            if (StrUtil.isBlank(sid)) {
                site = siteService.getInfoByReferer(referer);
                if (ObjectUtil.isEmpty(site)) {
                    site = siteService.getInfoByReferer(host);
                }
            } else {
                site = siteService.getInfoById(Long.parseLong(sid));
            }

            if (ObjectUtil.isEmpty(site)) {
                return Result.failed("站点不存在");
            }
            //站点信息
            KpnSiteVo kpnSiteVo = new KpnSiteVo();
            kpnSiteVo.setSid(site.getId());
            kpnSiteVo.setCurrencyCode(site.getCurrencyCode());
            kpnSiteVo.setLogoUrl(externalEndpoint + MarksixConstants.Symbol.FORWARD_SLASH + site.getLogoUrl());
//            kpnSiteVo.setChannels(channelVos);
//            kpnSiteVo.setTopics(topicVos);

            return Result.succeed(kpnSiteVo, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    @GetMapping("/heartbeat")
    @ApiOperation(value = "心跳,1分钟一次")
    public Result<SiteConfigInfoVo> heartbeat(@ApiParam(value = "站点id", required = true)
                                              @RequestHeader(value = "sid") Long sid,
                                              @ApiParam("在线唯一标识 已登录的使用username,未登录的用uuid")
                                              @RequestParam String uniqueOnlineId) {
        try {
            taskExecutor.execute(() -> {
                String uniqueOnlineIdRedisKey = StrUtil.format(MarksixConstants.RedisKey.KPN_SITE_ONLINE_UNIQUE_ID, sid, uniqueOnlineId);
                String redisUniqueOnlineId = (String) RedisRepository.get(uniqueOnlineIdRedisKey);

                if (StrUtil.isBlank(redisUniqueOnlineId)) {
                    RedisRepository.incr(StrUtil.format(MarksixConstants.RedisKey.KPN_SITE_ONLINE_COUNT, sid));
                }
                RedisRepository.setExpire(uniqueOnlineIdRedisKey, sid + MarksixConstants.Symbol.SHARP + uniqueOnlineId, MarksixConstants.RedisKey.EXPIRE_TIME_90_SECONDS);
            });

            //线路
            Map<String, List<String>> kpnLineVos = kpnLineService.getLines();
            //站点平台配置
            KpnSitePlatform sitePlatform = sitePlatformService.getBySiteId(sid);
            //防走失
            SiteConfigInfoVo siteConfigInfoVo = SiteConfigInfoVo.builder().kpnLineVos(kpnLineVos).tryTime(sitePlatform.getTryTime()).lostDomain(sitePlatform.getLostDomain()).build();

            return Result.succeed(siteConfigInfoVo, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }
    /**
     * 获取站点线路
     *
     * @param sid 站点id
     * @return
     */
    @GetMapping("/lines")
    @ApiOperation(value = "获取站点线路")
    public Result<Map<String, List<String>>> getLines(@RequestHeader(value = "sid", required = false) Long sid) {
        try {
            Map<String, List<String>> kpnLineVos = kpnLineService.getLines();
            return Result.succeed(kpnLineVos, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }


    /**
     * 获取站点广告
     *
     * @return
     */
    @GetMapping("/pictures")
    @ApiOperation(value = "获取站点广告")
    public Result<List<KpnSiteAdvertiseVo>> getSiteAdvertise(@RequestHeader(value = "sid") Long sid,
                                                             @ApiParam(value = "设备类型 H5/PC", required = true) String deviceType,
                                                             @ApiParam(value = "投放位置 1首页轮播图,2首页平台展示,3首页专题广告,4福利,5游戏轮播图,6游戏广告", required = true) Integer position) {
        try {
            List<KpnSiteAdvertise> siteAds = siteAdvertiseService.getSiteAdvertise(sid, deviceType, position);
            List<KpnSiteAdvertiseVo> siteAdVos = siteAds.stream().map(ad -> {
                KpnSiteAdvertiseVo adVo = new KpnSiteAdvertiseVo();
                BeanUtil.copyProperties(ad, adVo);
                adVo.setName(LanguageUtil.getLanguageName(adVo));
                adVo.setUrl(externalEndpoint + MarksixConstants.Symbol.FORWARD_SLASH + adVo.getUrl());
                return adVo;
            }).collect(Collectors.toList());

//            Map<String, Map<Integer, List<KpnSiteAdvertiseVo>>> siteAdVoMap = siteAdVos.stream()
//                    .collect(groupingBy(KpnSiteAdvertiseVo::getDevice, groupingBy(KpnSiteAdvertiseVo::getPosition)));

            return Result.succeed(siteAdVos, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    /**
     * 获取站点广告
     *
     * @return
     */
    @GetMapping("/ads/hits")
    @ApiOperation(value = "统计广告点击量")
    public Result<String> getSiteAdvertise(@RequestHeader(value = "sid") Long sid,
                                           @ApiParam("广告id") Long adId) {
        try {
            siteAdvertiseService.addHits(adId);
            return Result.succeed("succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }


    //todo lk
    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<String> login(@ApiParam(value = "站点id", required = true) @RequestHeader("sid") Long sid,
//                                @ApiParam(value = "图形验证码id", required = true) String verifyCodeId,
//                                @ApiParam(value = "验证码", required = true) String verifyCode,
                                @ApiParam(value = "登录账号", required = true) String username,
                                @ApiParam(value = "密码", required = true) String password) {
        //校验账号
        if (StrUtil.isBlank(username)) {
            return Result.failed("账号不能为空");
        }
        if (StrUtil.isBlank(password)) {
            return Result.failed("密码不能为空");
        }

//        //校验验证码
//        if (StrUtil.isBlank(verifyCodeId) || StrUtil.isBlank(verifyCode)) {
//            return Result.failed("验证码不能为空");
//        }
//        String cachedCode = (String) RedisRepository.get(verifyCodeId);
//        if (StrUtil.isBlank(cachedCode)) {
//            return Result.failed("验证码已过期");
//        }
//
//        if (!cachedCode.equalsIgnoreCase(verifyCode)) {
//            return Result.failed("验证码错误");
//        }

        KpnSite kpnSite = kpnSiteService.getInfoById(sid);

        username = kpnSite.getCode() + "_" + username;
        LoginAppUser sysUser = userService.findByUsername(username);
        if (sysUser == null || !sysUser.getEnabled()) {
            return Result.failed("用户名或密码错误");
        }
        if(!UserTypeEnum.APP.name().equals(sysUser.getType())){
            return Result.failed("非普通用户");
        }

        Result tokenResult = uaaService.login(authorization, username, password, AUTHENTICATION_MODE);
        if (tokenResult == null || !tokenResult.getResp_code().equals(CodeEnum.SUCCESS.getCode())) {
            log.error("登录失败: username={}, msg={}", username, tokenResult.getResp_msg());
            return Result.failed(tokenResult.getResp_msg());
        }
        String accessToken = (String) (((LinkedHashMap) tokenResult.getDatas()).get(MarksixConstants.Str.ACCESS_TOKEN));

        return Result.succeed(accessToken, "succeed");
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public Result<String> register(@ApiParam(value = "站点id", required = true) @RequestHeader("sid") Long sid,
                                   @ApiParam(value = "邀请码") String inviteCode,
                                   @ApiParam(value = "登录账号", required = true) String username,
                                   @ApiParam(value = "密码", required = true) String password) {
        try {
            //校验账号
            if (StrUtil.isBlank(username)) {
                return Result.failed("账号不能为空");
            }
            if (!username.matches(MarksixConstants.Reg.CHECK_USERNAME)) {
                return Result.failed("账号不合法,需为6到10位的数字或字母");
            }

            //密码校验
            if (StrUtil.isBlank(password)) {
                return Result.failed("密码不能为空");
            }
            if (!password.matches(MarksixConstants.Reg.CHECK_PASSWORD)) {
                return Result.failed("密码不合法,需为6到10位的数字或字母");
            }

            //检查邀请码 可以为空,不为空时保证存在
            SysUser promoteUser = null;
            if (StrUtil.isNotBlank(inviteCode)) {
                promoteUser = sysUserService.getByInviteCode(inviteCode);
                if (ObjectUtil.isEmpty(promoteUser)) {
                    return Result.failed("邀请码错误");
                }
            }

            KpnSite kpnSite = kpnSiteService.getInfoById(sid);
            String nickName = username;
            username = kpnSite.getCode() + MarksixConstants.Symbol.UNDERSCORE + username;
            LoginAppUser sysUser = userService.findByUsername(username);
            if (sysUser != null) {
                return Result.failed("账号已经存在");
            }

            sysUserService.register(sid, promoteUser, nickName, username, password);

            //登录
            Result tokenResult = uaaService.login(authorization, username, password, AUTHENTICATION_MODE);
            if (tokenResult == null || !tokenResult.getResp_code().equals(CodeEnum.SUCCESS.getCode())) {
                log.error("登录失败: username={}, msg={}", username, tokenResult.getResp_msg());
                return Result.failed(tokenResult.getResp_msg());
            }
            String accessToken = (String) (((LinkedHashMap) tokenResult.getDatas()).get(MarksixConstants.Str.ACCESS_TOKEN));
            return Result.succeed(accessToken, "注册成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("注册失败");
        }
    }

    @ApiOperation("获取站点支付产品")
    @GetMapping("/products")
    public Result<List<KpnSiteProductVo>> getSiteProducts(@ApiParam(value = "站点id", required = true) @RequestHeader("sid") Long sid) {
        try {
            List<KpnSiteProductVo> siteProductVos = siteProductService.getSiteProducts(sid);
            return Result.succeed(siteProductVos,"succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    /**
     * 列表
     */
    @ApiOperation(value = "语言列表列表")
    @GetMapping("/language")
    public Result<Map<String, String>> languageList() {
        return Result.succeed(LanguageEnum.getOptions());
    }

    /**
     * 语言包-H5
     */
    @ApiOperation(value = "语言包-H5")
    @GetMapping("/h5FullSource")
    public Result<FullSourceVo> h5FullSource() {
        try {
            FullSourceVo fullSourceVo = new FullSourceVo();
            I18nSourceDTO frontFullSource = I18nUtil.getFrontAppFullSource();
            BeanUtil.copyProperties(frontFullSource, fullSourceVo);

            return Result.succeed(fullSourceVo, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    /**
     * 语言包-PC
     */
    @ApiOperation(value = "语言包-pc")
    @GetMapping("/pcFullSource")
    public Result<FullSourceVo> pcFullSource() {
        try {
            FullSourceVo fullSourceVo = new FullSourceVo();
            I18nSourceDTO frontFullSource = I18nUtil.getFrontFullSource();
            BeanUtil.copyProperties(frontFullSource, fullSourceVo);

            return Result.succeed(fullSourceVo, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    /**
     * 获取站点客服信息
     */
    @ApiOperation(value = "站点客服信息")
    @GetMapping("/getSiteServes")
    public Result<List<KpnSiteServeVo>> getSiteServes(@RequestHeader(value = "sid") Long sid) {
        try {
            List<KpnSiteServe> siteServes = siteServeService.getBySid(sid);

            List<KpnSiteServeVo> siteServeVos = siteServes.stream().map(kpnSiteServe -> {
                KpnSiteServeVo siteServeVo = new KpnSiteServeVo();
                siteServeVo.setPlatform(kpnSiteServe.getPlatform());
                siteServeVo.setServeAccount(kpnSiteServe.getServeAccount());
                siteServeVo.setPcIconUrl(externalEndpoint + MarksixConstants.Symbol.FORWARD_SLASH + kpnSiteServe.getPcIconUrl());
                siteServeVo.setPcIconUrl(externalEndpoint + MarksixConstants.Symbol.FORWARD_SLASH + kpnSiteServe.getAppIconUrl());
                return siteServeVo;
            }).collect(Collectors.toList());

            return Result.succeed(siteServeVos, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }


}
