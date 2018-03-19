package org.service.cms.write;

import org.entity.dto.Admin;

/**
 * admin 写的接口
 * @author Administrator
 *
 */
public interface AdminServiceWrite {
	/**
	 * 新增admin
	 * @param admin
	 * @return 
	 * @throws Exception
	 */
	public Long addAmin(Admin admin) throws Exception;

	/**
	 * 修改admin
	 * @param admin
	 * @return
	 * @throws Exception
	 */
	public boolean updateAdmin(Admin admin) throws Exception;

	/**
	 * 新增admin
	 * @param admin
	 * @return 
	 * @throws Exception
	 */
	public Long addAminCanNull(Admin admin)throws Exception;

}
