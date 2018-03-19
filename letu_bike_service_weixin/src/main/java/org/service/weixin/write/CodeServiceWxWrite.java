package org.service.weixin.write;

import org.entity.dto.Code;

public interface CodeServiceWxWrite {
	
	/**
	 * 新增短信验证码
	 * @param code
	 * @throws Exception
	 */
	public void add(Code code) throws Exception;
	/**
	 * 更新验证吗
	 * @param code
	 * @throws Exception
	 */
	public void updateCode(Code code) throws Exception;

}
