package com.central.user.service;

import com.central.common.model.*;
import com.central.common.service.ISuperService;
import com.central.user.model.co.GaBindCo;
import com.central.user.model.co.SysUserListCo;
import com.central.user.model.co.UserListCo;
import com.central.user.model.vo.SysUserInfoMoneyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zlt
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
public interface ISysUserService extends ISuperService<SysUser> {
	/**
	 * 获取UserDetails对象
	 * @param username
	 * @return
	 */
	LoginAppUser findByUsername(String username);

	LoginAppUser findByOpenId(String username);

	LoginAppUser findByMobile(String username);

	/**
	 * 通过SysUser 转换为 LoginAppUser，把roles和permissions也查询出来
	 * @param sysUser
	 * @return
	 */
	LoginAppUser getLoginAppUser(SysUser sysUser);

	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	SysUser selectByUsername(String username);
	/**
	 * 根据手机号查询用户
	 * @param mobile
	 * @return
	 */
	SysUser selectByMobile(String mobile);
	/**
	 * 根据openId查询用户
	 * @param openId
	 * @return
	 */
	SysUser selectByOpenId(String openId);

	/**
	 * 用户分配角色
	 * @param id
	 * @param roleIds
	 */
	void setRoleToUser(Long id, Set<Long> roleIds);

	/**
	 * 更新密码
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	Result updatePassword(Long id, String oldPassword, String newPassword);

	/**
	 * 用户列表
	 * @param params
	 * @return
	 */
	PageResult<SysUser> findUsers(SysUserListCo params);

	/**
	 * 用户角色列表
	 * @param userId
	 * @return
	 */
	List<SysRole> findRolesByUserId(Long userId);

	Result saveOrUpdateUser(SysUser sysUser) throws Exception;

	/**
	 * 删除用户
	 */
	boolean delUser(Long id);

	SysUser selectById(Long id);

	 String resetUpdatePassword(Long id) ;

	/**
	 * 二维码code变更
	 * @param params
	 * @return
	 */
	Result updateGaKey(Map<String, Object> params);

	/**
	 * 二维码绑定状态变更
	 * @param params
	 * @return
	 */
	Result updateGaBind(GaBindCo params);

	/**
	 * 谷歌验证码是否校验状态修改
	 * @param id
	 * @return
	 */
	Result updateVerify(Long id);

	Integer findUserNum(@Param("p") Map<String, Object> params);

	List<SysUser> findListByIds(List<Long> ids);

	List<SysUserInfoMoneyVo> findListByUserIds(List<Long> userIdList);
}
