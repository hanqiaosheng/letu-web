package org.service.weixin.impl.read;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.dao.BlockMapper;
import org.entity.dto.Block;
import org.entity.dto.BlockExample;
import org.service.weixin.read.BlockWxServiceRead;
import org.springframework.stereotype.Service;

@Service("blockWxServiceRead")
public class BlockWxServiceReadImpl implements BlockWxServiceRead {

	@Resource
	BlockMapper blockMapper;

	@Override
	public Long findBlockId(String blockCode) throws Exception {
		// TODO Auto-generated method stub
		BlockExample example = new BlockExample();
		BlockExample.Criteria criteria = example.createCriteria();
		criteria.andBlockCodeEqualTo(blockCode);
		List<Block> list = blockMapper.selectByExample(example);
		if (0 != list.size())
			return list.get(0).getBlockId();
		else
			return null;
	}

	@Override
	public List<Long> findBlockIds(String blockCode) throws Exception {
		// TODO Auto-generated method stub
		BlockExample example = new BlockExample();
		BlockExample.Criteria criteria = example.createCriteria();
		criteria.andBlockCodeLike(blockCode + "%");
		List<Block> list = blockMapper.selectByExample(example);
		List<Long> ids = new ArrayList<Long>();
		for (Block b : list)
			if (null != b)
				ids.add(b.getBlockId());
		if (0 != list.size())
			return ids;
		else
			return null;
	}

	@Override
	public List<Long> findBlockIdsByCodes(List<String> blockCodes) throws Exception {
		// TODO Auto-generated method stub
		BlockExample example = new BlockExample();
		BlockExample.Criteria criteria = example.createCriteria();
		criteria.andBlockCodeIn(blockCodes);
		List<Block> list = blockMapper.selectByExample(example);
		List<Long> ids = new ArrayList<Long>();
		if (null != list && 0 != list.size())
			for (Block b : list)
				ids.add(b.getBlockId());
		if (0 != list.size())
			return ids;
		else
			return null;
	}

}
