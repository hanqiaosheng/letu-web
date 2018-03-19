package org.interceptor.cms;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.component.AppConfig;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {
	
	@Resource
	AppConfig AppConfig;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
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
