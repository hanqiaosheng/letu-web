package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.Models;
import org.entity.dto.ModelsExample;

public interface ModelsMapper {
    int countByExample(ModelsExample example);

    int deleteByExample(ModelsExample example);

    int deleteByPrimaryKey(Long modelsId);

    int insert(Models record);

    int insertSelective(Models record);

    List<Models> selectByExample(ModelsExample example);

    Models selectByPrimaryKey(Long modelsId);

    int updateByExampleSelective(@Param("record") Models record, @Param("example") ModelsExample example);

    int updateByExample(@Param("record") Models record, @Param("example") ModelsExample example);

    int updateByPrimaryKeySelective(Models record);

    int updateByPrimaryKey(Models record);
    
    List<Models> selectUnionByExample(ModelsExample example);
    
    int countUnionByExample(ModelsExample example);
    
    List<Long> selectModelIdByExample(ModelsExample example);
}