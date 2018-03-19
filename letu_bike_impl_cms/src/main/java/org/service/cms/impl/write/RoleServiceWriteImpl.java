package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.AdminMapper;
import org.dao.AdminToRoleMapper;
import org.dao.RoleMapper;
import org.dao.RoleToPermissionMapper;
import org.entity.dto.AdminToRoleExample;
import org.entity.dto.AdminToRoleKey;
import org.entity.dto.Role;
import org.entity.dto.RoleToPermissionExample;
import org.entity.dto.RoleToPermissionKey;
import org.service.cms.write.RoleServiceWrite;
import org.springframework.stereotype.Service;
import org.util.DateUtil;


@Service("roleServiceWrite")
public class RoleServiceWriteImpl implements RoleServiceWrite {

	@Resource
	RoleMapper roleMapper;
	@Resource
	AdminToRoleMapper adminToRoleMapper;
	@Resource
	AdminMapper adminMapper;
	@Resource
	RoleToPermissionMapper roleToPermissionMapper;
	
	
	@Override
	public void addAdminToRole(AdminToRoleKey adminToRoleKey) throws Exception {
		// TODO Auto-generated method stub
		adminToRoleMapper.insertSelective(adminToRoleKey);
	}



	@Override
	public void deleteAtoRyAId(Long adminId) throws Exception {
		// TODO Auto-generated method stub
		AdminToRoleExample adminToRoleExample = new AdminToRoleExample();
		AdminToRoleExample.Criteria criteria = adminToRoleExample.createCriteria();
		criteria.andAdminIdEqualTo(adminId);
		adminToRoleMapper.deleteByExample(adminToRoleExample);
		
	}



	@Override
	public Long addRole(Role role) throws Exception {
		// TODO Auto-generated method stub
		role.setRoleCreatetime(DateUtil.getNowTime());
		roleMapper.insertSelective(role);
		Long roleId = role.getRoleId();//返回主键
		return roleId;
	}



	@Override
	public void addRolePermission(RoleToPermissionKey rolePermission) throws Exception {
		// TODO Auto-generated method stub
		roleToPermissionMapper.insertSelective(rolePermission);
	}



	@Override
	public void deleteRtoP(Long roleId) throws Exception {
		// TODO Auto-generated method stub
		RoleToPermissionExample example = new RoleToPermissionExample();
		RoleToPermissionExample.Criteria criteria = example.createCriteria();
		//角色id
		criteria.andRoleIdEqualTo(roleId);
		roleToPermissionMapper.deleteByExample(example);
		
	}



	@Override
	public boolean updateRole(Role role) throws Exception {
		// TODO Auto-generated method stub
		role.setRoleUpdatetime(DateUtil.getNowTime());
		int row = roleMapper.updateByPrimaryKeySelective(role);
		return 1==row;
	}
	
	
	@Override
	public void deleteRole(Role role) throws Exception {
		// TODO Auto-generated method stub
		roleMapper.updateByPrimaryKeySelective(role);
	}
	
	
	
	
}
