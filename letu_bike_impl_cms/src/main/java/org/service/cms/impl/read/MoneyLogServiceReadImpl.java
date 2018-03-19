package org.service.cms.impl.read;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dao.MoneyLogMapper;
import org.entity.dto.MoneyLog;
import org.entity.dto.MoneyLogExample;
import org.service.cms.read.MoneyLogServiceRead;
import org.util.DateUtil;
import org.util.PageUtil;

public class MoneyLogServiceReadImpl implements MoneyLogServiceRead {
	
	@Resource
	MoneyLogMapper moneyLogMapper;

	@Override
	public List<MoneyLog> moneyLogList(Integer pageIndex, Integer item, Date FromTime, Date ToTime) throws Exception {
		MoneyLogExample moneyLogExample = new MoneyLogExample();
		MoneyLogExample.Criteria criteria = moneyLogExample.createCriteria();
		criteria.andMoneyLogStreamTypeNotEqualTo(1);
		//criteria.andMoneyLogStateEqualTo(1);
		if(item!=null&&item!=-1){
			criteria.andMoneyLogStreamTypeEqualTo(item);
		}
		if(FromTime!=null){
			criteria.andMoneyLogCreateTimeGreaterThanOrEqualTo(FromTime);
		}
		if(ToTime!=null){
			criteria.andMoneyLogCreateTimeLessThan(DateUtil.plusDate(ToTime, 1));
		}
		moneyLogExample.setOrderByClause("money_log_create_time desc");
		if(pageIndex!=null){
			moneyLogExample.setLimitStart(PageUtil.getStart(pageIndex));
			moneyLogExample.setLimitEnd(PageUtil.size);
		}
		List<MoneyLog> moneyLogs = moneyLogMapper.selectUnionByExample(moneyLogExample);
		return moneyLogs;
	}

	@Override
	public Integer countMoneyLog(Integer item, Date FromTime, Date ToTime) throws Exception {
		// TODO Auto-generated method stub
		
		MoneyLogExample moneyLogExample = new MoneyLogExample();
		MoneyLogExample.Criteria criteria = moneyLogExample.createCriteria();
		criteria.andMoneyLogStreamTypeNotEqualTo(1);
		//criteria.andMoneyLogStateEqualTo(1);
		if(item!=null&&item!=-1){
			criteria.andMoneyLogStreamTypeEqualTo(item);
		}
		if(FromTime!=null){
			criteria.andMoneyLogCreateTimeGreaterThanOrEqualTo(FromTime);
		}
		if(ToTime!=null){
			criteria.andMoneyLogCreateTimeLessThan(DateUtil.plusDate(ToTime, 1));
		}
		Integer pageCount = moneyLogMapper.countUnionByExample(moneyLogExample);
		Integer totalPage = PageUtil.getTotalPage(pageCount);
		return totalPage;
	}

	@Override
	public List<MoneyLog> refundList() throws Exception {
		// TODO Auto-generated method stub
		MoneyLogExample moneyLogExample = new MoneyLogExample();
		MoneyLogExample.Criteria criteria = moneyLogExample.createCriteria();
		criteria.andMoneyLogStreamTypeEqualTo(2);
		criteria.andMoneyLogRefundStateNotEqualTo(2);//除了成功
		criteria.andMoneyLogRefundStateEqualTo(1);
		List<MoneyLog> refundList = moneyLogMapper.selectUnionByExample(moneyLogExample);
		return refundList;
	}

	

}
