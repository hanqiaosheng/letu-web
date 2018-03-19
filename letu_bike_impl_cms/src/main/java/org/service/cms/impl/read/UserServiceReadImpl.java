package org.service.cms.impl.read;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dao.DefriendRecordMapper;
import org.dao.UserGuideMapper;
import org.dao.UserMapper;
import org.entity.dto.DefriendRecord;
import org.entity.dto.DefriendRecordExample;
import org.entity.dto.User;
import org.entity.dto.UserExample;
import org.entity.dto.UserGuide;
import org.entity.dto.UserGuideExample;
import org.service.cms.read.UserServiceRead;
import org.springframework.stereotype.Service;
import org.util.DateUtil;
import org.util.PageUtil;

@Service("userServiceRead")
public class UserServiceReadImpl implements UserServiceRead {
	@Resource
	UserMapper userMapper;

	@Resource
	UserGuideMapper userGuideMapper;

	@Resource
	DefriendRecordMapper defriendRecordMapper;

	@Override
	public List<User> findUserByCondition(User user, Integer pageIndex, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		if (null != user.getUserTel() && !"".equals(user.getUserTel()))
			criteria.andUserTelLike("%" + user.getUserTel().trim() + "%");
		if (null != user.getUserIdcard() && !"".equals(user.getUserIdcard()))
			criteria.andUserIdcardLike("%" + user.getUserIdcard().trim() + "%");
		if (null != user.getUserRealname() && !"".equals(user.getUserRealname()))
			criteria.andUserRealnameLike("%" + user.getUserRealname().trim() + "%");
		if (null != user.getUserId())
			criteria.andUserIdEqualTo(user.getUserId());
		
		if (null != user.getUserIsblacklist() && 0 == user.getUserIsblacklist()) {
			if (null != user.getFromTime() && !"".equals(user.getFromTime())) {
				if (null != user.getToTime() && !"".equals(user.getToTime())){
					criteria.andUserDefriendTimeBetween(DateUtil.changStringDate03(user.getFromTime()),
							DateUtil.changStringDate03(user.getToTime()));
				}else {
					criteria.andUserDefriendTimeBetween(DateUtil.changStringDate03(user.getFromTime()), new Date());
				}
			} else if (null != user.getToTime() && !"".equals(user.getToTime()))
				criteria.andUserDefriendTimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(user.getToTime()), 1));
			criteria.andUserIsblacklistEqualTo(user.getUserIsblacklist());
			example.setOrderByClause("user_defriend_time DESC");
		}else{
			if (null != user.getFromTime() && !"".equals(user.getFromTime())) {
				if (null != user.getToTime() && !"".equals(user.getToTime())){
					criteria.andUserCreatetimeBetween(DateUtil.changStringDate03(user.getFromTime()),
							DateUtil.changStringDate03(user.getToTime()));
				}else {
					criteria.andUserCreatetimeBetween(DateUtil.changStringDate03(user.getFromTime()), new Date());
				}
			} else if (null != user.getToTime() && !"".equals(user.getToTime()))
				criteria.andUserCreatetimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(user.getToTime()), 1));
		
			example.setOrderByClause("FIELD(user_idcard,'') asc,user_createtime DESC");
		}
		criteria.andUserTelIsNotNull();
		criteria.andUserStateNotEqualTo(2);
		if(null!=pageIndex&&0!=pageIndex){
			example.setLimitStart(PageUtil.getStart(pageIndex));
			example.setLimitEnd(pageSize);
		}
		List<User> list = userMapper.selectByExample(example);
		for (User u : list) {
			DefriendRecordExample drExample = new DefriendRecordExample();
			DefriendRecordExample.Criteria drCriteria = drExample.createCriteria();
			drCriteria.andDefriendUserIdEqualTo(u.getUserId());
			drCriteria.andDefriendRegainTimeIsNull();
			List<DefriendRecord> drList = defriendRecordMapper.selectByExample(drExample);
			if (0 == drList.size())
				continue;
			else {
				Long adminId = defriendRecordMapper.selectByExample(drExample).get(0).getDefriendAdminId();
				u.setDefriendAdminId(adminId);
				u.setDefriendReason(drList.get(0).getDefriendReason());
			}
		}
		return list;
	}

	@Override
	public User findByIdCondition(Long userId, String phone, String idCard, String name) {
		User user = userMapper.selectByPrimaryKey(userId);
		//todo mybatis
		if(phone!=null&&!"".equals(phone)){
			if(!user.getUserTel().equals(phone)){
				return null;
			}
		}
		if(idCard!=null&&!"".equals(idCard)){
			if(!user.getUserIdcard().equals(idCard)){
				return null;
			}
		}
		if(name!=null&&!"".equals(name)){
			if(!user.getUserRealname().matches(".*"+name+".*")){
				return null;
			}
		}
		return user;
	}

	@Override
	public User findById(Long userId) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public List<User> findByIds(List<Long> userIds) throws Exception {
		// TODO Auto-generated method stub
		if(userIds.isEmpty()){
			return Collections.emptyList();
		}
		return userMapper.selectByIds(userIds);
	}

	@Override
	public List<User> findUserAndRentByIds(List<Long> userIds) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.selectUserAndRentByIds(userIds);
	}

	

	@Override
	public Integer countAllUser(User user) throws Exception {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		if (null != user.getUserTel() && !"".equals(user.getUserTel()))
			criteria.andUserTelLike("%" + user.getUserTel().trim() + "%");
		if (null != user.getUserIdcard() && !"".equals(user.getUserIdcard()))
			criteria.andUserIdcardLike("%" + user.getUserIdcard().trim() + "%");
		if (null != user.getUserRealname() && !"".equals(user.getUserRealname()))
			criteria.andUserRealnameLike("%" + user.getUserRealname().trim() + "%");
		if (null != user.getFromTime() && !"".equals(user.getFromTime())) {
			if (null != user.getToTime() && !"".equals(user.getToTime()))
				criteria.andUserCreatetimeBetween(DateUtil.changStringDate03(user.getFromTime()),
						DateUtil.plusDate(DateUtil.changStringDate03(user.getToTime()), 1));
			else {
				criteria.andUserCreatetimeBetween(DateUtil.changStringDate03(user.getFromTime()), new Date());
			}
		} else if (null != user.getToTime() && !"".equals(user.getToTime()))
			criteria.andUserCreatetimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(user.getToTime()), 1));
		if (null != user.getUserIsblacklist() && 0 == user.getUserIsblacklist())
			criteria.andUserIsblacklistEqualTo(user.getUserIsblacklist());
		else{
			criteria.andUserIsblacklistEqualTo((short) 1);
		}
		criteria.andUserTelIsNotNull();
		criteria.andUserStateNotEqualTo(2);
		return userMapper.countByExample(example);
	}


	@Override
	public User findUserByPhone(String phone) throws Exception {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUserIsblacklistEqualTo((short) 1);
		criteria.andUserTelEqualTo(phone);
		criteria.andUserStateNotEqualTo(2);
		List<User> users = userMapper.selectByExample(example);
		if(users.size()>0){
			return users.get(0);
		}
		return null;
	}



	@Override
	public Integer userCount(Date date) throws Exception {
		// TODO Auto-generated method stub
		
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUserCreatetimeGreaterThanOrEqualTo(date);
		criteria.andUserCreatetimeLessThan(DateUtil.plusDateNum(date, 1));
		criteria.andUserIdcardIsNotNull();
		Integer userNumber = userMapper.countByExample(example);
		return userNumber;
	}
	
	@Override
	public List<UserGuide> findAllGuideInfo() throws Exception {
		// TODO Auto-generated method stub
		UserGuideExample example = new UserGuideExample();
		List<UserGuide> userGuideList = userGuideMapper.selectByExample(example);
		return userGuideList;
	}

	@Override
	public UserGuide findGuideInfoById(Long userGuideId) throws Exception {
		// TODO Auto-generated method stub
		UserGuide userGuide = userGuideMapper.selectByPrimaryKey(userGuideId);
		return userGuide;
	}
	@Override
	public List<User> findVillagerByCondition(User user, Integer pageIndex, Integer pageSize, List<Long> channelIds) throws Exception {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		if (null != user.getUserTel() && !"".equals(user.getUserTel()))
			criteria.andUserTelLike("%" + user.getUserTel().trim() + "%");
		if (null != user.getUserIdcard() && !"".equals(user.getUserIdcard()))
			criteria.andUserIdcardLike("%" + user.getUserIdcard().trim() + "%");
		if (null != user.getUserRealname() && !"".equals(user.getUserRealname()))
			criteria.andUserRealnameLike("%" + user.getUserRealname().trim() + "%");
		if (null != user.getUserId())
			criteria.andUserIdEqualTo(user.getUserId());
		if (null != user.getUserChannelId())
			criteria.andUserChannelIdEqualTo(user.getUserChannelId());
		if (null != channelIds ) {
			criteria.andUserChannelIdIn(channelIds);
		}
		if (null != user.getUserIsblacklist() && 0 == user.getUserIsblacklist()) {
			if (null != user.getFromTime() && !"".equals(user.getFromTime())) {
				if (null != user.getToTime() && !"".equals(user.getToTime())){
					criteria.andUserDefriendTimeBetween(DateUtil.changStringDate03(user.getFromTime()),
							DateUtil.changStringDate03(user.getToTime()));
				}else {
					criteria.andUserDefriendTimeBetween(DateUtil.changStringDate03(user.getFromTime()), new Date());
				}
			} else if (null != user.getToTime() && !"".equals(user.getToTime()))
				criteria.andUserDefriendTimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(user.getToTime()), 1));
			criteria.andUserIsblacklistEqualTo(user.getUserIsblacklist());
			example.setOrderByClause("user_defriend_time DESC");
		}else{
			if (null != user.getFromTime() && !"".equals(user.getFromTime())) {
				if (null != user.getToTime() && !"".equals(user.getToTime())){
					criteria.andUserCreatetimeBetween(DateUtil.changStringDate03(user.getFromTime()),
							DateUtil.changStringDate03(user.getToTime()));
				}else {
					criteria.andUserCreatetimeBetween(DateUtil.changStringDate03(user.getFromTime()), new Date());
				}
			} else if (null != user.getToTime() && !"".equals(user.getToTime()))
				criteria.andUserCreatetimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(user.getToTime()), 1));
		
			example.setOrderByClause("FIELD(user_idcard,'') asc,user_createtime DESC");
		}
		criteria.andUserChannelIdIsNotNull();
		criteria.andUserChannelIdGreaterThan((long)0);
		criteria.andUserTelIsNotNull();
		criteria.andUserStateNotEqualTo(2);
		if(null!=pageIndex&&0!=pageIndex){
			example.setLimitStart(PageUtil.getStart(pageIndex));
			example.setLimitEnd(pageSize);
		}
		List<User> list = userMapper.selectByExample(example);
		for (User u : list) {
			DefriendRecordExample drExample = new DefriendRecordExample();
			DefriendRecordExample.Criteria drCriteria = drExample.createCriteria();
			drCriteria.andDefriendUserIdEqualTo(u.getUserId());
			drCriteria.andDefriendRegainTimeIsNull();
			List<DefriendRecord> drList = defriendRecordMapper.selectByExample(drExample);
			if (0 == drList.size())
				continue;
			else {
				Long adminId = defriendRecordMapper.selectByExample(drExample).get(0).getDefriendAdminId();
				u.setDefriendAdminId(adminId);
				u.setDefriendReason(drList.get(0).getDefriendReason());
			}
		}
		return list;
		
	}

	@Override
	public Integer countAllVillager(User user, List<Long> channelIds) throws Exception {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		if (null != user.getUserTel() && !"".equals(user.getUserTel()))
			criteria.andUserTelLike("%" + user.getUserTel().trim() + "%");
		if (null != user.getUserIdcard() && !"".equals(user.getUserIdcard()))
			criteria.andUserIdcardLike("%" + user.getUserIdcard().trim() + "%");
		if (null != user.getUserRealname() && !"".equals(user.getUserRealname()))
			criteria.andUserRealnameLike("%" + user.getUserRealname().trim() + "%");
		if (null != user.getUserChannelId())
			criteria.andUserChannelIdEqualTo(user.getUserChannelId());
		if (null != channelIds ) {
			criteria.andUserChannelIdIn(channelIds);
		}
		if (null != user.getFromTime() && !"".equals(user.getFromTime())) {
			if (null != user.getToTime() && !"".equals(user.getToTime()))
				criteria.andUserCreatetimeBetween(DateUtil.changStringDate03(user.getFromTime()),
						DateUtil.plusDate(DateUtil.changStringDate03(user.getToTime()), 1));
			else {
				criteria.andUserCreatetimeBetween(DateUtil.changStringDate03(user.getFromTime()), new Date());
			}
		} else if (null != user.getToTime() && !"".equals(user.getToTime()))
			criteria.andUserCreatetimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(user.getToTime()), 1));
		if (null != user.getUserIsblacklist() && 0 == user.getUserIsblacklist())
			criteria.andUserIsblacklistEqualTo(user.getUserIsblacklist());
		else{
			criteria.andUserIsblacklistEqualTo((short) 1);
		}
		criteria.andUserChannelIdIsNotNull();
		criteria.andUserTelIsNotNull();
		criteria.andUserStateNotEqualTo(2);
		criteria.andUserChannelIdGreaterThan((long)0);
		return userMapper.countByExample(example);
		
	}

	@Override
	public User findByIdcard(String tel, String userIdcard) throws Exception {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUserTelEqualTo(tel);
		criteria.andUserIdcardEqualTo(userIdcard);
		criteria.andUserStateNotEqualTo(2);
		criteria.andUserIsblacklistEqualTo((short) 1);
		List<User> users = userMapper.selectByExample(example);
		if(users.size()>0){
			return users.get(0);
		}
		return null;
		
	}

	@Override
	public List<User> findAllUser() throws Exception {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUserStateNotEqualTo(2);
		List<User> list = userMapper.selectByExample(example);
		return list;
	}
}
