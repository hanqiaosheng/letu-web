package org.service.weixin.write;

import org.entity.dto.Message;

public interface MessageWxServiceWrite {

	/**
	 * 新增消息
	 * @param message
	 * @throws Exception
	 */
	public void addMessage(Message message) throws Exception;

}
