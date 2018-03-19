package org.service.cms.impl.read;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dao.BikeMapper;
import org.entity.dto.Bike;
import org.entity.dto.BikeExample;
import org.entity.dto.LatLng;
import org.service.cms.read.BikeServiceRead;
import org.springframework.stereotype.Service;
import org.util.DateUtil;
import org.util.PageUtil;

@Service("bikeServiceRead")
public class BikeServiceReadImpl implements BikeServiceRead {

	@Resource
	BikeMapper bikeMapper;

	@Override
	public List<Bike> findAllBike(Integer pageIndex,Integer pageSize, Bike bike, Date bikePutStartTime, Date bikePutEndTime,
			String blockCode, List<Long> channelIds,String bikeLockCode,Long channelid, Long modelsId) throws Exception {
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		// 使用中
		criteria.andBikeDelEqualTo(0);
		if (null != channelid && channelid !=-1) {
			criteria.andChannelIdEqualTo(channelid);
		}
		/*if (null != blockputIds)
			criteria.andBikeBlockIdIn(blockputIds);*/
		//方格
		if (null != blockCode ) {
			criteria.andBikeBlockLike(blockCode+"%");
		}
		if (bike.getBikeCode() != null && !bike.getBikeCode().equals("")) {
			criteria.andBikeCodeLike("%" + bike.getBikeCode() + "%");
		}
		
		if (null!=modelsId&&-1!=modelsId){
			criteria.andBikeModelsIdEqualTo(modelsId);
		}
		
		if (null != bike.getBikeFixedReturnId() && bike.getBikeFixedReturnId() !=-1) {
			criteria.andBikeFixedReturnIdEqualTo(bike.getBikeFixedReturnId());
		}
		
		if (bike.getBikeState() != null && bike.getBikeState() != -1) {
			if (-2 == bike.getBikeState())
				criteria.andBikeStateNotEqualTo(2);
			else
				criteria.andBikeStateEqualTo(bike.getBikeState());
		}
		// 批次号
		if (null != bike.getBikeBatchNumber() && !"".equals(bike.getBikeBatchNumber()))
			criteria.andBikeBatchNumberLike("%" + bike.getBikeBatchNumber() + "%");
		// 维护人
		if (null != bike.getBikeRepair() && !"".equals(bike.getBikeRepair()))
			criteria.andManagerNameLike("%" + bike.getBikeRepair().getManagerName() + "%");
		// 设备锁号
		if (null != bikeLockCode && !"".equals(bikeLockCode))
			criteria.andBikeLockCodeLike("%" + bikeLockCode.trim() + "%");
		if (bikePutStartTime != null) {
			if (bikePutEndTime != null) {
				criteria.andBikePutTimeBetween(bikePutStartTime, DateUtil.plusDate(bikePutEndTime, 1));
			} else {
				criteria.andBikePutTimeBetween(bikePutStartTime, new Date());
			}
		} else {
			if (bikePutEndTime != null) {
				criteria.andBikePutTimeLessThan(DateUtil.plusDate(bikePutEndTime, 1));
			}
		}
		// 渠道
		if (null != channelIds ) {
			criteria.andChannelIdIn(channelIds);
		}
		
		
		bikeExample.setOrderByClause("bike_id asc");

		if(pageIndex!=null){
			bikeExample.setLimitStart(PageUtil.getStart(pageIndex));
			bikeExample.setLimitEnd(pageSize);
		}
		
		List<Bike> bikeList = bikeMapper.selectUnionByExample(bikeExample);
		return bikeList;
		
	}

