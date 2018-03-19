package org.listener;


import javax.annotation.Resource;

import org.component.AppConfig;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 初始化监听器
 * 
 * @author haobo
 *
 */

public class InitListener implements ApplicationListener<ContextRefreshedEvent> {
	@Resource
	AppConfig AppConfig;
	
//	@Resource
//	DataServiceRead dataServiceRead;
	public void onApplicationEvent(ContextRefreshedEvent ev) {
		// TODO Auto-generated method stub
		if (ev.getApplicationContext().getParent() == null) {
			try {
//				dataServiceRead.findData();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
		}
	}

}
