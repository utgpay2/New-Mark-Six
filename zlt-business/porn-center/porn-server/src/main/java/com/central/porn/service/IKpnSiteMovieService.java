package com.central.porn.service;

import com.central.common.model.KpnSiteMovie;
import com.central.common.service.ISuperService;
import com.central.porn.entity.PornPageResult;
import com.central.porn.entity.dto.MovieSearchConditionDto;
import com.central.porn.entity.vo.KpnMovieVo;
import com.central.porn.entity.vo.KpnSiteMovieBaseVo;

import java.util.List;


public interface IKpnSiteMovieService extends ISuperService<KpnSiteMovie> {

    /**
     * 获取站点影片列表基本信息
     *
     * @param sid      站点id
     * @param movieIds 影片id集合
     * @param isDetail 是否详情
     * @return
     */
    List<KpnSiteMovieBaseVo> getSiteMovieByIds(Long sid, List<Long> movieIds, Boolean isDetail);

    /**
     * 获取影片详情
     *
     * @param sid     站点id
     * @param movieId 影片id
     * @return
     */
    KpnMovieVo getSiteMovieDetail(Long sid, Long movieId);

    /**
     * 获取站点影片信息
     *
     * @param sid     站点id
     * @param movieId 影片id
     * @return
     */
    KpnSiteMovie getSiteMovieVvFavorites(Long sid, Long movieId);

    /**
     * 增加站点影片播放量
     *
     * @param sid     站点id
     * @param topicId 专题id
     * @param movieId 影片id
     */
    Long addSiteMovieVv(Long sid, Long topicId, Long movieId);

    /**
     * 增加站点影片收藏量
     *
     * @param sid     站点id
     * @param userId  会员id
     * @param movieId 影片id
     */
    Long addSiteMovieFavorites(Long sid, Long userId, Long movieId);

    /**
     * 移除站点影片收藏量
     *
     * @param sid     站点id
     * @param movieId 影片id
     */
    Long removeSiteMovieFavorites(Long sid, Long userId, Long movieId);

    /**
     * 获取站点演员数量
     *
     * @param sid     站点id
     * @param actorId 演员id
     * @return
     */
    Long getSiteActorMovieNum(Long sid, Long actorId);


    /**
     * 分页查询站点演员影片
     *
     * @param sid       站点id
     * @param actorId   演员id
     * @param sortType  排序类型 HOT,LATEST,TIME
     * @param sortOrder 排序顺序 0正序,1倒序
     * @param actorId   演员id
     * @param currPage  当前页数
     * @param pageSize  每页条数
     * @return
     */
    PornPageResult<KpnSiteMovieBaseVo> getSiteMovieByActor(Long sid, Long actorId, String sortType, Integer sortOrder, Integer currPage, Integer pageSize);

    /**
     * 补足 1.月播放量排行榜 2.
     *
     * @param sid         站点id
     * @param movieIds    已经存在的影片id
     */
    List<KpnSiteMovieBaseVo> getFillingSiteMovie(Long sid, List<Long> movieIds);


    /**
     * 关键字查询影片
     *
     * @param sid      站点id
     * @param keywords 关键词
     * @param currPage 当前页
     * @param pageSize 每页条数
     * @return
     */
    PornPageResult<KpnSiteMovieBaseVo> searchSiteMovieKeywords(Long sid, String keywords, Integer currPage, Integer pageSize);

    /**
     * 查询影片库
     *
     * @param sid                站点id
     * @param from               0:标签,1:专题,2:频道,3:热门VIP推荐
     * @param fromId             来源id
     * @param sortType           排序字段 HOT:最热,LATEST:最新,TIME:时长
     * @param sortOrder          排序顺序 0:ASC,1:DESC
     * @param searchConditionDto 找片条件
     * @param currPage           当前页
     * @param pageSize           每页条数
     */
    PornPageResult<KpnSiteMovieBaseVo> searchDepot(Long sid, Integer from, Long fromId, String sortType, Integer sortOrder, MovieSearchConditionDto searchConditionDto, Integer currPage, Integer pageSize);

    /**
     * 获取站点影片id
     *
     * @param sid 站点id
     * @return
     */
    List<Long> getSiteMovieIds(Long sid);

    /**
     * 获取站点影片id,按播放量排序
     *
     * @param sid
     * @return
     */
    List<Long> getSiteMovieIdsOrderByVv(Long sid, Boolean isVip);

    /**
     * 获取vip推荐
     * @param sid 站点id
     * @return
     */
    List<KpnSiteMovieBaseVo> searchSiteVipMovieTop5(Long sid);

    /**
     * 站点影片播放量-add
     *
     * @param sid     站点id
     * @param movieId 影片id
     */
    void addSiteMovieVv(Long sid, Long movieId);

    /**
     * 站点影片收藏量-加1
     *
     * @param sid     站点id
     * @param movieId 影片id
     */
    void addSiteMovieFavorites(Long sid, Long movieId);

    /**
     * 站点影片收藏量-减1
     *
     * @param sid     站点id
     * @param movieId 影片id
     */
    void removeSiteMovieFavorites(Long sid, Long movieId);
}
