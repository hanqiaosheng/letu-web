package org.service.weixin.impl.write;


import java.util.Date;

import javax.annotation.Resource;

import org.dao.IdcardMapper;
import org.dao.UserMapper;
import org.entity.dto.Idcard;
import org.entity.dto.User;
import org.entity.weixin.pojo.SNSUserInfo;
import org.service.weixin.write.UserServiceWeixinWrite;
import org.springframework.stereotype.Service;

@Service("userServiceWeixinWrite")
public class UserServiceWeixinImplWrite implements UserServiceWeixinWrite{

	@Resource
	UserMapper userMapper;
	@Resource
	IdcardMapper idcardMapper;
	
	@Override
	public User updateUser(User user) throws Exception {
		int row = userMapper.updateByPrimaryKeySelective(user);
		if(1 == row){
			return user;
		}else{
			return null;
		}
	}

	@Override
	public User regiest(SNSUserInfo snsUserInfo) throws Exception {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserGender(snsUserInfo.getSex());
		user.setUserNickname(snsUserInfo.getNickName());;
		user.setUserOpenid(snsUserInfo.getOpenId());
		user.setUserProfileImage(snsUserInfo.getHeadimgurl());
		user.setUserCreatetime(new Date());
		userMapper.insertSelective(user);  //有返回主键
		
		User newUser = userMapper.selectByPrimaryKey(user.getUserId());
		return newUser;
	}

	@Override
	public void addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		userMapper.insertSelective(user);
	}

	@Override
	public User regiest2(User iUser) throws Exception {
		// TODO Auto-generated method stub
		userMapper.insertSelective(iUser);  //有返回主键
		User newUser = userMapper.selectByPrimaryKey(iUser.getUserId());
		return newUser;
	}

	@Override
	public void addIdCard(Idcard idcard) throws Exception {
		// TODO Auto-generated method stub
		idcardMapper.insertSelective(idcard);
	}

	@Override
	public void updateIdcard(Idcard idcard) throws Exception {
		// TODO Auto-generated method stub
		idcardMapper.updateByPrimaryKeySelective(idcard);
	}
}
