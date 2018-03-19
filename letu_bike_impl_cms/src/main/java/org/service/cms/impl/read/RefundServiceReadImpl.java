package org.service.cms.impl.read;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dao.RefundMapper;
import org.entity.dto.Refund;
import org.entity.dto.RefundExample;
import org.service.cms.read.RefundServiceRead;
import org.springframework.stereotype.Service;
import org.util.DateUtil;
import org.util.PageUtil;
@Service("refundServiceRead")
public class RefundServiceReadImpl implements RefundServiceRead  {
	
	@Resource
	RefundMapper refundMapper;

	@Override
	public List<Refund> findAllRefundList(Integer pageIndex, String userPhone, Date refundStarttime, Date refundEndtime,
			Date operateStarttime, Date operateEndtime, Integer refundState, String adminName,String refundOrderId) throws Exception {
		// TODO Auto-generated method stub
		RefundExample refundExample = new RefundExample();
		RefundExample.Criteria criteria = refundExample.createCriteria();
		criteria.andRefundStateNotEqualTo(-1);
		if(userPhone!=null&&!userPhone.equals("")){
			criteria.andUserTel("%"+userPhone.trim()+"%");
		}
		
		//操作人姓名
        if(adminName!=null&&!adminName.equals("")){
        	criteria.andAdminName("%"+adminName.trim()+"%");
		}
        
        //单号
        if(refundOrderId!=null&&!refundOrderId.equals("")){
        	criteria.andRefundOrderIdLike("%"+refundOrderId.trim()+"%");
		}
        
        if(refundStarttime!=null){
        	criteria.andRefundCreatetimeGreaterThanOrEqualTo(refundStarttime);
        }
        
        if(refundEndtime!=null){
        	criteria.andRefundCreatetimeLessThan(DateUtil.plusDate(refundEndtime, 1));
        }
        
        if(operateStarttime!=null){
        	criteria.andRefundOperatetimeGreaterThanOrEqualTo(operateStarttime);
        }
        
        if(operateEndtime!=null){
        	criteria.andRefundOperatetimeLessThan(DateUtil.plusDate(operateEndtime, 1));
        }
        
        if(refundState!=null&&refundState!=-2){
        	criteria.andRefundStateEqualTo(refundState);
        }
        refundExample.setOrderByClause("refund_createtime desc");
        if(pageIndex!=null){
        	refundExample.setLimitStart(PageUtil.getStart(pageIndex));
        	refundExample.setLimitEnd(PageUtil.size);
        }
		List<Refund> refundList = refundMapper.selectUnionByExample(refundExample);
		
		return refundList;
	}

	@Override
	public Integer countAllRefundList(String userPhone, Date refundStarttime, Date refundEndtime,
			Date operateStarttime, Date operateEndtime, Integer refundState, String adminName,String refundOrderId) throws Exception {
		
		RefundExample refundExample = new RefundExample();
		RefundExample.Criteria criteria = refundExample.createCriteria();
		criteria.andRefundStateNotEqualTo(-1);
		if(userPhone!=null&&!userPhone.equals("")){
			criteria.andUserTel("%"+userPhone.trim()+"%");
		}
		
        if(adminName!=null&&!adminName.equals("")){
        	criteria.andAdminName("%"+adminName.trim()+"%");
		}
        
      //单号
        if(refundOrderId!=null&&!refundOrderId.equals("")){
        	criteria.andRefundOrderIdLike("%"+refundOrderId.trim()+"%");
		}
        
        if(refundStarttime!=null){
        	criteria.andRefundCreatetimeGreaterThanOrEqualTo(refundStarttime);
        }
        
        if(refundEndtime!=null){
        	criteria.andRefundCreatetimeLessThan(DateUtil.plusDate(refundEndtime, 1));
        }
        
        if(operateStarttime!=null){
        	criteria.andRefundOperatetimeGreaterThanOrEqualTo(operateStarttime);
        }
        
        if(operateEndtime!=null){
        	criteria.andRefundOperatetimeLessThan(DateUtil.plusDate(operateEndtime, 1));
        }
        
        if(refundState!=null&&refundState!=-2){
        	criteria.andRefundStateEqualTo(refundState);
        }
		Integer pageCount = refundMapper.countUnionByExample(refundExample);
		Integer totalPage = PageUtil.getTotalPage(pageCount);
		return totalPage;
	}

	@Override
	public Refund findRefundById(Long refundId) throws Exception {
		Refund refund = refundMapper.selectByPrimaryKey(refundId);
		return refund;
	}

	@Override
	public Refund findRefundByOutTrade(String moneyLogOutTrade) throws Exception {
		RefundExample refundExample = new RefundExample();
		RefundExample.Criteria criteria = refundExample.createCriteria();
		criteria.andRefundStateNotEqualTo(-1);
		criteria.andRefundCodeEqualTo(moneyLogOutTrade);
		refundExample.setOrderByClause("refund_createtime desc");
		List<Refund> list = refundMapper.selectByExample(refundExample);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Integer findUnsuccess() throws Exception {
		// TODO Auto-generated method stub
		RefundExample refundExample = new RefundExample();
		RefundExample.Criteria criteria = refundExample.createCriteria();
		criteria.andRefundStateNotEqualTo(-1);
		criteria.andRefundStateNotEqualTo(2);
		Integer pageCount = refundMapper.countUnionByExample(refundExample);
		return pageCount;
	}

	

}
