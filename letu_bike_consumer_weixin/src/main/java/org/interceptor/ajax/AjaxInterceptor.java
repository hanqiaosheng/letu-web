package org.interceptor.ajax;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.component.AppConfig;
import org.entity.dto.User;
import org.service.weixin.read.DefriendWxServiceRead;
import org.service.weixin.read.SysParamentServiceRead;
import org.service.weixin.read.UserServiceWeixinRead;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 基础数据拦截器拦截器
 * @author Administrator
 *
 */
public class AjaxInterceptor  implements HandlerInterceptor{
	
	@Resource
	AppConfig AppConfig;
	@Resource
	UserServiceWeixinRead userServiceWeixinRead;
	@Resource
	DefriendWxServiceRead defriendWxServiceRead;
	@Resource
	SysParamentServiceRead sysParamentServiceRead;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user_session = (User)session.getAttribute("curruser");
		String request_url = request.getRequestURI();
		if(request_url.indexOf("/msgList")>0||request_url.indexOf("/msgDetail")>0||request_url.indexOf("/userGuide")>0||request_url.indexOf("/userGuideContent")>0||request_url.indexOf("/regist")>0||request_url.indexOf("/msgCode")>0||request_url.indexOf("/sysMsg")>0||request_url.indexOf("already/")>0||request_url.indexOf("getDerfriendReason")>0||request_url.indexOf("/login")>0||request_url.indexOf("/loginCode")>0||
				request_url.indexOf("/getLoginCode")>0||request_url.indexOf("/recoveryPage")>0||request_url.indexOf("/findFixeds")>0||request_url.indexOf("/fixedReturnInfos")>0){
			return true;
		}
		if (request.getHeader("x-requested-with")!= null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){//如果是ajax请求响应头会有x-requested-with 
			if(null==user_session||null==user_session.getUserId()){
					ServletOutputStream out = response.getOutputStream();
					out.print("unlogin");//返回给前端页面的未登陆标识
					out.flush();
					out.close();
					return false;
			}else{
				User user = userServiceWeixinRead.findByUId(user_session.getUserId());
				if(0==user.getUserIsblacklist()){
					ServletOutputStream out = response.getOutputStream();
					out.print("isblack");//返回给前端页面的拉黑标识
					out.flush();
					out.close();
					return false;
				}
				
				/*if(2==user.getUserState()){
					ServletOutputStream out = response.getOutputStream();
					out.print("isdelete");//返回给前端页面的删除标识
					out.flush();
					out.close();
					return false;
				}*/
				
				//session.setAttribute("curruser", user);
				return true;
			}
					}else{			//不是ajax
                        return true;
                       }
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	public String getOAuthUrl(String url) throws UnsupportedEncodingException{
		StringBuffer sb = new StringBuffer("https://open.weixin.qq.com/connect/oauth2/authorize");
		sb.append("?appid=");
		sb.append(AppConfig.getApp_id());
		sb.append("&redirect_uri=");
		sb.append(URLEncoder.encode(url,"UTF-8"));
		sb.append("&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		return sb.toString();
	}
}
