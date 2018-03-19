package org.listener;



import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 初始化监听器
 * 
 * @author haobo
 *
 */

public class InitListener implements ApplicationListener<ContextRefreshedEvent> {


	public void onApplicationEvent(ContextRefreshedEvent ev) {
		//ListTranscoder<DataDet> listTranscoder = new ListTranscoder<>();
		// TODO Auto-generated method stub
		if (ev.getApplicationContext().getParent() == null) {
			/*try {
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
			}*/
		}
	}

}
