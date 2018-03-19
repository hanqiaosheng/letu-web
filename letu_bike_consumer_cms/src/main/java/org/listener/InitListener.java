package org.listener;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.component.AppConfig;
import org.entity.dto.DataDet;
import org.service.cms.read.DataServiceRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.util.redis.ListTranscoder;
import org.util.redis.RedisService;

/**
 * 初始化监听器
 * 
 * @author haobo
 *
 */

public class InitListener implements ApplicationListener<ContextRefreshedEvent> {
	@Resource
	AppConfig AppConfig;

	@Resource
	DataServiceRead dataServiceRead;

	@Autowired
	RedisService redisService;

	public void onApplicationEvent(ContextRefreshedEvent ev) {
		ListTranscoder<DataDet> listTranscoder = new ListTranscoder<>();
		// TODO Auto-generated method stub
		if (ev.getApplicationContext().getParent() == null) {
			try {
				Map<String, List<DataDet>> map = dataServiceRead.findDataDet();
				for (Map.Entry<String, List<DataDet>> entry : map.entrySet()) {
					redisService.set(entry.getKey().getBytes(), listTranscoder.serialize(entry.getValue()));
					for(DataDet d:entry.getValue()){
						redisService.set("det"+d.getDataDetId(), d.getDataDetVal());
					}
				}
				redisService.closeJedis();
			} catch (Exception e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
			}
		}
	}

}
