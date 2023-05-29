package com.xxl.job.executor.service.impl;

import com.central.common.model.*;
import com.central.common.model.enums.LotteryEnum;
import com.central.common.model.enums.MbChangeTypeEnum;
import com.central.common.model.enums.OrderStatusEnum;
import com.central.common.model.enums.StatusEnum;
import com.central.common.utils.DateUtil;
import com.central.common.utils.SnowflakeIdWorker;
import com.xxl.job.executor.entity.vo.SiteLotteryVO;
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
public class WinningSettlementImpl {
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
    /**
     * 模拟新增千万级订单数据
     */
    public void add(){
        System.out.println("开始时间："+ DateUtil.dateToHHmmss(new Date()));
        for (int i=0;i<2000;i++){
            System.out.println("插入一万条开始时间："+ DateUtil.dateToHHmmss(new Date()));
            List<QuizOrders> ordersList = new ArrayList<>();
            for(int j=0;j<10000;j++){

                QuizOrders quizOrders = new QuizOrders();
                quizOrders.setPeriods("2023008");//期数
                quizOrders.setYear(2023);//年份
                quizOrders.setSiteLotteryId(1L);//站点彩种ID
                quizOrders.setLotteryName("香港六合彩");//站点彩种名称
                quizOrders.setSiteCategoryId(374l);//站点下注分类ID
                quizOrders.setBettingContent("特码");//站点下注分类名称
                quizOrders.setQuizId(1l);//开奖规则主表ID
                quizOrders.setQuizTitle("号码");//开奖规则主表标题
                quizOrders.setQuizChooseId(1L);//开奖规则明细ID
                quizOrders.setQuizIntroduce("01");//开奖规则明细名称
                quizOrders.setTotalPrice(BigDecimal.valueOf(100));//订单金额
                quizOrders.setUnits(1);//注数
                quizOrders.setOdds(BigDecimal.valueOf(48.8));//赔率

                String orderSn = SnowflakeIdWorker.createOrderSn();//订单号
                quizOrders.setOrderNo(orderSn);
                quizOrders.setSiteId(1L);//站点id
                quizOrders.setSiteCode("mks_site01");//站点编码
                quizOrders.setSiteName("总站");//站点名称
                quizOrders.setMemberId(143l);//会员ID
                quizOrders.setUserName("mks_site02_admin");//用户名
                quizOrders.setParentId(2L);//上级id
                quizOrders.setParentName("admin");//上级账号
                quizOrders.setCreateTime(new Date());
                quizOrders.setCreateBy("mks_site02_admin");
                quizOrders.setUpdateTime(new Date());
                quizOrders.setUpdateBy("mks_site02_admin");
                ordersList.add(quizOrders);
            }
            siteOrderService.saveBatch(ordersList);
            System.out.println("插入一万条结束时间："+ DateUtil.dateToHHmmss(new Date()));
        }
        System.out.println("结束时间："+ DateUtil.dateToHHmmss(new Date()));
    }
    /**
     * 结算
     * @return
     */
    @Transactional(rollbackFor={RuntimeException.class,Exception.class})
    public void winningSettlement(List<QuizOrders> list,
                              String one,String two,String three,String four,String five,String six,
                              String seven,int seven_ones,int seven_tens,
    String[] bigSingle,String[] bigDouble,String[] smallSingle,String[] smallDouble){
        List<QuizOrders> ordersList = new ArrayList<>();
        List<MoneyLog> moneyLogList = new ArrayList<>();
        for (QuizOrders quizOrders: list){
            SysUser sysUser = userService.getById(quizOrders.getMemberId());
            BigDecimal currentBalance = sysUser.getCurrentBalance();//用户当前余额
            quizOrders.setUpdateTime(new Date());
            quizOrders.setUpdateBy(sysUser.getUsername());
            BigDecimal winAmount = BigDecimal.ZERO;
            if(LotteryEnum.HONGKONG_MKS.getRemark().equals(quizOrders.getLotteryName())) {//香港六合彩
                if ("特码".equals(quizOrders.getBettingContent())) {//分类
                    if("号码".equals(quizOrders.getQuizTitle())){//号码
                        if(seven.equals(quizOrders.getQuizIntroduce())){//购买的号码相同为中奖
                            winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                        }
                    }else if("大小单双".equals(quizOrders.getQuizTitle())){//大小单双

                        if (!"49".equals(seven)) {//特别码49为和
                            //大：特别码大于或等于25
                            if ("大".equals(quizOrders.getQuizIntroduce())) {
                                if (Integer.parseInt(seven) >= 25)
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                            //小：特别码小于或等于24
                            if ("小".equals(quizOrders.getQuizIntroduce())) {
                                if (Integer.parseInt(seven) <= 24)
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                            //单：特别码为奇数
                            if ("单".equals(quizOrders.getQuizIntroduce())) {
                                if (Integer.parseInt(seven) % 2 != 0)
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                            //双：特别码为偶数
                            if ("双".equals(quizOrders.getQuizIntroduce())) {
                                if (Integer.parseInt(seven) % 2 == 0)
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                            //合大：和值大于或等于7
                            if ("合大".equals(quizOrders.getQuizIntroduce())) {
                                if (seven_ones + seven_tens >= 7)
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                            //合小：和值小于或等于6合数小
                            if ("合小".equals(quizOrders.getQuizIntroduce())) {
                                if (seven_ones + seven_tens <= 6)
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                            //合单：和值为奇数
                            if ("合单".equals(quizOrders.getQuizIntroduce())) {
                                if ((seven_ones + seven_tens) % 2 != 0)
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                            //合双：和值为偶数
                            if ("合双".equals(quizOrders.getQuizIntroduce())) {
                                if ((seven_ones + seven_tens) % 2 == 0)
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                            //大单
                            if ("大单".equals(quizOrders.getQuizIntroduce())) {
                                if (Arrays.asList(bigSingle).contains(seven))
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                            //大双
                            if ("大双".equals(quizOrders.getQuizIntroduce())) {
                                if (Arrays.asList(bigDouble).contains(seven))
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                            //小单
                            if ("小单".equals(quizOrders.getQuizIntroduce())) {
                                if (Arrays.asList(smallSingle).contains(seven))
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                            //小双
                            if ("小双".equals(quizOrders.getQuizIntroduce())) {
                                if (Arrays.asList(smallDouble).contains(seven))
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                            //尾大：大于等于5为大
                            if ("尾大".equals(quizOrders.getQuizIntroduce())) {
                                if (seven_ones <= 5)
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                            //尾小：小于等于4为小
                            if ("尾小".equals(quizOrders.getQuizIntroduce())) {
                                if (seven_ones <= 4)
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                        }
                    }else if("生肖".equals(quizOrders.getQuizTitle())){//生肖

                    }else if("家野".equals(quizOrders.getQuizTitle())){//家野

                    }else if("波色".equals(quizOrders.getQuizTitle())){//波色

                    }
                }
            }

            if(winAmount.compareTo(BigDecimal.ZERO)==1){//中奖
                quizOrders.setWinMount(winAmount);//赢取金额
                quizOrders.setWinLoseAmount(winAmount.subtract(quizOrders.getTotalPrice()));//输赢金额（扣除本金）
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
            }else {//未中奖
//                quizOrders.setWinMount();//赢取金额
                quizOrders.setWinLoseAmount(quizOrders.getTotalPrice().negate());//输赢金额（本金为负）
                quizOrders.setStatus(OrderStatusEnum.ORDER_FOUR.getStatus());
            }
            ordersList.add(quizOrders);
        }
        if(null!=moneyLogList && moneyLogList.size()>0) {
            //账变记录
            moneyLogService.saveBatch(moneyLogList);
        }
        //更新投注
        siteOrderService.saveOrUpdateBatch(ordersList);
    }
    /**
     * 香港六合彩结算
     */
    public void winningSettlementHongkong(){

        Map<String, Object> params = new HashMap<>();
        params.put("lotteryId", LotteryEnum.HONGKONG_MKS.getStatus());
        List<SiteLotteryVO> siteLotteryVOList = lotteryService.findList(params);
        //彩种最大
        WnData wnData = wnDataService.lastOneWnData(LotteryEnum.HONGKONG_MKS.getStatus());
        if(StatusEnum.ONE_TRUE.getStatus()==wnData.getStatus()){
            //已经结算完成
            return;
        }
        //彩种状态修改为结算中
        lotteryService.updateLotteryStatus(LotteryEnum.HONGKONG_MKS.getStatus(), StatusEnum.ONE_TRUE.getStatus());
        String[] wnNumbers = wnData.getNumbers().split(",");
        String one = wnNumbers[0];
        String two = wnNumbers[1];
        String three = wnNumbers[2];
        String four = wnNumbers[3];
        String five = wnNumbers[4];
        String six = wnNumbers[5];
        String seven = wnNumbers[6];
        int seven_ones = Integer.parseInt(seven)%10;
        int seven_tens = Integer.parseInt(seven)/10%10;
        //大单:
        String[] bigSingle = {"25","27","29","31","33","35","37","39","41","43","45","47"};
        //大双:
        String[] bigDouble = {"26","28","30","32","34","36","38","40","42","44","46","48"};
        //小单:
        String[] smallSingle = {"01","03","05","07","09","11","13","15","17","19","21","23"};
        //小双:
        String[] smallDouble = {"02","04","06","08","10","12","14","16","18","20","22","24"};
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
        for(NumberAttributes number : numberList){
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
//            List<String> hongboList = new ArrayList<>();
//            List<String> lvboList = new ArrayList<>();
//            List<String> lanboList = new ArrayList<>();
            //家野
//            List<String> jiaList = new ArrayList<>();
//            List<String> yeList = new ArrayList<>();
        }
        //按站点结算
        for (SiteLotteryVO siteLotteryVO:siteLotteryVOList) {
            for (int i = 1; i <= 9000; i++) {//最大数据
                //分页查询
                Map<String, Object> paramspage = new HashMap<>();
                paramspage.put("page", i);//分页起始位置
                paramspage.put("limit", 10000);//分页结束位置
                paramspage.put("siteLotteryId", siteLotteryVO.getId());//站点彩种ID
                paramspage.put("status", OrderStatusEnum.ORDER_ONE.getStatus());//1 待开奖,2 已取消,3 中奖,4 未中奖
                paramspage.put("periods", wnData.getQihao());//期数
                PageResult<QuizOrders> pageResult = siteOrderService.findList(paramspage);
                List<QuizOrders> list = pageResult.getData();
                if (null != list && list.size() > 0) {
                    this.winningSettlement(list,
                            one,two,three,four,five,six,seven,
                            seven_ones,seven_tens,
                            bigSingle,bigDouble,smallSingle,smallDouble);
                } else {
                    break;
                }
            }
        }
        //彩种状态修改为结算完成
        lotteryService.updateLotteryStatus(LotteryEnum.HONGKONG_MKS.getStatus(), StatusEnum.ZERO_FALSE.getStatus());
    }
    /**
     * 澳门六合彩结算
     */
    public void winningSettlementMacao(){
        //

    }
    /**
     * 台湾六合彩结算
     */
    public void winningSettlementTaiwan(){
        //

    }
    /**
     * 新加坡六合彩结算
     */
    public void winningSettlementSingapore(){
        //

    }
}
