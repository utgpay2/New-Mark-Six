package com.central.marksix.controller;

import cn.hutool.core.util.ObjectUtil;
import com.central.common.annotation.LoginUser;
import com.central.common.model.*;
import com.central.marksix.entity.dto.*;
import com.central.marksix.entity.vo.CategoryVO;
import com.central.marksix.entity.vo.QuizChooseVo;
import com.central.marksix.entity.vo.SiteLotteryVO;
import com.central.common.model.enums.OrderStatusEnum;
import com.central.marksix.entity.vo.WnDataVo;
import com.central.marksix.service.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 彩种表
 *
 * @author zlt
 * @date 2023-05-09 19:57:30
 */
@Slf4j
@RestController
@RequestMapping("/v1/lottery")
@Api(tags = "彩票相关API接口")
public class LotteryController {
    @Autowired
    private ILotteryService lotteryService;
    @Autowired
    private IWnDataService wnDataService;
    @Autowired
    private IQuizService quizService;
    @Autowired
    private IQuizDetailsService quizDetailsService;
    @Autowired
    private IQuizChooseService quizChooseService;
    @Autowired
    private ISiteCategoryLotteryService categoryLotteryService;
    @Autowired
    private IQuizOrdersService siteOrderService;
    @Autowired
    private ILotteryBetCalculationService lotteryBetCalculationService;
    /**
     * 列表
     */
    @ApiOperation(value = "查询彩种列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sortBy", value = "排序方式：1正序(默认)、2倒叙", required = false, dataType = "Integer")
    })
    @GetMapping
    public Result<List<SiteLotteryVO>> list(@ApiIgnore @RequestParam Map<String, Object> params, @ApiIgnore @LoginUser SysUser user) {
        if(null==params){
            params = new HashMap<>();
        }
        params.put("siteId", user.getSiteId());
        return Result.succeed(lotteryService.findListBySiteId(params));
    }


    /**
     * 列表
     */
    @ApiOperation(value = "开奖号码列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sortBy", value = "排序方式：1正序、2倒叙(默认)", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "lotteryId", value = "彩种id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping("/wndatalist")
    public Result<PageResult<WnDataVo>>  wnDatalist(@ApiIgnore @RequestParam Map<String, Object> params) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("lotteryId"))) {
            return Result.failed("彩种id不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("page"))) {
            return Result.failed("分页起始位置不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("limit"))) {
            return Result.failed("分页结束位置不能为空");
        }
        return Result.succeed(wnDataService.findList(params));
    }
    @ApiOperation(value = "根据彩种ID查询最近一期开奖数据")
    @GetMapping("/lastonewndata/{id}")
    public Result lastOneWnData(@PathVariable Integer id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.failed("ID不能为空");
        }
        return Result.succeed(wnDataService.lastOneWnData(id));
    }
    @ApiOperation(value = "查询站点下注分类-分类一类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "siteLotteryId", value = "站点彩种ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "sortBy", value = "排序方式：1正序(默认)、2倒叙", required = false, dataType = "Integer")
    })
    @GetMapping("/listsitecategory")
    public Result<List<CategoryVO>> listSiteCategory(@ApiIgnore @RequestParam Map<String, Object> params) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("siteLotteryId"))) {
            return Result.failed("站点彩种ID不能为空");
        }
        return Result.succeed(categoryLotteryService.findList(params));
    }
    @ApiOperation(value = "查询彩票规则主表-分类二类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "siteCategoryId", value = "站点下注分类一类ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "sortBy", value = "排序方式：1正序(默认)、2倒叙", required = false, dataType = "Integer")
    })
    @GetMapping("/quizlist")
    public Result<List<Quiz>> quizList(@ApiIgnore @RequestParam Map<String, Object> params) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("siteCategoryId"))) {
            return Result.failed("站点下注分类一类ID");
        }
        return Result.succeed(quizService.findList(params));
    }
    @ApiOperation(value = "查询彩票规则主表-分类三类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "quizId", value = "开奖分类二类ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "sortBy", value = "排序方式：1正序(默认)、2倒叙", required = false, dataType = "Integer")
    })
    @GetMapping("/quizdetailslist")
    public Result<List<QuizDetails>> quizDetailsList(@ApiIgnore @RequestParam Map<String, Object> params) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("quizId"))) {
            return Result.failed("开奖分类二类ID");
        }
        return Result.succeed(quizDetailsService.findList(params));
    }
    @ApiOperation(value = "查询彩票规则主表对应明细规则")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "quizDetailsId", value = "开奖种类三类ID", required = true, dataType = "Integer"),
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
     * 计算复式投注
     */
    @ApiOperation(value = "计算复式投注")
    @PostMapping("/duplexLotteryBet")
    public Result duplexLotteryBetNumber(@RequestBody DuplexLotteryBetDto duplexLotteryBetDto) {
        if (ObjectUtil.isEmpty(duplexLotteryBetDto)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(duplexLotteryBetDto.getQuizTitle())) {
            return Result.failed("开奖分类二类名称不能为空");
        }
        if (ObjectUtil.isEmpty(duplexLotteryBetDto.getQuizChooseDtoList())) {
            return Result.failed("号码及其属性不能为空");
        }
        for(QuizChooseDto dto:duplexLotteryBetDto.getQuizChooseDtoList()){
            if (ObjectUtil.isEmpty(dto.getIntroduce())) {
                return Result.failed("号码不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getOdds())||dto.getOdds()==0) {
                return Result.failed("赔率不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getColor())) {
                return Result.failed("波色不能为空");
            }
        }
        return lotteryBetCalculationService.duplexLotteryBetNumber(duplexLotteryBetDto);
    }
    /**
     * 计算胆拖投注
     */
    @ApiOperation(value = "计算胆拖投注")
    @PostMapping("/braveryTowLotteryBet")
    public Result braveryTowLotteryBetNumber(@RequestBody BraveryTowLotteryBetDto braveryTowLotteryBetDto) {
        if (ObjectUtil.isEmpty(braveryTowLotteryBetDto)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(braveryTowLotteryBetDto.getQuizTitle())) {
            return Result.failed("开奖分类二类名称不能为空");
        }
        if (ObjectUtil.isEmpty(braveryTowLotteryBetDto.getBraveryList())) {
            return Result.failed("胆码及其属性不能为空");
        }
        if (ObjectUtil.isEmpty(braveryTowLotteryBetDto.getTowList())) {
            return Result.failed("拖码及其属性不能为空");
        }
        for(QuizChooseDto dto:braveryTowLotteryBetDto.getBraveryList()){
            if (ObjectUtil.isEmpty(dto.getIntroduce())) {
                return Result.failed("胆码号码不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getOdds())||dto.getOdds()==0) {
                return Result.failed("胆码赔率不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getColor())) {
                return Result.failed("胆码波色不能为空");
            }
        }
        for(QuizChooseDto dto:braveryTowLotteryBetDto.getTowList()){
            if (ObjectUtil.isEmpty(dto.getIntroduce())) {
                return Result.failed("拖码号码不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getOdds())||dto.getOdds()==0) {
                return Result.failed("拖码赔率不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getColor())) {
                return Result.failed("拖码波色不能为空");
            }
        }
        return lotteryBetCalculationService.braveryTowLotteryBetNumber(braveryTowLotteryBetDto);
    }
    /**
     * 生肖对碰
     */
    @ApiOperation(value = "生肖对碰")
    @PostMapping("/zodiacBumpLotteryBet")
    public Result zodiacBumpLotteryBetNumber(@RequestBody ZodiacBumpLotteryBetDto zodiacBumpLotteryBetDto) {
        if (ObjectUtil.isEmpty(zodiacBumpLotteryBetDto)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(zodiacBumpLotteryBetDto.getQuizTitle())) {
            return Result.failed("开奖分类二类名称不能为空");
        }
        if (ObjectUtil.isEmpty(zodiacBumpLotteryBetDto.getZodiacOne())) {
            return Result.failed("生肖一不能为空");
        }
        if (ObjectUtil.isEmpty(zodiacBumpLotteryBetDto.getZodiacTwo())) {
            return Result.failed("生肖二不能为空");
        }
        return lotteryBetCalculationService.zodiacBumpLotteryBetNumber(zodiacBumpLotteryBetDto);
    }
    /**
     * 尾数对碰
     */
    @ApiOperation(value = "尾数对碰")
    @PostMapping("/tailBumpLotteryBet")
    public Result tailBumpLotteryBetNumber(@RequestBody TailBumpLotteryBetDto tailBumpLotteryBetDto) {
        if (ObjectUtil.isEmpty(tailBumpLotteryBetDto)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(tailBumpLotteryBetDto.getQuizTitle())) {
            return Result.failed("开奖分类二类名称不能为空");
        }
        if (ObjectUtil.isEmpty(tailBumpLotteryBetDto.getTailOne())) {
            return Result.failed("尾数一不能为空");
        }
        if (ObjectUtil.isEmpty(tailBumpLotteryBetDto.getTailTwo())) {
            return Result.failed("尾数二不能为空");
        }
        return lotteryBetCalculationService.tailBumpLotteryBetNumber(tailBumpLotteryBetDto);
    }
    /**
     * 生尾对碰
     */
    @ApiOperation(value = "生尾对碰")
    @PostMapping("/zodiacTailBumpLotteryBet")
    public Result zodiacTailBumpLotteryBetNumber(@RequestBody ZodiacTailBumpLotteryBetDto zodiacTailBumpLotteryBetDto) {
        if (ObjectUtil.isEmpty(zodiacTailBumpLotteryBetDto)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(zodiacTailBumpLotteryBetDto.getQuizTitle())) {
            return Result.failed("开奖分类二类名称不能为空");
        }
        if (ObjectUtil.isEmpty(zodiacTailBumpLotteryBetDto.getZodiacOne())) {
            return Result.failed("生肖一不能为空");
        }
        if (ObjectUtil.isEmpty(zodiacTailBumpLotteryBetDto.getTailTwo())) {
            return Result.failed("尾数二不能为空");
        }
        return lotteryBetCalculationService.ZodiacTailBumpLotteryBetNumber(zodiacTailBumpLotteryBetDto);
    }

    /**
     * 投注
     */
    @ApiOperation(value = "投注")
    @PostMapping("/bettingorders")
    public Result bettingOrders(@RequestBody List<QuizOrdersDto> ordersDtoList, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(ordersDtoList)) {
            return Result.failed("请求参数不能为空");
        }
        for(QuizOrdersDto dto:ordersDtoList){
            if (ObjectUtil.isEmpty(dto.getPeriods())) {
                return Result.failed("期数波色不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getYear())||dto.getYear()==0) {
                return Result.failed("年份不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getLotteryId())||dto.getLotteryId()==0) {
                return Result.failed("彩种ID不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getSiteLotteryId())||dto.getSiteLotteryId()==0) {
                return Result.failed("站点彩种ID不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getLotteryName())) {
                return Result.failed("站点彩种名称不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getSiteCategoryId())||dto.getSiteCategoryId()==0) {
                return Result.failed("站点下注分类一类ID不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getSiteCategoryName())) {
                return Result.failed("站点下注分类一类名称不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getBettingContent())) {
                return Result.failed("投注内容不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getQuizId())||dto.getQuizId()==0) {
                return Result.failed("开奖分类二类ID不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getQuizTitle())) {
                return Result.failed("开奖分类二类名称不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getQuizDetailsId())||dto.getQuizDetailsId()==0) {
                return Result.failed("开奖分类三类ID不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getQuizDetailsName())) {
                return Result.failed("开奖分类三类名称不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getQuizChooseId())||dto.getQuizChooseId()==0) {
                return Result.failed("开奖规则明细ID不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getQuizIntroduce())) {
                return Result.failed("开奖规则明细名称不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getTotalPrice())||BigDecimal.ZERO.compareTo(dto.getTotalPrice())==1) {
                return Result.failed("订单金额不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getUnits())||dto.getUnits()==0) {
                return Result.failed("注数不能为空");
            }
            if (ObjectUtil.isEmpty(dto.getSubordersList())) {
                if (ObjectUtil.isEmpty(dto.getOdds())||dto.getOdds()==0) {
                    return Result.failed("赔率不能为空");
                }
            }else {
                for (QuizSubordersDto subordersDto:dto.getSubordersList()){
                    if (ObjectUtil.isEmpty(subordersDto.getBettingContent())) {
                        return Result.failed("投注内容不能为空");
                    }
                    if (ObjectUtil.isEmpty(subordersDto.getTotalPrice())||BigDecimal.ZERO.compareTo(subordersDto.getTotalPrice())==1) {
                        return Result.failed("订单金额不能为空");
                    }
                    if (ObjectUtil.isEmpty(subordersDto.getUnits())||subordersDto.getUnits()==0) {
                        return Result.failed("注数不能为空");
                    }
                    if (ObjectUtil.isEmpty(subordersDto.getOdds())||subordersDto.getOdds()==0) {
                        return Result.failed("赔率不能为空");
                    }
                }
            }
        }
        return siteOrderService.bettingOrders(ordersDtoList,user);
    }
    /**
     * 查询我的投注记录
     */
    @ApiOperation(value = "查询我的投注记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sortBy", value = "排序方式：1正序、2倒叙(默认)", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "days", value = "1 今天,2 昨天,3 近七天", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "status", value = "0 全部,1 待开奖,2 已取消,3 中奖,4 未中奖", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping("/queryorders")
    public Result<PageResult<QuizOrders>> queryBettingOrders(@ApiIgnore @RequestParam Map<String, Object> params, @ApiIgnore @LoginUser SysUser user) {
        if (ObjectUtil.isEmpty(params)) {
            return Result.failed("请求参数不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("page"))) {
            return Result.failed("分页起始位置不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("days"))) {
            return Result.failed("时间选项不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("status"))) {
            return Result.failed("状态选项不能为空");
        }
        if (ObjectUtil.isEmpty(params.get("limit"))) {
            return Result.failed("分页结束位置不能为空");
        }
        params.put("siteId",user.getSiteId());
        params.put("memberId",user.getId());
        return Result.succeed(siteOrderService.findList(params));
    }
    @ApiOperation(value = "撤销投注")
    @PostMapping("/cancelbetting")
    public Result cancelBetting(@RequestBody List<Long> ids, @ApiIgnore @LoginUser SysUser user) {
        return siteOrderService.cancelBetting(ids,user);
    }
    /**
     * 列表
     */
    @ApiOperation(value = "订单状态")
    @GetMapping("/orderstatus")
    public Result<Map<Integer, String>> orderStatusList() {
        return Result.succeed(OrderStatusEnum.getOptions());
    }
}