	@Override
	public Integer countAllBike(Bike bike, Date bikePutStartTime, Date bikePutEndTime,String blockCode,
			List<Long> channelIds,String bikeLockCode,Long channelid, Long modelsId) throws Exception {
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeDelEqualTo(0);
		if (null != channelid && channelid !=-1) {
			criteria.andChannelIdEqualTo(channelid);
		}
		/*if (null != blockputIds)
			criteria.andBikeBlockIdIn(blockputIds);*/
		//方格
		if (null != blockCode ) {
			criteria.andBikeBlockLike(blockCode+"%");
		}

		if (bike.getBikeCode() != null && !bike.getBikeCode().equals("")) {
			criteria.andBikeCodeLike("%" + bike.getBikeCode() + "%");
		}

		if (bike.getBikeState() != null && bike.getBikeState() != -1) {
			if (-2 == bike.getBikeState())
				criteria.andBikeStateNotEqualTo(2);
			else
				criteria.andBikeStateEqualTo(bike.getBikeState());
		}
		
		if (null!=modelsId&&-1!=modelsId){
			criteria.andBikeModelsIdEqualTo(modelsId);
		}
		
		if (null != bike.getBikeFixedReturnId() && bike.getBikeFixedReturnId() !=-1) {
			criteria.andBikeFixedReturnIdEqualTo(bike.getBikeFixedReturnId());
		}
		
		// 批次号
		if (null != bike.getBikeBatchNumber() && !"".equals(bike.getBikeBatchNumber()))
			criteria.andBikeBatchNumberLike("%" + bike.getBikeBatchNumber() + "%");
		// 维护人
		if (null != bike.getBikeRepair() && !"".equals(bike.getBikeRepair()))
			criteria.andManagerNameLike("%" + bike.getBikeRepair().getManagerName() + "%");
		// 设备锁号
		if (null != bikeLockCode && !"".equals(bikeLockCode))
			criteria.andBikeLockCodeLike("%" + bikeLockCode.trim() + "%");
		if (bikePutStartTime != null) {
			if (bikePutEndTime != null) {
				criteria.andBikePutTimeBetween(bikePutStartTime, DateUtil.plusDate(bikePutEndTime, 1));
			} else {
				criteria.andBikePutTimeBetween(bikePutStartTime, new Date());
			}
		} else {
			if (bikePutEndTime != null) {
				criteria.andBikePutTimeLessThan(DateUtil.plusDate(bikePutEndTime, 1));
			}
		}

		// 渠道
		if (null != channelIds ) {
			criteria.andChannelIdIn(channelIds);
		}
		Integer pageCount = bikeMapper.countUnionByExample(bikeExample);
		Integer totalPage = PageUtil.getTotalPage(pageCount);
		return totalPage;
	}

	@Override
	public Bike findBikeByBikeId(Long bikeId) throws Exception {
		Bike bike = bikeMapper.selectUnionByPrimaryKey(bikeId);
		return bike;
	}

	@Override
	public List<Bike> findByChannel(Integer pageIndex, Integer pageSize, Long channelId) throws Exception {
		// TODO Auto-generated method stub
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andChannelIdEqualTo(channelId);
		bikeExample.setLimitStart(PageUtil.getStart(pageIndex));
		bikeExample.setLimitEnd(pageSize);
		bikeExample.setOrderByClause("bike_id desc");
		return bikeMapper.selectUnionByExample(bikeExample);
	}

	@Override
	public Integer findByChannelCount(Long channelId) throws Exception {
		// TODO Auto-generated method stub
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andChannelIdEqualTo(channelId);
		return bikeMapper.countByExample(bikeExample);
	}

	@Override
	public Bike findBikeByBikeCode(String bikeCode) throws Exception {
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeDelEqualTo(0);
		criteria.andBikeCodeLike(bikeCode+"%");
		List<Bike> bikeList = bikeMapper.selectUnionByExample(bikeExample);
		if (bikeList.size() > 0) {
			return bikeList.get(0);
		}
		return null;
	}

	@Override
	public List<String> findBikeBatchNumber() throws Exception {
		List<String> bikeBatchNumbers = bikeMapper.selectBikeBatchNumber();
		return bikeBatchNumbers;
	}
	@Override
	public List<LatLng> findByBlockIds(List<String> blocks) throws Exception {
		// TODO Auto-generated method stub
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeBlockIn(blocks);
		criteria.andBikeDelEqualTo(0);
		criteria.andBikeStateEqualTo(0);
		bikeExample.setLimitStart(0);
		bikeExample.setLimitEnd(500);
		return bikeMapper.selectBikesLatLng(bikeExample);
	}

	@Override
	public String findMaxBikeBatchNumber() throws Exception {
		// TODO Auto-generated method stub
		return bikeMapper.selectMaxBatch();
	}

	@Override
	public Integer findByModelsId(List<Long> modelIds) throws Exception {
		// TODO Auto-generated method stub
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeModelsIdIn(modelIds);
		criteria.andBikeDelEqualTo(0);
		return bikeMapper.countByExample(bikeExample);
	}

	@Override
	public List<Bike> findBikeByModels(Integer pageIndex, Integer base_path_web, List<Long> modelsIds) throws Exception {
		// TODO Auto-generated method stub
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeModelsIdIn(modelsIds);
		criteria.andBikeDelEqualTo(0);	//使用中
		bikeExample.setLimitStart(PageUtil.getStart(pageIndex));
		bikeExample.setLimitEnd(base_path_web);
		return bikeMapper.selectUnionByExample(bikeExample);
	}

