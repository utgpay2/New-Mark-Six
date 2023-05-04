package com.central.common.redis.constant;

public interface RedisKeyConstant {

    /**
     * 锁等待时间
     */
    Integer WAIT_TIME = 120;
    Integer LEASE_TIME = 60;
    /**
     * 上下分
     */
    String SYS_USER_MONEY_MONEY_LOCK = "lock::sys_user_money::money::";

}
