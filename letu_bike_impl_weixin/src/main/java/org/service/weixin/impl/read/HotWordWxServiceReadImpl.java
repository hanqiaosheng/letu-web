package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.HotWordMapper;
import org.entity.dto.HotWord;
import org.entity.dto.HotWordExample;
import org.service.weixin.read.HotWordWxServiceRead;
import org.springframework.stereotype.Service;

@Service("hotWordWxServiceRead")
public class HotWordWxServiceReadImpl implements HotWordWxServiceRead{

	@Resource
	HotWordMapper hotWordMapper;

	@Override
	public List<HotWord> findAllHotWord() throws Exception {
		HotWordExample example = new HotWordExample();
		HotWordExample.Criteria criteria = example.createCriteria();
		criteria.andHotWordStateEqualTo(2);
		example.setOrderByClause("hot_word_top_num desc");
		return hotWordMapper.selectByExample(example);
	}

	
}
