package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.ChannelMapper;
import org.entity.dto.Channel;
import org.entity.dto.ChannelExample;
import org.service.weixin.read.ChannelWxServiceRead;
import org.springframework.stereotype.Service;

@Service("channelWxServiceRead")
public class ChannelWxServiceReadImpl implements ChannelWxServiceRead {

	@Resource
	ChannelMapper channelMapper;

	@Override
	public Channel findById(Long adminChannelId) throws Exception {
		// TODO Auto-generated method stub
		return channelMapper.selectByPrimaryKey(adminChannelId);
	}

	@Override
	public List<Channel> findSonChannels(Long channelId) throws Exception {
		// TODO Auto-generated method stub
		ChannelExample channelExample = new ChannelExample();
		ChannelExample.Criteria criteria = channelExample.createCriteria();
		criteria.andChannelParentIdEqualTo(channelId);
		criteria.andChannelStateEqualTo(1);//使用中
		return channelMapper.selectByExample(channelExample);
	}
}
