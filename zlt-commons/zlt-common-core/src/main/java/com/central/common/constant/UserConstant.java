package com.central.common.constant;

import java.math.BigDecimal;

/**
 * 会员相关操作全局变量
 * 主要存储 会员redis Key, 会员下注订单， 上线分等和会员有关常量
 *
 */
public interface UserConstant {

    /**
     * 上下分最多只能 7 位数
     */
    BigDecimal maxtransferMoney = BigDecimal.valueOf(10000000);
}
