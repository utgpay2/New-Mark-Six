package com.central.backend.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.central.backend.service.IQuizChooseService;
import com.central.common.annotation.LoginUser;
import com.central.common.model.QuizChoose;
import com.central.common.model.SysUser;
import com.central.backend.model.dto.QuizChooseDto;
import com.central.common.vo.QuizChooseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import com.central.common.model.Result;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 竞猜奖项详情
 *
 * @author zlt
 * @date 2023-05-09 18:42:17
 */
@Slf4j
@RestController
@RequestMapping("/quizchoose")
@Api(tags = "站点彩种分类(四类)")
public class QuizChooseController {
    @Autowired
    private IQuizChooseService quizChooseService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询彩票规则主表对应明细规则")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "siteId", value = "站点id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "siteLotteryId", value = "站点彩种ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "siteCategoryId", value = "站点彩种分类(一类)ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "quizId", value = "站点彩种分类(二类)ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "quizDetailsId", value = "站点彩种分类(三类)ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "sortBy", value = "排序方式：1正序(默认)、2倒叙", required = false, dataType = "Integer")
    })
    @GetMapping("/quizchooselist")
    public Result<List<QuizChooseVo>> quizChooseList(@ApiIgnore @RequestParam Map<String, Object> params) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("quizDetailsId"))) {
            return Result.failed("彩票规则主表id不能为空");
        }
        return Result.succeed(quizChooseService.findList(params));
    }


    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存")
    @PostMapping("/saveOrUpdateQuizChoose")
    public Result saveOrUpdateQuizChoose(@RequestBody QuizChoose quizChoose, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(quizChoose)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(quizChoose.getQuizDetailsId())) {
            return Result.failed("站点彩种分类(三类)ID");
        }
        if (ObjectUtil.isEmpty(quizChoose.getIntroduce())) {
            return Result.failed("标题不能为空");
        }
        if (ObjectUtil.isEmpty(quizChoose.getSort())) {
            return Result.failed("顺序不能为空");
        }
        if (ObjectUtil.isEmpty(quizChoose.getOdds())) {
            return Result.failed("赔率不能为空");
        }
        if (ObjectUtil.isEmpty(quizChoose.getSiteId())) {
            return Result.failed("站点id不能为空");
        }
        if (ObjectUtil.isEmpty(quizChoose.getSiteLotteryId())) {
            return Result.failed("站点彩种ID不能为空");
        }
        if (ObjectUtil.isEmpty(quizChoose.getSiteCategoryId())) {
            return Result.failed("站点彩种分类(一类)不能为空");
        }
        if (ObjectUtil.isEmpty(quizChoose.getQuizId())) {
            return Result.failed("站点彩种分类(二类)id不能为空");
        }
        return quizChooseService.saveOrUpdateQuizChoose(quizChoose,user);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @PostMapping("/deleteQuizChoose")
    public Result deleteQuizChoose(@RequestBody QuizChooseDto quizChooseDto) {
        if (ObjectUtil.isEmpty(quizChooseDto)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(quizChooseDto.getId())) {
            return Result.failed("站点彩种分类(四类)id不能为空");
        }
        if (ObjectUtil.isEmpty(quizChooseDto.getQuizDetailsId())) {
            return Result.failed("站点彩种分类(三类)id不能为空");
        }
        if (ObjectUtil.isEmpty(quizChooseDto.getSiteId())) {
            return Result.failed("站点id不能为空");
        }
        if (ObjectUtil.isEmpty(quizChooseDto.getSiteLotteryId())) {
            return Result.failed("站点彩种ID不能为空");
        }
        if (ObjectUtil.isEmpty(quizChooseDto.getSiteCategoryId())) {
            return Result.failed("站点彩种分类(一类)不能为空");
        }
        if (ObjectUtil.isEmpty(quizChooseDto.getQuizId())) {
            return Result.failed("站点彩种分类(二类)id不能为空");
        }
        return quizChooseService.deleteQuizChoose(quizChooseDto);
    }
}
