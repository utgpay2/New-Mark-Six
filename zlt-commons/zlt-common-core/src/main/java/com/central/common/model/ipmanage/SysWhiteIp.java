package com.central.common.model.ipmanage;

import com.central.common.model.SuperEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 
 *
 * @author yixiu
 * @date 2023-02-03 15:07:56
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("sys_white_ip")
public class SysWhiteIp extends SuperEntity {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "后台白名单")
    private String ip;
    @ApiModelProperty(value = "备注")
    private String remark;
    }
