package org.service.report.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dao.BikeRentInfoMapper;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.BikeRentInfoExample;
import org.service.report.BikeRentInfoServiceReport;
import org.springframework.stereotype.Service;
import org.util.DateUtil;
import org.util.PageUtil;

@Service("bikeRentInfoServiceReport")
public class BikeRentInfoServiceReportImpl implements BikeRentInfoServiceReport {

	@Resource
	BikeRentInfoMapper bikeRentInfoMapper;


	public Integer countAllBikeRentInfo(List<Long> channelIds, Long bikeId, Long bikerentId, String userPhone,
			Date rentFromTime, Date rentTotime, Date returnFromTime, Date returnTotime, List<Long> blockrentIds,
			List<Long> blockreturnIds) throws Exception {
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		if (null != bikeId && 0 != bikeId)
			criteria.andRentInfoBikeIdEqualTo(bikeId);
		criteria.andRentIsdelEqualTo(0);

		if (blockrentIds != null) {
			criteria.andRentStartBlockIdIn(blockrentIds);
		}

		if (blockreturnIds != null) {
			criteria.andRentEndBlockIdIn(blockreturnIds);
		}

		if (bikerentId != null) {
			criteria.andRentInfoIdEqualTo(bikerentId);
		}
		if (userPhone != null && !userPhone.equals("")) {
			criteria.andRentTelLike("%" + userPhone + "%");
		}
		if (rentFromTime != null) {
			criteria.andRentStarttimeGreaterThanOrEqualTo(rentFromTime);
		}

		if (rentTotime != null) {
			criteria.andRentStarttimeLessThan(DateUtil.plusDate(rentTotime, 1));
		}

		if (returnFromTime != null) {
			criteria.andRentEndtimeGreaterThanOrEqualTo(returnFromTime);
		}

		if (returnTotime != null) {
			criteria.andRentEndtimeLessThan(DateUtil.plusDate(returnTotime, 1));
		}
		// 渠道id
		if (null != channelIds ) {
			criteria.andRentBikeChannelIdIn(channelIds);
		}

		Integer pageCount = bikeRentInfoMapper.countUnionByExample(bikeRentInfoExample);
		Integer totalPage = PageUtil.getTotalPage(pageCount);
		return totalPage;
	}



	@Override
	public List<BikeRentInfo> findUserBikeRentinfo(BikeRentInfo bikeRentInfo, Integer pageIndex, Integer pageSize)
			throws Exception {
		// TODO Auto-generated method stub
		BikeRentInfoExample example = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = example.createCriteria();
		criteria.andRentInfoUserIdEqualTo(bikeRentInfo.getRentInfoUserId());
		if (null != bikeRentInfo.getRentBlockIds() && 0 != bikeRentInfo.getRentBlockIds().size())// 起点方格
			criteria.andRentStartBlockIdIn(bikeRentInfo.getRentBlockIds());
		if (null != bikeRentInfo.getReturnBlockIds() && 0 != bikeRentInfo.getReturnBlockIds().size())// 终点方格
			criteria.andRentEndBlockIdIn(bikeRentInfo.getReturnBlockIds());
		if (null != bikeRentInfo.getRentStartBlockId() && -1 == bikeRentInfo.getRentStartBlockId())// 输入了地点但是没有对应的区域方格
																									// 标志-1
			criteria.andRentStartBlockIdEqualTo(bikeRentInfo.getRentStartBlockId());
		if (null != bikeRentInfo.getRentInfoId() && 0 != bikeRentInfo.getRentInfoId())// 租赁编号
			criteria.andRentInfoIdEqualTo(bikeRentInfo.getRentInfoId());
		if (null != bikeRentInfo.getBikeIds() && 0 != bikeRentInfo.getBikeIds().size())// 与车Code对应的车Id
			criteria.andRentInfoBikeIdIn(bikeRentInfo.getBikeIds());
		if (null != bikeRentInfo.getRentFromTime() && !"".equals(bikeRentInfo.getRentFromTime())) {// 租赁时间
			if (null != bikeRentInfo.getRentTotime() && !"".equals(bikeRentInfo.getRentTotime()))
				criteria.andRentStarttimeBetween(bikeRentInfo.getRentFromTime(),DateUtil.plusDate(bikeRentInfo.getRentTotime(), 1));
			else {
				criteria.andRentStarttimeBetween(bikeRentInfo.getRentFromTime(), new Date());
			}
		} else if (null != bikeRentInfo.getRentTotime() && !"".equals(bikeRentInfo.getRentTotime()))
			criteria.andRentStarttimeLessThan(DateUtil.plusDate(bikeRentInfo.getRentTotime(), 1));
		if (null != bikeRentInfo.getReturnFromTime() && !"".equals(bikeRentInfo.getReturnFromTime())) {// 归还时间
			if (null != bikeRentInfo.getReturnTotime() && !"".equals(bikeRentInfo.getReturnTotime()))
				criteria.andRentEndtimeBetween(bikeRentInfo.getReturnFromTime(), DateUtil.plusDate(bikeRentInfo.getReturnTotime(), 1));
			else {
				criteria.andRentEndtimeBetween(bikeRentInfo.getReturnFromTime(), new Date());
			}
		} else if (null != bikeRentInfo.getReturnTotime() && !"".equals(bikeRentInfo.getReturnTotime()))
			criteria.andRentEndtimeLessThan(DateUtil.plusDate(bikeRentInfo.getReturnTotime(), 1));
		criteria.andRentIsdelEqualTo(0);
		example.setOrderByClause("rent_starttime desc");
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(pageSize);
		return bikeRentInfoMapper.selectByExample(example);
	}

