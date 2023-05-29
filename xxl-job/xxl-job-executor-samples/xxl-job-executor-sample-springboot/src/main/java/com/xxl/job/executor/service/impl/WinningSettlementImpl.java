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
    String[] bigSingle,String[] bigDouble,String[] smallSingle,String[] smallDouble,
                                  List<String> jinList,List<String> muList,List<String> shuiList,List<String> huoList,List<String> tuList,
                                  List<String> shuList,List<String> niuList,List<String> huList,List<String> tuzList,List<String> longList,List<String> sheList,
                                  List<String> maList,List<String> yanList,List<String> houList,List<String> jiList,List<String> gouList,List<String> zhuList,
                                  List<String> hongboList,List<String> lvboList,List<String> lanboList,
                                  List<String> jiaList,List<String> yeList,
                                  String winZodiacStr){
        List<QuizOrders> ordersList = new ArrayList<>();
        List<MoneyLog> moneyLogList = new ArrayList<>();
        for (QuizOrders quizOrders: list){
            SysUser sysUser = userService.getById(quizOrders.getMemberId());
            BigDecimal currentBalance = sysUser.getCurrentBalance();//用户当前余额
            quizOrders.setUpdateTime(new Date());
            quizOrders.setUpdateBy(sysUser.getUsername());
            BigDecimal winAmount = BigDecimal.ZERO;
            if(LotteryEnum.HONGKONG_MKS.getRemark().equals(quizOrders.getLotteryName())) {//香港六合彩
                if ("特码".equals(quizOrders.getSiteCategoryName())) {//分类
                    if("特码".equals(quizOrders.getQuizTitle())){//号码
                        if(seven.equals(quizOrders.getQuizIntroduce())){//购买的号码相同为中奖
                            winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                        }
                    }else if("大小单双".equals(quizOrders.getQuizTitle())){//大小单双
                        if (!"49".equals(seven)) {//特别码49为和
                            winAmount = this.daxiaodangshuanghonglvlanbo(quizOrders,seven,seven_ones,seven_tens,bigSingle,bigDouble,smallSingle,smallDouble,hongboList,lvboList,lanboList);
                        }else {
                            winAmount = quizOrders.getTotalPrice().negate();
                        }
                    }else if("生肖".equals(quizOrders.getQuizTitle())){//生肖
                        //鼠、牛、虎、兔、龙、蛇、马、羊、猴、鸡、狗、猪
                        if ("鼠".equals(quizOrders.getQuizIntroduce())) {
                            if (Arrays.asList(shuList).contains(seven))
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                        }
                        if ("牛".equals(quizOrders.getQuizIntroduce())) {
                            if (Arrays.asList(niuList).contains(seven))
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                        }
                        if ("虎".equals(quizOrders.getQuizIntroduce())) {
                            if (Arrays.asList(huList).contains(seven))
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                        }
                        if ("兔".equals(quizOrders.getQuizIntroduce())) {
                            if (Arrays.asList(tuzList).contains(seven))
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                        }
                        if ("龙".equals(quizOrders.getQuizIntroduce())) {
                            if (Arrays.asList(longList).contains(seven))
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                        }
                        if ("蛇".equals(quizOrders.getQuizIntroduce())) {
                            if (Arrays.asList(sheList).contains(seven))
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                        }
                        if ("马".equals(quizOrders.getQuizIntroduce())) {
                            if (Arrays.asList(maList).contains(seven))
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                        }
                        if ("羊".equals(quizOrders.getQuizIntroduce())) {
                            if (Arrays.asList(yanList).contains(seven))
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                        }
                        if ("猴".equals(quizOrders.getQuizIntroduce())) {
                            if (Arrays.asList(houList).contains(seven))
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                        }
                        if ("鸡".equals(quizOrders.getQuizIntroduce())) {
                            if (Arrays.asList(jiList).contains(seven))
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                        }
                        if ("狗".equals(quizOrders.getQuizIntroduce())) {
                            if (Arrays.asList(gouList).contains(seven))
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                        }
                        if ("猪".equals(quizOrders.getQuizIntroduce())) {
                            if (Arrays.asList(zhuList).contains(seven))
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                        }
                    }else if("家野".equals(quizOrders.getQuizTitle())){//家野
                        if ("家".equals(quizOrders.getQuizIntroduce())) {
                            if (Arrays.asList(jiaList).contains(seven))
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                        }
                        if ("野".equals(quizOrders.getQuizIntroduce())) {
                            if (Arrays.asList(yeList).contains(seven))
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                        }
                    }else if("波色".equals(quizOrders.getQuizTitle())){//波色
                        winAmount = this.daxiaodangshuanghonglvlanbo(quizOrders,seven,seven_ones,seven_tens,bigSingle,bigDouble,smallSingle,smallDouble,hongboList,lvboList,lanboList);
//                        if ("金".equals(quizOrders.getQuizIntroduce())) {
//                            if (Arrays.asList(jinList).contains(seven))
//                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
//                        }
//                        if ("木".equals(quizOrders.getQuizIntroduce())) {
//                            if (Arrays.asList(muList).contains(seven))
//                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
//                        }
//                        if ("水".equals(quizOrders.getQuizIntroduce())) {
//                            if (Arrays.asList(shuiList).contains(seven))
//                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
//                        }
//                        if ("火".equals(quizOrders.getQuizIntroduce())) {
//                            if (Arrays.asList(huoList).contains(seven))
//                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
//                        }
//                        if ("土".equals(quizOrders.getQuizIntroduce())) {
//                            if (Arrays.asList(tuList).contains(seven))
//                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
//                        }
                    }
                }else if ("生肖".equals(quizOrders.getSiteCategoryName())) {//分类
                    if("平特一肖".equals(quizOrders.getQuizTitle())) {
                        //当期开奖的全部号码(前6个号码和特别码)，其中只要有一个号码在投注的生肖范围则中奖；没有一个球号在投注的生肖范围内，则不中奖;多个球号在投注生肖范围内，则中奖；但奖金不倍增，派彩只派一次，即不论同生肖号码出现一个或多个号码都只派一次。
                        winAmount = pingteyixiao(quizOrders,
                                one,two,three,four,five,six,seven,
                                shuList,niuList,huList,tuzList,longList,sheList,
                                maList,yanList,houList,jiList,gouList,zhuList);
                    }else if("合肖".equals(quizOrders.getQuizTitle())) {
                        if(!"49".equals(seven)){
                            if(seven.indexOf(quizOrders.getBettingContent())!=-1) {
                                //选择2-11个生肖，所选生肖为开奖号码的特别码对应的生肖，即为中奖（和局:特别码为49，退还本金）；如投注方案为猴、虎，开奖号码为01(兔),02(虎),03(牛),04(鼠),05(猪),06(狗) + 08(猴)，即中合肖。
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                        }else {//和
                            winAmount = quizOrders.getTotalPrice().negate();
                        }

                    }else if("连肖".equals(quizOrders.getQuizTitle())) {
                        if ("二肖连中".equals(quizOrders.getQuizIntroduce())) {
                            //选择2个或2个以上生肖，所选生肖与开奖号码所对应的生肖一致，即中奖；如投注方案为牛、鼠，开奖号码为：01(虎),02(牛),03(鼠),04(猪),05(狗),06(鸡) + 07(猴)，即中二连肖。
                            String[] bettingZodiacStr = quizOrders.getBettingContent().split(",");//投注
                            List<String> bettingZodiacList = this.getBettingZodiacList(bettingZodiacStr);
                            for(String bettingZodiac: bettingZodiacList){
                                if(bettingZodiac.indexOf(winZodiacStr)!=-1) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    break;
                                }
                            }
                        }
                        if ("三肖连中".equals(quizOrders.getQuizIntroduce())) {
                            //选择3个或3个以上生肖，所选生肖与开奖号码所对应的生肖一致，即中奖；如投注方案为牛、鼠、猪，开奖号码为：01(虎),02(牛),03(鼠),04(猪),05(狗),06(鸡) + 07(猴)，即中三连肖。
                            String[] bettingZodiacStr = quizOrders.getBettingContent().split(",");//投注
                            List<String> bettingZodiacList = this.getBettingZodiacList(bettingZodiacStr);
                            for(String bettingZodiac: bettingZodiacList){
                                if(bettingZodiac.indexOf(winZodiacStr)!=-1) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    break;
                                }
                            }
                        }
                        if ("四肖连中".equals(quizOrders.getQuizIntroduce())) {
                            //选择4个或4个以上生肖，所选生肖与开奖号码所对应的生肖一致，即中奖；如投注方案为牛、鼠、猪、狗，开奖号码为：01(虎),02(牛),03(鼠),04(猪),05(狗),06(鸡) + 07(猴)，即中四连肖。
                            String[] bettingZodiacStr = quizOrders.getBettingContent().split(",");//投注
                            List<String> bettingZodiacList = this.getBettingZodiacList(bettingZodiacStr);
                            for(String bettingZodiac: bettingZodiacList){
                                if(bettingZodiac.indexOf(winZodiacStr)!=-1) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    break;
                                }
                            }
                        }
                        if ("五肖连中".equals(quizOrders.getQuizIntroduce())) {
                            //选择5个或5个以上生肖，所选生肖与开奖号码所对应的生肖一致，即中奖；如投注方案为牛、鼠、猪、狗、鸡，开奖号码为：01(虎),02(牛),03(鼠),04(猪),05(狗),06(鸡) + 07(猴)，即中五连肖。
                            String[] bettingZodiacStr = quizOrders.getBettingContent().split(",");//投注
                            List<String> bettingZodiacList = this.getBettingZodiacList(bettingZodiacStr);
                            for(String bettingZodiac: bettingZodiacList){
                                if(bettingZodiac.indexOf(winZodiacStr)!=-1) {
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                    break;
                                }
                            }
                        }
                        if ("二肖连不中".equals(quizOrders.getQuizIntroduce())) {
                            String[] bettingZodiacStr = quizOrders.getBettingContent().split(",");//投注
                            List<String> bettingZodiacList = this.getBettingZodiacList(bettingZodiacStr);
                            boolean b = true;
                            for(String bettingZodiac: bettingZodiacList){
                                if(bettingZodiac.indexOf(winZodiacStr)!=-1) {
                                    b = false;
                                    break;
                                }
                            }
                            if(b){
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                        }
                        if ("三肖连不中".equals(quizOrders.getQuizIntroduce())) {
                            String[] bettingZodiacStr = quizOrders.getBettingContent().split(",");//投注
                            List<String> bettingZodiacList = this.getBettingZodiacList(bettingZodiacStr);
                            boolean b = true;
                            for(String bettingZodiac: bettingZodiacList){
                                if(bettingZodiac.indexOf(winZodiacStr)!=-1) {
                                    b = false;
                                    break;
                                }
                            }
                            if(b){
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                        }
                        if ("四肖连不中".equals(quizOrders.getQuizIntroduce())) {
                            String[] bettingZodiacStr = quizOrders.getBettingContent().split(",");//投注
                            List<String> bettingZodiacList = this.getBettingZodiacList(bettingZodiacStr);
                            boolean b = true;
                            for(String bettingZodiac: bettingZodiacList){
                                if(bettingZodiac.indexOf(winZodiacStr)!=-1) {
                                    b = false;
                                    break;
                                }
                            }
                            if(b){
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                        }
                    }
                } else if ("半波".equals(quizOrders.getSiteCategoryName())) {//分类
                    if("半波".equals(quizOrders.getQuizTitle())) {
                        //以特码色波和特码单双大小为一个投注组合，当开出特码符合投注组合，即视为中奖。若开出特码为49号时，所有投注半波任意一个组合视为和局，其余情况视为不中奖。
                        if(!"49".equals(seven)){
                            if("红大".equals(quizOrders.getQuizIntroduce())){//特别码大于或等于25
                                if (Arrays.asList(hongboList).contains(seven)){
                                    if (Integer.parseInt(seven) >= 25)
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                }
                            }
                            if("红小".equals(quizOrders.getQuizIntroduce())){//特别码小于或等于24
                                if (Arrays.asList(hongboList).contains(seven)){
                                    if (Integer.parseInt(seven) <= 24)
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                }
                            }
                            if("红单".equals(quizOrders.getQuizIntroduce())){//特别码为奇数
                                if (Arrays.asList(hongboList).contains(seven)){
                                    if (Integer.parseInt(seven) % 2 != 0)
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                }
                            }
                            if("红双".equals(quizOrders.getQuizIntroduce())){//特别码为偶数
                                if (Arrays.asList(hongboList).contains(seven)){
                                    if (Integer.parseInt(seven) % 2 == 0)
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                }
                            }
                            if("红合单".equals(quizOrders.getQuizIntroduce())){//和值为奇数
                                if (Arrays.asList(hongboList).contains(seven)){
                                    if ((seven_ones + seven_tens) % 2 != 0)
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                }
                            }
                            if("红合双".equals(quizOrders.getQuizIntroduce())){//和值为偶数
                                if (Arrays.asList(hongboList).contains(seven)){
                                    if ((seven_ones + seven_tens) % 2 == 0)
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                }
                            }
                            if("蓝大".equals(quizOrders.getQuizIntroduce())){//特别码大于或等于25
                                if (Arrays.asList(lanboList).contains(seven)){
                                    if (Integer.parseInt(seven) >= 25)
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                }
                            }
                            if("蓝小".equals(quizOrders.getQuizIntroduce())){//特别码小于或等于24
                                if (Arrays.asList(lanboList).contains(seven)){
                                    if (Integer.parseInt(seven) <= 24)
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                }
                            }
                            if("蓝单".equals(quizOrders.getQuizIntroduce())){//特别码为奇数
                                if (Arrays.asList(lanboList).contains(seven)){
                                    if (Integer.parseInt(seven) % 2 != 0)
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                }
                            }
                            if("蓝双".equals(quizOrders.getQuizIntroduce())){//特别码为偶数
                                if (Arrays.asList(lanboList).contains(seven)){
                                    if (Integer.parseInt(seven) % 2 == 0)
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                }
                            }
                            if("蓝合单".equals(quizOrders.getQuizIntroduce())){//和值为奇数
                                if (Arrays.asList(lanboList).contains(seven)){
                                    if ((seven_ones + seven_tens) % 2 != 0)
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                }
                            }
                            if("蓝合双".equals(quizOrders.getQuizIntroduce())){//和值为偶数
                                if (Arrays.asList(lanboList).contains(seven)){
                                    if ((seven_ones + seven_tens) % 2 == 0)
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                }
                            }
                            if("绿大".equals(quizOrders.getQuizIntroduce())){//特别码大于或等于25
                                if (Arrays.asList(lvboList).contains(seven)){
                                    if (Integer.parseInt(seven) >= 25)
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                }
                            }
                            if("绿小".equals(quizOrders.getQuizIntroduce())){//特别码小于或等于24
                                if (Arrays.asList(lvboList).contains(seven)){
                                    if (Integer.parseInt(seven) <= 24)
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                }
                            }
                            if("绿单".equals(quizOrders.getQuizIntroduce())){//特别码为奇数
                                if (Arrays.asList(lvboList).contains(seven)){
                                    if (Integer.parseInt(seven) % 2 != 0)
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                }
                            }
                            if("绿双".equals(quizOrders.getQuizIntroduce())){//特别码为偶数
                                if (Arrays.asList(lvboList).contains(seven)){
                                    if (Integer.parseInt(seven) % 2 == 0)
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                }
                            }
                            if("绿合单".equals(quizOrders.getQuizIntroduce())){//和值为奇数
                                if (Arrays.asList(lvboList).contains(seven)){
                                    if ((seven_ones + seven_tens) % 2 != 0)
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                }
                            }
                            if("绿合双".equals(quizOrders.getQuizIntroduce())){//和值为偶数
                                if (Arrays.asList(lvboList).contains(seven)){
                                    if ((seven_ones + seven_tens) % 2 == 0)
                                        winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                                }
                            }
                        }else {//和
                            winAmount = quizOrders.getTotalPrice().negate();
                        }
                    }
                }else if ("正码".equals(quizOrders.getSiteCategoryName())) {//分类
                    if("大小单双".equals(quizOrders.getQuizTitle())) {//大小单双
                        if("总和大".equals(quizOrders.getQuizIntroduce())){//所有七个开奖号码的总和大于或者等于175
                            if (Integer.parseInt(one)+Integer.parseInt(two)+Integer.parseInt(three)+Integer.parseInt(four)+Integer.parseInt(five)+Integer.parseInt(six)+Integer.parseInt(seven) >= 175)
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                        }
                        if("总和小".equals(quizOrders.getQuizIntroduce())){//所有七个开奖号码的总和小于或者等于174
                            if (Integer.parseInt(one)+Integer.parseInt(two)+Integer.parseInt(three)+Integer.parseInt(four)+Integer.parseInt(five)+Integer.parseInt(six)+Integer.parseInt(seven) <= 174)
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                        }
                        if("总和单".equals(quizOrders.getQuizIntroduce())){//所有七个开奖号码的总和值为奇数
                            if ((Integer.parseInt(one)+Integer.parseInt(two)+Integer.parseInt(three)+Integer.parseInt(four)+Integer.parseInt(five)+Integer.parseInt(six)+Integer.parseInt(seven)) % 2 != 0)
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                        }
                        if("总和双".equals(quizOrders.getQuizIntroduce())){//所有七个开奖号码的总和值为偶数
                            if ((Integer.parseInt(one)+Integer.parseInt(two)+Integer.parseInt(three)+Integer.parseInt(four)+Integer.parseInt(five)+Integer.parseInt(six)+Integer.parseInt(seven)) % 2 == 0)
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                        }
                    }
                    if("正码1".equals(quizOrders.getQuizTitle())){
                        if(!"49".equals(one)){
                            int one_ones = Integer.parseInt(one)%10;
                            int one_tens = Integer.parseInt(one)/10%10;
                            winAmount = this.daxiaodangshuanghonglvlanbo(quizOrders,one,one_ones,one_tens,bigSingle,bigDouble,smallSingle,smallDouble,hongboList,lvboList,lanboList);
                        }else {//和
                            winAmount = quizOrders.getTotalPrice().negate();
                        }
                    }
                    if("正码2".equals(quizOrders.getQuizTitle())){
                        if(!"49".equals(two)){
                            int two_ones = Integer.parseInt(two)%10;
                            int two_tens = Integer.parseInt(two)/10%10;
                            winAmount = this.daxiaodangshuanghonglvlanbo(quizOrders,two,two_ones,two_tens,bigSingle,bigDouble,smallSingle,smallDouble,hongboList,lvboList,lanboList);
                        }else {//和
                            winAmount = quizOrders.getTotalPrice().negate();
                        }
                    }
                    if("正码3".equals(quizOrders.getQuizTitle())){
                        if(!"49".equals(three)){
                            int three_ones = Integer.parseInt(three)%10;
                            int three_tens = Integer.parseInt(three)/10%10;
                            winAmount = this.daxiaodangshuanghonglvlanbo(quizOrders,three,three_ones,three_tens,bigSingle,bigDouble,smallSingle,smallDouble,hongboList,lvboList,lanboList);
                        }else {//和
                            winAmount = quizOrders.getTotalPrice().negate();
                        }
                    }
                    if("正码4".equals(quizOrders.getQuizTitle())){
                        if(!"49".equals(four)){
                            int four_ones = Integer.parseInt(four)%10;
                            int four_tens = Integer.parseInt(four)/10%10;
                            winAmount = this.daxiaodangshuanghonglvlanbo(quizOrders,four,four_ones,four_tens,bigSingle,bigDouble,smallSingle,smallDouble,hongboList,lvboList,lanboList);
                        }else {//和
                            winAmount = quizOrders.getTotalPrice().negate();
                        }
                    }
                    if("正码5".equals(quizOrders.getQuizTitle())){
                        if(!"49".equals(five)){
                            int five_ones = Integer.parseInt(five)%10;
                            int five_tens = Integer.parseInt(five)/10%10;
                            winAmount = this.daxiaodangshuanghonglvlanbo(quizOrders,five,five_ones,five_tens,bigSingle,bigDouble,smallSingle,smallDouble,hongboList,lvboList,lanboList);
                        }else {//和
                            winAmount = quizOrders.getTotalPrice().negate();
                        }
                    }
                    if("正码6".equals(quizOrders.getQuizTitle())){
                        if(!"49".equals(six)){
                            int six_ones = Integer.parseInt(six)%10;
                            int six_tens = Integer.parseInt(six)/10%10;
                            winAmount = this.daxiaodangshuanghonglvlanbo(quizOrders,six,six_ones,six_tens,bigSingle,bigDouble,smallSingle,smallDouble,hongboList,lvboList,lanboList);
                        }else {//和
                            winAmount = quizOrders.getTotalPrice().negate();
                        }
                    }
                }else if ("正码特".equals(quizOrders.getSiteCategoryName())) {//分类
                    if("正一特".equals(quizOrders.getQuizTitle())){//号码
                        if(!"49".equals(one)){
                            if(one.equals(quizOrders.getBettingContent())){//购买的号码相同为中奖
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                        }else {//和
                            winAmount = quizOrders.getTotalPrice().negate();
                        }
                    }
                    if("正二特".equals(quizOrders.getQuizTitle())){//号码
                        if(!"49".equals(two)){
                            if(two.equals(quizOrders.getBettingContent())){//购买的号码相同为中奖
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                        }else {//和
                            winAmount = quizOrders.getTotalPrice().negate();
                        }
                    }
                    if("正三特".equals(quizOrders.getQuizTitle())){//号码
                        if(!"49".equals(three)){
                            if(three.equals(quizOrders.getBettingContent())){//购买的号码相同为中奖
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                        }else {//和
                            winAmount = quizOrders.getTotalPrice().negate();
                        }
                    }
                    if("正四特".equals(quizOrders.getQuizTitle())){//号码
                        if(!"49".equals(four)){
                            if(four.equals(quizOrders.getBettingContent())){//购买的号码相同为中奖
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                        }else {//和
                            winAmount = quizOrders.getTotalPrice().negate();
                        }
                    }
                    if("正五特".equals(quizOrders.getQuizTitle())){//号码
                        if(!"49".equals(five)){
                            if(five.equals(quizOrders.getBettingContent())){//购买的号码相同为中奖
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                        }else {//和
                            winAmount = quizOrders.getTotalPrice().negate();
                        }
                    }
                    if("正六特".equals(quizOrders.getQuizTitle())){//号码
                        if(!"49".equals(six)){
                            if(six.equals(quizOrders.getBettingContent())){//购买的号码相同为中奖
                                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                        }else {//和
                            winAmount = quizOrders.getTotalPrice().negate();
                        }
                    }
                }else if ("连码".equals(quizOrders.getSiteCategoryName())) {//分类
                }else if ("头尾数".equals(quizOrders.getSiteCategoryName())) {//分类
                }else if ("多选中一".equals(quizOrders.getSiteCategoryName())) {//分类
                }else if ("自选不中".equals(quizOrders.getSiteCategoryName())) {//分类
                }else if ("正特任中".equals(quizOrders.getSiteCategoryName())) {//分类
                }else if ("五行".equals(quizOrders.getSiteCategoryName())) {//分类
                }else if ("七码".equals(quizOrders.getSiteCategoryName())) {//分类
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
            }else if(winAmount.compareTo(BigDecimal.ZERO)==-1){//和
                quizOrders.setWinMount(BigDecimal.ZERO);//赢取金额
                quizOrders.setWinLoseAmount(BigDecimal.ZERO);//输赢金额（扣除本金）
                quizOrders.setStatus(OrderStatusEnum.ORDER_THREE.getStatus());

                MoneyLog moneyLog = new MoneyLog();
                moneyLog.setUserId(sysUser.getId());
                moneyLog.setUserName(sysUser.getUsername());
                moneyLog.setOrderNo(quizOrders.getOrderNo());
                moneyLog.setOrderType(MbChangeTypeEnum.BETTINGTIE.getType());//账变类型
                moneyLog.setOrderTypeName(MbChangeTypeEnum.BETTINGTIE.getName());//账变类型名称
                moneyLog.setBeforeMoney(currentBalance);//账变前金额
                moneyLog.setMoney(winAmount);//账变金额
                currentBalance = currentBalance.add(winAmount.abs());
                moneyLog.setAfterMoney(currentBalance);//账变后金额
                moneyLog.setCreateTime(new Date());
                moneyLog.setCreateBy(sysUser.getUsername());
                moneyLog.setUpdateTime(new Date());
                moneyLog.setUpdateBy(sysUser.getUsername());
                moneyLogList.add(moneyLog);

                //扣除M币
                userService.addRewardMb(sysUser, winAmount.abs());
            }else{//未中奖
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
    private BigDecimal daxiaodangshuanghonglvlanbo(QuizOrders quizOrders,String enterNumber,
                                                   Integer onesNumber,Integer tensNumber,
                                                   String[] bigSingle,String[] bigDouble,String[] smallSingle,String[] smallDouble,
                                                   List<String> hongboList,List<String> lvboList,List<String> lanboList){
        BigDecimal winAmount = BigDecimal.ZERO;
        //大：特别码大于或等于25
        if ("大".equals(quizOrders.getBettingContent())) {
            if (Integer.parseInt(enterNumber) >= 25)
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //小：特别码小于或等于24
        if ("小".equals(quizOrders.getBettingContent())) {
            if (Integer.parseInt(enterNumber) <= 24)
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //单：特别码为奇数
        if ("单".equals(quizOrders.getBettingContent())) {
            if (Integer.parseInt(enterNumber) % 2 != 0)
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //双：特别码为偶数
        if ("双".equals(quizOrders.getBettingContent())) {
            if (Integer.parseInt(enterNumber) % 2 == 0)
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //合大：和值大于或等于7
        if ("合大".equals(quizOrders.getBettingContent())) {
            if (onesNumber + tensNumber >= 7)
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //合小：和值小于或等于6合数小
        if ("合小".equals(quizOrders.getBettingContent())) {
            if (onesNumber + tensNumber <= 6)
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //合单：和值为奇数
        if ("合单".equals(quizOrders.getBettingContent())) {
            if ((onesNumber + tensNumber) % 2 != 0)
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //合双：和值为偶数
        if ("合双".equals(quizOrders.getBettingContent())) {
            if ((onesNumber + tensNumber) % 2 == 0)
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //大单
        if ("大单".equals(quizOrders.getBettingContent())) {
            if (Arrays.asList(bigSingle).contains(enterNumber))
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //大双
        if ("大双".equals(quizOrders.getBettingContent())) {
            if (Arrays.asList(bigDouble).contains(enterNumber))
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //小单
        if ("小单".equals(quizOrders.getBettingContent())) {
            if (Arrays.asList(smallSingle).contains(enterNumber))
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //小双
        if ("小双".equals(quizOrders.getBettingContent())) {
            if (Arrays.asList(smallDouble).contains(enterNumber))
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //尾大：大于等于5为大
        if ("尾大".equals(quizOrders.getBettingContent())) {
            if (onesNumber <= 5)
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //尾小：小于等于4为小
        if ("尾小".equals(quizOrders.getBettingContent())) {
            if (onesNumber <= 4)
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        if ("红波".equals(quizOrders.getBettingContent())) {
            if (Arrays.asList(hongboList).contains(enterNumber))
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        if ("蓝波".equals(quizOrders.getBettingContent())) {
            if (Arrays.asList(lanboList).contains(enterNumber))
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        if ("绿波".equals(quizOrders.getBettingContent())) {
            if (Arrays.asList(lvboList).contains(enterNumber))
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        return winAmount;
    }
    //平特肖
    private BigDecimal pingteyixiao(QuizOrders quizOrders,
                              String one,String two,String three,String four,String five,String six,String seven,
                              List<String> shuList,List<String> niuList,List<String> huList,List<String> tuzList,List<String> longList,List<String> sheList,
                              List<String> maList,List<String> yanList,List<String> houList,List<String> jiList,List<String> gouList,List<String> zhuList){
        //鼠、牛、虎、兔、龙、蛇、马、羊、猴、鸡、狗、猪
        if ("鼠".equals(quizOrders.getQuizIntroduce())) {
            if (Arrays.asList(shuList).contains(one)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(shuList).contains(two)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(shuList).contains(three)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(shuList).contains(four)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(shuList).contains(five)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(shuList).contains(six)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(shuList).contains(seven)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }
        }
        if ("牛".equals(quizOrders.getQuizIntroduce())) {
            if (Arrays.asList(niuList).contains(one)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(niuList).contains(two)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(niuList).contains(three)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(niuList).contains(four)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(niuList).contains(five)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(niuList).contains(six)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(niuList).contains(seven)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }
        }
        if ("虎".equals(quizOrders.getQuizIntroduce())) {
            if (Arrays.asList(huList).contains(one)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(huList).contains(two)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(huList).contains(three)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(huList).contains(four)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(huList).contains(five)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(huList).contains(six)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(huList).contains(seven)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }
        }
        if ("兔".equals(quizOrders.getQuizIntroduce())) {
            if (Arrays.asList(tuzList).contains(one)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(tuzList).contains(two)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(tuzList).contains(three)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(tuzList).contains(four)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(tuzList).contains(five)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(tuzList).contains(six)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(tuzList).contains(seven)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }
        }
        if ("龙".equals(quizOrders.getQuizIntroduce())) {
            if (Arrays.asList(longList).contains(one)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(longList).contains(two)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(longList).contains(three)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(longList).contains(four)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(longList).contains(five)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(longList).contains(six)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(longList).contains(seven)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }
        }
        if ("蛇".equals(quizOrders.getQuizIntroduce())) {
            if (Arrays.asList(sheList).contains(one)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(sheList).contains(two)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(sheList).contains(three)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(sheList).contains(four)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(sheList).contains(five)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(sheList).contains(six)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(sheList).contains(seven)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }
        }
        if ("马".equals(quizOrders.getQuizIntroduce())) {
            if (Arrays.asList(maList).contains(one)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(maList).contains(two)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(maList).contains(three)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(maList).contains(four)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(maList).contains(five)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(maList).contains(six)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(maList).contains(seven)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }
        }
        if ("羊".equals(quizOrders.getQuizIntroduce())) {
            if (Arrays.asList(yanList).contains(one)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(yanList).contains(two)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(yanList).contains(three)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(yanList).contains(four)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(yanList).contains(five)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(yanList).contains(six)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(yanList).contains(seven)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }
        }
        if ("猴".equals(quizOrders.getQuizIntroduce())) {
            if (Arrays.asList(houList).contains(one)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(houList).contains(two)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(houList).contains(three)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(houList).contains(four)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(houList).contains(five)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(houList).contains(six)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(houList).contains(seven)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }
        }
        if ("鸡".equals(quizOrders.getQuizIntroduce())) {
            if (Arrays.asList(jiList).contains(one)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(jiList).contains(two)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(jiList).contains(three)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(jiList).contains(four)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(jiList).contains(five)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(jiList).contains(six)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(jiList).contains(seven)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }
        }
        if ("狗".equals(quizOrders.getQuizIntroduce())) {
            if (Arrays.asList(gouList).contains(one)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(gouList).contains(two)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(gouList).contains(three)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(gouList).contains(four)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(gouList).contains(five)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(gouList).contains(six)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(gouList).contains(seven)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }
        }
        if ("猪".equals(quizOrders.getQuizIntroduce())) {
            if (Arrays.asList(zhuList).contains(one)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(zhuList).contains(two)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(zhuList).contains(three)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(zhuList).contains(four)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(zhuList).contains(five)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(zhuList).contains(six)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }else if (Arrays.asList(zhuList).contains(seven)){
                return quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
            }
        }
        return BigDecimal.ZERO;
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
        String winZodiacStr = this.winZodiac(one,shuList,niuList,huList,tuzList,longList,sheList,maList,yanList,houList,jiList,gouList,zhuList)+","+
                this.winZodiac(two,shuList,niuList,huList,tuzList,longList,sheList,maList,yanList,houList,jiList,gouList,zhuList)+","+
                this.winZodiac(three,shuList,niuList,huList,tuzList,longList,sheList,maList,yanList,houList,jiList,gouList,zhuList)+","+
                this.winZodiac(four,shuList,niuList,huList,tuzList,longList,sheList,maList,yanList,houList,jiList,gouList,zhuList)+","+
                this.winZodiac(five,shuList,niuList,huList,tuzList,longList,sheList,maList,yanList,houList,jiList,gouList,zhuList)+","+
                this.winZodiac(six,shuList,niuList,huList,tuzList,longList,sheList,maList,yanList,houList,jiList,gouList,zhuList)+","+
                this.winZodiac(seven,shuList,niuList,huList,tuzList,longList,sheList,maList,yanList,houList,jiList,gouList,zhuList);
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
                            bigSingle,bigDouble,smallSingle,smallDouble,
                            jinList,muList,shuiList,huoList,tuList,
                            shuList,niuList,huList,tuzList,longList,sheList,maList,yanList,houList,jiList,gouList,zhuList,
                            hongboList,lvboList,lanboList,
                            jiaList,yeList,
                            winZodiacStr);
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
    public List<String> getBettingZodiacList(String[] bettingZodiac) {
        List<String> bettingZodiacList = new ArrayList<>();
        for(int i = 0; i < bettingZodiac.length; i++){
            for(int j = 0; j < bettingZodiac.length; j++){
                if(i != j){
                    if(bettingZodiac.length==2){
                        bettingZodiacList.add(bettingZodiac[i] + "," + bettingZodiac[j]);
                    }else {
                        for (int k = 0; k < bettingZodiac.length; k++) {
                            if (k != i && k != j) {
                                if (bettingZodiac.length == 3) {
                                    bettingZodiacList.add(bettingZodiac[i] + "," + bettingZodiac[j] + "," + bettingZodiac[k]);
                                } else {
                                    for (int m = 0; m < bettingZodiac.length; m++) {
                                        if (m != i && m != k && m != j) {
                                            if (bettingZodiac.length == 4) {
                                                bettingZodiacList.add(bettingZodiac[i] + "," + bettingZodiac[j] + "," + bettingZodiac[k] + "," + bettingZodiac[m]);
                                            } else {
                                                for (int n = 0; n < bettingZodiac.length; n++) {
                                                    if (n != i && n != j && n != k && n != m) {
                                                        bettingZodiacList.add(bettingZodiac[i] + "," + bettingZodiac[j] + "," + bettingZodiac[k] + "," + bettingZodiac[m] + "," + bettingZodiac[n]);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return bettingZodiacList;
    }
}
