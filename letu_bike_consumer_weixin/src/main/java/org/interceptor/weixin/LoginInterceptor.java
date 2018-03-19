package org.interceptor.weixin;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.component.AppConfig;
import org.entity.dto.User;
import org.entity.weixin.pojo.WeixinOauth2Token;
import org.service.weixin.read.UserServiceWeixinRead;
import org.service.weixin.write.UserServiceWeixinWrite;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
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
		//String url = AppConfig.getBase_path_weixin()+"html/regist.html"; //跳转到注册页面的url
		//String realnameurl = AppConfig.getBase_path_weixin()+"html/realName.html"; //跳转到实名认证页面的url
		String request_url = request.getRequestURI();
		User user = (User)session.getAttribute("curruser");
		if(request_url.indexOf("/login")<0){
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
					/*String accessToken = wot.getAccessToken();
					// 获取用户基本信息
					SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);*/
					User dbUser = userServiceWeixinRead.findUserByOpenId(openId);
					if(dbUser != null){
						/*dbUser.setUserGender(1);
						dbUser.setUserOpenid(openId);
						String nickName = "昵称";
						dbUser.setUserNickname(nickName);
						dbUser.setUserProfileImage("");
						User iUser = userServiceWeixinWrite.updateUser(dbUser);*/
						session.setAttribute("curruser", dbUser);
					}else{
						
						User iUser = new User();
						iUser.setUserGender(1);
						iUser.setUserOpenid(openId);
//						String nickName = StringUtil.filterEmoji(snsUserInfo.getNickName());
//						snsUserInfo.setNickName(nickName);
						iUser.setUserNickname("昵称");
//						iUser.setUserOpenid(snsUserInfo.getOpenId());
//						iUser.setUserProfileImage(snsUserInfo.getHeadimgurl());
//						iUser.setUserCreatetime(DateUtil.getNowTime());
//						User user2 = userServiceWeixinWrite.regiest(snsUserInfo);
						session.setAttribute("curruser", iUser);
					}
				}
			}else{
				if(request_url.indexOf("index/")<0){
					/*User newuser = userServiceWeixinRead.findByUId(user.getUserId());
					if(null==newuser){
						response.sendRedirect(url);
					}
					if(null==newuser.getUserTel()){
						response.sendRedirect(url);
					}
					if(null==newuser.getUserIdcard()){
						response.sendRedirect(realnameurl);
					}
				*/
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
		sb.append("&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
		return sb.toString();
	}

}
