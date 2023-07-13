package com.xxl.job.executor.entity.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

/**
 * 彩种表
 *
 * @author zlt
 * @date 2023-05-09 19:57:30
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class SiteLotteryVO {
    private Long id;
    private static final long serialVersionUID=1L;
    private Integer lotteryId;//彩种ID
    private Integer siteId;//站点id
    private String siteCode;//站点编码
    private String siteName;//站点名称
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private String createBy;//创建人
    private String updateBy;//更新人
    private Integer sort;//排序
    private String lotteryName;//彩种名称
    private String picture;//图片地址
    private Time betDeadlineTime;//下注截止时间
    private Time betSettlementTime;//结算完成时间
    private Integer isDisplay;//是否显示(0隐藏，1显示)
    private Integer status;//是否结算中(0否，1结算中)
    }
