package com.xxl.job.executor.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.central.common.model.WnData;
import com.central.common.model.enums.LotteryEnum;
import com.central.common.model.enums.StatusEnum;
import com.central.common.utils.DateUtil;
import com.xxl.job.executor.service.IHongKongPositionUtils;
import com.xxl.job.executor.service.IWnDataService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HongKongPositionUtils implements IHongKongPositionUtils {
    @Autowired
    private IWnDataService iWnDataService;
    @Override
    public void reqPosition(String url) throws Exception {
        String htmlStr = ConnectionURL.Connet(url);
        //解析字符串为Document对象
        Document doc = Jsoup.parse(htmlStr);
        Elements tables = doc.body().getElementsByTag("table");
        Element nextTable = tables.get(3);//下期
        Elements nextTrs = nextTable.getElementsByTag("tr");
        Element nextTr0 = nextTrs.get(0);
        Elements nextTr0Tds = nextTr0.getElementsByTag("td");
        Element nextTr0Td1 = nextTr0Tds.get(1);//下一期开奖期号
        String nextQihao = null!=nextTr0Td1.text()?nextTr0Td1.text().replaceAll("\\s*", "").replaceAll("/",""):"";//例如  23/093
        Element nextTr1 =  nextTrs.get(1);
        Elements nextTr1Tds = nextTr1.getElementsByTag("td");
        Element nextTr1Td1 = nextTr1Tds.get(1);//下一期开奖日期
        String nextDate = null!=nextTr1Td1.text()?nextTr1Td1.text().replaceAll("\\s*", "").substring(0,10):"";//例如  19/08/2023 (星期六)
        Element nextTr2 =  nextTrs.get(2);
        Elements nextTr2Tds = nextTr2.getElementsByTag("td");
        Element nextTr2Td1 = nextTr2Tds.get(1);//下一期开奖时间
        String nextTime = nextTr2Td1.text();//例如  晚上 9:15
        String[] timeStr = nextTime.split(" ");
        Long time = DateUtil.strToDate(timeStr[1],DateUtil.HHMM).getTime();
        if("晚上".equals(timeStr[0])){
            nextTime = DateUtil.longToStr(time+43200000,DateUtil.HHMM);
        }else {
            nextTime = DateUtil.longToStr(time,DateUtil.HHMM);
        }

        Element lastTable = tables.get(7);//上一期
        Elements lastTrs = lastTable.getElementsByTag("tr");
        Element lastTr0 = lastTrs.get(0);
        Elements lastTr0Tds = lastTr0.getElementsByTag("td");
        Element lastTr0Td0 = lastTr0Tds.get(0);//开奖期号
        String qihao =  null!=lastTr0Td0.text()?lastTr0Td0.text().replaceAll("\\s*", "").replaceAll("/",""):"";//例如  攪珠期數 : 23/092
        qihao =  qihao.substring(qihao.indexOf(":")+1,qihao.length());
        Element lastTr0Td1 = lastTr0Tds.get(1);//开奖日期
        String settlTime =  null!=lastTr0Td1.text()?lastTr0Td1.text().replaceAll("\\s*", ""):"";//例如  攪珠日期 : 17/08/2023(星期四)
        settlTime =  settlTime.substring(settlTime.indexOf(":")+1,settlTime.length()-5);
        Elements lastImgs = lastTable.getElementsByTag("img");
        Element lastImg0 = lastImgs.get(0);
        String srcStr0 = null!=lastImg0.attr("src")?lastImg0.attr("src").replaceAll("\\s*", ""):"";
        String number0 = srcStr0.substring(srcStr0.indexOf("no_")+3,srcStr0.indexOf("no_")+5);
        Element lastImg1 = lastImgs.get(1);
        String srcStr1 = null!=lastImg1.attr("src")?lastImg1.attr("src").replaceAll("\\s*", ""):"";
        String number1 = srcStr1.substring(srcStr1.indexOf("no_")+3,srcStr1.indexOf("no_")+5);
        Element lastImg2 = lastImgs.get(2);
        String srcStr2 = null!=lastImg2.attr("src")?lastImg2.attr("src").replaceAll("\\s*", ""):"";
        String number2 = srcStr2.substring(srcStr2.indexOf("no_")+3,srcStr2.indexOf("no_")+5);
        Element lastImg3 = lastImgs.get(3);
        String srcStr3 = null!=lastImg3.attr("src")?lastImg3.attr("src").replaceAll("\\s*", ""):"";
        String number3 = srcStr3.substring(srcStr3.indexOf("no_")+3,srcStr3.indexOf("no_")+5);
        Element lastImg4 = lastImgs.get(4);
        String srcStr4 = null!=lastImg4.attr("src")?lastImg4.attr("src").replaceAll("\\s*", ""):"";
        String number4 = srcStr4.substring(srcStr4.indexOf("no_")+3,srcStr4.indexOf("no_")+5);
        Element lastImg5 = lastImgs.get(5);
        String srcStr5 = null!=lastImg5.attr("src")?lastImg5.attr("src").replaceAll("\\s*", ""):"";
        String number5 = srcStr5.substring(srcStr5.indexOf("no_")+3,srcStr5.indexOf("no_")+5);
        Element lastImg6 = lastImgs.get(7);
        String srcStr6 = null!=lastImg6.attr("src")?lastImg6.attr("src").replaceAll("\\s*", ""):"";
        String number6 = srcStr6.substring(srcStr6.indexOf("no_")+3,srcStr6.indexOf("no_")+5);
        LambdaQueryWrapper<WnData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WnData::getQihao,qihao);
        WnData oldWnData = iWnDataService.getBaseMapper().selectOne(queryWrapper);
        if(null!=oldWnData){
            return;
        }
        WnData wnData = new WnData();
        //期号
        wnData.setQihao(Long.valueOf(qihao));
        //彩种id
        wnData.setLotteryId(LotteryEnum.HONGKONG_MKS.getStatus());
        //开奖号码
        wnData.setNumbers(number0+","+number1+","+number2+","+number3+","+number4+","+number5+","+number6);
        //开奖视频
        wnData.setVideoPath("https://lhczh.com/html/KaijVideo.html");
        Date date = DateUtil.strToDate(nextDate+" "+nextTime,DateUtil.ddMMyyyyHHMM);
        //下一期开奖时间
        wnData.setNextTime(DateUtil.dateToyyyyMMddHHmmss(date));
        //下一期号
        wnData.setNextQihao(nextQihao);
        //号码归属年份
        wnData.setYear(Integer.valueOf(settlTime.substring(6,10)));
        //是否结算完成(0否，1结算完成)
        wnData.setStatus(StatusEnum.ZERO_FALSE.getStatus());
        //是否显示(0隐藏，1显示)
        wnData.setIsDisplay(StatusEnum.ONE_TRUE.getStatus());
        wnData.setCreateTime(new Date());
        wnData.setUpdateTime(new Date());
        iWnDataService.saveWnData(wnData);
    }
}
