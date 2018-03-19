package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.AdminMapper;
import org.entity.dto.Admin;
import org.service.cms.write.AdminServiceWrite;
import org.springframework.stereotype.Service;
import org.util.DateUtil;

@Service("adminServiceWrite")
public class AdminServiceWriteImpl implements AdminServiceWrite {

	
	@Resource
	AdminMapper adminMapper;

	@Override
	public Long addAmin(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		admin.setAdminRegTime(DateUtil.getNowTime());
		adminMapper.insertSelective(admin);
		return admin.getAdminId();
		
	}

	@Override
	public boolean updateAdmin(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		if(admin.getAdminChannelId()==-1){
			admin.setAdminChannelId(null);
		}
		int row = adminMapper.updateByPrimaryKeySelective(admin);
		return 1==row;
	}

	@Override
	public Long addAminCanNull(Admin admin) throws Exception {
		admin.setAdminRegTime(DateUtil.getNowTime());
		if(admin.getAdminChannelId()!=null && admin.getAdminChannelId()==-1){
			admin.setAdminChannelId(null);
		}
		adminMapper.insert(admin);
		return admin.getAdminId();
	}
	
	
	
}
