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
import java.util.stream.Collectors;

/**
 * 获奖结算定时任务
 */
@Slf4j
@Service
public class LotteryWinDataImpl {
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
    @Autowired
    private IKillOddsService killOddsService;
    /**
     * 结算
     * @return
     */
    @Transactional(rollbackFor={RuntimeException.class,Exception.class})
    public BigDecimal winningSettlement(List<QuizOrders> list,
                                  String one,String two,String three,String four,String five,String six,
                                  String seven,int seven_ones,int seven_tens,
                                  String[] bigSingle,String[] bigDouble,String[] smallSingle,String[] smallDouble,
                                  List<String> jinList,List<String> muList,List<String> shuiList,List<String> huoList,List<String> tuList,
                                  List<String> shuList,List<String> niuList,List<String> huList,List<String> tuzList,List<String> longList,List<String> sheList,
                                  List<String> maList,List<String> yanList,List<String> houList,List<String> jiList,List<String> gouList,List<String> zhuList,
                                  List<String> hongboList,List<String> lvboList,List<String> lanboList,
                                  List<String> jiaList,List<String> yeList,
                                  String winZodiacStr,
                                  int bigcount,int smallcount,int doublecount,int singlecount,
//                                  String[] head0,String[] head1,String[] head2,String[] head3,String[] head4,
//                                  String[] tail0,String[] tail1,String[] tail2,String[] tail3,String[] tail4,String[] tail5,String[] tail6,String[] tail7,String[] tail8,String[] tail9,
                                  String[] wnTail,
                                  String[] wnNumbersSix,
                                  String[] wnNumbers){
        List<QuizOrders> ordersList = new ArrayList<>();
        List<MoneyLog> moneyLogList = new ArrayList<>();
        BigDecimal winAmount = BigDecimal.ZERO;
        for (QuizOrders quizOrders: list) {
            SysUser sysUser = userService.getSysUserById(quizOrders.getMemberId());
            BigDecimal currentBalance = sysUser.getMBalance();//用户当前余额
            quizOrders.setUpdateTime(new Date());
            quizOrders.setUpdateBy(sysUser.getUsername());

            if (LotteryEnum.HONGKONG_MKS.getRemark().equals(quizOrders.getLotteryName())) {//香港六合彩
                if ("特码".equals(quizOrders.getSiteCategoryName())) {//分类一类
                    if ("特码".equals(quizOrders.getQuizTitle())) {//分类二类
                        if ("特码".equals(quizOrders.getQuizDetailsName())
                                || "特码A".equals(quizOrders.getQuizDetailsName())
                                || "特码B".equals(quizOrders.getQuizDetailsName())) {//分类三类
                            if (seven.equals(quizOrders.getQuizIntroduces())) {//购买的号码相同为中奖
                                winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                        } else if ("大小单双".equals(quizOrders.getQuizDetailsName())) {//分类三类 大小单双
                            if (!"49".equals(seven)) {//特别码49为和
                                winAmount = winAmount.add(this.daxiaodangshuanghonglvlanbo(quizOrders, seven, seven_ones, seven_tens, bigSingle, bigDouble, smallSingle, smallDouble, hongboList, lvboList, lanboList));
                            } else {
                                winAmount = winAmount.add(quizOrders.getTotalPrice().negate());
                            }
                        } else if ("生肖".equals(quizOrders.getQuizDetailsName())) {//分类三类 生肖
                            //鼠、牛、虎、兔、龙、蛇、马、羊、猴、鸡、狗、猪
                            if ("鼠".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(shuList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("牛".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(niuList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("虎".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(huList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("兔".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(tuzList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("龙".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(longList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("蛇".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(sheList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("马".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(maList).contains(seven))
                                    winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
                            }
                            if ("羊".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(yanList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("猴".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(houList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("鸡".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(jiList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("狗".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(gouList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("猪".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(zhuList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                        } else if ("家野".equals(quizOrders.getQuizDetailsName())) {//分类三类 家野
                            if ("家".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(jiaList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("野".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(yeList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                        } else if ("波色".equals(quizOrders.getQuizDetailsName())) {//分类三类 波色
                            winAmount = winAmount.add(this.daxiaodangshuanghonglvlanbo(quizOrders, seven, seven_ones, seven_tens, bigSingle, bigDouble, smallSingle, smallDouble, hongboList, lvboList, lanboList));
                        }
                    }
                } else if ("生肖".equals(quizOrders.getSiteCategoryName())) {//分类一类
                    if ("特肖".equals(quizOrders.getQuizTitle())) {//分类二类
                        if ("特肖".equals(quizOrders.getQuizDetailsName())) {//分类三类 生肖
                            //鼠、牛、虎、兔、龙、蛇、马、羊、猴、鸡、狗、猪
                            if ("鼠".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(shuList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("牛".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(niuList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("虎".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(huList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("兔".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(tuzList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("龙".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(longList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("蛇".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(sheList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("马".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(maList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("羊".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(yanList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("猴".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(houList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("鸡".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(jiList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("狗".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(gouList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("猪".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(zhuList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                        }
                    } else if ("平特一肖".equals(quizOrders.getQuizTitle())) {//分类二类
                        //当期开奖的全部号码(前6个号码和特别码)，其中只要有一个号码在投注的生肖范围则中奖；没有一个球号在投注的生肖范围内，则不中奖;多个球号在投注生肖范围内，则中奖；但奖金不倍增，派彩只派一次，即不论同生肖号码出现一个或多个号码都只派一次。
                        if ("平特一肖".equals(quizOrders.getQuizDetailsName())) {//分类三类
                            winAmount = winAmount.add(pingteyixiao(quizOrders,
                                    one, two, three, four, five, six, seven,
                                    shuList, niuList, huList, tuzList, longList, sheList,
                                    maList, yanList, houList, jiList, gouList, zhuList));
                        }
                    } else if ("合肖".equals(quizOrders.getQuizTitle())) {//分类二类
                        if (!"49".equals(seven)) {
                            if (seven.indexOf(quizOrders.getQuizIntroduces()) != -1) {
                                //选择2-11个生肖，所选生肖为开奖号码的特别码对应的生肖，即为中奖（和局:特别码为49，退还本金）；如投注方案为猴、虎，开奖号码为01(兔),02(虎),03(牛),04(鼠),05(猪),06(狗) + 08(猴)，即中合肖。
                                winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                        } else {//和
                            winAmount = winAmount.add(quizOrders.getTotalPrice().negate());
                        }

                    } else if ("连肖".equals(quizOrders.getQuizTitle())) {//分类二类
                        if ("二肖连中".equals(quizOrders.getQuizIntroduces())) {
                            //选择2个或2个以上生肖，所选生肖与开奖号码所对应的生肖一致，即中奖；如投注方案为牛、鼠，开奖号码为：01(虎),02(牛),03(鼠),04(猪),05(狗),06(鸡) + 07(猴)，即中二连肖。
                            String[] bettingZodiacStr = quizOrders.getQuizIntroduces().split(",");//投注
                            List<String> bettingZodiacList = this.getBettingComList(bettingZodiacStr, bettingZodiacStr.length);
                            for (String bettingZodiac : bettingZodiacList) {
                                if (bettingZodiac.indexOf(winZodiacStr) != -1) {
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                    break;
                                }
                            }
                        }
                        if ("三肖连中".equals(quizOrders.getQuizIntroduces())) {
                            //选择3个或3个以上生肖，所选生肖与开奖号码所对应的生肖一致，即中奖；如投注方案为牛、鼠、猪，开奖号码为：01(虎),02(牛),03(鼠),04(猪),05(狗),06(鸡) + 07(猴)，即中三连肖。
                            String[] bettingZodiacStr = quizOrders.getQuizIntroduces().split(",");//投注
                            List<String> bettingZodiacList = this.getBettingComList(bettingZodiacStr, bettingZodiacStr.length);
                            for (String bettingZodiac : bettingZodiacList) {
                                if (bettingZodiac.indexOf(winZodiacStr) != -1) {
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                    break;
                                }
                            }
                        }
                        if ("四肖连中".equals(quizOrders.getQuizIntroduces())) {
                            //选择4个或4个以上生肖，所选生肖与开奖号码所对应的生肖一致，即中奖；如投注方案为牛、鼠、猪、狗，开奖号码为：01(虎),02(牛),03(鼠),04(猪),05(狗),06(鸡) + 07(猴)，即中四连肖。
                            String[] bettingZodiacStr = quizOrders.getQuizIntroduces().split(",");//投注
                            List<String> bettingZodiacList = this.getBettingComList(bettingZodiacStr, bettingZodiacStr.length);
                            for (String bettingZodiac : bettingZodiacList) {
                                if (bettingZodiac.indexOf(winZodiacStr) != -1) {
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                    break;
                                }
                            }
                        }
                        if ("五肖连中".equals(quizOrders.getQuizIntroduces())) {
                            //选择5个或5个以上生肖，所选生肖与开奖号码所对应的生肖一致，即中奖；如投注方案为牛、鼠、猪、狗、鸡，开奖号码为：01(虎),02(牛),03(鼠),04(猪),05(狗),06(鸡) + 07(猴)，即中五连肖。
                            String[] bettingZodiacStr = quizOrders.getQuizIntroduces().split(",");//投注
                            List<String> bettingZodiacList = this.getBettingComList(bettingZodiacStr, bettingZodiacStr.length);
                            for (String bettingZodiac : bettingZodiacList) {
                                if (bettingZodiac.indexOf(winZodiacStr) != -1) {
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                    break;
                                }
                            }
                        }
                        if ("二肖连不中".equals(quizOrders.getQuizIntroduces())) {
                            String[] bettingZodiacStr = quizOrders.getQuizIntroduces().split(",");//投注
                            List<String> bettingZodiacList = this.getBettingComList(bettingZodiacStr, bettingZodiacStr.length);
                            boolean b = true;
                            for (String bettingZodiac : bettingZodiacList) {
                                if (bettingZodiac.indexOf(winZodiacStr) != -1) {
                                    b = false;
                                    break;
                                }
                            }
                            if (b) {
                                winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                        }
                        if ("三肖连不中".equals(quizOrders.getQuizIntroduces())) {
                            String[] bettingZodiacStr = quizOrders.getQuizIntroduces().split(",");//投注
                            List<String> bettingZodiacList = this.getBettingComList(bettingZodiacStr, bettingZodiacStr.length);
                            boolean b = true;
                            for (String bettingZodiac : bettingZodiacList) {
                                if (bettingZodiac.indexOf(winZodiacStr) != -1) {
                                    b = false;
                                    break;
                                }
                            }
                            if (b) {
                                winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                        }
                        if ("四肖连不中".equals(quizOrders.getQuizIntroduces())) {
                            String[] bettingZodiacStr = quizOrders.getQuizIntroduces().split(",");//投注
                            List<String> bettingZodiacList = this.getBettingComList(bettingZodiacStr, bettingZodiacStr.length);
                            boolean b = true;
                            for (String bettingZodiac : bettingZodiacList) {
                                if (bettingZodiac.indexOf(winZodiacStr) != -1) {
                                    b = false;
                                    break;
                                }
                            }
                            if (b) {
                                winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                        }
                    }
                } else if ("半波".equals(quizOrders.getSiteCategoryName())) {//分类一类
                    if ("半波".equals(quizOrders.getQuizTitle())) {
                        //以特码色波和特码单双大小为一个投注组合，当开出特码符合投注组合，即视为中奖。若开出特码为49号时，所有投注半波任意一个组合视为和局，其余情况视为不中奖。
                        if (!"49".equals(seven)) {
                            if ("红大".equals(quizOrders.getQuizIntroduces())) {//特别码大于或等于25
                                if (Arrays.asList(hongboList).contains(seven)) {
                                    if (Integer.parseInt(seven) >= 25)
                                        winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            }
                            if ("红小".equals(quizOrders.getQuizIntroduces())) {//特别码小于或等于24
                                if (Arrays.asList(hongboList).contains(seven)) {
                                    if (Integer.parseInt(seven) <= 24)
                                        winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            }
                            if ("红单".equals(quizOrders.getQuizIntroduces())) {//特别码为奇数
                                if (Arrays.asList(hongboList).contains(seven)) {
                                    if (Integer.parseInt(seven) % 2 != 0)
                                        winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            }
                            if ("红双".equals(quizOrders.getQuizIntroduces())) {//特别码为偶数
                                if (Arrays.asList(hongboList).contains(seven)) {
                                    if (Integer.parseInt(seven) % 2 == 0)
                                        winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            }
                            if ("红合单".equals(quizOrders.getQuizIntroduces())) {//和值为奇数
                                if (Arrays.asList(hongboList).contains(seven)) {
                                    if ((seven_ones + seven_tens) % 2 != 0)
                                        winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            }
                            if ("红合双".equals(quizOrders.getQuizIntroduces())) {//和值为偶数
                                if (Arrays.asList(hongboList).contains(seven)) {
                                    if ((seven_ones + seven_tens) % 2 == 0)
                                        winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            }
                            if ("蓝大".equals(quizOrders.getQuizIntroduces())) {//特别码大于或等于25
                                if (Arrays.asList(lanboList).contains(seven)) {
                                    if (Integer.parseInt(seven) >= 25)
                                        winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            }
                            if ("蓝小".equals(quizOrders.getQuizIntroduces())) {//特别码小于或等于24
                                if (Arrays.asList(lanboList).contains(seven)) {
                                    if (Integer.parseInt(seven) <= 24)
                                        winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            }
                            if ("蓝单".equals(quizOrders.getQuizIntroduces())) {//特别码为奇数
                                if (Arrays.asList(lanboList).contains(seven)) {
                                    if (Integer.parseInt(seven) % 2 != 0)
                                        winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            }
                            if ("蓝双".equals(quizOrders.getQuizIntroduces())) {//特别码为偶数
                                if (Arrays.asList(lanboList).contains(seven)) {
                                    if (Integer.parseInt(seven) % 2 == 0)
                                        winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            }
                            if ("蓝合单".equals(quizOrders.getQuizIntroduces())) {//和值为奇数
                                if (Arrays.asList(lanboList).contains(seven)) {
                                    if ((seven_ones + seven_tens) % 2 != 0)
                                        winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            }
                            if ("蓝合双".equals(quizOrders.getQuizIntroduces())) {//和值为偶数
                                if (Arrays.asList(lanboList).contains(seven)) {
                                    if ((seven_ones + seven_tens) % 2 == 0)
                                        winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            }
                            if ("绿大".equals(quizOrders.getQuizIntroduces())) {//特别码大于或等于25
                                if (Arrays.asList(lvboList).contains(seven)) {
                                    if (Integer.parseInt(seven) >= 25)
                                        winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            }
                            if ("绿小".equals(quizOrders.getQuizIntroduces())) {//特别码小于或等于24
                                if (Arrays.asList(lvboList).contains(seven)) {
                                    if (Integer.parseInt(seven) <= 24)
                                        winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            }
                            if ("绿单".equals(quizOrders.getQuizIntroduces())) {//特别码为奇数
                                if (Arrays.asList(lvboList).contains(seven)) {
                                    if (Integer.parseInt(seven) % 2 != 0)
                                        winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            }
                            if ("绿双".equals(quizOrders.getQuizIntroduces())) {//特别码为偶数
                                if (Arrays.asList(lvboList).contains(seven)) {
                                    if (Integer.parseInt(seven) % 2 == 0)
                                        winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            }
                            if ("绿合单".equals(quizOrders.getQuizIntroduces())) {//和值为奇数
                                if (Arrays.asList(lvboList).contains(seven)) {
                                    if ((seven_ones + seven_tens) % 2 != 0)
                                        winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            }
                            if ("绿合双".equals(quizOrders.getQuizIntroduces())) {//和值为偶数
                                if (Arrays.asList(lvboList).contains(seven)) {
                                    if ((seven_ones + seven_tens) % 2 == 0)
                                        winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            }
                        } else {//和
                            winAmount = winAmount.add(quizOrders.getTotalPrice().negate());
                        }
                    }
                } else if ("正码".equals(quizOrders.getSiteCategoryName())) {//分类一类
                    if ("正码".equals(quizOrders.getQuizTitle())) {//分类二类
                        if ("大小单双".equals(quizOrders.getQuizDetailsName())) {//分类三类 大小单双
                            if ("总和大".equals(quizOrders.getQuizIntroduces())) {//所有七个开奖号码的总和大于或者等于175
                                if (Integer.parseInt(one) + Integer.parseInt(two) + Integer.parseInt(three) + Integer.parseInt(four) + Integer.parseInt(five) + Integer.parseInt(six) + Integer.parseInt(seven) >= 175)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("总和小".equals(quizOrders.getQuizIntroduces())) {//所有七个开奖号码的总和小于或者等于174
                                if (Integer.parseInt(one) + Integer.parseInt(two) + Integer.parseInt(three) + Integer.parseInt(four) + Integer.parseInt(five) + Integer.parseInt(six) + Integer.parseInt(seven) <= 174)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("总和单".equals(quizOrders.getQuizIntroduces())) {//所有七个开奖号码的总和值为奇数
                                if ((Integer.parseInt(one) + Integer.parseInt(two) + Integer.parseInt(three) + Integer.parseInt(four) + Integer.parseInt(five) + Integer.parseInt(six) + Integer.parseInt(seven)) % 2 != 0)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("总和双".equals(quizOrders.getQuizIntroduces())) {//所有七个开奖号码的总和值为偶数
                                if ((Integer.parseInt(one) + Integer.parseInt(two) + Integer.parseInt(three) + Integer.parseInt(four) + Integer.parseInt(five) + Integer.parseInt(six) + Integer.parseInt(seven)) % 2 == 0)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                        }
                    }
                    //选1个或1个以上正码属性，所选正码属性与开奖号码的第一位属性一致，即为中奖；若正码一是49，则为和局。
                    if ("正码1".equals(quizOrders.getQuizTitle())) {
                        if (!"49".equals(one)) {
                            int one_ones = Integer.parseInt(one) % 10;
                            int one_tens = Integer.parseInt(one) / 10 % 10;
                            winAmount = winAmount.add(this.daxiaodangshuanghonglvlanbo(quizOrders, one, one_ones, one_tens, bigSingle, bigDouble, smallSingle, smallDouble, hongboList, lvboList, lanboList));
                        } else {//和
                            winAmount = winAmount.add(quizOrders.getTotalPrice().negate());
                        }
                    }
                    //选1个或1个以上正码属性，所选正码属性与开奖号码的第二位属性一致，即为中奖；若正码二是49，则为和局。
                    if ("正码2".equals(quizOrders.getQuizTitle())) {
                        if (!"49".equals(two)) {
                            int two_ones = Integer.parseInt(two) % 10;
                            int two_tens = Integer.parseInt(two) / 10 % 10;
                            winAmount = winAmount.add(this.daxiaodangshuanghonglvlanbo(quizOrders, two, two_ones, two_tens, bigSingle, bigDouble, smallSingle, smallDouble, hongboList, lvboList, lanboList));
                        } else {//和
                            winAmount = winAmount.add(quizOrders.getTotalPrice().negate());
                        }
                    }
                    //选1个或1个以上正码属性，所选正码属性与开奖号码的第三位属性一致，即为中奖；若正码三是49，则为和局。
                    if ("正码3".equals(quizOrders.getQuizTitle())) {
                        if (!"49".equals(three)) {
                            int three_ones = Integer.parseInt(three) % 10;
                            int three_tens = Integer.parseInt(three) / 10 % 10;
                            winAmount = winAmount.add(this.daxiaodangshuanghonglvlanbo(quizOrders, three, three_ones, three_tens, bigSingle, bigDouble, smallSingle, smallDouble, hongboList, lvboList, lanboList));
                        } else {//和
                            winAmount = winAmount.add(quizOrders.getTotalPrice().negate());
                        }
                    }
                    //选1个或1个以上正码属性，所选正码属性与开奖号码的第四位属性一致，即为中奖；若正码四是49，则为和局。
                    if ("正码4".equals(quizOrders.getQuizTitle())) {
                        if (!"49".equals(four)) {
                            int four_ones = Integer.parseInt(four) % 10;
                            int four_tens = Integer.parseInt(four) / 10 % 10;
                            winAmount = winAmount.add(this.daxiaodangshuanghonglvlanbo(quizOrders, four, four_ones, four_tens, bigSingle, bigDouble, smallSingle, smallDouble, hongboList, lvboList, lanboList));
                        } else {//和
                            winAmount = winAmount.add(quizOrders.getTotalPrice().negate());
                        }
                    }
                    //选1个或1个以上正码属性，所选正码属性与开奖号码的第五位属性一致，即为中奖；若正码五是49，则为和局。
                    if ("正码5".equals(quizOrders.getQuizTitle())) {
                        if (!"49".equals(five)) {
                            int five_ones = Integer.parseInt(five) % 10;
                            int five_tens = Integer.parseInt(five) / 10 % 10;
                            winAmount = winAmount.add(this.daxiaodangshuanghonglvlanbo(quizOrders, five, five_ones, five_tens, bigSingle, bigDouble, smallSingle, smallDouble, hongboList, lvboList, lanboList));
                        } else {//和
                            winAmount = winAmount.add(quizOrders.getTotalPrice().negate());
                        }
                    }
                    //选1个或1个以上正码属性，所选正码属性与开奖号码的第六位属性一致，即为中奖；若正码六是49，则为和局。
                    if ("正码6".equals(quizOrders.getQuizTitle())) {
                        if (!"49".equals(six)) {
                            int six_ones = Integer.parseInt(six) % 10;
                            int six_tens = Integer.parseInt(six) / 10 % 10;
                            winAmount = winAmount.add(this.daxiaodangshuanghonglvlanbo(quizOrders, six, six_ones, six_tens, bigSingle, bigDouble, smallSingle, smallDouble, hongboList, lvboList, lanboList));
                        } else {//和
                            winAmount = winAmount.add(quizOrders.getTotalPrice().negate());
                        }
                    }
                } else if ("正码特".equals(quizOrders.getSiteCategoryName())) {//分类一类
                    if ("正一特".equals(quizOrders.getQuizTitle())) {//分类二类
                        if ("正一特".equals(quizOrders.getQuizDetailsName())) {//分类三类
                            if (!"49".equals(one)) {
                                if (one.equals(quizOrders.getQuizIntroduces())) {//购买的号码相同为中奖
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            } else {//和
                                winAmount = winAmount.add(quizOrders.getTotalPrice().negate());
                            }
                        }
                        if ("大小单双".equals(quizOrders.getQuizDetailsName()) || "色波".equals(quizOrders.getQuizDetailsName())) {//分类三类
                            if (!"49".equals(one)) {
                                int one_ones = Integer.parseInt(one) % 10;
                                int one_tens = Integer.parseInt(one) / 10 % 10;
                                winAmount = winAmount.add(this.daxiaodangshuanghonglvlanbo(quizOrders, one, one_ones, one_tens, bigSingle, bigDouble, smallSingle, smallDouble, hongboList, lvboList, lanboList));
                            } else {//和
                                winAmount = winAmount.add(quizOrders.getTotalPrice().negate());
                            }
                        }
                    }
                    if ("正二特".equals(quizOrders.getQuizTitle())) {//分类二类
                        if ("正二特".equals(quizOrders.getQuizDetailsName())) {//分类三类
                            if (!"49".equals(two)) {
                                if (two.equals(quizOrders.getQuizIntroduces())) {//购买的号码相同为中奖
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            } else {//和
                                winAmount = winAmount.add(quizOrders.getTotalPrice().negate());
                            }
                        }
                        if ("大小单双".equals(quizOrders.getQuizDetailsName()) || "色波".equals(quizOrders.getQuizDetailsName())) {//分类三类
                            if (!"49".equals(two)) {
                                int two_ones = Integer.parseInt(two) % 10;
                                int two_tens = Integer.parseInt(two) / 10 % 10;
                                winAmount = winAmount.add(this.daxiaodangshuanghonglvlanbo(quizOrders, two, two_ones, two_tens, bigSingle, bigDouble, smallSingle, smallDouble, hongboList, lvboList, lanboList));
                            } else {//和
                                winAmount = winAmount.add(quizOrders.getTotalPrice().negate());
                            }
                        }
                    }
                    if ("正三特".equals(quizOrders.getQuizTitle())) {//分类二类
                        if ("正三特".equals(quizOrders.getQuizDetailsName())) {//分类三类
                            if (!"49".equals(three)) {
                                if (three.equals(quizOrders.getQuizIntroduces())) {//购买的号码相同为中奖
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            } else {//和
                                winAmount = winAmount.add(quizOrders.getTotalPrice().negate());
                            }
                        }
                        if ("大小单双".equals(quizOrders.getQuizDetailsName()) || "色波".equals(quizOrders.getQuizDetailsName())) {//分类三类
                            if (!"49".equals(three)) {
                                int three_ones = Integer.parseInt(three) % 10;
                                int three_tens = Integer.parseInt(three) / 10 % 10;
                                winAmount = winAmount.add(this.daxiaodangshuanghonglvlanbo(quizOrders, three, three_ones, three_tens, bigSingle, bigDouble, smallSingle, smallDouble, hongboList, lvboList, lanboList));
                            } else {//和
                                winAmount = winAmount.add(quizOrders.getTotalPrice().negate());
                            }
                        }
                    }
                    if ("正四特".equals(quizOrders.getQuizTitle())) {//分类二类
                        if ("正四特".equals(quizOrders.getQuizDetailsName())) {//分类三类
                            if (!"49".equals(four)) {
                                if (four.equals(quizOrders.getQuizIntroduces())) {//购买的号码相同为中奖
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            } else {//和
                                winAmount = winAmount.add(quizOrders.getTotalPrice().negate());
                            }
                        }
                        if ("大小单双".equals(quizOrders.getQuizDetailsName()) || "色波".equals(quizOrders.getQuizDetailsName())) {//分类三类
                            if (!"49".equals(four)) {
                                int four_ones = Integer.parseInt(four) % 10;
                                int four_tens = Integer.parseInt(four) / 10 % 10;
                                winAmount = winAmount.add(this.daxiaodangshuanghonglvlanbo(quizOrders, four, four_ones, four_tens, bigSingle, bigDouble, smallSingle, smallDouble, hongboList, lvboList, lanboList));
                            } else {//和
                                winAmount = winAmount.add(quizOrders.getTotalPrice().negate());
                            }
                        }
                    }
                    if ("正五特".equals(quizOrders.getQuizTitle())) {//分类二类
                        if ("正五特".equals(quizOrders.getQuizDetailsName())) {//分类三类
                            if (!"49".equals(five)) {
                                if (five.equals(quizOrders.getQuizIntroduces())) {//购买的号码相同为中奖
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            } else {//和
                                winAmount = winAmount.add(quizOrders.getTotalPrice().negate());
                            }
                        }
                        if ("大小单双".equals(quizOrders.getQuizDetailsName()) || "色波".equals(quizOrders.getQuizDetailsName())) {//分类三类
                            if (!"49".equals(five)) {
                                int five_ones = Integer.parseInt(five) % 10;
                                int five_tens = Integer.parseInt(five) / 10 % 10;
                                winAmount = winAmount.add(this.daxiaodangshuanghonglvlanbo(quizOrders, five, five_ones, five_tens, bigSingle, bigDouble, smallSingle, smallDouble, hongboList, lvboList, lanboList));
                            } else {//和
                                winAmount = winAmount.add(quizOrders.getTotalPrice().negate());
                            }
                        }
                    }
                    if ("正六特".equals(quizOrders.getQuizTitle())) {//分类二类
                        if ("正六特".equals(quizOrders.getQuizDetailsName())) {//分类三类
                            if (!"49".equals(six)) {
                                if (six.equals(quizOrders.getQuizIntroduces())) {//购买的号码相同为中奖
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                                }
                            } else {//和
                                winAmount = winAmount.add(quizOrders.getTotalPrice().negate());
                            }
                        }
                        if ("大小单双".equals(quizOrders.getQuizDetailsName()) || "色波".equals(quizOrders.getQuizDetailsName())) {//分类三类
                            if (!"49".equals(six)) {
                                int six_ones = Integer.parseInt(six) % 10;
                                int six_tens = Integer.parseInt(six) / 10 % 10;
                                winAmount = winAmount.add(this.daxiaodangshuanghonglvlanbo(quizOrders, six, six_ones, six_tens, bigSingle, bigDouble, smallSingle, smallDouble, hongboList, lvboList, lanboList));
                            } else {//和
                                winAmount = winAmount.add(quizOrders.getTotalPrice().negate());
                            }
                        }
                    }
                } else if ("连码".equals(quizOrders.getSiteCategoryName())) {//分类一类
                    Map<String, Object> params = new HashMap<>();
                    params.put("orderNo", quizOrders.getOrderNo());
                    List<QuizSuborders> quizSubordersList = quizSubordersService.findList(params);
                    if ("三全中".equals(quizOrders.getQuizTitle())) {//分类二类
                        //所投注的每三个号码为一组合，若三个号码都是开奖号码之正码，视为中奖，其余行情视为不中奖
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbersSix).contains(bettingNumberList[0])
                                    && Arrays.asList(wnNumbersSix).contains(bettingNumberList[1])
                                    && Arrays.asList(wnNumbersSix).contains(bettingNumberList[2])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                            }
                        }
                    }
                    if ("三中二".equals(quizOrders.getQuizTitle())) {//分类二类
                        //所投注的每三个号码为一组合，若其中2个号码都是开奖号码之正码，视为三中二奖，若3个都是开奖号码中的正码，即为三中二之中三，其余行情视为不中奖
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            //若3个都是开奖号码中的正码，即为三中二之中三
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbersSix).contains(bettingNumberList[0])
                                    && Arrays.asList(wnNumbersSix).contains(bettingNumberList[1])
                                    && Arrays.asList(wnNumbersSix).contains(bettingNumberList[2])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds2()));
                            } else if (!Arrays.asList(wnNumbersSix).contains(bettingNumberList[0])
                                    && Arrays.asList(wnNumbersSix).contains(bettingNumberList[1])
                                    && Arrays.asList(wnNumbersSix).contains(bettingNumberList[2])) {//为三中二
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                            } else if (Arrays.asList(wnNumbersSix).contains(bettingNumberList[0])
                                    && !Arrays.asList(wnNumbersSix).contains(bettingNumberList[1])
                                    && Arrays.asList(wnNumbersSix).contains(bettingNumberList[2])) {//为三中二
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                            } else if (Arrays.asList(wnNumbersSix).contains(bettingNumberList[0])
                                    && Arrays.asList(wnNumbersSix).contains(bettingNumberList[1])
                                    && !Arrays.asList(wnNumbersSix).contains(bettingNumberList[2])) {//为三中二
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                            }
                        }
                    }
                    if ("二全中".equals(quizOrders.getQuizTitle())) {//分类二类
                        //所投注的每二个号码为一组合，若二个号码都是开奖号码之正码，视为中奖，其余行情视为不中奖（含一个正码加一个特码情形）
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbersSix).contains(bettingNumberList[0])
                                    && Arrays.asList(wnNumbersSix).contains(bettingNumberList[1])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                            }
                        }
                    }
                    if ("二中特".equals(quizOrders.getQuizTitle())) {//分类二类
                        //所投注的每二个号码为一组合，若二个号码都是开奖号码之正码，叫二中特之中二，其中一个是正码，一个是特码，视为中奖,其余行情视为不中奖二中特之中二赔率高于二中特之中特的赔率
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbersSix).contains(bettingNumberList[0])
                                    && Arrays.asList(wnNumbersSix).contains(bettingNumberList[1])) {//二中特之中二
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds2()));
                            } else {//二中特之中特
                                if (seven.equals(bettingNumberList[0])) {
                                    if (Arrays.asList(wnNumbersSix).contains(bettingNumberList[1]))
                                        winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                } else if (seven.equals(bettingNumberList[1])) {
                                    if (Arrays.asList(wnNumbersSix).contains(bettingNumberList[0]))
                                        winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                }
                            }
                        }
                    }
                    if ("特串".equals(quizOrders.getQuizTitle())) {//分类二类
                        //所投注的每两个号码为一组合，其中一个是正码，一个是特码，视为中奖，其余情形视为不中奖（含二个都是正码之情形）
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (seven.equals(bettingNumberList[0])) {
                                if (Arrays.asList(wnNumbersSix).contains(bettingNumberList[1]))
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                            } else if (seven.equals(bettingNumberList[1])) {
                                if (Arrays.asList(wnNumbersSix).contains(bettingNumberList[0]))
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                            }
                        }
                    }
                    if ("四中一".equals(quizOrders.getQuizTitle())) {//分类二类
                        //所投注号码每四个为一组，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[2])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[3]))
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                        }
                    }
                } else if ("头尾数".equals(quizOrders.getSiteCategoryName())) {//分类一类
                    if ("特码头尾数".equals(quizOrders.getQuizTitle())) {//分类二类
                        //对特码的头尾数进行下注，下注的头尾数与特码的头尾数一致即中奖
                        if ("特码头尾数".equals(quizOrders.getQuizDetailsName())) {//分类三类
                            //0头、1头、2头、3头、4头
                            //0尾、1尾、2尾、3尾、4尾、5尾、6尾、7尾、8尾、9尾
                            if (quizOrders.getQuizIntroduces().equals(seven_ones + "头") || quizOrders.getQuizIntroduces().equals(seven_ones + "尾")) {
                                winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                        }
                    }
                    if ("正特尾数".equals(quizOrders.getQuizTitle())) {//分类二类
                        //对正码和特码的尾数进行下注，下注尾数必须在当期开出的正码或特码对应的尾数中即中奖，不论同尾数出现一个或者多个都只派彩一次
                        if ("正特尾数".equals(quizOrders.getQuizDetailsName())) {//分类三类
                            if (quizOrders.getQuizIntroduces().indexOf(Arrays.toString(wnTail)) != -1) {
                                winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                        }
                    }
                    //选择2-4个尾数为一投注组合进行投注。该注的2-4个尾数必须在当期开出的7个开奖号码相对应的尾数中，（49亦算输赢，不为和）。每个号码都有自己的赔率，下注组合的总赔率，取该组合码的最低赔率为下单赔率
                    if ("二尾连中".equals(quizOrders.getQuizTitle())
                            || "三尾连中".equals(quizOrders.getQuizTitle())
                            || "四尾连中".equals(quizOrders.getQuizTitle())
                            || "二尾连不中".equals(quizOrders.getQuizTitle())
                            || "三尾连不中".equals(quizOrders.getQuizTitle())
                            || "四尾连不中".equals(quizOrders.getQuizTitle())) {//分类二类
                        Map<String, Object> params = new HashMap<>();
                        params.put("orderNo", quizOrders.getOrderNo());
                        List<QuizSuborders> quizSubordersList = quizSubordersService.findList(params);
                        if ("二尾连中".equals(quizOrders.getQuizTitle())) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                                if (Arrays.asList(wnTail).contains(bettingNumberList[0])
                                        && Arrays.asList(wnTail).contains(bettingNumberList[1])) {
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                }
                            }
                        }
                        if ("三尾连中".equals(quizOrders.getQuizTitle())) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                                if (Arrays.asList(wnTail).contains(bettingNumberList[0])
                                        && Arrays.asList(wnTail).contains(bettingNumberList[1])
                                        && Arrays.asList(wnTail).contains(bettingNumberList[2])) {
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                }
                            }
                        }
                        if ("四尾连中".equals(quizOrders.getQuizTitle())) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                                if (Arrays.asList(wnTail).contains(bettingNumberList[0])
                                        && Arrays.asList(wnTail).contains(bettingNumberList[1])
                                        && Arrays.asList(wnTail).contains(bettingNumberList[2])
                                        && Arrays.asList(wnTail).contains(bettingNumberList[3])) {
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                }
                            }
                        }
                        if ("二尾连不中".equals(quizOrders.getQuizTitle())) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                                if (!Arrays.asList(wnTail).contains(bettingNumberList[0])
                                        && !Arrays.asList(wnTail).contains(bettingNumberList[1])) {
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                }
                            }
                        }
                        if ("三尾连不中".equals(quizOrders.getQuizTitle())) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                                if (!Arrays.asList(wnTail).contains(bettingNumberList[0])
                                        && !Arrays.asList(wnTail).contains(bettingNumberList[1])
                                        && !Arrays.asList(wnTail).contains(bettingNumberList[2])) {
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                }
                            }
                        }
                        if ("四尾连不中".equals(quizOrders.getQuizTitle())) {
                            for (QuizSuborders quizSuborders : quizSubordersList) {
                                String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                                if (!Arrays.asList(wnTail).contains(bettingNumberList[0])
                                        && !Arrays.asList(wnTail).contains(bettingNumberList[1])
                                        && !Arrays.asList(wnTail).contains(bettingNumberList[2])
                                        && !Arrays.asList(wnTail).contains(bettingNumberList[3])) {
                                    winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                                }
                            }
                        }
                    }
                } else if ("多选中一".equals(quizOrders.getSiteCategoryName())) {//分类一类 每个号码都有自己的赔率，下注组合取该组合最低赔率为总赔率
                    //挑选5个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
                    Map<String, Object> params = new HashMap<>();
                    params.put("orderNo", quizOrders.getOrderNo());
                    List<QuizSuborders> quizSubordersList = quizSubordersService.findList(params);
                    if ("五选中一".equals(quizOrders.getQuizTitle())) {//分类二类
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[2])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[3])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[4])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                            }
                        }
                    }
                    //挑选6个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
                    if ("六选中一".equals(quizOrders.getQuizTitle())) {//分类二类
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[2])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[3])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[4])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[5])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                            }
                        }
                    }
                    //挑选7个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
                    if ("七选中一".equals(quizOrders.getQuizTitle())) {//分类二类
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
                            }
                        }
                    }
                    //挑选8个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
                    if ("八选中一".equals(quizOrders.getQuizTitle())) {//分类二类
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
                            }
                        }
                    }
                    //挑选9个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
                    if ("九选中一".equals(quizOrders.getQuizTitle())) {//分类二类
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
                            }
                        }
                    }
                    //挑选10个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
                    if ("十选中一".equals(quizOrders.getQuizTitle())) {//分类二类
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
                            }
                        }
                    }
                } else if ("自选不中".equals(quizOrders.getSiteCategoryName())) {//分类一类
                    Map<String, Object> params = new HashMap<>();
                    params.put("orderNo", quizOrders.getOrderNo());
                    List<QuizSuborders> quizSubordersList = quizSubordersService.findList(params);
                    if ("五不中".equals(quizOrders.getQuizTitle())) {//分类二类
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (!Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[2])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[3])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[4])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                            }
                        }
                    }
                    if ("六不中".equals(quizOrders.getQuizTitle())) {//分类二类
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (!Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[2])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[3])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[4])
                                    && !Arrays.asList(wnNumbers).contains(bettingNumberList[5])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                            }
                        }
                    }
                    if ("七不中".equals(quizOrders.getQuizTitle())) {//分类二类
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
                            }
                        }
                    }
                    if ("八不中".equals(quizOrders.getQuizTitle())) {//分类二类
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
                            }
                        }
                    }
                    if ("九不中".equals(quizOrders.getQuizTitle())) {//分类二类
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
                            }
                        }
                    }
                    if ("十不中".equals(quizOrders.getQuizTitle())) {//分类二类
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
                            }
                        }
                    }
                    if ("十一不中".equals(quizOrders.getQuizTitle())) {//分类二类
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
                            }
                        }
                    }
                } else if ("正特任中".equals(quizOrders.getSiteCategoryName())) {//分类一类
                    Map<String, Object> params = new HashMap<>();
                    params.put("orderNo", quizOrders.getOrderNo());
                    List<QuizSuborders> quizSubordersList = quizSubordersService.findList(params);
                    //每个号码都有自己的赔率，下注组合的总赔率，取该组合的最低赔率
                    if ("一粒任中".equals(quizOrders.getQuizTitle())) {//分类二类
                        //挑选1个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            if (Arrays.asList(wnNumbers).contains(quizSuborders.getBettingContent())) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                            }
                        }
                    }
                    if ("二粒任中".equals(quizOrders.getQuizTitle())) {//分类二类
                        //挑选2个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[1])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                            }
                        }
                    }
                    if ("三粒任中".equals(quizOrders.getQuizTitle())) {//分类二类
                        //挑选3个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[2])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                            }
                        }
                    }
                    if ("四粒任中".equals(quizOrders.getQuizTitle())) {//分类二类
                        //挑选4个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[2])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[3])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                            }
                        }
                    }
                    if ("五粒任中".equals(quizOrders.getQuizTitle())) {//分类二类
                        //挑选5个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
                        for (QuizSuborders quizSuborders : quizSubordersList) {
                            String[] bettingNumberList = quizSuborders.getBettingContent().split(",");//投注转换数组
                            if (Arrays.asList(wnNumbers).contains(bettingNumberList[0])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[1])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[2])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[3])
                                    || Arrays.asList(wnNumbers).contains(bettingNumberList[4])) {
                                winAmount = winAmount.add(quizSuborders.getTotalPrice().multiply(quizSuborders.getOdds()));
                            }
                        }
                    }
                } else if ("五行".equals(quizOrders.getSiteCategoryName())) {//分类一类
                    if ("五行".equals(quizOrders.getQuizTitle())) {//分类二类
                        if ("五行".equals(quizOrders.getQuizDetailsName())) {//分类三类
                            if ("金".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(jinList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("木".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(muList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("水".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(shuiList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("火".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(huoList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("土".equals(quizOrders.getQuizIntroduces())) {
                                if (Arrays.asList(tuList).contains(seven))
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                        }
                    }
                } else if ("七码".equals(quizOrders.getSiteCategoryName())) {//分类一类
                    if ("七码".equals(quizOrders.getQuizTitle())) {//分类二类
                        if ("单".equals(quizOrders.getQuizDetailsName())) {//分类三类
                            if ("单0".equals(quizOrders.getQuizIntroduces())) {
                                if (0 == singlecount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("单1".equals(quizOrders.getQuizIntroduces())) {
                                if (1 == singlecount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("单2".equals(quizOrders.getQuizIntroduces())) {
                                if (2 == singlecount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("单3".equals(quizOrders.getQuizIntroduces())) {
                                if (3 == singlecount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("单4".equals(quizOrders.getQuizIntroduces())) {
                                if (4 == singlecount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("单5".equals(quizOrders.getQuizIntroduces())) {
                                if (5 == singlecount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("单6".equals(quizOrders.getQuizIntroduces())) {
                                if (6 == singlecount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("单7".equals(quizOrders.getQuizIntroduces())) {
                                if (7 == singlecount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                        }
                        if ("双".equals(quizOrders.getQuizDetailsName())) {//分类三类
                            if ("双0".equals(quizOrders.getQuizIntroduces())) {
                                if (0 == doublecount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("双1".equals(quizOrders.getQuizIntroduces())) {
                                if (1 == doublecount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("双2".equals(quizOrders.getQuizIntroduces())) {
                                if (2 == doublecount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("双3".equals(quizOrders.getQuizIntroduces())) {
                                if (3 == doublecount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("双4".equals(quizOrders.getQuizIntroduces())) {
                                if (4 == doublecount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("双5".equals(quizOrders.getQuizIntroduces())) {
                                if (5 == doublecount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("双6".equals(quizOrders.getQuizIntroduces())) {
                                if (6 == doublecount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("双7".equals(quizOrders.getQuizIntroduces())) {
                                if (7 == doublecount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }

                        }
                        if ("大".equals(quizOrders.getQuizDetailsName())) {//分类三类
                            if ("大0".equals(quizOrders.getQuizIntroduces())) {
                                if (0 == bigcount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("大1".equals(quizOrders.getQuizIntroduces())) {
                                if (1 == bigcount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("大2".equals(quizOrders.getQuizIntroduces())) {
                                if (2 == bigcount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("大3".equals(quizOrders.getQuizIntroduces())) {
                                if (3 == bigcount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("大4".equals(quizOrders.getQuizIntroduces())) {
                                if (4 == bigcount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("大5".equals(quizOrders.getQuizIntroduces())) {
                                if (5 == bigcount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("大6".equals(quizOrders.getQuizIntroduces())) {
                                if (6 == bigcount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("大7".equals(quizOrders.getQuizIntroduces())) {
                                if (7 == bigcount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                        }
                        if ("小".equals(quizOrders.getQuizDetailsName())) {//分类三类
                            if ("小0".equals(quizOrders.getQuizIntroduces())) {
                                if (0 == smallcount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("小1".equals(quizOrders.getQuizIntroduces())) {
                                if (1 == smallcount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("小2".equals(quizOrders.getQuizIntroduces())) {
                                if (2 == smallcount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("小3".equals(quizOrders.getQuizIntroduces())) {
                                if (3 == smallcount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("小4".equals(quizOrders.getQuizIntroduces())) {
                                if (4 == smallcount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("小5".equals(quizOrders.getQuizIntroduces())) {
                                if (5 == smallcount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("小6".equals(quizOrders.getQuizIntroduces())) {
                                if (6 == smallcount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                            if ("小7".equals(quizOrders.getQuizIntroduces())) {
                                if (7 == smallcount)
                                    winAmount = winAmount.add(quizOrders.getTotalPrice().multiply(quizOrders.getOdds()));
                            }
                        }
                    }
                }
            }
        }
        return winAmount;
    }
    private BigDecimal daxiaodangshuanghonglvlanbo(QuizOrders quizOrders,String enterNumber,
                                                   Integer onesNumber,Integer tensNumber,
                                                   String[] bigSingle,String[] bigDouble,String[] smallSingle,String[] smallDouble,
                                                   List<String> hongboList,List<String> lvboList,List<String> lanboList){
        BigDecimal winAmount = BigDecimal.ZERO;
        //大：特别码大于或等于25
        if ("大".equals(quizOrders.getQuizIntroduces())) {
            if (Integer.parseInt(enterNumber) >= 25)
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //小：特别码小于或等于24
        if ("小".equals(quizOrders.getQuizIntroduces())) {
            if (Integer.parseInt(enterNumber) <= 24)
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //单：特别码为奇数
        if ("单".equals(quizOrders.getQuizIntroduces())) {
            if (Integer.parseInt(enterNumber) % 2 != 0)
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //双：特别码为偶数
        if ("双".equals(quizOrders.getQuizIntroduces())) {
            if (Integer.parseInt(enterNumber) % 2 == 0)
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //合大：和值大于或等于7
        if ("合大".equals(quizOrders.getQuizIntroduces())) {
            if (onesNumber + tensNumber >= 7)
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //合小：和值小于或等于6合数小
        if ("合小".equals(quizOrders.getQuizIntroduces())) {
            if (onesNumber + tensNumber <= 6)
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //合单：和值为奇数
        if ("合单".equals(quizOrders.getQuizIntroduces())) {
            if ((onesNumber + tensNumber) % 2 != 0)
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //合双：和值为偶数
        if ("合双".equals(quizOrders.getQuizIntroduces())) {
            if ((onesNumber + tensNumber) % 2 == 0)
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //大单
        if ("大单".equals(quizOrders.getQuizIntroduces())) {
            if (Arrays.asList(bigSingle).contains(enterNumber))
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //大双
        if ("大双".equals(quizOrders.getQuizIntroduces())) {
            if (Arrays.asList(bigDouble).contains(enterNumber))
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //小单
        if ("小单".equals(quizOrders.getQuizIntroduces())) {
            if (Arrays.asList(smallSingle).contains(enterNumber))
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //小双
        if ("小双".equals(quizOrders.getQuizIntroduces())) {
            if (Arrays.asList(smallDouble).contains(enterNumber))
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //尾大：大于等于5为大
        if ("尾大".equals(quizOrders.getQuizIntroduces())) {
            if (onesNumber <= 5)
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        //尾小：小于等于4为小
        if ("尾小".equals(quizOrders.getQuizIntroduces())) {
            if (onesNumber <= 4)
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        if ("红波".equals(quizOrders.getQuizIntroduces())) {
            if (Arrays.asList(hongboList).contains(enterNumber))
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        if ("蓝波".equals(quizOrders.getQuizIntroduces())) {
            if (Arrays.asList(lanboList).contains(enterNumber))
                winAmount = quizOrders.getTotalPrice().multiply(quizOrders.getOdds());
        }
        if ("绿波".equals(quizOrders.getQuizIntroduces())) {
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
        if ("鼠".equals(quizOrders.getQuizIntroduces())) {
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
        if ("牛".equals(quizOrders.getQuizIntroduces())) {
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
        if ("虎".equals(quizOrders.getQuizIntroduces())) {
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
        if ("兔".equals(quizOrders.getQuizIntroduces())) {
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
        if ("龙".equals(quizOrders.getQuizIntroduces())) {
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
        if ("蛇".equals(quizOrders.getQuizIntroduces())) {
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
        if ("马".equals(quizOrders.getQuizIntroduces())) {
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
        if ("羊".equals(quizOrders.getQuizIntroduces())) {
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
        if ("猴".equals(quizOrders.getQuizIntroduces())) {
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
        if ("鸡".equals(quizOrders.getQuizIntroduces())) {
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
        if ("狗".equals(quizOrders.getQuizIntroduces())) {
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
        if ("猪".equals(quizOrders.getQuizIntroduces())) {
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
     * 香港六合彩结算(自开)
     */
    public void winningSettlementHongkong(){
        //确定是否已经存在本期开奖号码
        Integer lotteryId = LotteryEnum.HONGKONG_MKS.getStatus();
        Map<String, Object> params = new HashMap<>();
        params.put("lotteryId", lotteryId);
        List<SiteLotteryVO> siteLotteryVOList = lotteryService.findList(params);
        //彩种最大
        WnData wnData = wnDataService.lastOneWnData(lotteryId);
        if(null!=wnData){
            //判断是否当前日期开奖号码
            if(DateUtil.dateToyyyyMMdd(new Date()).equals(DateUtil.dateToyyyyMMdd(wnData.getCreateTime()))){
                return;
            }

        }
        //生成新的开奖号码
        String qihaoStr;//期号
        String nextQihao;//下一期号
        Integer year = DateUtil.getYear();//号码归属年份
        Integer nextTime = 0;//下期开奖时间
        if(null==wnData){
            qihaoStr = year+"001";
            nextQihao = year+"002";
        }else {
            nextTime = wnData.getNextTime();
            if (year != wnData.getYear()){
                qihaoStr = year+"001";
                nextQihao = year+"002";
            }else {
                qihaoStr = String.valueOf(wnData.getQihao()+1);
                nextQihao = String.valueOf(wnData.getQihao()+2);
                year = wnData.getYear();
            }
        }
        wnData = new WnData();
        //随机生成中奖号码
        Set<Integer> set = generateRandomNumbers(7, 49);
        String numbers = "";
        for(Integer number: set){
            numbers = numbers + String.format("%02d", number)+",";
        }
        wnData.setQihao(Long.valueOf(qihaoStr));
        //彩种id
        wnData.setLotteryId(lotteryId);
        //开奖号码
        wnData.setNumbers(numbers.substring(0, numbers.length()-1));
        //创建时间
        wnData.setCreateTime(new Date());
        //开奖视频
        //wnData.setVideoPath();
        //下一期开奖时间
        wnData.setNextTime(nextTime);
        wnData.setNextQihao(nextQihao);
        wnData.setYear(year);
        //是否结算完成(0否，1结算完成)
        wnData.setStatus(StatusEnum.ZERO_FALSE.getStatus());

        //汇总彩票销售额
        Map<String, Object> paramssumTotalPrice = new HashMap<>();
        paramssumTotalPrice.put("lotteryId", lotteryId);//彩种ID
        paramssumTotalPrice.put("status", OrderStatusEnum.ORDER_ONE.getStatus());//1 待开奖,2 已取消,3 中奖,4 未中奖
        paramssumTotalPrice.put("periods", wnData.getQihao());//期数
        BigDecimal totalSalesAmount = siteOrderService.sumTotalPrice(paramssumTotalPrice);
        //杀率0到100
        List<KillOdds> killOddsList = killOddsService.findList(null);
        if(null!=killOddsList && killOddsList.size()>0) {
            killOddsList = killOddsList.stream().filter(killOdds1 -> lotteryId == killOdds1.getLotteryId())
                    .collect(Collectors.toList());
        }
        KillOdds killOdds = killOddsList.get(0);
        //中奖率=100减去杀率
        Integer winOdds = 100 - killOdds.getKillOdds();
        //计算出设定中奖金额（彩票销售总额*中奖率/100）
        BigDecimal totalWinAmount = totalSalesAmount.multiply(BigDecimal.valueOf(winOdds/100));
        //计算中奖金额

        //修改投注结果
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
        //开奖生肖
        String winZodiacStr = this.winZodiac(one,shuList,niuList,huList,tuzList,longList,sheList,maList,yanList,houList,jiList,gouList,zhuList)+","+
                this.winZodiac(two,shuList,niuList,huList,tuzList,longList,sheList,maList,yanList,houList,jiList,gouList,zhuList)+","+
                this.winZodiac(three,shuList,niuList,huList,tuzList,longList,sheList,maList,yanList,houList,jiList,gouList,zhuList)+","+
                this.winZodiac(four,shuList,niuList,huList,tuzList,longList,sheList,maList,yanList,houList,jiList,gouList,zhuList)+","+
                this.winZodiac(five,shuList,niuList,huList,tuzList,longList,sheList,maList,yanList,houList,jiList,gouList,zhuList)+","+
                this.winZodiac(six,shuList,niuList,huList,tuzList,longList,sheList,maList,yanList,houList,jiList,gouList,zhuList)+","+
                this.winZodiac(seven,shuList,niuList,huList,tuzList,longList,sheList,maList,yanList,houList,jiList,gouList,zhuList);
        Map<String, Object> paramspage = new HashMap<>();
        paramspage.put("lotteryId", lotteryId);//彩种ID
        paramspage.put("status", OrderStatusEnum.ORDER_ONE.getStatus());//1 待开奖,2 已取消,3 中奖,4 未中奖
        paramspage.put("periods", wnData.getQihao());//期数
        List<QuizOrders> list =  siteOrderService.findList(paramspage);
        BigDecimal actualWinAmount = BigDecimal.ZERO;//实际中奖金额
        if (null != list && list.size() > 0) {
            actualWinAmount = this.winningSettlement(list,
                    one,two,three,four,five,six,seven,
                    seven_ones,seven_tens,
                    bigSingle,bigDouble,smallSingle,smallDouble,
                    jinList,muList,shuiList,huoList,tuList,
                    shuList,niuList,huList,tuzList,longList,sheList,maList,yanList,houList,jiList,gouList,zhuList,
                    hongboList,lvboList,lanboList,
                    jiaList,yeList,
                    winZodiacStr,
                    bigcount,smallcount,doublecount,singlecount,
//                            head0,head1,head2,head3,head4,
//                            tail0,tail1,tail2,tail3,tail4,tail5,tail6,tail7,tail8,tail9,
                    wnTail,
                    wnNumbersSix,
                    wnNumbers);
        }

        //如果实际中奖金额小于设定中奖金额
        if(totalWinAmount.compareTo(actualWinAmount)==1){
            wnDataService.saveWnData(wnData);
        }else {//如果实际中奖金额大于设定中奖金额，必须重新生成中奖号码
            this.winningSettlementHongkong();
        }
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
    public List<String> getBettingComList(String[] bettingCom,int length) {
        List<String> bettingZodiacList = new ArrayList<>();
        for(int i = 0; i < bettingCom.length; i++){
            for(int j = 0; j < bettingCom.length; j++){
                if(i != j){
                    if(length==2){
                        bettingZodiacList.add(bettingCom[i] + "," + bettingCom[j]);
                    }else {
                        for (int k = 0; k < bettingCom.length; k++) {
                            if (k != i && k != j) {
                                if (length == 3) {
                                    bettingZodiacList.add(bettingCom[i] + "," + bettingCom[j] + "," + bettingCom[k]);
                                } else {
                                    for (int m = 0; m < bettingCom.length; m++) {
                                        if (m != i && m != k && m != j) {
                                            if (length == 4) {
                                                bettingZodiacList.add(bettingCom[i] + "," + bettingCom[j] + "," + bettingCom[k] + "," + bettingCom[m]);
                                            } else {
                                                for (int n = 0; n < bettingCom.length; n++) {
                                                    if (n != i && n != j && n != k && n != m) {
                                                        if (length == 5) {
                                                            bettingZodiacList.add(bettingCom[i] + "," + bettingCom[j] + "," + bettingCom[k] + "," + bettingCom[m] + "," + bettingCom[n]);
                                                        } else {
                                                            for (int o = 0; o < bettingCom.length; o++) {
                                                                if (o != i && o != j && o != k && o != m && o != n) {
                                                                    if (length == 6) {
                                                                        bettingZodiacList.add(bettingCom[i] + "," + bettingCom[j] + "," + bettingCom[k] + "," + bettingCom[m] + "," + bettingCom[n] + "," + bettingCom[o]);
                                                                    }else {
                                                                        for (int p = 0; p < bettingCom.length; p++) {
                                                                            if (p != i && p != j && p != k && p != m && p != n && p != o) {
                                                                                if (length == 7) {
                                                                                    bettingZodiacList.add(bettingCom[i] + "," + bettingCom[j] + "," + bettingCom[k] + "," + bettingCom[m] + "," + bettingCom[n] + "," + bettingCom[o] + "," + bettingCom[p]);
                                                                                }else {
                                                                                    for (int q = 0; q < bettingCom.length; q++) {
                                                                                        if (q != i && q != j && q != k && q != m && q != n && q != o && q != p) {
                                                                                            if (length == 8) {
                                                                                                bettingZodiacList.add(bettingCom[i] + "," + bettingCom[j] + "," + bettingCom[k] + "," + bettingCom[m] + "," + bettingCom[n] + "," + bettingCom[o] + "," + bettingCom[p] + "," + bettingCom[q]);
                                                                                            }else {
                                                                                                for (int r = 0; r < bettingCom.length; r++) {
                                                                                                    if (r != i && r != j && r != k && r != m && r != n && r != o && r != p && r != q) {
                                                                                                        if (length == 9) {
                                                                                                            bettingZodiacList.add(bettingCom[i] + "," + bettingCom[j] + "," + bettingCom[k] + "," + bettingCom[m] + "," + bettingCom[n] + "," + bettingCom[o] + "," + bettingCom[p] + "," + bettingCom[q] + "," + bettingCom[r]);
                                                                                                        }else {
                                                                                                            for (int s = 0; s < bettingCom.length; s++) {
                                                                                                                if (s != i && s != j && s != k && s != m && s != n && s != o && s != p && s != q && s != r) {
                                                                                                                    if (length == 10) {
                                                                                                                        bettingZodiacList.add(bettingCom[i] + "," + bettingCom[j] + "," + bettingCom[k] + "," + bettingCom[m] + "," + bettingCom[n] + "," + bettingCom[o] + "," + bettingCom[p] + "," + bettingCom[q] + "," + bettingCom[r] + "," + bettingCom[s]);
                                                                                                                    }else {
                                                                                                                        for (int t = 0; t < bettingCom.length; t++) {
                                                                                                                            if (t != i && t != j && t != k && t != m && t != n && t != o && t != p && t != q && t != r && t != s) {
                                                                                                                                if (length == 11) {
                                                                                                                                    bettingZodiacList.add(bettingCom[i] + "," + bettingCom[j] + "," + bettingCom[k] + "," + bettingCom[m] + "," + bettingCom[n] + "," + bettingCom[o] + "," + bettingCom[p] + "," + bettingCom[q] + "," + bettingCom[r] + "," + bettingCom[s] + "," + bettingCom[t]);
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
                                }
                            }
                        }
                    }
                }
            }
        }
        return bettingZodiacList;
    }



    public static Set<Integer> generateRandomNumbers(int n, int max) {
        if (n > max) {
            throw new IllegalArgumentException("n must be less than or equal to max");
        }

        Set<Integer> set = new HashSet<>();
        Random random = new Random();

        while (set.size() < n) {
            int num = random.nextInt(max) + 1;
            set.add(num);
        }

        return set;
    }

}
