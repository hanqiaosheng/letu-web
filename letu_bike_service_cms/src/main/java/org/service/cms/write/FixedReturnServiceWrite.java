package org.service.cms.write;

import org.entity.dto.FixedReturn;

public interface FixedReturnServiceWrite {

	public Long addFixedReturn(FixedReturn fixedReturn) throws Exception;

	public void editFixedReturn(FixedReturn fixedReturn) throws Exception;

	public void update(FixedReturn fixedReturn,Integer flag) throws Exception;
	
}
