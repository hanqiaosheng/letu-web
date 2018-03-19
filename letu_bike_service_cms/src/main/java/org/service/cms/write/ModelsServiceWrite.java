package org.service.cms.write;


import org.entity.dto.Models;

public interface ModelsServiceWrite {
     
     /**
      * 删除车辆型号
      * @param models
      * @return
      * @throws Exception
      */
     public int deleteModelsById(Models models) throws Exception;
     
     /**
      * 增加车辆型号
      * @param models
      * @return
      * @throws Exception
      */
     public Long addModels(Models models) throws Exception;
     
     /**
      * 编辑车辆型号
      * @param models
      * @return
      * @throws Exception
      */
     public int updateModels(Models models) throws Exception;
     
    
}
