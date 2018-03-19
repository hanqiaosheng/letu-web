package org.controller.weixin;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.component.AppConfig;
import org.service.weixin.read.CoreServiceRead;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.util.weixin.SignUtil;

/***
 * 微信接入管理
 * @author Administrator
 *
 */

@Controller
@Scope("prototype")
@RequestMapping("core")
public class CoreController {
	
	@Resource
	AppConfig AppConfig;
	
	@RequestMapping(method = {RequestMethod.GET})
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws Exception{
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();
		// 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce,AppConfig.getApp_token())) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}
	
	@RequestMapping(method = {RequestMethod.POST})
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws Exception{
		// 调用核心业务类接收消息、处理消息
		String respXml = CoreServiceRead.processRequest(request);
		response.setContentType("text/html;charset=utf-8");
		// 响应消息
		PrintWriter out = response.getWriter();
		out.print(respXml);
		out.close();
	}
	
}
