package com.central.backend.controller;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.co.KpnSiteTopicSaveCo;
import com.central.backend.co.KpnSiteTopicUpdateCo;
import com.central.backend.service.IKpnSiteMovieService;
import com.central.backend.service.IKpnSiteTopicComposingService;
import com.central.backend.service.IKpnSiteTopicMovieService;
import com.central.backend.service.IKpnSiteTopicService;
import com.central.backend.vo.KpnSiteTopicVo;
import com.central.backend.vo.MovieVo;
import com.central.backend.vo.SiteMovieListVo;
import com.central.common.model.KpnSiteTopicComposing;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Api(tags = "推荐页专题api")
@Validated
@RequestMapping("/siteTopic")
public class KpnSiteTopicController {

    @Autowired
    private IKpnSiteTopicService siteTopicService;

    @Autowired
    private IKpnSiteTopicMovieService siteTopicMovieService;

    @Autowired
    private IKpnSiteMovieService siteMovieService;

    @Autowired
    private IKpnSiteTopicComposingService siteTopicComposingService;

    @ApiOperation("查询排版布局下拉框")
    @ResponseBody
    @GetMapping("/findTopicComposingList")
    public Result findTopicComposingList() {
        List<KpnSiteTopicComposing> list = siteTopicComposingService.findTopicComposingList();
        return Result.succeed(list);
    }


    @ApiOperation("查询推荐页专题列表")
    @ResponseBody
    @GetMapping("/findSiteTopicList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "siteId", value = "站点Id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    public Result<PageResult<KpnSiteTopicVo>> findSiteTopicList(@RequestParam Map<String, Object> params) {
        PageResult<KpnSiteTopicVo> list = siteTopicService.findSiteTopicList(params);
        return Result.succeed(list);
    }

    @ApiOperation("查询专题关联影片列表")
    @ResponseBody
    @GetMapping("/findSiteTopicMovieList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topicId", value = "专题id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    public Result<PageResult<SiteMovieListVo>> findSiteTopicMovieList(@RequestParam Map<String, Object> params) {
        PageResult<SiteMovieListVo> list = siteTopicMovieService.findSiteMovieList(params);
        return Result.succeed(list);
    }





    @ApiOperation(value = "修改状态")
    @GetMapping("/updateEnabledTopic")
    public Result updateEnabledTopic(@Valid @ModelAttribute KpnSiteTopicUpdateCo params) {
        // params.setUpdateBy(sysUser.getUsername());
        Result result = siteTopicService.updateEnabledTopic(params);
        return result;
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/deleteId/{id}")
    public Result deleteId(@PathVariable Long id) {
        boolean b = siteTopicService.deleteId(id);
        return b ? Result.succeed("删除成功") : Result.failed("删除失败");
    }


    @ApiOperation(value = "新增or更新")
    @PostMapping("/saveOrUpdateTopic")
    public Result saveOrUpdateTopic(@RequestBody KpnSiteTopicSaveCo params) {
   /*     if (info.getId() == null) {
            info.setUpdateBy(sysUser.getUsername());
            info.setCreateBy(sysUser.getUsername());
        }else {
            info.setUpdateBy(sysUser.getUsername());
        }*/

        if (ObjectUtil.isEmpty(params.getKpnSiteTopicInfo().getNameZh()) || ObjectUtil.isEmpty(params.getKpnSiteTopicInfo().getNameEn()) ||ObjectUtil.isEmpty(params.getKpnSiteTopicInfo().getNameKh())) {
            return Result.failed("名称不能为空");
        }
        if (ObjectUtil.isEmpty(params.getKpnSiteTopicInfo().getSort())) {
            return Result.failed("排序不能为空");
        }
        if (ObjectUtil.isEmpty(params.getKpnSiteTopicInfo().getComposingId())) {
            return Result.failed("排版布局不能为空");
        }

        if (ObjectUtil.isEmpty(params.getMovieList()) || params.getMovieList().size()==0) {
            return Result.failed("影片不能为空");
        }
        return siteTopicService.saveOrUpdateTopic(params);
    }




    @ApiOperation("查询影片列表")
    @ResponseBody
    @GetMapping("/findMovieList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "siteId", value = "站点编码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "影片名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "movieId", value = "影片id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    public Result<PageResult<MovieVo>> findMovieList(@RequestParam Map<String, Object> params) {
        PageResult<MovieVo> list = siteMovieService.findMovieList(params);
        return Result.succeed(list);
    }

    @ApiOperation("删除专题关联影片")
    @DeleteMapping(value = "/deleteMovieId/{topicMovieId}")
    public Result deleteMovieId(@PathVariable Long topicMovieId) {
        boolean b = siteTopicMovieService.deleteId(topicMovieId);
        return b ? Result.succeed("删除成功") : Result.failed("删除失败");
    }



}
