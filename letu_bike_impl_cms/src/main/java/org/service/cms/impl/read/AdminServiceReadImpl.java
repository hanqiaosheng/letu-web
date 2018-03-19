package org.service.cms.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.entity.dto.Admin;
import org.entity.dto.AdminExample;
import org.dao.AdminMapper;
import org.dao.AdminToRoleMapper;
import org.dao.RoleMapper;
import org.service.cms.read.AdminServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;

@Service("adminServiceRead")
public class AdminServiceReadImpl implements AdminServiceRead {
	
	@Resource
	AdminMapper adminMapper;
	
	@Resource
	RoleMapper RoleMapper;
	
	@Resource
	AdminToRoleMapper adminToRoleMapper;
	
	
	
	@Override
	public Admin findByName(String adminname) throws Exception {
		// TODO Auto-generated method stub
		AdminExample adminExample = new AdminExample();
		AdminExample.Criteria criteria =adminExample.createCriteria();
		criteria.andAdminUsernameEqualTo(adminname);
		//在使用中
		criteria.andAdminStateEqualTo(1);
		List<Admin> admins = adminMapper.selectByExample(adminExample);
		if(admins.size()>0){
			return admins.get(0);
		}
		return null;
		
	}


	@Override
	public Admin findAdminId(Long adminId) throws Exception {
		// TODO Auto-generated method stub
		return adminMapper.selectUnionRoleByPrimaryKey(adminId);
	}


	@Override
	public List<Admin> findAllAdmin(Integer pageIndex, Integer pageSize,String adminname, Long roleId,List<Long> channelIds) throws Exception {
		// TODO Auto-generated method stub
		AdminExample adminExample = new AdminExample();
		AdminExample.Criteria criteria = adminExample.createCriteria();
		//管理员用户名
		if(null!=adminname&&!"".equals(adminname)){
			criteria.andAdminUsernameLike("%"+adminname.trim()+"%");
		}
		//角色Id
		if(null!=roleId&&!"".equals(roleId)){
			criteria.andAdminRoleIdEqualTo(roleId);
		}
		if(null!=channelIds){
			criteria.andAdminChannelIdIn(channelIds);
		}
		criteria.andAdminStateNotEqualTo(-1);
		adminExample.setOrderByClause("admin_reg_time desc");
		if(pageIndex!=null){
			adminExample.setLimitStart(PageUtil.getStart(pageIndex));
			adminExample.setLimitEnd(pageSize);
		}
		return adminMapper.selectUnionByExample(adminExample);
	}



	@Override
	public Integer pageCount(String adminname, Long roleId,List<Long> channelIds) throws Exception {
		// TODO Auto-generated method stub
		AdminExample adminExample = new AdminExample();
		AdminExample.Criteria criteria = adminExample.createCriteria();
		criteria.andAdminStateNotEqualTo(-1);
		//管理员用户名
		if(null!=adminname&&!"".equals(adminname)){
			criteria.andAdminUsernameLike("%"+adminname.trim()+"%");
		}
		if(null!=channelIds){
			criteria.andAdminChannelIdIn(channelIds);
		}
		//角色Id
		if(null!=roleId&&!"".equals(roleId)){
			criteria.andAdminRoleIdEqualTo(roleId);
		}
		List<Admin> list = adminMapper.selectUnionByExample(adminExample);
		if(list.size()>0){
			return list.size();
		}else{
			return 0;
		}
	}


	@Override
	public List<Admin> findByChannelId(Long ChannelId) throws Exception {
		// TODO Auto-generated method stub
		AdminExample adminExample = new AdminExample();
		AdminExample.Criteria criteria = adminExample.createCriteria();
		criteria.andAdminStateEqualTo(1);
		criteria.andAdminChannelIdEqualTo(ChannelId);
		return adminMapper.selectUnionByExample(adminExample);
		
	}


	@Override
	public List<Admin> findByRealName(String adminRealName) throws Exception {
		// TODO Auto-generated method stub
		AdminExample adminExample = new AdminExample();
		AdminExample.Criteria criteria =adminExample.createCriteria();
		if(null!=adminRealName&&!"".equals(adminRealName)){
			criteria.andAdminRealnameLike("%"+adminRealName+"%");
		}
		//在使用中
		criteria.andAdminStateEqualTo(1);
		return adminMapper.selectByExample(adminExample);
	}

	@Override
	public Admin findByTel(String tel) {
		AdminExample adminExample = new AdminExample();
		AdminExample.Criteria criteria =adminExample.createCriteria();
		//todo 现在是比较搓的方法,想办法把唯一键做到mappr中
		if(tel!=null && !"".equals(tel)){
			criteria.andAdminTelEqualTo(tel);
			List<Admin> adminList = adminMapper.selectByExample(adminExample);
			if(adminList.size()!=0){
				return adminList.get(0);
			}
		}
		return null;
	}

	@Override
	public Admin findByIdCard(String idCard) {
		AdminExample adminExample = new AdminExample();
		AdminExample.Criteria criteria =adminExample.createCriteria();
		//todo 现在是比较搓的方法,想办法把唯一键做到mappr中
		if(idCard!=null && !"".equals(idCard)){
			List<Admin> adminList = adminMapper.selectByExample(adminExample);
			if(adminList.size()!=0){
				return adminList.get(0);
			}
		}
		return null;
	}


    @Override
    public Admin findByOpenId(String openId) {
        return adminMapper.selectByOpenId(openId);
    }

	@Override
	public List<Admin> findAllByCondition(Integer pageIndex, Integer pageSize, Admin admin, Long roleId) throws Exception {
		AdminExample adminExample = new AdminExample();
		AdminExample.Criteria criteria =adminExample.createCriteria();

		if(admin!=null) {
			//管理员用户名
			if (null != admin.getAdminUsername() && !"".equals(admin.getAdminUsername())) {
				criteria.andAdminUsernameLike("%" + admin.getAdminUsername().trim() + "%");
			}
			//管理员真实姓名
			if(null != admin.getAdminRealname() && !"".equals(admin.getAdminRealname())){
				criteria.andAdminRealnameLike("%"+admin.getAdminRealname().trim()+"%");
			}
			//管理员电话
			if(null != admin.getAdminTel() && !"".equals(admin.getAdminTel())){
				criteria.andAdminTelEqualTo(admin.getAdminTel());
			}
		}
		//角色Id
		if(null!=roleId&&!"".equals(roleId)){
			criteria.andAdminRoleIdEqualTo(roleId);
		}

		criteria.andAdminStateNotEqualTo(-1);
		adminExample.setOrderByClause("admin_reg_time desc");
		if(pageIndex!=null){
			adminExample.setLimitStart(PageUtil.getStart(pageIndex));
			adminExample.setLimitEnd(pageSize);
		}
		return adminMapper.selectUnionByExample(adminExample);
	}

}
