package org.service.weixin.write;

import org.entity.dto.FixedReturn;

public interface FixedReturnWxServiceWrite {
    public void update(FixedReturn fixedReturn,Integer flag) throws Exception;
}
