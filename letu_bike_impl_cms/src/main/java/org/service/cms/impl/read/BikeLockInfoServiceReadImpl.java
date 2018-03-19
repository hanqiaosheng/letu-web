package org.service.cms.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.BikeLockInfoMapper;
import org.entity.dto.BikeLockInfo;
import org.entity.dto.BikeLockInfoExample;
import org.service.cms.read.BikeLockInfoServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;

@Service("bikeLockInfoServiceRead")
public class BikeLockInfoServiceReadImpl implements BikeLockInfoServiceRead {

	@Resource
	BikeLockInfoMapper bikeLockInfoMapper;

	public List<BikeLockInfo> findAllLock() throws Exception {
		BikeLockInfoExample example = new BikeLockInfoExample();
		// BikeLockInfoExample.Criteria criteria = example.createCriteria();
		List<BikeLockInfo> lockList = bikeLockInfoMapper.selectByExample(example);
		return lockList;
	}

	@Override
	public List<BikeLockInfo> findBikeLockInfoByCondition(BikeLockInfo bikeLockInfo, Integer pageIndex,
			Integer pageSize,String bikeCode,Long modelsId, Integer flag) throws Exception {
		// TODO Auto-generated method stub
		BikeLockInfoExample example = new BikeLockInfoExample();
		BikeLockInfoExample.Criteria criteria = example.createCriteria();
		if (null != bikeLockInfo.getBikeLockCode() && !"".equals(bikeLockInfo.getBikeLockCode()))
			criteria.andBikeLockCodeLike("%" + bikeLockInfo.getBikeLockCode().trim() + "%");
		if (null != bikeLockInfo.getBikeLockStatus() && -1 != bikeLockInfo.getBikeLockStatus())
			criteria.andBikeLockStatusEqualTo(bikeLockInfo.getBikeLockStatus());
		if (null != bikeLockInfo.getBikeLockState() && -1 != bikeLockInfo.getBikeLockState())
			criteria.andBikeLockStateEqualTo(bikeLockInfo.getBikeLockState());
		if(null != bikeCode && !"".equals(bikeCode)){
			criteria.andBikeCodeLike("%"+bikeCode.trim()+"%");
		}
		if(null!=bikeLockInfo.getBikeLockVoltage() && -1 != bikeLockInfo.getBikeLockVoltage()){
			if(bikeLockInfo.getBikeLockVoltage()==0){
				criteria.andBikeLockVoltageGreaterThanOrEqualTo(3.6);//电量
			}else if(bikeLockInfo.getBikeLockVoltage()==1){
				criteria.andBikeLockVoltageLessThan(3.6);
			}
		}
		if(null!=bikeLockInfo.getBikeLockIsFence() && -1 != bikeLockInfo.getBikeLockIsFence()){
			criteria.andBikeLockIsFenceEqualTo(bikeLockInfo.getBikeLockIsFence());//是否使用电子围栏
		}
		if(null!=bikeLockInfo.getBikeLockUpstate() && -1 != bikeLockInfo.getBikeLockUpstate()){
			criteria.andBikeLockUpstateEqualTo(bikeLockInfo.getBikeLockUpstate());//升级完成状态
		}
		if (null != bikeLockInfo.getBikeLockVersion() && !"".equals(bikeLockInfo.getBikeLockVersion()))
			criteria.andBikeLockVersionLike("%" + bikeLockInfo.getBikeLockVersion().trim() + "%");
		if(null!=modelsId&&-1!=modelsId){
			criteria.andModelsIdEqualTo(modelsId);
		}
		criteria.andBikeLockDelEqualTo(0);//未删除
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(pageSize);
		example.setOrderByClause("bli.bike_lock_Id desc");
		return bikeLockInfoMapper.selectUnionByExample(example);
	}

	@Override
	public BikeLockInfo findById(Long bikeLockInfoId) throws Exception {
		// TODO Auto-generated method stub
		return bikeLockInfoMapper.selectByPrimaryKey(bikeLockInfoId);
	}

	@Override
	public Integer countAllBikeLockInfo(BikeLockInfo bikeLockInfo,String bikeCode,Long modelsId, Integer flag) throws Exception {
		// TODO Auto-generated method stub
		BikeLockInfoExample example = new BikeLockInfoExample();
		BikeLockInfoExample.Criteria criteria = example.createCriteria();
		if (null != bikeLockInfo.getBikeLockCode() && !"".equals(bikeLockInfo.getBikeLockCode()))
			criteria.andBikeLockCodeLike("%" + bikeLockInfo.getBikeLockCode().trim() + "%");
		if (null != bikeLockInfo.getBikeLockStatus() && -1 != bikeLockInfo.getBikeLockStatus())
			criteria.andBikeLockStatusEqualTo(bikeLockInfo.getBikeLockStatus());
		if (null != bikeLockInfo.getBikeLockState() && -1 != bikeLockInfo.getBikeLockState())
			criteria.andBikeLockStateEqualTo(bikeLockInfo.getBikeLockState());
		if(null != bikeCode && !"".equals(bikeCode)){
			criteria.andBikeCodeLike("%"+bikeCode.trim()+"%");
		}
		if(null!=bikeLockInfo.getBikeLockVoltage() && -1 != bikeLockInfo.getBikeLockVoltage()){
			if(bikeLockInfo.getBikeLockVoltage()==0){
				criteria.andBikeLockVoltageGreaterThanOrEqualTo(3.6);//电量
			}else if(bikeLockInfo.getBikeLockVoltage()==1){
				criteria.andBikeLockVoltageLessThan(3.6);
			}
		}
		
		if(null!=bikeLockInfo.getBikeLockUpstate() && -1 != bikeLockInfo.getBikeLockUpstate()){
			criteria.andBikeLockUpstateEqualTo(bikeLockInfo.getBikeLockUpstate());//升级完成状态
		}
		if (null != bikeLockInfo.getBikeLockVersion() && !"".equals(bikeLockInfo.getBikeLockVersion()))
			criteria.andBikeLockVersionLike("%" + bikeLockInfo.getBikeLockVersion().trim() + "%");
		if(null!=modelsId&&-1!=modelsId){
			criteria.andModelsIdEqualTo(modelsId);
		}
		criteria.andBikeLockDelEqualTo(0);//未删除
		return bikeLockInfoMapper.countUnionByExample(example);
	}

