package org.service.weixin.impl.write;

import javax.annotation.Resource;

import org.dao.FixedReturnMapper;
import org.entity.dto.FixedReturn;
import org.service.weixin.write.FixedReturnWxServiceWrite;
import org.springframework.stereotype.Service;

@Service("fixedReturnWxServiceWrite")
public class FixedReturnWxServiceWriteImpl implements FixedReturnWxServiceWrite {
	@Resource
	FixedReturnMapper fixedReturnMapper;

	@Override
	public void update(FixedReturn fixedReturn,Integer flag) throws Exception {
		// TODO Auto-generated method stub
		if(null!=flag){
			if(flag==1){//开锁
				if(fixedReturn.getFixedReturnBikeNum()>0){
					int num = fixedReturn.getFixedReturnBikeNum()-1;
					fixedReturn.setFixedReturnBikeNum(num);
				}
			}else if(flag==2){//关锁
				int num = fixedReturn.getFixedReturnBikeNum()+1;
				fixedReturn.setFixedReturnBikeNum(num);
			}
		}
		
		
		fixedReturnMapper.updateByPrimaryKeySelective(fixedReturn);
	}

}
