package org.service.lock.impl;

import java.util.List;

import javax.annotation.Resource;

import org.dao.BikeLockInfoMapper;
import org.dao.BikeMapper;
import org.dao.BikeRentInfoMapper;
import org.dao.BlockMapper;
import org.dao.DataDetMapper;
import org.dao.FixedReturnMapper;
import org.dao.ModelsMapper;
import org.dao.RentPriceMapper;
import org.dao.SysParamentMapper;
import org.dao.UserMapper;
import org.entity.dto.Bike;
import org.entity.dto.BikeExample;
import org.entity.dto.BikeExample.Criteria;
import org.entity.dto.BikeLockInfo;
import org.entity.dto.BikeLockInfoExample;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.Block;
import org.entity.dto.BlockExample;
import org.entity.dto.DataDet;
import org.entity.dto.FixedReturn;
import org.entity.dto.Models;
import org.entity.dto.RentPrice;
import org.entity.dto.RentPriceExample;
import org.entity.dto.SysParament;
import org.entity.dto.SysParamentExample;
import org.entity.dto.User;
import org.service.lock.self.LockBikeService;
import org.springframework.stereotype.Service;

@Service("lockBikeService")
public class LockBikeServiceImpl implements LockBikeService {

	@Resource
	BikeLockInfoMapper bikeLockInfoMapper;

	@Resource
	BikeMapper bikeMapper;

	@Resource
	BlockMapper blockMapper;
	
	@Resource
	UserMapper userMapper;
	
	@Resource
	ModelsMapper modelsMapper;
	
	@Resource
	FixedReturnMapper fixedReturnMapper;
	
	@Resource
	SysParamentMapper sysParamentMapper;
	
	@Resource
	BikeRentInfoMapper bikeRentInfoMapper;
	
	@Resource
	RentPriceMapper rentPriceMapper;
	
	@Resource
	DataDetMapper dataDetMapper;

	public void updateBike(Bike bike) throws Exception {
		bikeMapper.updateByPrimaryKeySelective(bike);
	}

	public void updateLock(BikeLockInfo bikeLockInfo) throws Exception {
		BikeLockInfoExample example = new BikeLockInfoExample();
		BikeLockInfoExample.Criteria criteria = example.createCriteria();
		criteria.andBikeLockCodeEqualTo(bikeLockInfo.getBikeLockCode());
		bikeLockInfoMapper.updateByExampleSelective(bikeLockInfo, example);
	}

	public void addLock(BikeLockInfo bikeLockInfo) throws Exception {
		bikeLockInfoMapper.insert(bikeLockInfo);
	}

	public Bike getBike(String bid) throws Exception {
		BikeLockInfoExample example = new BikeLockInfoExample();
		BikeLockInfoExample.Criteria criteria = example.createCriteria();
		criteria.andBikeLockCodeEqualTo(bid);
		List<BikeLockInfo> list = bikeLockInfoMapper.selectByExample(example);
		Bike bike = new Bike();
		if (0 != list.size()) {
			bike = bikeMapper.selectByPrimaryKey(list.get(0).getBikeLockBikeId());
		}
		return bike;
	}

	public BikeLockInfo getLock(String bid) throws Exception {
		BikeLockInfoExample example = new BikeLockInfoExample();
		BikeLockInfoExample.Criteria criteria = example.createCriteria();
		criteria.andBikeLockCodeEqualTo(bid);
		List<BikeLockInfo> list = bikeLockInfoMapper.selectByExample(example);
		if (0 != list.size()) {
			return list.get(0);
		}
		return null;
	}

	public Long getBlockId(String blockCode) throws Exception {
		BlockExample example = new BlockExample();
		BlockExample.Criteria criteria = example.createCriteria();
		criteria.andBlockCodeEqualTo(blockCode);
		List<Block> list = blockMapper.selectByExample(example);
		if (0 != list.size()) {
			return list.get(0).getBlockId();
		}
		return (long) 0;
	}

	@Override
	public BikeLockInfo getBikeLockInfo(Long blockCode) throws Exception {
		// TODO Auto-generated method stub
		BikeLockInfoExample example = new BikeLockInfoExample();
		BikeLockInfoExample.Criteria criteria = example.createCriteria();
		criteria.andBikeLockBikeIdEqualTo(blockCode);
		List<BikeLockInfo> list = bikeLockInfoMapper.selectByExample(example);
		if (0 != list.size()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Bike getBikeBybikeCode(String bikeCode) throws Exception {
		// TODO Auto-generated method stub
		BikeExample example = new BikeExample();
		Criteria criteria = example.createCriteria();
		criteria.andBikeCodeEqualTo(bikeCode);
		List<Bike> list = bikeMapper.selectByExample(example);
		if (0 != list.size()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void updateUser(User user) throws Exception {
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public void updateRentinfo(BikeRentInfo bikeRentInfo) throws Exception {
		bikeRentInfoMapper.updateByPrimaryKeySelective(bikeRentInfo);
	}

	@Override
	public BikeRentInfo getBikeRentInfoById(Long id) throws Exception {
		BikeRentInfo bikeRentInfo = bikeRentInfoMapper.selectByPrimaryKey(id);
		return bikeRentInfo;
	}

	@Override
	public Long findBlockId(String blockCode) throws Exception {
		BlockExample example = new BlockExample();
		BlockExample.Criteria criteria = example.createCriteria();
		criteria.andBlockCodeEqualTo(blockCode);
		List<Block> list = blockMapper.selectByExample(example);
		if (0 != list.size())
			return list.get(0).getBlockId();
		else
			return null;
	}

	@Override
	public Double getParament(String paramentName) throws Exception {
		SysParamentExample example = new SysParamentExample();
		org.entity.dto.SysParamentExample.Criteria criteria = example.createCriteria();
		criteria.andSysParamentNameEqualTo(paramentName);
		List<SysParament> list = sysParamentMapper.selectByExample(example);
		if(list.size()>0){
			return Double.valueOf(list.get(0).getSysParamentValue());
		}
		return null;
	}

	@Override
	public Models getModels(Long modelsId) throws Exception {
		// TODO Auto-generated method stub
		return modelsMapper.selectByPrimaryKey(modelsId);
	}

	@Override
	public FixedReturn getFixed(Long fixedId) throws Exception {
		// TODO Auto-generated method stub
		return fixedReturnMapper.selectByPrimaryKey(fixedId);
	}

	@Override
	public DataDet getDataDet(long dataDetId) throws Exception {
		// TODO Auto-generated method stub
		return dataDetMapper.selectByPrimaryKey(dataDetId);
	}

	@Override
	public User getUser(Long userId) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public RentPrice getRentPrice(Long modelsId, Integer type) {
		RentPriceExample example = new RentPriceExample();
		RentPriceExample.Criteria criteria = example.createCriteria();
		criteria.andRentPriceModelsIdEqualTo(modelsId);
		criteria.andRentPriceTypeEqualTo(type);
		List<RentPrice> list = rentPriceMapper.selectByExampleWithBLOBs(example);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void updateLock(BikeLockInfo bikeLockInfo, Integer flag) throws Exception {
		BikeLockInfoExample example = new BikeLockInfoExample();
		BikeLockInfoExample.Criteria criteria = example.createCriteria();
		criteria.andBikeLockCodeEqualTo(bikeLockInfo.getBikeLockCode());
		//使用中的车锁
		criteria.andBikeLockDelEqualTo(0);
		bikeLockInfoMapper.updateByExampleSelective(bikeLockInfo, example);
	}
}
