package org.service.cms.impl.read;

import org.dao.CouponSchemeMapper;
import org.entity.dto.CouponScheme;
import org.entity.dto.CouponSchemeExample;
import org.service.cms.read.CouponSchemeServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;

import javax.annotation.Resource;
import java.util.List;

@Service("couponSchemeServiceRead")
public class CouponSchemeServiceReadImpl implements CouponSchemeServiceRead {
    @Resource
    CouponSchemeMapper couponSchemeMapper;

    @Override
    public List<CouponScheme> findAll(Integer pageIndex, Integer page_size_web, String title) throws Exception {
        CouponSchemeExample example = new CouponSchemeExample();
        CouponSchemeExample.Criteria criteria = example.createCriteria();
        if(null!=title&&!"".equals(title)){
            criteria.andPlanNameLike("%"+title.trim()+"%");
        }
        criteria.andPlanStateNotEqualTo(0);//没有删除的
        criteria.andPlanTypeEqualTo(0);
        example.setOrderByClause("plan_create_time desc");
        example.setLimitStart(PageUtil.getStart(pageIndex));
        example.setLimitEnd(page_size_web);
        return couponSchemeMapper.selectUnionByExample(example);
    }

    @Override
    public Integer countAll(String title) throws Exception {
        CouponSchemeExample example = new CouponSchemeExample();
        CouponSchemeExample.Criteria criteria = example.createCriteria();
        if(null!=title&&!"".equals(title)){
            criteria.andPlanNameLike("%"+title.trim()+"%");
        }
        criteria.andPlanStateNotEqualTo(0);//没有删除的
        criteria.andPlanTypeEqualTo(0);
        return couponSchemeMapper.countUnionByExample(example);
    }

    @Override
    public CouponScheme findCouponSchemeById(Long couponSchemeId) throws Exception {
        return couponSchemeMapper.selectByPrimaryKey(couponSchemeId);
    }

    @Override
    public CouponScheme findUnionById(Long couponSchemeId) throws Exception {
        return couponSchemeMapper.selectUnionByPrimaryKey(couponSchemeId);
    }


    @Override
    public List<CouponScheme> findAllOnline() throws Exception {
        CouponSchemeExample example = new CouponSchemeExample();
        CouponSchemeExample.Criteria criteria = example.createCriteria();
        criteria.andPlanStateEqualTo(1);//上线
        criteria.andPlanTypeEqualTo(0);
        return couponSchemeMapper.selectByExample(example);
    }

    @Override
    public List<CouponScheme> findAllPlan() throws Exception {
        CouponSchemeExample example = new CouponSchemeExample();
        CouponSchemeExample.Criteria criteria = example.createCriteria();
        criteria.andPlanStateNotEqualTo(0);
        criteria.andPlanTypeEqualTo(0);
        return couponSchemeMapper.selectByExample(example);
    }
}
