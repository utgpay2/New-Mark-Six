package com.central.push.constant;

/**
 * webSocket 消息类型
 *
 * @author zlt
 * @date 2018/10/29
 */
public interface SocketTypeConstant {

    /**
     * 客户端发送消息心跳
     */
    String HEARTBEAT = "heartbeat";

    /**
     * 用户金额
     */
    String MONEY = "money";

    /**
     * 轮播图
     */
    String BANNER = "banner";

    /**
     * 公告
     */
    String NOTICE = "notice";

    /**
     * 在线人数
     */
    String ONLINE_NUMS = "onlineNum";

    /**
     * 即时彩池
     */
    String LIVE_POT = "livePot";

    /**
     * 桌台配置信息
     */
    String TABLE_INFO = "tableInfo";

    /**
     * 下注开奖结果
     */
    String LOTTERY_RESULT = "lotteryResult";

    /**
     * 派彩结果
     */
    String PAYOUT_RESULT = "payoutResult";

    /**
     * 后台修改桌台状态信息
     */
    String UPDATE_TABLE_STATUS = "updateTableStatus";

    /**
     * 后台修改游戏状态信息
     */
    String UPDATE_GAME_STATUS = "updateGameStatus";

    /**
     * 桌台用户分组信息
     */
    String TABLE_GROUP_USER = "tableGroupUser";

    /**
     * 桌台在线人数
     */
    String GAME_ONLINE_NUM = "gameOnlineNum";


}
