package org.service.report;

import java.util.List;

import org.entity.dto.Channel;

public interface ChannelServiceReport {
	
	/**
	 * 查詢所有的渠道
	 * @param pageSize 
	 * @param pageIndex 
	 * @param channelName 
	 * @param charger 
	 * @param channelId 
	 * @param flag 
	 * @return
	 * @throws Exception
	 */
	public List<Channel> findAllChannel(Integer pageIndex, Integer pageSize,String channelNum, Long channelId, String charger, String channelName, Integer flag) throws Exception;
	/**
	 * 根据渠道id查渠道的信息
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	public Channel findById(Long channelId) throws Exception;
	/**
	 * 查找所有的渠道条数 含 冻结
	 * @param channelId
	 * @param charger
	 * @param channelName
	 * @return
	 * @throws Exception
	 */
	public Integer findAllChannelCount(String channelNum,Long channelId, String charger, String channelName) throws Exception;
	/**
	 * 根据渠道名查渠道
	 * @param channelName
	 * @return
	 * @throws Exception
	 */
	public Channel findByName(String channelName) throws Exception;
	
	/**
	 * 查询不被冻结的所有渠道
	 * @param channelId 
	 * @return
	 * @throws Exception
	 */
	public List<Channel> findAllChannelNotfreeze(List<Long> channelIds) throws Exception;
	/**
	 * 查询所有渠道
	 * @param channelId 
	 * @return
	 * @throws Exception
	 */
	public List<Channel> findAllChannelByChannelIds(List<Long> channelIds) throws Exception;
	
	/**
	 * 查找子级渠道 
	 * @param pageSize 
	 * @param pageIndex 
	 * @param num
	 * @param channelAdmin 
	 * @param channelName 
	 * @param charger 
	 * @param channelName2 
	 * @return
	 * @throws Exception
	 */
	public List<Channel> findByParentId(Integer pageIndex, Integer pageSize, String channelNum, String num, String charger, String channelName)  throws Exception;
	/**
	 * 查子级渠道的个数
	 * @param num
	 * @param charger
	 * @param channelName
	 * @return
	 * @throws Exception
	 */
	public Integer findAllCountByParentId(String channelNum,String num, String charger, String channelName) throws Exception;
	/**
	 * 查找子渠道
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	public List<Channel> findSonChannels(Long channelId) throws Exception;
	/**
	 * 查找等级1的渠道
	 * @return
	 * @throws Exception
	 */
	public List<Channel> findLevelOne(List<Long> channelIds) throws Exception;
	/**
	 * 查找子级的渠道
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	public List<Channel> findByParentChannelId(Long channelId) throws Exception;
	/**
	 * 获取子级的渠道数量
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	public Integer getCountByParentId(Long channelParentId)throws Exception;
	
	public List<Channel> findAllChannels()throws Exception;
	
	public List<Channel> findChannel(List<String> str)throws Exception;

}
