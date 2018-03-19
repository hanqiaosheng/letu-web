package org.service.cms.write;

import org.entity.dto.Channel;

public interface ChannelServiceWrite {
	/**
	 * 新增渠道
	 * @param channel
	 * @throws Exception
	 */
	public Long addChannel(Channel channel) throws Exception;
	
	/**
	 * 修改渠道
	 * @param channel
	 * @throws Exception
	 */
	public void update(Channel channel) throws Exception;

}
