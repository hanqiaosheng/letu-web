package org.service.cms.write;

import org.entity.dto.SysMsg;

public interface SysMsgServiceWrite {
	/**
	 * 上下线删除消息
	 * 
	 * @param sysMsg
	 * @return
	 * @throws Exception
	 */
	public void updateSysMsg(SysMsg sysMsg) throws Exception;
	
	/**
	 * 添加消息
	 * @param sysMsg
	 * @throws Exception
	 */
	public Long addSysMsg(SysMsg sysMsg) throws Exception;
	
	
}
