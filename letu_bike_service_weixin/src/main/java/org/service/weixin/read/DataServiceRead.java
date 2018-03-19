package org.service.weixin.read;

import java.util.List;

import org.entity.dto.Data;

/**
 * 下拉数据字典业务层
 * @author Administrator
 *
 */
public interface DataServiceRead {
	
	/**
     * 查询出当前所有数据字典类型
     * @param pageIndex 页码
     * @param pageSize 每页长度
     * @return
     * @throws Exception
     */
    public List<Data> findAllData(Integer pageNo,Integer pageSize)throws Exception;
    
    /**
     * 得到当前所有的数据字典页数
     * @param pageSize
     * @return
     * @throws Exception
     */
    public Integer findTotalPage(Integer pageSize)throws Exception;
    
    /**
     * 查找某一数据字典类型
     * @param did
     * @return
     * @throws Exception
     */
    public Data findDataById(Long did)throws Exception;
    
    /**
     * 得到所有的数据字典的条数
     * @return
     * @throws Exception
     */
    public Integer findTotalCount() throws Exception;
    
    /**
     * 修改data
     * @param data
     * @return
     */
    public boolean updateData(Data data)throws Exception;
    
    /**
     * 根据名字找data
     * @param dataName
     * @return
     * @throws Exception
     */
    public Data findByName(String dataName) throws Exception;

    /**
     * 得到所有数据字典数据
     * @throws Exception
     */
	public void findData() throws Exception;
    
    
}