	@Override
	public Bike findBikeByEqualBikeCode(String bikeCode) throws Exception {
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeDelEqualTo(0);
		criteria.andBikeCodeEqualTo(bikeCode);
		List<Bike> bikeList = bikeMapper.selectByExample(bikeExample);
		if (bikeList.size() > 0) {
			return bikeList.get(0);
		}
		return null;
	}

	@Override
	public List<LatLng> findByBlockIdsAndChannel(List<String> blocks, List<Long> modelsList) throws Exception {
		// TODO Auto-generated method stub
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeBlockIn(blocks);
		criteria.andBikeModelsIdIn(modelsList);
		criteria.andBikeDelEqualTo(0);
		bikeExample.setLimitStart(0);
		bikeExample.setLimitEnd(100);
		return bikeMapper.selectBikesLatLng(bikeExample);
	}

	@Override
	public List<Bike> findAllBikes(Integer pageIndex, Integer pageSize, String bikeCode) throws Exception {
		// TODO Auto-generated method stub
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		if(null!=bikeCode&&!"".equals(bikeCode)){
			criteria.andBikeCodeLike(bikeCode+"%");
		}
		criteria.andBikeDelEqualTo(0);
		bikeExample.setLimitStart(PageUtil.getStart(pageIndex));
		bikeExample.setLimitEnd(pageSize);
		bikeExample.setOrderByClause("bike_alert=2 desc,bike_id asc");
		return bikeMapper.selectByExample(bikeExample);
	}
	
	@Override
	public Integer findAllBikeCount(String bikeCode) throws Exception {
		// TODO Auto-generated method stub
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeDelEqualTo(0);
		if(null!=bikeCode&&!"".equals(bikeCode)){
			criteria.andBikeCodeLike(bikeCode+"%");
		}
		return bikeMapper.countByExample(bikeExample);
	}
	
	@Override
	public List<Bike> findByChannel(Integer pageIndex, Integer pageSize, String bikeCode,List<Long> modelsList) {
		// TODO Auto-generated method stub
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeDelEqualTo(0);
		if(null!=bikeCode&&!"".equals(bikeCode)){
			criteria.andBikeCodeLike(bikeCode+"%");
		}
		criteria.andBikeModelsIdIn(modelsList);
		bikeExample.setLimitStart(PageUtil.getStart(pageIndex));
		bikeExample.setLimitEnd(pageSize);
		bikeExample.setOrderByClause("bike_id asc");
		return bikeMapper.selectByExample(bikeExample);
	}
	
	@Override
	public Integer findByChannelCounts(String bikeCode, List<Long> modelsList) {
		// TODO Auto-generated method stub
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeDelEqualTo(0);
		if(null!=bikeCode&&!"".equals(bikeCode)){
			criteria.andBikeCodeLike(bikeCode+"%");
		}
		criteria.andBikeModelsIdIn(modelsList);
		return bikeMapper.countByExample(bikeExample);
	}

	@Override
	public List<Bike> findAllFixBikes(Integer pageIndex, Integer page_size_weixin, String bikeCode) throws Exception {
		// TODO Auto-generated method stub
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeDelEqualTo(0);
		if(null!=bikeCode&&!"".equals(bikeCode)){
			criteria.andBikeCodeLike(bikeCode+"%");
		}
		List<Integer> states = new ArrayList<Integer>();
		states.add(0);
		states.add(3);
		states.add(4);
		criteria.andBikeStateIn(states);
		bikeExample.setLimitStart(PageUtil.getStart(pageIndex));
		bikeExample.setLimitEnd(page_size_weixin);
		bikeExample.setOrderByClause("bike_id asc");
		return bikeMapper.selectByExample(bikeExample);
	}

	@Override
	public Integer findAllFixBikeCount(String bikeCode) throws Exception {
		// TODO Auto-generated method stub
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeDelEqualTo(0);
		if(null!=bikeCode&&!"".equals(bikeCode)){
			criteria.andBikeCodeLike(bikeCode+"%");
		}
		List<Integer> states = new ArrayList<Integer>();
		states.add(0);
		states.add(3);
		states.add(4);
		criteria.andBikeStateIn(states);
		return bikeMapper.countByExample(bikeExample);
	}

