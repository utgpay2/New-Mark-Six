package com.central.common.constant;

import com.central.common.model.NumberAttributes;
import com.central.common.model.QuizDetails;
import com.central.common.model.QuizOrders;
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
    //- 缓存商户
    public static final String SITE_LIST_KEY = "SITE";
    //- 缓存彩种
    public static final String SITE_LOTTERY_KEY = "SITE:LOTTERY";
    //- 缓存杀率
    public static final String SITE_KILLODDS_KEY = "SITE:KILLODDS";
    //-彩种ID查询所有商户彩种
    public static final String SITE_LOTTERYID_LIST_KEY = "SITE:LOTTERY:ID:LIST:{}";
    public static final String SITE_LOTTERYID_OBJECT_KEY = "SITE:LOTTERY:ID:OBJECT:{}";
    //- 缓存商户下彩种
    public static final String SITE_LOTTERY_LIST_KEY = "SITE:LOTTERY:{}";
    //- 缓存--分页开查询奖号码列表
    public static final String WNDATA_LIST_PAGE_KEY = "WNDATA:{}:{}:{}";
    //- 缓存--根据彩种ID查询最近一期开奖数据
    public static final String LASTONE_WNDATA_KEY = "WNDATA:{}";
    //- 缓存分类
    public static final String SITE_LOTTERY_CATEGORY_KEY = "SITE:LOTTERY:CATEGORY";
    //- 缓存商户下彩种下分类（一类）
    public static final String SITE_LOTTERY_CATEGORY_LIST_KEY = "SITE:LOTTERY:CATEGORY:{}:{}";
    //- 缓存商户下彩种下分类下分类（二类）
    public static final String SITE_LOTTERY_CATEGORY_QUIZ_LIST_KEY = "SITE:LOTTERY:CATEGORY:QUIZ:{}:{}:{}";
    //- 缓存商户下彩种下分类下分类下分类（三类）
    public static final String SITE_LOTTERY_CATEGORY_QUIZ_QUIZDETAILS_LIST_KEY = "SITE:LOTTERY:CATEGORY:QUIZ:QUIZDETAILS:{}:{}:{}:{}";
    //- 缓存竞猜奖项详情
    public static final String SITE_LOTTERY_CATEGORY_QUIZ_QUIZDETAILS_QUIZCHOOSE_LIST_KEY = "SITE:LOTTERY:CATEGORY:QUIZ:QUIZDETAILS:QUIZCHOOSE:{}:{}:{}:{}:{}";
    //- 缓存号码属性表
    public static final String NUMBERATTRIBUTES_LIST_KEY = "NUMBERATTRIBUTES:LIST:{}:{}";
    //- 缓存我的投注记录
    public static final String SITE_LOTTERY_ORDERS_MY_LIST_KEY = "SITE:LOTTERY:ORDERS:MY:{}:{}:{}:{}:{}";
    //- 缓存统计我的投注记录
    public static final String SITE_LOTTERY_ORDERS_MYSTATI_LIST_KEY = "SITE:LOTTERY:ORDERS:MYSTATI:{}:{}:{}:{}";
    //- 缓存子投注记录
    public static final String SITE_LOTTERY_ORDERS_SUB_LIST_KEY = "SITE:LOTTERY:ORDERS:SUB:{}";
    //- 缓存商户用户
    public static final String SITE_SYSUSER_KEY = "SITE:SYSUSER:OBJECT:{}";
    //- 缓存商户用户账变记录
    public static final String SITE_MONEYLOG_KEY = "SITE:MONEYLOG:OBJECT:{}";
}
