package org.service.weixin.write;

import org.entity.dto.GradeRecord;

public interface GradeWxServiceWrite {

	/**
	 * 新增积分记录
	 * @param message
	 * @throws Exception
	 */
	public void addGradeRecord(GradeRecord gradeRecord) throws Exception;

}
