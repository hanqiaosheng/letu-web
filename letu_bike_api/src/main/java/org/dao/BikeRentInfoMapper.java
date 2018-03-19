package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.BikeRentInfoExample;

public interface BikeRentInfoMapper {
    int countByExample(BikeRentInfoExample example);
    
    int countUnionByExample(BikeRentInfoExample example);

    int deleteByExample(BikeRentInfoExample example);

    int deleteByPrimaryKey(Long rentInfoId);

    int insert(BikeRentInfo record);

    Long insertSelective(BikeRentInfo record);

    List<BikeRentInfo> selectByExampleWithBLOBs(BikeRentInfoExample example);

    List<BikeRentInfo> selectByExample(BikeRentInfoExample example);
    
    List<BikeRentInfo> selectUnionByExample(BikeRentInfoExample example);
    
    BikeRentInfo selectByPrimaryKey(Long rentInfoId);
    
    BikeRentInfo selectUnionByPrimaryKey(Long rentInfoId);

    int updateByExampleSelective(@Param("record") BikeRentInfo record, @Param("example") BikeRentInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") BikeRentInfo record, @Param("example") BikeRentInfoExample example);

    int updateByExample(@Param("record") BikeRentInfo record, @Param("example") BikeRentInfoExample example);

    int updateByPrimaryKeySelective(BikeRentInfo record);

    int updateByPrimaryKeyWithBLOBs(BikeRentInfo record);

    int updateByPrimaryKey(BikeRentInfo record);
    
    List<BikeRentInfo> selectByExampleUser(BikeRentInfoExample example);

    List<BikeRentInfo> selectByUserIdsAndState(@Param("userIds") List<Long> userIds,@Param("state") int state);

    List<BikeRentInfo> selectByUserIdAndGroupId(@Param("userId") Long userId,@Param("groupId") Long groupId);

}