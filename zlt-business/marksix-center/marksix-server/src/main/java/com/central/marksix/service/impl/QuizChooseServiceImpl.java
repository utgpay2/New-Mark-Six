package com.central.marksix.service.impl;

import com.central.common.model.NumberAttributes;
import com.central.common.model.QuizChoose;
import com.central.common.model.enums.StatusEnum;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.marksix.entity.vo.NumberAttributesVo;
import com.central.marksix.entity.vo.QuizChooseVo;
import com.central.marksix.mapper.QuizChooseMapper;
import com.central.marksix.service.INumberAttributesService;
import com.central.marksix.service.IQuizChooseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        List<QuizChoose> chooseList = baseMapper.findList( params);
        List<QuizChooseVo> chooseVoList = new ArrayList<>();
        for(QuizChoose quizChoose:chooseList){
            QuizChooseVo quizChooseVo = new QuizChooseVo();
            BeanUtils.copyProperties(quizChoose,quizChooseVo);
            if("01".equals(quizChoose.getIntroduce())||
                "02".equals(quizChoose.getIntroduce())||
                "03".equals(quizChoose.getIntroduce())||
                "04".equals(quizChoose.getIntroduce())||
                "05".equals(quizChoose.getIntroduce())||
                "06".equals(quizChoose.getIntroduce())||
                "07".equals(quizChoose.getIntroduce())||
                "08".equals(quizChoose.getIntroduce())||
                "09".equals(quizChoose.getIntroduce())||
                "10".equals(quizChoose.getIntroduce())||
                "11".equals(quizChoose.getIntroduce())||
                "12".equals(quizChoose.getIntroduce())||
                "13".equals(quizChoose.getIntroduce())||
                "14".equals(quizChoose.getIntroduce())||
                "15".equals(quizChoose.getIntroduce())||
                "16".equals(quizChoose.getIntroduce())||
                "17".equals(quizChoose.getIntroduce())||
                "18".equals(quizChoose.getIntroduce())||
                "19".equals(quizChoose.getIntroduce())||
                "20".equals(quizChoose.getIntroduce())||
                "21".equals(quizChoose.getIntroduce())||
                "22".equals(quizChoose.getIntroduce())||
                "23".equals(quizChoose.getIntroduce())||
                "24".equals(quizChoose.getIntroduce())||
                "25".equals(quizChoose.getIntroduce())||
                "26".equals(quizChoose.getIntroduce())||
                "27".equals(quizChoose.getIntroduce())||
                "28".equals(quizChoose.getIntroduce())||
                "29".equals(quizChoose.getIntroduce())||
                "30".equals(quizChoose.getIntroduce())||
                "31".equals(quizChoose.getIntroduce())||
                "32".equals(quizChoose.getIntroduce())||
                "33".equals(quizChoose.getIntroduce())||
                "34".equals(quizChoose.getIntroduce())||
                "35".equals(quizChoose.getIntroduce())||
                "36".equals(quizChoose.getIntroduce())||
                "37".equals(quizChoose.getIntroduce())||
                "38".equals(quizChoose.getIntroduce())||
                "39".equals(quizChoose.getIntroduce())||
                "40".equals(quizChoose.getIntroduce())||
                "41".equals(quizChoose.getIntroduce())||
                "42".equals(quizChoose.getIntroduce())||
                "43".equals(quizChoose.getIntroduce())||
                "44".equals(quizChoose.getIntroduce())||
                "45".equals(quizChoose.getIntroduce())||
                "46".equals(quizChoose.getIntroduce())||
                "47".equals(quizChoose.getIntroduce())||
                "48".equals(quizChoose.getIntroduce())||
                "49".equals(quizChoose.getIntroduce())){
                quizChooseVo = this.setQuizChooseVo(quizChooseVo,quizChoose.getIntroduce(),null);
            }
            if("金".equals(quizChoose.getIntroduce())||
                    "木".equals(quizChoose.getIntroduce())||
                    "水".equals(quizChoose.getIntroduce())||
                    "火".equals(quizChoose.getIntroduce())||
                    "土".equals(quizChoose.getIntroduce())){
                NumberAttributesVo numberAttributesVo = new NumberAttributesVo();
                numberAttributesVo.setFiveElements(quizChoose.getIntroduce());
                quizChooseVo = this.setQuizChooseVo(quizChooseVo,"",numberAttributesVo);
            }
            if("鼠".equals(quizChoose.getIntroduce())||
            "牛".equals(quizChoose.getIntroduce())||
            "虎".equals(quizChoose.getIntroduce())||
            "兔".equals(quizChoose.getIntroduce())||
            "龙".equals(quizChoose.getIntroduce())||
            "蛇".equals(quizChoose.getIntroduce())||
            "马".equals(quizChoose.getIntroduce())||
            "羊".equals(quizChoose.getIntroduce())||
            "猴".equals(quizChoose.getIntroduce())||
            "鸡".equals(quizChoose.getIntroduce())||
            "狗".equals(quizChoose.getIntroduce())||
            "猪".equals(quizChoose.getIntroduce())){
                NumberAttributesVo numberAttributesVo = new NumberAttributesVo();
                numberAttributesVo.setZodiac(quizChoose.getIntroduce());
                quizChooseVo = this.setQuizChooseVo(quizChooseVo,"",numberAttributesVo);
            }
            chooseVoList.add(quizChooseVo);
        }
        return chooseVoList;
    }
    public QuizChooseVo setQuizChooseVo(QuizChooseVo quizChooseVo,String number,NumberAttributesVo numberAttributesVo) {
        List<NumberAttributes> numberAttributesList = numberAttributesService.findList(numberAttributesVo);
        if(null!=number&&!"".equals(number)) {
            for (NumberAttributes numberAttributes : numberAttributesList) {
                if (number.equals(numberAttributes.getNumber())) {
                    List<NumberAttributes> numberList = quizChooseVo.getNumberList();
                    if (null == numberList)
                        numberList = new ArrayList<>();
                    numberList.add(numberAttributes);
                    quizChooseVo.setNumberList(numberList);
                }
            }
        }else {
            quizChooseVo.setNumberList(numberAttributesList);
        }
        return  quizChooseVo;
    }
}
