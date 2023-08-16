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
                ||"三中二".equals(duplexLotteryBetDto.getQuizTitle())
                ||"三肖".equals(duplexLotteryBetDto.getQuizTitle())
                ||"三肖连中".equals(duplexLotteryBetDto.getQuizTitle())
                ||"三肖连不中".equals(duplexLotteryBetDto.getQuizTitle())
                ||"三尾连中".equals(duplexLotteryBetDto.getQuizTitle())
                ||"三尾连不中".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<3||quizChooseDtoList.size()>10){
                return Result.failed("选择投注号码必须大于等于3个投注号码，最多可以选择10个号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr, 3);
        }

        //所投注的每二个号码为一组合，若二个号码都是开奖号码之正码，视为中奖，其余行情视为不中奖（含一个正码加一个特码情形）
        //所投注的每二个号码为一组合，若二个号码都是开奖号码之正码，叫二中特之中二，其中一个是正码，一个是特码，视为中奖,其余行情视为不中奖二中特之中二赔率高于二中特之中特的赔率
        //所投注的每两个号码为一组合，其中一个是正码，一个是特码，视为中奖，其余情形视为不中奖（含二个都是正码之情形）
        if("二全中".equals(duplexLotteryBetDto.getQuizTitle())
                ||"二中特".equals(duplexLotteryBetDto.getQuizTitle())
                ||"特串".equals(duplexLotteryBetDto.getQuizTitle())
                ||"二肖".equals(duplexLotteryBetDto.getQuizDetailsTitle())
                ||"二肖连中".equals(duplexLotteryBetDto.getQuizDetailsTitle())
                ||"二肖连不中".equals(duplexLotteryBetDto.getQuizDetailsTitle())
                ||"二尾连中".equals(duplexLotteryBetDto.getQuizTitle())
                ||"二尾连不中".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<2||quizChooseDtoList.size()>10){
                return Result.failed("选择投注号码必须大于等于2个投注号码，最多可以选择10个号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,2);
        }
        //所投注号码每四个为一组，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("四全中".equals(duplexLotteryBetDto.getQuizTitle())
                ||"四肖".equals(duplexLotteryBetDto.getQuizDetailsTitle())
                ||"四肖连中".equals(duplexLotteryBetDto.getQuizDetailsTitle())
                ||"四肖连不中".equals(duplexLotteryBetDto.getQuizDetailsTitle())
                ||"四尾连中".equals(duplexLotteryBetDto.getQuizTitle())
                ||"四尾连不中".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<4||quizChooseDtoList.size()>10){
                return Result.failed("选择投注号码必须大于等于4个投注号码，最多可以选择10个号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,4);
        }
        //挑选5个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("五选中一".equals(duplexLotteryBetDto.getQuizTitle())
                ||"五不中".equals(duplexLotteryBetDto.getQuizTitle())
                ||"五肖".equals(duplexLotteryBetDto.getQuizDetailsTitle())
                ||"五肖连中".equals(duplexLotteryBetDto.getQuizDetailsTitle())) {//分类二类
            if(quizChooseDtoList.size()<5||quizChooseDtoList.size()>10){
                return Result.failed("选择投注号码必须大于等于5个投注号码，最多可以选择10个号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,5);
        }
        //挑选6个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("六选中一".equals(duplexLotteryBetDto.getQuizTitle())
                ||"六不中".equals(duplexLotteryBetDto.getQuizTitle())
                ||"六肖".equals(duplexLotteryBetDto.getQuizDetailsTitle())
                ||"六肖连中".equals(duplexLotteryBetDto.getQuizDetailsTitle())) {//分类二类
            if(quizChooseDtoList.size()<6||quizChooseDtoList.size()>10){
                return Result.failed("选择投注号码必须大于等于6个投注号码，最多可以选择10个号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,6);
        }
        //挑选7个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("七选中一".equals(duplexLotteryBetDto.getQuizTitle())
                ||"七不中".equals(duplexLotteryBetDto.getQuizTitle())
                ||"七肖".equals(duplexLotteryBetDto.getQuizDetailsTitle())) {//分类二类
            if(quizChooseDtoList.size()<7||quizChooseDtoList.size()>10){
                return Result.failed("选择投注号码必须大于等于7个投注号码，最多可以选择10个号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,7);
        }
        //挑选8个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("八选中一".equals(duplexLotteryBetDto.getQuizTitle())
                ||"八不中".equals(duplexLotteryBetDto.getQuizTitle())
                ||"八肖".equals(duplexLotteryBetDto.getQuizDetailsTitle())) {//分类二类
            if(quizChooseDtoList.size()<8||quizChooseDtoList.size()>11){
                return Result.failed("选择投注号码必须大于等于8个投注号码，最多可以选择11个号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,8);
        }
        //挑选9个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("九选中一".equals(duplexLotteryBetDto.getQuizTitle())
                ||"九不中".equals(duplexLotteryBetDto.getQuizTitle())
                ||"九肖".equals(duplexLotteryBetDto.getQuizDetailsTitle())) {//分类二类
            if(quizChooseDtoList.size()<9||quizChooseDtoList.size()>12){
                return Result.failed("选择投注号码必须大于等于9个投注号码，最多可以选择12个号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,9);
        }
        //挑选10个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("十选中一".equals(duplexLotteryBetDto.getQuizTitle())
                ||"十不中".equals(duplexLotteryBetDto.getQuizTitle())
                ||"十肖".equals(duplexLotteryBetDto.getQuizDetailsTitle())) {//分类二类
            if(quizChooseDtoList.size()<10||quizChooseDtoList.size()>13){
                return Result.failed("选择投注号码必须大于等于10个投注号码，最多可以选择13个号码");
            }
            if("十肖".equals(duplexLotteryBetDto.getQuizDetailsTitle())){
                if(quizChooseDtoList.size()>12){
                    return Result.failed("选择投注号码必须大于等于10个投注号码，最多可以选择12个号码");
                }
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,10);
        }
        if("十一不中".equals(duplexLotteryBetDto.getQuizTitle())
                ||"十一肖".equals(duplexLotteryBetDto.getQuizDetailsTitle())) {//分类二类
            if(quizChooseDtoList.size()<11||quizChooseDtoList.size()>13){
                return Result.failed("选择投注号码必须大于等于11个投注号码，最多可以选择13个号码");
            }
            if("十一肖".equals(duplexLotteryBetDto.getQuizDetailsTitle())){
                if(quizChooseDtoList.size()>12){
                    return Result.failed("选择投注号码必须大于等于11个投注号码，最多可以选择12个号码");
                }
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,11);
        }
        if("十二不中".equals(duplexLotteryBetDto.getQuizTitle())) {//分类二类
            if(quizChooseDtoList.size()<12||quizChooseDtoList.size()>14){
                return Result.failed("选择投注号码必须大于等于12个投注号码，最多可以选择14个号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,12);
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
    public HashSet<String> doubleNumber(List<BumpBettingNumberDto> bumpBettingNumberDtoList1,List<BumpBettingNumberDto> bumpBettingNumberDtoList2){
        List<String> list = new ArrayList<>();
        for(int i=0;i<bumpBettingNumberDtoList1.size();i++) {
            BumpBettingNumberDto bumpBettingNumberDto1 = bumpBettingNumberDtoList1.get(i);
            for (int j = 0; j < bumpBettingNumberDtoList2.size(); j++) {
                BumpBettingNumberDto bumpBettingNumberDto2 = bumpBettingNumberDtoList2.get(j);
                if(!bumpBettingNumberDto1.getBettingNumber().equals(bumpBettingNumberDto2.getBettingNumber())) {
                    Integer[] str1 = {Integer.parseInt(bumpBettingNumberDto1.getBettingNumber()), Integer.parseInt(bumpBettingNumberDto2.getBettingNumber())};
                    Arrays.sort(str1);
                    list.add(String.format("%02d", str1[0]) + "," + String.format("%02d", str1[1]));
                }
            }
        }
        //去重集合
        return new HashSet<>(list);
    }
    /**
     * 复式投注
     * @param str 选择号码
     * @param length 每注彩票组合个数
     * @return
     */
    public HashSet<String> duplexNumber(String[] str , int length){
        List<String> list = new ArrayList<>();
        for(int i=0;i<str.length;i++) {
            if (length == 1) {
                list.add(str[i]);
            }else {
                for (int j = i+1; j < str.length; j++) {
                    if (length == 2) {
                        if (i != j) {
                            String[] str1 = {str[i], str[j]};
                            Arrays.sort(str1);
                            list.add(str1[0] + "," + str1[1]);
                        }
                    } else {
                        for (int k = j+1; k < str.length; k++) {

                            if (length == 3) {
                                if (k != i && k != j) {
                                    String[] str1 = {str[i], str[j], str[k]};
                                    Arrays.sort(str1);
                                    list.add(str1[0] + "," + str1[1] + "," + str1[2]);
                                }
                            } else {
                                for (int m = k+1; m < str.length; m++) {
                                    if (length == 4) {
                                        if (m != i && m != k && m != j) {
                                            String[] str1 = {str[i], str[j], str[k], str[m]};
                                            Arrays.sort(str1);
                                            list.add(str1[0] + "," + str1[1] + "," + str1[2] + "," + str1[3]);
                                        }
                                    } else {
                                        for (int n = m+1; n < str.length; n++) {
                                            if (length == 5) {
                                                if (n != i && n != j && n != k && n != m) {
                                                    String[] str1 = {str[i], str[j], str[k], str[m], str[n]};
                                                    Arrays.sort(str1);
                                                    list.add(str1[0] + "," + str1[1] + "," + str1[2] + "," + str1[3] + "," + str1[4]);
                                                }
                                            } else {
                                                for (int o = n+1; o < str.length; o++) {
                                                    if (length == 6) {
                                                        if (o != i && o != j && o != k && o != m && o != n) {
                                                            String[] str1 = {str[i], str[j], str[k], str[m], str[n], str[o]};
                                                            Arrays.sort(str1);
                                                            list.add(str1[0] + "," + str1[1] + "," + str1[2] + "," + str1[3] + "," + str1[4] + "," + str1[5]);
                                                        }
                                                    } else {
                                                        for (int p = o+1; p < str.length; p++) {
                                                            if (length == 7) {
                                                                if (p != i && p != j && p != k && p != m && p != n && p != o) {
                                                                    String[] str1 = {str[i], str[j], str[k], str[m], str[n], str[o], str[p]};
                                                                    Arrays.sort(str1);
                                                                    list.add(str1[0] + "," + str1[1] + "," + str1[2] + "," + str1[3] + "," + str1[4] + "," + str1[5] + "," + str1[6]);
                                                                }
                                                            } else {
                                                                for (int q = p+1; q < str.length; q++) {
                                                                    if (length == 8) {
                                                                        if (q != i && q != j && q != k && q != m && q != n && q != o && q != p) {
                                                                            String[] str1 = {str[i], str[j], str[k], str[m], str[n], str[o], str[p], str[q]};
                                                                            Arrays.sort(str1);
                                                                            list.add(str1[0] + "," + str1[1] + "," + str1[2] + "," + str1[3] + "," + str1[4] + "," + str1[5] + "," + str1[6] + "," + str1[7]);
                                                                        }
                                                                    } else {
                                                                        for (int r = q+1; r < str.length; r++) {
                                                                            if (length == 9) {
                                                                                if (r != i && r != j && r != k && r != m && r != n && r != o && r != p && r != q) {
                                                                                    String[] str1 = {str[i], str[j], str[k], str[m], str[n], str[o], str[p], str[q], str[r]};
                                                                                    Arrays.sort(str1);
                                                                                    list.add(str1[0] + "," + str1[1] + "," + str1[2] + "," + str1[3] + "," + str1[4] + "," + str1[5] + "," + str1[6] + "," + str1[7] + "," + str1[8]);
                                                                                }
                                                                            } else {
                                                                                for (int s = r+1; s < str.length; s++) {
                                                                                    if (length == 10) {
                                                                                        if (s != i && s != j && s != k && s != m && s != n && s != o && s != p && s != q && s != r) {
                                                                                            String[] str1 = {str[i], str[j], str[k], str[m], str[n], str[o], str[p], str[q], str[r], str[s]};
                                                                                            Arrays.sort(str1);
                                                                                            list.add(str1[0] + "," + str1[1] + "," + str1[2] + "," + str1[3] + "," + str1[4] + "," + str1[5] + "," + str1[6] + "," + str1[7] + "," + str1[8]+ "," + str1[9]);
                                                                                        }
                                                                                    } else {
                                                                                        for (int t = s+1; t < str.length; t++) {
                                                                                            if (length == 11) {
                                                                                                if (t != i && t != j && t != k && t != m && t != n && t != o && t != p && t != q && t != r && t != s) {
                                                                                                    String[] str1 = {str[i], str[j], str[k], str[m], str[n], str[o], str[p], str[q], str[r], str[s], str[t]};
                                                                                                    Arrays.sort(str1);
                                                                                                    list.add(str1[0] + "," + str1[1] + "," + str1[2] + "," + str1[3] + "," + str1[4] + "," + str1[5] + "," + str1[6] + "," + str1[7] + "," + str1[8]+ "," + str1[9]+ "," + str1[10]);
                                                                                                }
                                                                                            } else {
                                                                                                for (int u = t+1; u < str.length; u++) {
                                                                                                    if (length == 12) {
                                                                                                        if (u != i && u != j && u != k && u != m && u != n && u != o && u != p && u != q && u != r && u != s && u != t) {
                                                                                                            String[] str1 = {str[i], str[j], str[k], str[m], str[n], str[o], str[p], str[q], str[r], str[s], str[t], str[u]};
                                                                                                            Arrays.sort(str1);
                                                                                                            list.add(str1[0] + "," + str1[1] + "," + str1[2] + "," + str1[3] + "," + str1[4] + "," + str1[5] + "," + str1[6] + "," + str1[7] + "," + str1[8]+ "," + str1[9]+ "," + str1[10]+ "," + str1[11]);
                                                                                                        }
                                                                                                    } else {
                                                                                                        for (int v = u+1; v < str.length; v++) {
                                                                                                            if (length == 13) {
                                                                                                                if (v != i && v != j && v != k && v != m && v != n && v != o && v != p && v != q && v != r && v != s && v != t && v != u) {
                                                                                                                    String[] str1 = {str[i], str[j], str[k], str[m], str[n], str[o], str[p], str[q], str[r], str[s], str[t], str[u], str[v]};
                                                                                                                    Arrays.sort(str1);
                                                                                                                    list.add(str1[0] + "," + str1[1] + "," + str1[2] + "," + str1[3] + "," + str1[4] + "," + str1[5] + "," + str1[6] + "," + str1[7] + "," + str1[8]+ "," + str1[9]+ "," + str1[10]+ "," + str1[11]+ "," + str1[12]);
                                                                                                                }
                                                                                                            } else {
                                                                                                                for (int w = v+1; w < str.length; w++) {
                                                                                                                    if (length == 14) {
                                                                                                                        if (w != i && w != j && w != k && w != m && w != n && w != o && w != p && w != q && w != r && w != s && w != t && w != u && w != v) {
                                                                                                                            String[] str1 = {str[i], str[j], str[k], str[m], str[n], str[o], str[p], str[q], str[r], str[s], str[t], str[u], str[v], str[w]};
                                                                                                                            Arrays.sort(str1);
                                                                                                                            list.add(str1[0] + "," + str1[1] + "," + str1[2] + "," + str1[3] + "," + str1[4] + "," + str1[5] + "," + str1[6] + "," + str1[7] + "," + str1[8]+ "," + str1[9]+ "," + str1[10]+ "," + str1[11]+ "," + str1[12]+ "," + str1[13]);
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        for (int y = w+1; y < str.length; y++) {
                                                                                                                            if (length == 15) {
                                                                                                                                if (y != i && y != j && y != k && y != m && y != n && y != o && y != p && y != q && y != r && y != s && y != t && y != u && y != v && y != w) {
                                                                                                                                    String[] str1 = {str[i], str[j], str[k], str[m], str[n], str[o], str[p], str[q], str[r], str[s], str[t], str[u], str[v], str[w], str[y]};
                                                                                                                                    Arrays.sort(str1);
                                                                                                                                    list.add(str1[0] + "," + str1[1] + "," + str1[2] + "," + str1[3] + "," + str1[4] + "," + str1[5] + "," + str1[6] + "," + str1[7] + "," + str1[8]+ "," + str1[9]+ "," + str1[10]+ "," + str1[11]+ "," + str1[12]+ "," + str1[13]+ "," + str1[14]);
                                                                                                                                }
                                                                                                                            } else {

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
                ||"三中二".equals(braveryTowLotteryBetDto.getQuizTitle())
                ||"三尾连中".equals(braveryTowLotteryBetDto.getQuizTitle())
                ||"三尾连不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=3){
                return Result.failed("选择投注胆码总数必须小于3个投注号码");
            }
            if(braverySize+towSize<3||braverySize+towSize>10){
                return Result.failed("选择投注胆码加拖码总数必须大于等于3个投注号码，最多可以选择10个号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,3-braverySize);
        }

        //所投注的每二个号码为一组合，若二个号码都是开奖号码之正码，视为中奖，其余行情视为不中奖（含一个正码加一个特码情形）
        //所投注的每二个号码为一组合，若二个号码都是开奖号码之正码，叫二中特之中二，其中一个是正码，一个是特码，视为中奖,其余行情视为不中奖二中特之中二赔率高于二中特之中特的赔率
        //所投注的每两个号码为一组合，其中一个是正码，一个是特码，视为中奖，其余情形视为不中奖（含二个都是正码之情形）
        if("二全中".equals(braveryTowLotteryBetDto.getQuizTitle())
                ||"二中特".equals(braveryTowLotteryBetDto.getQuizTitle())
                ||"特串".equals(braveryTowLotteryBetDto.getQuizTitle())
                ||"二尾连中".equals(braveryTowLotteryBetDto.getQuizTitle())
                ||"二尾连不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=2){
                return Result.failed("选择投注胆码总数必须小于2个投注号码");
            }
            if(braverySize+towSize<2||braverySize+towSize>10){
                return Result.failed("选择投注胆码加拖码总数必须大于等于2个投注号码，最多可以选择10个号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,2-braverySize);
        }
        //所投注号码每四个为一组，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("四全中".equals(braveryTowLotteryBetDto.getQuizTitle())
                ||"四尾连中".equals(braveryTowLotteryBetDto.getQuizTitle())
                ||"四尾连不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=4){
                return Result.failed("选择投注胆码总数必须小于4个投注号码");
            }
            if(braverySize+towSize<4||braverySize+towSize>10){
                return Result.failed("选择投注胆码加拖码总数必须大于等于4个投注号码，最多可以选择10个号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,4-braverySize);
        }
        //挑选5个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("五选中一".equals(braveryTowLotteryBetDto.getQuizTitle())
                ||"五不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=5){
                return Result.failed("选择投注胆码总数必须小于5个投注号码");
            }
            if(braverySize+towSize<5||braverySize+towSize>10){
                return Result.failed("选择投注胆码加拖码总数必须大于等于5个投注号码，最多可以选择10个号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,5-braverySize);
        }
        //挑选6个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("六选中一".equals(braveryTowLotteryBetDto.getQuizTitle())
                ||"六不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=6){
                return Result.failed("选择投注胆码总数必须小于6个投注号码");
            }
            if(braverySize+towSize<6||braverySize+towSize>10){
                return Result.failed("选择投注胆码加拖码总数必须大于等于6个投注号码，最多可以选择10个号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,6-braverySize);
        }
        //挑选7个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("七选中一".equals(braveryTowLotteryBetDto.getQuizTitle())
                ||"七不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=7){
                return Result.failed("选择投注胆码总数必须小于7个投注号码");
            }
            if(braverySize+towSize<7||braverySize+towSize>10){
                return Result.failed("选择投注胆码加拖码总数必须大于等于7个投注号码，最多可以选择10个号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,7-braverySize);
        }
        //挑选8个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("八选中一".equals(braveryTowLotteryBetDto.getQuizTitle())
                ||"八不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=8){
                return Result.failed("选择投注胆码总数必须小于8个投注号码");
            }
            if(braverySize+towSize<8||braverySize+towSize>11){
                return Result.failed("选择投注胆码加拖码总数必须大于等于8个投注号码，最多可以选择11个号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,8-braverySize);
        }
        //挑选9个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("九选中一".equals(braveryTowLotteryBetDto.getQuizTitle())
                ||"九不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=9){
                return Result.failed("选择投注胆码总数必须小于9个投注号码");
            }
            if(braverySize+towSize<9||braverySize+towSize>11){
                return Result.failed("选择投注胆码加拖码总数必须大于等于9个投注号码，最多可以选择12个号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,9-braverySize);
        }
        //挑选10个号码为一组进行下注，如果有一个号码在开奖号码的七个号码（正码和特码）里面，视为中奖，其他情形都视为不中奖
        if("十选中一".equals(braveryTowLotteryBetDto.getQuizTitle())
                ||"十不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=10){
                return Result.failed("选择投注胆码总数必须小于10个投注号码");
            }
            if(braverySize+towSize<10||braverySize+towSize>12){
                return Result.failed("选择投注胆码加拖码总数必须大于等于10个投注号码，最多可以选择12个号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,10-braverySize);
        }
        if("十一不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=11){
                return Result.failed("选择投注胆码总数必须小于11个投注号码");
            }
            if(braverySize+towSize<11||braverySize+towSize>13){
                return Result.failed("选择投注胆码加拖码总数必须大于等于11个投注号码，最多可以选择13个号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,11-braverySize);
        }
        if("十二不中".equals(braveryTowLotteryBetDto.getQuizTitle())) {//分类二类
            if(braverySize>=12){
                return Result.failed("选择投注胆码总数必须小于12个投注号码");
            }
            if(braverySize+towSize<12||braverySize+towSize>14){
                return Result.failed("选择投注胆码加拖码总数必须大于等于12个投注号码，最多可以选择14个号码");
            }
            bettingNumberHashSet = this.duplexNumber(numberStr,12-braverySize);
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
        List<BumpBettingNumberDto> bumpBettingNumberDtoList1 = new ArrayList<>();
        List<BumpBettingNumberDto> bumpBettingNumberDtoList2 = new ArrayList<>();
        for (NumberAttributes numberAttributes:numberList) {
            if (zodiacBumpLotteryBetDto.getZodiacOne().equals(numberAttributes.getZodiac())) {
                BumpBettingNumberDto bumpBettingNumberDto = new BumpBettingNumberDto();
                bumpBettingNumberDto.setOdds(zodiacBumpLotteryBetDto.getOddsOne());
                bumpBettingNumberDto.setOdds2(zodiacBumpLotteryBetDto.getOddsOne2());
                bumpBettingNumberDto.setBettingNumber(numberAttributes.getNumber());
                bumpBettingNumberDto.setColor(numberAttributes.getColor());
                bumpBettingNumberDtoList1.add(bumpBettingNumberDto);
                bumpBettingNumberDtoList.add(bumpBettingNumberDto);
            }
            if (zodiacBumpLotteryBetDto.getZodiacTwo().equals(numberAttributes.getZodiac())) {
                BumpBettingNumberDto bumpBettingNumberDto = new BumpBettingNumberDto();
                bumpBettingNumberDto.setOdds(zodiacBumpLotteryBetDto.getOddsTwo());
                bumpBettingNumberDto.setOdds2(zodiacBumpLotteryBetDto.getOddsTwo2());
                bumpBettingNumberDto.setBettingNumber(numberAttributes.getNumber());
                bumpBettingNumberDto.setColor(numberAttributes.getColor());
                bumpBettingNumberDtoList2.add(bumpBettingNumberDto);
                bumpBettingNumberDtoList.add(bumpBettingNumberDto);
            }
        }

        HashSet<String> bettingNumberHashSet = this.doubleNumber(bumpBettingNumberDtoList1,bumpBettingNumberDtoList2);
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
        List<BumpBettingNumberDto> bumpBettingNumberDtoList1 = new ArrayList<>();
        List<BumpBettingNumberDto> bumpBettingNumberDtoList2 = new ArrayList<>();
        for (NumberAttributes numberAttributes:numberList) {
            if (tailBumpLotteryBetDto.getTailOne()==Integer.parseInt(numberAttributes.getNumber())%10) {
                BumpBettingNumberDto bumpBettingNumberDto = new BumpBettingNumberDto();
                bumpBettingNumberDto.setOdds(tailBumpLotteryBetDto.getOddsOne());
                bumpBettingNumberDto.setOdds2(tailBumpLotteryBetDto.getOddsOne2());
                bumpBettingNumberDto.setBettingNumber(numberAttributes.getNumber());
                bumpBettingNumberDto.setColor(numberAttributes.getColor());
                bumpBettingNumberDtoList.add(bumpBettingNumberDto);
                bumpBettingNumberDtoList1.add(bumpBettingNumberDto);
            }
            if (tailBumpLotteryBetDto.getTailTwo()==Integer.parseInt(numberAttributes.getNumber())%10) {
                BumpBettingNumberDto bumpBettingNumberDto = new BumpBettingNumberDto();
                bumpBettingNumberDto.setOdds(tailBumpLotteryBetDto.getOddsTwo());
                bumpBettingNumberDto.setOdds2(tailBumpLotteryBetDto.getOddsTwo2());
                bumpBettingNumberDto.setBettingNumber(numberAttributes.getNumber());
                bumpBettingNumberDto.setColor(numberAttributes.getColor());
                bumpBettingNumberDtoList.add(bumpBettingNumberDto);
                bumpBettingNumberDtoList2.add(bumpBettingNumberDto);
            }
        }

        HashSet<String> bettingNumberHashSet = this.doubleNumber(bumpBettingNumberDtoList1,bumpBettingNumberDtoList2);
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
        List<BumpBettingNumberDto> bumpBettingNumberDtoList1 = new ArrayList<>();
        List<BumpBettingNumberDto> bumpBettingNumberDtoList2 = new ArrayList<>();
        for (NumberAttributes numberAttributes:numberList) {
            if (zodiacTailBumpLotteryBetDto.getZodiacOne().equals(numberAttributes.getZodiac())) {
                BumpBettingNumberDto bumpBettingNumberDto = new BumpBettingNumberDto();
                bumpBettingNumberDto.setOdds(zodiacTailBumpLotteryBetDto.getOddsOne());
                bumpBettingNumberDto.setOdds2(zodiacTailBumpLotteryBetDto.getOddsOne2());
                bumpBettingNumberDto.setBettingNumber(numberAttributes.getNumber());
                bumpBettingNumberDto.setColor(numberAttributes.getColor());
                bumpBettingNumberDtoList.add(bumpBettingNumberDto);
                bumpBettingNumberDtoList1.add(bumpBettingNumberDto);
            }
            if (zodiacTailBumpLotteryBetDto.getTailTwo()==Integer.parseInt(numberAttributes.getNumber())%10) {
                BumpBettingNumberDto bumpBettingNumberDto = new BumpBettingNumberDto();
                bumpBettingNumberDto.setOdds(zodiacTailBumpLotteryBetDto.getOddsTwo());
                bumpBettingNumberDto.setOdds2(zodiacTailBumpLotteryBetDto.getOddsTwo2());
                bumpBettingNumberDto.setBettingNumber(numberAttributes.getNumber());
                bumpBettingNumberDto.setColor(numberAttributes.getColor());
                bumpBettingNumberDtoList.add(bumpBettingNumberDto);
                bumpBettingNumberDtoList2.add(bumpBettingNumberDto);
            }
        }

        HashSet<String> bettingNumberHashSet = this.doubleNumber(bumpBettingNumberDtoList1,bumpBettingNumberDtoList2);
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
