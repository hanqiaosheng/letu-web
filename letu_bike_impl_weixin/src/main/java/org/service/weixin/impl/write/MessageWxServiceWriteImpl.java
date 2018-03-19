package org.service.weixin.impl.write;

import javax.annotation.Resource;

import org.dao.MessageMapper;
import org.entity.dto.Message;
import org.service.weixin.write.MessageWxServiceWrite;
import org.springframework.stereotype.Service;


@Service("messageWxServiceWrite")
public class MessageWxServiceWriteImpl implements MessageWxServiceWrite {

	@Resource
	MessageMapper messageMapper;
	
	
	
	@Override
	public void addMessage(Message message) throws Exception {
		// TODO Auto-generated method stub
		messageMapper.insertSelective(message);
	}

}
