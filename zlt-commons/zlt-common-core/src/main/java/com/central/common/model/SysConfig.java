package com.central.common.model;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_config")
@ApiModel("用户实体")
public class SysConfig extends SuperEntity{

    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("访问地址")
    private String url;
    @ApiModelProperty("连接参数")
    private String param;
    @ApiModelProperty("平台类型，H5为1, PC为2")
    private Integer type;
    @ApiModelProperty("权重（权重大,优先访问）1-100")
    private String accessWeight;

}
