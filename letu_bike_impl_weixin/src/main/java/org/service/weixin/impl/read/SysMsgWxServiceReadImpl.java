package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.SysMsgMapper;
import org.entity.dto.SysMsg;
import org.entity.dto.SysMsgExample;
import org.service.weixin.read.SysMsgWxServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;

@Service("sysMsgWxServiceRead")
public class SysMsgWxServiceReadImpl implements SysMsgWxServiceRead {

	@Resource
	SysMsgMapper sysMsgMapper;
	
	
	@Override
	public List<SysMsg> findAllSysMsg(Integer pageIndex, Integer page_size_weixin) throws Exception {
		// TODO Auto-generated method stub
		SysMsgExample example = new SysMsgExample();
		SysMsgExample.Criteria criteria = example.createCriteria();
		//上线
		criteria.andSysMsgIsonlineEqualTo((short) 1);
		if(null!=pageIndex){
			example.setLimitStart(PageUtil.getStart(pageIndex));
			example.setLimitEnd(page_size_weixin);
		}
		
		example.setOrderByClause("sys_msg_onlinetime desc");
		return sysMsgMapper.selectByExample(example);
	}

	@Override
	public Integer findAllSysMsgCount() throws Exception {
		// TODO Auto-generated method stub\
		SysMsgExample example = new SysMsgExample();
		SysMsgExample.Criteria criteria = example.createCriteria();
		//上线
		criteria.andSysMsgIsonlineEqualTo((short) 1);
		return sysMsgMapper.countByExample(example);
	}

	@Override
	public SysMsg findBySId(Long sysMsgId) throws Exception {
		// TODO Auto-generated method stub
		return sysMsgMapper.selectByPrimaryKey(sysMsgId);
	}

}
