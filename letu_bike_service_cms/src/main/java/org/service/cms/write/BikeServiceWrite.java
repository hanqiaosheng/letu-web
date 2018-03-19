package org.service.cms.write;


import org.entity.dto.Bike;

public interface BikeServiceWrite {
     
     /**
      * 删除车辆
      * @param bike
      * @return
      * @throws Exception
      */
     public int deleteBikeById(Bike bike) throws Exception;
     
     /**
      * 增加车辆
      * @param bike
      * @return
      * @throws Exception
      */
     public Long addBike(Bike bike) throws Exception;
     
     /**
      * 编辑车辆
      * @param bike
      * @return
      * @throws Exception
      */
     public int editBike(Bike bike) throws Exception;
     
     /**
      * 更新车辆可以为null
      * @param bike
      * @return
      * @throws Exception
      */
     public int updateBike(Bike bike) throws Exception;
     
    
}
