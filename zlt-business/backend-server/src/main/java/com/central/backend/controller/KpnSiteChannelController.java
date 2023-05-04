package com.central.backend.controller;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.co.KpnSiteChannelUpdateCo;
import com.central.backend.service.IKpnSiteChannelService;
import com.central.backend.service.IKpnTagService;
import com.central.backend.vo.CategoryVo;
import com.central.backend.vo.KpnTagVo;
import com.central.common.annotation.LoginUser;
import com.central.common.constant.PornConstants;
import com.central.common.model.KpnSiteChannel;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@Api(tags = "频道栏目配置api")
@Validated
@RequestMapping("/siteChannel")
public class KpnSiteChannelController {

    @Autowired
    private IKpnSiteChannelService siteChannelService;

    @Autowired
    private IKpnTagService kpnTagService;


    @Value("${zlt.minio.externalEndpoint}")
    private String externalEndpoint;


    @ApiOperation("查询频道栏目列表")
    @ResponseBody
    @GetMapping("/findSiteChannelList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "siteId", value = "站点Id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    public Result<PageResult<KpnSiteChannel>> findSiteChannelList(@RequestParam Map<String, Object> params) {
        PageResult<KpnSiteChannel> list = siteChannelService.findSiteChannelList(params);
        List<KpnTagVo> tagList = kpnTagService.findTagList(params);
        Map<Long, KpnTagVo> map = tagList.stream().collect(Collectors.toMap(KpnTagVo::getId, (p) -> p));
        list.getData().stream().forEach(info->{
            if (info.getIcon()!=null){
                info.setIcon(externalEndpoint + PornConstants.Symbol.FORWARD_SLASH + info.getIcon());
            }
            //关联标签
            if (info.getTags()!=null){
               String[] tage =info.getTags().split(",");
               StringBuilder tageName=new StringBuilder();
                for (int i = 0; i < tage.length; i++) {
                    KpnTagVo kpnTagInfo = map.get(Long.valueOf(tage[i]));
                    if (kpnTagInfo!=null){
                        String character= i==0 ? "" : "/";
                        tageName.append( character + kpnTagInfo.getNameZh() );
                    }
                }
                info.setTagsName(tageName.toString());
            }
        });
        return Result.succeed(list);
    }



    @ApiOperation(value = "新增or更新频道")
    @PostMapping(value = "/saveOrUpdateSiteChannel")
    public Result saveOrUpdateSiteChannel(@RequestBody  KpnSiteChannel siteChannel, @LoginUser SysUser sysUser) {

        if (sysUser!=null) {
            if (siteChannel.getId() == null) {
                siteChannel.setUpdateBy(sysUser.getUsername());
                siteChannel.setCreateBy(sysUser.getUsername());
            }else {
                siteChannel.setUpdateBy(sysUser.getUsername());
            }
        }

        if (ObjectUtil.isEmpty(siteChannel.getNameZh()) || ObjectUtil.isEmpty(siteChannel.getNameEn()) || ObjectUtil.isEmpty(siteChannel.getNameKh())) {
            return Result.failed("名称不能为空");
        }
        if (ObjectUtil.isEmpty(siteChannel.getSort())) {
            return Result.failed("排序不能为空");
        }
        if (ObjectUtil.isEmpty(siteChannel.getIcon())) {
            return Result.failed("图标不能为空");
        }
        return  siteChannelService.saveOrUpdateSiteChannel(siteChannel);
    }



    @ApiOperation(value = "修改状态")
    @GetMapping("/updateEnabledChannel")
    public Result updateEnabledChannel(@Valid @ModelAttribute KpnSiteChannelUpdateCo params, @LoginUser SysUser sysUser) {
        if (sysUser!=null) {
            params.setUpdateBy(sysUser.getUsername());
        }
        Result result = siteChannelService.updateEnabledChannel(params);
        return result;
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/deleteId/{id}")
    public Result deleteId(@PathVariable Long id) {
        boolean b = siteChannelService.deleteId(id);
        return b ? Result.succeed("删除成功") : Result.failed("删除失败");
    }

    @ApiOperation("关联标签")
    @ResponseBody
    @GetMapping("/findTagList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "siteId", value = "站点Id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "categoryId", value = "分类id", required = true, dataType = "Long"),
    })
    public Result<List<KpnTagVo>> findTagList(@RequestParam Map<String, Object> params) {
        List<KpnTagVo> list = kpnTagService.findTagList(params);
        return Result.succeed(list);
    }

    @ApiOperation("分类")
    @ResponseBody
    @GetMapping("/findTagCategoryList")
    public Result<List<CategoryVo>> findTagCategoryList() {
        List<CategoryVo> list = kpnTagService.findTagCategoryList();
        return Result.succeed(list);
    }

}
