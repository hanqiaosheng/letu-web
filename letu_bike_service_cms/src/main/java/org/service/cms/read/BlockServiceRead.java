package org.service.cms.read;

import java.util.List;

public interface BlockServiceRead {
	/**
	 * 根据区域方格code查找方格id
	 * @param blockCode
	 * @return
	 * @throws Exception
	 */
	public Long findBlockId(String blockCode) throws Exception;
	
	/**
	 * 根据区域方格code查找方格id（多个）
	 * @param blockCode
	 * @return
	 * @throws Exception
	 */
	public List<Long> findBlockIds(String blockCode) throws Exception;
	/**
	 * 根据多个区域方格code查找方格id
	 * @param blockCodes
	 * @return
	 * @throws Exception
	 */
	public List<Long> findBlockIdsByCodes(List<String> blockCodes) throws Exception;
}
