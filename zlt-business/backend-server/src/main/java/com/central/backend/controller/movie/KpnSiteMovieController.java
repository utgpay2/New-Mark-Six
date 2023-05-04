package com.central.backend.controller.movie;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.central.backend.model.dto.KpnSiteMoviePayTypeDto;
import com.central.backend.model.dto.KpnSiteMovieStatusDto;
import com.central.backend.service.IAsyncService;
import com.central.backend.service.IKpnMovieTagService;
import com.central.backend.service.IKpnSiteMovieService;
import com.central.backend.service.IKpnTagCategoryService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.*;
import com.central.common.model.enums.KpnMovieCountryEnum;
import com.central.common.utils.I18nUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 站点影片
 */
@Slf4j
@RestController
@RequestMapping("/site/movie")
@Api(tags = "站点影片库")
public class KpnSiteMovieController {
    @Autowired
    private IKpnSiteMovieService siteMovieService;

    @Autowired
    private IKpnMovieTagService movieTagService;

    @Autowired
    private IKpnTagCategoryService tagCategoryService;

    @Autowired
    private IAsyncService asyncService;

    @ApiOperation(value = "国家下拉框")
    @GetMapping("/getCountryOptions")
    private Result<Map<Integer, String>> getCountryOptions() {
        Map<Integer, String> optionMap = KpnMovieCountryEnum.getOptions(Boolean.TRUE);

        return Result.succeed(MapUtil.sort(optionMap));
    }

//    @ApiOperation(value = "标签分类下拉框")
//    @GetMapping("/getTagCategoryOptions")
//    private Result<Map<Integer, String>> getTagCategoryOptions() {
//        // TODO 按角色筛选
//        List<KpnTagCategory> tagCategoryList = tagCategoryService.getOptions();
//        Map<Long, String> collect = tagCategoryList.stream().collect(Collectors.toMap(c -> c.getId(), c -> c.getName(), (o, o2) -> o2));
//        collect.put(-1L, I18nUtil.))
//        return Result.succeed(MapUtil.sort(optionMap));
//    }


    /**
     * 列表
     */
    @ApiOperation(value = "查询列表")
    @PostMapping("list")
    public Result<PageResult<KpnSiteMovieVo>> list(@RequestBody QueryMovieCo queryMovieCo) {
        return Result.succeed(siteMovieService.list(queryMovieCo));
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("/{id}")
    public Result findUserById(@PathVariable Long id) {
        KpnSiteMovie model = siteMovieService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
      * 批量发布上架下架
      */
    @ApiOperation(value = "批量发布上架下架")
    @PostMapping("/settingStatus")
    public Result updateBatchStatusById(@RequestBody List<KpnSiteMovieStatusDto> kpnSiteMovieStatusDtoList,@ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(kpnSiteMovieStatusDtoList) || kpnSiteMovieStatusDtoList.size() == 0) {
            return Result.failed("请求参数不能为空");
        }
        siteMovieService.updateBatchStatusById(kpnSiteMovieStatusDtoList, user);

        //add by year 删除站点影片缓存
        List<Long> siteMovieIds = kpnSiteMovieStatusDtoList.stream().map(KpnSiteMovieStatusDto::getId).collect(Collectors.toList());
        asyncService.deleteSiteMovieVoCacheById(siteMovieIds);
        asyncService.deleteSiteActorMovieNumCache(siteMovieIds);
        if (CollUtil.isNotEmpty(siteMovieIds)) {
            KpnSiteMovie siteMovie = siteMovieService.getById(siteMovieIds.get(0));
            if (ObjectUtil.isNotEmpty(siteMovie)) {
                asyncService.openSiteMoviesChangeSwitch(siteMovie.getSiteId());
            }
        }
        return Result.succeed("保存成功");
    }

    @ApiOperation(value = "批量设置付费类型")
    @PostMapping("/settingPayType")
    public Result updateBatchPayTypeById(@RequestBody List<KpnSiteMoviePayTypeDto> kpnSiteMoviePayTypeDtoList, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(kpnSiteMoviePayTypeDtoList) || kpnSiteMoviePayTypeDtoList.size() == 0) {
            return Result.failed("请求参数不能为空");
        }
        siteMovieService.updateBatchPayTypeById(kpnSiteMoviePayTypeDtoList, user);

        //add by year 删除站点影片缓存
        List<Long> siteMovieIds = kpnSiteMoviePayTypeDtoList.stream().map(KpnSiteMoviePayTypeDto::getId).collect(Collectors.toList());
        asyncService.deleteSiteMovieVoCacheById(siteMovieIds);
        return Result.succeed("保存成功");
    }
}
