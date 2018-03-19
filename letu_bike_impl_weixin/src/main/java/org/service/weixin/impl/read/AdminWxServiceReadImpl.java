package org.service.weixin.impl.read;

import javax.annotation.Resource;

import org.dao.AdminMapper;
import org.entity.dto.Admin;
import org.service.weixin.read.AdminWxServiceRead;
import org.springframework.stereotype.Service;

@Service("adminWxServiceRead")
public class AdminWxServiceReadImpl implements AdminWxServiceRead {

	
	@Resource
	AdminMapper adminMapper;

	@Override
	public Admin findById(Long insuranceAdminId) throws Exception {
		// TODO Auto-generated method stub
		return adminMapper.selectByPrimaryKey(insuranceAdminId);
	}
}
