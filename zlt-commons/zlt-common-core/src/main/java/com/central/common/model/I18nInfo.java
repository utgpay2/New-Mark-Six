package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 
 *
 * @author lance
 * @date 2022-01-24 21:19:58
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("i18n_info")
public class I18nInfo extends SuperEntity {

    private static final long serialVersionUID = 425485278578578L;

    @ApiModelProperty(value = "所属 0=前台PC，1=后台 2=前台移动端 3=前台错误消息 4=后台错误消息")
    private Integer fromOf;

    @ApiModelProperty(value = "中文-简体")
    private String zh;

    @ApiModelProperty(value = "英语-美国")
    private String en;

    @ApiModelProperty(value = "高棉语")
    private String kh;

    @ApiModelProperty(value = "泰语")
    private String th;

    @ApiModelProperty(value = "越南语")
    private String vi;

    @ApiModelProperty(value = "马来语")
    private String my;

    @ApiModelProperty(value = "操作人")
    private String operator;

    @ApiModelProperty(value = "操作时间")
    private Date updateTime;
}
