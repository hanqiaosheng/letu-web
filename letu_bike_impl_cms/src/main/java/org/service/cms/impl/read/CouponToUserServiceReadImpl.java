package org.service.cms.impl.read;

import org.service.cms.read.CouponToUserServiceRead;
import org.dao.CouponToUserMapper;
import org.entity.dto.CouponToUserExample;
import org.entity.dto.CouponToUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service("couponToUserServiceRead")
public class CouponToUserServiceReadImpl implements CouponToUserServiceRead {
    @Resource
    CouponToUserMapper couponToUserMapper;

    @Override
    public List<Long> findByCouponId(Long couponId) throws Exception {
        CouponToUserExample example = new CouponToUserExample();
        CouponToUserExample.Criteria criteria = example.createCriteria();
        criteria.andPlanIdEqualTo(couponId);
        List<CouponToUser> couponToUsers = couponToUserMapper.selectByExample(example);
        List<Long> userIds = new LinkedList<>();
        for(CouponToUser couponToUser:couponToUsers){
            userIds.add(couponToUser.getUserId());
        }
        return userIds;
    }

    @Override
    public List<Long> findByUserId(Long userId) throws Exception {
        CouponToUserExample example = new CouponToUserExample();
        CouponToUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<CouponToUser> couponToUsers = couponToUserMapper.selectByExample(example);
        List<Long> planIds = new LinkedList<>();
        for(CouponToUser couponToUser:couponToUsers){
            planIds.add(couponToUser.getPlanId());
        }
        return planIds;
    }

    @Override
    public CouponToUser findByIds(Long couponId, Long userId) throws Exception {
        CouponToUserExample example = new CouponToUserExample();
        CouponToUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andPlanIdEqualTo(couponId);
        List<CouponToUser> couponToUsers = couponToUserMapper.selectByExample(example);
        if(couponToUsers.size()>0){
            return couponToUsers.get(0);
        }
        return null;
    }
}
