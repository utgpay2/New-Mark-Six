package com.xxl.job.executor.service.jobhandler;

import com.central.common.model.enums.LotteryEnum;
import com.central.common.utils.DateUtil;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.executor.service.ILotteryWinDataService;
import com.xxl.job.executor.service.IWinningSettlementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

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
public class NewHongKongMarkSixLotteryXxlJob {
    private static Logger logger = LoggerFactory.getLogger(NewHongKongMarkSixLotteryXxlJob.class);

    @Autowired
    private ILotteryWinDataService iLotteryWinDataService;

    /**
     * 新香港六合彩开奖
     */
    @XxlJob("newHongKongMarkSixLotteryJobHandler")
    public void newHongKongMarkSixLotteryJobHandler() throws Exception {
        XxlJobHelper.log("新香港六合彩开奖开始=========》》》"+ DateUtil.dateToyyyyMMddHHmmss(new Date()));
        try {
            iLotteryWinDataService.markSixLottery(LotteryEnum.NEWHONGKONG_MKS.getStatus());
        } catch (Exception e) {
            XxlJobHelper.log(e);
            XxlJobHelper.handleFail("New HK Mark Six Lottery Settlement Failed");
        }
        XxlJobHelper.log("新香港六合彩开奖结束=========》》》"+ DateUtil.dateToyyyyMMddHHmmss(new Date()));
    }


}
