package org.service.cms.read;

import java.util.List;

import org.entity.dto.Code;


/**
 * 短信业务层
 * @author Administrator
 *
 */
public interface CodeServiceRead {

	public List<Code> findAllShotMessage(Integer pageIndex, Integer pageSize, String tel,Integer codeState)throws Exception;

	public Integer getCountShort(String tel,Integer codeState)throws Exception;

	/**\
	 * 根据手机号和方式查最近的验证码
	 * @param telphone
	 * @param i
	 * @return
	 * @throws Exception
	 */
	public Code findByPhone(String telphone, int i) throws Exception;


}
