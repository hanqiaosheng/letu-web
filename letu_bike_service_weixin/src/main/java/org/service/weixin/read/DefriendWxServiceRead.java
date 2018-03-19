package org.service.weixin.read;

import org.entity.dto.DefriendRecord;

public interface DefriendWxServiceRead {
	
	/**
	 * 根据id查询拉黑表
	 * @param deFriendId
	 * @return
	 * @throws Exception
	 */
	public DefriendRecord findDefriendByUserId(Long userId) throws Exception;
}
