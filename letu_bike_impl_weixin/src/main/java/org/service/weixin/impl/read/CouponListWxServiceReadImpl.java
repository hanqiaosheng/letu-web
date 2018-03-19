package org.service.weixin.impl.read;

import org.dao.CouponMapper;
import org.entity.dto.Coupon;
import org.entity.dto.CouponExample;
import org.service.weixin.read.CouponListWxServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;

import javax.annotation.Resource;
import java.util.List;

@Service("couponListWxServiceRead")
public class CouponListWxServiceReadImpl implements CouponListWxServiceRead {
    @Resource
    CouponMapper CouponMapper;

    @Override
    public List<Coupon> findAll(Integer pageIndex, Integer page_size_web, String title) throws Exception {
        CouponExample example = new CouponExample();
        CouponExample.Criteria criteria = example.createCriteria();
        criteria.andCouponStateEqualTo(1);//使用中
        if(null!=title&&!("").equals(title)){
            criteria.andCouponNameLike("%"+title.trim()+"%");
        }
        example.setOrderByClause("coupon_create_time desc");
        example.setLimitStart(PageUtil.getStart(pageIndex));
        example.setLimitEnd(page_size_web);
        return CouponMapper.selectByExample(example);
    }

    @Override
    public Integer countAll(String title) throws Exception {
        CouponExample example = new CouponExample();
        CouponExample.Criteria criteria = example.createCriteria();
        criteria.andCouponStateEqualTo(1);//使用中
        if(null!=title&&!("").equals(title)){
            criteria.andCouponNameLike("%"+title.trim()+"%");
        }
        return CouponMapper.countByExample(example);
    }

    @Override
    public Coupon findById(Long CouponId) throws Exception {
        return CouponMapper.selectByPrimaryKey(CouponId);
    }

    @Override
    public List<Coupon> findAllCoupon() throws Exception {
        CouponExample example = new CouponExample();
        CouponExample.Criteria criteria = example.createCriteria();
        criteria.andCouponStateEqualTo(1);//使用中
        return CouponMapper.selectByExample(example);
    }
}
