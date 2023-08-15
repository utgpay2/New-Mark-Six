package com.proxy.center.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.proxy.center.vo.SysUserInfoMoneyVo;
import com.proxy.center.vo.UserExtensionListInfoVo;
import com.proxy.center.vo.UserListInfoVo;
import com.central.common.model.SysUser;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.proxy.center.co.*;

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
     * 查询最近上线列表
     * @return
     */
    List<UserListInfoVo> onlineList(@Param("merchantCode") String merchantCode);

    Integer findUserNum(@Param("p") Map<String, Object> params);

    List<SysUser> findListByIds(@Param("ids") List<Long> ids);

    List<SysUserInfoMoneyVo> findListByUserIds(@Param("userIdList") List<Long> userIdList);


    Integer findMonthNumberInfo(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("date") String date, @Param("merchantCode") String merchantCode);


    List<UserExtensionListInfoVo> findUserExtensionList(Page<UserExtensionListInfoVo> page, @Param("r") SysUserExtensionCo params);



    SysUser getMerchantAdministrator(@Param("siteCode") String siteCode);

    List<Map> getProxyBySiteCode(@Param("siteCode") String siteCode);
}
