package org.service.report.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dao.MoneyLogMapper;
import org.entity.dto.MoneyLog;
import org.entity.dto.MoneyLogExample;
import org.service.report.MoneyLogServiceReport;
import org.springframework.stereotype.Service;
import org.util.DateUtil;
import org.util.PageUtil;

@Service("moneyLogServiceReport")
public class MoneyLogServiceReportImpl implements MoneyLogServiceReport {
	
	@Resource
	MoneyLogMapper moneyLogMapper;

	@Override
	public List<MoneyLog> moneyLogList(Integer pageIndex, String name,String tel, Date FromTime, Date ToTime, List<Long> channelIds,List<Long> channelIdList) throws Exception {
		MoneyLogExample moneyLogExample = new MoneyLogExample();
		MoneyLogExample.Criteria criteria = moneyLogExample.createCriteria();
		criteria.andMoneyLogStreamTypeEqualTo(2);
		criteria.andMoneyLogRefundStateEqualTo(2);//除了成功
		if(name!=null&&!name.equals("")){
			criteria.andUserNameLike("%"+name+"%");
		}
		if(tel!=null&&!tel.equals("")){
			criteria.andUserTelLike("%"+tel+"%");
		}
		if(FromTime!=null){
			criteria.andMoneyLogCreateTimeGreaterThanOrEqualTo(FromTime);
		}
		//搜索所属渠道
		if(channelIdList.size()>0){
			criteria.andMoneyLogChannelIdIn(channelIdList);
		}
		//登录所在渠道
		if(null!=channelIds){
			criteria.andMoneyLogChannelIdIn(channelIds);
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
	public Integer countMoneyLog(String name,String tel, Date FromTime, Date ToTime, List<Long> channelIds,List<Long> channelIdList) throws Exception {
		// TODO Auto-generated method stub
		
		MoneyLogExample moneyLogExample = new MoneyLogExample();
		MoneyLogExample.Criteria criteria = moneyLogExample.createCriteria();
		criteria.andMoneyLogStreamTypeEqualTo(2);
		criteria.andMoneyLogRefundStateEqualTo(2);//除了成功
		if(name!=null&&!name.equals("")){
			criteria.andUserNameLike("%"+name+"%");
		}
		if(tel!=null&&!tel.equals("")){
			criteria.andUserTelLike("%"+tel+"%");
		}
		//搜索所属渠道
		if(channelIdList.size()>0){
			criteria.andMoneyLogChannelIdIn(channelIdList);
		}
		//登录所在渠道
		if(null!=channelIds){
			criteria.andMoneyLogChannelIdIn(channelIds);
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
	public List<MoneyLog> findMoneyLog(List<Long>channelIdList,List<Long> channels, Integer type,String name,String tel, Date FromTime, Date ToTime,
			Integer pageIndex) throws Exception {
		MoneyLogExample moneyLogExample = new MoneyLogExample();
		MoneyLogExample.Criteria criteria = moneyLogExample.createCriteria();
		if(channels.size()>0){
			criteria.andMoneyLogChannelIdIn(channels);
		}
		if(null!=channelIdList){
			criteria.andMoneyLogChannelIdIn(channelIdList);
		}
		if(type!=null&&type!=-2){
			criteria.andMoneyLogStreamTypeEqualTo(type);
		}
		if(name!=null&&!name.equals("")){
			criteria.andUserNameLike("%"+name+"%");
		}
		if(tel!=null&&!tel.equals("")){
			criteria.andUserTelLike("%"+tel+"%");
		}
		if(FromTime!=null){
			criteria.andMoneyLogCreateTimeGreaterThanOrEqualTo(FromTime);
		}
		if(ToTime!=null){
			criteria.andMoneyLogCreateTimeLessThan(DateUtil.plusDate(ToTime, 1));
		}
		criteria.andMoneyLogRefundStateEqualTo(0);
		criteria.andMoneyLogStateEqualTo(1);
		// TODO Auto-generated method stub
		moneyLogExample.setOrderByClause("money_log_create_time desc");
		if(pageIndex!=null){
			moneyLogExample.setLimitStart(PageUtil.getStart(pageIndex));
			moneyLogExample.setLimitEnd(PageUtil.size);
		}
		List<MoneyLog> moneyLogs = moneyLogMapper.selectUnionByExample(moneyLogExample);
		return moneyLogs;
		
	}

	@Override
	public Integer countMoneyLognum(List<Long>channelIdList,List<Long> channels, Integer type, String name,String tel, Date FromTime, Date ToTime)
			throws Exception {
		// TODO Auto-generated method stub
		MoneyLogExample moneyLogExample = new MoneyLogExample();
		MoneyLogExample.Criteria criteria = moneyLogExample.createCriteria();
		if(channels.size()>0){
			criteria.andMoneyLogChannelIdIn(channels);
		}
		if(null!=channelIdList){
			criteria.andMoneyLogChannelIdIn(channelIdList);
		}
		if(type!=null&&type!=-2){
			criteria.andMoneyLogStreamTypeEqualTo(type);
		}
		if(name!=null&&!name.equals("")){
			criteria.andUserNameLike("%"+name+"%");
		}
		if(tel!=null&&!tel.equals("")){
			criteria.andUserTelLike("%"+tel+"%");
		}
		if(FromTime!=null){
			criteria.andMoneyLogCreateTimeGreaterThanOrEqualTo(FromTime);
		}
		if(ToTime!=null){
			criteria.andMoneyLogCreateTimeLessThan(DateUtil.plusDate(ToTime, 1));
		}
		criteria.andMoneyLogRefundStateEqualTo(0);
		criteria.andMoneyLogStateEqualTo(1);
		Integer pageCount = moneyLogMapper.countUnionByExample(moneyLogExample);
		Integer totalPage = PageUtil.getTotalPage(pageCount);
		return totalPage;
	}


}
