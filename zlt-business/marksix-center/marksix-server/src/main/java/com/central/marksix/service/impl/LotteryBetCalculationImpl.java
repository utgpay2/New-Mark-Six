package com.central.marksix.service.impl;

import com.central.common.model.NumberAttributes;
import com.central.common.model.Result;
import com.central.common.utils.DateUtil;
import com.central.marksix.entity.dto.*;
import com.central.marksix.entity.vo.BettingNumberGroupVo;
import com.central.marksix.entity.vo.BettingNumberVo;
import com.central.marksix.service.ILotteryBetCalculationService;
import com.central.marksix.service.INumberAttributesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 彩票投注计算
 */
@Slf4j
@Service
public class LotteryBetCalculationImpl implements ILotteryBetCalculationService {
    @Autowired
    private INumberAttributesService numberAttributesService;
    /**
     * 彩票复式投注计算
     * @param duplexLotteryBetDto
     * @return
     */
    @Override
    public Result duplexLotteryBetNumber(DuplexLotteryBetDto duplexLotteryBetDto){
        List<QuizChooseDto> quizChooseDtoList = duplexLotteryBetDto.getQuizChooseDtoList();
        HashSet<String> bettingNumberHashSet = new HashSet<>();
            String[] numberStr = new String[quizChooseDtoList.size()];
        for (int i=0;i<quizChooseDtoList.size();i++){
            QuizChooseDto dto = quizChooseDtoList.get(i);
            numberStr[i] = dto.getIntroduce();
        }
        //所投注的每三个号码为一组合，若三个号码都是开奖号码之正码，视为中奖，其余行情视为不中奖
        //所投注的每三个号码为一组合，若其中2个号码都是开奖号码之正码，视为三中二奖，若3个都是开奖号码中的正码，即为三中二之中三，其余行情视为不中奖
        if("三全中".equals(duplexLotteryBetDto.getQuizTitle())
                ||"三中二".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<3){
                return Result.failed("选择投注号码必须大于等于3个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,3,true);
        }

        //所投注的每二个号码为一组合，若二个号码都是开奖号码之正码，视为中奖，其余行情视为不中奖（含一个正码加一个特码情形）
        //所投注的每二个号码为一组合，若二个号码都是开奖号码之正码，叫二中特之中二，其中一个是正码，一个是特码，视为中奖,其余行情视为不中奖二中特之中二赔率高于二中特之中特的赔率
        //所投注的每两个号码为一组合，其中一个是正码，一个是特码，视为中奖，其余情形视为不中奖（含二个都是正码之情形）
        if("二全中".equals(duplexLotteryBetDto.getQuizTitle())
                ||"二中特".equals(duplexLotteryBetDto.getQuizTitle())
                ||"特串".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<2){
                return Result.failed("选择投注号码必须大于等于2个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,2,true);
        }
        //所投注号码每四个为一组，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("四中一".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<4){
                return Result.failed("选择投注号码必须大于等于4个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,4,true);
        }
        //选择2-4个尾数为一投注组合进行投注。该注的2-4个尾数必须在当期开出的7个开奖号码相对应的尾数中，（49亦算输赢，不为和）。每个号码都有自己的赔率，下注组合的总赔率，取该组合码的最低赔率为下单赔率
        if("二尾连中".equals(duplexLotteryBetDto.getQuizTitle())
                ||"二尾连不中".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<2){
                return Result.failed("选择投注号码必须大于等于2个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,2,false);
        }
        if("三尾连中".equals(duplexLotteryBetDto.getQuizTitle())
                ||"三尾连不中".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<3){
                return Result.failed("选择投注号码必须大于等于3个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,3,false);
        }
        if("四尾连中".equals(duplexLotteryBetDto.getQuizTitle())
                ||"四尾连不中".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<4){
                return Result.failed("选择投注号码必须大于等于4个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,4,false);
        }
        //挑选5个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("五选中一".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<5){
                return Result.failed("选择投注号码必须大于等于5个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,5,true);
        }
        //挑选6个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("六选中一".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<6){
                return Result.failed("选择投注号码必须大于等于6个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,6,true);
        }
        //挑选7个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("七选中一".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<7){
                return Result.failed("选择投注号码必须大于等于7个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,7,true);
        }
        //挑选8个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("八选中一".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<8){
                return Result.failed("选择投注号码必须大于等于8个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,8,true);
        }
        //挑选9个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("九选中一".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<9){
                return Result.failed("选择投注号码必须大于等于9个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,9,true);
        }
        //挑选10个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("十选中一".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<10){
                return Result.failed("选择投注号码必须大于等于10个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,10,true);
        }
        if("五不中".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<5){
                return Result.failed("选择投注号码必须大于等于5个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,5,true);
        }
        if("六不中".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<6){
                return Result.failed("选择投注号码必须大于等于6个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,6,true);
        }
        if("七不中".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<7){
                return Result.failed("选择投注号码必须大于等于7个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,7,true);
        }
        if("八不中".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<8){
                return Result.failed("选择投注号码必须大于等于2个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,8,true);
        }
        if("九不中".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<9){
                return Result.failed("选择投注号码必须大于等于9个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,9,true);
        }
        if("十不中".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<10){
                return Result.failed("选择投注号码必须大于等于10个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,10,true);
        }
        if("十一不中".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<11){
                return Result.failed("选择投注号码必须大于等于11个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,11,true);
        }
        if("十二不中".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<12){
                return Result.failed("选择投注号码必须大于等于12个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,12,true);
        }
        //挑选1个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("一粒任中".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            bettingNumberHashSet = new HashSet();
            for (String str:numberStr){
                bettingNumberHashSet.add(str);
            }
        }
        //挑选2个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("二粒任中".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<2){
                return Result.failed("选择投注号码必须大于等于2个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,2,true);
        }
        //挑选3个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("三粒任中".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<3){
                return Result.failed("选择投注号码必须大于等于3个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,3,true);
        }
        //挑选4个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("四粒任中".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<4){
                return Result.failed("选择投注号码必须大于等于4个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,4,true);
        }
        //挑选5个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("五粒任中".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<5){
                return Result.failed("选择投注号码必须大于等于5个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,5,true);
        }
        List<BettingNumberGroupVo> bettingNumberGroupVoList = new ArrayList<>();
        boolean b = true;
        for (String bettingNumberStr:bettingNumberHashSet) {
            BettingNumberGroupVo bettingNumberGroupVo = new BettingNumberGroupVo();
            String[] bettingNumber = bettingNumberStr.split(",");//每组投注号码
            Double[] oddsArrays = new Double[bettingNumber.length];
            Double[] oddsArrays2 = new Double[bettingNumber.length];
            List<BettingNumberVo> numberVoList =  new ArrayList<>();
            for(int i=0;i<bettingNumber.length;i++) {
                BettingNumberVo bettingNumberVo = new BettingNumberVo();
                for (int j = 0; j < quizChooseDtoList.size(); j++) {
                    QuizChooseDto dto = quizChooseDtoList.get(j);
                    if(bettingNumber[i].equals(dto.getIntroduce())){
                        oddsArrays[i] = dto.getOdds();
                        if(null!=dto.getOdds2()&&dto.getOdds2()>0){
                            oddsArrays2[i] = dto.getOdds2();
                        }else {
                            b = false;
                        }
                        bettingNumberVo.setBettingNumber(bettingNumber[i]);
                        bettingNumberVo.setColor(dto.getColor());
                    }
                }
                numberVoList.add(bettingNumberVo);
            }
            Double oddsMin = this.getMin(oddsArrays);//取最小值（赔率）
            if(b){
                Double oddsMin2 = this.getMin(oddsArrays2);//取最小值（赔率）
                bettingNumberGroupVo.setOddsMin2(oddsMin2);
            }
            bettingNumberGroupVo.setOddsMin(oddsMin);
            bettingNumberGroupVo.setNumberVoList(numberVoList);
            bettingNumberGroupVoList.add(bettingNumberGroupVo);
        }
        return Result.succeed(bettingNumberGroupVoList);
    }
    /**
     * 复式投注
     * @param str 选择号码
     * @param length 每注彩票组合个数
     * @return
     */
    public HashSet<String> duplexNumber(String[] str , int length,boolean b){
        List<String> list = new ArrayList<>();
        for(int i=0;i<str.length;i++) {
            if (length == 1) {
                list.add(str[i]);
            }else {
                for (int j = 0; j < str.length; j++) {
                    if (i != j) {
                        if (length == 2) {
                            Integer[] str1 = {Integer.parseInt(str[i]), Integer.parseInt(str[j])};
                            Arrays.sort(str1);
                            if (b) {
                                list.add(String.format("%02d", str1[0]) + "," + String.format("%02d", str1[1]));
                            } else {
                                list.add(str1[0] + "," + str1[1]);
                            }
                        } else {
                            for (int k = 0; k < str.length; k++) {
                                if (k != i && k != j) {
                                    if (length == 3) {
                                        Integer[] str1 = {Integer.parseInt(str[i]), Integer.parseInt(str[j]), Integer.parseInt(str[k])};
                                        Arrays.sort(str1);
                                        if (b) {
                                            list.add(String.format("%02d", str1[0]) + "," + String.format("%02d", str1[1]) + "," + String.format("%02d", str1[2]));
                                        } else {
                                            list.add(str1[0] + "," + str1[1] + "," + str1[2]);
                                        }
                                    } else {
                                        for (int m = 0; m < str.length; m++) {
                                            if (m != i && m != k && m != j) {
                                                if (length == 4) {
                                                    Integer[] str1 = {Integer.parseInt(str[i]), Integer.parseInt(str[j]), Integer.parseInt(str[k]), Integer.parseInt(str[m])};
                                                    Arrays.sort(str1);
                                                    if (b) {
                                                        list.add(String.format("%02d", str1[i]) + "," + String.format("%02d", str1[j]) + "," + String.format("%02d", str1[k]) + "," + String.format("%02d", str1[m]));
                                                    } else {
                                                        list.add(str1[i] + "," + str1[j] + "," + str1[k] + "," + str1[m]);
                                                    }
                                                } else {
                                                    for (int n = 0; n < str.length; n++) {
                                                        if (n != i && n != j && n != k && n != m) {
                                                            if (length == 5) {
                                                                Integer[] str1 = {Integer.parseInt(str[i]), Integer.parseInt(str[j]), Integer.parseInt(str[k]), Integer.parseInt(str[m]), Integer.parseInt(str[n])};
                                                                Arrays.sort(str1);
                                                                if (b) {
                                                                    list.add(String.format("%02d", str1[i]) + "," + String.format("%02d", str1[j]) + "," + String.format("%02d", str1[k]) + "," + String.format("%02d", str1[m]) + "," + String.format("%02d", str1[n]));
                                                                } else {
                                                                    list.add(str1[i] + "," + str1[j] + "," + str1[k] + "," + str1[m] + "," + str1[n]);
                                                                }
                                                            } else {
                                                                for (int o = 0; o < str.length; o++) {
                                                                    if (o != i && o != j && o != k && o != m && o != n) {
                                                                        if (length == 6) {
                                                                            Integer[] str1 = {Integer.parseInt(str[i]), Integer.parseInt(str[j]), Integer.parseInt(str[k]), Integer.parseInt(str[m]), Integer.parseInt(str[n]), Integer.parseInt(str[o])};
                                                                            Arrays.sort(str1);
                                                                            if (b) {
                                                                                list.add(String.format("%02d", str1[i]) + "," + String.format("%02d", str1[j]) + "," + String.format("%02d", str1[k]) + "," + String.format("%02d", str1[m]) + "," + String.format("%02d", str1[n]) + "," + String.format("%02d", str1[o]));
                                                                            } else {
                                                                                list.add(str1[i] + "," + str1[j] + "," + str1[k] + "," + str1[m] + "," + str1[n] + "," + str1[o]);
                                                                            }
                                                                        } else {
                                                                            for (int p = 0; p < str.length; p++) {
                                                                                if (p != i && p != j && p != k && p != m && p != n && p != o) {
                                                                                    if (length == 7) {
                                                                                        Integer[] str1 = {Integer.parseInt(str[i]), Integer.parseInt(str[j]), Integer.parseInt(str[k]), Integer.parseInt(str[m]), Integer.parseInt(str[n]), Integer.parseInt(str[o]), Integer.parseInt(str[p])};
                                                                                        Arrays.sort(str1);
                                                                                        if (b) {
                                                                                            list.add(String.format("%02d", str1[i]) + "," + String.format("%02d", str1[j]) + "," + String.format("%02d", str1[k]) + "," + String.format("%02d", str1[m]) + "," + String.format("%02d", str1[n]) + "," + String.format("%02d", str1[o]) + "," + String.format("%02d", str1[p]));
                                                                                        } else {
                                                                                            list.add(str1[i] + "," + str1[j] + "," + str1[k] + "," + str1[m] + "," + str1[n] + "," + str1[o] + "," + str1[p]);
                                                                                        }
                                                                                    } else {
                                                                                        for (int q = 0; q < str.length; q++) {
                                                                                            if (q != i && q != j && q != k && q != m && q != n && q != o && q != p) {
                                                                                                if (length == 8) {
                                                                                                    Integer[] str1 = {Integer.parseInt(str[i]), Integer.parseInt(str[j]), Integer.parseInt(str[k]), Integer.parseInt(str[m]), Integer.parseInt(str[n]), Integer.parseInt(str[o]), Integer.parseInt(str[p]), Integer.parseInt(str[q])};
                                                                                                    Arrays.sort(str1);
                                                                                                    if (b) {
                                                                                                        list.add(String.format("%02d", str1[i]) + "," + String.format("%02d", str1[j]) + "," + String.format("%02d", str1[k]) + "," + String.format("%02d", str1[m]) + "," + String.format("%02d", str1[n]) + "," + String.format("%02d", str1[o]) + "," + String.format("%02d", str1[p]) + "," + String.format("%02d", str1[q]));
                                                                                                    } else {
                                                                                                        list.add(str1[i] + "," + str1[j] + "," + str1[k] + "," + str1[m] + "," + str1[n] + "," + str1[o] + "," + str1[p] + "," + str1[q]);
                                                                                                    }
                                                                                                } else {
                                                                                                    for (int r = 0; r < str.length; r++) {
                                                                                                        if (r != i && r != j && r != k && r != m && r != n && r != o && r != p && r != q) {
                                                                                                            if (length == 9) {
                                                                                                                Integer[] str1 = {Integer.parseInt(str[i]), Integer.parseInt(str[j]), Integer.parseInt(str[k]), Integer.parseInt(str[m]), Integer.parseInt(str[n]), Integer.parseInt(str[o]), Integer.parseInt(str[p]), Integer.parseInt(str[q]), Integer.parseInt(str[r])};
                                                                                                                Arrays.sort(str1);
                                                                                                                if (b) {
                                                                                                                    list.add(String.format("%02d", str1[i]) + "," + String.format("%02d", str1[j]) + "," + String.format("%02d", str1[k]) + "," + String.format("%02d", str1[m]) + "," + String.format("%02d", str1[n]) + "," + String.format("%02d", str1[o]) + "," + String.format("%02d", str1[p]) + "," + String.format("%02d", str1[q]) + "," + String.format("%02d", str1[r]));
                                                                                                                } else {
                                                                                                                    list.add(str1[i] + "," + str1[j] + "," + str1[k] + "," + str1[m] + "," + str1[n] + "," + str1[o] + "," + str1[p] + "," + str1[q] + "," + str1[r]);
                                                                                                                }
                                                                                                            } else {
                                                                                                                for (int s = 0; s < str.length; s++) {
                                                                                                                    if (s != i && s != j && s != k && s != m && s != n && s != o && s != p && s != q && s != r) {
                                                                                                                        if (length == 10) {
                                                                                                                            Integer[] str1 = {Integer.parseInt(str[i]), Integer.parseInt(str[j]), Integer.parseInt(str[k]), Integer.parseInt(str[m]), Integer.parseInt(str[n]), Integer.parseInt(str[o]), Integer.parseInt(str[p]), Integer.parseInt(str[q]), Integer.parseInt(str[r]), Integer.parseInt(str[s])};
                                                                                                                            Arrays.sort(str1);
                                                                                                                            if (b) {
                                                                                                                                list.add(String.format("%02d", str1[i]) + "," + String.format("%02d", str1[j]) + "," + String.format("%02d", str1[k]) + "," + String.format("%02d", str1[m]) + "," + String.format("%02d", str1[n]) + "," + String.format("%02d", str1[o]) + "," + String.format("%02d", str1[p]) + "," + String.format("%02d", str1[q]) + "," + String.format("%02d", str1[r]) + "," + String.format("%02d", str1[s]));
                                                                                                                            } else {
                                                                                                                                list.add(str1[i] + "," + str1[j] + "," + str1[k] + "," + str1[m] + "," + str1[n] + "," + str1[o] + "," + str1[p] + "," + str1[q] + "," + str1[r] + "," + str1[s]);
                                                                                                                            }
                                                                                                                        } else {
                                                                                                                            for (int t = 0; t < str.length; t++) {
                                                                                                                                if (t != i && t != j && t != k && t != m && t != n && t != o && t != p && t != q && t != r && t != s) {
                                                                                                                                    if (length == 11) {
                                                                                                                                        Integer[] str1 = {Integer.parseInt(str[i]), Integer.parseInt(str[j]), Integer.parseInt(str[k]), Integer.parseInt(str[m]), Integer.parseInt(str[n]), Integer.parseInt(str[o]), Integer.parseInt(str[p]), Integer.parseInt(str[q]), Integer.parseInt(str[r]), Integer.parseInt(str[s]), Integer.parseInt(str[t])};
                                                                                                                                        Arrays.sort(str1);
                                                                                                                                        if (b) {
                                                                                                                                            list.add(String.format("%02d", str1[i]) + "," + String.format("%02d", str1[j]) + "," + String.format("%02d", str1[k]) + "," + String.format("%02d", str1[m]) + "," + String.format("%02d", str1[n]) + "," + String.format("%02d", str1[o]) + "," + String.format("%02d", str1[p]) + "," + String.format("%02d", str1[q]) + "," + String.format("%02d", str1[r]) + "," + String.format("%02d", str1[s]) + "," + String.format("%02d", str1[t]));
                                                                                                                                        } else {
                                                                                                                                            list.add(str1[i] + "," + str1[j] + "," + str1[k] + "," + str1[m] + "," + str1[n] + "," + str1[o] + "," + str1[p] + "," + str1[q] + "," + str1[r] + "," + str1[s] + "," + str1[t]);
                                                                                                                                        }
                                                                                                                                    } else {
                                                                                                                                        for (int u = 0; u < str.length; u++) {
                                                                                                                                            if (u != i && u != j && u != k && u != m && u != n && u != o && u != p && u != q && u != r && u != s && u != t) {
                                                                                                                                                if (length == 12) {
                                                                                                                                                    Integer[] str1 = {Integer.parseInt(str[i]), Integer.parseInt(str[j]), Integer.parseInt(str[k]), Integer.parseInt(str[m]), Integer.parseInt(str[n]), Integer.parseInt(str[o]), Integer.parseInt(str[p]), Integer.parseInt(str[q]), Integer.parseInt(str[r]), Integer.parseInt(str[s]), Integer.parseInt(str[t]), Integer.parseInt(str[u])};
                                                                                                                                                    Arrays.sort(str1);
                                                                                                                                                    if (b) {
                                                                                                                                                        list.add(String.format("%02d", str1[i]) + "," + String.format("%02d", str1[j]) + "," + String.format("%02d", str1[k]) + "," + String.format("%02d", str1[m]) + "," + String.format("%02d", str1[n]) + "," + String.format("%02d", str1[o]) + "," + String.format("%02d", str1[p]) + "," + String.format("%02d", str1[q]) + "," + String.format("%02d", str1[r]) + "," + String.format("%02d", str1[s]) + "," + String.format("%02d", str1[t])+ "," + String.format("%02d", str1[u]));
                                                                                                                                                    } else {
                                                                                                                                                        list.add(str1[i] + "," + str1[j] + "," + str1[k] + "," + str1[m] + "," + str1[n] + "," + str1[o] + "," + str1[p] + "," + str1[q] + "," + str1[r] + "," + str1[s] + "," + str1[t]+ "," + str1[u]);
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
                        }
                    }
                }
            }
        }
        //去重集合
        return new HashSet<>(list);
    }
    /**
     * 彩票胆拖投注计算
     * @param braveryTowLotteryBetDto
     * @return
     */
    @Override
    public Result braveryTowLotteryBetNumber(BraveryTowLotteryBetDto braveryTowLotteryBetDto){
        boolean bl = true;
        //胆码
        List<QuizChooseDto> braveryList = braveryTowLotteryBetDto.getBraveryList();
        //拖码
        List<QuizChooseDto> towList = braveryTowLotteryBetDto.getTowList();
        HashSet<String> bettingNumberHashSet = new HashSet<>();
        int towSize = towList.size();
        int braverySize = braveryList.size();
        String[] numberStr = new String[towSize];
        for (int i=0;i<towSize;i++){
            QuizChooseDto dto = towList.get(i);
            numberStr[i] = dto.getIntroduce();
        }
        //所投注的每三个号码为一组合，若三个号码都是开奖号码之正码，视为中奖，其余行情视为不中奖
        //所投注的每三个号码为一组合，若其中2个号码都是开奖号码之正码，视为三中二奖，若3个都是开奖号码中的正码，即为三中二之中三，其余行情视为不中奖
        if("三全中".equals(braveryTowLotteryBetDto.getQuizTitle())
                ||"三中二".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=3){
                return Result.failed("选择投注胆码总数必须小于3个投注号码");
            }
            if(braverySize+towSize<3){
                return Result.failed("选择投注胆码加拖码总数必须大于等于3个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,3-braverySize,true);
        }

        //所投注的每二个号码为一组合，若二个号码都是开奖号码之正码，视为中奖，其余行情视为不中奖（含一个正码加一个特码情形）
        //所投注的每二个号码为一组合，若二个号码都是开奖号码之正码，叫二中特之中二，其中一个是正码，一个是特码，视为中奖,其余行情视为不中奖二中特之中二赔率高于二中特之中特的赔率
        //所投注的每两个号码为一组合，其中一个是正码，一个是特码，视为中奖，其余情形视为不中奖（含二个都是正码之情形）
        if("二全中".equals(braveryTowLotteryBetDto.getQuizTitle())
                ||"二中特".equals(braveryTowLotteryBetDto.getQuizTitle())
                ||"特串".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=2){
                return Result.failed("选择投注胆码总数必须小于2个投注号码");
            }
            if(braverySize+towSize<2){
                return Result.failed("选择投注胆码加拖码总数必须大于等于2个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,2-braverySize,true);
        }
        //所投注号码每四个为一组，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("四中一".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=4){
                return Result.failed("选择投注胆码总数必须小于4个投注号码");
            }
            if(braverySize+towSize<4){
                return Result.failed("选择投注胆码加拖码总数必须大于等于4个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,4-braverySize,true);
        }
        //选择2-4个尾数为一投注组合进行投注。该注的2-4个尾数必须在当期开出的7个开奖号码相对应的尾数中，（49亦算输赢，不为和）。每个号码都有自己的赔率，下注组合的总赔率，取该组合码的最低赔率为下单赔率
        if("二尾连中".equals(braveryTowLotteryBetDto.getQuizTitle())
                ||"二尾连不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=2){
                return Result.failed("选择投注胆码总数必须小于2个投注号码");
            }
            if(braverySize+towSize<2){
                return Result.failed("选择投注胆码加拖码总数必须大于等于2个投注号码");
            }
            bl = false;
            bettingNumberHashSet = this.duplexNumber(numberStr,2-braverySize,false);
        }
        if("三尾连中".equals(braveryTowLotteryBetDto.getQuizTitle())
                ||"三尾连不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=3){
                return Result.failed("选择投注胆码总数必须小于3个投注号码");
            }
            if(braverySize+towSize<3){
                return Result.failed("选择投注胆码加拖码总数必须大于等于3个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,3-braverySize,false);
            bl = false;
        }
        if("四尾连中".equals(braveryTowLotteryBetDto.getQuizTitle())
                ||"四尾连不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=4){
                return Result.failed("选择投注胆码总数必须小于4个投注号码");
            }
            if(braverySize+towSize<4){
                return Result.failed("选择投注胆码加拖码总数必须大于等于4个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,4-braverySize,false);
            bl = false;
        }
        //挑选5个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("五选中一".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=5){
                return Result.failed("选择投注胆码总数必须小于5个投注号码");
            }
            if(braverySize+towSize<5){
                return Result.failed("选择投注胆码加拖码总数必须大于等于5个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,5-braverySize,true);
        }
        //挑选6个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("六选中一".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=6){
                return Result.failed("选择投注胆码总数必须小于6个投注号码");
            }
            if(braverySize+towSize<6){
                return Result.failed("选择投注胆码加拖码总数必须大于等于6个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,6-braverySize,true);
        }
        //挑选7个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("七选中一".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=7){
                return Result.failed("选择投注胆码总数必须小于7个投注号码");
            }
            if(braverySize+towSize<7){
                return Result.failed("选择投注胆码加拖码总数必须大于等于7个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,7-braverySize,true);
        }
        //挑选8个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("八选中一".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=8){
                return Result.failed("选择投注胆码总数必须小于8个投注号码");
            }
            if(braverySize+towSize<8){
                return Result.failed("选择投注胆码加拖码总数必须大于等于8个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,8-braverySize,true);
        }
        //挑选9个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("九选中一".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=9){
                return Result.failed("选择投注胆码总数必须小于9个投注号码");
            }
            if(braverySize+towSize<9){
                return Result.failed("选择投注胆码加拖码总数必须大于等于9个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,9-braverySize,true);
        }
        //挑选10个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("十选中一".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=10){
                return Result.failed("选择投注胆码总数必须小于10个投注号码");
            }
            if(braverySize+towSize<10){
                return Result.failed("选择投注胆码加拖码总数必须大于等于10个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,10-braverySize,true);
        }
        if("五不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=5){
                return Result.failed("选择投注胆码总数必须小于5个投注号码");
            }
            if(braverySize+towSize<5){
                return Result.failed("选择投注胆码加拖码总数必须大于等于5个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,5-braverySize,true);
        }
        if("六不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=6){
                return Result.failed("选择投注胆码总数必须小于6个投注号码");
            }
            if(braverySize+towSize<6){
                return Result.failed("选择投注胆码加拖码总数必须大于等于6个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,6-braverySize,true);
        }
        if("七不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=7){
                return Result.failed("选择投注胆码总数必须小于7个投注号码");
            }
            if(braverySize+towSize<7){
                return Result.failed("选择投注胆码加拖码总数必须大于等于7个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,7-braverySize,true);
        }
        if("八不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=8){
                return Result.failed("选择投注胆码总数必须小于8个投注号码");
            }
            if(braverySize+towSize<8){
                return Result.failed("选择投注胆码加拖码总数必须大于等于2个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,8-braverySize,true);
        }
        if("九不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=9){
                return Result.failed("选择投注胆码总数必须小于9个投注号码");
            }
            if(braverySize+towSize<9){
                return Result.failed("选择投注胆码加拖码总数必须大于等于9个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,9-braverySize,true);
        }
        if("十不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=10){
                return Result.failed("选择投注胆码总数必须小于10个投注号码");
            }
            if(braverySize+towSize<10){
                return Result.failed("选择投注胆码加拖码总数必须大于等于10个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,10-braverySize,true);
        }
        if("十一不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=11){
                return Result.failed("选择投注胆码总数必须小于11个投注号码");
            }
            if(braverySize+towSize<11){
                return Result.failed("选择投注胆码加拖码总数必须大于等于11个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,11-braverySize,true);
        }
        if("十二不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=12){
                return Result.failed("选择投注胆码总数必须小于12个投注号码");
            }
            if(braverySize+towSize<12){
                return Result.failed("选择投注胆码加拖码总数必须大于等于12个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,12-braverySize,true);
        }
        //挑选1个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("一粒任中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            bettingNumberHashSet = new HashSet();
            for (String str:numberStr){
                bettingNumberHashSet.add(str);
            }
        }
        //挑选2个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("二粒任中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=2){
                return Result.failed("选择投注胆码总数必须小于2个投注号码");
            }
            if(braverySize+towSize<2){
                return Result.failed("选择投注胆码加拖码总数必须大于等于2个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,2-braverySize,true);
        }
        //挑选3个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("三粒任中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=3){
                return Result.failed("选择投注胆码总数必须小于3个投注号码");
            }
            if(braverySize+towSize<3){
                return Result.failed("选择投注胆码加拖码总数必须大于等于3个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,3-braverySize,true);
        }
        //挑选4个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("四粒任中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=4){
                return Result.failed("选择投注胆码总数必须小于4个投注号码");
            }
            if(braverySize+towSize<4){
                return Result.failed("选择投注胆码加拖码总数必须大于等于4个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,4-braverySize,true);
        }
        //挑选5个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("五粒任中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=5){
                return Result.failed("选择投注胆码总数必须小于5个投注号码");
            }
            if(braverySize+towSize<5){
                return Result.failed("选择投注胆码加拖码总数必须大于等于5个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,5-braverySize,true);
        }

        List<String> list = new ArrayList<>();
        for (String towNumberStr:bettingNumberHashSet) {//拖码
            String[] towNumber = towNumberStr.split(",");//每组投注号码（拖码）
            Integer[] bettingNumberArrays = new Integer[towNumber.length+braverySize];
            for(int k=0;k<braverySize;k++){//胆码
                QuizChooseDto braveryNumber = braveryList.get(k);
                bettingNumberArrays[k] = Integer.parseInt(braveryNumber.getIntroduce());
            }
            for(int t=0;t<towNumber.length;t++) {//拖码
                bettingNumberArrays[braverySize+t] = Integer.parseInt(towNumber[t]);
            }
            Arrays.sort(bettingNumberArrays);//排序

            String bettingNumber = "";
            for(int b=0;b<bettingNumberArrays.length;b++){
                if(bl){
                    bettingNumber = bettingNumber+String.format("%02d", bettingNumberArrays[b]);
                }else {
                    bettingNumber = bettingNumber + bettingNumberArrays[b];
                }
                if(b!=bettingNumberArrays.length-1){
                    bettingNumber = bettingNumber+",";
                }
            }
            list.add(bettingNumber);
        }
        //去重集合
        HashSet<String> newBettingNumberHashSet =  new HashSet<>(list);
        List<BettingNumberGroupVo> bettingNumberGroupVoList = new ArrayList<>();
        boolean b = true;
        for (String bettingNumberStr:newBettingNumberHashSet) {
            BettingNumberGroupVo bettingNumberGroupVo = new BettingNumberGroupVo();
            String[] bettingNumber = bettingNumberStr.split(",");//每组投注号码
            Double[] oddsArrays = new Double[bettingNumber.length];
            Double[] oddsArrays2 = new Double[bettingNumber.length];
            List<BettingNumberVo> numberVoList =  new ArrayList<>();
            for(int i=0;i<bettingNumber.length;i++) {

                for (int j = 0; j < braverySize; j++) {//胆码
                    BettingNumberVo bettingNumberVo = new BettingNumberVo();
                    QuizChooseDto dto = braveryList.get(j);
                    if(bettingNumber[i].equals(dto.getIntroduce())){
                        oddsArrays[i] = dto.getOdds();
                        if(null!=dto.getOdds2()&&dto.getOdds2()>0){
                            oddsArrays2[i] = dto.getOdds2();
                        }else {
                            b = false;
                        }
                        bettingNumberVo.setBettingNumber(bettingNumber[i]);
                        bettingNumberVo.setColor(dto.getColor());
                        numberVoList.add(bettingNumberVo);
                    }
                }
                for (int j = 0; j < towSize; j++) {//拖码
                    QuizChooseDto dto = towList.get(j);
                    BettingNumberVo bettingNumberVo = new BettingNumberVo();
                    if(bettingNumber[i].equals(dto.getIntroduce())){
                        oddsArrays[i] = dto.getOdds();
                        if(null!=dto.getOdds2()&&dto.getOdds2()>0){
                            oddsArrays2[i] = dto.getOdds2();
                        }else {
                            b = false;
                        }
                        bettingNumberVo.setBettingNumber(bettingNumber[i]);
                        bettingNumberVo.setColor(dto.getColor());
                        numberVoList.add(bettingNumberVo);
                    }
                }

            }
            Double oddsMin = this.getMin(oddsArrays);//取最小值（赔率）
            if(b){
                Double oddsMin2 = this.getMin(oddsArrays2);//取最小值（赔率）
                bettingNumberGroupVo.setOddsMin2(oddsMin2);
            }
            bettingNumberGroupVo.setOddsMin(oddsMin);
            bettingNumberGroupVo.setNumberVoList(numberVoList);
            bettingNumberGroupVoList.add(bettingNumberGroupVo);
        }
        return Result.succeed(bettingNumberGroupVoList);
    }

    /**
     * 获取数组中的最小值 (得到最小赔率)
     * @param arr
     * @return
     */
    public Double getMin(Double[] arr) {
        // 假设第一位是最小值
        Double min = arr[0];
        for(int index = 0; index < arr.length; index ++) {
            // 判断数组元素的最小值
            if(arr[index] < min) {
                // 把最小值存储Min变量
                min = arr[index];
            }
        }
        return min;
    }
    /**
     * 生肖对碰
     * @param zodiacBumpLotteryBetDto
     * @return
     */
    @Override
    public Result zodiacBumpLotteryBetNumber(ZodiacBumpLotteryBetDto zodiacBumpLotteryBetDto){
        List<NumberAttributes> numberList = numberAttributesService.findList(null,DateUtil.getYear());
        List<BumpBettingNumberDto> bumpBettingNumberDtoList = new ArrayList<>();
        for (NumberAttributes numberAttributes:numberList) {
            if (zodiacBumpLotteryBetDto.getZodiacOne().equals(numberAttributes.getZodiac())) {
                BumpBettingNumberDto bumpBettingNumberDto = new BumpBettingNumberDto();
                bumpBettingNumberDto.setOdds(zodiacBumpLotteryBetDto.getOddsOne());
                bumpBettingNumberDto.setOdds2(zodiacBumpLotteryBetDto.getOddsOne2());
                bumpBettingNumberDto.setBettingNumber(numberAttributes.getNumber());
                bumpBettingNumberDto.setColor(numberAttributes.getColor());
                bumpBettingNumberDtoList.add(bumpBettingNumberDto);
            }
            if (zodiacBumpLotteryBetDto.getZodiacTwo().equals(numberAttributes.getZodiac())) {
                BumpBettingNumberDto bumpBettingNumberDto = new BumpBettingNumberDto();
                bumpBettingNumberDto.setOdds(zodiacBumpLotteryBetDto.getOddsTwo());
                bumpBettingNumberDto.setOdds2(zodiacBumpLotteryBetDto.getOddsTwo2());
                bumpBettingNumberDto.setBettingNumber(numberAttributes.getNumber());
                bumpBettingNumberDto.setColor(numberAttributes.getColor());
                bumpBettingNumberDtoList.add(bumpBettingNumberDto);
            }
        }

        HashSet<String> bettingNumberHashSet = new HashSet<>();
        String[] numberStr = new String[bumpBettingNumberDtoList.size()];
        for (int i = 0; i< bumpBettingNumberDtoList.size(); i++){
            BumpBettingNumberDto dto = bumpBettingNumberDtoList.get(i);
            numberStr[i] = dto.getBettingNumber();
        }
        //所投注的每三个号码为一组合，若三个号码都是开奖号码之正码，视为中奖，其余行情视为不中奖
        //所投注的每三个号码为一组合，若其中2个号码都是开奖号码之正码，视为三中二奖，若3个都是开奖号码中的正码，即为三中二之中三，其余行情视为不中奖
        if("三全中".equals(zodiacBumpLotteryBetDto.getQuizTitle())
                ||"三中二".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<3){
                return Result.failed("选择投注号码必须大于等于3个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,3,true);
        }

        //所投注的每二个号码为一组合，若二个号码都是开奖号码之正码，视为中奖，其余行情视为不中奖（含一个正码加一个特码情形）
        //所投注的每二个号码为一组合，若二个号码都是开奖号码之正码，叫二中特之中二，其中一个是正码，一个是特码，视为中奖,其余行情视为不中奖二中特之中二赔率高于二中特之中特的赔率
        //所投注的每两个号码为一组合，其中一个是正码，一个是特码，视为中奖，其余情形视为不中奖（含二个都是正码之情形）
        if("二全中".equals(zodiacBumpLotteryBetDto.getQuizTitle())
                ||"二中特".equals(zodiacBumpLotteryBetDto.getQuizTitle())
                ||"特串".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<2){
                return Result.failed("选择投注号码必须大于等于2个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,2,true);
        }
        //所投注号码每四个为一组，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("四中一".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<4){
                return Result.failed("选择投注号码必须大于等于4个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,4,true);
        }
        //选择2-4个尾数为一投注组合进行投注。该注的2-4个尾数必须在当期开出的7个开奖号码相对应的尾数中，（49亦算输赢，不为和）。每个号码都有自己的赔率，下注组合的总赔率，取该组合码的最低赔率为下单赔率
        if("二尾连中".equals(zodiacBumpLotteryBetDto.getQuizTitle())
                ||"二尾连不中".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<2){
                return Result.failed("选择投注号码必须大于等于2个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,2,false);
        }
        if("三尾连中".equals(zodiacBumpLotteryBetDto.getQuizTitle())
                ||"三尾连不中".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<3){
                return Result.failed("选择投注号码必须大于等于3个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,3,false);
        }
        if("四尾连中".equals(zodiacBumpLotteryBetDto.getQuizTitle())
                ||"四尾连不中".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<4){
                return Result.failed("选择投注号码必须大于等于4个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,4,false);
        }
        //挑选5个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("五选中一".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<5){
                return Result.failed("选择投注号码必须大于等于5个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,5,true);
        }
        //挑选6个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("六选中一".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<6){
                return Result.failed("选择投注号码必须大于等于6个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,6,true);
        }
        //挑选7个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("七选中一".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<7){
                return Result.failed("选择投注号码必须大于等于7个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,7,true);
        }
        //挑选8个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("八选中一".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<8){
                return Result.failed("选择投注号码必须大于等于8个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,8,true);
        }
        //挑选9个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("九选中一".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<9){
                return Result.failed("选择投注号码必须大于等于9个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,9,true);
        }
        //挑选10个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("十选中一".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<10){
                return Result.failed("选择投注号码必须大于等于10个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,10,true);
        }
        if("五不中".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<5){
                return Result.failed("选择投注号码必须大于等于5个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,5,true);
        }
        if("六不中".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<6){
                return Result.failed("选择投注号码必须大于等于6个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,6,true);
        }
        if("七不中".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<7){
                return Result.failed("选择投注号码必须大于等于7个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,7,true);
        }
        if("八不中".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<8){
                return Result.failed("选择投注号码必须大于等于2个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,8,true);
        }
        if("九不中".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<9){
                return Result.failed("选择投注号码必须大于等于9个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,9,true);
        }
        if("十不中".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<10){
                return Result.failed("选择投注号码必须大于等于10个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,10,true);
        }
        if("十一不中".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<11){
                return Result.failed("选择投注号码必须大于等于11个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,11,true);
        }
        //挑选1个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("一粒任中".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            bettingNumberHashSet = new HashSet();
            for (String str:numberStr){
                bettingNumberHashSet.add(str);
            }
        }
        //挑选2个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("二粒任中".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<2){
                return Result.failed("选择投注号码必须大于等于2个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,2,true);
        }
        //挑选3个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("三粒任中".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<3){
                return Result.failed("选择投注号码必须大于等于3个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,3,true);
        }
        //挑选4个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("四粒任中".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<4){
                return Result.failed("选择投注号码必须大于等于4个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,4,true);
        }
        //挑选5个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("五粒任中".equals(zodiacBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<5){
                return Result.failed("选择投注号码必须大于等于5个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,5,true);
        }
        List<BettingNumberGroupVo> bettingNumberGroupVoList = new ArrayList<>();
        boolean b = true;
        for (String bettingNumberStr:bettingNumberHashSet) {
            BettingNumberGroupVo bettingNumberGroupVo = new BettingNumberGroupVo();
            String[] bettingNumber = bettingNumberStr.split(",");//每组投注号码
            Double[] oddsArrays = new Double[bettingNumber.length];
            Double[] oddsArrays2 = new Double[bettingNumber.length];
            List<BettingNumberVo> numberVoList =  new ArrayList<>();
            for(int i=0;i<bettingNumber.length;i++) {
                BettingNumberVo bettingNumberVo = new BettingNumberVo();
                for (int j = 0; j < bumpBettingNumberDtoList.size(); j++) {
                    BumpBettingNumberDto dto = bumpBettingNumberDtoList.get(j);
                    if(bettingNumber[i].equals(dto.getBettingNumber())){
                        oddsArrays[i] = dto.getOdds();
                        if(null!=dto.getOdds2()&&dto.getOdds2()>0){
                            oddsArrays2[i] = dto.getOdds2();
                        }else {
                            b = false;
                        }
                        bettingNumberVo.setBettingNumber(bettingNumber[i]);
                        bettingNumberVo.setColor(dto.getColor());
                    }
                }
                numberVoList.add(bettingNumberVo);
            }
            Double oddsMin = this.getMin(oddsArrays);//取最小值（赔率）
            bettingNumberGroupVo.setOddsMin(oddsMin);
            if(b){
                Double oddsMin2 = this.getMin(oddsArrays2);//取最小值（赔率）
                bettingNumberGroupVo.setOddsMin2(oddsMin2);
            }
            bettingNumberGroupVo.setNumberVoList(numberVoList);
            bettingNumberGroupVoList.add(bettingNumberGroupVo);
        }
        return Result.succeed(bettingNumberGroupVoList);
    }
    /**
     * 尾数对碰
     * @param tailBumpLotteryBetDto
     * @return
     */
    @Override
    public Result tailBumpLotteryBetNumber(TailBumpLotteryBetDto tailBumpLotteryBetDto){
        List<NumberAttributes> numberList = numberAttributesService.findList(null,DateUtil.getYear());
        List<BumpBettingNumberDto> bumpBettingNumberDtoList = new ArrayList<>();
        for (NumberAttributes numberAttributes:numberList) {
            if (tailBumpLotteryBetDto.getTailOne()==Integer.parseInt(numberAttributes.getNumber())%10) {
                BumpBettingNumberDto bumpBettingNumberDto = new BumpBettingNumberDto();
                bumpBettingNumberDto.setOdds(tailBumpLotteryBetDto.getOddsOne());
                bumpBettingNumberDto.setOdds2(tailBumpLotteryBetDto.getOddsOne2());
                bumpBettingNumberDto.setBettingNumber(numberAttributes.getNumber());
                bumpBettingNumberDto.setColor(numberAttributes.getColor());
                bumpBettingNumberDtoList.add(bumpBettingNumberDto);
            }
            if (tailBumpLotteryBetDto.getTailTwo()==Integer.parseInt(numberAttributes.getNumber())%10) {
                BumpBettingNumberDto bumpBettingNumberDto = new BumpBettingNumberDto();
                bumpBettingNumberDto.setOdds(tailBumpLotteryBetDto.getOddsTwo());
                bumpBettingNumberDto.setOdds2(tailBumpLotteryBetDto.getOddsTwo2());
                bumpBettingNumberDto.setBettingNumber(numberAttributes.getNumber());
                bumpBettingNumberDto.setColor(numberAttributes.getColor());
                bumpBettingNumberDtoList.add(bumpBettingNumberDto);
            }
        }

        HashSet<String> bettingNumberHashSet = new HashSet<>();
        String[] numberStr = new String[bumpBettingNumberDtoList.size()];
        for (int i = 0; i< bumpBettingNumberDtoList.size(); i++){
            BumpBettingNumberDto dto = bumpBettingNumberDtoList.get(i);
            numberStr[i] = dto.getBettingNumber();
        }
        //所投注的每三个号码为一组合，若三个号码都是开奖号码之正码，视为中奖，其余行情视为不中奖
        //所投注的每三个号码为一组合，若其中2个号码都是开奖号码之正码，视为三中二奖，若3个都是开奖号码中的正码，即为三中二之中三，其余行情视为不中奖
        if("三全中".equals(tailBumpLotteryBetDto.getQuizTitle())
                ||"三中二".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<3){
                return Result.failed("选择投注号码必须大于等于3个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,3,true);
        }

        //所投注的每二个号码为一组合，若二个号码都是开奖号码之正码，视为中奖，其余行情视为不中奖（含一个正码加一个特码情形）
        //所投注的每二个号码为一组合，若二个号码都是开奖号码之正码，叫二中特之中二，其中一个是正码，一个是特码，视为中奖,其余行情视为不中奖二中特之中二赔率高于二中特之中特的赔率
        //所投注的每两个号码为一组合，其中一个是正码，一个是特码，视为中奖，其余情形视为不中奖（含二个都是正码之情形）
        if("二全中".equals(tailBumpLotteryBetDto.getQuizTitle())
                ||"二中特".equals(tailBumpLotteryBetDto.getQuizTitle())
                ||"特串".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<2){
                return Result.failed("选择投注号码必须大于等于2个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,2,true);
        }
        //所投注号码每四个为一组，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("四中一".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<4){
                return Result.failed("选择投注号码必须大于等于4个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,4,true);
        }
        //选择2-4个尾数为一投注组合进行投注。该注的2-4个尾数必须在当期开出的7个开奖号码相对应的尾数中，（49亦算输赢，不为和）。每个号码都有自己的赔率，下注组合的总赔率，取该组合码的最低赔率为下单赔率
        if("二尾连中".equals(tailBumpLotteryBetDto.getQuizTitle())
                ||"二尾连不中".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<2){
                return Result.failed("选择投注号码必须大于等于2个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,2,false);
        }
        if("三尾连中".equals(tailBumpLotteryBetDto.getQuizTitle())
                ||"三尾连不中".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<3){
                return Result.failed("选择投注号码必须大于等于3个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,3,false);
        }
        if("四尾连中".equals(tailBumpLotteryBetDto.getQuizTitle())
                ||"四尾连不中".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<4){
                return Result.failed("选择投注号码必须大于等于4个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,4,false);
        }
        //挑选5个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("五选中一".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<5){
                return Result.failed("选择投注号码必须大于等于5个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,5,true);
        }
        //挑选6个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("六选中一".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<6){
                return Result.failed("选择投注号码必须大于等于6个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,6,true);
        }
        //挑选7个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("七选中一".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<7){
                return Result.failed("选择投注号码必须大于等于7个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,7,true);
        }
        //挑选8个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("八选中一".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<8){
                return Result.failed("选择投注号码必须大于等于8个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,8,true);
        }
        //挑选9个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("九选中一".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<9){
                return Result.failed("选择投注号码必须大于等于9个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,9,true);
        }
        //挑选10个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("十选中一".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<10){
                return Result.failed("选择投注号码必须大于等于10个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,10,true);
        }
        if("五不中".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<5){
                return Result.failed("选择投注号码必须大于等于5个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,5,true);
        }
        if("六不中".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<6){
                return Result.failed("选择投注号码必须大于等于6个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,6,true);
        }
        if("七不中".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<7){
                return Result.failed("选择投注号码必须大于等于7个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,7,true);
        }
        if("八不中".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<8){
                return Result.failed("选择投注号码必须大于等于2个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,8,true);
        }
        if("九不中".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<9){
                return Result.failed("选择投注号码必须大于等于9个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,9,true);
        }
        if("十不中".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<10){
                return Result.failed("选择投注号码必须大于等于10个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,10,true);
        }
        if("十一不中".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<11){
                return Result.failed("选择投注号码必须大于等于11个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,11,true);
        }
        //挑选1个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("一粒任中".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            bettingNumberHashSet = new HashSet();
            for (String str:numberStr){
                bettingNumberHashSet.add(str);
            }
        }
        //挑选2个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("二粒任中".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<2){
                return Result.failed("选择投注号码必须大于等于2个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,2,true);
        }
        //挑选3个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("三粒任中".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<3){
                return Result.failed("选择投注号码必须大于等于3个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,3,true);
        }
        //挑选4个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("四粒任中".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<4){
                return Result.failed("选择投注号码必须大于等于4个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,4,true);
        }
        //挑选5个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("五粒任中".equals(tailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<5){
                return Result.failed("选择投注号码必须大于等于5个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,5,true);
        }
        List<BettingNumberGroupVo> bettingNumberGroupVoList = new ArrayList<>();
        boolean b = true;
        for (String bettingNumberStr:bettingNumberHashSet) {
            BettingNumberGroupVo bettingNumberGroupVo = new BettingNumberGroupVo();
            String[] bettingNumber = bettingNumberStr.split(",");//每组投注号码
            Double[] oddsArrays = new Double[bettingNumber.length];
            Double[] oddsArrays2 = new Double[bettingNumber.length];
            List<BettingNumberVo> numberVoList =  new ArrayList<>();
            for(int i=0;i<bettingNumber.length;i++) {
                BettingNumberVo bettingNumberVo = new BettingNumberVo();
                for (int j = 0; j < bumpBettingNumberDtoList.size(); j++) {
                    BumpBettingNumberDto dto = bumpBettingNumberDtoList.get(j);
                    if(bettingNumber[i].equals(dto.getBettingNumber())){
                        oddsArrays[i] = dto.getOdds();
                        if(null!=dto.getOdds2()&&dto.getOdds2()>0){
                            oddsArrays2[i] = dto.getOdds2();
                        }else {
                            b = false;
                        }
                        bettingNumberVo.setBettingNumber(bettingNumber[i]);
                        bettingNumberVo.setColor(dto.getColor());
                    }
                }
                numberVoList.add(bettingNumberVo);
            }
            if(b){
                Double oddsMin2 = this.getMin(oddsArrays2);//取最小值（赔率）
                bettingNumberGroupVo.setOddsMin2(oddsMin2);
            }
            Double oddsMin = this.getMin(oddsArrays);//取最小值（赔率）
            bettingNumberGroupVo.setOddsMin(oddsMin);
            bettingNumberGroupVo.setNumberVoList(numberVoList);
            bettingNumberGroupVoList.add(bettingNumberGroupVo);
        }
        return Result.succeed(bettingNumberGroupVoList);
    }
    /**
     * 生尾对碰
     * @param zodiacTailBumpLotteryBetDto
     * @return
     */
    @Override
    public Result ZodiacTailBumpLotteryBetNumber(ZodiacTailBumpLotteryBetDto zodiacTailBumpLotteryBetDto){
        List<NumberAttributes> numberList = numberAttributesService.findList(null, DateUtil.getYear());
        List<BumpBettingNumberDto> bumpBettingNumberDtoList = new ArrayList<>();
        for (NumberAttributes numberAttributes:numberList) {
            if (zodiacTailBumpLotteryBetDto.getZodiacOne().equals(numberAttributes.getZodiac())) {
                BumpBettingNumberDto bumpBettingNumberDto = new BumpBettingNumberDto();
                bumpBettingNumberDto.setOdds(zodiacTailBumpLotteryBetDto.getOddsOne());
                bumpBettingNumberDto.setOdds2(zodiacTailBumpLotteryBetDto.getOddsOne2());
                bumpBettingNumberDto.setBettingNumber(numberAttributes.getNumber());
                bumpBettingNumberDto.setColor(numberAttributes.getColor());
                bumpBettingNumberDtoList.add(bumpBettingNumberDto);
            }
            if (zodiacTailBumpLotteryBetDto.getTailTwo()==Integer.parseInt(numberAttributes.getNumber())%10) {
                BumpBettingNumberDto bumpBettingNumberDto = new BumpBettingNumberDto();
                bumpBettingNumberDto.setOdds(zodiacTailBumpLotteryBetDto.getOddsTwo());
                bumpBettingNumberDto.setOdds2(zodiacTailBumpLotteryBetDto.getOddsTwo2());
                bumpBettingNumberDto.setBettingNumber(numberAttributes.getNumber());
                bumpBettingNumberDto.setColor(numberAttributes.getColor());
                bumpBettingNumberDtoList.add(bumpBettingNumberDto);
            }
        }

        HashSet<String> bettingNumberHashSet = new HashSet<>();
        String[] numberStr = new String[bumpBettingNumberDtoList.size()];
        for (int i = 0; i< bumpBettingNumberDtoList.size(); i++){
            BumpBettingNumberDto dto = bumpBettingNumberDtoList.get(i);
            numberStr[i] = dto.getBettingNumber();
        }
        //所投注的每三个号码为一组合，若三个号码都是开奖号码之正码，视为中奖，其余行情视为不中奖
        //所投注的每三个号码为一组合，若其中2个号码都是开奖号码之正码，视为三中二奖，若3个都是开奖号码中的正码，即为三中二之中三，其余行情视为不中奖
        if("三全中".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())
                ||"三中二".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<3){
                return Result.failed("选择投注号码必须大于等于3个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,3,true);
        }

        //所投注的每二个号码为一组合，若二个号码都是开奖号码之正码，视为中奖，其余行情视为不中奖（含一个正码加一个特码情形）
        //所投注的每二个号码为一组合，若二个号码都是开奖号码之正码，叫二中特之中二，其中一个是正码，一个是特码，视为中奖,其余行情视为不中奖二中特之中二赔率高于二中特之中特的赔率
        //所投注的每两个号码为一组合，其中一个是正码，一个是特码，视为中奖，其余情形视为不中奖（含二个都是正码之情形）
        if("二全中".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())
                ||"二中特".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())
                ||"特串".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<2){
                return Result.failed("选择投注号码必须大于等于2个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,2,true);
        }
        //所投注号码每四个为一组，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("四中一".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<4){
                return Result.failed("选择投注号码必须大于等于4个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,4,true);
        }
        //选择2-4个尾数为一投注组合进行投注。该注的2-4个尾数必须在当期开出的7个开奖号码相对应的尾数中，（49亦算输赢，不为和）。每个号码都有自己的赔率，下注组合的总赔率，取该组合码的最低赔率为下单赔率
        if("二尾连中".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())
                ||"二尾连不中".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<2){
                return Result.failed("选择投注号码必须大于等于2个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,2,false);
        }
        if("三尾连中".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())
                ||"三尾连不中".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<3){
                return Result.failed("选择投注号码必须大于等于3个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,3,false);
        }
        if("四尾连中".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())
                ||"四尾连不中".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<4){
                return Result.failed("选择投注号码必须大于等于4个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,4,false);
        }
        //挑选5个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("五选中一".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<5){
                return Result.failed("选择投注号码必须大于等于5个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,5,true);
        }
        //挑选6个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("六选中一".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<6){
                return Result.failed("选择投注号码必须大于等于6个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,6,true);
        }
        //挑选7个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("七选中一".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<7){
                return Result.failed("选择投注号码必须大于等于7个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,7,true);
        }
        //挑选8个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("八选中一".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<8){
                return Result.failed("选择投注号码必须大于等于8个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,8,true);
        }
        //挑选9个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("九选中一".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<9){
                return Result.failed("选择投注号码必须大于等于9个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,9,true);
        }
        //挑选10个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("十选中一".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<10){
                return Result.failed("选择投注号码必须大于等于10个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,10,true);
        }
        if("五不中".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<5){
                return Result.failed("选择投注号码必须大于等于5个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,5,true);
        }
        if("六不中".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<6){
                return Result.failed("选择投注号码必须大于等于6个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,6,true);
        }
        if("七不中".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<7){
                return Result.failed("选择投注号码必须大于等于7个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,7,true);
        }
        if("八不中".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<8){
                return Result.failed("选择投注号码必须大于等于2个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,8,true);
        }
        if("九不中".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<9){
                return Result.failed("选择投注号码必须大于等于9个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,9,true);
        }
        if("十不中".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<10){
                return Result.failed("选择投注号码必须大于等于10个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,10,true);
        }
        if("十一不中".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<11){
                return Result.failed("选择投注号码必须大于等于11个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,11,true);
        }
        //挑选1个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("一粒任中".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            bettingNumberHashSet = new HashSet();
            for (String str:numberStr){
                bettingNumberHashSet.add(str);
            }
        }
        //挑选2个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("二粒任中".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<2){
                return Result.failed("选择投注号码必须大于等于2个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,2,true);
        }
        //挑选3个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("三粒任中".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<3){
                return Result.failed("选择投注号码必须大于等于3个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,3,true);
        }
        //挑选4个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("四粒任中".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<4){
                return Result.failed("选择投注号码必须大于等于4个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,4,true);
        }
        //挑选5个号码为一投注组合进行下注，当期开出的7个号码有任何1个号码在该注组合中，即视为中奖，其余情形视为不中奖
        if("五粒任中".equals(zodiacTailBumpLotteryBetDto.getQuizTitle())) {//分类二类
            if(bumpBettingNumberDtoList.size()<5){
                return Result.failed("选择投注号码必须大于等于5个投注号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,5,true);
        }
        List<BettingNumberGroupVo> bettingNumberGroupVoList = new ArrayList<>();
        boolean b = true;
        for (String bettingNumberStr:bettingNumberHashSet) {
            BettingNumberGroupVo bettingNumberGroupVo = new BettingNumberGroupVo();
            String[] bettingNumber = bettingNumberStr.split(",");//每组投注号码
            Double[] oddsArrays = new Double[bettingNumber.length];
            Double[] oddsArrays2 = new Double[bettingNumber.length];
            List<BettingNumberVo> numberVoList =  new ArrayList<>();
            for(int i=0;i<bettingNumber.length;i++) {
                BettingNumberVo bettingNumberVo = new BettingNumberVo();
                for (int j = 0; j < bumpBettingNumberDtoList.size(); j++) {
                    BumpBettingNumberDto dto = bumpBettingNumberDtoList.get(j);
                    if(bettingNumber[i].equals(dto.getBettingNumber())){
                        oddsArrays[i] = dto.getOdds();
                        if(null!=dto.getOdds2()&&dto.getOdds2()>0){
                            oddsArrays2[i] = dto.getOdds2();
                        }else {
                            b = false;
                        }
                        bettingNumberVo.setBettingNumber(bettingNumber[i]);
                        bettingNumberVo.setColor(dto.getColor());
                    }
                }
                numberVoList.add(bettingNumberVo);
            }
            if(b){
                Double oddsMin2 = this.getMin(oddsArrays2);//取最小值（赔率）
                bettingNumberGroupVo.setOddsMin2(oddsMin2);
            }
            Double oddsMin = this.getMin(oddsArrays);//取最小值（赔率）
            bettingNumberGroupVo.setOddsMin(oddsMin);
            bettingNumberGroupVo.setNumberVoList(numberVoList);
            bettingNumberGroupVoList.add(bettingNumberGroupVo);
        }
        return Result.succeed(bettingNumberGroupVoList);
    }
}
