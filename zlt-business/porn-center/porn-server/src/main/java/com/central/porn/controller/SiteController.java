package com.central.porn.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.PornConstants;
import com.central.common.dto.I18nSourceDTO;
import com.central.common.language.LanguageEnum;
import com.central.common.language.LanguageUtil;
import com.central.common.model.*;
import com.central.common.model.enums.*;
import com.central.common.redis.template.RedisRepository;
import com.central.common.utils.I18nUtil;
import com.central.porn.entity.PornPageResult;
import com.central.porn.entity.dto.MovieSearchConditionDto;
import com.central.porn.entity.vo.*;
import com.central.porn.enums.*;
import com.central.porn.service.*;
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
    private IKpnSiteChannelService siteChannelService;

    @Autowired
    private IKpnSiteTopicService siteTopicService;

    @Autowired
    private IKpnLineService kpnLineService;

    @Autowired
    private IKpnSiteAdvertiseService siteAdvertiseService;

    @Autowired
    private IKpnSiteActorService siteActorService;

    @Autowired
    private IKpnSiteMovieService siteMovieService;

    @Autowired
    private IRptSiteMovieDateService rptSiteMovieDateService;

    @Autowired
    private IRptSiteSearchTotalService rptSiteSearchTotalService;

    @Autowired
    private IRptSiteSearchDateService rptSiteSearchDateService;

    @Autowired
    private UserService userService;

    @Autowired
    private UaaService uaaService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IKpnSiteService kpnSiteService;

    @Value("${porn.business.authorization:Basic d2ViQXBwOndlYkFwcA==}")
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
            String referer = request.getHeader(PornConstants.Str.REFERER);
            String host = request.getHeader(PornConstants.Str.REHOST);
            String sid = request.getHeader(PornConstants.Str.SID);
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
            kpnSiteVo.setLogoUrl(externalEndpoint + PornConstants.Symbol.FORWARD_SLASH + site.getLogoUrl());
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
                String uniqueOnlineIdRedisKey = StrUtil.format(PornConstants.RedisKey.KPN_SITE_ONLINE_UNIQUE_ID, sid, uniqueOnlineId);
                String redisUniqueOnlineId = (String) RedisRepository.get(uniqueOnlineIdRedisKey);

                if (StrUtil.isBlank(redisUniqueOnlineId)) {
                    RedisRepository.incr(StrUtil.format(PornConstants.RedisKey.KPN_SITE_ONLINE_COUNT, sid));
                }
                RedisRepository.setExpire(uniqueOnlineIdRedisKey, sid + PornConstants.Symbol.SHARP + uniqueOnlineId, PornConstants.RedisKey.EXPIRE_TIME_90_SECONDS);
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
     * 获取站点频道
     *
     * @param sid 站点id
     * @return
     */
    @GetMapping("/channels")
    @ApiOperation(value = "获取站点频道")
    public Result<List<KpnSiteChannelVo>> getChannels(@RequestHeader("sid") Long sid) {
        try {
            List<KpnSiteChannel> channelList = siteChannelService.getAllChannelsBySiteId(sid);

            List<KpnSiteChannelVo> channelVos = channelList.stream().map(kpnSiteChannel -> {
                KpnSiteChannelVo kpnSiteChannelVo = new KpnSiteChannelVo();
                BeanUtil.copyProperties(kpnSiteChannel, kpnSiteChannelVo);
                kpnSiteChannelVo.setName(LanguageUtil.getLanguageName(kpnSiteChannelVo));
                if (kpnSiteChannelVo.getIsStable() && KpnStableChannelEnum.RECOMMEND.getSort().equals(kpnSiteChannelVo.getSort())) {
                    kpnSiteChannelVo.setIsRecommend(true);
                }
                if (kpnSiteChannelVo.getIsStable() && KpnStableChannelEnum.SEARCH.getSort().equals(kpnSiteChannelVo.getSort())) {
                    kpnSiteChannelVo.setIsSearch(true);
                }
                if (kpnSiteChannelVo.getIsStable() && KpnStableChannelEnum.NEWEST.getSort().equals(kpnSiteChannelVo.getSort())) {
                    kpnSiteChannelVo.setIsNewest(true);
                }
                if (kpnSiteChannelVo.getIsStable() && KpnStableChannelEnum.POPULAR.getSort().equals(kpnSiteChannelVo.getSort())) {
                    kpnSiteChannelVo.setIsHottest(true);
                }
                kpnSiteChannelVo.setIcon(externalEndpoint + PornConstants.Symbol.FORWARD_SLASH + kpnSiteChannelVo.getIcon());

                return kpnSiteChannelVo;
            }).collect(Collectors.toList());

            return Result.succeed(channelVos, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    /**
     * 获取站点专题
     *
     * @param sid 站点id
     * @return
     */
    @GetMapping("/topics")
    @ApiOperation(value = "获取站点专题")
    public Result<List<KpnSiteTopicVo>> getTopics(@RequestHeader("sid") Long sid,
                                                  @ApiParam(value = "设备类型 H5/PC", required = true) String deviceType) {
        try {
            List<KpnSiteTopicVo> siteTopicVos = siteTopicService.getBySiteId(sid);
            List<KpnSiteTopicVo> siteTopicVoList = new ArrayList<>();
            for (KpnSiteTopicVo vo:siteTopicVos) {
                if (KpnDeviceTypeEnum.H5.getRemark().equals(deviceType)) {
                    if(KpnSiteTopicComposingEnum.ONE_FALSE.getStatus()==Math.toIntExact(vo.getComposingId())||
                            KpnSiteTopicComposingEnum.TWO_FALSE.getStatus()==Math.toIntExact(vo.getComposingId())||
                            KpnSiteTopicComposingEnum.THREE_FALSE.getStatus()==Math.toIntExact(vo.getComposingId())||
                            KpnSiteTopicComposingEnum.Four_FALSE.getStatus()==Math.toIntExact(vo.getComposingId())){
                        siteTopicVoList.add(vo);
                    }
                }
                else if (KpnDeviceTypeEnum.PC.getRemark().equals(deviceType)) {
                    if(KpnSiteTopicComposingEnum.ONE_FALSE.getStatus()==Math.toIntExact(vo.getComposingId())||
                            KpnSiteTopicComposingEnum.TWO_FALSE.getStatus()==Math.toIntExact(vo.getComposingId())||
                            KpnSiteTopicComposingEnum.THREE_FALSE.getStatus()==Math.toIntExact(vo.getComposingId())||
                            KpnSiteTopicComposingEnum.Four_FALSE.getStatus()==Math.toIntExact(vo.getComposingId())){
                        siteTopicVoList.add(vo);
                    }
                }
            }
            return Result.succeed(siteTopicVoList, "succeed");
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
                adVo.setUrl(externalEndpoint + PornConstants.Symbol.FORWARD_SLASH + adVo.getUrl());
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

    /**
     * 热门演员表-收藏量取前10
     */
    @GetMapping("/actor/top10")
    @ApiOperation(value = "热门演员表Top10")
    public Result<List<KpnActorVo>> getActorList(@RequestHeader("sid") Long sid) {
        try {
            List<KpnActorVo> actorListByFavorites = siteActorService.getActorListByFavorites(sid, KpnSortOrderEnum.DESC.name(), 1, 10).getData();
            return Result.succeed(actorListByFavorites, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    /**
     * 热门VIP推荐
     */
    @GetMapping("/vip/top5")
    @ApiOperation(value = "热门VIP推荐")
    public Result<List<KpnSiteMovieBaseVo>> getVipTop5(@RequestHeader("sid") Long sid) {
        try {
            List<KpnSiteMovieBaseVo> siteMoviesVipTop5 = siteMovieService.searchSiteVipMovieTop5(sid);
            return Result.succeed(siteMoviesVipTop5, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    /**
     * 月播放排行榜
     */
    @GetMapping("/movie/month")
    @ApiOperation(value = "月播放排行榜")
    public Result<List<KpnSiteMovieBaseVo>> getMovieMonth(@RequestHeader("sid") Long sid) {
        try {
            List<KpnSiteMovieBaseVo> kpnSiteMovieBaseVos = rptSiteMovieDateService.searchSiteMovieMonth(sid);
            return Result.succeed(kpnSiteMovieBaseVos, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    /**
     * 站点周搜索排行榜TOP10
     */
    @GetMapping("/search/week")
    @ApiOperation(value = "站点周搜索排行榜TOP10")
    public Result<List<KpnSiteSearchVo>> getSearchWeek(@RequestHeader("sid") Long sid) {
        try {
            List<KpnSiteSearchVo> kpnSiteSearchMonthVos = rptSiteSearchDateService.getSiteSearchWeek(sid);
            return Result.succeed(kpnSiteSearchMonthVos, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    /**
     * 站点总搜索排行榜TOP10
     */
    @GetMapping("/search/total")
    @ApiOperation(value = "站点总搜索排行榜TOP10")
    public Result<List<KpnSiteSearchVo>> getSearchTotal(@RequestHeader("sid") Long sid) {
        try {
            List<KpnSiteSearchVo> kpnSiteSearchMonthVos = rptSiteSearchTotalService.getSiteSearchTotal(sid);
            return Result.succeed(kpnSiteSearchMonthVos, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    /**
     * 关键词搜索影片
     */
    @GetMapping("/search/keywords")
    @ApiOperation(value = "关键词搜索影片")
    public Result<PornPageResult<KpnSiteMovieBaseVo>> searchMovieByKeywords(@ApiParam(value = "站点id", required = true)
                                                                  @RequestHeader("sid") Long sid,
                                                                  @ApiParam("关键词") String keywords,
                                                                  @ApiParam("当前页") Integer currPage,
                                                                  @ApiParam("每页条数") Integer pageSize) {
        try {
            if (StrUtil.isBlank(keywords)) {
                return Result.failed("关键词不能为空");
            }
            if (StrUtil.length(keywords) <= 1) {
                return Result.failed("关键词太短");
            }
            if (StrUtil.length(keywords) > 100) {
                return Result.failed("关键词太长");
            }

            PornPageResult<KpnSiteMovieBaseVo> kpnSiteMovieBaseVos = siteMovieService.searchSiteMovieKeywords(sid, keywords, currPage, pageSize);
            return Result.succeed(kpnSiteMovieBaseVos, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    /**
     * 找片-搜索选项
     *
     * @return
     */
    @GetMapping("/search/options")
    @ApiOperation(value = "找片-搜索选项")
    public Result<Map<String, Map<Integer, String>>> getSearchOptions() {
        try {
            //todo key走语言包返回.
            Map<String, Map<Integer, String>> searchOptionMap = new TreeMap<>();
            searchOptionMap.put(PornConstants.Str.COUNTRY, KpnMovieCountryEnum.getOptions());
            searchOptionMap.put(PornConstants.Str.TYPE, KpnMovieTypeEnum.getOptions());
            searchOptionMap.put(PornConstants.Str.PAY_TYPE, KpnMoviePayTypeEnum.getOptions());
            searchOptionMap.put(PornConstants.Str.SHOOTING, KpnMovieShootingEnum.getOptions());
            searchOptionMap.put(PornConstants.Str.SUBTITLE, KpnMovieSubtitleEnum.getOptions());

            return Result.succeed(searchOptionMap, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    /**
     * 搜索影片库
     */
    @GetMapping("/search/depot")
    @ApiOperation(value = "搜索影片库")
    public Result<PornPageResult<KpnSiteMovieBaseVo>> searchDepot(@RequestHeader("sid") Long sid,
                                                                  @ApiParam("0:找片,1:标签,2:专题,3:频道,4:热门VIP推荐,5:最新,6:最热") Integer from,
                                                                  @ApiParam("标签/专题/频道 ID") Long fromId,
                                                                  @ApiParam("排序字段 HOT:最热,LATEST:最新,DURATION:时长") String sortType,
//                                                                  @ApiParam("找片搜索条件,只在找片时使用") @RequestBody MovieSearchConditionCo searchConditionCo,
                                                                  @ApiParam("找片 国家 -1:全部,0:日本,1:中国大陆,2:中国台湾,3:韩国,4:欧美,5:东南亚,6:其他地区") Integer country,
                                                                  @ApiParam("找片 影片类型: -1:全部,0:无码,1有码") Integer type,
                                                                  @ApiParam("找片 付费类型: -1:全部,0:免费,1:vip") Integer payType,
                                                                  @ApiParam("找片 拍摄类型: -1:全部,0:专业拍摄,1:偷拍,2:自拍,3:其他") Integer shooting,
                                                                  @ApiParam("找片 字幕类型: -1:全部,0:无字幕,1:中文字幕,2:英文字幕,3:中英文字幕,4:其他字幕") Integer subtitle,
                                                                  @ApiParam("排序顺序 0:ASC,1:DESC") Integer sortOrder,
                                                                  @ApiParam("当前页") Integer currPage,
                                                                  @ApiParam("每页条数") Integer pageSize) {
        try {
            if (StrUtil.isBlank(sortType) || !KpnMovieSortTypeEnum.isLegalType(sortType.toUpperCase())) {
                sortType = KpnMovieSortTypeEnum.HOT.getType();
            }

            if (ObjectUtil.isNull(sortOrder) || !KpnSortOrderEnum.isLegalCode(sortOrder)) {
                sortOrder = KpnSortOrderEnum.DESC.getCode();
            }
            //找片
            MovieSearchConditionDto searchConditionDto = null;
            if (KpnSiteMovieSearchFromEnum.isSearch(from)) {
                searchConditionDto = MovieSearchConditionDto.builder().country(country).type(type).payType(payType).shooting(shooting).subtitle(subtitle).build();
            }

            PornPageResult<KpnSiteMovieBaseVo> kpnSiteMoviePageResult = siteMovieService.searchDepot(sid, from, fromId, sortType, sortOrder, searchConditionDto, currPage, pageSize);
            return Result.succeed(kpnSiteMoviePageResult, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    /**
     * 演员列表查询
     *
     * @param sid       站点id
     * @param sortType  排序列
     * @param sortOrder 排序顺序
     * @param currPage  当前页数
     * @param pageSize  每页条数
     */
    @GetMapping("/actor/list")
    @ApiOperation(value = "演员列表")
    public Result<PornPageResult<KpnActorVo>> getActorList(@ApiParam(value = "站点id", required = true) @RequestHeader(value = "sid") Long sid,
                                                           @ApiParam("排序字段 HOT:收藏量,LATEST:最新") String sortType,
                                                           @ApiParam("排序顺序 0:正序,1倒序") Integer sortOrder,
                                                           @ApiParam("当前页数") Integer currPage,
                                                           @ApiParam("每页条数") Integer pageSize) {
        try {
            //排序字段
            if (StrUtil.isBlank(sortType) || !KpnActorSortTypeEnum.isLegalType(sortType)) {
                sortType = KpnActorSortTypeEnum.HOT.getType();
            }

            //排序顺序
            if (ObjectUtil.isNull(sortOrder) || !KpnSortOrderEnum.isLegalCode(sortOrder)) {
                sortOrder = KpnSortOrderEnum.DESC.getCode();
            }

            PornPageResult<KpnActorVo> actorPageVos = null;
            //按收藏量查询
            if (sortType.equalsIgnoreCase(KpnActorSortTypeEnum.HOT.getType())) {
                actorPageVos = siteActorService.getActorListByFavorites(sid, KpnSortOrderEnum.getByCode(sortOrder).name(), currPage, pageSize);
            }
            //按创建时间
            else if (sortType.equalsIgnoreCase(KpnActorSortTypeEnum.LATEST.getType())) {
                actorPageVos = siteActorService.getActorListByCreateTime(sid, KpnSortOrderEnum.getByCode(sortOrder).name(), currPage, pageSize);
            }

            return Result.succeed(actorPageVos, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

//    @ApiOperation("获取图形验证码")
//    @GetMapping("/verifyCode")
//    public void getVerifyCode(HttpServletResponse response,
//                              @ApiParam(value = "图形验证码id") String verifyCodeId) throws IOException {
//        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(150, 50, 4, 50);
//        String code = lineCaptcha.getCode();
//        if (StrUtil.isBlank(verifyCodeId)) {
//            verifyCodeId = UUID.randomUUID().toString();
//        }
//        RedisRepository.setExpire(verifyCodeId, code, PornConstants.RedisKey.EXPIRE_TIME_7_DAYS);
//        response.setHeader(PornConstants.Str.VERIFY_CODE_ID, verifyCodeId);
//        response.setHeader(PornConstants.Str.VERIFY_CODE_SECONDS, String.valueOf(PornConstants.RedisKey.EXPIRE_TIME_7_DAYS));
//        try (ServletOutputStream outputStream = response.getOutputStream()) {
//            lineCaptcha.write(outputStream);
//        } catch (Exception e) {
//            log.error("获取验证码异常:" + e.getMessage(), e);
//        }
//    }

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

        Result tokenResult = uaaService.login(authorization, username, password, AUTHENTICATION_MODE);
        if (tokenResult == null || !tokenResult.getResp_code().equals(CodeEnum.SUCCESS.getCode())) {
            log.error("登录失败: username={}, msg={}", username, tokenResult.getResp_msg());
            return Result.failed(tokenResult.getResp_msg());
        }
        String accessToken = (String) (((LinkedHashMap) tokenResult.getDatas()).get(PornConstants.Str.ACCESS_TOKEN));

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
            if (!username.matches(PornConstants.Reg.CHECK_USERNAME)) {
                return Result.failed("账号不合法,需为6到10位的数字或字母");
            }

            //密码校验
            if (StrUtil.isBlank(password)) {
                return Result.failed("密码不能为空");
            }
            if (!password.matches(PornConstants.Reg.CHECK_PASSWORD)) {
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
            username = kpnSite.getCode() + PornConstants.Symbol.UNDERSCORE + username;
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
            String accessToken = (String) (((LinkedHashMap) tokenResult.getDatas()).get(PornConstants.Str.ACCESS_TOKEN));
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

//    @ApiOperation(value = "token续期,每10分钟调用一次")
//    @PostMapping("/refreshAccessToken")
//    public Result<String> refreshAccessToken() {
//        try {
//            return Result.succeed("succeed");
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//            return Result.failed("failed");
//        }
//    }
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
                siteServeVo.setPcIconUrl(externalEndpoint + PornConstants.Symbol.FORWARD_SLASH + kpnSiteServe.getPcIconUrl());
                siteServeVo.setPcIconUrl(externalEndpoint + PornConstants.Symbol.FORWARD_SLASH + kpnSiteServe.getAppIconUrl());
                return siteServeVo;
            }).collect(Collectors.toList());

            return Result.succeed(siteServeVos, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }


}
