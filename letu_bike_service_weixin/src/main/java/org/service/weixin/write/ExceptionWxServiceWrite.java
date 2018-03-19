package org.service.weixin.write;

import org.entity.dto.Exceptions;

public interface ExceptionWxServiceWrite {

	/**
	 * 上传异常
	 * @param exception
	 * @throws Exception
	 */
	public void addException(Exceptions exceptions) throws Exception;

}
