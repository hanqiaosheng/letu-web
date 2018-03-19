package org.service.cms.write;

import org.entity.dto.OperateLog;

public interface OperateServiceWrite {
	/**
	 * 添加操作日志
	 * @param operateLog
	 * @throws Exception
	 */
    public void addOperateLogs(OperateLog operateLog)throws Exception;
}
