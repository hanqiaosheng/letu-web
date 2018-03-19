package org.service.cms.read;

import java.util.List;

import org.entity.dto.OperateLog;

public interface OperateLogServiceRead {

	/**
	 * 根据条件查询操作日志（分页）
	 * @param pageIndex
	 * @param page_size_web
	 * @return
	 * @throws Exception
	 */
	public List<OperateLog> findOperateByCondition(List<Long> adminIds, OperateLog operateLog,Integer pageIndex, Integer page_size_web)throws Exception;

	/**
	 * 分页数量
	 * @return
	 * @throws Exception
	 */
	public Integer countAllOperate(List<Long> adminIds,OperateLog operateLog)throws Exception;

	
}
