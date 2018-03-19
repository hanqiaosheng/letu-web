package org.service.cms.write;

import org.entity.dto.Block;

public interface BlockServiceWrite {
	/**
	 * 添加方格
	 * @param block
	 */
	public Long addBlock(Block block) throws Exception;

}
