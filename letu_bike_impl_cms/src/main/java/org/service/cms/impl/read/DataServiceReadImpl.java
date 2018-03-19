package org.service.cms.impl.read;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.dao.DataDetMapper;
import org.dao.DataMapper;
import org.entity.dto.Data;
import org.entity.dto.DataDet;
import org.entity.dto.DataDetExample;
import org.service.cms.read.DataServiceRead;
import org.springframework.stereotype.Service;
import org.util.TypedataUtil;
@Service("dataServiceRead")
public class DataServiceReadImpl implements DataServiceRead {
	
	 @Resource
	 DataMapper dataMapper;

	 @Resource
	 DataDetMapper dataDetMapper;

	@Override
	public List<Data> findAllData(Integer pageNo, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer findTotalPage(Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Data findDataById(Long did) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer findTotalCount() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateData(Data data) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Data findByName(String dataName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void findData() throws Exception {
		// TODO Auto-generated method stub
		List<Data> listName = dataMapper.selectByExample(null);
		for(Data data:listName){
			DataDetExample example = new DataDetExample();
			DataDetExample.Criteria criteria = example.createCriteria();
			criteria.andDataIdEqualTo(data.getDataId());
			criteria.andDataDetDrEqualTo(1);
			List<DataDet> list = dataDetMapper.selectByExample(example);
			Map<Long, String> newMap = new HashMap<Long, String>();
			for (DataDet typeData : list) {
				newMap.put(typeData.getDataDetId(), typeData.getDataDetVal());
			}
			TypedataUtil.getTypedata().put(data.getDataName(), newMap);
			 
			
			Map<Long,String> map = TypedataUtil.getPermission();
		}
//		System.out.println(DataUtil.getTypedata().toString());
	}

	@Override
	public Map<String, List<DataDet>> findDataDet() throws Exception {
		// TODO Auto-generated method stub
		List<Data> listName = dataMapper.selectByExample(null);
		Map<String, List<DataDet>> dataMap=new HashMap<String, List<DataDet>>();
		for(Data data:listName){
			DataDetExample example = new DataDetExample();
			DataDetExample.Criteria criteria = example.createCriteria();
			criteria.andDataIdEqualTo(data.getDataId());
			criteria.andDataDetDrEqualTo(1);
			List<DataDet> list = dataDetMapper.selectByExample(example);
			dataMap.put(data.getDataName(), list);
		}
		return dataMap;
	}
}
