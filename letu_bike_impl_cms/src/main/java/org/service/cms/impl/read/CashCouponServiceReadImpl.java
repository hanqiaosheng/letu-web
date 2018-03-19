package org.service.cms.impl.read;


import java.util.List;

import javax.annotation.Resource;

import org.dao.CashCouponMapper;
import org.entity.dto.CashCoupon;
import org.entity.dto.CashCouponExample;
import org.service.cms.read.CashCouponServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;
@Service("cashCouponServiceRead")
public class CashCouponServiceReadImpl implements CashCouponServiceRead {
	@Resource
	CashCouponMapper cashCouponMapper;

	@Override
	public List<CashCoupon> findAll(Integer pageIndex, Integer page_size_web, String title) throws Exception {
		CashCouponExample example = new CashCouponExample();
		CashCouponExample.Criteria criteria = example.createCriteria();
		criteria.andCouponStateEqualTo(1);//使用中
		if(null!=title&&!("").equals(title)){
			criteria.andCouponNameLike("%"+title.trim()+"%");
		}
		example.setOrderByClause("coupon_create_time desc");
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(page_size_web);
		return cashCouponMapper.selectByExample(example);
	}

	@Override
	public Integer countAll(String title) throws Exception {
		CashCouponExample example = new CashCouponExample();
		CashCouponExample.Criteria criteria = example.createCriteria();
		criteria.andCouponStateEqualTo(1);//使用中
		if(null!=title&&!("").equals(title)){
			criteria.andCouponNameLike("%"+title.trim()+"%");
		}
		return cashCouponMapper.countByExample(example);
	}

	@Override
	public CashCoupon findById(Long cashCouponId) throws Exception {
		return cashCouponMapper.selectByPrimaryKey(cashCouponId);
	}

	@Override
	public List<CashCoupon> findAllCashCoupon() throws Exception {
		CashCouponExample example = new CashCouponExample();
		CashCouponExample.Criteria criteria = example.createCriteria();
		criteria.andCouponStateEqualTo(1);//使用中
		return cashCouponMapper.selectByExample(example);
	}
	

	
}
