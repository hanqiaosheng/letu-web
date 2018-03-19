package org.service.weixin.impl.write;


import javax.annotation.Resource;

import org.dao.TradeorderMapper;
import org.entity.dto.Tradeorder;
import org.service.weixin.write.TrOrderWxServiceWrite;
import org.springframework.stereotype.Service;

@Service("trOrderWxServiceWrite")
public class TrOrderWxServiceWriteImpl implements TrOrderWxServiceWrite{
	
	@Resource
	TradeorderMapper tradeorderMapper;

	@Override
	public void updateTrOrder(Tradeorder tradeorder)throws Exception {
		// TODO Auto-generated method stub
		tradeorderMapper.updateByPrimaryKeySelective(tradeorder);
	}

	@Override
	public void addTrOrder(Tradeorder tradeorder) throws Exception {
		// TODO Auto-generated method stub
		tradeorderMapper.insertSelective(tradeorder);
	}

	
}