	@Override
	public Integer findUserBikeRentinfoCount(BikeRentInfo bikeRentInfo) throws Exception {
		// TODO Auto-generated method stub
		BikeRentInfoExample example = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = example.createCriteria();
		criteria.andRentInfoUserIdEqualTo(bikeRentInfo.getRentInfoUserId());
		if (null != bikeRentInfo.getRentBlockIds() && 0 != bikeRentInfo.getRentBlockIds().size())// 起点方格
			criteria.andRentStartBlockIdIn(bikeRentInfo.getRentBlockIds());
		if (null != bikeRentInfo.getReturnBlockIds() && 0 != bikeRentInfo.getReturnBlockIds().size())// 终点方格
			criteria.andRentEndBlockIdIn(bikeRentInfo.getReturnBlockIds());
		if (null != bikeRentInfo.getRentStartBlockId() && -1 == bikeRentInfo.getRentStartBlockId())// 输入了地点但是没有对应的区域方格
																									// 标志-1
			criteria.andRentStartBlockIdEqualTo(bikeRentInfo.getRentStartBlockId());
		if (null != bikeRentInfo.getRentInfoId() && 0 != bikeRentInfo.getRentInfoId())// 租赁编号
			criteria.andRentInfoIdEqualTo(bikeRentInfo.getRentInfoId());
		if (null != bikeRentInfo.getBikeIds() && 0 != bikeRentInfo.getBikeIds().size())// 与车Code对应的车Id
			criteria.andRentInfoBikeIdIn(bikeRentInfo.getBikeIds());
		if (null != bikeRentInfo.getRentFromTime() && !"".equals(bikeRentInfo.getRentFromTime())) {// 租赁时间
			if (null != bikeRentInfo.getRentTotime() && !"".equals(bikeRentInfo.getRentTotime()))
				criteria.andRentStarttimeBetween(bikeRentInfo.getRentFromTime(), DateUtil.plusDate(bikeRentInfo.getRentTotime(), 1));
			else {
				criteria.andRentStarttimeBetween(bikeRentInfo.getRentFromTime(), new Date());
			}
		} else if (null != bikeRentInfo.getRentTotime() && !"".equals(bikeRentInfo.getRentTotime()))
			criteria.andRentStarttimeLessThan(DateUtil.plusDate(bikeRentInfo.getRentTotime(), 1));
		if (null != bikeRentInfo.getReturnFromTime() && !"".equals(bikeRentInfo.getReturnFromTime())) {// 归还时间
			if (null != bikeRentInfo.getReturnTotime() && !"".equals(bikeRentInfo.getReturnTotime()))
				criteria.andRentEndtimeBetween(bikeRentInfo.getReturnFromTime(), DateUtil.plusDate(bikeRentInfo.getReturnTotime(), 1));
			else {
				criteria.andRentEndtimeBetween(bikeRentInfo.getReturnFromTime(), new Date());
			}
		} else if (null != bikeRentInfo.getReturnTotime() && !"".equals(bikeRentInfo.getReturnTotime()))
			criteria.andRentEndtimeLessThan(DateUtil.plusDate(bikeRentInfo.getReturnTotime(), 1));
		criteria.andRentIsdelEqualTo(0);
		return bikeRentInfoMapper.countByExample(example);
	}

