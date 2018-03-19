package org.controller.weixin;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.entity.dto.Bike;
import org.service.weixin.read.BikeWxServiceRead;
import org.service.weixin.write.BikeWxServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.MessageUtil;

/**
 * 异常上报
 * @author Administrator
 *
 */

@Controller
@Scope("prototype")
@RequestMapping(value = "/exception", method = RequestMethod.POST)
public class ExceptionReportWxController {
	
	
	@Resource
	BikeWxServiceRead bikeWxServiceRead;
	
	@Resource
	BikeWxServiceWrite bikeWxServiceWrite;
	
	
	protected final MessageUtil messageUtil = new MessageUtil();
	
	/**
	 * 异常上报
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/subException", method = RequestMethod.POST)
	public @ResponseBody MessageUtil subException(HttpSession session,Long bikeId) throws Exception{
		Bike bike = bikeWxServiceRead.findByBikeId(bikeId);
		if(bike.getBikeState()==2){
			bike.setBikeState(0);
			bikeWxServiceWrite.updateBike(bike);
		}
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		return messageUtil;
	}

}
