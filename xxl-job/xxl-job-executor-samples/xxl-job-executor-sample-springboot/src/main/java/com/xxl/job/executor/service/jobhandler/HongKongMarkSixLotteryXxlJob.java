package com.xxl.job.executor.service.jobhandler;

import com.central.common.model.Lottery;
import com.central.common.model.WnData;
import com.central.common.model.enums.LotteryEnum;
import com.central.common.utils.DateUtil;
import com.central.common.vo.SiteLotteryVo;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.executor.service.IHongKongPositionUtils;
import com.xxl.job.executor.service.ILotteryService;
import com.xxl.job.executor.service.ILotteryWinDataService;
import com.xxl.job.executor.service.IWnDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * XxlJob开发示例（Bean模式）
 *
 * 开发步骤：
 *      1、任务开发：在Spring Bean实例中，开发Job方法；
 *      2、注解配置：为Job方法添加注解 "@XxlJob(value="自定义jobhandler名称", init = "JobHandler初始化方法", destroy = "JobHandler销毁方法")"，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 *      3、执行日志：需要通过 "XxlJobHelper.log" 打印执行日志；
 *      4、任务结果：默认任务结果为 "成功" 状态，不需要主动设置；如有诉求，比如设置任务结果为失败，可以通过 "XxlJobHelper.handleFail/handleSuccess" 自主设置任务结果；
 *
 * @author xuxueli 2019-12-11 21:52:51
 */
@Component
public class HongKongMarkSixLotteryXxlJob {
    private static Logger logger = LoggerFactory.getLogger(HongKongMarkSixLotteryXxlJob.class);
    @Autowired
    private ILotteryService lotteryService;
    @Autowired
    private IHongKongPositionUtils iHongKongPositionUtils;
    @Autowired
    private IWnDataService iWnDataService;

    /**
     * 香港六合彩开奖(自动爬取官菜数据)
     */
    @XxlJob("hongKongMarkSixLotteryJobHandler")
    public void hongKongMarkSixLotteryJobHandler() throws Exception {
        XxlJobHelper.log("香港六合彩开奖开始=========》》》"+ DateUtil.dateToyyyyMMddHHmmss(new Date()));
        try {
            WnData wnData = iWnDataService.lastOneWnData(LotteryEnum.HONGKONG_MKS.getStatus());
            if(null!=wnData){
                //判断是否当前日期开奖号码
                if(!DateUtil.dateToyyyyMMdd(new Date()).equals(DateUtil.strToDate(wnData.getNextTime(),DateUtil.YYYYMMDD))){
                    return;
                }

            }
            Map<String, Object> params = new HashMap<>();
            List<Lottery> list = lotteryService.findLotteryList(params);
            list = list.stream().filter(lottery -> LotteryEnum.HONGKONG_MKS.getStatus()==Math.toIntExact(lottery.getId()))
                    .collect(Collectors.toList());
            Lottery lottery = list.get(0);
            iHongKongPositionUtils.reqPosition(lottery.getLotteryUrl());
        } catch (Exception e) {
            XxlJobHelper.log(e);
            XxlJobHelper.handleFail("New HK Mark Six Lottery Settlement Failed");
            Thread.sleep(2*60*1000);//1秒=1000
            this.hongKongMarkSixLotteryJobHandler();
        }
        XxlJobHelper.log("香港六合彩开奖结束=========》》》"+ DateUtil.dateToyyyyMMddHHmmss(new Date()));
    }


}
