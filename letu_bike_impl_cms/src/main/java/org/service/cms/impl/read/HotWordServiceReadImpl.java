package org.service.cms.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.HotWordMapper;
import org.entity.dto.HotWord;
import org.entity.dto.HotWordExample;
import org.service.cms.read.HotWordServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;

@Service("hotWordServiceRead")
public class HotWordServiceReadImpl implements HotWordServiceRead {
	@Resource
	HotWordMapper hotWordMapper;

	@Override
	public List<HotWord> findByCondition(Integer pageIndex, Integer pageSize,String wordName) throws Exception {
		// TODO Auto-generated method stub
		HotWordExample example = new HotWordExample();
		HotWordExample.Criteria criteria = example.createCriteria();
        if(null!=wordName&&!wordName.equals("")){
        	criteria.andHotWordNameLike("%"+wordName+"%");
		}
        criteria.andHotWordStateNotEqualTo(-1);
        example.setLimitStart(PageUtil.getStart(pageIndex));
        example.setLimitEnd(pageSize);
        example.setOrderByClause("hot_word_top_num desc");
		return hotWordMapper.selectByExample(example);
	}

	@Override
	public Integer countByCondition(String wordName) throws Exception {
		// TODO Auto-generated method stub
		HotWordExample example = new HotWordExample();
		HotWordExample.Criteria criteria = example.createCriteria();
        if(null!=wordName&&!wordName.equals("")){
        	criteria.andHotWordNameLike("%"+wordName+"%");
		}
        criteria.andHotWordStateNotEqualTo(-1);
   
		return hotWordMapper.countByExample(example);
	}

	@Override
	public HotWord findById(Long hotWordId) throws Exception {
		// TODO Auto-generated method stub
		return hotWordMapper.selectByPrimaryKey(hotWordId);
	}
}