	@Override
	public List<BikeRentInfo> findNewBikeRentInfos(List<Long> channelIdList,Integer pageIndex,Date returnFromTime,Date returnTotime,String tel,String name,String bikeCode,List<Long> channels) throws Exception {
		BikeRentInfoExample example = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = example.createCriteria();
		//criteria.andRentIsdelEqualTo(0);
		criteria.andRentStateEqualTo(1);
		if(channelIdList.size()>0){
			criteria.andRentBikeChannelIdIn(channelIdList);
		}
		if(returnFromTime!=null){
			criteria.andRentEndtimeGreaterThanOrEqualTo(returnFromTime);
		}
        if(returnTotime!=null){
        	criteria.andRentEndtimeLessThan(DateUtil.plusDate(returnTotime, 1));
		}
        if(channels!=null){
        	criteria.andRentBikeChannelIdIn(channels);
        }
        if(null!=tel&&!tel.equals("")){
        	criteria.andRentTelLike("%"+tel+"%");
        }
        if(null!=name&&!name.equals("")){
        	criteria.andUserNameLike("%"+name+"%");
        }
        if(null!=bikeCode&&!bikeCode.equals("")){
        	criteria.andBikeCodeLike("%"+bikeCode+"%");
        }
    	example.setOrderByClause("rent_starttime desc");
        if(null!=pageIndex){
        	example.setLimitStart(PageUtil.getStart(pageIndex));
    		example.setLimitEnd(PageUtil.size);
        }
		
		List<BikeRentInfo> bikeRentInfos = bikeRentInfoMapper.selectUnionByExample(example);
		return bikeRentInfos;
	}

	@Override
	public Integer countBikeRentInfos(List<Long> channelIdList,Date returnFromTime,Date returnTotime,String tel,String name,String bikeCode,List<Long> channels) throws Exception {
		// TODO Auto-generated method stub
		BikeRentInfoExample example = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = example.createCriteria();
		//criteria.andRentIsdelEqualTo(0);
		criteria.andRentStateEqualTo(1);
		if(channelIdList.size()>0){
			criteria.andRentBikeChannelIdIn(channelIdList);
		}
		if(returnFromTime!=null){
			criteria.andRentEndtimeGreaterThanOrEqualTo(returnFromTime);
		}
        if(returnTotime!=null){
        	criteria.andRentEndtimeLessThan(DateUtil.plusDate(returnTotime, 1));
		}
        if(channels!=null){
        	criteria.andRentBikeChannelIdIn(channels);
        }
        if(null!=tel&&!tel.equals("")){
        	criteria.andRentTelLike("%"+tel+"%");
        }
        if(null!=name&&!name.equals("")){
        	criteria.andUserNameLike("%"+name+"%");
        }
        if(null!=bikeCode&&!bikeCode.equals("")){
        	criteria.andBikeCodeLike("%"+bikeCode+"%");
        }
		Integer pageCount = bikeRentInfoMapper.countUnionByExample(example);
		Integer totalPage = PageUtil.getTotalPage(pageCount);
		return totalPage;
	}



	@Override
	public List<BikeRentInfo> findBikeRentInfo(List<Long>channelIdList,List<Long> channelIds, Long bikeId, Date rentstarttime, Date rentendtime, Integer pageIndex,Integer starttime,Integer endtime)
			throws Exception {
		// TODO Auto-generated method stub
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		if (null != channelIds && channelIds.size()>0) {
			criteria.andRentBikeChannelIdIn(channelIds);
		}
		if (null != channelIdList && channelIdList.size()>0) {
			criteria.andRentBikeChannelIdIn(channelIdList);
		}
		if (null != bikeId && !"".equals(bikeId)) {
			criteria.andBikeCodeLike("%"+bikeId+"%");
		}
		if (rentstarttime != null) {
			criteria.andRentStarttimeGreaterThanOrEqualTo(rentstarttime);
		}

		if (rentendtime != null) {
			criteria.andRentStarttimeLessThan(DateUtil.plusDate(rentendtime, 1));
		}
		if(starttime!=null){
			criteria.andRentLongtimeGreaterThanOrEqualTo(starttime);
		}
		if (endtime != null) {
			criteria.andRentLongtimeLessThan(endtime);
		}
		criteria.andRentStateEqualTo(1);
		criteria.andRentIsdelEqualTo(0);
		bikeRentInfoExample.setOrderByClause("rent_starttime desc");
		if(pageIndex!=null){
			bikeRentInfoExample.setLimitStart(PageUtil.getStart(pageIndex));
			bikeRentInfoExample.setLimitEnd(PageUtil.size);
		}
		List<BikeRentInfo> bikeRentInfoList = bikeRentInfoMapper.selectUnionByExample(bikeRentInfoExample);
		return bikeRentInfoList;
	}

