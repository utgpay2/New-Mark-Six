package com.central.porn.entity;

import com.central.common.model.PageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分页查询")
public class PornPageResult<T> {
    @ApiModelProperty("当前页")
    public Integer currPage;

    @ApiModelProperty("每页条数")
    public Integer pageSize;

    @ApiModelProperty("总页数")
    public Integer totalPage;

    @ApiModelProperty("总条数")
    public Long count;

    /**
     * 当前页结果集
     */
    @ApiModelProperty("当前页结果集")
    public List<T> data;

}
