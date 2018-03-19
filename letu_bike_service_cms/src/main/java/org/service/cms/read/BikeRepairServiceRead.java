package org.service.cms.read;


import java.util.List;

import org.entity.dto.BikeRepair;

public interface BikeRepairServiceRead {
	/**
	 * 查询所有负责人
	 * @return
	 * @throws Exception
	 */

     public List<BikeRepair> findAllBikeRepair() throws Exception;
     
     /**
 	 * 根据id查询负责人
 	 * @return
 	 * @throws Exception
 	 */

      public BikeRepair findBikeRepairById(Long managerId) throws Exception;
     

}		
