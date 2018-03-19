package org.service.weixin.read;

import java.util.List;

import org.entity.dto.DataDet;

/**
 * 数据字典详情业务层
 * @author Administrator
 *
 */
public interface DataDetWxServiceRead {
	
	public static Long invest  = 1432L;
	
	/**
     * 根据数据字典名获取详情列表
     * @param name
     * @return
     * @throws Exception
     */
    public List<DataDet> findDataDetByName(String dataName)throws Exception;
    
    /**
     * 根据数据字典类别ID获取详情列表
     * @param name
     * @return
     * @throws Exception
     */
    public List<DataDet> findDataDetByDataId(Long dataId)throws Exception;
    
    
    /**
     * 根据数据字典详情ID获取详情列表
     * @param name
     * @return
     * @throws Exception
     */
    public DataDet findDataDetByDataDetId(Long dataDetId)throws Exception;
    
    /**
     * 新增数据字典详情
     * @param dataDet
     * @return
     * @throws Exception
     */
    public boolean addDataDet(DataDet dataDet) throws Exception; 
    
    /**
     * 软删除数据字典详情
     * @param dataDet
     * @return
     * @throws Exception
     */
    public boolean deleteDataDet(DataDet dataDet) throws Exception; 
    
    /**
     * 根据数据字典名查找详情的条数
     * @param dataName
     * @return
     * @throws Exception
     */
    public Integer findDataDetCountByDname(String dataName) throws Exception;
    
    /**
     * 修改数据字典详情
     * @param dataDet
     * @return
     * @throws Exception
     */
    public boolean updateDataDet(DataDet dataDet) throws Exception;
    /**
     * 数据字典里取大的权限类别
     * @return
     * @throws Exception
     */
	public List<DataDet> findParentPermission() throws Exception;
    


}
