package org.service.cms.impl.read;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dao.OperateLogMapper;
import org.entity.dto.OperateLog;
import org.entity.dto.OperateLogExample;
import org.service.cms.read.OperateLogServiceRead;
import org.springframework.stereotype.Service;
import org.util.DateUtil;
import org.util.PageUtil;
@Service("operateLogServiceRead")
public class OperateLogServiceReadImpl implements OperateLogServiceRead {
	
	@Resource
	OperateLogMapper operateLogMapper;

	@Override
	public List<OperateLog> findOperateByCondition(List<Long> adminIds,OperateLog operateLog,Integer pageIndex, Integer page_size_web) throws Exception {
		OperateLogExample example = new OperateLogExample();
		OperateLogExample.Criteria criteria=example.createCriteria();
		
		if (null != operateLog.getFromTime() && !"".equals(operateLog.getFromTime())) {//查询的创建时间
			if (null != operateLog.getToTime() && !"".equals(operateLog.getToTime()))
				criteria.andOperateTimeBetween(DateUtil.changStringDate03(operateLog.getFromTime()),
						DateUtil.plusDate(DateUtil.changStringDate03(operateLog.getToTime()), 1));
			else {
				criteria.andOperateTimeBetween(DateUtil.changStringDate03(operateLog.getFromTime()), new Date());
			}
		} else if (null != operateLog.getToTime() && !"".equals(operateLog.getToTime()))
			criteria.andOperateTimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(operateLog.getToTime()), 1));
		if(null!=operateLog.getOperateRemark()&&!"".equals(operateLog.getOperateRemark())){
			criteria.andOperateRemarkLike("%"+operateLog.getOperateRemark()+"%");
		}
		if(adminIds.size()>0){
			criteria.andOperateAdminIdIn(adminIds);
		}
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(page_size_web);
		example.setOrderByClause("operate_time desc");

		return operateLogMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public Integer countAllOperate(List<Long> adminIds,OperateLog operateLog) throws Exception {
		OperateLogExample example = new OperateLogExample();
		OperateLogExample.Criteria criteria=example.createCriteria();
		
		if (null != operateLog.getFromTime() && !"".equals(operateLog.getFromTime())) {//查询的创建时间
			if (null != operateLog.getToTime() && !"".equals(operateLog.getToTime()))
				criteria.andOperateTimeBetween(DateUtil.changStringDate03(operateLog.getFromTime()),
						DateUtil.plusDate(DateUtil.changStringDate03(operateLog.getToTime()), 1));
			else {
				criteria.andOperateTimeBetween(DateUtil.changStringDate03(operateLog.getFromTime()), new Date());
			}
		} else if (null != operateLog.getToTime() && !"".equals(operateLog.getToTime()))
			criteria.andOperateTimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(operateLog.getToTime()), 1));
		
		if(null!=operateLog.getOperateRemark()&&!"".equals(operateLog.getOperateRemark())){
			criteria.andOperateRemarkLike("%"+operateLog.getOperateRemark()+"%");
		}
		
		if(adminIds.size()>0){
			criteria.andOperateAdminIdIn(adminIds);
		}
		return operateLogMapper.countByExample(example);
	}



}
