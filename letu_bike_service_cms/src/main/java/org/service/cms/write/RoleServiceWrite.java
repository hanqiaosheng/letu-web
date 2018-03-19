package org.service.cms.write;

import org.entity.dto.AdminToRoleKey;
import org.entity.dto.Role;
import org.entity.dto.RoleToPermissionKey;

public interface RoleServiceWrite {
	/**
	 * 增加管理员和角色的关联表
	 * @param adminToRoleKey
	 * @throws Exception
	 */
	public void addAdminToRole(AdminToRoleKey adminToRoleKey) throws Exception;
	
	/**
	 * 删除管理员和角色的关联数据
	 * @param adminId
	 * @throws Exception
	 */
	public void deleteAtoRyAId(Long adminId) throws Exception;
	/**
	 * 新增角色
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public Long addRole(Role role) throws Exception;
	
	/**
	 * 新增角色和权限关联表
	 * @param rolePermission
	 * @throws Exception
	 */
	public void addRolePermission(RoleToPermissionKey rolePermission) throws Exception;
	
	/**
	 * 根据角色id删除 角色和权限关联数据
	 * @param roleId
	 * @throws Exception
	 */
	public void deleteRtoP(Long roleId) throws Exception;

	/**
	 * 更新角色
	 * @param role
	 * @throws Exception
	 */
	public boolean updateRole(Role role) throws Exception;
	
	/**
	 * 删除角色
	 * @param role
	 * @throws Exception
	 */
	public void deleteRole(Role role) throws Exception;

}
