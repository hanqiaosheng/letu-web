package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.ChannelMapper;
import org.entity.dto.Channel;
import org.service.cms.write.ChannelServiceWrite;
import org.springframework.stereotype.Service;


@Service("channelServiceWrite")
public class ChannelServiceWriteImpl implements ChannelServiceWrite {

	@Resource
	ChannelMapper channelMapper;

	@Override
	public Long addChannel(Channel channel) throws Exception {
		// TODO Auto-generated method stub
		channelMapper.insertSelective(channel);
		return channel.getChannelId();
	}

	@Override
	public void update(Channel channel) throws Exception {
		// TODO Auto-generated method stub
		channelMapper.updateByPrimaryKeySelective(channel);
	}
	
	
}
