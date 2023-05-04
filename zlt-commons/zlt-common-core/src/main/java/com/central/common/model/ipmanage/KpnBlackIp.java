package com.central.common.model.ipmanage;

import com.baomidou.mybatisplus.annotation.TableName;
import com.central.common.model.SuperEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 
 *
 * @author yixiu
 * @date 2023-02-03 15:50:11
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_black_ip")
public class KpnBlackIp extends SuperEntity {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "会员黑名单ip段")
    private String ipSection;
    @ApiModelProperty(value = "站点id")
    private Integer siteId;
    @ApiModelProperty(value = "站点编码")
    private String siteCode;
    @ApiModelProperty(value = "站点名称")
    private String siteName;
    @ApiModelProperty(value = "备注")
    private String remark;
    }
