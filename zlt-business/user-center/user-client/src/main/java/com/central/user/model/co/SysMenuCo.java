package com.central.user.model.co;

import com.central.common.model.SysMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@ApiModel
public class SysMenuCo {

    private Long id;

    private Long parentId;
    @ApiModelProperty(value = "菜单名称")
    private String name;
    private String css;
    private String url;
    private String path;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    private Integer type;
    private Boolean hidden;
    /**
     * 请求的类型
     */
    private String pathMethod;

    private List<SysMenu> subMenus;

    private Long roleId;

    private Set<Long> menuIds;
}
