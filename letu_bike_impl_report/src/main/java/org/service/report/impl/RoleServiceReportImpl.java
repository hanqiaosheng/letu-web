package org.service.report.impl;

import java.util.List;

import javax.annotation.Resource;

import org.dao.AdminToRoleMapper;
import org.dao.RoleMapper;
import org.dao.RoleToPermissionMapper;
import org.entity.dto.AdminToRoleExample;
import org.entity.dto.AdminToRoleKey;
import org.entity.dto.Role;
import org.entity.dto.RoleExample;
import org.entity.dto.RoleToPermissionExample;
import org.entity.dto.RoleToPermissionKey;
import org.service.report.RoleServiceReport;
import org.springframework.stereotype.Service;
import org.util.PageUtil;


@Service("roleServiceReport")
public class RoleServiceReportImpl implements RoleServiceReport {
	
	@Resource
	AdminToRoleMapper adminToRoleMapper;
	
	@Resource
	RoleMapper roleMapper;
	
	@Resource
	RoleToPermissionMapper roleToPermissionMapper;
	
	@Override
	public List<AdminToRoleKey> findKeyByAId(Long adminId) throws Exception {
		// TODO Auto-generated method stub
		AdminToRoleExample adminToRoleExample = new AdminToRoleExample();
		AdminToRoleExample.Criteria criteria = adminToRoleExample.createCriteria();
		criteria.andAdminIdEqualTo(adminId);
		return adminToRoleMapper.selectByExample(adminToRoleExample);
	}

	@Override
	public Role findById(Long roleId) throws Exception {
		// TODO Auto-generated method stub
		return roleMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public List<Role> findAllRole(Integer pageIndex) throws Exception {
		// TODO Auto-generated method stub
		RoleExample example = new RoleExample();
		RoleExample.Criteria criteria = example.createCriteria();
		criteria.andRoleStateNotEqualTo(-1);
		if(null != pageIndex){
			example.setLimitStart(PageUtil.getStart(pageIndex));
			example.setLimitEnd(PageUtil.size);
		}	
		List<Role> roLeist = roleMapper.selectByExample(example);
		return roLeist;
	}

	@Override
	public Integer findTotalPage() throws Exception {
		// TODO Auto-generated method stub
		RoleExample example = new RoleExample();
		RoleExample.Criteria criteria = example.createCriteria();
		criteria.andRoleStateNotEqualTo(-1);
		int pageCount = roleMapper.countByExample(example);
		Integer totalPage = PageUtil.getTotalPage(pageCount);
		return totalPage;
	}

	@Override
	public List<Role> findUsefulRole(Integer level) throws Exception {
		// TODO Auto-generated method stub
		RoleExample example = new RoleExample();
		RoleExample.Criteria criteria = example.createCriteria();
		criteria.andRoleStateNotEqualTo(-1);//未被删除
		if(level!=null){
			if(level==1){
				criteria.andRoleStateNotEqualTo(2);//一级渠道可使用
			}else if(level==2){
				criteria.andRoleStateEqualTo(1);//二级渠道可使用
			}
		}
			
		List<Role> list = roleMapper.selectByExample(example);
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
			
	}

	@Override
	public List<RoleToPermissionKey> findByRId(Long roleId) throws Exception {
		// TODO Auto-generated method stub
		RoleToPermissionExample example = new RoleToPermissionExample();
		RoleToPermissionExample.Criteria criteria = example.createCriteria();
		//角色id
		criteria.andRoleIdEqualTo(roleId);
		return roleToPermissionMapper.selectByExample(example);
	}

	@Override
	public Role findByRName(String rname) throws Exception {
		// TODO Auto-generated method stub
		RoleExample roleExample = new RoleExample();
		RoleExample.Criteria criteria = roleExample.createCriteria();
		//角色名
		criteria.andRoleNameEqualTo(rname);
		List<Role> roles = roleMapper.selectByExample(roleExample);
		if(roles.size()>0){
			return roles.get(0);
		}
		return null;
	}

}
