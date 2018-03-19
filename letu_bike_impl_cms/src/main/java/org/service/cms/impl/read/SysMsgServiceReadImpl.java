package org.service.cms.impl.read;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dao.SysMsgMapper;
import org.entity.dto.SysMsg;
import org.entity.dto.SysMsgExample;
import org.service.cms.read.SysMsgServiceRead;
import org.springframework.stereotype.Service;
import org.util.DateUtil;
import org.util.PageUtil;
@Service("sysMsgServiceRead")
public class SysMsgServiceReadImpl implements SysMsgServiceRead {
	
	@Resource
	SysMsgMapper sysMsgMapper;

	@Override
	public List<SysMsg> findSysMsgByCondition(SysMsg sysMsg, Integer pageIndex, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		SysMsgExample example=new SysMsgExample();
		SysMsgExample.Criteria criteria=example.createCriteria();
		if(null!=sysMsg.getSysMsgTitle()&&!"".equals(sysMsg.getSysMsgTitle()))
			criteria.andSysMsgTitleLike("%"+sysMsg.getSysMsgTitle()+"%");
		if(null!=sysMsg.getSysMsgIsonline())
			criteria.andSysMsgIsonlineEqualTo(sysMsg.getSysMsgIsonline());
		if (null != sysMsg.getFromTime() && !"".equals(sysMsg.getFromTime())) {//查询的创建时间
			if (null != sysMsg.getToTime() && !"".equals(sysMsg.getToTime()))
				criteria.andSysMsgCreatetimeBetween(DateUtil.changStringDate03(sysMsg.getFromTime()),
						DateUtil.plusDate(DateUtil.changStringDate03(sysMsg.getToTime()), 1));
			else {
				criteria.andSysMsgCreatetimeBetween(DateUtil.changStringDate03(sysMsg.getFromTime()), new Date());
			}
		} else if (null != sysMsg.getToTime() && !"".equals(sysMsg.getToTime()))
			criteria.andSysMsgCreatetimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(sysMsg.getToTime()), 1));
		if (null != sysMsg.getFromTimeB() && !"".equals(sysMsg.getFromTimeB())) {//查询的上线时间
			if (null != sysMsg.getToTimeB() && !"".equals(sysMsg.getToTimeB()))
				criteria.andSysMsgOnlinetimeBetween(DateUtil.changStringDate03(sysMsg.getFromTimeB()),
						DateUtil.plusDate(DateUtil.changStringDate03(sysMsg.getToTimeB()), 1));
			else {
				criteria.andSysMsgCreatetimeBetween(DateUtil.changStringDate03(sysMsg.getFromTimeB()), new Date());
			}
		} else if (null != sysMsg.getToTimeB() && !"".equals(sysMsg.getToTimeB()))
			criteria.andSysMsgOnlinetimeLessThanOrEqualTo(DateUtil.plusDate(DateUtil.changStringDate03(sysMsg.getToTimeB()), 1));
		if (null != sysMsg.getFromTimeC() && !"".equals(sysMsg.getFromTimeC())) {//查询的下线时间
			if (null != sysMsg.getToTimeC() && !"".equals(sysMsg.getToTimeC()))
				criteria.andSysMsgOfflinetimeBetween(DateUtil.changStringDate03(sysMsg.getFromTimeC()),
						DateUtil.plusDate(DateUtil.changStringDate03(sysMsg.getToTimeC()), 1));
			else {
				criteria.andSysMsgCreatetimeBetween(DateUtil.changStringDate03(sysMsg.getFromTimeC()), new Date());
			}
		} else if (null != sysMsg.getToTimeC() && !"".equals(sysMsg.getToTimeC()))
			criteria.andSysMsgOfflinetimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(sysMsg.getToTimeC()), 1));
		criteria.andSysMsgIsonlineNotEqualTo((short)-1);
		example.setOrderByClause("sys_msg_isonline=1 desc,sys_msg_createtime DESC");
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(pageSize);
		return sysMsgMapper.selectByExample(example);
	}

	@Override
	public SysMsg findById(Long sysMsgId) throws Exception {
		// TODO Auto-generated method stub
		return sysMsgMapper.selectByPrimaryKey(sysMsgId);
	}

	@Override
	public Integer countAllSysMsg(SysMsg sysMsg) throws Exception {
		// TODO Auto-generated method stub
		SysMsgExample example=new SysMsgExample();
		SysMsgExample.Criteria criteria=example.createCriteria();
		if(null!=sysMsg.getSysMsgTitle()&&!"".equals(sysMsg.getSysMsgTitle()))
			criteria.andSysMsgTitleLike("%"+sysMsg.getSysMsgTitle()+"%");
		if(null!=sysMsg.getSysMsgIsonline())
			criteria.andSysMsgIsonlineEqualTo(sysMsg.getSysMsgIsonline());
		if (null != sysMsg.getFromTime() && !"".equals(sysMsg.getFromTime())) {
			if (null != sysMsg.getToTime() && !"".equals(sysMsg.getToTime()))
				criteria.andSysMsgCreatetimeBetween(DateUtil.changStringDate03(sysMsg.getFromTime()),
						DateUtil.plusDate(DateUtil.changStringDate03(sysMsg.getToTime()), 1));
			else {
				criteria.andSysMsgCreatetimeBetween(DateUtil.changStringDate03(sysMsg.getFromTime()), new Date());
			}
		} else if (null != sysMsg.getToTime() && !"".equals(sysMsg.getToTime()))
			criteria.andSysMsgCreatetimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(sysMsg.getToTime()), 1));
		if (null != sysMsg.getFromTimeB() && !"".equals(sysMsg.getFromTimeB())) {//查询的上线时间
			if (null != sysMsg.getToTimeB() && !"".equals(sysMsg.getToTimeB()))
				criteria.andSysMsgOnlinetimeBetween(DateUtil.changStringDate03(sysMsg.getFromTimeB()),
						DateUtil.changStringDate03(sysMsg.getToTimeB()));
			else {
				criteria.andSysMsgCreatetimeBetween(DateUtil.changStringDate03(sysMsg.getFromTimeB()), new Date());
			}
		} else if (null != sysMsg.getToTimeB() && !"".equals(sysMsg.getToTimeB()))
			criteria.andSysMsgOnlinetimeLessThanOrEqualTo(DateUtil.changStringDate03(sysMsg.getToTimeB()));
		if (null != sysMsg.getFromTimeC() && !"".equals(sysMsg.getFromTimeC())) {//查询的下线时间
			if (null != sysMsg.getToTimeC() && !"".equals(sysMsg.getToTimeC()))
				criteria.andSysMsgOfflinetimeBetween(DateUtil.changStringDate03(sysMsg.getFromTimeC()),
						DateUtil.plusDate(DateUtil.changStringDate03(sysMsg.getToTimeC()), 1));
			else {
				criteria.andSysMsgCreatetimeBetween(DateUtil.changStringDate03(sysMsg.getFromTimeC()), new Date());
			}
		} else if (null != sysMsg.getToTimeC() && !"".equals(sysMsg.getToTimeC()))
			criteria.andSysMsgOfflinetimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(sysMsg.getToTimeC()), 1));
		criteria.andSysMsgIsonlineNotEqualTo((short)-1);
		return sysMsgMapper.countByExample(example);
	}

}
