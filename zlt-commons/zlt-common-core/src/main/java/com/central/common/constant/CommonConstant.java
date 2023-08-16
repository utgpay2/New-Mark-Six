package com.central.common.constant;

import java.math.BigDecimal;

/**
 * 全局公共常量
 *
 * @author zlt
 * @date 2018/10/29
 */
public interface CommonConstant {
    /**
     * 项目版本号(banner使用)
     */
    String PROJECT_VERSION = "1.0.0";

    /**
     * token请求头名称
     */
    String TOKEN_HEADER = "Authorization";

    /**
     * The access token issued by the authorization server. This value is REQUIRED.
     */
    String ACCESS_TOKEN = "access_token";

    String BEARER_TYPE = "Bearer";

    /**
     * 标签 header key
     */
    String HEADER_LABEL = "x-label";

    /**
     * 标签 header 分隔符
     */
    String HEADER_LABEL_SPLIT = ",";

    /**
     * 标签或 名称
     */
    String LABEL_OR = "labelOr";

    /**
     * 标签且 名称
     */
    String LABEL_AND = "labelAnd";

    /**
     * 权重key
     */
    String WEIGHT_KEY = "weight";

    /**
     * 删除
     */
    String STATUS_DEL = "1";

    /**
     * 正常
     */
    String STATUS_NORMAL = "0";

    /**
     * 锁定
     */
    String STATUS_LOCK = "9";

    /**
     * 目录
     */
    Integer CATALOG = -1;

    /**
     * 菜单
     */
    Integer MENU = 1;

    /**
     * 权限
     */
    Integer PERMISSION = 2;

    /**
     * 删除标记
     */
    String DEL_FLAG = "is_del";

    /**
     * 超级管理员用户名
     */
    String ADMIN_USER_NAME = "admin";

    /**
     * 公共日期格式
     */
    String MONTH_FORMAT = "yyyy-MM";
    String DATE_FORMAT = "yyyy-MM-dd";
    String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    String SIMPLE_MONTH_FORMAT = "yyyyMM";
    String SIMPLE_DATE_FORMAT = "yyyyMMdd";
    String SIMPLE_DATETIME_FORMAT = "yyyyMMddHHmmss";
    String TIME_ZONE_GMT7 = "GMT+8";

    String DEF_USER_PASSWORD = "123456";

    String LOCK_KEY_PREFIX = "LOCK_KEY";

    /**
     * 租户id参数
     */
    String TENANT_ID_PARAM = "tenantId";


    /**
     * 日志链路追踪id信息头
     */
    String TRACE_ID_HEADER = "x-traceId-header";
    /**
     * 日志链路追踪id日志标志
     */
    String LOG_TRACE_ID = "traceId";
    /**
     * 负载均衡策略-版本号 信息头
     */
    String Z_L_T_VERSION = "z-l-t-version";
    /**
     * 注册中心元数据 版本号
     */
    String METADATA_VERSION = "version";

    /**
     * 文件分隔符
     */
    String PATH_SPLIT = "/";

    /**
     * 后台管理用户
     */
    String USER_TYPE_BACKEND = "BACKEND";

    /**
     * 前端APP用户
     */
    String USER_TYPE_APP = "APP";



    String PLAYER_ACCOUNT_QUEUE = "player_acc_list";

    public String REDIS_WEBAPP = "webApp:";

    /** 在线状态,1在线,2离线*/
    Integer ONLINE = 1;
    Integer OFFLINE = 2;

    //禁用
    Integer DISABLE = 0;
    //正常
    Integer NORMAL = 1;
    //维护
    Integer MAINTAIN = 2;


    //百分比函数
    BigDecimal ONE_HUNDRED=new BigDecimal(100);

    //小
    Integer SMALL = 1;
    //大
    Integer BIG = 2;
    //和
    Integer GENTLE = 3;
    //单
    Integer ONE = 1;
    //双
    Integer TWO = 2;
}