	@Override
	public Integer CountBikeRentInfo(List<Long>channelIdList,List<Long> channelIds, Long bikeId, Date rentstarttime, Date rentendtime,Integer starttime,Integer endtime) throws Exception {
		// TODO Auto-generated method stub
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		if (null != channelIds && channelIds.size()>0) {
			criteria.andRentBikeChannelIdIn(channelIds);
		}
		if (null != channelIdList && channelIdList.size()>0) {
			criteria.andRentBikeChannelIdIn(channelIdList);
		}
		if (null != bikeId && !"".equals(bikeId)) {
			criteria.andBikeCodeLike("%"+bikeId+"%");
		}
		if (rentstarttime != null) {
			criteria.andRentStarttimeGreaterThanOrEqualTo(rentstarttime);
		}

		if (rentendtime != null) {
			criteria.andRentStarttimeLessThan(DateUtil.plusDate(rentendtime, 1));
		}
		if(starttime!=null){
			criteria.andRentLongtimeGreaterThanOrEqualTo(starttime);
		}
		if (endtime != null) {
			criteria.andRentLongtimeLessThan(endtime);
		}
		criteria.andRentStateEqualTo(1);
		criteria.andRentIsdelEqualTo(0);
		bikeRentInfoExample.setOrderByClause("rent_pay_time desc");
		return bikeRentInfoMapper.countUnionByExample(bikeRentInfoExample);
	}

	@Override
	public Integer countRentInfoByFixed(Long fixedReturnId,Date fromtime,Date totime) throws Exception {
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		if (null != fixedReturnId) {
			criteria.andRentStartFixedIdEqualTo(fixedReturnId);
		}
		if(null!=fromtime){
			criteria.andRentStarttimeGreaterThanOrEqualTo(fromtime);
		}
		if(null!=totime){
			criteria.andRentStarttimeLessThan(DateUtil.plusDate(totime, 1));
		}
		criteria.andRentIsdelEqualTo(0);

		return bikeRentInfoMapper.countByExample(bikeRentInfoExample);
	}

	@Override
	public Integer countByChannelIds(List<Long> channelIds) throws Exception {
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		if (null != channelIds) {
			criteria.andRentBikeChannelIdIn(channelIds);
		}
		criteria.andRentIsdelEqualTo(0);
		return bikeRentInfoMapper.countByExample(bikeRentInfoExample);
	}



	@Override
	public Integer countRentInfoByEndFixed(Long fixedReturnId, Date fromTime, Date toTime) throws Exception {
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		if (null != fixedReturnId) {
			criteria.andRentEndFixedIdEqualTo(fixedReturnId);
		}
		if(null!=fromTime){
			criteria.andRentEndtimeGreaterThanOrEqualTo(fromTime);
		}
		if(null!=toTime){
			criteria.andRentEndtimeLessThan(DateUtil.plusDate(toTime, 1));
		}
		criteria.andRentIsdelEqualTo(0);

		return bikeRentInfoMapper.countByExample(bikeRentInfoExample);
	}



	@Override
	public Integer countReturnByChannelIds(List<Long> channelIds) throws Exception {
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		if (null != channelIds) {
			criteria.andRentBikeChannelIdIn(channelIds);
		}
		criteria.andRentIsdelEqualTo(0);
		criteria.andRentEndtimeIsNotNull();
		return bikeRentInfoMapper.countByExample(bikeRentInfoExample);
	}



	@Override
	public Integer countRentInfoByRideTime(Integer longTime1, Integer longTime2, Date fromTime, Date toTime)
			throws Exception {
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		if(null!=fromTime){
			criteria.andRentEndtimeGreaterThanOrEqualTo(fromTime);
		}
		if(null!=toTime){
			criteria.andRentEndtimeLessThan(DateUtil.plusDate(toTime, 1));
		}
		if(null!=longTime1){
			criteria.andRentLongtimeGreaterThanOrEqualTo(longTime1);
		}
		if(null!=longTime2){
			criteria.andRentLongtimeLessThan(longTime2);
		}
		criteria.andRentIsdelEqualTo(0);
		criteria.andRentEndtimeIsNotNull();
		return bikeRentInfoMapper.countByExample(bikeRentInfoExample);
	}
	
}
