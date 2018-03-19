package org.controller.weixin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.component.AppConfig;
import org.entity.dto.SysMsg;
import org.service.weixin.read.SysMsgWxServiceRead;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 系统公告
 * @author Administrator
 *
 */
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.MessageUtil;
@Controller
@Scope("prototype")
@RequestMapping(value = "/sysMsg", method = RequestMethod.POST)
public class SysMsgWxController {

	
	@Resource
	SysMsgWxServiceRead sysMsgWxServiceRead;
	@Resource
	AppConfig AppConfig;
	
	protected final MessageUtil messageUtil = new MessageUtil();
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(SysMsgWxController.class);
		
	/**
	 * 资讯列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/msgList", method = RequestMethod.POST)
	public 	@ResponseBody MessageUtil msgList(@RequestParam(defaultValue = "1")Integer pageIndex) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		//查询所有的资讯
		List<SysMsg> sysMsgList = sysMsgWxServiceRead.findAllSysMsg(null,AppConfig.getPage_size_weixin());
		Integer totalCount = sysMsgWxServiceRead.findAllSysMsgCount();
		data.put("pageSize", AppConfig.getPage_size_weixin());
		data.put("sysMsgList", sysMsgList);
		data.put("listSize", sysMsgList.size());
		data.put("totalCount", totalCount);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	
	/**
	 * app资讯列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/msgListApp", method = RequestMethod.POST)
	public 	@ResponseBody MessageUtil msgListApp(@RequestParam(defaultValue = "1")Integer pageIndex) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		//查询所有的资讯
		List<SysMsg> sysMsgList = sysMsgWxServiceRead.findAllSysMsg(pageIndex,AppConfig.getPage_size_weixin());
		Integer totalCount = sysMsgWxServiceRead.findAllSysMsgCount();
		data.put("pageSize", AppConfig.getPage_size_weixin());
		data.put("sysMsgList", sysMsgList);
		data.put("totalCount", totalCount);
		data.put("pageIndex", pageIndex);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	
	/**
	 * 资讯详情
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/msgDetail", method = RequestMethod.POST)
	public @ResponseBody MessageUtil msgDetail(Long sysMsgId) throws Exception{
		Map<String, Object> data = new HashMap<String,Object>();
		SysMsg sysMsg = sysMsgWxServiceRead.findBySId(sysMsgId);
		data.put("sysMsg", sysMsg);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	
	
	
}
