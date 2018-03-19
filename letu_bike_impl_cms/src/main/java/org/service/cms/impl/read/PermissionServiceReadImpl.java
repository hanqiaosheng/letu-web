package org.service.cms.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.PermissionMapper;
import org.dao.RoleToPermissionMapper;
import org.entity.dto.Permission;
import org.entity.dto.PermissionExample;
import org.entity.dto.RoleToPermissionExample;
import org.entity.dto.RoleToPermissionKey;
import org.service.cms.read.PermissionServiceRead;
import org.springframework.stereotype.Service;

@Service("permissionServiceRead")
public class PermissionServiceReadImpl implements PermissionServiceRead {

	@Resource
	PermissionMapper permissionMapper;
	@Resource
	RoleToPermissionMapper roleToPermissionMapper;
	
	@Override
	public List<RoleToPermissionKey> findByRId(Long roleId) throws Exception {
		// TODO Auto-generated method stub
		RoleToPermissionExample permissionExample = new RoleToPermissionExample();
		RoleToPermissionExample.Criteria criteria = permissionExample.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		return roleToPermissionMapper.selectByExample(permissionExample);
	}

	@Override
	public Permission findByPId(Long permissionId) throws Exception {
		// TODO Auto-generated method stub
		return permissionMapper.selectByPrimaryKey(permissionId);
	}

	@Override
	public List<Permission> findAllPermisssion() throws Exception {
		// TODO Auto-generated method stub\
		List<Permission> list = permissionMapper.selectByExample(null);
		return list;
	}

	@Override
	public List<Permission> findPermissionByTypeId(Long dataDetKey) throws Exception {
		// TODO Auto-generated method stub
		PermissionExample permissionExample = new PermissionExample();
		PermissionExample.Criteria criteria = permissionExample.createCriteria();
		criteria.andPermissionTypeIdEqualTo(dataDetKey);
		return permissionMapper.selectByExample(permissionExample);
	}

	@Override
	public List<Permission> findAllPermissionsByPid(Long permissionId) throws Exception {
		// TODO Auto-generated method stub
		PermissionExample permissionExample = new PermissionExample();
		PermissionExample.Criteria criteria = permissionExample.createCriteria();
		criteria.andPermissionParentIdEqualTo(permissionId);
		return permissionMapper.selectByExample(permissionExample);
	}

}
