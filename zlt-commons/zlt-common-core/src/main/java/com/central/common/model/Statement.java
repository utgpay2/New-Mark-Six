package com.central.common.model;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 重要声明表
 *
 * @author zlt
 * @date 2023-05-09 19:57:30
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("mks_statement")
public class Statement extends SuperEntity{

    @ApiModelProperty(value = "声明")
    private String statement;
}
