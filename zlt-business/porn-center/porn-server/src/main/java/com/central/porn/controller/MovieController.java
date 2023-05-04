package com.central.porn.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.annotation.LoginUser;
import com.central.common.model.KpnSiteUserActorFavorites;
import com.central.common.model.KpnSiteUserMovieFavorites;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.common.model.enums.*;
import com.central.porn.entity.PornPageResult;
import com.central.porn.entity.vo.KpnActorVo;
import com.central.porn.entity.vo.KpnMovieVo;
import com.central.porn.entity.vo.KpnSiteMovieBaseVo;
import com.central.porn.enums.KpnMovieSortTypeEnum;
import com.central.porn.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 影片相关
 */
@Slf4j
@RestController
@RequestMapping("/v1/movie")
@Api(tags = "影片相关api接口")
public class MovieController {

    @Autowired
    private IKpnSiteUserMovieHistoryService userMovieHistoryService;

    @Autowired
    private IKpnSiteUserMovieFavoritesService userMovieFavoritesService;

    @Autowired
    private IKpnSiteUserActorFavoritesService userActorFavoritesService;

    @Autowired
    private IKpnSiteMovieService siteMovieService;

    @Autowired
    private IKpnSiteActorService siteActorService;

    /**
     * 进入播放页,获取影片详细信息
     *
     * @return
     */
    @GetMapping("/detail/info")
    @ApiOperation(value = "播放页-获取影片详情")
    public Result<KpnMovieVo> detailInfo(@RequestHeader("sid") Long sid,
                                         @ApiIgnore @LoginUser SysUser sysUser,
                                         @ApiParam("专题id(从专题进入影片详情需要带该参数记录专题访问次数,其他途径进入影片详情设置null)")
                                                 Long topicId,
                                         @ApiParam("影片id") Long movieId) {
        try {
            //站点影片播放次数
            Long siteMovieVv = siteMovieService.addSiteMovieVv(sid, topicId, movieId);

            //获取影片详情
            KpnMovieVo kpnMovieVo = siteMovieService.getSiteMovieDetail(sid, movieId);
            kpnMovieVo.setVv(siteMovieVv);

            //已经登录会员
            if (ObjectUtil.isNotEmpty(sysUser)) {
                Long userId = sysUser.getId();
                //更新浏览历史
                userMovieHistoryService.addUserMovieHistory(userId, movieId);
                //影片收藏状态
                KpnSiteUserMovieFavorites userMovieFavorites = userMovieFavoritesService.getUserMovieFavorites(userId, movieId);
                if (ObjectUtil.isNotEmpty(userMovieFavorites)) {
                    kpnMovieVo.setHasFavor(true);
                }
            }

            return Result.succeed(kpnMovieVo, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    /**
     * 获取演员详细信息
     *
     * @return 获取演员详情
     */
    @GetMapping("/detail/actor")
    @ApiOperation(value = "获取影片演员详情")
    public Result<KpnActorVo> movieActorInfo(@RequestHeader(value = "sid") Long sid, @ApiIgnore @LoginUser SysUser sysUser, Long actorId) {
        try {
            KpnActorVo kpnActorVo = siteActorService.getKpnActorVo(sid, actorId);
            //已经登录会员
            if (ObjectUtil.isNotEmpty(sysUser)) {
                //演员收藏状态
                KpnSiteUserActorFavorites userActorFavorites = userActorFavoritesService.getUserActorFavorites(sysUser.getId(), kpnActorVo.getId());
                if (ObjectUtil.isNotEmpty(userActorFavorites)) {
                    kpnActorVo.setHasFavor(true);
                }
            }
            return Result.succeed(kpnActorVo, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

    /**
     * 获取演员影片列表信息
     *
     * @param sid      站点id
     * @param actorId  演员id
     * @param currPage 当前页数
     * @param pageSize 每页条数
     * @return 获取演员详情
     */
    @GetMapping("/actor/movies")
    @ApiOperation(value = "获取演员影片列表信息")
    public Result<PornPageResult<KpnSiteMovieBaseVo>> getActorMovies(@ApiParam("站点id") @RequestHeader(value = "sid") Long sid,
                                                                     @ApiParam("演员id") Long actorId,
                                                                     @ApiParam("排序字段 HOT:收藏量,LATEST:上架时间,DURATION:影片时长") String sortType,
                                                                     @ApiParam("排序顺序 0:正序,1倒序") Integer sortOrder,
                                                                     @ApiParam("当前页数") Integer currPage,
                                                                     @ApiParam("每页条数") Integer pageSize) {
        try {
            //排序字段
            if (StrUtil.isBlank(sortType) || !KpnMovieSortTypeEnum.isLegalType(sortType)) {
                sortType = KpnMovieSortTypeEnum.HOT.getType();
            }

            //排序序号
            if (ObjectUtil.isNull(sortOrder) || !KpnSortOrderEnum.isLegalCode(sortOrder)) {
                sortOrder = KpnSortOrderEnum.DESC.getCode();
            }
            PornPageResult<KpnSiteMovieBaseVo> siteMovieBaseVoPage = siteMovieService.getSiteMovieByActor(sid, actorId, sortType, sortOrder, currPage, pageSize);
            return Result.succeed(siteMovieBaseVoPage, "succeed");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("failed");
        }
    }

}
