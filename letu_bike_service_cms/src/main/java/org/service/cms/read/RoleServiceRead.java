package org.service.cms.read;

import java.util.List;

import org.entity.dto.AdminToRoleKey;
import org.entity.dto.Role;
import org.entity.dto.RoleToPermissionKey;

	/**
	 * 角色接口
	 * @author Administrator
	 *
	 */
public interface RoleServiceRead {
	/**
	 * 根据adminid查找管理员
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public List<AdminToRoleKey> findKeyByAId(Long adminId) throws Exception;
	/**
	 * 根据角色id查角色
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public Role findById(Long roleId) throws Exception;
	
	
	/**
	 * 查询所有角色
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	public List<Role> findAllRole(Integer pageIndex) throws Exception;
	
	/**
	 * 更新角色信息
	 * @param adminRole
	 * @throws Exception
	 */
	public Integer findTotalPage() throws Exception;
	
	/**
	 * 查找所有 有用的角色不含冻结
	 * @return
	 * @throws Exception
	 */
	public List<Role> findUsefulRole(Integer level) throws Exception;
	/**
	 * 根据角色id查找关联表数据
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<RoleToPermissionKey> findByRId(Long roleId) throws Exception;
	/**
	 * 根据名字来查询角色是否重复
	 * @param rname
	 * @return
	 * @throws Exception
	 */
	public Role findByRName(String rname) throws Exception;

}
