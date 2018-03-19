package org.service.cms.impl.read;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dao.BikeFixInfoMapper;
import org.entity.dto.BikeFixInfo;
import org.entity.dto.BikeFixInfoExample;
import org.service.cms.read.BikeFixInfoServiceRead;
import org.springframework.stereotype.Service;
import org.util.DateUtil;
import org.util.PageUtil;
@Service("bikeFixInfoServiceRead")
public class BikeFixInfoServiceReadImpl implements BikeFixInfoServiceRead  {
	
	@Resource
	BikeFixInfoMapper bikeFixInfoMapper;

	@Override
	public List<BikeFixInfo> findAllBikeFixInfo(List<Long> channelIds,Integer pageIndex,String fixBatch,String bikecode,Date bikeFixStartTime,Date bikeFixStartTimeB,Date bikeFixEndTime,Date bikeFixEndTimeB,String bikeFixPerson,String blockCode) throws Exception {
		BikeFixInfoExample bikeFixInfoExample = new BikeFixInfoExample();
		BikeFixInfoExample.Criteria criteria = bikeFixInfoExample.createCriteria();
		criteria.andFixDelEqualTo(0);
		
		if(blockCode!=null){
			criteria.andFixBlockLike(blockCode+"%");
		}
		
		if(fixBatch!=null&&!fixBatch.equals("")){
			criteria.andFixBatchNumberEqualTo(fixBatch.trim());
		}
		if(bikecode!=null&&!bikecode.equals("")){
			criteria.andBikeCodeLike("%"+bikecode.trim()+"%");
		}
		if(bikeFixStartTime!=null){
			criteria.andFixStarttimeGreaterThanOrEqualTo(bikeFixStartTime);
		}
		if(bikeFixStartTimeB!=null){
			criteria.andFixStarttimeLessThan(DateUtil.plusDate(bikeFixStartTimeB, 1));
		}
		if(bikeFixEndTime!=null){
			criteria.andFixEndtimeGreaterThanOrEqualTo(bikeFixEndTime);
		}
		if(bikeFixEndTimeB!=null){
			criteria.andFixEndtimeLessThan(DateUtil.plusDate(bikeFixEndTimeB, 1));
		}
		if(bikeFixPerson!=null&&!bikeFixPerson.equals("")){
			criteria.andFixManEqualTo(bikeFixPerson.trim());
		}
		
		//渠道下的车辆
		if(channelIds!=null){
			criteria.andChannelIdIn(channelIds);
		}	
		
		bikeFixInfoExample.setOrderByClause("FIELD(fix_starttime,'') asc,fix_starttime desc,fix_id asc");
		bikeFixInfoExample.setLimitStart(PageUtil.getStart(pageIndex));
		bikeFixInfoExample.setLimitEnd(PageUtil.size);
		List<BikeFixInfo>  bikeFixInfoList = bikeFixInfoMapper.selectUnionByExample(bikeFixInfoExample);
		return bikeFixInfoList;
	}

	@Override
	public Integer countAllBikeFixInfo(List<Long> channelIds,String fixBatch,String bikecode,Date bikeFixStartTime,Date bikeFixStartTimeB,Date bikeFixEndTime,Date bikeFixEndTimeB,String bikeFixPerson,String blockCode) throws Exception {
		// TODO Auto-generated method stub
		BikeFixInfoExample bikeFixInfoExample = new BikeFixInfoExample();
		BikeFixInfoExample.Criteria criteria = bikeFixInfoExample.createCriteria();
		criteria.andFixDelEqualTo(0);
		
		if(blockCode!=null){
			criteria.andFixBlockLike(blockCode+"%");
		}
		
		if(fixBatch!=null&&!fixBatch.equals("")){
			criteria.andFixBatchNumberEqualTo(fixBatch.trim());
		}
		if(bikecode!=null&&!bikecode.equals("")){
			criteria.andBikeCodeLike("%"+bikecode.trim()+"%");
		}
		if(bikeFixStartTime!=null){
			criteria.andFixStarttimeGreaterThanOrEqualTo(bikeFixStartTime);
		}
		if(bikeFixStartTimeB!=null){
			criteria.andFixStarttimeLessThan(bikeFixStartTimeB);
		}
		if(bikeFixEndTime!=null){
			criteria.andFixEndtimeGreaterThanOrEqualTo(bikeFixEndTime);
		}
		if(bikeFixEndTimeB!=null){
			criteria.andFixEndtimeLessThan(bikeFixEndTimeB);
		}
		if(bikeFixPerson!=null&&!bikeFixPerson.equals("")){
			criteria.andFixManEqualTo(bikeFixPerson.trim());
		}
		//渠道下的车辆
		if(channelIds!=null){
			criteria.andChannelIdIn(channelIds);
		}
		Integer pageCount = bikeFixInfoMapper.countUnionByExample(bikeFixInfoExample);
		Integer totalPage = PageUtil.getTotalPage(pageCount);
		return totalPage;
	}

