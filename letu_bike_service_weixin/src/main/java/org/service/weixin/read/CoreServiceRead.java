package org.service.weixin.read;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.entity.weixin.message.resp.Article;
import org.entity.weixin.message.resp.NewsMessage;
import org.entity.weixin.message.resp.TextMessage;
import org.util.weixin.MessageUtil;

/**
 * 核心服务类
 * 
 * @author haobo
 * @date 2014-10-17
 */
public class CoreServiceRead {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml格式的消息数据0
		String respXml = null;
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			// 事件推送
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					textMessage.setContent("总算等到你！\n这一次，我是你的专属骑行伴侣！\n手机注册并完成认证后，点击左下方的【点我用车】，就可以开启你的乐途之行啦！");
					// 将消息对象转换成xml
					respXml = MessageUtil.messageToXml(textMessage);
				}
				//定位
				/*if (eventType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
					textMessage.setContent("您好，欢迎关注乐途,点击“扫码租车”开始骑行吧！");
					// 将消息对象转换成xml
					respXml = MessageUtil.messageToXml(textMessage);
				}*/
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 暂不做处理
				}
			// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建菜单时的key值对应
					String eventKey = requestMap.get("EventKey");
					// 根据key值判断用户点击的按钮
					if (eventKey.equals("a")) {
						Article article = new Article();
						article.setTitle("开源中国");
						article.setDescription("开源中国社区成立于2008年8月，是目前中国最大的开源技术社区。\n\n开源中国的目的是为中国的IT技术人员提供一个全面的、快捷更新的用来检索开源软件以及交流开源经验的平台。\n\n经过不断的改进,目前开源中国社区已经形成了由开源软件库、代码分享、资讯、讨论区和博客等几大频道内容。");
						article.setPicUrl("");
						article.setUrl("http://m.oschina.net");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						// 创建图文消息
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respXml = MessageUtil.messageToXml(newsMessage);
					} else if (eventKey.equals("contact_us")) {
						textMessage.setContent("[太阳]联系我们[太阳]：\n合作邮箱\n[机智]business@letulife.com\n客服邮箱\n[机智]service@letulife.com\n客服电话\n[机智]0571-56231981\n简历投递\n[机智]hr@letulife.com");
						respXml = MessageUtil.messageToXml(textMessage);
					} else if (eventKey.equals("recommend")) {
						textMessage.setContent("推荐");
						respXml = MessageUtil.messageToXml(textMessage);
					}
				}
			}
			// 当用户发消息时
			else {
				textMessage.setContent("您好！");
				if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
					/*String content = requestMap.get("Content");
					if("入口".equals(content)){
						textMessage.setContent("http://icengke.cn/weixin/course/gotoSkip.action");
					}*/
				}
				respXml = MessageUtil.messageToXml(textMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}
