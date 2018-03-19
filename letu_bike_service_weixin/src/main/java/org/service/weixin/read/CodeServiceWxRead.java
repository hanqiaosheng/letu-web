package org.service.weixin.read;

import org.entity.dto.Code;

public interface CodeServiceWxRead {
	
	/**\
	 * 根据手机号和方式查最近的验证码
	 * @param telphone
	 * @param i
	 * @return
	 * @throws Exception
	 */
	public Code findByPhone(String telphone, int i) throws Exception;

}
