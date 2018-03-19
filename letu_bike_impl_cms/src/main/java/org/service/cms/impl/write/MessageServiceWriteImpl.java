package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.MessageMapper;
import org.entity.dto.Message;
import org.service.cms.write.MessageServiceWrite;
import org.springframework.stereotype.Service;


@Service("messageServiceWrite")
public class MessageServiceWriteImpl implements MessageServiceWrite {
	
	@Resource
	MessageMapper messageMapper;
	
	
	
	@Override
	public void addMessage(Message message) throws Exception {
		// TODO Auto-generated method stub
		messageMapper.insertSelective(message);
	}

}
