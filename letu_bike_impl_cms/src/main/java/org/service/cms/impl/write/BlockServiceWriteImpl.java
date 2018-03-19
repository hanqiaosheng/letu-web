package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.BlockMapper;
import org.entity.dto.Block;
import org.service.cms.write.BlockServiceWrite;
import org.springframework.stereotype.Service;
@Service("blockServiceWrite")
public class BlockServiceWriteImpl implements BlockServiceWrite {
	@Resource
	BlockMapper blockMapper;

	@Override
	public Long addBlock(Block block) throws Exception {
		// TODO Auto-generated method stub
		blockMapper.insertSelective(block);
		return block.getBlockId();
	}


	

}
