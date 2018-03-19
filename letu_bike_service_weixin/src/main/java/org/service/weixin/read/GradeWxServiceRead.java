package org.service.weixin.read;

import java.util.List;

import org.entity.dto.GradeRecord;

public interface GradeWxServiceRead {
	/**
	 * 通过用户id获取记录
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<GradeRecord> findByUserId(Integer pageIndex, Integer pageSize, Long userId,Integer tName) throws Exception;

	public Integer countAllRecord(Long userId,Integer tName)throws Exception;
}
