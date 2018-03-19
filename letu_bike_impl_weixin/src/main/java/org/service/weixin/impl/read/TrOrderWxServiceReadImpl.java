package org.service.weixin.impl.read;



import java.util.List;

import javax.annotation.Resource;

import org.dao.TradeorderMapper;
import org.entity.dto.Tradeorder;
import org.entity.dto.TradeorderExample;
import org.service.weixin.read.TrOrderWxServiceRead;
import org.springframework.stereotype.Service;

@Service("trOrderWxServiceRead")
public class TrOrderWxServiceReadImpl implements TrOrderWxServiceRead{
	
	@Resource
	TradeorderMapper tradeorderMapper;

	@Override
	public Tradeorder findByTransactionNumber(String transactionNumber) throws Exception {
		// TODO Auto-generated method stub
		TradeorderExample example = new TradeorderExample();
		TradeorderExample.Criteria criteria = example.createCriteria();
		criteria.andTrOrderStateEqualTo((short)0);
		criteria.andTrOrderTransactionNumberEqualTo(transactionNumber);
		List<Tradeorder> tradeorders = tradeorderMapper.selectByExample(example);
		if(tradeorders.size()>0){
			return tradeorders.get(0);
		}
		return null;
	}
	
}