	@Override
	public List<BikeFixInfo> findBikeFixInfoBybikeId(Long bikeId,String fixBatch, Integer pageIndex, Date bikeFixStartTime,Date bikeFixStartTimeB,Date bikeFixEndTime,
			Date bikeFixEndTimeB, String bikeFixPerson,String blockCode) throws Exception {
		BikeFixInfoExample bikeFixInfoExample = new BikeFixInfoExample();
		BikeFixInfoExample.Criteria criteria = bikeFixInfoExample.createCriteria();
		criteria.andFixBikeIdEqualTo(bikeId);
		criteria.andFixDelEqualTo(0);
		
		if(blockCode!=null){
			criteria.andFixBlockLike(blockCode+"%");
		}
		
		if(fixBatch!=null&&!fixBatch.equals("")){
			criteria.andFixBatchNumberEqualTo(fixBatch.trim());
		}
		if(bikeFixStartTime!=null){
			criteria.andFixStarttimeGreaterThanOrEqualTo(bikeFixStartTime);
		}
		if(bikeFixStartTimeB!=null){
			criteria.andFixStarttimeLessThan(DateUtil.plusDate(bikeFixStartTimeB, 1));
		}
		if(bikeFixEndTime!=null){
			criteria.andFixEndtimeGreaterThanOrEqualTo(bikeFixEndTime);
		}
		if(bikeFixEndTimeB!=null){
			criteria.andFixEndtimeLessThan(DateUtil.plusDate(bikeFixEndTimeB, 1));
		}
		if(bikeFixPerson!=null&&!bikeFixPerson.equals("")){
			criteria.andFixManEqualTo(bikeFixPerson.trim());
		}
		bikeFixInfoExample.setOrderByClause("FIELD(fix_starttime,'') asc,fix_starttime desc,fix_id asc");
		bikeFixInfoExample.setLimitStart(PageUtil.getStart(pageIndex));
		bikeFixInfoExample.setLimitEnd(PageUtil.size);
		List<BikeFixInfo>  bikeFixInfoList = bikeFixInfoMapper.selectUnionByExample(bikeFixInfoExample);
		return bikeFixInfoList;
	}
	
