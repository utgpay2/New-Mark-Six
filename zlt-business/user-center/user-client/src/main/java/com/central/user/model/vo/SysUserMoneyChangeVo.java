package com.central.user.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户钱包表
 *
 * @author zlt
 * @date 2021-12-03 19:31:47
 */
@Data
@ApiModel("额度记录")
public class SysUserMoneyChangeVo {

    @ApiModelProperty(value = "额度")
    private String amount;

    @ApiModelProperty(value = "交易前额度")
    private String amountBefore;

    @ApiModelProperty(value = "交易后额度")
    private String amountAfter;

    @ApiModelProperty(value = "1:转入,2:转出,3:派彩,4:下注,5:手动入款,6:手动出款")
    private String orderType;

    @ApiModelProperty(value = "时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
}