	@Override
	public List<Bike> findByFixChannel(Integer pageIndex, Integer page_size_weixin, String bikeCode,
			List<Long> modelsList) throws Exception {
		// TODO Auto-generated method stub
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeDelEqualTo(0);
		if(null!=bikeCode&&!"".equals(bikeCode)){
			criteria.andBikeCodeLike(bikeCode+"%");
		}
		List<Integer> states = new ArrayList<Integer>();
		states.add(0);
		states.add(3);
		states.add(4);
		criteria.andBikeStateIn(states);
		criteria.andBikeModelsIdIn(modelsList);
		bikeExample.setLimitStart(PageUtil.getStart(pageIndex));
		bikeExample.setLimitEnd(page_size_weixin);
		bikeExample.setOrderByClause("bike_id asc");
		return bikeMapper.selectByExample(bikeExample);
	}

	@Override
	public Integer findByFixChannelCounts(String bikeCode, List<Long> modelsList) throws Exception {
		// TODO Auto-generated method stub
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeDelEqualTo(0);
		if(null!=bikeCode&&!"".equals(bikeCode)){
			criteria.andBikeCodeLike(bikeCode+"%");
		}
		List<Integer> states = new ArrayList<Integer>();
		states.add(0);
		states.add(3);
		states.add(4);
		criteria.andBikeStateIn(states);
		criteria.andBikeModelsIdIn(modelsList);
		return bikeMapper.countByExample(bikeExample);
	}

	@Override
	public List<Bike> findByChannels(List<Long> channelIds) throws Exception {
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		if(null!=channelIds){
			criteria.andChannelIdIn(channelIds);
		}
		criteria.andBikeStateEqualTo(0);
		criteria.andBikeDelEqualTo(0);
		return  bikeMapper.selectUnionByExample(bikeExample);
	}

	@Override
	public List<Bike> findByFixedReturnId(Long fixedReturnId) throws Exception {
		// TODO Auto-generated method stub
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeDelEqualTo(0);
		criteria.andBikeStateEqualTo(0);
		criteria.andBikeFixedReturnIdEqualTo(fixedReturnId);
		return bikeMapper.selectByExample(bikeExample);
	}

	@Override
	public List<Bike> findBikeQrList(Integer pageIndex, Integer page_size_web, Bike bike, String bikeLockCode,
			Long channelid,List<Long> channelIds) throws Exception {
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeDelEqualTo(0);
		// 批次号
		if (null != bike.getBikeBatchNumber() && !"".equals(bike.getBikeBatchNumber())){
			criteria.andBikeBatchNumberLike("%" + bike.getBikeBatchNumber() + "%");
		}
		// 维护人
		if (null != bike.getBikeRepair() && !"".equals(bike.getBikeRepair())){
			criteria.andManagerNameLike("%" + bike.getBikeRepair().getManagerName() + "%");
		}
		// 设备锁号
		if (null != bikeLockCode && !"".equals(bikeLockCode)){
			criteria.andBikeLockCodeLike("%" + bikeLockCode + "%");
		}
		//渠道
		if (null != channelid && channelid !=-1) {
			criteria.andChannelIdEqualTo(channelid);
		}
		//车型
		if (null != bike.getBikeModelsId() && bike.getBikeModelsId() !=-1) {
			criteria.andBikeModelsIdEqualTo(bike.getBikeModelsId());
		}
		// 渠道
		if (null != channelIds ) {
			criteria.andChannelIdIn(channelIds);
		}
		if (bike.getBikeCode() != null && !bike.getBikeCode().equals("")) {
			criteria.andBikeCodeEqualTo(bike.getBikeCode());
		}		
		bikeExample.setLimitStart(PageUtil.getStart(pageIndex));
		bikeExample.setLimitEnd(page_size_web);
		return bikeMapper.selectUnionByExample(bikeExample);
	}

	@Override
	public Integer countBikeQrList(Bike bike, String bikeLockCode, Long channelid,List<Long> channelIds) throws Exception {
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeDelEqualTo(0);
		// 批次号
		if (null != bike.getBikeBatchNumber() && !"".equals(bike.getBikeBatchNumber())){
			criteria.andBikeBatchNumberLike("%" + bike.getBikeBatchNumber() + "%");
		}
		// 维护人
		if (null != bike.getBikeRepair() && !"".equals(bike.getBikeRepair())){
			criteria.andManagerNameLike("%" + bike.getBikeRepair().getManagerName() + "%");
		}
		// 设备锁号
		if (null != bikeLockCode && !"".equals(bikeLockCode)){
			criteria.andBikeLockCodeLike("%" + bikeLockCode + "%");
		}
		//渠道
		if (null != channelid && channelid !=-1) {
			criteria.andChannelIdEqualTo(channelid);
		}
		//车型
		if (null != bike.getBikeModelsId() && bike.getBikeModelsId() !=-1) {
			criteria.andBikeModelsIdEqualTo(bike.getBikeModelsId());
		}
		// 渠道
		if (null != channelIds ) {
			criteria.andChannelIdIn(channelIds);
		}
		if (bike.getBikeCode() != null && !bike.getBikeCode().equals("")) {
			criteria.andBikeCodeEqualTo(bike.getBikeCode());
		}
		return bikeMapper.countUnionByExample(bikeExample);
	}

