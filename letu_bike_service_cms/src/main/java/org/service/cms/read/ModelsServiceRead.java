package org.service.cms.read;


import java.util.List;

import org.entity.dto.Models;

public interface ModelsServiceRead {
	
	
	/**
	 * 查找车型
	 * @param bikeModelsId
	 * @return
	 * @throws Exception
	 */
	public Models findModelsById(Long bikeModelsId) throws Exception;
	
	/**
	 * 根据渠道id查车型 
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	public List<Models> findModelsByChannelId(Long channelId) throws Exception;

	/**
	 * 查找子级和父级的车型
	 * @param channelIds
	 * @return
	 * @throws Exception
	 */
	public List<Models> findChannelIds(List<Long> channelIds) throws Exception;
/**
	 * 查租赁的费用
	 * @param pageIndex
	 * @param integer
	 * @param channelId
	 * @param channelName
	 * @return
	 * @throws Exception
	 */
	public List<Models> findAllModels2(Integer pageIndex, Integer integer, Long channelId, String channelName) throws Exception;
	/**
	 * 查租赁的数目
	 * @param channelId
	 * @param channelName
	 * @return
	 * @throws Exception
	 */
	public Integer findAllCountModels(Long channelId, String channelName) throws Exception;/**
	 * 查询所有使用中的车型
	 * @return
	 * @throws Exception
	 */
	public List<Models> findAllModels(Integer pageIndex,String modelsCode,String modelsName,Long channelId,List<Long> currChannelIds) throws Exception;
	public Integer countAllModels(String modelsCode,String modelsName,Long channelId, List<Long> currChannelIds) throws Exception;
	/**
	 * 查子级租赁费用
	 * @param pageIndex
	 * @param page_size_web
	 * @param channelId
	 * @param channelName
	 * @param channelIds
	 * @return
	 * @throws Exception
	 */
	public List<Models> findChannelIdsPage(Integer pageIndex, Integer page_size_web, Long channelId, String channelName,
			List<Long> channelIds) throws Exception;
	
	/**
	 * 查子级租赁数目
	 * @param channelId
	 * @param channelName
	 * @param channelIds
	 * @return
	 * @throws Exception
	 */
	public Integer findAllCountModelsPage(Long channelId, String channelName, List<Long> channelIds) throws Exception;
	
	/**
	 * 根据渠道和车型
	 * @param checkCode
	 * @param modelsChannelId
	 * @return
	 * @throws Exception
	 */
	public Models findByChannelIdAndCode(String checkCode, Long modelsChannelId) throws Exception;
	
	/**
	 * 根据渠道查车型list id
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	public List<Long> findByChannelId(Long channelId) throws Exception;

	
	/**
	 * 根据车型查询信息
	 * @return
	 * @throws Exception
	 */
	public Models findModelsByName(String modelsName)throws Exception;
	
	public List<Models> findAllModels()throws Exception;

}
