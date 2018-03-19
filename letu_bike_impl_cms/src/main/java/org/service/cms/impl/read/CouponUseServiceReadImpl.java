package org.service.cms.impl.read;

import org.dao.CouponUseMapper;
import org.entity.dto.CouponUse;
import org.entity.dto.CouponUseExample;
import org.service.cms.read.CouponUseServiceRead;
import org.util.DateUtil;
import org.util.PageUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class CouponUseServiceReadImpl implements CouponUseServiceRead{
    @Resource
    CouponUseMapper CouponUseMapper;

    @Override
    public List<CouponUse> findAllCouponUse(Integer pageIndex, Integer pageSize, String name, Date startTime,
                                                      Date endTime, String userTel, String redeemCode) throws Exception {
        CouponUseExample example = new CouponUseExample();
        CouponUseExample.Criteria criteria = example.createCriteria();
//        if(null!=name&&!name.equals("")){
//            criteria.andRedeemPlanNameLike("%"+name+"%");
//        }
//        if(null!=userTel&&!userTel.equals("")){
//            criteria.andUserTelLike("%"+userTel+"%");
//        }
//        if(null!=redeemCode&&!redeemCode.equals("")){
//            criteria.andRedeemCodeLike("%"+redeemCode+"%");
//        }
//        if(null!=startTime){
//            criteria.andUrpCreateTimeGreaterThanOrEqualTo(startTime);
//        }
//        if(null!=endTime){
//            criteria.andUrpCreateTimeLessThan(DateUtil.plusDateNum(endTime, 1));
//        }
        if(null!=pageIndex){
            example.setLimitStart(PageUtil.getStart(pageIndex));
            example.setLimitEnd(pageSize);
        }
        example.setOrderByClause("create_time desc");
        return CouponUseMapper.selectUnionByExample(example);
    }

    @Override
    public Integer countAllCouponUse(String name, Date startTime, Date endTime,String userTel,String redeemCode) throws Exception {
        CouponUseExample example = new CouponUseExample();
        CouponUseExample.Criteria criteria = example.createCriteria();
//        if(null!=name&&!name.equals("")){
//            criteria.andRedeemPlanNameLike("%"+name+"%");
//        }
//        if(null!=userTel&&!userTel.equals("")){
//            criteria.andUserTelLike("%"+userTel+"%");
//        }
//        if(null!=redeemCode&&!redeemCode.equals("")){
//            criteria.andRedeemCodeLike("%"+redeemCode+"%");
//        }
//        if(null!=startTime){
//            criteria.andUrpCreateTimeGreaterThanOrEqualTo(startTime);
//        }
//        if(null!=endTime){
//            criteria.andUrpCreateTimeLessThan(DateUtil.plusDateNum(endTime, 1));
//        }
        return CouponUseMapper.countUnionByExample(example);
    }


}
