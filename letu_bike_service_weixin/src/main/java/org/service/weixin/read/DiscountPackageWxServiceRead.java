package org.service.weixin.read;


import java.util.List;

import org.entity.dto.DiscountPackage;

public interface DiscountPackageWxServiceRead {
	public DiscountPackage findByScenicId(Long scenicId)throws Exception;

	public List<DiscountPackage> findAllByScenicId(Long scenicSpotId)throws Exception;

	public Integer countAllByScenicId(Long scenicSpotId)throws Exception;
	
	public DiscountPackage findByPackageId(Long packageId)throws Exception;

}
