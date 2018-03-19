package org.service.weixin.read;

import java.util.List;

import org.entity.dto.SysMsg;

public interface SysMsgWxServiceRead {
	/**
	 * 查找所有上线的资讯
	 * @param pageIndex
	 * @param page_size_weixin
	 * @return
	 * @throws Exception
	 */
	public List<SysMsg> findAllSysMsg(Integer pageIndex, Integer page_size_weixin) throws Exception;

	/**
	 * 超找所有资讯条数
	 * @return
	 * @throws Exception
	 */
	public Integer findAllSysMsgCount() throws Exception;
	/**
	 * 根据id查资讯
	 * @param sysMsgId
	 * @return
	 * @throws Exception
	 */
	public SysMsg findBySId(Long sysMsgId) throws Exception;

}
