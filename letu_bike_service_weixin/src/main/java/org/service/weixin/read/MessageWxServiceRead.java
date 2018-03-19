package org.service.weixin.read;

import java.util.List;

import org.entity.dto.Message;

public interface MessageWxServiceRead {
	/**
	 * 查找用户的消息
	 * @param pageIndex
	 * @param page_size_weixin
	 * @param insuranceId 
	 * @return
	 * @throws Exception
	 */
	public List<Message> findMessageByUserId(Long userId,String code,Integer pageIndex, Integer page_size_weixin, Long insuranceId) throws Exception;

	/**
	 * 查找用户所有消息条数
	 * @param code 
	 * @param insuranceId 
	 * @return
	 * @throws Exception
	 */
	public Integer findUserMessageCount(Long userId, String code, Long insuranceId) throws Exception;
	/**
	 * 根据id查消息
	 * @param sysMsgId
	 * @return
	 * @throws Exception
	 */
	public Message findByMId(Long messageId) throws Exception;

}
