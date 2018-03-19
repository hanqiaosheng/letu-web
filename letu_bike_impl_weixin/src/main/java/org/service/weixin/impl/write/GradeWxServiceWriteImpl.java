package org.service.weixin.impl.write;

import javax.annotation.Resource;

import org.dao.GradeRecordMapper;
import org.entity.dto.GradeRecord;
import org.service.weixin.write.GradeWxServiceWrite;
import org.springframework.stereotype.Service;


@Service("gradeWxServiceWrite")
public class GradeWxServiceWriteImpl implements GradeWxServiceWrite {

	@Resource
	GradeRecordMapper gradeRecordMapper;
	
	
	
	@Override
	public void addGradeRecord(GradeRecord gradeRecord) throws Exception {
		// TODO Auto-generated method stub
		gradeRecordMapper.insertSelective(gradeRecord);
	}

}
