package org.service.cms.impl.write;

import org.entity.dto.CouponToUser;
import org.service.cms.write.CouponToUserServiceWrite;
import org.entity.dto.CouponToUserExample;
import org.dao.CouponToUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("couponToUserServiceWrite")
public class CouponToUserServiceWriteImpl implements CouponToUserServiceWrite{
    @Resource
    CouponToUserMapper couponToUserMapper;

    @Override
    public void add(CouponToUser couponToUser) throws Exception {
        couponToUserMapper.insertSelective(couponToUser);
    }

    @Override
    public void update(CouponToUser couponToUser) throws Exception {
        CouponToUserExample example = new CouponToUserExample();
        CouponToUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(couponToUser.getUserId());
        criteria.andPlanIdEqualTo(couponToUser.getPlanId());
        couponToUserMapper.updateByExample(couponToUser,example);
    }

    @Override
    public void delete(CouponToUser couponToUser) throws Exception {
        CouponToUserExample example = new CouponToUserExample();
        CouponToUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(couponToUser.getUserId());
        criteria.andPlanIdEqualTo(couponToUser.getPlanId());
        couponToUserMapper.deleteByExample(example);
    }
}
