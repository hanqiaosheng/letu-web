package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.FixedReturnMapper;
import org.entity.dto.FixedReturn;
import org.service.cms.write.FixedReturnServiceWrite;
import org.springframework.stereotype.Service;


@Service("fixedReturnServiceWrite")
public class FixedReturnServiceWriteImpl implements FixedReturnServiceWrite {
	
	@Resource
	FixedReturnMapper fixedReturnMapper;

	@Override
	public Long addFixedReturn(FixedReturn fixedReturn) throws Exception {
		// TODO Auto-generated method stub
		fixedReturnMapper.insertSelective(fixedReturn);
		return fixedReturn.getFixedReturnId();
	}

	@Override
	public void editFixedReturn(FixedReturn fixedReturn) throws Exception {
		// TODO Auto-generated method stub
		fixedReturnMapper.updateByPrimaryKeySelective(fixedReturn);
	}


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
