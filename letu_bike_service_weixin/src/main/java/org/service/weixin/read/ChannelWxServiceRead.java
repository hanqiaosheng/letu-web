package org.service.weixin.read;

import java.util.List;

import org.entity.dto.Channel;

public interface ChannelWxServiceRead {
	
	/**
	 * 根据渠道id查渠道
	 * @param adminChannelId
	 * @return
	 * @throws Exception
	 */
	public Channel findById(Long adminChannelId) throws Exception;
	
	/**
	 * 查找子渠道
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	public List<Channel> findSonChannels(Long channelId) throws Exception;

}
