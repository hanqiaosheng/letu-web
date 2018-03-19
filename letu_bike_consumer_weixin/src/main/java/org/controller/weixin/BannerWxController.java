package org.controller.weixin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.entity.dto.Banner;
import org.service.weixin.read.BannerWxServiceRead;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.MessageUtil;
@Controller
@Scope("prototype")
@RequestMapping(value = "/banner", method = RequestMethod.POST)
public class BannerWxController {
	
	@Resource
	BannerWxServiceRead bannerWxServiceRead;
	protected final MessageUtil messageUtil = new MessageUtil();
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(BannerWxController.class);
		
	/**
	 * banner列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bannerList", method = RequestMethod.POST)
	public 	@ResponseBody MessageUtil bannerList(String cityCode) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		//查询所有banner
		List<Banner> bannerList = bannerWxServiceRead.findAllBanner(cityCode);
	    //查询所有景点
		data.put("bannerList", bannerList);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	
}
