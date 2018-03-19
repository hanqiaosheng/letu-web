package org.service.cms.read;

import java.util.List;

import org.entity.dto.Permission;
import org.entity.dto.RoleToPermissionKey;

public interface PermissionServiceRead {
	/**
	 * 根据角色id查关联的权限
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<RoleToPermissionKey> findByRId(Long roleId) throws Exception;

	/**
	 * 根据权限id查权限
	 * @param permisssionId
	 * @return
	 * @throws Exception
	 */
	public Permission findByPId(Long permissionId) throws Exception;

	/**
	 * 查询所有权限
	 * @return
	 * @throws Exception
	 */
	public List<Permission> findAllPermisssion() throws Exception;
	/**
	 * 根据数据字典的key来查找属于该权限类别下的子权限
	 * @param dataDetKey
	 * @return
	 * @throws Exception
	 */
	public List<Permission> findPermissionByTypeId(Long key) throws Exception;

	/**
	 * 根据权限父类id查找所有子权限
	 * @param permissionId
	 * @return
	 * @throws Exception
	 */
	public List<Permission> findAllPermissionsByPid(Long permissionId) throws Exception;

}
