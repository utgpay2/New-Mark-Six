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
@ApiModel("用户信息及余额")
public class SysUserInfoMoneyVo {

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "头像")
    private String headImgUrl;

    @ApiModelProperty(value = "余额")
    private BigDecimal money;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public BigDecimal getMoney() {
        return keepDecimal(money);
    }

    private BigDecimal keepDecimal(BigDecimal val) {
        return val == null ? BigDecimal.ZERO.setScale(2) : val.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
