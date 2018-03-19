package org.service.weixin.read;

import java.util.List;

import org.entity.dto.Idcard;
import org.entity.dto.User;
import org.entity.dto.UserGuide;

public interface UserServiceWeixinRead {

	public User login(User user) throws Exception;
	
	/**
	 * 根据opendId 查User
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	public User findUserByOpenId(String openId) throws Exception;
	
	/**
	 * 根据smallopendId 查User
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	public User findUserBySmallOpenId(String smallOpendId) throws Exception;

	/**
	 * 
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public User findUserByPhone(String phone) throws Exception;
	
	/**
	 * 根据用户主键来查找用户
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public User findByUId(Long userId) throws Exception	;


	/**
	 * 根据用户id查idcard
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Idcard findIdCardByUserId(Long userId) throws Exception;
	
	/**
	 * 查询用户指南
	 * @return
	 * @throws Exception
	 */
	public List<UserGuide> findAllUserGuide()throws Exception;

	/**
	 * 根据id查询用户指南
	 * @param userGuideId
	 * @return
	 * @throws Exception
	 */
	public UserGuide findUserGuideById(Long userGuideId)throws Exception;
	
	/**
	 * 根据token查user
	 * @param token
	 * @param activeTime
	 * @return
	 * @throws Exception
	 */
	public User findUserByToken(String token, Integer activeTime) throws Exception;
}
