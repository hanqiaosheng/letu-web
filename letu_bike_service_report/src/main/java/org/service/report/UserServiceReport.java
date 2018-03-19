package org.service.report;

import java.util.Date;
import java.util.List;

import org.entity.dto.User;
import org.entity.dto.UserGuide;

public interface UserServiceReport {
	/**
	 * 根据条件查询用户（分页）
	 * @param user
	 * @param pageIndex
	 * @param page_size
	 * @return
	 * @throws Exception
	 */
	public List<User> findUserByCondition(User user,Integer pageIndex, Integer pageSize) throws Exception;

	/**
	 * 通过id查找用户
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public User findById(Long userId) throws Exception;

	
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
	
	/**
	 * 查询用户报表
	 * @return
	 * @throws Exception
	 */
	public List<User> findUserList(String tel,String name,String idcard,Date fromtime, Date totime,Integer pageIndex)throws Exception;
	
	/**
	 * 查询用户报表数量
	 * @param tel
	 * @param name
	 * @param idcard
	 * @param fromtime
	 * @param totime
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	public Integer countUserList(String tel,String name,String idcard,Date fromtime, Date totime)throws Exception;
	
	/**
	 * 查询新增租车用户报表
	 * @param tel
	 * @param bikeCode
	 * @param fromtime
	 * @param totime
	 * @param channelIds
	 * @return
	 * @throws Exception
	 */
	public List<User> findNewUser(List<Long>channelIdList,String tel,String bikeCode,Date fromtime, Date totime,List<Long> channelIds,Integer pageIndex)throws Exception;
	
	public Integer countNewUser(List<Long>channelIdList,String tel,String bikeCode,Date fromtime, Date totime,List<Long> channelIds)throws Exception;
}
