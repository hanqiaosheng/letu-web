package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.DiscountPackage;
import org.entity.dto.DiscountPackageExample;

public interface DiscountPackageMapper {
    int countByExample(DiscountPackageExample example);

    int deleteByExample(DiscountPackageExample example);

    int deleteByPrimaryKey(Long discountPackageId);

    int insert(DiscountPackage record);

    int insertSelective(DiscountPackage record);

    List<DiscountPackage> selectByExampleWithBLOBs(DiscountPackageExample example);

    List<DiscountPackage> selectByExample(DiscountPackageExample example);

    DiscountPackage selectByPrimaryKey(Long discountPackageId);

    int updateByExampleSelective(@Param("record") DiscountPackage record, @Param("example") DiscountPackageExample example);

    int updateByExampleWithBLOBs(@Param("record") DiscountPackage record, @Param("example") DiscountPackageExample example);

    int updateByExample(@Param("record") DiscountPackage record, @Param("example") DiscountPackageExample example);

    int updateByPrimaryKeySelective(DiscountPackage record);

    int updateByPrimaryKeyWithBLOBs(DiscountPackage record);

    int updateByPrimaryKey(DiscountPackage record);
}