package com.central.common.constant;

import com.central.common.model.WnData;

public interface RedisConstants {
    //缓存时间 1分钟
    public static final Long EXPIRE_TIME_1_MINUTE = 1 * 60L;
    //缓存时间 30天
    public static final Long EXPIRE_TIME_30_DAYS = 30 * 24 * 60 * 60L;
    //缓存时间 7天
    public static final Long EXPIRE_TIME_7_DAYS = 7 * 24 * 60 * 60L;
    //缓存时间 1天
    public static final Long EXPIRE_TIME_1_DAYS = 1 * 24 * 60 * 60L;
    //- 缓存站点下彩种
    public static final String SITE_LOTTERY_LIST_KEY = "SITE:LOTTERY:LIST:{}:{}:{}";
    //- 缓存彩种
    public static final String LOTTERYID_LIST_KEY = "LOTTERYID:LIST:{}:{}";
    //- 缓存--分页开查询奖号码列表
    public static final String WNDATA_LIST_PAGE_KEY = "WNDATA:LIST:PAGE:{}:{}:{}:{}";
    //- 缓存--根据彩种ID查询最近一期开奖数据
    public static final String LASTONE_WNDATA_KEY = "LASTONE:WNDATA:{}";
}
