package org.service.cms.write;



import org.entity.dto.BikeRentInfo;

public interface BikeRentInfoServiceWrite {
	
     
     /**
      * 删除车辆租赁记录
      * @param bike
      * @return
      * @throws Exception
      */
     public int deleteBikeRentInfoById(BikeRentInfo bikeRentInfo) throws Exception;

     /**
      * 更改车辆租赁 状态
      * @param bikeRentInfo
      * @throws Exception
      */
     public void updateBikeRentInfo(BikeRentInfo bikeRentInfo) throws Exception;
     
     /**
 	 * 新增租赁信息
 	 * @param bikeRentInfo
 	 * @throws Exception
 	 */
 	public long addRentInfo(BikeRentInfo bikeRentInfo) throws Exception;
     
     
}
