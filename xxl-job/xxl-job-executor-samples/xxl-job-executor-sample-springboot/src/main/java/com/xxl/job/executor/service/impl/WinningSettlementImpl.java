package com.xxl.job.executor.service.impl;

import com.central.common.model.*;
import com.central.common.model.enums.MbChangeTypeEnum;
import com.central.common.model.enums.OrderStatusEnum;
import com.central.common.model.enums.StatusEnum;
import com.central.common.utils.DateUtil;
import com.central.common.utils.SnowflakeIdWorker;
import com.central.common.vo.SiteLotteryVo;
import com.xxl.job.executor.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 获奖结算定时任务
 */
@Slf4j
@Service
public class WinningSettlementImpl implements IWinningSettlementService{
    @Autowired
    private ILotteryService lotteryService;
    @Autowired
    private IQuizOrdersService siteOrderService;
    @Autowired
    private IWnDataService wnDataService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private IMoneyLogService moneyLogService;
    @Autowired
    private INumberAttributesService numberAttributesService;
    @Autowired
    private IQuizSubordersService quizSubordersService;
    /**
     * 模拟新增千万级订单数据
     */
    @Override
    public void add(){
        System.out.println("开始时间："+ DateUtil.dateToHHmmss(new Date()));
        for (int i=0;i<2000;i++){
            System.out.println("插入一万条开始时间："+ DateUtil.dateToHHmmss(new Date()));
            List<QuizOrders> ordersList = new ArrayList<>();
            for(int j=0;j<10000;j++){

                QuizOrders quizOrders = new QuizOrders();
                quizOrders.setPeriods("2023008");//期数
                quizOrders.setYear(2023);//年份
                quizOrders.setSiteLotteryId(1L);//商户彩种ID
                quizOrders.setLotteryName("香港六合彩");//商户彩种名称
                quizOrders.setSiteCategoryId(374L);//商户下注分类ID
                quizOrders.setQuizId(1L);//开奖规则主表ID
                quizOrders.setQuizTitle("号码");//开奖规则主表标题
                quizOrders.setQuizChooseIds("1L");//开奖规则明细ID
                quizOrders.setQuizIntroduces("01");//开奖规则明细名称
                quizOrders.setTotalPrice(BigDecimal.valueOf(100));//订单金额
                quizOrders.setUnits(1);//注数
                quizOrders.setOdds(BigDecimal.valueOf(48.8));//赔率

                String orderSn = SnowflakeIdWorker.createOrderSn();//订单号
                quizOrders.setOrderNo(orderSn);
                quizOrders.setSiteId(1L);//商户id
                quizOrders.setSiteCode("mks_site01");//商户编码
                quizOrders.setSiteName("总站");//商户名称
                quizOrders.setMemberId(143L);//会员ID
                quizOrders.setUserName("mks_site02_admin");//用户名
                quizOrders.setParentId(2L);//上级id
                quizOrders.setParentName("admin");//上级账号
                quizOrders.setCreateTime(new Date());
                quizOrders.setCreateBy("mks_site02_admin");
                quizOrders.setUpdateTime(new Date());
                quizOrders.setUpdateBy("mks_site02_admin");
                ordersList.add(quizOrders);
            }
            siteOrderService.saveOrUpdateQuizOrdersBatch(ordersList);
            System.out.println("插入一万条结束时间："+ DateUtil.dateToHHmmss(new Date()));
        }
        System.out.println("结束时间："+ DateUtil.dateToHHmmss(new Date()));
    }
    /**
     * 结算
     */
    @Transactional(rollbackFor={RuntimeException.class,Exception.class})
    public void winningSettlement(List<QuizOrders> list,
                              String one,String two,String three,String four,String five,String six,
                              String seven,int seven_ones,int seven_tens,
                              List<String> bigSingle,List<String> bigDouble,List<String> smallSingle,List<String> smallDouble,
                              String oneZodiac,String twoZodiac,String threeZodiac,String fourZodiac,String fiveZodiac,String sixZodiac,String sevenZodiac,
                              List<String> jinList,List<String> muList,List<String> shuiList,List<String> huoList,List<String> tuList,
                              List<String> shuList,List<String> niuList,List<String> huList,List<String> tuzList,List<String> longList,List<String> sheList,
                              List<String> maList,List<String> yanList,List<String> houList,List<String> jiList,List<String> gouList,List<String> zhuList,
                              List<String> hongboList,List<String> lvboList,List<String> lanboList,
                              List<String> jiaList,List<String> yeList,List<String> skyList,List<String> landList,List<String> beforeList,List<String> afterList,
                              List<String> winZodiacList,
                              int bigcount,int smallcount,int doublecount,int singlecount,
//                                  String[] head0,String[] head1,String[] head2,String[] head3,String[] head4,
//                                  String[] tail0,String[] tail1,String[] tail2,String[] tail3,String[] tail4,String[] tail5,String[] tail6,String[] tail7,String[] tail8,String[] tail9,
                              String[] wnTail,
                              String[] wnNumbersSix,
                              String[] wnNumbers){
        List<QuizOrders> ordersList = new ArrayList<>();//总订单
        List<QuizSuborders> subordersList = new ArrayList<>();//子订单
        List<MoneyLog> moneyLogList = new ArrayList<>();
        for (QuizOrders quizOrders: list){
            Map<String, Object> params = new HashMap<>();
            params.put("orderNo",quizOrders.getOrderNo());
            List<QuizSuborders> quizSubordersList = quizSubordersService.findList(params);
            SysUser sysUser = userService.getSysUserById(quizOrders.getMemberId());
            BigDecimal currentBalance = sysUser.getMBalance();//用户当前余额
            quizOrders.setUpdateTime(new Date());
            quizOrders.setSettlementTime(new Date());//结算时间
            quizOrders.setUpdateBy(sysUser.getUsername());
            BigDecimal winAmount = BigDecimal.ZERO;
            BigDecimal winLoseAmount = BigDecimal.ZERO;
            if ("特码".equals(quizOrders.getSiteCategoryName())) {//分类一类
                if("特码A".equals(quizOrders.getQuizTitle())) {//分类二类
                    if ("特码A".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        if(null!=quizSubordersList&&quizSubordersList.size()>0){
                            for(QuizSuborders quizSuborders : quizSubordersList){
                                if (seven.equals(quizSuborders.getBettingContent())) {//购买的号码相同为中奖
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                }else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        }else {
                            if (seven.equals(quizOrders.getQuizIntroduces())) {//购买的号码相同为中奖
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                            }else {
                                winLoseAmount = quizOrders.getTotalPrice().negate();
                            }
                        }

                    }
                }
                if("特码B".equals(quizOrders.getQuizTitle())) {//分类二类
                    if ("特码B".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        if(null!=quizSubordersList&&quizSubordersList.size()>0){
                            for(QuizSuborders quizSuborders : quizSubordersList){
                                if (seven.equals(quizSuborders.getBettingContent())) {//购买的号码相同为中奖
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                }else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        }else {
                            if (seven.equals(quizOrders.getQuizIntroduces())) {//购买的号码相同为中奖
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                            }else {
                                winLoseAmount = quizOrders.getTotalPrice().negate();
                            }
                        }
                    }
                }
                if("两面".equals(quizOrders.getQuizTitle())) {//分类二类
                    if ("两面".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        if ("特小".equals(quizOrders.getQuizIntroduces())) {//小：特别码小于或等于24
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (Integer.parseInt(seven) <= 24){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (Integer.parseInt(seven) <= 24) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("特大".equals(quizOrders.getQuizIntroduces())) {//大：特别码大于或等于25
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (Integer.parseInt(seven) >= 25){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (Integer.parseInt(seven) >= 25) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("特单".equals(quizOrders.getQuizIntroduces())) {//单：特别码为奇数
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (Integer.parseInt(seven) % 2 != 0){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (Integer.parseInt(seven) % 2 != 0) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("特双".equals(quizOrders.getQuizIntroduces())) {//双：特别码为偶数
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (Integer.parseInt(seven) % 2 == 0){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (Integer.parseInt(seven) % 2 == 0) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }

                        }
                        if ("特大双".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (bigDouble.contains(seven)){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (bigDouble.contains(seven)){
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("特小双".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (smallDouble.contains(seven)){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (smallDouble.contains(seven)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }

                        }
                        if ("特大单".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (bigSingle.contains(seven)){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizOrders.getTotalPrice();
                                        quizSuborders.setWinMount(quizOrders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (bigSingle.contains(seven)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("特小单".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (smallSingle.contains(seven)){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizOrders.getTotalPrice();
                                        quizSuborders.setWinMount(quizOrders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (smallSingle.contains(seven)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("特尾大".equals(quizOrders.getQuizIntroduces())) {//尾大：大于等于5为大
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (seven_ones <= 5){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizOrders.getTotalPrice();
                                        quizSuborders.setWinMount(quizOrders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (seven_ones <= 5) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("特尾小".equals(quizOrders.getQuizIntroduces())) {//尾小：小于等于4为小
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (seven_ones <= 4){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizOrders.getTotalPrice();
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (seven_ones <= 4) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("特家肖".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (jiaList.contains(sevenZodiac)){
                                        winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    }else {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (jiaList.contains(sevenZodiac)) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }
                        }
                        if ("特野肖".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (yeList.contains(sevenZodiac)){
                                        winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    }else {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (yeList.contains(sevenZodiac)) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }
                        }
                        if ("特前肖".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (beforeList.contains(sevenZodiac)){
                                        winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    }else {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (beforeList.contains(sevenZodiac)) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }
                        }
                        if ("特后肖".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (afterList.contains(sevenZodiac)){
                                        winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    }else {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (afterList.contains(sevenZodiac)) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }
                        }
                        if ("特天肖".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (skyList.contains(sevenZodiac)){
                                        winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    }else {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (skyList.contains(sevenZodiac)) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }
                        }
                        if ("特地肖".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (landList.contains(sevenZodiac)){
                                        winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    }else {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (landList.contains(sevenZodiac)) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }
                        }
                    }
                }
            }else if ("生肖".equals(quizOrders.getSiteCategoryName())) {//分类一类
                if ("正肖".equals(quizOrders.getQuizTitle())) {//分类二类
                    if ("正肖".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        //对开奖的六个正码对应的生肖进行投注。 【最大赔率：1.9】
                        //范例： 投注号码：正肖-猪开奖号码：03 08 41 32 21 38+25，开奖号码38在下注生肖范围内，故中奖。
                        //中奖说明：当期开奖的前6个号码（不含特码，不分先后顺序），其中有一个号码在投注的生肖范围内即算中奖。如果有N个号码在某一投注的生肖范围内，中奖金额=本金”（赔率-1）“中奖次数+本金。
                        String zodicStr = oneZodiac+twoZodiac+threeZodiac+fourZodiac+fiveZodiac+sixZodiac;
                        if(null!=quizSubordersList&&quizSubordersList.size()>0){
                            for(QuizSuborders quizSuborders : quizSubordersList){
                                BigDecimal amount = zhengxiao(quizSuborders,zodicStr, quizOrders.getQuizIntroduces());
                                if(BigDecimal.ZERO.compareTo(amount)==0){
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }else {
                                    winAmount = winAmount.add(amount);
                                    winLoseAmount = winLoseAmount.add(amount.subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(amount);
                                    quizSuborders.setWinLoseAmount(amount.subtract(quizSuborders.getTotalPrice()));
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        }else {
                            BigDecimal amount = zhengxiao(quizOrders,zodicStr, quizOrders.getQuizIntroduces());
                            if(BigDecimal.ZERO.compareTo(amount)==0) {
                                winAmount = amount;
                                winLoseAmount = amount.subtract(quizOrders.getTotalPrice());
                            }else {
                                winLoseAmount = quizOrders.getTotalPrice().negate();
                            }
                        }

                    }
                }else if("特肖".equals(quizOrders.getQuizTitle())) {//分类二类
                    if ("特肖".equals(quizOrders.getQuizDetailsName())) {//分类三类 生肖
                        //鼠、牛、虎、兔、龙、蛇、马、羊、猴、鸡、狗、猪
                        if(null!=quizSubordersList&&quizSubordersList.size()>0){
                            for(QuizSuborders quizSuborders : quizSubordersList){
                                if(sevenZodiac.equals(quizOrders.getQuizIntroduces())){
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                }else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        }else {
                            if(sevenZodiac.equals(quizOrders.getQuizIntroduces())) {
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                            }else {
                                winLoseAmount = quizOrders.getTotalPrice().negate();
                            }
                        }

                    }
                }else if("平特一肖".equals(quizOrders.getQuizTitle())) {//分类二类
                    //当期开奖的全部号码(前6个号码和特别码)，其中只要有一个号码在投注的生肖范围则中奖；没有一个球号在投注的生肖范围内，则不中奖;多个球号在投注生肖范围内，则中奖；但奖金不倍增，派彩只派一次，即不论同生肖号码出现一个或多个号码都只派一次。
                    if ("平特一肖".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        String zodicStr = oneZodiac+twoZodiac+threeZodiac+fourZodiac+fiveZodiac+sixZodiac+sevenZodiac;
                        if(null!=quizSubordersList&&quizSubordersList.size()>0){
                            for(QuizSuborders quizSuborders : quizSubordersList){
                                if(zodicStr.indexOf(quizOrders.getQuizIntroduces())!=-1){
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                }else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        }else {
                            if(zodicStr.indexOf(quizOrders.getQuizIntroduces())!=-1) {
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                            }else {
                                winLoseAmount = quizOrders.getTotalPrice().negate();
                            }
                        }
                    }
                }else if("合肖".equals(quizOrders.getQuizTitle())) {//分类二类
                    //选择2-11个生肖，所选生肖为开奖号码的特别码对应的生肖，即为中奖 如投注方案为猴、虎，开奖号码为01(兔),02(虎),03(牛),04(鼠),05(猪),06(狗) + 08(猴)，即中合肖。
                    if(null!=quizSubordersList&&quizSubordersList.size()>0){
                        for(QuizSuborders quizSuborders : quizSubordersList){
                            if(quizOrders.getQuizIntroduces().indexOf(sevenZodiac)!=-1){
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                            }else {
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                quizSuborders.setWinMount(BigDecimal.ZERO);
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                            }
                            quizSuborders.setUpdateTime(new Date());
                            subordersList.add(quizSuborders);
                        }
                    }else {
                        if(quizOrders.getQuizIntroduces().indexOf(sevenZodiac)!=-1) {
                            winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                        }else {
                            winLoseAmount = quizOrders.getTotalPrice().negate();
                        }
                    }

                }else if("连肖".equals(quizOrders.getQuizTitle())) {//分类二类
                    if ("二肖连中".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        //选择2个或2个以上生肖，所选生肖与开奖号码所对应的生肖一致，即中奖；如投注方案为牛、鼠，开奖号码为：01(虎),02(牛),03(鼠),04(猪),05(狗),06(鸡) + 07(猴)，即中二连肖。
                        if(null!=quizSubordersList&&quizSubordersList.size()>0){
                            for(QuizSuborders quizSuborders : quizSubordersList){
                                String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                                if (winZodiacList.contains(bettingNumberList[0])
                                        &&winZodiacList.contains(bettingNumberList[1])){
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                }else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        }else {
                            String[] bettingNumberList = quizOrders.getQuizIntroduces().split(",");//投注转换数组
                            if (winZodiacList.contains(bettingNumberList[0])
                                    &&winZodiacList.contains(bettingNumberList[1])){
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                            }else {
                                winLoseAmount = quizOrders.getTotalPrice().negate();
                            }
                        }

                    }
                    if ("三肖连中".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        //选择3个或3个以上生肖，所选生肖与开奖号码所对应的生肖一致，即中奖；如投注方案为牛、鼠、猪，开奖号码为：01(虎),02(牛),03(鼠),04(猪),05(狗),06(鸡) + 07(猴)，即中三连肖。
                        if(null!=quizSubordersList&&quizSubordersList.size()>0){
                            for(QuizSuborders quizSuborders : quizSubordersList){
                                String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                                if (winZodiacList.contains(bettingNumberList[0])
                                        &&winZodiacList.contains(bettingNumberList[1])
                                        &&winZodiacList.contains(bettingNumberList[2])){
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                }else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        }else {
                            String[] bettingNumberList = quizOrders.getQuizIntroduces().split(",");//投注转换数组
                            if (winZodiacList.contains(bettingNumberList[0])
                                    &&winZodiacList.contains(bettingNumberList[1])
                                    &&winZodiacList.contains(bettingNumberList[2])){
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                            }else {
                                winLoseAmount = quizOrders.getTotalPrice().negate();
                            }
                        }
                    }
                    if ("四肖连中".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        //选择4个或4个以上生肖，所选生肖与开奖号码所对应的生肖一致，即中奖；如投注方案为牛、鼠、猪、狗，开奖号码为：01(虎),02(牛),03(鼠),04(猪),05(狗),06(鸡) + 07(猴)，即中四连肖。
                        if(null!=quizSubordersList&&quizSubordersList.size()>0){
                            for(QuizSuborders quizSuborders : quizSubordersList){
                                String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                                if (winZodiacList.contains(bettingNumberList[0])
                                        &&winZodiacList.contains(bettingNumberList[1])
                                        &&winZodiacList.contains(bettingNumberList[2])
                                        &&winZodiacList.contains(bettingNumberList[3])){
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                }else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        }else {
                            String[] bettingNumberList = quizOrders.getQuizIntroduces().split(",");//投注转换数组
                            if (winZodiacList.contains(bettingNumberList[0])
                                    &&winZodiacList.contains(bettingNumberList[1])
                                    &&winZodiacList.contains(bettingNumberList[2])
                                    &&winZodiacList.contains(bettingNumberList[3])){
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                            }else {
                                winLoseAmount = quizOrders.getTotalPrice().negate();
                            }
                        }
                    }
                    if ("五肖连中".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        //选择5个或5个以上生肖，所选生肖与开奖号码所对应的生肖一致，即中奖；如投注方案为牛、鼠、猪、狗、鸡，开奖号码为：01(虎),02(牛),03(鼠),04(猪),05(狗),06(鸡) + 07(猴)，即中五连肖。
                        if(null!=quizSubordersList&&quizSubordersList.size()>0){
                            for(QuizSuborders quizSuborders : quizSubordersList){
                                String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                                if (winZodiacList.contains(bettingNumberList[0])
                                        &&winZodiacList.contains(bettingNumberList[1])
                                        &&winZodiacList.contains(bettingNumberList[2])
                                        &&winZodiacList.contains(bettingNumberList[3])
                                        &&winZodiacList.contains(bettingNumberList[4])){
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                }else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        }else {
                            String[] bettingNumberList = quizOrders.getQuizIntroduces().split(",");//投注转换数组
                            if (winZodiacList.contains(bettingNumberList[0])
                                    &&winZodiacList.contains(bettingNumberList[1])
                                    &&winZodiacList.contains(bettingNumberList[2])
                                    &&winZodiacList.contains(bettingNumberList[3])
                                    &&winZodiacList.contains(bettingNumberList[4])){
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                            }else {
                                winLoseAmount = quizOrders.getTotalPrice().negate();
                            }
                        }
                    }
                }
            } else if ("色波".equals(quizOrders.getSiteCategoryName())) {//分类一类
                if("半波".equals(quizOrders.getQuizTitle())) {//分类二类
                    if("大小单双".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        //以特码色波和特码单双大小为一个投注组合，当开出特码符合投注组合，即视为中奖。若开出特码为49号时，所有投注半波任意一个组合视为和局，其余情况视为不中奖。
                        if ("红大".equals(quizOrders.getQuizIntroduces())) {//特别码大于或等于25
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (hongboList.contains(seven) && Integer.parseInt(seven) >= 25){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (hongboList.contains(seven)&&Integer.parseInt(seven) >= 25) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("红小".equals(quizOrders.getQuizIntroduces())) {//特别码小于或等于24
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (hongboList.contains(seven) && Integer.parseInt(seven) <= 24){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (hongboList.contains(seven)&&Integer.parseInt(seven) <= 24) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }

                        }
                        if ("红单".equals(quizOrders.getQuizIntroduces())) {//特别码为奇数
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (hongboList.contains(seven) && Integer.parseInt(seven) % 2 != 0){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (hongboList.contains(seven)&&(Integer.parseInt(seven) % 2 != 0)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("红双".equals(quizOrders.getQuizIntroduces())) {//特别码为偶数
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (hongboList.contains(seven) && Integer.parseInt(seven) % 2 == 0){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (hongboList.contains(seven)&&(Integer.parseInt(seven) % 2 == 0)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("红合单".equals(quizOrders.getQuizIntroduces())) {//和值为奇数
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (hongboList.contains(seven) && (seven_ones + seven_tens) % 2 != 0){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (hongboList.contains(seven)&&((seven_ones + seven_tens) % 2 != 0)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("红合双".equals(quizOrders.getQuizIntroduces())) {//和值为偶数
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (hongboList.contains(seven) && (seven_ones + seven_tens) % 2 == 0){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (hongboList.contains(seven)&&((seven_ones + seven_tens) % 2 == 0)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("蓝大".equals(quizOrders.getQuizIntroduces())) {//特别码大于或等于25
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lanboList.contains(seven) && Integer.parseInt(seven) >= 25){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (lanboList.contains(seven)&&Integer.parseInt(seven) >= 25) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("蓝小".equals(quizOrders.getQuizIntroduces())) {//特别码小于或等于24
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lanboList.contains(seven) && Integer.parseInt(seven) <= 24){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (lanboList.contains(seven)&&Integer.parseInt(seven) <= 24) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("蓝单".equals(quizOrders.getQuizIntroduces())) {//特别码为奇数
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lanboList.contains(seven) && Integer.parseInt(seven) % 2 != 0){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)&&(Integer.parseInt(seven) % 2 != 0)) {//特别码49为和
                                    if (lanboList.contains(seven)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("蓝双".equals(quizOrders.getQuizIntroduces())) {//特别码为偶数
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lanboList.contains(seven) && Integer.parseInt(seven) % 2 == 0){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (lanboList.contains(seven)&&(Integer.parseInt(seven) % 2 == 0)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("蓝合单".equals(quizOrders.getQuizIntroduces())) {//和值为奇数
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lanboList.contains(seven) && (seven_ones + seven_tens) % 2 != 0){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (lanboList.contains(seven)&&(seven_ones + seven_tens) % 2 != 0) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("蓝合双".equals(quizOrders.getQuizIntroduces())) {//和值为偶数
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lanboList.contains(seven) && (seven_ones + seven_tens) % 2 == 0){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (lanboList.contains(seven)&&((seven_ones + seven_tens) % 2 == 0)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("绿大".equals(quizOrders.getQuizIntroduces())) {//特别码大于或等于25
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lvboList.contains(seven) && Integer.parseInt(seven) >= 25){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (lvboList.contains(seven)&&Integer.parseInt(seven) >= 25) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("绿小".equals(quizOrders.getQuizIntroduces())) {//特别码小于或等于24
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lvboList.contains(seven) && Integer.parseInt(seven) <= 24){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (lvboList.contains(seven)&&Integer.parseInt(seven) <= 24) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("绿单".equals(quizOrders.getQuizIntroduces())) {//特别码为奇数
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lvboList.contains(seven) && Integer.parseInt(seven) % 2 != 0){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (lvboList.contains(seven)&&(Integer.parseInt(seven) % 2 != 0)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("绿双".equals(quizOrders.getQuizIntroduces())) {//特别码为偶数
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lvboList.contains(seven) && Integer.parseInt(seven) % 2 == 0){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (lvboList.contains(seven)&&(Integer.parseInt(seven) % 2 == 0)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("绿合单".equals(quizOrders.getQuizIntroduces())) {//和值为奇数
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lvboList.contains(seven) && (seven_ones + seven_tens) % 2 != 0){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (lvboList.contains(seven)&&((seven_ones + seven_tens) % 2 != 0)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("绿合双".equals(quizOrders.getQuizIntroduces())) {//和值为偶数
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lvboList.contains(seven) && (seven_ones + seven_tens) % 2 == 0){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (lvboList.contains(seven)&&((seven_ones + seven_tens) % 2 == 0)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                    }
                }else if("半半波".equals(quizOrders.getQuizTitle())) {//分类二类
                    if("大小单双".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        //以特码色波和特码单双大小为一个投注组合，当开出特码符合投注组合，即视为中奖。若开出特码为49号时，所有投注半波任意一个组合视为和局，其余情况视为不中奖。
                        if ("红大双".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (hongboList.contains(seven) && bigDouble.contains(seven)){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (hongboList.contains(seven) && bigDouble.contains(seven)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("红小双".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (hongboList.contains(seven) && smallDouble.contains(seven)){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (hongboList.contains(seven) && smallDouble.contains(seven)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }

                        }
                        if ("红大单".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (hongboList.contains(seven) && bigSingle.contains(seven)){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (hongboList.contains(seven) && bigSingle.contains(seven)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("红小单".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (hongboList.contains(seven) && smallSingle.contains(seven)){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (hongboList.contains(seven) && smallSingle.contains(seven)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("绿大双".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lvboList.contains(seven) && bigDouble.contains(seven)){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (lvboList.contains(seven) && bigDouble.contains(seven)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("绿小双".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lvboList.contains(seven) && smallDouble.contains(seven)){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (lvboList.contains(seven) && smallDouble.contains(seven)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }

                        }
                        if ("绿大单".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lvboList.contains(seven) && bigSingle.contains(seven)){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (lvboList.contains(seven) && bigSingle.contains(seven)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("绿小单".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lvboList.contains(seven) && smallSingle.contains(seven)){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (lvboList.contains(seven) && smallSingle.contains(seven)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("蓝大双".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lanboList.contains(seven) && bigDouble.contains(seven)){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (lanboList.contains(seven) && bigDouble.contains(seven)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("蓝小双".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lanboList.contains(seven) && smallDouble.contains(seven)){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizOrders.getTotalPrice();
                                        quizSuborders.setWinMount(quizOrders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (lanboList.contains(seven) && smallDouble.contains(seven)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }

                        }
                        if ("蓝大单".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lanboList.contains(seven) && bigSingle.contains(seven)){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (lanboList.contains(seven) && bigSingle.contains(seven)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("蓝小单".equals(quizOrders.getQuizIntroduces())) {//
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lanboList.contains(seven) && smallSingle.contains(seven)){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (lanboList.contains(seven) && smallSingle.contains(seven)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                    }
                }else if("三色波".equals(quizOrders.getQuizTitle())) {//分类二类
                    if("三色波".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        if ("红波".equals(quizOrders.getQuizIntroduces())) {
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (hongboList.contains(seven)){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (hongboList.contains(seven)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }

                        }
                        if ("蓝波".equals(quizOrders.getQuizIntroduces())) {
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lanboList.contains(seven)){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (lanboList.contains(seven)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                        if ("绿波".equals(quizOrders.getQuizIntroduces())) {
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (!"49".equals(seven)) {//特别码49为和
                                        if (lvboList.contains(seven)){
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }else {
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                            quizSuborders.setWinMount(BigDecimal.ZERO);
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                        }
                                    } else {
                                        winAmount = quizSuborders.getTotalPrice();
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                        quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (!"49".equals(seven)) {//特别码49为和
                                    if (lvboList.contains(seven)) {
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                        winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                    }else {
                                        winLoseAmount = quizOrders.getTotalPrice().negate();
                                    }
                                } else {
                                    winAmount = quizOrders.getTotalPrice();
                                }
                            }
                        }
                    }
                }
            }else if ("正码".equals(quizOrders.getSiteCategoryName())) {//分类一类
                if("正码".equals(quizOrders.getQuizTitle())) {//分类二类
                    if ("大小单双".equals(quizOrders.getQuizDetailsName())) {//分类三类 大小单双
                        if ("总和大".equals(quizOrders.getQuizIntroduces())) {//所有七个开奖号码的总和大于或者等于175
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (Integer.parseInt(one) + Integer.parseInt(two) + Integer.parseInt(three) + Integer.parseInt(four) + Integer.parseInt(five) + Integer.parseInt(six) + Integer.parseInt(seven) >= 175){
                                        winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    }else {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (Integer.parseInt(one) + Integer.parseInt(two) + Integer.parseInt(three) + Integer.parseInt(four) + Integer.parseInt(five) + Integer.parseInt(six) + Integer.parseInt(seven) >= 175){
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }
                        }
                        if ("总和小".equals(quizOrders.getQuizIntroduces())) {//所有七个开奖号码的总和小于或者等于174
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if (Integer.parseInt(one) + Integer.parseInt(two) + Integer.parseInt(three) + Integer.parseInt(four) + Integer.parseInt(five) + Integer.parseInt(six) + Integer.parseInt(seven) <= 174){
                                        winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    }else {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if (Integer.parseInt(one) + Integer.parseInt(two) + Integer.parseInt(three) + Integer.parseInt(four) + Integer.parseInt(five) + Integer.parseInt(six) + Integer.parseInt(seven) <= 174){
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }
                        }
                        if ("总和单".equals(quizOrders.getQuizIntroduces())) {//所有七个开奖号码的总和值为奇数
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if ((Integer.parseInt(one) + Integer.parseInt(two) + Integer.parseInt(three) + Integer.parseInt(four) + Integer.parseInt(five) + Integer.parseInt(six) + Integer.parseInt(seven)) % 2 != 0){
                                        winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    }else {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if ((Integer.parseInt(one) + Integer.parseInt(two) + Integer.parseInt(three) + Integer.parseInt(four) + Integer.parseInt(five) + Integer.parseInt(six) + Integer.parseInt(seven)) % 2 != 0){
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }
                        }
                        if ("总和双".equals(quizOrders.getQuizIntroduces())) {//所有七个开奖号码的总和值为偶数
                            if(null!=quizSubordersList&&quizSubordersList.size()>0){
                                for(QuizSuborders quizSuborders : quizSubordersList){
                                    if ((Integer.parseInt(one) + Integer.parseInt(two) + Integer.parseInt(three) + Integer.parseInt(four) + Integer.parseInt(five) + Integer.parseInt(six) + Integer.parseInt(seven)) % 2 == 0){
                                        winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    }else {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    }
                                    quizSuborders.setUpdateTime(new Date());
                                    subordersList.add(quizSuborders);
                                }
                            }else {
                                if ((Integer.parseInt(one) + Integer.parseInt(two) + Integer.parseInt(three) + Integer.parseInt(four) + Integer.parseInt(five) + Integer.parseInt(six) + Integer.parseInt(seven)) % 2 == 0){
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }
                        }
                    }
                    if ("正码".equals(quizOrders.getQuizDetailsName())) {//分类三类 大小单双
                        //从01-49中任选1个号码组成一注。不限顺序，开奖的正码中包含投注号，即为中奖。
                        if(null!=quizSubordersList&&quizSubordersList.size()>0){
                            for(QuizSuborders quizSuborders : quizSubordersList){
                                if (Arrays.asList(wnNumbersSix).contains(quizSuborders.getBettingContent())){
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                }else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        }else {
                            if (Arrays.asList(wnNumbersSix).contains(quizOrders.getQuizIntroduces())) {
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                            }else {
                                winLoseAmount = quizOrders.getTotalPrice().negate();
                            }
                        }
                    }
                }
                if("正码1-6".equals(quizOrders.getQuizTitle())) {//分类二类
                    //选1个或1个以上正码属性，所选正码属性与开奖号码的第一位属性一致，即为中奖；若正码一是49，则为和局。
                    if ("正码1".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        int one_ones = Integer.parseInt(one) % 10;
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                if (!"49".equals(one)) {
                                    BigDecimal amount = this.daxiaodangshuanghonglvlanbo(one,
                                            one_ones, quizSuborders.getBettingContent(), quizSuborders.getTotalPrice(), quizSuborders.getOdds(),
                                            hongboList, lvboList, lanboList);
                                    if (BigDecimal.ZERO.compareTo(amount) == 0) {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    } else {
                                        winAmount = winAmount.add(amount);
                                        quizSuborders.setWinMount(amount);
                                        quizSuborders.setWinLoseAmount(amount.subtract(quizSuborders.getTotalPrice()));
                                    }
                                } else {//和
                                    winAmount = winAmount.add(quizOrders.getTotalPrice());
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                    quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        } else {
                            if (!"49".equals(one)) {
                                BigDecimal amount = this.daxiaodangshuanghonglvlanbo(one,
                                        one_ones, quizOrders.getQuizIntroduces(), quizOrders.getTotalPrice(), quizOrders.getOdds(),
                                        hongboList, lvboList, lanboList);
                                if (BigDecimal.ZERO.compareTo(amount) == 0) {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }else {
                                    winAmount = amount;
                                }
                            } else {//和
                                winAmount = quizOrders.getTotalPrice();
                            }
                        }
                    }
                    //选1个或1个以上正码属性，所选正码属性与开奖号码的第二位属性一致，即为中奖；若正码二是49，则为和局。
                    if ("正码2".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        int two_ones = Integer.parseInt(two) % 10;
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                if (!"49".equals(two)) {
                                    BigDecimal amount = this.daxiaodangshuanghonglvlanbo(two,
                                            two_ones, quizSuborders.getBettingContent(), quizSuborders.getTotalPrice(), quizSuborders.getOdds(),
                                            hongboList, lvboList, lanboList);
                                    if (BigDecimal.ZERO.compareTo(amount) == 0) {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    } else {
                                        winAmount = winAmount.add(amount);
                                        quizSuborders.setWinMount(amount);
                                        quizSuborders.setWinLoseAmount(amount.subtract(quizSuborders.getTotalPrice()));
                                    }
                                } else {//和
                                    winAmount = winAmount.add(quizOrders.getTotalPrice());
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                    quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        } else {
                            if (!"49".equals(two)) {
                                BigDecimal amount = this.daxiaodangshuanghonglvlanbo(two,
                                        two_ones, quizOrders.getQuizIntroduces(), quizOrders.getTotalPrice(), quizOrders.getOdds(),
                                        hongboList, lvboList, lanboList);
                                if (BigDecimal.ZERO.compareTo(amount) == 0) {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }else {
                                    winAmount = amount;
                                }
                            } else {//和
                                winAmount = quizOrders.getTotalPrice();
                            }
                        }
                    }
                    //选1个或1个以上正码属性，所选正码属性与开奖号码的第三位属性一致，即为中奖；若正码三是49，则为和局。
                    if ("正码3".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        int three_ones = Integer.parseInt(three) % 10;
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                if (!"49".equals(three)) {
                                    BigDecimal amount = this.daxiaodangshuanghonglvlanbo(three,
                                            three_ones, quizSuborders.getBettingContent(), quizSuborders.getTotalPrice(), quizSuborders.getOdds(),
                                            hongboList, lvboList, lanboList);
                                    if (BigDecimal.ZERO.compareTo(amount) == 0) {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    } else {
                                        winAmount = winAmount.add(amount);
                                        quizSuborders.setWinMount(amount);
                                        quizSuborders.setWinLoseAmount(amount.subtract(quizSuborders.getTotalPrice()));
                                    }
                                } else {//和
                                    winAmount = winAmount.add(quizOrders.getTotalPrice());
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                    quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        } else {
                            if (!"49".equals(three)) {
                                BigDecimal amount = this.daxiaodangshuanghonglvlanbo(three,
                                        three_ones, quizOrders.getQuizIntroduces(), quizOrders.getTotalPrice(), quizOrders.getOdds(),
                                        hongboList, lvboList, lanboList);
                                if (BigDecimal.ZERO.compareTo(amount) == 0) {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }else {
                                    winAmount = amount;
                                }
                            } else {//和
                                winAmount = quizOrders.getTotalPrice();
                            }
                        }
                    }
                    //选1个或1个以上正码属性，所选正码属性与开奖号码的第四位属性一致，即为中奖；若正码四是49，则为和局。
                    if ("正码4".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        int four_ones = Integer.parseInt(four) % 10;
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                if (!"49".equals(four)) {
                                    BigDecimal amount = this.daxiaodangshuanghonglvlanbo(four,
                                            four_ones, quizSuborders.getBettingContent(), quizSuborders.getTotalPrice(), quizSuborders.getOdds(),
                                            hongboList, lvboList, lanboList);
                                    if (BigDecimal.ZERO.compareTo(amount) == 0) {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    } else {
                                        winAmount = winAmount.add(amount);
                                        quizSuborders.setWinMount(amount);
                                        quizSuborders.setWinLoseAmount(amount.subtract(quizSuborders.getTotalPrice()));
                                    }
                                } else {//和
                                    winAmount = winAmount.add(quizOrders.getTotalPrice());
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                    quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        } else {
                            if (!"49".equals(four)) {
                                BigDecimal amount = this.daxiaodangshuanghonglvlanbo(four,
                                        four_ones, quizOrders.getQuizIntroduces(), quizOrders.getTotalPrice(), quizOrders.getOdds(),
                                        hongboList, lvboList, lanboList);
                                if (BigDecimal.ZERO.compareTo(amount) == 0) {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }else {
                                    winAmount = amount;
                                }
                            } else {//和
                                winAmount = quizOrders.getTotalPrice();
                            }
                        }
                    }
                    //选1个或1个以上正码属性，所选正码属性与开奖号码的第五位属性一致，即为中奖；若正码五是49，则为和局。
                    if ("正码5".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        int five_ones = Integer.parseInt(five) % 10;
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                if (!"49".equals(five)) {
                                    BigDecimal amount = this.daxiaodangshuanghonglvlanbo(five,
                                            five_ones, quizSuborders.getBettingContent(), quizSuborders.getTotalPrice(), quizSuborders.getOdds(),
                                            hongboList, lvboList, lanboList);
                                    if (BigDecimal.ZERO.compareTo(amount) == 0) {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    } else {
                                        winAmount = winAmount.add(amount);
                                        quizSuborders.setWinMount(amount);
                                        quizSuborders.setWinLoseAmount(amount.subtract(quizSuborders.getTotalPrice()));
                                    }
                                } else {//和
                                    winAmount = winAmount.add(quizOrders.getTotalPrice());
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                    quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        } else {
                            if (!"49".equals(five)) {
                                BigDecimal amount = this.daxiaodangshuanghonglvlanbo(five,
                                        five_ones, quizOrders.getQuizIntroduces(), quizOrders.getTotalPrice(), quizOrders.getOdds(),
                                        hongboList, lvboList, lanboList);
                                if (BigDecimal.ZERO.compareTo(amount) == 0) {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }else {
                                    winAmount = amount;
                                }
                            } else {//和
                                winAmount = quizOrders.getTotalPrice();
                            }
                        }
                    }
                    //选1个或1个以上正码属性，所选正码属性与开奖号码的第六位属性一致，即为中奖；若正码六是49，则为和局。
                    if ("正码6".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        int six_ones = Integer.parseInt(six) % 10;
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                if (!"49".equals(six)) {
                                    BigDecimal amount = this.daxiaodangshuanghonglvlanbo(six,
                                            six_ones, quizSuborders.getBettingContent(), quizSuborders.getTotalPrice(), quizSuborders.getOdds(),
                                            hongboList, lvboList, lanboList);
                                    if (BigDecimal.ZERO.compareTo(amount) == 0) {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    } else {
                                        winAmount = winAmount.add(amount);
                                        quizSuborders.setWinMount(amount);
                                        quizSuborders.setWinLoseAmount(amount.subtract(quizSuborders.getTotalPrice()));
                                    }
                                } else {//和
                                    winAmount = winAmount.add(quizOrders.getTotalPrice());
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                    quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        } else {
                            if (!"49".equals(six)) {
                                BigDecimal amount = this.daxiaodangshuanghonglvlanbo(six,
                                        six_ones, quizOrders.getQuizIntroduces(), quizOrders.getTotalPrice(), quizOrders.getOdds(),
                                        hongboList, lvboList, lanboList);
                                if (BigDecimal.ZERO.compareTo(amount) == 0) {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }else {
                                    winAmount = amount;
                                }
                            } else {//和
                                winAmount = quizOrders.getTotalPrice();
                            }
                        }
                    }
                }
            }else if ("正码特".equals(quizOrders.getSiteCategoryName())) {//分类一类
                if("正一特".equals(quizOrders.getQuizTitle())){//分类二类
                    if("正一特".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                if (!"49".equals(one)) {
                                    if (one.equals(quizSuborders.getBettingContent())) {//购买的号码相同为中奖
                                        winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizOrders.getOdds()));
                                        winLoseAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice()));
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizOrders.getOdds()));
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    } else {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    }
                                } else {//和
                                    winAmount = winAmount.add(quizOrders.getTotalPrice());
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                    quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        } else {
                            if (!"49".equals(one)) {
                                if (one.equals(quizOrders.getQuizIntroduces())) {//购买的号码相同为中奖
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            } else {//和
                                winAmount = quizOrders.getTotalPrice();
                            }
                        }
                    }
                }
                if("正二特".equals(quizOrders.getQuizTitle())){//分类二类
                    if("正二特".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                if (!"49".equals(two)) {
                                    if (two.equals(quizSuborders.getBettingContent())) {//购买的号码相同为中奖
                                        winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizOrders.getOdds()));
                                        winLoseAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice()));
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizOrders.getOdds()));
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    } else {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    }
                                } else {//和
                                    winAmount = winAmount.add(quizOrders.getTotalPrice());
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                    quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        } else {
                            if (!"49".equals(two)) {
                                if (two.equals(quizOrders.getQuizIntroduces())) {//购买的号码相同为中奖
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            } else {//和
                                winAmount = quizOrders.getTotalPrice();
                            }
                        }
                    }
                }
                if("正三特".equals(quizOrders.getQuizTitle())){//分类二类
                    if("正三特".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                if (!"49".equals(three)) {
                                    if (three.equals(quizSuborders.getBettingContent())) {//购买的号码相同为中奖
                                        winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizOrders.getOdds()));
                                        winLoseAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice()));
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizOrders.getOdds()));
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    } else {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    }
                                } else {//和
                                    winAmount = winAmount.add(quizOrders.getTotalPrice());
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                    quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        } else {
                            if (!"49".equals(three)) {
                                if (three.equals(quizOrders.getQuizIntroduces())) {//购买的号码相同为中奖
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            } else {//和
                                winAmount = quizOrders.getTotalPrice();
                            }
                        }
                    }
                }
                if("正四特".equals(quizOrders.getQuizTitle())){//分类二类
                    if("正四特".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                if (!"49".equals(four)) {
                                    if (four.equals(quizSuborders.getBettingContent())) {//购买的号码相同为中奖
                                        winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizOrders.getOdds()));
                                        winLoseAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice()));
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizOrders.getOdds()));
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    } else {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    }
                                } else {//和
                                    winAmount = winAmount.add(quizOrders.getTotalPrice());
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                    quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        } else {
                            if (!"49".equals(four)) {
                                if (four.equals(quizOrders.getQuizIntroduces())) {//购买的号码相同为中奖
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            } else {//和
                                winAmount = quizOrders.getTotalPrice();
                            }
                        }
                    }
                }
                if("正五特".equals(quizOrders.getQuizTitle())){//分类二类
                    if("正五特".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                if (!"49".equals(five)) {
                                    if (five.equals(quizSuborders.getBettingContent())) {//购买的号码相同为中奖
                                        winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizOrders.getOdds()));
                                        winLoseAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice()));
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizOrders.getOdds()));
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    } else {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    }
                                } else {//和
                                    winAmount = winAmount.add(quizOrders.getTotalPrice());
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                    quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        } else {
                            if (!"49".equals(five)) {
                                if (five.equals(quizOrders.getQuizIntroduces())) {//购买的号码相同为中奖
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            } else {//和
                                winAmount = quizOrders.getTotalPrice();
                            }
                        }
                    }
                }
                if("正六特".equals(quizOrders.getQuizTitle())){//分类二类
                    if("正六特".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                if (!"49".equals(six)) {
                                    if (six.equals(quizSuborders.getBettingContent())) {//购买的号码相同为中奖
                                        winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizOrders.getOdds()));
                                        winLoseAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice()));
                                        quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizOrders.getOdds()));
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    } else {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    }
                                } else {//和
                                    winAmount = winAmount.add(quizOrders.getTotalPrice());
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice());
                                    quizSuborders.setWinLoseAmount(BigDecimal.ZERO);
                                }
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        } else {
                            if (!"49".equals(six)) {
                                if (six.equals(quizOrders.getQuizIntroduces())) {//购买的号码相同为中奖
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            } else {//和
                                winAmount = quizOrders.getTotalPrice();
                            }
                        }
                    }
                }
            }else if ("连码".equals(quizOrders.getSiteCategoryName())) {//分类一类

                if("三全中".equals(quizOrders.getQuizTitle())) {//分类二类
                    //所投注的每三个号码为一组合，若三个号码都是开奖号码之正码，视为中奖，其余行情视为不中奖
                    if (null != quizSubordersList && quizSubordersList.size() > 0) {
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbersSix).contains(bettingNumberList[0])
                                    && Arrays.asList(wnNumbersSix).contains(bettingNumberList[1])
                                    && Arrays.asList(wnNumbersSix).contains(bettingNumberList[2])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                            } else {
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                quizSuborders.setWinMount(BigDecimal.ZERO);
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                            }
                            quizSuborders.setUpdateBy(sysUser.getUsername());
                            quizSuborders.setUpdateTime(new Date());
                            subordersList.add(quizSuborders);
                        }
                    }
                }
                if("三中二".equals(quizOrders.getQuizTitle())) {//分类二类
                    //所投注的每三个号码为一组合，若其中2个号码都是开奖号码之正码，视为三中二奖，若3个都是开奖号码中的正码，即为三中二之中三，其余行情视为不中奖
                    if (null != quizSubordersList && quizSubordersList.size() > 0) {
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            //若3个都是开奖号码中的正码，即为三中二之中三
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbersSix).contains(bettingNumberList[0])
                                    && Arrays.asList(wnNumbersSix).contains(bettingNumberList[1])
                                    && Arrays.asList(wnNumbersSix).contains(bettingNumberList[2])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds2()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds2()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds2()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds2()).subtract(quizSuborders.getTotalPrice()));
                            } else if (!Arrays.asList(wnNumbersSix).contains(bettingNumberList[0])
                                    && Arrays.asList(wnNumbersSix).contains(bettingNumberList[1])
                                    && Arrays.asList(wnNumbersSix).contains(bettingNumberList[2])) {//为三中二
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                            } else if (Arrays.asList(wnNumbersSix).contains(bettingNumberList[0])
                                    && !Arrays.asList(wnNumbersSix).contains(bettingNumberList[1])
                                    && Arrays.asList(wnNumbersSix).contains(bettingNumberList[2])) {//为三中二
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                            } else if (Arrays.asList(wnNumbersSix).contains(bettingNumberList[0])
                                    && Arrays.asList(wnNumbersSix).contains(bettingNumberList[1])
                                    && !Arrays.asList(wnNumbersSix).contains(bettingNumberList[2])) {//为三中二
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                            } else {
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                quizSuborders.setWinMount(BigDecimal.ZERO);
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                            }
                            quizSuborders.setUpdateBy(sysUser.getUsername());
                            quizSuborders.setUpdateTime(new Date());
                            subordersList.add(quizSuborders);
                        }
                    }
                }
                if("二全中".equals(quizOrders.getQuizTitle())) {//分类二类
                    //所投注的每二个号码为一组合，若二个号码都是开奖号码之正码，视为中奖，其余行情视为不中奖（含一个正码加一个特码情形）
                    if (null != quizSubordersList && quizSubordersList.size() > 0) {
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbersSix).contains(bettingNumberList[0])
                                    && Arrays.asList(wnNumbersSix).contains(bettingNumberList[1])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                            } else {
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                quizSuborders.setWinMount(BigDecimal.ZERO);
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                            }
                            quizSuborders.setUpdateBy(sysUser.getUsername());
                            quizSuborders.setUpdateTime(new Date());
                            subordersList.add(quizSuborders);
                        }
                    }
                }
                if("二中特".equals(quizOrders.getQuizTitle())) {//分类二类
                    //所投注的每二个号码为一组合，若二个号码都是开奖号码之正码，叫二中特之中二，其中一个是正码，一个是特码，视为中奖,其余行情视为不中奖二中特之中二赔率高于二中特之中特的赔率
                    if (null != quizSubordersList && quizSubordersList.size() > 0) {
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbersSix).contains(bettingNumberList[0])
                                    && Arrays.asList(wnNumbersSix).contains(bettingNumberList[1])) {//二中特之中二
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds2()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds2()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds2()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds2()).subtract(quizSuborders.getTotalPrice()));
                            } else {//二中特之中特
                                if (seven.equals(bettingNumberList[0]) || seven.equals(bettingNumberList[1])) {
                                    if (seven.equals(bettingNumberList[0])) {
                                        if (Arrays.asList(wnNumbersSix).contains(bettingNumberList[1])) {
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }
                                    } else if (seven.equals(bettingNumberList[1])) {
                                        if (Arrays.asList(wnNumbersSix).contains(bettingNumberList[0])) {
                                            winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                            quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                            quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                        }
                                    } else {
                                        winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                        quizSuborders.setWinMount(BigDecimal.ZERO);
                                        quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                    }
                                } else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                            }
                            quizSuborders.setUpdateBy(sysUser.getUsername());
                            quizSuborders.setUpdateTime(new Date());
                            subordersList.add(quizSuborders);
                        }
                    }
                }
                if("特串".equals(quizOrders.getQuizTitle())) {//分类二类
                    //所投注的每两个号码为一组合，其中一个是正码，一个是特码，视为中奖，其余情形视为不中奖（含二个都是正码之情形）
                    if (null != quizSubordersList && quizSubordersList.size() > 0) {
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (seven.equals(bettingNumberList[0])) {
                                if (Arrays.asList(wnNumbersSix).contains(bettingNumberList[1])) {
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                } else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                            } else if (seven.equals(bettingNumberList[1])) {
                                if (Arrays.asList(wnNumbersSix).contains(bettingNumberList[0])) {
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                } else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                            } else {
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                quizSuborders.setWinMount(BigDecimal.ZERO);
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                            }
                            quizSuborders.setUpdateBy(sysUser.getUsername());
                            quizSuborders.setUpdateTime(new Date());
                            subordersList.add(quizSuborders);
                        }
                    }
                }
                if("四全中".equals(quizOrders.getQuizTitle())) {//分类二类
                    //所投注号码每四个为一组，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
                    if (null != quizSubordersList && quizSubordersList.size() > 0) {
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    && Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    && Arrays.asList(wnNumbers).contains(bettingNumberList[2])
                                    && Arrays.asList(wnNumbers).contains(bettingNumberList[3])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                            } else {
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                quizSuborders.setWinMount(BigDecimal.ZERO);
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                            }
                            quizSuborders.setUpdateBy(sysUser.getUsername());
                            quizSuborders.setUpdateTime(new Date());
                            subordersList.add(quizSuborders);
                        }
                    }
                }
            }else if ("头尾数".equals(quizOrders.getSiteCategoryName())) {//分类一类
                if("特码头尾数".equals(quizOrders.getQuizTitle())) {//分类二类
                    //对特码的头尾数进行下注，下注的头尾数与特码的头尾数一致即中奖
                    if("特码头尾数".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        //0头、1头、2头、3头、4头
                        //0尾、1尾、2尾、3尾、4尾、5尾、6尾、7尾、8尾、9尾
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                if (quizOrders.getQuizIntroduces().equals(seven_ones + "头") || quizOrders.getQuizIntroduces().equals(seven_ones + "尾")) {
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                } else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                                quizSuborders.setUpdateBy(sysUser.getUsername());
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        }else {
                            if (quizOrders.getQuizIntroduces().equals(seven_ones + "头") || quizOrders.getQuizIntroduces().equals(seven_ones + "尾")) {
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                            }else {
                                winLoseAmount = quizOrders.getTotalPrice().negate();
                            }
                        }
                    }
                }
                if("正特尾数".equals(quizOrders.getQuizTitle())) {//分类二类
                    //对正码和特码的尾数进行下注，下注尾数必须在当期开出的正码或特码对应的尾数中即中奖，不论同尾数出现一个或者多个都只派彩一次
                    if("正特尾数".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                if (quizOrders.getQuizIntroduces().indexOf(Arrays.toString(wnTail)) != -1) {
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                } else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                                quizSuborders.setUpdateBy(sysUser.getUsername());
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        }else {
                            if (quizOrders.getQuizIntroduces().indexOf(Arrays.toString(wnTail)) != -1) {
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                            }else {
                                winLoseAmount = quizOrders.getTotalPrice().negate();
                            }
                        }
                    }
                }
                //选择2-4个尾数为一投注组合进行投注。该注的2-4个尾数必须在当期开出的7个开奖号码相对应的尾数中，（49亦算输赢，不为和）。每个号码都有自己的赔率，下注组合的总赔率，取该组合码的最低赔率为下单赔率
                if("二尾连中".equals(quizOrders.getQuizTitle())
                        ||"三尾连中".equals(quizOrders.getQuizTitle())
                        ||"四尾连中".equals(quizOrders.getQuizTitle())) {//分类二类
                    if("二尾连中".equals(quizOrders.getQuizTitle())){
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                                if (Arrays.asList(wnTail).contains(bettingNumberList[0])
                                        && Arrays.asList(wnTail).contains(bettingNumberList[1])) {
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                } else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                                quizSuborders.setUpdateBy(sysUser.getUsername());
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        }
                    }
                    if("三尾连中".equals(quizOrders.getQuizTitle())){
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                                if (Arrays.asList(wnTail).contains(bettingNumberList[0])
                                        && Arrays.asList(wnTail).contains(bettingNumberList[1])
                                        && Arrays.asList(wnTail).contains(bettingNumberList[2])) {
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                } else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                                quizSuborders.setUpdateBy(sysUser.getUsername());
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        }
                    }
                    if("四尾连中".equals(quizOrders.getQuizTitle())){
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                                if (Arrays.asList(wnTail).contains(bettingNumberList[0])
                                        && Arrays.asList(wnTail).contains(bettingNumberList[1])
                                        && Arrays.asList(wnTail).contains(bettingNumberList[2])
                                        && Arrays.asList(wnTail).contains(bettingNumberList[3])) {
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                } else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                                quizSuborders.setUpdateBy(sysUser.getUsername());
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        }
                    }
                }
            }else if ("多选中一".equals(quizOrders.getSiteCategoryName())) {//分类一类 每个号码都有自己的赔率，下注组合取该组合最低赔率为总赔率
                //挑选5个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
                if("五选中一".equals(quizOrders.getQuizTitle())) {//分类二类
                    if (null != quizSubordersList && quizSubordersList.size() > 0) {
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[2])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[3])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[4])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                            } else {
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                quizSuborders.setWinMount(BigDecimal.ZERO);
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                            }
                            quizSuborders.setUpdateBy(sysUser.getUsername());
                            quizSuborders.setUpdateTime(new Date());
                            subordersList.add(quizSuborders);
                        }
                    }
                }
                //挑选6个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
                if("六选中一".equals(quizOrders.getQuizTitle())) {//分类二类
                    if (null != quizSubordersList && quizSubordersList.size() > 0) {
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[2])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[3])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[4])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[5])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                            } else {
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                quizSuborders.setWinMount(BigDecimal.ZERO);
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                            }
                            quizSuborders.setUpdateBy(sysUser.getUsername());
                            quizSuborders.setUpdateTime(new Date());
                            subordersList.add(quizSuborders);
                        }
                    }
                }
                //挑选7个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
                if("七选中一".equals(quizOrders.getQuizTitle())) {//分类二类
                    if (null != quizSubordersList && quizSubordersList.size() > 0) {
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[2])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[3])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[4])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[5])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[6])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                            } else {
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                quizSuborders.setWinMount(BigDecimal.ZERO);
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                            }
                            quizSuborders.setUpdateBy(sysUser.getUsername());
                            quizSuborders.setUpdateTime(new Date());
                            subordersList.add(quizSuborders);
                        }
                    }
                }
                //挑选8个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
                if("八选中一".equals(quizOrders.getQuizTitle())) {//分类二类
                    if (null != quizSubordersList && quizSubordersList.size() > 0) {
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[2])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[3])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[4])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[5])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[6])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[7])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                            } else {
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                quizSuborders.setWinMount(BigDecimal.ZERO);
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                            }
                            quizSuborders.setUpdateBy(sysUser.getUsername());
                            quizSuborders.setUpdateTime(new Date());
                            subordersList.add(quizSuborders);
                        }
                    }
                }
                //挑选9个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
                if("九选中一".equals(quizOrders.getQuizTitle())) {//分类二类
                    if (null != quizSubordersList && quizSubordersList.size() > 0) {
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[2])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[3])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[4])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[5])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[6])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[7])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[8])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                            } else {
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                quizSuborders.setWinMount(BigDecimal.ZERO);
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                            }
                            quizSuborders.setUpdateBy(sysUser.getUsername());
                            quizSuborders.setUpdateTime(new Date());
                            subordersList.add(quizSuborders);
                        }
                    }
                }
                //挑选10个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
                if("十选中一".equals(quizOrders.getQuizTitle())) {//分类二类
                    if (null != quizSubordersList && quizSubordersList.size() > 0) {
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[2])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[3])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[4])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[5])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[6])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[7])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[8])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[9])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                            } else {
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                quizSuborders.setWinMount(BigDecimal.ZERO);
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                            }
                            quizSuborders.setUpdateBy(sysUser.getUsername());
                            quizSuborders.setUpdateTime(new Date());
                            subordersList.add(quizSuborders);
                        }
                    }
                }
            }else if ("自选不中".equals(quizOrders.getSiteCategoryName())) {//分类一类
                if("五不中".equals(quizOrders.getQuizTitle())) {//分类二类
                    if (null != quizSubordersList && quizSubordersList.size() > 0) {
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (!Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[2])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[3])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[4])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                            } else {
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                quizSuborders.setWinMount(BigDecimal.ZERO);
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                            }
                            quizSuborders.setUpdateBy(sysUser.getUsername());
                            quizSuborders.setUpdateTime(new Date());
                            subordersList.add(quizSuborders);
                        }
                    }
                }
                if("六不中".equals(quizOrders.getQuizTitle())) {//分类二类
                    if (null != quizSubordersList && quizSubordersList.size() > 0) {
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (!Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[2])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[3])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[4])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[5])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                            } else {
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                quizSuborders.setWinMount(BigDecimal.ZERO);
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                            }
                            quizSuborders.setUpdateBy(sysUser.getUsername());
                            quizSuborders.setUpdateTime(new Date());
                            subordersList.add(quizSuborders);
                        }
                    }
                }
                if("七不中".equals(quizOrders.getQuizTitle())) {//分类二类
                    if (null != quizSubordersList && quizSubordersList.size() > 0) {
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (!Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[2])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[3])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[4])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[5])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[6])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                            } else {
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                quizSuborders.setWinMount(BigDecimal.ZERO);
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                            }
                            quizSuborders.setUpdateBy(sysUser.getUsername());
                            quizSuborders.setUpdateTime(new Date());
                            subordersList.add(quizSuborders);
                        }
                    }
                }
                if("八不中".equals(quizOrders.getQuizTitle())) {//分类二类
                    if (null != quizSubordersList && quizSubordersList.size() > 0) {
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (!Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[2])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[3])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[4])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[5])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[6])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[7])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                            } else {
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                quizSuborders.setWinMount(BigDecimal.ZERO);
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                            }
                            quizSuborders.setUpdateBy(sysUser.getUsername());
                            quizSuborders.setUpdateTime(new Date());
                            subordersList.add(quizSuborders);
                        }
                    }
                }
                if("九不中".equals(quizOrders.getQuizTitle())) {//分类二类
                    if (null != quizSubordersList && quizSubordersList.size() > 0) {
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (!Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[2])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[3])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[4])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[5])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[6])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[7])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[8])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                            } else {
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                quizSuborders.setWinMount(BigDecimal.ZERO);
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                            }
                            quizSuborders.setUpdateBy(sysUser.getUsername());
                            quizSuborders.setUpdateTime(new Date());
                            subordersList.add(quizSuborders);
                        }
                    }
                }
                if("十不中".equals(quizOrders.getQuizTitle())) {//分类二类
                    if (null != quizSubordersList && quizSubordersList.size() > 0) {
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (!Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[2])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[3])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[4])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[5])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[6])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[7])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[8])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[9])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                            } else {
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                quizSuborders.setWinMount(BigDecimal.ZERO);
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                            }
                            quizSuborders.setUpdateBy(sysUser.getUsername());
                            quizSuborders.setUpdateTime(new Date());
                            subordersList.add(quizSuborders);
                        }
                    }
                }
                if("十一不中".equals(quizOrders.getQuizTitle())) {//分类二类
                    if (null != quizSubordersList && quizSubordersList.size() > 0) {
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (!Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[2])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[3])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[4])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[5])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[6])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[7])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[8])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[9])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[10])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                            } else {
                                winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                quizSuborders.setWinMount(BigDecimal.ZERO);
                                quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                            }
                            quizSuborders.setUpdateBy(sysUser.getUsername());
                            quizSuborders.setUpdateTime(new Date());
                            subordersList.add(quizSuborders);
                        }
                    }
                }
            }else if ("五行".equals(quizOrders.getSiteCategoryName())) {//分类一类
                if("五行".equals(quizOrders.getQuizTitle())) {//分类二类
                    if("五行".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                if (("金".equals(quizSuborders.getBettingContent())&&Arrays.asList(jinList).contains(seven))||
                                        ("木".equals(quizSuborders.getBettingContent())&&Arrays.asList(muList).contains(seven))||
                                        ("水".equals(quizSuborders.getBettingContent())&&Arrays.asList(shuiList).contains(seven))||
                                        ("火".equals(quizSuborders.getBettingContent())&&Arrays.asList(huoList).contains(seven))||
                                        ("土".equals(quizSuborders.getBettingContent())&&Arrays.asList(tuList).contains(seven))) {
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                } else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                                quizSuborders.setUpdateBy(sysUser.getUsername());
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        }else {
                            if (("金".equals(quizOrders.getQuizIntroduces())&&Arrays.asList(jinList).contains(seven))||
                                    ("木".equals(quizOrders.getQuizIntroduces())&&Arrays.asList(muList).contains(seven))||
                                    ("水".equals(quizOrders.getQuizIntroduces())&&Arrays.asList(shuiList).contains(seven))||
                                    ("火".equals(quizOrders.getQuizIntroduces())&&Arrays.asList(huoList).contains(seven))||
                                    ("土".equals(quizOrders.getQuizIntroduces())&&Arrays.asList(tuList).contains(seven))) {
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                            }else {
                                winLoseAmount = quizOrders.getTotalPrice().negate();
                            }
                        }
                    }
                }
            }else if ("七码".equals(quizOrders.getSiteCategoryName())) {//分类一类
                //估猜七个开奖号码中（单，双，大，小）各有多少个。例如投注【单0】若该期开出的七个号码中一个单数号码都没有出现，既视为中奖
                if("七码".equals(quizOrders.getQuizTitle())) {//分类二类
                    if("单".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                if (("单0".equals(quizSuborders.getBettingContent())&&0 == singlecount)||
                                        ("单1".equals(quizSuborders.getBettingContent())&&1 == singlecount)||
                                        ("单2".equals(quizSuborders.getBettingContent())&&2 == singlecount)||
                                        ("单3".equals(quizSuborders.getBettingContent())&&3 == singlecount)||
                                        ("单4".equals(quizSuborders.getBettingContent())&&4 == singlecount)||
                                        ("单5".equals(quizSuborders.getBettingContent())&&5 == singlecount)||
                                        ("单6".equals(quizSuborders.getBettingContent())&&6 == singlecount)||
                                        ("单7".equals(quizSuborders.getBettingContent())&&7 == singlecount)) {
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                } else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                                quizSuborders.setUpdateBy(sysUser.getUsername());
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        }else {
                            if ("单0".equals(quizOrders.getQuizIntroduces())) {
                                if (0 == singlecount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("单1".equals(quizOrders.getQuizIntroduces())) {
                                if (1 == singlecount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("单2".equals(quizOrders.getQuizIntroduces())) {
                                if (2 == singlecount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("单3".equals(quizOrders.getQuizIntroduces())) {
                                if (3 == singlecount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("单4".equals(quizOrders.getQuizIntroduces())) {
                                if (4 == singlecount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("单5".equals(quizOrders.getQuizIntroduces())) {
                                if (5 == singlecount){
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("单6".equals(quizOrders.getQuizIntroduces())) {
                                if (6 == singlecount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("单7".equals(quizOrders.getQuizIntroduces())) {
                                if (7 == singlecount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }
                        }
                    }
                    if("双".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                if (("双0".equals(quizSuborders.getBettingContent())&&0 == doublecount)||
                                        ("双1".equals(quizSuborders.getBettingContent())&&1 == doublecount)||
                                        ("双2".equals(quizSuborders.getBettingContent())&&2 == doublecount)||
                                        ("双3".equals(quizSuborders.getBettingContent())&&3 == doublecount)||
                                        ("双4".equals(quizSuborders.getBettingContent())&&4 == doublecount)||
                                        ("双5".equals(quizSuborders.getBettingContent())&&5 == doublecount)||
                                        ("双6".equals(quizSuborders.getBettingContent())&&6 == doublecount)||
                                        ("双7".equals(quizSuborders.getBettingContent())&&7 == doublecount)) {
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                } else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                                quizSuborders.setUpdateBy(sysUser.getUsername());
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        }else {
                            if ("双0".equals(quizOrders.getQuizIntroduces())) {
                                if (0 == doublecount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("双1".equals(quizOrders.getQuizIntroduces())) {
                                if (1 == doublecount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("双2".equals(quizOrders.getQuizIntroduces())) {
                                if (2 == doublecount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("双3".equals(quizOrders.getQuizIntroduces())) {
                                if (3 == doublecount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("双4".equals(quizOrders.getQuizIntroduces())) {
                                if (4 == doublecount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("双5".equals(quizOrders.getQuizIntroduces())) {
                                if (5 == doublecount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("双6".equals(quizOrders.getQuizIntroduces())) {
                                if (6 == doublecount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("双7".equals(quizOrders.getQuizIntroduces())) {
                                if (7 == doublecount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }
                        }
                    }
                    if("大".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                if (("大0".equals(quizSuborders.getBettingContent())&&0 == bigcount)||
                                        ("大1".equals(quizSuborders.getBettingContent())&&1 == bigcount)||
                                        ("大2".equals(quizSuborders.getBettingContent())&&2 == bigcount)||
                                        ("大3".equals(quizSuborders.getBettingContent())&&3 == bigcount)||
                                        ("大4".equals(quizSuborders.getBettingContent())&&4 == bigcount)||
                                        ("大5".equals(quizSuborders.getBettingContent())&&5 == bigcount)||
                                        ("大6".equals(quizSuborders.getBettingContent())&&6 == bigcount)||
                                        ("大7".equals(quizSuborders.getBettingContent())&&7 == bigcount)) {
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                } else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                                quizSuborders.setUpdateBy(sysUser.getUsername());
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        }else {
                            if ("大0".equals(quizOrders.getQuizIntroduces())) {
                                if (0 == bigcount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("大1".equals(quizOrders.getQuizIntroduces())) {
                                if (1 == bigcount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("大2".equals(quizOrders.getQuizIntroduces())) {
                                if (2 == bigcount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("大3".equals(quizOrders.getQuizIntroduces())) {
                                if (3 == bigcount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("大4".equals(quizOrders.getQuizIntroduces())) {
                                if (4 == bigcount)
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                            }else if ("大5".equals(quizOrders.getQuizIntroduces())) {
                                if (5 == bigcount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("大6".equals(quizOrders.getQuizIntroduces())) {
                                if (6 == bigcount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("大7".equals(quizOrders.getQuizIntroduces())) {
                                if (7 == bigcount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }
                        }
                    }
                    if("小".equals(quizOrders.getQuizDetailsName())) {//分类三类
                        if (null != quizSubordersList && quizSubordersList.size() > 0) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                if (("小0".equals(quizSuborders.getBettingContent())&&0 == smallcount)||
                                        ("小1".equals(quizSuborders.getBettingContent())&&1 == smallcount)||
                                        ("小2".equals(quizSuborders.getBettingContent())&&2 == smallcount)||
                                        ("小3".equals(quizSuborders.getBettingContent())&&3 == smallcount)||
                                        ("小4".equals(quizSuborders.getBettingContent())&&4 == smallcount)||
                                        ("小5".equals(quizSuborders.getBettingContent())&&5 == smallcount)||
                                        ("小6".equals(quizSuborders.getBettingContent())&&6 == smallcount)||
                                        ("小7".equals(quizSuborders.getBettingContent())&&7 == smallcount)) {
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                    quizSuborders.setWinMount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()));
                                } else {
                                    winLoseAmount = winLoseAmount.add(quizSuborders.getTotalPrice().negate());
                                    quizSuborders.setWinMount(BigDecimal.ZERO);
                                    quizSuborders.setWinLoseAmount(quizSuborders.getTotalPrice().negate());
                                }
                                quizSuborders.setUpdateBy(sysUser.getUsername());
                                quizSuborders.setUpdateTime(new Date());
                                subordersList.add(quizSuborders);
                            }
                        }else {
                            if ("小0".equals(quizOrders.getQuizIntroduces())) {
                                if (0 == smallcount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("小1".equals(quizOrders.getQuizIntroduces())) {
                                if (1 == smallcount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("小2".equals(quizOrders.getQuizIntroduces())) {
                                if (2 == smallcount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("小3".equals(quizOrders.getQuizIntroduces())) {
                                if (3 == smallcount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("小4".equals(quizOrders.getQuizIntroduces())) {
                                if (4 == smallcount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("小5".equals(quizOrders.getQuizIntroduces())) {
                                if (5 == smallcount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("小6".equals(quizOrders.getQuizIntroduces())) {
                                if (6 == smallcount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }else if ("小7".equals(quizOrders.getQuizIntroduces())) {
                                if (7 == smallcount) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    winLoseAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice());
                                }else {
                                    winLoseAmount = quizOrders.getTotalPrice().negate();
                                }
                            }
                        }
                    }
                }
            }

            if(winAmount.compareTo(BigDecimal.ZERO)!=1){
                quizOrders.setWinMount(winAmount);//赢取金额
                quizOrders.setWinLoseAmount(winLoseAmount);//输赢金额（扣除本金）
                quizOrders.setStatus(OrderStatusEnum.ORDER_THREE.getStatus());

                MoneyLog moneyLog = new MoneyLog();
                moneyLog.setUserId(sysUser.getId());
                moneyLog.setUserName(sysUser.getUsername());
                moneyLog.setOrderNo(quizOrders.getOrderNo());
                moneyLog.setOrderType(MbChangeTypeEnum.BETTINGWIN.getType());//账变类型
                moneyLog.setOrderTypeName(MbChangeTypeEnum.BETTINGWIN.getName());//账变类型名称
                moneyLog.setBeforeMoney(currentBalance);//账变前金额
                moneyLog.setMoney(winAmount);//账变金额
                currentBalance = currentBalance.add(winAmount);
                moneyLog.setAfterMoney(currentBalance);//账变后金额
                moneyLog.setCreateTime(new Date());
                moneyLog.setCreateBy(sysUser.getUsername());
                moneyLog.setUpdateTime(new Date());
                moneyLog.setUpdateBy(sysUser.getUsername());
                moneyLogList.add(moneyLog);

                //扣除M币
                userService.addRewardMb(sysUser, winAmount);
            }else{//未中奖
                quizOrders.setWinMount(BigDecimal.ZERO);//赢取金额
                quizOrders.setWinLoseAmount(quizOrders.getTotalPrice().negate());//输赢金额（本金为负）
                quizOrders.setStatus(OrderStatusEnum.ORDER_FOUR.getStatus());
            }
            ordersList.add(quizOrders);
        }
        if(null!=moneyLogList && moneyLogList.size()>0) {
            //账变记录
            moneyLogService.saveMoneyLogBatch(moneyLogList);
        }
        //更新投注
        siteOrderService.saveOrUpdateQuizOrdersBatch(ordersList);
        if(null!=subordersList && subordersList.size()>0) {
            //批量更新子单
            quizSubordersService.saveOrUpdateQuizSubordersBatch(subordersList);
        }
    }
    private BigDecimal zhengxiao(QuizSuborders quizSuborders,String zodiacStr,String zodiac){
        int count = 0;//次数
        int index = 0;
        while((index=zodiacStr.indexOf(zodiac))!=-1){
            index = zodiac.length() + index;
            count++;
            zodiacStr = zodiacStr.substring(index, zodiacStr.length());
        }
        //对开奖的六个正码对应的生肖进行投注。 【最大赔率：1.9】
        //范例： 投注号码：正肖-猪开奖号码：03 08 41 32 21 38+25，开奖号码38在下注生肖范围内，故中奖。
        //中奖说明：当期开奖的前6个号码（不含特码，不分先后顺序），其中有一个号码在投注的生肖范围内即算中奖。如果有N个号码在某一投注的生肖范围内，中奖金额=本金”（赔率-1）“中奖次数+本金。
        if(count==1){
            return quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds());
        }else if(count>1){
            return quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()).subtract(quizSuborders.getTotalPrice()).multiply(BigDecimal.valueOf(count)).add(quizSuborders.getTotalPrice());
        }else {
            return BigDecimal.ZERO;
        }
    }
    private BigDecimal zhengxiao(QuizOrders quizOrders,String zodiacStr,String zodiac){
        int count = 0;//次数
        int index = 0;
        while((index=zodiacStr.indexOf(zodiac))!=-1){
            index = zodiac.length() + index;
            count++;
            zodiacStr = zodiacStr.substring(index, zodiacStr.length());
        }
        //对开奖的六个正码对应的生肖进行投注。 【最大赔率：1.9】
        //范例： 投注号码：正肖-猪开奖号码：03 08 41 32 21 38+25，开奖号码38在下注生肖范围内，故中奖。
        //中奖说明：当期开奖的前6个号码（不含特码，不分先后顺序），其中有一个号码在投注的生肖范围内即算中奖。如果有N个号码在某一投注的生肖范围内，中奖金额=本金”（赔率-1）“中奖次数+本金。
        if(count==1){
            return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }else if(count>1){
            return quizOrders.getTotalPrice().multiply(quizOrders.getOdds()).subtract(quizOrders.getTotalPrice()).multiply(BigDecimal.valueOf(count)).add(quizOrders.getTotalPrice());
        }else {
            return BigDecimal.ZERO;
        }
    }
    private String winZodiac(String winNumber,List<String> shuList,List<String> niuList,List<String> huList,List<String> tuzList,List<String> longList,List<String> sheList,
                             List<String> maList,List<String> yanList,List<String> houList,List<String> jiList,List<String> gouList,List<String> zhuList){
        //鼠、牛、虎、兔、龙、蛇、马、羊、猴、鸡、狗、猪
        if (Arrays.asList(shuList).contains(winNumber)){
            return "鼠";
        }else if (Arrays.asList(niuList).contains(winNumber)){
            return "牛";
        }else if (Arrays.asList(huList).contains(winNumber)){
            return "虎";
        }else if (Arrays.asList(tuzList).contains(winNumber)){
            return "兔";
        }else if (Arrays.asList(longList).contains(winNumber)){
            return "龙";
        }else if (Arrays.asList(sheList).contains(winNumber)){
            return "蛇";
        }else if (Arrays.asList(maList).contains(winNumber)){
            return "马";
        }else if (Arrays.asList(yanList).contains(winNumber)){
            return "羊";
        }else if (Arrays.asList(houList).contains(winNumber)){
            return "猴";
        }else if (Arrays.asList(jiList).contains(winNumber)){
            return "鸡";
        }else if (Arrays.asList(gouList).contains(winNumber)){
            return "狗";
        }else if (Arrays.asList(zhuList).contains(winNumber)){
            return "猪";
        }
        return "";
    }
    /**
     * 香港六合彩结算
     */
    @Override
    public void winningSettlement(Integer lotteryId){

        Map<String, Object> params = new HashMap<>();
        params.put("lotteryId", lotteryId);
        List<SiteLotteryVo> siteLotteryVOList = lotteryService.findList(params);
        //彩种最大
        WnData wnData = wnDataService.lastOneWnData(lotteryId);
        if(null!=wnData){
            //判断是否当前日期开奖号码
            if(!DateUtil.dateToyyyyMMdd(new Date()).equals(DateUtil.dateToyyyyMMdd(wnData.getCreateTime()))){
                return;
            }

        }
        if(StatusEnum.ONE_TRUE.getStatus()==wnData.getStatus()){
            //已经结算完成
            return;
        }
        wnData.setIsDisplay(StatusEnum.ONE_TRUE.getStatus());
        wnDataService.updateWnData(wnData);
        //彩种状态修改为结算中
        lotteryService.updateLotteryStatus(lotteryId, StatusEnum.ONE_TRUE.getStatus());
        String[] wnNumbers = wnData.getNumbers().split(",");
        String one = wnNumbers[0];
        String two = wnNumbers[1];
        String three = wnNumbers[2];
        String four = wnNumbers[3];
        String five = wnNumbers[4];
        String six = wnNumbers[5];
        String seven = wnNumbers[6];
        String[] wnNumbersSix = {wnNumbers[0],wnNumbers[1],wnNumbers[2],wnNumbers[3],wnNumbers[4],wnNumbers[5]};
        int seven_ones = Integer.parseInt(seven)%10;
        int seven_tens = Integer.parseInt(seven)/10%10;
        //大单:
        List<String> bigSingle = new ArrayList<>();
        bigSingle.add("25");bigSingle.add("27");bigSingle.add("29");bigSingle.add("31");bigSingle.add("33");bigSingle.add("35");bigSingle.add("37");bigSingle.add("39");
        bigSingle.add("39");bigSingle.add("41");bigSingle.add("43");bigSingle.add("45");bigSingle.add("47");
        //大双:
        List<String> bigDouble = new ArrayList<>();
        bigDouble.add("26");bigDouble.add("28");bigDouble.add("30");bigDouble.add("32");bigDouble.add("34");bigDouble.add("36");bigDouble.add("38");bigDouble.add("40");
        bigDouble.add("42");bigDouble.add("44");bigDouble.add("46");bigDouble.add("48");
        //小单:
        List<String> smallSingle = new ArrayList<>();
        smallSingle.add("01");smallSingle.add("03");smallSingle.add("05");smallSingle.add("07");smallSingle.add("09");smallSingle.add("11");smallSingle.add("13");smallSingle.add("15");
        smallSingle.add("17");smallSingle.add("19");smallSingle.add("21");smallSingle.add("23");
        //小双:
        List<String> smallDouble = new ArrayList<>();
        smallDouble.add("02");smallDouble.add("04");smallDouble.add("06");smallDouble.add("08");smallDouble.add("10");smallDouble.add("12");smallDouble.add("14");smallDouble.add("16");
        smallDouble.add("18");smallDouble.add("20");smallDouble.add("22");smallDouble.add("24");
        List<NumberAttributes> numberList = numberAttributesService.findList();
        //金木水火土
        List<String> jinList = new ArrayList<>();
        List<String> muList = new ArrayList<>();
        List<String> shuiList = new ArrayList<>();
        List<String> huoList = new ArrayList<>();
        List<String> tuList = new ArrayList<>();
        //鼠、牛、虎、兔、龙、蛇、马、羊、猴、鸡、狗、猪
        List<String> shuList = new ArrayList<>();
        List<String> niuList = new ArrayList<>();
        List<String> huList = new ArrayList<>();
        List<String> tuzList = new ArrayList<>();
        List<String> longList = new ArrayList<>();
        List<String> sheList = new ArrayList<>();
        List<String> maList = new ArrayList<>();
        List<String> yanList = new ArrayList<>();
        List<String> houList = new ArrayList<>();
        List<String> jiList = new ArrayList<>();
        List<String> gouList = new ArrayList<>();
        List<String> zhuList = new ArrayList<>();

        //红波蓝波绿波
        List<String> hongboList = new ArrayList<>();
        List<String> lvboList = new ArrayList<>();
        List<String> lanboList = new ArrayList<>();
        //家野
        List<String> jiaList = new ArrayList<>();
        List<String> yeList = new ArrayList<>();
        //天
        List<String> skyList = new ArrayList<>();
        skyList.add("牛");skyList.add("兔");skyList.add("龙");skyList.add("马");skyList.add("猴");skyList.add("猪");
        //地
        List<String> landList = new ArrayList<>();
        landList.add("鼠");landList.add("虎");landList.add("蛇");landList.add("羊");landList.add("鸡");landList.add("狗");
        //前
        List<String> beforeList = new ArrayList<>();
        beforeList.add("鼠");beforeList.add("牛");beforeList.add("虎");beforeList.add("兔");beforeList.add("龙");beforeList.add("蛇");
        //后
        List<String> afterList = new ArrayList<>();
        afterList.add("马");afterList.add("羊");afterList.add("猴");afterList.add("鸡");afterList.add("狗");afterList.add("猪");
        int bigcount = 0;//7码中大总数
        int smallcount = 0;//7码中小总数
        int doublecount = 0;//7码中双总数
        int singlecount = 0;//7码中单总数
        //0头、1头、2头、3头、4头
        String[] head0 = {"01","02","03","04","05","06","07","08","09"};
        String[] head1 = {"10","11","12","13","14","15","16","17","18","19"};
        String[] head2 = {"20","21","22","23","24","25","26","27","28","29"};
        String[] head3 = {"30","31","32","33","34","35","36","37","38","39"};
        String[] head4 = {"40","41","42","43","44","45","46","47","48","49"};
        //0尾、1尾、2尾、3尾、4尾、5尾、6尾、7尾、8尾、9尾
        String[] tail0 = {"10","20","30","40"};
        String[] tail1 = {"01","11","21","31","41"};
        String[] tail2 = {"02","12","22","32","42"};
        String[] tail3 = {"03","13","23","33","43"};
        String[] tail4 = {"04","14","24","34","44"};
        String[] tail5 = {"05","15","25","35","45"};
        String[] tail6 = {"06","16","26","36","46"};
        String[] tail7 = {"07","17","27","37","47"};
        String[] tail8 = {"08","18","28","38","48"};
        String[] tail9 = {"09","19","29","39","49"};
        String[] wnHead = new String[7];
        String[] wnTail = new String[7];
        for(int i=0;i<wnNumbers.length;i++){
            String wnnumber = wnNumbers[i];
            //大：特别码大于或等于25
            if (Integer.parseInt(wnnumber) >= 25) {
                bigcount = bigcount+1;
            }
            //小：特别码小于或等于24
            if (Integer.parseInt(wnnumber) <= 24) {
                smallcount = smallcount+1;
            }
            //单：特别码为奇数
            if (Integer.parseInt(wnnumber) % 2 != 0) {
                singlecount = singlecount+1;
            }
            //双：特别码为偶数
            if (Integer.parseInt(wnnumber) % 2 == 0) {
                doublecount = doublecount+1;
            }
            wnHead[i] = Integer.parseInt(wnnumber)/10%10+"头";
            wnTail[i] = Integer.parseInt(wnnumber)%10+"尾";

        }
        String oneZodiac = "";
        String twoZodiac = "";
        String threeZodiac = "";
        String fourZodiac = "";
        String fiveZodiac = "";
        String sixZodiac = "";
        String sevenZodiac = "";
        for(NumberAttributes number : numberList){
            if(one.equals(number.getNumber())){
                oneZodiac = number.getZodiac();
            }
            if(two.equals(number.getNumber())){
                twoZodiac = number.getZodiac();
            }
            if(three.equals(number.getNumber())){
                threeZodiac = number.getZodiac();
            }
            if(four.equals(number.getNumber())){
                fourZodiac = number.getZodiac();
            }
            if(five.equals(number.getNumber())){
                fiveZodiac = number.getZodiac();
            }
            if(six.equals(number.getNumber())){
                sixZodiac = number.getZodiac();
            }
            if(seven.equals(number.getNumber())){
                sevenZodiac = number.getZodiac();
            }
            //金木水火土
            if("金".equals(number.getFiveElements())){
                jinList.add(number.getNumber());
            }
            if("木".equals(number.getFiveElements())){
                muList.add(number.getNumber());
            }
            if("水".equals(number.getFiveElements())){
                shuiList.add(number.getNumber());
            }
            if("火".equals(number.getFiveElements())){
                huoList.add(number.getNumber());
            }
            if("土".equals(number.getFiveElements())){
                tuList.add(number.getNumber());
            }
            //鼠、牛、虎、兔、龙、蛇、马、羊、猴、鸡、狗、猪
            if("鼠".equals(number.getZodiac())){
                shuList.add(number.getNumber());
            }
            if("牛".equals(number.getZodiac())){
                niuList.add(number.getNumber());
            }
            if("虎".equals(number.getZodiac())){
                huList.add(number.getNumber());
            }
            if("兔".equals(number.getZodiac())){
                tuzList.add(number.getNumber());
            }
            if("龙".equals(number.getZodiac())){
                longList.add(number.getNumber());
            }
            if("蛇".equals(number.getZodiac())){
                sheList.add(number.getNumber());
            }
            if("马".equals(number.getZodiac())){
                maList.add(number.getNumber());
            }
            if("羊".equals(number.getZodiac())){
                yanList.add(number.getNumber());
            }
            if("猴".equals(number.getZodiac())){
                houList.add(number.getNumber());
            }
            if("鸡".equals(number.getZodiac())){
                jiList.add(number.getNumber());
            }
            if("狗".equals(number.getZodiac())){
                gouList.add(number.getNumber());
            }
            if("猪".equals(number.getZodiac())){
                zhuList.add(number.getNumber());
            }
            //红波蓝波绿波
            if("红波".equals(number.getColor())){
                hongboList.add(number.getNumber());
            }
            if("绿波".equals(number.getColor())){
                lvboList.add(number.getNumber());
            }
            if("蓝波".equals(number.getColor())){
                lanboList.add(number.getNumber());
            }
            //家野
            if("家".equals(number.getPoultryandbeast())){
                jiaList.add(number.getNumber());
            }
            if("野".equals(number.getPoultryandbeast())){
                yeList.add(number.getNumber());
            }
        }
        //开奖号码生肖集合
        List<String> winZodiacList = new ArrayList<>();
        winZodiacList.add(oneZodiac);
        winZodiacList.add(twoZodiac);
        winZodiacList.add(threeZodiac);
        winZodiacList.add(fourZodiac);
        winZodiacList.add(fiveZodiac);
        winZodiacList.add(sixZodiac);
        winZodiacList.add(sevenZodiac);
        //按商户结算
        for (SiteLotteryVo siteLotteryVO:siteLotteryVOList) {
            for (int i = 1; i <= 9000; i++) {//最大数据
                //分页查询
                Map<String, Object> paramspage = new HashMap<>();
                paramspage.put("page", i);//分页起始位置
                paramspage.put("limit", 10000);//分页结束位置
                paramspage.put("siteLotteryId", siteLotteryVO.getId());//商户彩种ID
                paramspage.put("status", OrderStatusEnum.ORDER_ONE.getStatus());//1 待开奖,2 已取消,3 中奖,4 未中奖
                paramspage.put("periods", wnData.getQihao());//期数
                PageResult<QuizOrders> pageResult = siteOrderService.findListByPage(paramspage);
                List<QuizOrders> list = pageResult.getData();
                if (null != list && list.size() > 0) {
                    this.winningSettlement(list,
                            one,two,three,four,five,six,seven,
                            seven_ones,seven_tens,
                            bigSingle,bigDouble,smallSingle,smallDouble,
                            oneZodiac,twoZodiac,threeZodiac,fourZodiac,fiveZodiac,sixZodiac,sevenZodiac,
                            jinList,muList,shuiList,huoList,tuList,
                            shuList,niuList,huList,tuzList,longList,sheList,maList,yanList,houList,jiList,gouList,zhuList,
                            hongboList,lvboList,lanboList,
                            jiaList,yeList,skyList,landList,beforeList,afterList,
                            winZodiacList,
                            bigcount,smallcount,doublecount,singlecount,
//                            head0,head1,head2,head3,head4,
//                            tail0,tail1,tail2,tail3,tail4,tail5,tail6,tail7,tail8,tail9,
                            wnTail,
                            wnNumbersSix,
                            wnNumbers);
                } else {
                    break;
                }
            }
        }
        //修改开彩结果为结算完成
        wnData.setStatus(StatusEnum.ONE_TRUE.getStatus());
        wnDataService.updateWnData(wnData);
        //彩种状态修改为结算完成
        lotteryService.updateLotteryStatus(lotteryId, StatusEnum.ZERO_FALSE.getStatus());
    }
    private BigDecimal daxiaodangshuanghonglvlanbo(String enterNumber,
                                                   Integer onesNumber,String quizIntroduces,BigDecimal totalPrice,BigDecimal odds,
                                                   List<String> hongboList,List<String> lvboList,List<String> lanboList){
        BigDecimal winAmount = BigDecimal.ZERO;
        //大：特别码大于或等于25
        if ("大".equals(quizIntroduces)) {
            if (Integer.parseInt(enterNumber) >= 25)
                winAmount = totalPrice.multiply(odds);
        }
        //小：特别码小于或等于24
        if ("小".equals(quizIntroduces)) {
            if (Integer.parseInt(enterNumber) <= 24)
                winAmount = totalPrice.multiply(odds);
        }
        //单：特别码为奇数
        if ("单".equals(quizIntroduces)) {
            if (Integer.parseInt(enterNumber) % 2 != 0)
                winAmount = totalPrice.multiply(odds);
        }
        //双：特别码为偶数
        if ("双".equals(quizIntroduces)) {
            if (Integer.parseInt(enterNumber) % 2 == 0)
                winAmount = totalPrice.multiply(odds);
        }
        //尾大：大于等于5为大
        if ("尾大".equals(quizIntroduces)) {
            if (onesNumber <= 5)
                winAmount = totalPrice.multiply(odds);
        }
        //尾小：小于等于4为小
        if ("尾小".equals(quizIntroduces)) {
            if (onesNumber <= 4)
                winAmount = totalPrice.multiply(odds);
        }
        if ("红波".equals(quizIntroduces)) {
            if (Arrays.asList(hongboList).contains(enterNumber))
                winAmount = totalPrice.multiply(odds);
        }
        if ("蓝波".equals(quizIntroduces)) {
            if (Arrays.asList(lanboList).contains(enterNumber))
                winAmount = totalPrice.multiply(odds);
        }
        if ("绿波".equals(quizIntroduces)) {
            if (Arrays.asList(lvboList).contains(enterNumber))
                winAmount = totalPrice.multiply(odds);
        }
        return winAmount;
    }
}
