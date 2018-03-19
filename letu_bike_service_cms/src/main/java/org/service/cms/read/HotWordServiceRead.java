package org.service.cms.read;

import java.util.List;

import org.entity.dto.HotWord;

public interface HotWordServiceRead {
	/**
	 * 条件查询
	 * @param pageIndex
	 * @param pageSize
	 * @param wordName
	 * @return
	 * @throws Exception
	 */
	public List<HotWord> findByCondition(Integer pageIndex,Integer pageSize,String wordName)throws Exception;
	/**
	 * 条件查询数量
	 * @param wordName
	 * @return
	 * @throws Exception
	 */
	public Integer countByCondition(String wordName)throws Exception;
	/**
	 * 根据id查询信息
	 * @param bannerId
	 * @return
	 * @throws Exception
	 */
	public HotWord findById(Long hotWordId)throws Exception;
}