	@Override
	public List<Bike> findBikeByCode(String bikeCode) throws Exception {
		// TODO Auto-generated method stub
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeCodeLike("%"+bikeCode+"%");
		List<Bike> bikeList = bikeMapper.selectByExample(bikeExample);
		if (bikeList.size() > 0) {
			return bikeList;
		}
		return null;
	}

	@Override
	public Bike findBikeByIdAndDel(Long bikeLockBikeId) throws Exception {
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeDelEqualTo(0);//未删
		criteria.andBikeIdEqualTo(bikeLockBikeId);
		List<Bike> bikeList = bikeMapper.selectByExample(bikeExample);
		if(bikeList.size()>0){
			return bikeList.get(0);
		}
		return null;
	}

	@Override
	public List<Bike> findBikeByModelsId(Long modelsId) throws Exception {
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeDelEqualTo(0);//未删
		criteria.andBikeModelsIdEqualTo(modelsId);
		return bikeMapper.selectByExample(bikeExample);
	}

	@Override
	public List<LatLng> findByfixedReturnId(Long fixedReturnId) throws Exception {
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeFixedReturnIdEqualTo(fixedReturnId);
		criteria.andBikeDelEqualTo(0);
		criteria.andBikeStateEqualTo(0);
		bikeExample.setLimitStart(0);
		bikeExample.setLimitEnd(500);
		return bikeMapper.selectBikesLatLng(bikeExample);
	}

	@Override
	public List<Bike> findAllBike() throws Exception {
		// TODO Auto-generated method stub
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeStateEqualTo(0);
		return bikeMapper.selectByExample(bikeExample);
		
	}

	@Override
	public List<Bike> findAllBikeNoPage(Bike bike, Date bikePutStartTime, Date bikePutEndTime, String blockCode,
			List<Long> channelIds, Long channelid, Long modelsId) throws Exception {
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		// 使用中
		criteria.andBikeDelEqualTo(0);
		if (null != channelid && channelid !=-1) {
			criteria.andChannelIdEqualTo(channelid);
		}
		//方格
		if (null != blockCode ) {
			criteria.andBikeBlockLike(blockCode+"%");
		}
		if (bike.getBikeCode() != null && !bike.getBikeCode().equals("")) {
			criteria.andBikeCodeLike("%" + bike.getBikeCode() + "%");
		}
		
		if (null!=modelsId&&-1!=modelsId){
			criteria.andBikeModelsIdEqualTo(modelsId);
		}
		
		if (null != bike.getBikeFixedReturnId() && bike.getBikeFixedReturnId() !=-1) {
			criteria.andBikeFixedReturnIdEqualTo(bike.getBikeFixedReturnId());
		}
		
		if (bike.getBikeState() != null && bike.getBikeState() != -1) {
			if (-2 == bike.getBikeState())
				criteria.andBikeStateNotEqualTo(2);
			else
				criteria.andBikeStateEqualTo(bike.getBikeState());
		}
		// 批次号
		if (null != bike.getBikeBatchNumber() && !"".equals(bike.getBikeBatchNumber()))
			criteria.andBikeBatchNumberLike("%" + bike.getBikeBatchNumber() + "%");
		// 维护人
		if (null != bike.getBikeRepair() && !"".equals(bike.getBikeRepair()))
			criteria.andManagerNameLike("%" + bike.getBikeRepair().getManagerName() + "%");
		if (bikePutStartTime != null) {
			if (bikePutEndTime != null) {
				criteria.andBikePutTimeBetween(bikePutStartTime, DateUtil.plusDate(bikePutEndTime, 1));
			} else {
				criteria.andBikePutTimeBetween(bikePutStartTime, new Date());
			}
		} else {
			if (bikePutEndTime != null) {
				criteria.andBikePutTimeLessThan(DateUtil.plusDate(bikePutEndTime, 1));
			}
		}
		// 渠道
		if (null != channelIds ) {
			criteria.andChannelIdIn(channelIds);
		}
		

		List<Bike> bikeList = bikeMapper.selectUnionByExample(bikeExample);
		return bikeList;
	}

}
