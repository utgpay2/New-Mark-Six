package com.central.backend.co;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class SysMenuDistributionCo {


    private Long roleId;

    private List<Long> menuIds;
}
