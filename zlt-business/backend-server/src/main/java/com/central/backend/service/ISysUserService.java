package com.central.backend.service;

import com.central.backend.vo.SysUserInfoMoneyVo;
import com.central.backend.vo.UserExtensionListInfoVo;
import com.central.backend.vo.UserListInfoVo;
import com.central.backend.vo.UserVipExpireVo;
import com.central.common.model.*;
import com.central.common.service.ISuperService;
import org.apache.ibatis.annotations.Param;
import com.central.backend.co.*;

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
	PageResult<SysUser> findList(Map<String, Object> params, SysUser user);
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

	Result updateRemark(Long id,String remark);

	/**
	 * 查询最近上线列表
	 * @return
	 */
	List<UserListInfoVo> onlineList(String merchantCode);

	/**
	 * 用户角色列表
	 * @param userId
	 * @return
	 */
	List<SysRole> findRolesByUserId(Long userId);

	/**
	 * 状态变更
	 * @param params
	 * @return
	 */
	Result updateEnabled(EnabledUserCo params);

	Result saveOrUpdateUser(SysUser sysUser) throws Exception;

	/**
	 * 删除用户
	 */
	boolean delUser(Long id);

	SysUser selectById(Long id);

	/**
	 * 清除缓存
	 * @param sysUser
	 */
	void cacheEvictUser(SysUser sysUser);

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



	/**
	 * 查询会员管理列表
	 * @param params
	 * @return SysUser
	 * @Author: Lulu
	 */
	PageResult<SysUser> findUserList(SysUserCo params);

	Result saveOrUpdateUserInfo( SysUser user);


	/**
	 * 修改会员vip到期时间
	 * @param userId
	 * @Author: Lulu
	 */
	 Result updateUserVipExpire(Long userId,Integer days) ;

	PageResult<UserExtensionListInfoVo> findUserExtensionList(SysUserExtensionCo params) ;

}
