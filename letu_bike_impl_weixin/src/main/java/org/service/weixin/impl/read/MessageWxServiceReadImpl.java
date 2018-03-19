package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.MessageMapper;
import org.entity.dto.Message;
import org.entity.dto.MessageExample;
import org.service.weixin.read.MessageWxServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;
@Service("messageWxServiceRead")
public class MessageWxServiceReadImpl implements MessageWxServiceRead {
	
	@Resource
	MessageMapper messageMapper;
	@Override
	public List<Message> findMessageByUserId(Long userId, String code,Integer pageIndex, Integer page_size_weixin,Long insuranceId)
			throws Exception {
		// TODO Auto-generated method stub
		MessageExample example=new MessageExample();
		MessageExample.Criteria criteria=example.createCriteria();
		criteria.andMessageUserIdEqualTo(userId);
		criteria.andMessageIsdelEqualTo(1);
		if(null!=insuranceId){
			criteria.andMessageInsuranceEqualTo(insuranceId);
			example.setOrderByClause("message_send_time asc");
		}else{
			example.setOrderByClause("message_send_time DESC");
		}
		criteria.andTempletCodeEqualTo(code);
		example.setLimitStart(PageUtil.getWeixinStart(pageIndex));
		example.setLimitEnd(page_size_weixin);
		return messageMapper.selectByExample(example);
	}

	@Override
	public Integer findUserMessageCount(Long userId, String code,Long insuranceId) throws Exception {
		// TODO Auto-generated method stub
		MessageExample example=new MessageExample();
		MessageExample.Criteria criteria=example.createCriteria();
		criteria.andTempletCodeEqualTo(code);
		if(null!=insuranceId){
			criteria.andMessageInsuranceEqualTo(insuranceId);
		}
		criteria.andMessageUserIdEqualTo(userId);
		return messageMapper.countByExample(example);
	}

	@Override
	public Message findByMId(Long messageId) throws Exception {
		// TODO Auto-generated method stub
		return messageMapper.selectByPrimaryKey(messageId);
	}

}
