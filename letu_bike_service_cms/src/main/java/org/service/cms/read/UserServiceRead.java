package org.service.cms.read;

import java.util.Date;
import java.util.List;

import org.entity.dto.User;
import org.entity.dto.UserGuide;

public interface UserServiceRead {
	/**
	 * 根据条件查询用户（分页）
	 * @param user
	 * @param pageIndex
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<User> findUserByCondition(User user,Integer pageIndex, Integer pageSize) throws Exception;

	/**
	 * 根据带条件的id查找
	 * @param userId
	 * @param phone
	 * @param idCard
	 * @param name
	 * @return
	 */
	public User findByIdCondition(Long userId,String phone,String idCard,String name);

	/**
	 * 通过id查找用户
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public User findById(Long userId) throws Exception;

	/**
	 * 通过ids查找用户和租赁订单
	 *
	 * @param userIds
	 * @return
	 * @throws Exception
	 */
	public List<User> findByIds(List<Long> userIds) throws Exception;

	/**
	 * 通过ids查找用户和租赁订单
	 *
	 * @param userIds
	 * @return
	 * @throws Exception
	 */
	public List<User> findUserAndRentByIds(List<Long> userIds) throws Exception;

	
	/**
	 * 
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public User findUserByPhone(String phone) throws Exception;

	
	/**
	 * 根据查询条件 查找所有的用户条数
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Integer countAllUser(User user) throws Exception;
	
	
	
	/**
	 * 统计每日新增用户
	 * @return
	 * @throws Exception
	 */
	public Integer userCount(Date date) throws Exception;
	/**
	 * 查询用户指南信息
	 * @return
	 * @throws Exception
	 */
	public List<UserGuide> findAllGuideInfo()throws Exception;

	/**
	 * 根据id查询用户指南信息
	 * @param userGuideId
	 * @return
	 * @throws Exception
	 */
	public UserGuide findGuideInfoById(Long userGuideId)throws Exception;
	
	/**
	 * 通过条件查询村民（分页）
	 * @param user
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<User> findVillagerByCondition(User user,Integer pageIndex, Integer pageSize, List<Long> channelIds) throws Exception;
	
	/**
	 * 根据查询条件 查找所有的村民条数
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Integer countAllVillager(User user, List<Long> channelIds) throws Exception;
	
	/**
	 * 通过电话和身份证查用户
	 * @param tel
	 * @param userIdcard
	 * @return
	 * @throws Exception
	 */
	public User findByIdcard(String tel,String userIdcard)throws Exception;
	
	public List<User> findAllUser()throws Exception;
}
