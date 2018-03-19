package org.service.cms.read;

import java.util.List;

import org.entity.dto.SysMsg;

public interface SysMsgServiceRead {
	/**
	 * 根据条件查询系统消息（分页）
	 * @param sysMsg
	 * @param pageIndex
	 * @param page_size
	 * @return
	 * @throws Exception
	 */
	public List<SysMsg> findSysMsgByCondition(SysMsg sysMsg,Integer pageIndex, Integer pageSize) throws Exception;

	/**
	 * 通过id查找系统消息
	 * 
	 * @param sysMsgId
	 * @return
	 * @throws Exception
	 */
	public SysMsg findById(Long sysMsgId) throws Exception;

	


	
	/**
	 * 根据查询条件 查找所有的系统消息条数
	 * 
	 * @param sysMsg
	 * @return
	 * @throws Exception
	 */
	public Integer countAllSysMsg(SysMsg sysMsg) throws Exception;
	
}
