package org.service.weixin.read;

import java.util.List;

import org.entity.dto.FixedReturn;
import org.entity.dto.LatLng;

public interface FixedReturnWxServiceRead {


	public FixedReturn findByFixedId(Long fixedReturnId)throws Exception;
	
	public List<LatLng> findByBlockIds(List<String> blocks) throws Exception;

	public List<LatLng> findByBlockIdsAndFixed(List<String> blocks, List<Long> fixedReturns) throws Exception;

	//通过fixedReturnIds查询
	public List<LatLng> findByFixed(List<Long> fixedReturnIds)throws Exception;

}
