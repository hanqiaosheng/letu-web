package org.service.cms.impl.read;


import java.util.List;

import javax.annotation.Resource;

import org.dao.RedeemPlanMapper;
import org.entity.dto.RedeemPlan;
import org.entity.dto.RedeemPlanExample;
import org.service.cms.read.RedeemPlanServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;
@Service("redeemPlanServiceRead")
public class RedeemPlanServiceReadImpl implements RedeemPlanServiceRead {
	@Resource
	RedeemPlanMapper redeemPlanMapper;

	@Override
	public List<RedeemPlan> findAll(Integer pageIndex, Integer page_size_web, String title) throws Exception {
		RedeemPlanExample example = new RedeemPlanExample();
		RedeemPlanExample.Criteria criteria = example.createCriteria();
		if(null!=title&&!"".equals(title)){
			criteria.andPlanNameLike("%"+title.trim()+"%");
		}
		criteria.andPlanStateNotEqualTo(0);//没有删除的
		criteria.andPlanTypeEqualTo(0);
		example.setOrderByClause("plan_create_time desc");
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(page_size_web);
		return redeemPlanMapper.selectUnionByExample(example);
	}

	@Override
	public Integer countAll(String title) throws Exception {
		RedeemPlanExample example = new RedeemPlanExample();
		RedeemPlanExample.Criteria criteria = example.createCriteria();
		if(null!=title&&!"".equals(title)){
			criteria.andPlanNameLike("%"+title.trim()+"%");
		}
		criteria.andPlanStateNotEqualTo(0);//没有删除的
		criteria.andPlanTypeEqualTo(0);
		return redeemPlanMapper.countUnionByExample(example);
	}

	@Override
	public RedeemPlan findRedeemPlanById(Long redeemPlanId) throws Exception {
		return redeemPlanMapper.selectByPrimaryKey(redeemPlanId);
	}

	@Override
	public List<RedeemPlan> findAllOnline() throws Exception {
		RedeemPlanExample example = new RedeemPlanExample();
		RedeemPlanExample.Criteria criteria = example.createCriteria();
		criteria.andPlanStateEqualTo(1);//上线
		criteria.andPlanTypeEqualTo(0);
		return redeemPlanMapper.selectByExample(example);
	}

	@Override
	public List<RedeemPlan> findAllPlan() throws Exception {
		RedeemPlanExample example = new RedeemPlanExample();
		RedeemPlanExample.Criteria criteria = example.createCriteria();
		criteria.andPlanStateNotEqualTo(0);
		criteria.andPlanTypeEqualTo(0);
		return redeemPlanMapper.selectByExample(example);
	}

	@Override
	public List<RedeemPlan> findAllRegistPlan(Integer pageIndex, Integer page_size_web, String title)
			throws Exception {
		RedeemPlanExample example = new RedeemPlanExample();
		RedeemPlanExample.Criteria criteria = example.createCriteria();
		if(null!=title&&!"".equals(title)){
			criteria.andPlanNameLike("%"+title.trim()+"%");
		}
		criteria.andPlanStateNotEqualTo(0);//没有删除的
		criteria.andPlanTypeEqualTo(1);
		example.setOrderByClause("plan_create_time desc");
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(page_size_web);
		return redeemPlanMapper.selectUnionByExample(example);
	}

	@Override
	public Integer countAllRegistPlan(String title) throws Exception {
		RedeemPlanExample example = new RedeemPlanExample();
		RedeemPlanExample.Criteria criteria = example.createCriteria();
		if(null!=title&&!"".equals(title)){
			criteria.andPlanNameLike("%"+title.trim()+"%");
		}
		criteria.andPlanStateNotEqualTo(0);//没有删除的
		criteria.andPlanTypeEqualTo(1);
		return redeemPlanMapper.countUnionByExample(example);
	}
}
