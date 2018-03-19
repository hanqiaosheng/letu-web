package org.service.lock.self;

import org.entity.dto.Bike;
import org.entity.dto.BikeLockInfo;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.DataDet;
import org.entity.dto.FixedReturn;
import org.entity.dto.Models;
import org.entity.dto.RentPrice;
import org.entity.dto.User;

public interface LockBikeService {
	public void updateBike(Bike bike) throws Exception;

	public void updateLock(BikeLockInfo bikeLockInfo) throws Exception;
	
	public void updateLock(BikeLockInfo bikeLockInfo,Integer flag) throws Exception;

	public void addLock(BikeLockInfo bikeLockInfo) throws Exception;

	public Bike getBike(String bid) throws Exception;

	public BikeLockInfo getLock(String bid) throws Exception;

	public Long getBlockId(String blockCode) throws Exception;
	
	public BikeLockInfo getBikeLockInfo(Long blockCode) throws Exception;
	
	public Bike getBikeBybikeCode(String bikeCode) throws Exception;
	
	//更新用户状态
	public void updateUser(User user) throws Exception;
	
	//更新订单状态
	public void updateRentinfo(BikeRentInfo bikeRentInfo) throws Exception;
	
	//根据订单ID得到订单信息
	public BikeRentInfo getBikeRentInfoById(Long id)throws Exception;
	
	//根据blockCode查找
	public Long findBlockId(String blockCode)throws Exception;
	
	/**
	 * 根据系统参数名查找系统参数
	 * @param paramentName
	 * @return
	 * @throws Exception
	 */
	public Double getParament(String paramentName)throws Exception;

	//找车型
	public Models getModels(Long modelsId)throws Exception;

	//找站点
	public FixedReturn getFixed(Long fixedId)throws Exception;

	//获取误差距离
	public DataDet getDataDet(long dataDetId)throws Exception;

	//获取用户
	public User getUser(Long userId)throws Exception;

	//获取费用
	public RentPrice getRentPrice(Long modelsId, Integer type);
}
