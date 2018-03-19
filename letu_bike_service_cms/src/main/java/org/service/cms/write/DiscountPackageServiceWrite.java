package org.service.cms.write;

import org.entity.dto.DiscountPackage;

public interface DiscountPackageServiceWrite {

	public void add(DiscountPackage discountPackage)throws Exception;

	public void update(DiscountPackage discountPackage)throws Exception;

}
