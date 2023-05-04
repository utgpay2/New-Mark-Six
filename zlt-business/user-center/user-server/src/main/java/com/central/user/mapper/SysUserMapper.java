package com.central.user.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.SysUser;
import com.central.db.mapper.SuperMapper;
import com.central.user.model.co.SysUserListCo;
import com.central.user.model.co.UserListCo;
import com.central.user.model.vo.SysUserInfoMoneyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户表 Mapper 接口
 *
 * @author zlt
 * @data 2018-10-29
 */
@Mapper
public interface SysUserMapper extends SuperMapper<SysUser> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<SysUser> findList(Page<SysUser> page, @Param("u") SysUserListCo params);

    List<SysUser> adminFindList(Page<SysUser> page, @Param("u") SysUserListCo params);

    Integer findUserNum(@Param("p") Map<String, Object> params);

    List<SysUser> findListByIds(@Param("ids")List<Long> ids);

    List<SysUserInfoMoneyVo> findListByUserIds(@Param("userIdList") List<Long> userIdList);
}
