package org.service.cms.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.DataDetMapper;
import org.dao.DataMapper;
import org.entity.dto.DataDet;
import org.entity.dto.DataDetExample;
import org.service.cms.read.DataDetServiceRead;
import org.springframework.stereotype.Service;
@Service("dataDetServiceRead")
public class DataDetServiceReadImpl implements DataDetServiceRead {
	
	@Resource
	DataMapper dataMapper;
	
	@Resource
	DataDetMapper dataDetMapper;

	@Override
	public List<DataDet> findDataDetByName(String dataName) throws Exception {
		// TODO Auto-generated method stub
		DataDetExample detExample = new DataDetExample();
		DataDetExample.Criteria criteria = detExample.createCriteria();
		//未删
		criteria.andDataDetDrEqualTo(1);
		criteria.andDataDetNameEqualTo(dataName);
		return dataDetMapper.selectByExample(detExample);
	}
	
	
	@Override
	public List<DataDet> findDataDetByDataId(Long dataId) throws Exception {
		// TODO Auto-generated method stub
		DataDetExample detExample = new DataDetExample();
		DataDetExample.Criteria criteria = detExample.createCriteria();
		//未删
		criteria.andDataDetDrEqualTo(1);
		criteria.andDataIdEqualTo(dataId);
		return dataDetMapper.selectByExample(detExample);
	}

	@Override
	public DataDet findDataDetByDataDetId(Long dataDetId) throws Exception {
		// TODO Auto-generated method stub
		return dataDetMapper.selectByPrimaryKey(dataDetId);
	}
	
	@Override
	public boolean addDataDet(DataDet dataDet) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteDataDet(DataDet dataDet) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer findDataDetCountByDname(String dataName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateDataDet(DataDet dataDet) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DataDet> findParentPermission() throws Exception {
		// TODO Auto-generated method stub
		DataDetExample dataDetExample = new DataDetExample();
		DataDetExample.Criteria criteria = dataDetExample.createCriteria();
		//为父权限
		criteria.andDataDetNameEqualTo("permission");
		criteria.andDataDetDrEqualTo(1);
		return dataDetMapper.selectByExample(dataDetExample);
	}


	

	


}
