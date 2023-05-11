package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

/**
 * 彩种下注分类
 *
 * @author zlt
 * @date 2023-05-11 18:50:09
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("mks_site_category_lottery")
public class SiteCategoryLottery extends SuperEntity {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "站点分类ID")
    private Long categoryId;
    @ApiModelProperty(value = "站点彩种ID")
    private Long siteLotteryId;
    }
