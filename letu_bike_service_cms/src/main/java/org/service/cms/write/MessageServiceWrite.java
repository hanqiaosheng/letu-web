package org.service.cms.write;

import org.entity.dto.Message;

public interface MessageServiceWrite {
	
	/**
	 * 新增消息
	 * @param message
	 * @throws Exception
	 */
	public void addMessage(Message message) throws Exception;

}
