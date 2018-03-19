package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.GradeRecordMapper;
import org.entity.dto.GradeRecord;
import org.entity.dto.GradeRecordExample;
import org.service.weixin.read.GradeWxServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;
@Service("gradeWxServiceRead")
public class GradeWxServiceReadImpl implements GradeWxServiceRead {
	
	@Resource
	GradeRecordMapper gradeRecordMapper;

	@Override
	public List<GradeRecord> findByUserId(Integer pageIndex, Integer pageSize,Long userId,Integer tName) throws Exception {
		GradeRecordExample example = new GradeRecordExample();
		GradeRecordExample.Criteria criteria = example.createCriteria();
		criteria.andGradeUserIdEqualTo(userId);
		if(null!=tName&&-1!=tName){
			criteria.andGradeStateEqualTo(tName);
		}
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(pageSize);
		example.setOrderByClause("grade_create_time desc");
		return gradeRecordMapper.selectByExample(example);
	}

	@Override
	public Integer countAllRecord(Long userId,Integer tName) throws Exception {
		GradeRecordExample example = new GradeRecordExample();
		GradeRecordExample.Criteria criteria = example.createCriteria();
		if(null!=tName&&-1!=tName){
			criteria.andGradeStateEqualTo(tName);
		}
		criteria.andGradeUserIdEqualTo(userId);
		return gradeRecordMapper.countByExample(example);
	}


}
