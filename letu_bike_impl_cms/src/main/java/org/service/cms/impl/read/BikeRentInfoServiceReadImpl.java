package org.service.cms.impl.read;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dao.BikeRentInfoMapper;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.BikeRentInfoExample;
import org.service.cms.read.BikeRentInfoServiceRead;
import org.springframework.stereotype.Service;
import org.util.DateUtil;
import org.util.PageUtil;

@Service("bikeRentInfoServiceRead")
public class BikeRentInfoServiceReadImpl implements BikeRentInfoServiceRead {

	@Resource
	BikeRentInfoMapper bikeRentInfoMapper;

	@Override
	public List<BikeRentInfo> findAllBikeRentInfo(Integer pageIndex, List<Long> channelIds, Long bikeId, Long bikerentId,
			String userPhone, Date rentFromTime, Date rentTotime, Date returnFromTime, Date returnTotime,
			 Long rentStartFixedId,Long rentEndFixedId,Long channelid) throws Exception {
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		if (null != bikeId && 0 != bikeId)
			criteria.andRentInfoBikeIdEqualTo(bikeId);
		
		criteria.andRentIsdelEqualTo(0);

		//起点
		if (null != rentStartFixedId&&-1!=rentStartFixedId) {
			criteria.andRentStartFixedIdEqualTo(rentStartFixedId);
		}
		// 终点
		if (null != rentEndFixedId&&-1!=rentEndFixedId) {
			criteria.andRentEndFixedIdEqualTo(rentEndFixedId);
		}		

		if (bikerentId != null) {
			criteria.andRentInfoIdEqualTo(bikerentId);
		}
		if (userPhone != null && !userPhone.equals("")) {
			criteria.andRentTelLike("%" + userPhone.trim() + "%");
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
		
		if (null != channelid && channelid !=-1) {
			criteria.andRentBikeChannelIdEqualTo(channelid);
		}
		
		bikeRentInfoExample.setOrderByClause("FIELD(rent_starttime,'') asc,rent_starttime desc");
		bikeRentInfoExample.setLimitStart(PageUtil.getStart(pageIndex));
		bikeRentInfoExample.setLimitEnd(PageUtil.size);
		List<BikeRentInfo> bikeRentInfoList = bikeRentInfoMapper.selectUnionByExample(bikeRentInfoExample);
		return bikeRentInfoList;
	}

	public Integer countAllBikeRentInfo(List<Long> channelIds, Long bikeId, Long bikerentId, String userPhone,
			Date rentFromTime, Date rentTotime, Date returnFromTime, Date returnTotime,
			Long rentStartFixedId,Long rentEndFixedId,Long channelid) throws Exception {
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		if (null != bikeId && 0 != bikeId)
			criteria.andRentInfoBikeIdEqualTo(bikeId);
		criteria.andRentIsdelEqualTo(0);

		//起点
		if (null != rentStartFixedId&&-1!=rentStartFixedId) {
			criteria.andRentStartFixedIdEqualTo(rentStartFixedId);
		}
		// 终点
		if (null != rentEndFixedId&&-1!=rentEndFixedId) {
			criteria.andRentEndFixedIdEqualTo(rentEndFixedId);
		}	

		if (bikerentId != null) {
			criteria.andRentInfoIdEqualTo(bikerentId);
		}
		if (userPhone != null && !userPhone.equals("")) {
			criteria.andRentTelLike("%" + userPhone.trim() + "%");
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
		
		if (null != channelid && channelid !=-1) {
			criteria.andRentBikeChannelIdEqualTo(channelid);
		}

		Integer pageCount = bikeRentInfoMapper.countUnionByExample(bikeRentInfoExample);
		Integer totalPage = PageUtil.getTotalPage(pageCount);
		return totalPage;
	}

	@Override
	public BikeRentInfo findBikeRentInfoById(Long bikeRentId) throws Exception {
		BikeRentInfo bikeRentInfo = bikeRentInfoMapper.selectUnionByPrimaryKey(bikeRentId);
		return bikeRentInfo;
	}

	@Override
	public List<BikeRentInfo> findBikeRentInfoByBikeId(Long bikeId) throws Exception {
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		criteria.andRentInfoBikeIdEqualTo(bikeId);
		criteria.andRentIsdelEqualTo(0);
		bikeRentInfoExample.setOrderByClause("rent_endtime desc");
		List<BikeRentInfo> bikeRentInfoList = bikeRentInfoMapper.selectByExample(bikeRentInfoExample);
			return bikeRentInfoList;
	}

	@Override
	public List<BikeRentInfo> findAllBikeRentInfos(List<Long> channelIds, Integer pageIndex, Integer pageSize, String userTel,
			Long rentInfoId, String bikeCode, Integer rentState, Date rentTime, Date rentTime2, Date returnTime,
			Date returnTime2, Long rentStartFixedId,Long rentEndFixedId,Long channelid,Integer flag) throws Exception {
		// TODO Auto-generated method stub
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		// 终点
		// 如果没输入地址
		if (null != rentStartFixedId&&-1!=rentStartFixedId) {
			criteria.andRentStartFixedIdEqualTo(rentStartFixedId);
		}
		// 起点
		if (null != rentEndFixedId&&-1!=rentEndFixedId) {
			criteria.andRentEndFixedIdEqualTo(rentEndFixedId);
		}
		// 渠道
		if (null != channelIds ) {
			criteria.andRentBikeChannelIdIn(channelIds);
		}
		
		if (null != channelid && channelid !=-1) {
			criteria.andRentBikeChannelIdEqualTo(channelid);
		}

		// 租赁账号
		if (null != userTel && !"".equals(userTel)) {
			criteria.andRentTelLike("%" + userTel.trim() + "%");
		}

		// 租赁编号
		if (null != rentInfoId && !"".equals(rentInfoId)) {
			criteria.andRentInfoIdEqualTo(rentInfoId);
		}
		// 车牌号
		if (null != bikeCode && !"".equals(bikeCode)) {
			criteria.andBikeCodeLike("%"+bikeCode.trim()+"%");
		}
		// 租赁状态
		if (null != rentState && !"".equals(rentState)) {
			criteria.andRentStateEqualTo(rentState);
		}
		// 租赁开始时间
		if (null != rentTime && !"".equals(rentTime)) {
			criteria.andRentStarttimeGreaterThanOrEqualTo(rentTime);
		}

		// 租赁结束时间
		if (null != rentTime2 && !"".equals(rentTime2)) {
			criteria.andRentStarttimeLessThan(DateUtil.plusDate(rentTime2, 1));
		}

		// 归还时间
		if (null != returnTime && !"".equals(returnTime)) {
			criteria.andRentEndtimeGreaterThanOrEqualTo(returnTime);
		}
		// 归还时间
		if (null != returnTime2 && !"".equals(returnTime2)) {
			criteria.andRentEndtimeLessThan(DateUtil.plusDate(returnTime2, 1));
		}
		// 未删
		criteria.andRentIsdelEqualTo(0);
		bikeRentInfoExample.setLimitStart(PageUtil.getStart(pageIndex));
		bikeRentInfoExample.setLimitEnd(pageSize);
		if(flag!=null){
			if(flag==0){
				bikeRentInfoExample.setOrderByClause("rent_state=2 desc,rent_starttime desc");
			}else if(flag==1){
				bikeRentInfoExample.setOrderByClause("rent_starttime desc");
			}else if(flag==2){
				bikeRentInfoExample.setOrderByClause("rent_endtime desc");
			}
		}
		
		List<BikeRentInfo> bikeRentInfos = bikeRentInfoMapper.selectUnionByExample(bikeRentInfoExample);
		return bikeRentInfos;
	}

	@Override
	public Integer findAllBikeInfoCount(List<Long> channelIds,String userTel, Long rentInfoId, String bikeCode, Integer rentState, Date rentTime,
			Date rentTime2, Date returnTime, Date returnTime2, Long rentStartFixedId,Long rentEndFixedId,Long channelid) {
		// TODO Auto-generated method stub
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		// 终点
		// 如果没输入地址
		if (null != rentStartFixedId&&-1!=rentStartFixedId) {
			criteria.andRentStartFixedIdEqualTo(rentStartFixedId);
		}
		// 起点
		if (null != rentEndFixedId&&-1!=rentEndFixedId) {
			criteria.andRentEndFixedIdEqualTo(rentEndFixedId);
		}
		// 租赁账号
		if (null != userTel && !"".equals(userTel)) {
			criteria.andRentTelLike("%" + userTel.trim() + "%");
		}
		// 渠道
		if (null != channelIds ) {
			criteria.andRentBikeChannelIdIn(channelIds);
		}
		
		if (null != channelid && channelid !=-1) {
			criteria.andRentBikeChannelIdEqualTo(channelid);
		}
		// 租赁编号
		if (null != rentInfoId && !"".equals(rentInfoId)) {
			criteria.andRentInfoIdEqualTo(rentInfoId);
		}
		// 车牌号
		if (null != bikeCode && !"".equals(bikeCode)) {
			criteria.andBikeCodeLike("%"+bikeCode.trim()+"%");
		}
		// 租赁状态
		if (null != rentState && !"".equals(rentState)) {
			criteria.andRentStateEqualTo(rentState);
		}
		// 租赁开始时间
		if (null != rentTime && !"".equals(rentTime)) {
			criteria.andRentStarttimeGreaterThanOrEqualTo(rentTime);
		}

		// 租赁结束时间
		if (null != rentTime2 && !"".equals(rentTime2)) {
			criteria.andRentStarttimeLessThan(DateUtil.plusDate(rentTime2, 1));
		}

		// 归还时间
		if (null != returnTime && !"".equals(returnTime)) {
			criteria.andRentEndtimeGreaterThanOrEqualTo(returnTime);
		}
		// 归还时间
		if (null != returnTime2 && !"".equals(returnTime2)) {
			criteria.andRentEndtimeLessThan(DateUtil.plusDate(returnTime2, 1));
		}
		criteria.andRentIsdelEqualTo(0);
		return bikeRentInfoMapper.countUnionByExample(bikeRentInfoExample);
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
	public List<BikeRentInfo> findBikeRentInfos(Long channelId,Integer pageIndex,Date returnFromTime,Date returnTotime,Integer moneyNum,List<Long> channels) throws Exception {
		BikeRentInfoExample example = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = example.createCriteria();
		//criteria.andRentIsdelEqualTo(0);
		criteria.andRentStateEqualTo(1);
		if(channelId!=null&&channelId!=-1){
			criteria.andRentBikeChannelIdEqualTo(channelId);
		}
		if(returnFromTime!=null){
			criteria.andRentPayTimeGreaterThanOrEqualTo(returnFromTime);
		}
        if(returnTotime!=null){
        	criteria.andRentPayTimeLessThan(DateUtil.plusDate(returnTotime, 1));
		}
        if (null != channels ) {
			criteria.andRentBikeChannelIdIn(channels);
		}
        
        if (null != moneyNum&&moneyNum!=-1 ) {
			criteria.andRentPriceGreaterThan(new BigDecimal(0));
		}
		
    	example.setOrderByClause("rent_pay_time desc");
        if(null!=pageIndex){
        	example.setLimitStart(PageUtil.getStart(pageIndex));
    		example.setLimitEnd(PageUtil.size);
        }
		
		List<BikeRentInfo> bikeRentInfos = bikeRentInfoMapper.selectUnionByExample(example);
		return bikeRentInfos;
	}

	@Override
	public Integer countBikeRentInfos(Long channelId,Date returnFromTime,Date returnTotime,Integer moneyNum,List<Long> channels) throws Exception {
		// TODO Auto-generated method stub
		BikeRentInfoExample example = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = example.createCriteria();
		//criteria.andRentIsdelEqualTo(0);
		criteria.andRentStateEqualTo(1);
		if(channelId!=null&&channelId!=-1){
			criteria.andRentBikeChannelIdEqualTo(channelId);
		}
		if(returnFromTime!=null){
			criteria.andRentPayTimeGreaterThanOrEqualTo(returnFromTime);
		}
        if(returnTotime!=null){
        	criteria.andRentPayTimeLessThan(DateUtil.plusDate(returnTotime, 1));
		}
        if(channels!=null){
        	criteria.andRentBikeChannelIdIn(channels);
        }
        if (null != moneyNum&&moneyNum!=-1 ) {
			criteria.andRentPriceGreaterThan(new BigDecimal(0));
		}
		Integer pageCount = bikeRentInfoMapper.countUnionByExample(example);
		Integer totalPage = PageUtil.getTotalPage(pageCount);
		return totalPage;
	}

	@Override
	public List<BikeRentInfo> findByBikeId(Long bikeId,Integer pageIndex,Integer page_size_weixin) throws Exception {
		// TODO Auto-generated method stub
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		criteria.andRentInfoBikeIdEqualTo(bikeId);
		criteria.andRentIsdelEqualTo(0);
		bikeRentInfoExample.setLimitStart(PageUtil.getWeixinStart(pageIndex));
		bikeRentInfoExample.setLimitEnd(page_size_weixin);
		bikeRentInfoExample.setOrderByClause("rent_info_id desc");
		return bikeRentInfoMapper.selectByExampleUser(bikeRentInfoExample);
	}
	
	@Override
	public Integer findCountByBikeId(Long bikeId) throws Exception {
		// TODO Auto-generated method stub
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		criteria.andRentInfoBikeIdEqualTo(bikeId);
		criteria.andRentIsdelEqualTo(0);
		return bikeRentInfoMapper.countByExample(bikeRentInfoExample);
	}

	@Override
	public List<BikeRentInfo> findVipUserBikeRentinfo(BikeRentInfo bikeRentInfo, Integer pageIndex, Integer pageSize)
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
		criteria.andRentIsvillagerEqualTo(1);
		example.setOrderByClause("rent_starttime desc");
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(pageSize);
		return bikeRentInfoMapper.selectByExample(example);
		
	}

	@Override
	public Integer findVipUserBikeRentinfoCount(BikeRentInfo bikeRentInfo) throws Exception {
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
		criteria.andRentIsvillagerEqualTo(1);
		return bikeRentInfoMapper.countByExample(example);
		
	}

	@Override
	public List<BikeRentInfo> findNotRent() throws Exception {
		// TODO Auto-generated method stub
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		criteria.andRentStateEqualTo(2);
		criteria.andRentIsdelEqualTo(0);
		List<BikeRentInfo> bikeRentInfoList = bikeRentInfoMapper.selectUnionByExample(bikeRentInfoExample);
		return bikeRentInfoList;
	}

	@Override
	public List<BikeRentInfo> findByInvoiceId(Long invoiceId, Integer pageIndex, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		criteria.andRentIsdelEqualTo(0);
		criteria.andRentInvoiceIdEqualTo(invoiceId);
		bikeRentInfoExample.setOrderByClause("rent_pay_time desc");
		bikeRentInfoExample.setLimitStart(PageUtil.getStart(pageIndex));
		bikeRentInfoExample.setLimitEnd(pageSize);
		List<BikeRentInfo> bikeRentInfoList = bikeRentInfoMapper.selectUnionByExample(bikeRentInfoExample);
		return bikeRentInfoList;
	}

	@Override
	public Integer findfindByInvoiceIdCount(Long invoiceId, Integer pageIndex, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		criteria.andRentIsdelEqualTo(0);
		criteria.andRentInvoiceIdEqualTo(invoiceId);
		return bikeRentInfoMapper.countByExample(bikeRentInfoExample);
	}


	@Override
	public BikeRentInfo findNotFinishByUserId(Long userId) throws Exception {
		// TODO Auto-generated method stub
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		criteria.andRentInfoUserIdEqualTo(userId);//用户id
		criteria.andRentStateEqualTo(2);//未完成标志
		criteria.andRentIsdelEqualTo(0);//未删
		bikeRentInfoExample.setOrderByClause("rent_starttime desc");
		List<BikeRentInfo> bikeRentInfos = bikeRentInfoMapper.selectByExample(bikeRentInfoExample);
		if(bikeRentInfos.size()>0){
			return bikeRentInfos.get(0);
		}

		return null;
	}

	@Override
	public List<BikeRentInfo> findByUserIdsAndState(List<Long> userIds, int state) throws Exception {
		if(userIds.isEmpty()){
			return Collections.emptyList();
		}
		List<BikeRentInfo> bikeRentInfos = bikeRentInfoMapper.selectByUserIdsAndState(userIds,state);
		return bikeRentInfos;
	}

	@Override
	public BikeRentInfo findByUserId(Long userId,String phone,String idCard,String name) throws Exception {
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		criteria.andRentInfoUserIdEqualTo(userId);
		criteria.andRentIsdelEqualTo(0);
		bikeRentInfoExample.setOrderByClause("rent_starttime desc");
		List<BikeRentInfo> bikeRentInfos = bikeRentInfoMapper.selectUnionByExample(bikeRentInfoExample);
		if(bikeRentInfos.size()>0){
			BikeRentInfo bikeRentInfo=bikeRentInfos.get(0);
			//todo mybatis
			if(phone!=null&&!"".equals(phone)){
				if(bikeRentInfo.getUser().getUserTel()!=phone){
					return null;
				}
			}
			if(idCard!=null&&!"".equals(idCard)){
				if(bikeRentInfo.getUser().getUserIdcard()!=idCard){
					return null;
				}
			}
			if(name!=null&&!"".equals(name)){
				if(!bikeRentInfo.getUser().getUserRealname().matches(".*"+name+".*")){
					return null;
				}
			}
			return bikeRentInfo;
		}
		return null;
	}

	@Override
	public List<BikeRentInfo> findByUserIdAndGroupId(Long userId,Long groupId) throws Exception {
		return bikeRentInfoMapper.selectByUserIdAndGroupId(userId,groupId);
	}
}
