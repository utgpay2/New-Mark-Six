package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("sys_currency")
public class SysCurrency extends SuperEntity {

    private String currency;

    private String currencyName;

    private String status;

    private Integer sort;
}
