package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.BikeRentInfoMapper;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.BikeRentInfoExample;
import org.service.weixin.read.BikeRentInfoWxServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;

@Service("bikeRentInfoWxServiceRead")
public class BikeRentInfoWxServiceReadImpl implements BikeRentInfoWxServiceRead {

	@Resource
	BikeRentInfoMapper bikeRentInfoMapper;

	@Override
	public List<BikeRentInfo> findByUserId(Long userId,Integer pageIndex,Integer page_size_weixin) throws Exception {
		// TODO Auto-generated method stub
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		//已完成
		criteria.andRentStateEqualTo(1);
		//未删
		criteria.andRentIsdelEqualTo(0);
		criteria.andRentInfoUserIdEqualTo(userId);
		bikeRentInfoExample.setLimitStart(PageUtil.getWeixinStart(pageIndex));
		bikeRentInfoExample.setLimitEnd(page_size_weixin);
		bikeRentInfoExample.setOrderByClause("rent_starttime DESC");
		List<BikeRentInfo> bikeRentInfos = bikeRentInfoMapper.selectByExample(bikeRentInfoExample);
		return bikeRentInfos;
	}

	@Override
	public BikeRentInfo findByBikeRentId(Long bikeRentId) throws Exception {
		// TODO Auto-generated method stub
		
		return bikeRentInfoMapper.selectByPrimaryKey(bikeRentId);
	}

	@Override
	public Integer findCountByUserId(Long userId) throws Exception {
		// TODO Auto-generated method stub
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		//已完成
		criteria.andRentStateEqualTo(1);
		//未删
		criteria.andRentIsdelEqualTo(0);
		criteria.andRentInfoUserIdEqualTo(userId);
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
	public BikeRentInfo findNotPayByUserId(Long userId) throws Exception {
		// TODO Auto-generated method stub
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		criteria.andRentInfoUserIdEqualTo(userId);//用户id
		criteria.andRentStateEqualTo(2);//未完成标志
		criteria.andRentEndtimeIsNotNull();//有结束时间
		criteria.andRentIsdelEqualTo(0);//未删
		bikeRentInfoExample.setOrderByClause("rent_starttime desc");
		List<BikeRentInfo> bikeRentInfos = bikeRentInfoMapper.selectByExample(bikeRentInfoExample);
		if(bikeRentInfos.size()>0){
			return bikeRentInfos.get(0);
		}
		
		return null;
	}

	@Override
	public List<BikeRentInfo> findByUserIdAndNoInvoiceId(Long userId, Integer pageIndex, Integer page_size_weixin)
			throws Exception {
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		//已完成
		criteria.andRentStateEqualTo(1);
		//未删
		criteria.andRentIsdelEqualTo(0);
		criteria.andRentInvoiceIdIsNull();//未申请发票
		criteria.andRentInfoUserIdEqualTo(userId);
		bikeRentInfoExample.setLimitStart(PageUtil.getWeixinStart(pageIndex));
		bikeRentInfoExample.setLimitEnd(page_size_weixin);
		bikeRentInfoExample.setOrderByClause("rent_starttime DESC");
		List<BikeRentInfo> bikeRentInfos = bikeRentInfoMapper.selectByExample(bikeRentInfoExample);
		return bikeRentInfos;
	}

	@Override
	public Integer findCountByUserIdAndNoInvoiceId(Long userId) {
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		//已完成
		criteria.andRentStateEqualTo(1);
		//未删
		criteria.andRentIsdelEqualTo(0);
		criteria.andRentInvoiceIdIsNull();//未申请发票
		criteria.andRentInfoUserIdEqualTo(userId);
		return bikeRentInfoMapper.countByExample(bikeRentInfoExample);
	}

	@Override
	public List<BikeRentInfo> findByInvoiceId(Long invoiceId) throws Exception {
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		criteria.andRentInvoiceIdEqualTo(invoiceId);
		bikeRentInfoExample.setOrderByClause("rent_starttime ASC");
		return bikeRentInfoMapper.selectByExample(bikeRentInfoExample);
	}

	@Override
	public BikeRentInfo findRecentRentInfo(Long bikeId) throws Exception {
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		criteria.andRentInfoBikeIdEqualTo(bikeId);
		criteria.andRentStateEqualTo(1);
		bikeRentInfoExample.setOrderByClause("rent_endtime DESC");
		List<BikeRentInfo> bikeRentInfos = bikeRentInfoMapper.selectByExample(bikeRentInfoExample);
		if(bikeRentInfos.size()>0){
			return bikeRentInfos.get(0);
		}
		return null;
	}

	@Override
	public List<BikeRentInfo> findFinishByUserId(Long userId) throws Exception {
		BikeRentInfoExample bikeRentInfoExample = new BikeRentInfoExample();
		BikeRentInfoExample.Criteria criteria = bikeRentInfoExample.createCriteria();
		//已完成
		criteria.andRentStateEqualTo(1);
		//未删
		criteria.andRentIsdelEqualTo(0);
		criteria.andRentInfoUserIdEqualTo(userId);
		return bikeRentInfoMapper.selectByExample(bikeRentInfoExample);
	}

	
}
