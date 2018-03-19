package org.service.weixin.impl.read;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.dao.IdcardMapper;
import org.dao.UserGuideMapper;
import org.dao.UserMapper;
import org.entity.dto.Idcard;
import org.entity.dto.IdcardExample;
import org.entity.dto.User;
import org.entity.dto.UserExample;
import org.entity.dto.UserExample.Criteria;
import org.entity.dto.UserGuide;
import org.entity.dto.UserGuideExample;
import org.service.weixin.read.UserServiceWeixinRead;
import org.springframework.stereotype.Service;
import org.util.DateUtil;

@Service("userServiceWeixinRead")
public class UserServiceWeixinImplRead implements UserServiceWeixinRead{

	@Resource
	UserMapper userMapper;
	@Resource
	IdcardMapper idcardMapper;
	@Resource
	UserGuideMapper userGuideMapper;
	
	//当前类的logger日志
		private static Logger loggers = Logger.getLogger(UserServiceWeixinImplRead.class);
	
	
	@Override
	public User login(User user) throws Exception {
		// TODO Auto-generated method stub
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUserTelEqualTo(user.getUserTel());
		List<User> list = userMapper.selectByExample(userExample);
		System.out.println("123");
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public User findUserByOpenId(String openId) throws Exception {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserOpenidEqualTo(openId);
		criteria.andUserStateNotEqualTo(2);
		List<User> list = userMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
		
	}

	@Override
	public User findUserByPhone(String phone) throws Exception {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUserTelEqualTo(phone);
		criteria.andUserStateNotEqualTo(2);
		List<User> users = userMapper.selectByExample(example);
		if(users.size()>0){
			return users.get(0);
		}
		return null;
	}

	@Override
	public User findByUId(Long userId) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(userId);
	}

	

	@Override
	public Idcard findIdCardByUserId(Long userId) throws Exception {
		// TODO Auto-generated method stub
		IdcardExample example = new IdcardExample();
		IdcardExample.Criteria criteria = example.createCriteria();
		criteria.andIdcardUserIdEqualTo(userId);
		List<Idcard> idcards = idcardMapper.selectByExample(example);
		if(idcards.size()>0){
			return idcards.get(0);
		}
		
		return null;
	}
	@Override
	public List<UserGuide> findAllUserGuide() throws Exception {
		// TODO Auto-generated method stub
		UserGuideExample example = new UserGuideExample();
		List<UserGuide> userGuideList = userGuideMapper.selectByExample(example);
		return userGuideList;
	}

	@Override
	public UserGuide findUserGuideById(Long userGuideId) throws Exception {
		// TODO Auto-generated method stub
		return userGuideMapper.selectByPrimaryKey(userGuideId);
	}
	
	@Override
	public User findUserByToken(String token, Integer activeTime) throws Exception {
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		if (null == token) {
			return null;
		}
		criteria.andUserTokenEqualTo(token);
		criteria.andUserStateNotEqualTo(2);
		loggers.info("开始时间："+DateUtil.format(DateUtil.dateMinusMinute(activeTime))+";结束时间："+DateUtil.format(new Date()));
		if (-1 != activeTime) {
			loggers.info("有效期内");
			// 在有效期内
			criteria.andUserLogintimeGreaterThan(DateUtil.dateMinusMinute(activeTime));
		}
		List<User> userList = userMapper.selectByExample(example);
		if (userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}

	@Override
	public User findUserBySmallOpenId(String smallOpendId) throws Exception {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserSmallOpenidEqualTo(smallOpendId);
		List<User> list = userMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
