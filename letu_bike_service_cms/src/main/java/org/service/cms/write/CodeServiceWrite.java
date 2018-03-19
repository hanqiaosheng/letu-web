package org.service.cms.write;

import org.entity.dto.Code;

public interface CodeServiceWrite {

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

