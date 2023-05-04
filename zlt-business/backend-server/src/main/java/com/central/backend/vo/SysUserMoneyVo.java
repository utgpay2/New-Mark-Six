package com.central.backend.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 用户钱包表
 *
 * @author zlt
 * @date 2021-12-03 19:31:47
 */
@Data
@ApiModel("用户钱包")
public class SysUserMoneyVo{

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "余额")
    private BigDecimal money;

    @ApiModelProperty(value = "未完成打码量")
    private BigDecimal unfinishedCode;

    @ApiModelProperty(value = "洗码额")
    private BigDecimal washCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public BigDecimal getMoney() {
        return keepDecimal(money);
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public BigDecimal getUnfinishedCode() {
        return keepDecimal(unfinishedCode);
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public BigDecimal getWashCode() {
        return keepDecimal(washCode);
    }

    private BigDecimal keepDecimal(BigDecimal val) {
        return val == null ? BigDecimal.ZERO.setScale(2) : val.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
