package org.controller.weixin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.component.AppConfig;
import org.entity.dto.HotWord;
import org.service.weixin.read.HotWordWxServiceRead;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.MessageUtil;


@Controller
@Scope("prototype")
@RequestMapping(value = "hotword", method = RequestMethod.POST)
public class HotWordWxController {

	
	@Resource
	HotWordWxServiceRead hotWordWxServiceRead;
	@Resource
	AppConfig AppConfig;
	
	protected final MessageUtil messageUtil = new MessageUtil();
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(HotWordWxController.class);
		
	/**
	 * 热词列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hotWordList", method = RequestMethod.POST)
	public 	@ResponseBody MessageUtil hotWordList() throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		//查询所有的热词
		List<HotWord> hotWordList = hotWordWxServiceRead.findAllHotWord();
		data.put("hotWordList", hotWordList);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	
}
