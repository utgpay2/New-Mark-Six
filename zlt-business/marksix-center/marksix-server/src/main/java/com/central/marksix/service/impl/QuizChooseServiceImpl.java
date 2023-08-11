package com.central.marksix.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.RedisConstants;
import com.central.common.model.NumberAttributes;
import com.central.common.model.QuizChoose;
import com.central.common.model.enums.SortEnum;
import com.central.common.model.enums.StatusEnum;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.common.utils.DateUtil;
import com.central.common.dto.NumberAttributesDto;
import com.central.common.vo.QuizChooseVo;
import com.central.marksix.mapper.QuizChooseMapper;
import com.central.marksix.service.INumberAttributesService;
import com.central.marksix.service.IQuizChooseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 竞猜奖项详情
 *
 * @author zlt
 * @date 2023-05-09 18:42:17
 */
@Slf4j
@Service
public class QuizChooseServiceImpl extends SuperServiceImpl<QuizChooseMapper, QuizChoose> implements IQuizChooseService {
    @Autowired
    private INumberAttributesService numberAttributesService;
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public List<QuizChooseVo> findList(Map<String, Object> params){
        if(null == params){
            params = new HashMap<>();
        }
        params.put("status", StatusEnum.ONE_TRUE.getStatus());
        String redisKey = StrUtil.format(RedisConstants.SITE_LOTTERY_CATEGORY_QUIZ_QUIZDETAILS_QUIZCHOOSE_LIST_KEY,
                MapUtils.getInteger(params,"siteId"),
                MapUtils.getInteger(params,"siteLotteryId"),
                MapUtils.getInteger(params,"siteCategoryId"),
                MapUtils.getInteger(params,"quizId"),
                MapUtils.getInteger(params,"quizDetailsId"));
        List<QuizChooseVo> chooseVoList = (List<QuizChooseVo>)RedisRepository.get(redisKey);
        if (ObjectUtil.isEmpty(chooseVoList)) {
            List<QuizChoose> chooseList = baseMapper.findList(params);
            chooseVoList = new ArrayList<>();
            for (QuizChoose quizChoose : chooseList) {
                QuizChooseVo quizChooseVo = new QuizChooseVo();
                BeanUtils.copyProperties(quizChoose, quizChooseVo);
                if ("01".equals(quizChoose.getIntroduce()) ||
                        "02".equals(quizChoose.getIntroduce()) ||
                        "03".equals(quizChoose.getIntroduce()) ||
                        "04".equals(quizChoose.getIntroduce()) ||
                        "05".equals(quizChoose.getIntroduce()) ||
                        "06".equals(quizChoose.getIntroduce()) ||
                        "07".equals(quizChoose.getIntroduce()) ||
                        "08".equals(quizChoose.getIntroduce()) ||
                        "09".equals(quizChoose.getIntroduce()) ||
                        "10".equals(quizChoose.getIntroduce()) ||
                        "11".equals(quizChoose.getIntroduce()) ||
                        "12".equals(quizChoose.getIntroduce()) ||
                        "13".equals(quizChoose.getIntroduce()) ||
                        "14".equals(quizChoose.getIntroduce()) ||
                        "15".equals(quizChoose.getIntroduce()) ||
                        "16".equals(quizChoose.getIntroduce()) ||
                        "17".equals(quizChoose.getIntroduce()) ||
                        "18".equals(quizChoose.getIntroduce()) ||
                        "19".equals(quizChoose.getIntroduce()) ||
                        "20".equals(quizChoose.getIntroduce()) ||
                        "21".equals(quizChoose.getIntroduce()) ||
                        "22".equals(quizChoose.getIntroduce()) ||
                        "23".equals(quizChoose.getIntroduce()) ||
                        "24".equals(quizChoose.getIntroduce()) ||
                        "25".equals(quizChoose.getIntroduce()) ||
                        "26".equals(quizChoose.getIntroduce()) ||
                        "27".equals(quizChoose.getIntroduce()) ||
                        "28".equals(quizChoose.getIntroduce()) ||
                        "29".equals(quizChoose.getIntroduce()) ||
                        "30".equals(quizChoose.getIntroduce()) ||
                        "31".equals(quizChoose.getIntroduce()) ||
                        "32".equals(quizChoose.getIntroduce()) ||
                        "33".equals(quizChoose.getIntroduce()) ||
                        "34".equals(quizChoose.getIntroduce()) ||
                        "35".equals(quizChoose.getIntroduce()) ||
                        "36".equals(quizChoose.getIntroduce()) ||
                        "37".equals(quizChoose.getIntroduce()) ||
                        "38".equals(quizChoose.getIntroduce()) ||
                        "39".equals(quizChoose.getIntroduce()) ||
                        "40".equals(quizChoose.getIntroduce()) ||
                        "41".equals(quizChoose.getIntroduce()) ||
                        "42".equals(quizChoose.getIntroduce()) ||
                        "43".equals(quizChoose.getIntroduce()) ||
                        "44".equals(quizChoose.getIntroduce()) ||
                        "45".equals(quizChoose.getIntroduce()) ||
                        "46".equals(quizChoose.getIntroduce()) ||
                        "47".equals(quizChoose.getIntroduce()) ||
                        "48".equals(quizChoose.getIntroduce()) ||
                        "49".equals(quizChoose.getIntroduce())) {
                    quizChooseVo = this.setQuizChooseVo(quizChooseVo, quizChoose.getIntroduce(), null,"");
                }
                if ("金".equals(quizChoose.getIntroduce()) ||
                        "木".equals(quizChoose.getIntroduce()) ||
                        "水".equals(quizChoose.getIntroduce()) ||
                        "火".equals(quizChoose.getIntroduce()) ||
                        "土".equals(quizChoose.getIntroduce())) {
                    NumberAttributesDto numberAttributesDto = new NumberAttributesDto();
                    numberAttributesDto.setFiveElements(quizChoose.getIntroduce());
                    quizChooseVo = this.setQuizChooseVo(quizChooseVo, "", numberAttributesDto,"");
                }
                if("小".equals(quizChoose.getIntroduce()) ||
                    "大".equals(quizChoose.getIntroduce()) ||
                    "单".equals(quizChoose.getIntroduce()) ||
                    "双".equals(quizChoose.getIntroduce()) ||
                    "合大".equals(quizChoose.getIntroduce()) ||
                    "合小".equals(quizChoose.getIntroduce()) ||
                    "合单".equals(quizChoose.getIntroduce()) ||
                    "合双".equals(quizChoose.getIntroduce()) ||
                    "大双".equals(quizChoose.getIntroduce()) ||
                    "小双".equals(quizChoose.getIntroduce()) ||
                    "大单".equals(quizChoose.getIntroduce()) ||
                    "小单".equals(quizChoose.getIntroduce()) ||
                    "尾大".equals(quizChoose.getIntroduce()) ||
                    "尾小".equals(quizChoose.getIntroduce()) ||
                    "红大".equals(quizChoose.getIntroduce()) ||
                    "红小".equals(quizChoose.getIntroduce()) ||
                    "红单".equals(quizChoose.getIntroduce()) ||
                    "红双".equals(quizChoose.getIntroduce()) ||
                    "红大单".equals(quizChoose.getIntroduce()) ||
                    "红大双".equals(quizChoose.getIntroduce()) ||
                    "红小单".equals(quizChoose.getIntroduce()) ||
                    "红小双".equals(quizChoose.getIntroduce()) ||
                    "红合单".equals(quizChoose.getIntroduce()) ||
                    "红合双".equals(quizChoose.getIntroduce()) ||
                    "红合大".equals(quizChoose.getIntroduce()) ||
                    "红合小".equals(quizChoose.getIntroduce()) ||
                    "绿大".equals(quizChoose.getIntroduce()) ||
                    "绿小".equals(quizChoose.getIntroduce()) ||
                    "绿单".equals(quizChoose.getIntroduce()) ||
                    "绿双".equals(quizChoose.getIntroduce()) ||
                    "绿大单".equals(quizChoose.getIntroduce()) ||
                    "绿大双".equals(quizChoose.getIntroduce()) ||
                    "绿小单".equals(quizChoose.getIntroduce()) ||
                    "绿小双".equals(quizChoose.getIntroduce()) ||
                    "绿合单".equals(quizChoose.getIntroduce()) ||
                    "绿合双".equals(quizChoose.getIntroduce()) ||
                    "绿合大".equals(quizChoose.getIntroduce()) ||
                    "绿合小".equals(quizChoose.getIntroduce()) ||
                    "蓝大".equals(quizChoose.getIntroduce()) ||
                    "蓝小".equals(quizChoose.getIntroduce()) ||
                    "蓝单".equals(quizChoose.getIntroduce()) ||
                    "蓝双".equals(quizChoose.getIntroduce()) ||
                    "蓝大单".equals(quizChoose.getIntroduce()) ||
                    "蓝大双".equals(quizChoose.getIntroduce()) ||
                    "蓝小单".equals(quizChoose.getIntroduce()) ||
                    "蓝小双".equals(quizChoose.getIntroduce()) ||
                    "蓝合单".equals(quizChoose.getIntroduce()) ||
                    "蓝合双".equals(quizChoose.getIntroduce())||
                    "蓝合大".equals(quizChoose.getIntroduce()) ||
                    "蓝合小".equals(quizChoose.getIntroduce())) {
                    quizChooseVo = this.setQuizChooseVo(quizChooseVo, "", null,quizChoose.getIntroduce());
                }
//                if ("家禽".equals(quizChoose.getIntroduce())) {
//                    NumberAttributesDto numberAttributesDto = new NumberAttributesDto();
//                    numberAttributesDto.setPoultryandbeast("家");
//                    quizChooseVo = this.setQuizChooseVo(quizChooseVo, "", numberAttributesDto);
//                }
//                if ("野兽".equals(quizChoose.getIntroduce())) {
//                    NumberAttributesDto numberAttributesDto = new NumberAttributesDto();
//                    numberAttributesDto.setPoultryandbeast("野");
//                    quizChooseVo = this.setQuizChooseVo(quizChooseVo, "", numberAttributesDto);
//                }
                if ("鼠".equals(quizChoose.getIntroduce()) ||
                        "牛".equals(quizChoose.getIntroduce()) ||
                        "虎".equals(quizChoose.getIntroduce()) ||
                        "兔".equals(quizChoose.getIntroduce()) ||
                        "龙".equals(quizChoose.getIntroduce()) ||
                        "蛇".equals(quizChoose.getIntroduce()) ||
                        "马".equals(quizChoose.getIntroduce()) ||
                        "羊".equals(quizChoose.getIntroduce()) ||
                        "猴".equals(quizChoose.getIntroduce()) ||
                        "鸡".equals(quizChoose.getIntroduce()) ||
                        "狗".equals(quizChoose.getIntroduce()) ||
                        "猪".equals(quizChoose.getIntroduce())) {
                    NumberAttributesDto numberAttributesDto = new NumberAttributesDto();
                    numberAttributesDto.setZodiac(quizChoose.getIntroduce());
                    quizChooseVo = this.setQuizChooseVo(quizChooseVo, "", numberAttributesDto,"");
                }
                chooseVoList.add(quizChooseVo);
            }
            RedisRepository.setExpire(redisKey, chooseVoList, RedisConstants.EXPIRE_TIME_30_DAYS);
        }
        Comparator<QuizChooseVo> comparator;
        if(ObjectUtil.isEmpty(params.get("sortBy"))||SortEnum.DESC.getCode() != MapUtils.getInteger(params,"sortBy")){
            comparator = Comparator.comparing(QuizChooseVo::getSort);//正序
        }else {
            comparator = Comparator.comparing(QuizChooseVo::getSort).reversed();//倒序
        }
        return chooseVoList.stream().filter(quizChooseVo -> StatusEnum.ONE_TRUE.getStatus()==quizChooseVo.getStatus())
                .sorted(comparator)
                .collect(Collectors.toList());
    }
    public QuizChooseVo setQuizChooseVo(QuizChooseVo quizChooseVo, String number, NumberAttributesDto numberAttributesDto,String introduce) {
        List<NumberAttributes> numberAttributesList = numberAttributesService.findList(numberAttributesDto,DateUtil.getYear());
        List<NumberAttributes> numberList = quizChooseVo.getNumberList();
        if (null == numberList)
            numberList = new ArrayList<>();
        if(null!=number&&!"".equals(number)) {
            for (NumberAttributes numberAttributes : numberAttributesList) {
                if (number.equals(numberAttributes.getNumber())) {
                    numberList.add(numberAttributes);
                }
            }
            quizChooseVo.setNumberList(numberList);
        }else if("小".equals(introduce) ||
                "大".equals(introduce) ||
                "单".equals(introduce) ||
                "双".equals(introduce) ||
                "合大".equals(introduce) ||
                "合小".equals(introduce) ||
                "合单".equals(introduce) ||
                "合双".equals(introduce) ||
                "大双".equals(introduce) ||
                "小双".equals(introduce) ||
                "大单".equals(introduce) ||
                "小单".equals(introduce) ||
                "尾大".equals(introduce) ||
                "尾小".equals(introduce) ||
                "红大".equals(introduce) ||
            "红小".equals(introduce) ||
            "红单".equals(introduce) ||
            "红双".equals(introduce) ||
            "红大单".equals(introduce) ||
            "红大双".equals(introduce) ||
            "红小单".equals(introduce) ||
            "红小双".equals(introduce) ||
            "红合单".equals(introduce) ||
            "红合双".equals(introduce) ||
            "红合大".equals(introduce) ||
            "红合小".equals(introduce) ||
            "绿大".equals(introduce) ||
            "绿小".equals(introduce) ||
            "绿单".equals(introduce) ||
            "绿双".equals(introduce) ||
            "绿大单".equals(introduce) ||
            "绿大双".equals(introduce) ||
            "绿小单".equals(introduce) ||
            "绿小双".equals(introduce) ||
            "绿合单".equals(introduce) ||
            "绿合双".equals(introduce) ||
            "绿合大".equals(introduce) ||
            "绿合小".equals(introduce) ||
            "蓝大".equals(introduce) ||
            "蓝小".equals(introduce) ||
            "蓝单".equals(introduce) ||
            "蓝双".equals(introduce) ||
            "蓝大单".equals(introduce) ||
            "蓝大双".equals(introduce) ||
            "蓝小单".equals(introduce) ||
            "蓝小双".equals(introduce) ||
            "蓝合单".equals(introduce) ||
            "蓝合双".equals(introduce)||
            "蓝合大".equals(introduce) ||
            "蓝合小".equals(introduce)) {
            for (NumberAttributes numberAttributes : numberAttributesList) {
                if("小".equals(introduce) ||
                        "大".equals(introduce) ||
                        "单".equals(introduce) ||
                        "双".equals(introduce) ||
                        "合大".equals(introduce) ||
                        "合小".equals(introduce) ||
                        "合单".equals(introduce) ||
                        "合双".equals(introduce) ||
                        "大双".equals(introduce) ||
                        "小双".equals(introduce) ||
                        "大单".equals(introduce) ||
                        "小单".equals(introduce) ||
                        "尾大".equals(introduce) ||
                        "尾小".equals(introduce)){
                    if("大".equals(introduce)){
                        //大：大于或等于25
                        if (Integer.parseInt(numberAttributes.getNumber()) >= 25){
                            numberList.add(numberAttributes);
                        }
                    }else if("小".equals(introduce)){
                        //小：小于或等于24
                        if (Integer.parseInt(numberAttributes.getNumber()) <= 24){
                            numberList.add(numberAttributes);
                        }
                    }else if("单".equals(introduce)){
                        //单：为奇数
                        if (Integer.parseInt(numberAttributes.getNumber()) % 2 != 0){
                            numberList.add(numberAttributes);
                        }
                    }else if("双".equals(introduce)){
                        //双：为偶数
                        if (Integer.parseInt(numberAttributes.getNumber()) % 2 == 0){
                            numberList.add(numberAttributes);
                        }
                    }else if("小单".equals(introduce)){
                        if("01".equals(numberAttributes.getNumber())||
                                "03".equals(numberAttributes.getNumber())||
                                "05".equals(numberAttributes.getNumber())||
                                "07".equals(numberAttributes.getNumber())||
                                "09".equals(numberAttributes.getNumber())||
                                "11".equals(numberAttributes.getNumber())||
                                "13".equals(numberAttributes.getNumber())||
                                "15".equals(numberAttributes.getNumber())||
                                "17".equals(numberAttributes.getNumber())||
                                "19".equals(numberAttributes.getNumber())||
                                "21".equals(numberAttributes.getNumber())||
                                "23".equals(numberAttributes.getNumber())){
                            numberList.add(numberAttributes);
                        }
                    }else if("大单".equals(introduce)){
                        if("25".equals(numberAttributes.getNumber())||
                                "27".equals(numberAttributes.getNumber())||
                                "29".equals(numberAttributes.getNumber())||
                                "31".equals(numberAttributes.getNumber())||
                                "33".equals(numberAttributes.getNumber())||
                                "35".equals(numberAttributes.getNumber())||
                                "37".equals(numberAttributes.getNumber())||
                                "39".equals(numberAttributes.getNumber())||
                                "41".equals(numberAttributes.getNumber())||
                                "43".equals(numberAttributes.getNumber())||
                                "45".equals(numberAttributes.getNumber())||
                                "47".equals(numberAttributes.getNumber())){
                            numberList.add(numberAttributes);
                        }
                    }else if("大双".equals(introduce)){
                        if("26".equals(numberAttributes.getNumber())||
                                "28".equals(numberAttributes.getNumber())||
                                "30".equals(numberAttributes.getNumber())||
                                "32".equals(numberAttributes.getNumber())||
                                "34".equals(numberAttributes.getNumber())||
                                "36".equals(numberAttributes.getNumber())||
                                "38".equals(numberAttributes.getNumber())||
                                "40".equals(numberAttributes.getNumber())||
                                "42".equals(numberAttributes.getNumber())||
                                "44".equals(numberAttributes.getNumber())||
                                "46".equals(numberAttributes.getNumber())||
                                "48".equals(numberAttributes.getNumber())){
                            numberList.add(numberAttributes);
                        }
                    }else if("小双".equals(introduce)){
                        if("02".equals(numberAttributes.getNumber())||
                                "04".equals(numberAttributes.getNumber())||
                                "06".equals(numberAttributes.getNumber())||
                                "08".equals(numberAttributes.getNumber())||
                                "10".equals(numberAttributes.getNumber())||
                                "12".equals(numberAttributes.getNumber())||
                                "14".equals(numberAttributes.getNumber())||
                                "16".equals(numberAttributes.getNumber())||
                                "18".equals(numberAttributes.getNumber())||
                                "20".equals(numberAttributes.getNumber())||
                                "22".equals(numberAttributes.getNumber())||
                                "24".equals(numberAttributes.getNumber())){
                            numberList.add(numberAttributes);
                        }
                    }else if("合单".equals(introduce)){
                        //合单：和值为奇数
                        if ((Integer.parseInt(numberAttributes.getNumber())%10 + Integer.parseInt(numberAttributes.getNumber())/10%10) % 2 != 0)
                            numberList.add(numberAttributes);
                    }else if("合双".equals(introduce)){
                        //合双：和值为偶数
                        if ((Integer.parseInt(numberAttributes.getNumber())%10 + Integer.parseInt(numberAttributes.getNumber())/10%10) % 2 == 0)
                            numberList.add(numberAttributes);
                    }else if("合大".equals(introduce)){
                        //合大：和值大于或等于7
                        if (Integer.parseInt(numberAttributes.getNumber())%10 + Integer.parseInt(numberAttributes.getNumber())/10%10 >= 7)
                            numberList.add(numberAttributes);
                    }else if("合小".equals(introduce)){
                        //合小：和值小于或等于6合数小
                        if (Integer.parseInt(numberAttributes.getNumber())%10 + Integer.parseInt(numberAttributes.getNumber())/10%10 <= 6)
                            numberList.add(numberAttributes);
                    }else if("尾大".equals(introduce)){
                        //尾大：大于等于5为大
                        if (Integer.parseInt(numberAttributes.getNumber())%10 <= 5)
                            numberList.add(numberAttributes);
                    }else if("尾小".equals(introduce)){
                        //尾小：小于等于4为小
                        if (Integer.parseInt(numberAttributes.getNumber())%10 <= 4)
                            numberList.add(numberAttributes);
                    }
                }
                if("红大".equals(introduce) ||
                        "红小".equals(introduce) ||
                        "红单".equals(introduce) ||
                        "红双".equals(introduce) ||
                        "红合单".equals(introduce) ||
                        "红合双".equals(introduce)||
                        "红大单".equals(introduce) ||
                        "红大双".equals(introduce) ||
                        "红小单".equals(introduce) ||
                        "红小双".equals(introduce) ||
                        "红合大".equals(introduce) ||
                        "红合小".equals(introduce)){
                    if ("红波".equals(numberAttributes.getColor())) {
                        if("红大".equals(introduce)){
                            //大：大于或等于25
                            if (Integer.parseInt(numberAttributes.getNumber()) >= 25){
                                numberList.add(numberAttributes);
                            }
                        }else if("红小".equals(introduce)){
                            //小：小于或等于24
                            if (Integer.parseInt(numberAttributes.getNumber()) <= 24){
                                numberList.add(numberAttributes);
                            }
                        }else if("红单".equals(introduce)){
                            //单：为奇数
                            if (Integer.parseInt(numberAttributes.getNumber()) % 2 != 0){
                                numberList.add(numberAttributes);
                            }
                        }else if("红双".equals(introduce)){
                            //双：为偶数
                            if (Integer.parseInt(numberAttributes.getNumber()) % 2 == 0){
                                numberList.add(numberAttributes);
                            }
                        }else if("红小单".equals(introduce)){
                            if("01".equals(numberAttributes.getNumber())||
                                    "03".equals(numberAttributes.getNumber())||
                                    "05".equals(numberAttributes.getNumber())||
                                    "07".equals(numberAttributes.getNumber())||
                                    "09".equals(numberAttributes.getNumber())||
                                    "11".equals(numberAttributes.getNumber())||
                                    "13".equals(numberAttributes.getNumber())||
                                    "15".equals(numberAttributes.getNumber())||
                                    "17".equals(numberAttributes.getNumber())||
                                    "19".equals(numberAttributes.getNumber())||
                                    "21".equals(numberAttributes.getNumber())||
                                    "23".equals(numberAttributes.getNumber())){
                                numberList.add(numberAttributes);
                            }
                        }else if("红大单".equals(introduce)){
                            if("25".equals(numberAttributes.getNumber())||
                                    "27".equals(numberAttributes.getNumber())||
                                    "29".equals(numberAttributes.getNumber())||
                                    "31".equals(numberAttributes.getNumber())||
                                    "33".equals(numberAttributes.getNumber())||
                                    "35".equals(numberAttributes.getNumber())||
                                    "37".equals(numberAttributes.getNumber())||
                                    "39".equals(numberAttributes.getNumber())||
                                    "41".equals(numberAttributes.getNumber())||
                                    "43".equals(numberAttributes.getNumber())||
                                    "45".equals(numberAttributes.getNumber())||
                                    "47".equals(numberAttributes.getNumber())){
                                numberList.add(numberAttributes);
                            }
                        }else if("红大双".equals(introduce)){
                            if("26".equals(numberAttributes.getNumber())||
                                    "28".equals(numberAttributes.getNumber())||
                                    "30".equals(numberAttributes.getNumber())||
                                    "32".equals(numberAttributes.getNumber())||
                                    "34".equals(numberAttributes.getNumber())||
                                    "36".equals(numberAttributes.getNumber())||
                                    "38".equals(numberAttributes.getNumber())||
                                    "40".equals(numberAttributes.getNumber())||
                                    "42".equals(numberAttributes.getNumber())||
                                    "44".equals(numberAttributes.getNumber())||
                                    "46".equals(numberAttributes.getNumber())||
                                    "48".equals(numberAttributes.getNumber())){
                                numberList.add(numberAttributes);
                            }
                        }else if("红小双".equals(introduce)){
                            if("02".equals(numberAttributes.getNumber())||
                                    "04".equals(numberAttributes.getNumber())||
                                    "06".equals(numberAttributes.getNumber())||
                                    "08".equals(numberAttributes.getNumber())||
                                    "10".equals(numberAttributes.getNumber())||
                                    "12".equals(numberAttributes.getNumber())||
                                    "14".equals(numberAttributes.getNumber())||
                                    "16".equals(numberAttributes.getNumber())||
                                    "18".equals(numberAttributes.getNumber())||
                                    "20".equals(numberAttributes.getNumber())||
                                    "22".equals(numberAttributes.getNumber())||
                                    "24".equals(numberAttributes.getNumber())){
                                numberList.add(numberAttributes);
                            }
                        }else if("红合单".equals(introduce)){
                            //合单：和值为奇数
                            if ((Integer.parseInt(numberAttributes.getNumber())%10 + Integer.parseInt(numberAttributes.getNumber())/10%10) % 2 != 0)
                                numberList.add(numberAttributes);
                        }else if("红合双".equals(introduce)){
                            //合双：和值为偶数
                            if ((Integer.parseInt(numberAttributes.getNumber())%10 + Integer.parseInt(numberAttributes.getNumber())/10%10) % 2 == 0)
                                numberList.add(numberAttributes);
                        }else if("红合大".equals(introduce)){
                            //合大：和值大于或等于7
                            if (Integer.parseInt(numberAttributes.getNumber())%10 + Integer.parseInt(numberAttributes.getNumber())/10%10 >= 7)
                                numberList.add(numberAttributes);
                        }else if("红合小".equals(introduce)){
                            //合小：和值小于或等于6合数小
                            if (Integer.parseInt(numberAttributes.getNumber())%10 + Integer.parseInt(numberAttributes.getNumber())/10%10 <= 6)
                                numberList.add(numberAttributes);
                        }

                    }
                }
                if("绿大".equals(introduce) ||
                        "绿小".equals(introduce) ||
                        "绿单".equals(introduce) ||
                        "绿双".equals(introduce) ||
                        "绿大单".equals(introduce) ||
                        "绿大双".equals(introduce) ||
                        "绿小单".equals(introduce) ||
                        "绿小双".equals(introduce) ||
                        "绿合单".equals(introduce) ||
                        "绿合双".equals(introduce)||
                        "绿合大".equals(introduce) ||
                        "绿合小".equals(introduce) ){
                    if ("绿波".equals(numberAttributes.getColor())) {
                        if("绿大".equals(introduce)){
                            //大：大于或等于25
                            if (Integer.parseInt(numberAttributes.getNumber()) >= 25){
                                numberList.add(numberAttributes);
                            }
                        }else if("绿小".equals(introduce)){
                            //小：小于或等于24
                            if (Integer.parseInt(numberAttributes.getNumber()) <= 24){
                                numberList.add(numberAttributes);
                            }
                        }else if("绿单".equals(introduce)){
                            //单：为奇数
                            if (Integer.parseInt(numberAttributes.getNumber()) % 2 != 0){
                                numberList.add(numberAttributes);
                            }
                        }else if("绿双".equals(introduce)){
                            //双：为偶数
                            if (Integer.parseInt(numberAttributes.getNumber()) % 2 == 0){
                                numberList.add(numberAttributes);
                            }
                        }else if("绿小单".equals(introduce)){
                            if("01".equals(numberAttributes.getNumber())||
                                    "03".equals(numberAttributes.getNumber())||
                                    "05".equals(numberAttributes.getNumber())||
                                    "07".equals(numberAttributes.getNumber())||
                                    "09".equals(numberAttributes.getNumber())||
                                    "11".equals(numberAttributes.getNumber())||
                                    "13".equals(numberAttributes.getNumber())||
                                    "15".equals(numberAttributes.getNumber())||
                                    "17".equals(numberAttributes.getNumber())||
                                    "19".equals(numberAttributes.getNumber())||
                                    "21".equals(numberAttributes.getNumber())||
                                    "23".equals(numberAttributes.getNumber())){
                                numberList.add(numberAttributes);
                            }
                        }else if("绿大单".equals(introduce)){
                            if("25".equals(numberAttributes.getNumber())||
                                    "27".equals(numberAttributes.getNumber())||
                                    "29".equals(numberAttributes.getNumber())||
                                    "31".equals(numberAttributes.getNumber())||
                                    "33".equals(numberAttributes.getNumber())||
                                    "35".equals(numberAttributes.getNumber())||
                                    "37".equals(numberAttributes.getNumber())||
                                    "39".equals(numberAttributes.getNumber())||
                                    "41".equals(numberAttributes.getNumber())||
                                    "43".equals(numberAttributes.getNumber())||
                                    "45".equals(numberAttributes.getNumber())||
                                    "47".equals(numberAttributes.getNumber())){
                                numberList.add(numberAttributes);
                            }
                        }else if("绿大双".equals(introduce)){
                            if("26".equals(numberAttributes.getNumber())||
                                    "28".equals(numberAttributes.getNumber())||
                                    "30".equals(numberAttributes.getNumber())||
                                    "32".equals(numberAttributes.getNumber())||
                                    "34".equals(numberAttributes.getNumber())||
                                    "36".equals(numberAttributes.getNumber())||
                                    "38".equals(numberAttributes.getNumber())||
                                    "40".equals(numberAttributes.getNumber())||
                                    "42".equals(numberAttributes.getNumber())||
                                    "44".equals(numberAttributes.getNumber())||
                                    "46".equals(numberAttributes.getNumber())||
                                    "48".equals(numberAttributes.getNumber())){
                                numberList.add(numberAttributes);
                            }
                        }else if("绿小双".equals(introduce)){
                            if("02".equals(numberAttributes.getNumber())||
                                    "04".equals(numberAttributes.getNumber())||
                                    "06".equals(numberAttributes.getNumber())||
                                    "08".equals(numberAttributes.getNumber())||
                                    "10".equals(numberAttributes.getNumber())||
                                    "12".equals(numberAttributes.getNumber())||
                                    "14".equals(numberAttributes.getNumber())||
                                    "16".equals(numberAttributes.getNumber())||
                                    "18".equals(numberAttributes.getNumber())||
                                    "20".equals(numberAttributes.getNumber())||
                                    "22".equals(numberAttributes.getNumber())||
                                    "24".equals(numberAttributes.getNumber())){
                                numberList.add(numberAttributes);
                            }
                        }else if("绿合单".equals(introduce)){
                            //合单：和值为奇数
                            if ((Integer.parseInt(numberAttributes.getNumber())%10 + Integer.parseInt(numberAttributes.getNumber())/10%10) % 2 != 0)
                                numberList.add(numberAttributes);
                        }else if("绿合双".equals(introduce)){
                            //合双：和值为偶数
                            if ((Integer.parseInt(numberAttributes.getNumber())%10 + Integer.parseInt(numberAttributes.getNumber())/10%10) % 2 == 0)
                                numberList.add(numberAttributes);
                        }else if("绿合大".equals(introduce)){
                            //合大：和值大于或等于7
                            if (Integer.parseInt(numberAttributes.getNumber())%10 + Integer.parseInt(numberAttributes.getNumber())/10%10 >= 7)
                                numberList.add(numberAttributes);
                        }else if("绿合小".equals(introduce)){
                            //合小：和值小于或等于6合数小
                            if (Integer.parseInt(numberAttributes.getNumber())%10 + Integer.parseInt(numberAttributes.getNumber())/10%10 <= 6)
                                numberList.add(numberAttributes);
                        }
                    }
                }
                if("蓝大".equals(introduce) ||
                        "蓝小".equals(introduce) ||
                        "蓝单".equals(introduce) ||
                        "蓝双".equals(introduce) ||
                        "蓝大单".equals(introduce) ||
                        "蓝大双".equals(introduce) ||
                        "蓝小单".equals(introduce) ||
                        "蓝小双".equals(introduce) ||
                        "蓝合单".equals(introduce) ||
                        "蓝合双".equals(introduce) ||
                        "蓝合大".equals(introduce) ||
                        "蓝合小".equals(introduce)){
                    if ("蓝波".equals(numberAttributes.getColor())) {
                        if("蓝大".equals(introduce)){
                            //大：大于或等于25
                            if (Integer.parseInt(numberAttributes.getNumber()) >= 25){
                                numberList.add(numberAttributes);
                            }
                        }else if("蓝小".equals(introduce)){
                            //小：小于或等于24
                            if (Integer.parseInt(numberAttributes.getNumber()) <= 24){
                                numberList.add(numberAttributes);
                            }
                        }else if("蓝单".equals(introduce)){
                            //单：为奇数
                            if (Integer.parseInt(numberAttributes.getNumber()) % 2 != 0){
                                numberList.add(numberAttributes);
                            }
                        }else if("蓝双".equals(introduce)){
                            //双：为偶数
                            if (Integer.parseInt(numberAttributes.getNumber()) % 2 == 0){
                                numberList.add(numberAttributes);
                            }
                        }else if("蓝小单".equals(introduce)){
                            if("01".equals(numberAttributes.getNumber())||
                                    "03".equals(numberAttributes.getNumber())||
                                    "05".equals(numberAttributes.getNumber())||
                                    "07".equals(numberAttributes.getNumber())||
                                    "09".equals(numberAttributes.getNumber())||
                                    "11".equals(numberAttributes.getNumber())||
                                    "13".equals(numberAttributes.getNumber())||
                                    "15".equals(numberAttributes.getNumber())||
                                    "17".equals(numberAttributes.getNumber())||
                                    "19".equals(numberAttributes.getNumber())||
                                    "21".equals(numberAttributes.getNumber())||
                                    "23".equals(numberAttributes.getNumber())){
                                numberList.add(numberAttributes);
                            }
                        }else if("蓝大单".equals(introduce)){
                            if("25".equals(numberAttributes.getNumber())||
                                    "27".equals(numberAttributes.getNumber())||
                                    "29".equals(numberAttributes.getNumber())||
                                    "31".equals(numberAttributes.getNumber())||
                                    "33".equals(numberAttributes.getNumber())||
                                    "35".equals(numberAttributes.getNumber())||
                                    "37".equals(numberAttributes.getNumber())||
                                    "39".equals(numberAttributes.getNumber())||
                                    "41".equals(numberAttributes.getNumber())||
                                    "43".equals(numberAttributes.getNumber())||
                                    "45".equals(numberAttributes.getNumber())||
                                    "47".equals(numberAttributes.getNumber())){
                                numberList.add(numberAttributes);
                            }
                        }else if("蓝大双".equals(introduce)){
                            if("26".equals(numberAttributes.getNumber())||
                                    "28".equals(numberAttributes.getNumber())||
                                    "30".equals(numberAttributes.getNumber())||
                                    "32".equals(numberAttributes.getNumber())||
                                    "34".equals(numberAttributes.getNumber())||
                                    "36".equals(numberAttributes.getNumber())||
                                    "38".equals(numberAttributes.getNumber())||
                                    "40".equals(numberAttributes.getNumber())||
                                    "42".equals(numberAttributes.getNumber())||
                                    "44".equals(numberAttributes.getNumber())||
                                    "46".equals(numberAttributes.getNumber())||
                                    "48".equals(numberAttributes.getNumber())){
                                numberList.add(numberAttributes);
                            }
                        }else if("蓝小双".equals(introduce)){
                            if("02".equals(numberAttributes.getNumber())||
                                    "04".equals(numberAttributes.getNumber())||
                                    "06".equals(numberAttributes.getNumber())||
                                    "08".equals(numberAttributes.getNumber())||
                                    "10".equals(numberAttributes.getNumber())||
                                    "12".equals(numberAttributes.getNumber())||
                                    "14".equals(numberAttributes.getNumber())||
                                    "16".equals(numberAttributes.getNumber())||
                                    "18".equals(numberAttributes.getNumber())||
                                    "20".equals(numberAttributes.getNumber())||
                                    "22".equals(numberAttributes.getNumber())||
                                    "24".equals(numberAttributes.getNumber())){
                                numberList.add(numberAttributes);
                            }
                        }else if("蓝合单".equals(introduce)){
                            //合单：和值为奇数
                            if ((Integer.parseInt(numberAttributes.getNumber())%10 + Integer.parseInt(numberAttributes.getNumber())/10%10) % 2 != 0)
                                numberList.add(numberAttributes);
                        }else if("蓝合双".equals(introduce)){
                            //合双：和值为偶数
                            if ((Integer.parseInt(numberAttributes.getNumber())%10 + Integer.parseInt(numberAttributes.getNumber())/10%10) % 2 == 0)
                                numberList.add(numberAttributes);
                        }else if("蓝合大".equals(introduce)){
                            //合大：和值大于或等于7
                            if (Integer.parseInt(numberAttributes.getNumber())%10 + Integer.parseInt(numberAttributes.getNumber())/10%10 >= 7)
                                numberList.add(numberAttributes);
                        }else if("蓝合小".equals(introduce)){
                            //合小：和值小于或等于6合数小
                            if (Integer.parseInt(numberAttributes.getNumber())%10 + Integer.parseInt(numberAttributes.getNumber())/10%10 <= 6)
                                numberList.add(numberAttributes);
                        }
                    }
                }
            }
            quizChooseVo.setNumberList(numberList);
        }else {
                quizChooseVo.setNumberList(numberAttributesList);
        }

        return  quizChooseVo;
    }
}