	@Override
	public BikeLockInfo findByCode(String lockCode) throws Exception {
		// TODO Auto-generated method stub
		BikeLockInfoExample example = new BikeLockInfoExample();
		BikeLockInfoExample.Criteria criteria = example.createCriteria();
		criteria.andBikeLockCodeEqualTo(lockCode);
		criteria.andBikeLockDelEqualTo(0);
		List<BikeLockInfo> lockList = bikeLockInfoMapper.selectByExample(example);
		if(lockList.size()>0){
			return lockList.get(0);
		}
		return null;
	}

	@Override
	public Integer countuntreated() throws Exception {
		// TODO Auto-generated method stub
		BikeLockInfoExample example = new BikeLockInfoExample();
		BikeLockInfoExample.Criteria criteria = example.createCriteria();
		criteria.andBikeLockDelEqualTo(0);
		criteria.andBikeLockStateEqualTo(0);
		return bikeLockInfoMapper.countUnionByExample(example);
	}

	@Override
	public Integer countLowBattery() throws Exception {
		// TODO Auto-generated method stub
		BikeLockInfoExample example = new BikeLockInfoExample();
		BikeLockInfoExample.Criteria criteria = example.createCriteria();
		criteria.andBikeLockDelEqualTo(0);
		criteria.andBikeLockVoltageLessThan(3.5);
		return bikeLockInfoMapper.countUnionByExample(example);
	}

	@Override
	public List<BikeLockInfo> findBikeLockInfoByCondition(BikeLockInfo bikeLockInfo,
			Integer pageSize,String bikeCode,Long modelsId, Integer flag) throws Exception {
		// TODO Auto-generated method stub
		BikeLockInfoExample example = new BikeLockInfoExample();
		BikeLockInfoExample.Criteria criteria = example.createCriteria();
		if (null != bikeLockInfo.getBikeLockCode() && !"".equals(bikeLockInfo.getBikeLockCode()))
			criteria.andBikeLockCodeLike("%" + bikeLockInfo.getBikeLockCode().trim() + "%");
		if (null != bikeLockInfo.getBikeLockStatus() && -1 != bikeLockInfo.getBikeLockStatus())
			criteria.andBikeLockStatusEqualTo(bikeLockInfo.getBikeLockStatus());
		if (null != bikeLockInfo.getBikeLockState() && -1 != bikeLockInfo.getBikeLockState())
			criteria.andBikeLockStateEqualTo(bikeLockInfo.getBikeLockState());
		if(null != bikeCode && !"".equals(bikeCode)){
			criteria.andBikeCodeLike("%"+bikeCode.trim()+"%");
		}
		if(null!=bikeLockInfo.getBikeLockVoltage() && -1 != bikeLockInfo.getBikeLockVoltage()){
			if(bikeLockInfo.getBikeLockVoltage()==0){
				criteria.andBikeLockVoltageGreaterThanOrEqualTo(3.6);//电量
			}else if(bikeLockInfo.getBikeLockVoltage()==1){
				criteria.andBikeLockVoltageLessThan(3.6);
			}
		}
		if(null!=bikeLockInfo.getBikeLockIsFence() && -1 != bikeLockInfo.getBikeLockIsFence()){
			criteria.andBikeLockIsFenceEqualTo(bikeLockInfo.getBikeLockIsFence());//是否使用电子围栏
		}
		criteria.andBikeLockVoltageGreaterThanOrEqualTo(3.6);//电量
		if(null!=bikeLockInfo.getBikeLockUpstate() && -1 != bikeLockInfo.getBikeLockUpstate()){
			criteria.andBikeLockUpstateEqualTo(bikeLockInfo.getBikeLockUpstate());//升级完成状态
		}
		if (null != bikeLockInfo.getBikeLockVersion() && !"".equals(bikeLockInfo.getBikeLockVersion()))
			criteria.andBikeLockVersionLike("%" + bikeLockInfo.getBikeLockVersion().trim() + "%");
		if(null!=modelsId&&-1!=modelsId){
			criteria.andModelsIdEqualTo(modelsId);
		}
		criteria.andBikeLockDelEqualTo(0);//未删除
		return bikeLockInfoMapper.selectUnionByExample(example);
	}

	@Override
	public List<BikeLockInfo> findAllUpLock() throws Exception {
		BikeLockInfoExample example = new BikeLockInfoExample();
		BikeLockInfoExample.Criteria criteria = example.createCriteria();
		criteria.andBikeLockUpstateEqualTo(0);
		criteria.andBikeLockDelEqualTo(0);
		List<BikeLockInfo> lockList = bikeLockInfoMapper.selectByExample(example);
		return lockList;
	}

}
