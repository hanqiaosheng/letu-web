package org.interceptor.weixin;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.component.AppConfig;
import org.entity.dto.User;
import org.entity.weixin.pojo.SNSUserInfo;
import org.entity.weixin.pojo.WeixinOauth2Token;
import org.service.weixin.read.UserServiceWeixinRead;
import org.service.weixin.write.UserServiceWeixinWrite;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.util.StringUtil;
import org.util.weixin.AdvancedUtil;

public class LoginInterceptor implements HandlerInterceptor {
	
	@Resource
	AppConfig AppConfig;
	@Resource
	UserServiceWeixinRead userServiceWeixinRead;
	@Resource
	UserServiceWeixinWrite userServiceWeixinWrite;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String code = request.getParameter("code");
		HttpSession session = request.getSession();
		//判断进入的链接
		String url = AppConfig.getBase_path_admin()+"index/goRegist.action"; //跳转到登陆页面的url
		String request_url = request.getRequestURI();
		User user = (User)session.getAttribute("curruser");
		if(user == null){
			if(code == null){
				/*AppConfig.getApp_domain()*/
				String redirectUrl = getOAuthUrl(request.getRequestURL().toString()+"?"+request.getQueryString());
				response.sendRedirect(redirectUrl);
				return false;	
			}else{
				// 用code换取access_token（同时会得到OpenID）
				WeixinOauth2Token wot = AdvancedUtil.getOAuth2AceessToken(AppConfig.getApp_id(), AppConfig.getApp_secret(), code);
				String openId = wot.getOpenId();
				String accessToken = wot.getAccessToken();
				// 获取用户基本信息
				SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
				User dbUser = userServiceWeixinRead.findUserByOpenId(openId);
				if(dbUser != null){
					dbUser.setUserGender(snsUserInfo.getSex());
					dbUser.setUserOpenid(snsUserInfo.getOpenId());
					String nickName = StringUtil.filterEmoji(snsUserInfo.getNickName());
					dbUser.setUserNickname(nickName);
					dbUser.setUserProfileImage(snsUserInfo.getHeadimgurl());
					User iUser = userServiceWeixinWrite.updateUser(dbUser);
					session.setAttribute("curruser", iUser);
				}else{
					
					User iUser = new User();
					iUser.setUserGender(snsUserInfo.getSex());
					String nickName = StringUtil.filterEmoji(snsUserInfo.getNickName());
					snsUserInfo.setNickName(nickName);
//					iUser.setUserNickname(nickName);
//					iUser.setUserOpenid(snsUserInfo.getOpenId());
//					iUser.setUserProfileImage(snsUserInfo.getHeadimgurl());
//					iUser.setUserCreatetime(DateUtil.getNowTime());
					User user2 = userServiceWeixinWrite.regiest(snsUserInfo);
					session.setAttribute("curruser", user2);
				}
			}
		}else{
			if(request_url.indexOf("index/")<0){
			if(null==user.getUserTel()){
				response.sendRedirect(url);
			}
			}
		}
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