	@Override
	public Integer countBikeFixInfoBybikeId(Long bikeId,String fixBatch,Date bikeFixStartTime,Date bikeFixStartTimeB,Date bikeFixEndTime,Date bikeFixEndTimeB,String bikeFixPerson,String blockCode) throws Exception {
		// TODO Auto-generated method stub
		BikeFixInfoExample bikeFixInfoExample = new BikeFixInfoExample();
		BikeFixInfoExample.Criteria criteria = bikeFixInfoExample.createCriteria();
		criteria.andFixBikeIdEqualTo(bikeId);
		criteria.andFixDelEqualTo(0);
		
		if(blockCode!=null){
			criteria.andFixBlockLike(blockCode+"%");
		}
		
		if(fixBatch!=null&&!fixBatch.equals("")){
			criteria.andFixBatchNumberEqualTo(fixBatch.trim());
		}
		if(bikeFixStartTime!=null){
			criteria.andFixStarttimeGreaterThanOrEqualTo(bikeFixStartTime);
		}
		if(bikeFixStartTimeB!=null){
			criteria.andFixStarttimeLessThan(DateUtil.plusDate(bikeFixStartTimeB, 1));
		}
		if(bikeFixEndTime!=null){
			criteria.andFixEndtimeGreaterThanOrEqualTo(bikeFixEndTime);
		}
		if(bikeFixEndTimeB!=null){
			criteria.andFixEndtimeLessThan(DateUtil.plusDate(bikeFixEndTimeB, 1));
		}
		if(bikeFixPerson!=null&&!bikeFixPerson.equals("")){
			criteria.andFixManEqualTo(bikeFixPerson.trim());
		}
		Integer pageCount = bikeFixInfoMapper.countUnionByExample(bikeFixInfoExample);
		Integer totalPage = PageUtil.getTotalPage(pageCount);
		return totalPage;
	}

	@Override
	public BikeFixInfo findBikeFixInfoByFixId(Long bikeFixId) throws Exception {
		// TODO Auto-generated method stub
		BikeFixInfo bikeFixInfo = bikeFixInfoMapper.selectByPrimaryKey(bikeFixId);
		return bikeFixInfo;
	}

	@Override
	public String findMaxBatchNumber() throws Exception {
		// TODO Auto-generated method stub
	    return bikeFixInfoMapper.selectMaxBatch();
	}

	@Override
	public List<String> findBatchNumbers() throws Exception {
		// TODO Auto-generated method stub
		return bikeFixInfoMapper.selectBatchNumber();
	}
	
	@Override
	public List<BikeFixInfo> findByBikeId(Long bikeId,Integer pageIndex,Integer page_size_weixin) throws Exception {
		// TODO Auto-generated method stub
		BikeFixInfoExample bikeFixInfoExample = new BikeFixInfoExample();
		BikeFixInfoExample.Criteria criteria  = bikeFixInfoExample.createCriteria();
		criteria.andFixBikeIdEqualTo(bikeId);
		criteria.andFixDelEqualTo(0);//存在
		bikeFixInfoExample.setOrderByClause("fix_endtime desc,fix_id desc");
		bikeFixInfoExample.setLimitStart(PageUtil.getStart(pageIndex));
		bikeFixInfoExample.setLimitEnd(page_size_weixin);
		return bikeFixInfoMapper.selectUnionByExample(bikeFixInfoExample);
	}

	@Override
	public Integer countByBikeId(Long bikeId) throws Exception {
		// TODO Auto-generated method stub
		BikeFixInfoExample bikeFixInfoExample = new BikeFixInfoExample();
		BikeFixInfoExample.Criteria criteria  = bikeFixInfoExample.createCriteria();
		criteria.andFixBikeIdEqualTo(bikeId);
		criteria.andFixDelEqualTo(0);//存在
		return bikeFixInfoMapper.countByExample(bikeFixInfoExample);
	}
	
	@Override
	public String findMaxDate(Long bikeId) throws Exception {
		// TODO Auto-generated method stub
		return bikeFixInfoMapper.selectMaxDate(bikeId);
	}

	@Override
	public BikeFixInfo findBikeFixInfoByBikeIdAndNotFinish(Long bikeId) throws Exception {
		// TODO Auto-generated method stub
		BikeFixInfoExample bikeFixInfoExample = new BikeFixInfoExample();
		BikeFixInfoExample.Criteria criteria = bikeFixInfoExample.createCriteria();
		criteria.andFixBikeIdEqualTo(bikeId);
		criteria.andFixEndtimeIsNull();
		criteria.andFixDelEqualTo(0);
		List<BikeFixInfo> bikeFixInfos = bikeFixInfoMapper.selectByExample(bikeFixInfoExample);
		if(bikeFixInfos.size()>0){
			return bikeFixInfos.get(0);
		}
		return null;
	}
}
