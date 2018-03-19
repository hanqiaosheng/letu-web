package org.service.cms.read;

import org.entity.dto.Admin;

import java.util.List;

/**
 * 管理员查询接口
 * @author Administrator
 *
 */
public interface AdminServiceRead {
	/**
	 * 通过名字查找管理员
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public Admin findByName(String username) throws Exception;
	
	/**
	 * 根据管理员id查admin
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public Admin findAdminId(Long adminId) throws Exception;
	
	/**
	 * 查找所有的管理员列表
	 * @param pageIndex
	 * @param pageSize 
	 * @param adminname
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Admin> findAllAdmin(Integer pageIndex, Integer pageSize, String adminname, Long roleId,List<Long> channelIds) throws Exception;
	
	/**
	 * 计算管理员条数
	 * @param adminname
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public Integer pageCount(String adminname, Long roleId,List<Long> channelIds) throws Exception;
	
	public List<Admin> findByChannelId(Long ChannelId) throws Exception;
	
	/**
	 * 通过realname查admin
	 * @param adminRealName
	 * @return
	 * @throws Exception
	 */
	public List<Admin> findByRealName(String adminRealName) throws Exception;


	/**
	 * 根据电话查找
	 * @param tel
	 * @return
	 */
	public Admin findByTel(String tel);

	/**
	 * 根据身份证查找
	 * @param idCard
	 * @return
	 */
	public Admin findByIdCard(String idCard);

	/**
	 * openId查询导游信息
	 */
	Admin findByOpenId(String openId);

	public List<Admin> findAllByCondition(Integer pageIndex, Integer pageSize,
										  Admin admin, Long roleId) throws Exception;
}
