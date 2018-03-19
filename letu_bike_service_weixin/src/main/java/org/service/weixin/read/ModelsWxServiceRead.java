package org.service.weixin.read;


import java.util.List;

import org.entity.dto.Models;

public interface ModelsWxServiceRead {

	public Models findModelsById(Long modelsId) throws Exception;
	/**
	 * 根据渠道查车型list id
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	public List<Long> findByChannelId(Long channelId) throws Exception;

}
