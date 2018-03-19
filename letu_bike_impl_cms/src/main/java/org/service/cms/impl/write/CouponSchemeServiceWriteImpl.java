package org.service.cms.impl.write;

import org.dao.CouponSchemeMapper;
import org.entity.dto.CouponScheme;
import org.service.cms.write.CouponSchemeServiceWrite;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("couponSchemeServiceWrite")
public class CouponSchemeServiceWriteImpl implements CouponSchemeServiceWrite {
    @Resource
    CouponSchemeMapper CouponSchemeMapper;

    @Override
    public void addCouponScheme(CouponScheme CouponScheme) throws Exception {
        // TODO Auto-generated method stub
        CouponSchemeMapper.insertSelective(CouponScheme);
    }

    @Override
    public void updateCouponScheme(CouponScheme CouponScheme) throws Exception {
        // TODO Auto-generated method stub
        CouponSchemeMapper.updateByPrimaryKeySelective(CouponScheme);
    }

}
