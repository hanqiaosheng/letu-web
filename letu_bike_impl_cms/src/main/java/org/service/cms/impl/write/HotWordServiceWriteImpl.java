package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.HotWordMapper;
import org.entity.dto.HotWord;
import org.service.cms.write.HotWordServiceWrite;
import org.springframework.stereotype.Service;
@Service("hotWordServiceWrite")
public class HotWordServiceWriteImpl implements HotWordServiceWrite {

	@Resource
	HotWordMapper hotWordMapper;
	
	@Override
	public void add(HotWord hotWord) throws Exception {
		// TODO Auto-generated method stub
		hotWordMapper.insertSelective(hotWord);
	}

	@Override
	public void update(HotWord hotWord) throws Exception {
		// TODO Auto-generated method stub
		hotWordMapper.updateByPrimaryKeySelective(hotWord);
	}
	
}
