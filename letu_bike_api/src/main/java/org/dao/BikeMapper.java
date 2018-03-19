package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.Bike;
import org.entity.dto.BikeExample;
import org.entity.dto.LatLng;

public interface BikeMapper {
    int countByExample(BikeExample example);
    
    int countUnionByExample(BikeExample example);

    int deleteByExample(BikeExample example);

    int deleteByPrimaryKey(Long bikeId);

    int insert(Bike record);

    int insertSelective(Bike record);

    List<Bike> selectByExample(BikeExample example);
    
    List<LatLng> selectBikesLatLng(BikeExample example);
    
    List<Bike> selectUnionByExample(BikeExample example);

    Bike selectByPrimaryKey(Long bikeId);
    
    Bike selectUnionByPrimaryKey(Long bikeId);

    int updateByExampleSelective(@Param("record") Bike record, @Param("example") BikeExample example);

    int updateByExample(@Param("record") Bike record, @Param("example") BikeExample example);

    int updateByPrimaryKeySelective(Bike record);

    int updateByPrimaryKey(Bike record);
	
	List<String> selectBikeBatchNumber();
	
	String selectMaxBatch();}