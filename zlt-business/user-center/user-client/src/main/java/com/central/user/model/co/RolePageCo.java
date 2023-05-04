package com.central.user.model.co;

import com.central.common.model.co.PageCo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class RolePageCo extends PageCo {

    @ApiModelProperty(value = "角色名称", required = false)
    private String name;

    private String searchKey;

}
