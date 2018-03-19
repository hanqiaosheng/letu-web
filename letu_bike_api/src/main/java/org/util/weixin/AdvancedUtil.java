package org.util.weixin;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.entity.weixin.message.resp.Article;
import org.entity.weixin.message.resp.Music;
import org.entity.weixin.pojo.SNSUserInfo;
import org.entity.weixin.pojo.Template;
import org.entity.weixin.pojo.WeixinGroup;
import org.entity.weixin.pojo.WeixinOauth2Token;
import org.entity.weixin.pojo.WeixinUser;

/**
 * 高级接口的调用
 * 
 * @author haobo
 * @date 2015-11-14
 */
public class AdvancedUtil {

	/**
	 * 组装文本客服消息
	 * 
	 * @param openId 消息发送对象
	 * @param content 文本消息内容
	 * @return
	 */
	public static String makeTextCustomMessage(String openId, String content) {
		// 对消息内容中的双引号进行转义
		content = content.replace("\"", "\\\"");
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		return String.format(jsonMsg, openId, content);
	}

	/**
	 * 组装图片客服消息
	 * 
	 * @param openId 消息发送对象
	 * @param mediaId 媒体文件id
	 * @return
	 */
	public static String makeImageCustomMessage(String openId, String mediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}

	/**
	 * 组装语音客服消息
	 * 
	 * @param openId 消息发送对象
	 * @param mediaId 媒体文件id
	 * @return
	 */
	public static String makeVoiceCustomMessage(String openId, String mediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}

	/**
	 * 组装视频客服消息
	 * 
	 * @param openId 消息发送对象
	 * @param mediaId 媒体文件id
	 * @param thumbMediaId 视频消息缩略图的媒体id
	 * @return
	 */
	public static String makeVideoCustomMessage(String openId, String mediaId, String thumbMediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"%s\",\"thumb_media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId, thumbMediaId);
	}

	/**
	 * 组装音乐客服消息
	 * 
	 * @param openId 消息发送对象
	 * @param music 音乐对象
	 * @return
	 */
	public static String makeMusicCustomMessage(String openId, Music music) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"music\",\"music\":%s}";
		jsonMsg = String.format(jsonMsg, openId, JSONObject.fromObject(music).toString());
		// 参数名称替换 @20140125
		jsonMsg = jsonMsg.replace("musicUrl", "musicurl");
		jsonMsg = jsonMsg.replace("HQMusicUrl", "hqmusicurl");
		jsonMsg = jsonMsg.replace("thumbMediaId", "thumb_media_id");
		return jsonMsg;
	}

	/**
	 * 组装图文客服消息
	 * 
	 * @param openId 消息发送对象
	 * @param articleList 图文消息列表
	 * @return
	 */
	public static String makeNewsCustomMessage(String openId, List<Article> articleList) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"news\",\"news\":{\"articles\":%s}}";
		jsonMsg = String.format(jsonMsg, openId, JSONArray.fromObject(articleList).toString().replaceAll("\"", "\\\""));
		// 将jsonMsg中的picUrl替换为picurl
		jsonMsg = jsonMsg.replace("picUrl", "picurl");
		return jsonMsg;
	}

	/**
	 * 发送客服消息
	 * 
	 * @param accessToken 接口访问凭证
	 * @param jsonMsg json格式的客服消息
	 * @return
	 */
	public static boolean sendCustomMessage(String accessToken, String jsonMsg) {
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 发送客服消息
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", jsonMsg);
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
			} else {
				System.out.println("客服消息发送失败 errcode:" + errorCode);
			}
		}
		return result;
	}

	/**
	 * 发送模板消息
	 * 
	 * @param accessToken 接口访问凭证
	 * @param template 模板消息对象
	 * @return
	 */
	public static boolean sendTemplateMessage(String accessToken, Template template) {
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 发送模板消息
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", template.toJSON());
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
			} else {
				System.out.println("模板消息发送失败 errcode:" + errorCode);
			}
		}
		return result;
	}

	/**
	 * 创建用户分组
	 * 
	 * @param accessToken 接口访问凭证
	 * @param groupName 用户组名
	 * @return
	 */
	public static WeixinGroup createGroup(String accessToken, String groupName) {
		WeixinGroup weixinGroup = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		String reqJSON = String.format("{\"group\":{\"name\":\"%s\"}}", groupName);
		// 创建用户组
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", reqJSON);
		if (null != jsonObject) {
			JSONObject groupObject = jsonObject.getJSONObject("group");
			int id = groupObject.getInt("id");
			String name = groupObject.getString("name");

			weixinGroup = new WeixinGroup();
			weixinGroup.setId(id);
			weixinGroup.setName(name);
		}
		return weixinGroup;
	}

	/**
	 * 查询所有分组
	 * 
	 * @param accessToken
	 * @return List<WeixinGroup>
	 */
	public static List<WeixinGroup> getGroups(String accessToken) {
		List<WeixinGroup> weixinGroupList = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 查询所有分组
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			weixinGroupList = JSONArray.toList(jsonObject.getJSONArray("groups"), WeixinGroup.class);
		}
		return weixinGroupList;
	}

	/**
	 * 查询用户所在分组
	 * 
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public static int getMemberGroup(String accessToken, String openId) {
		int groupId = -1;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		String reqJSON = String.format("{\"openid\":\"%s\"}", openId);
		// 查询所有分组
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", reqJSON);
		if (null != jsonObject) {
			groupId = jsonObject.getInt("groupid");
		}
		return groupId;
	}

	/**
	 * 修改分组名称
	 * 
	 * @param accessToken
	 * @param groupId 用户组ID
	 * @param newGroupName 新的用户组名称
	 * @return
	 */
	public static boolean updateGroupName(String accessToken, int groupId, String newGroupName) {
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		String reqJSON = String.format("{\"group\":{\"id\":%d,\"name\":\"%s\"}}", groupId, newGroupName);
		// 查询所有分组
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", reqJSON);
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
			} else {
				System.out.println("修改分组名称失败 errcode:" + errorCode);
			}
		}
		return result;
	}

	/**
	 * 移动用户分组
	 * 
	 * @param accessToken
	 * @param openId 用户的OPENID
	 * @param groupId 新的用户组ID
	 * @return
	 */
	public static boolean updateMemberGroup(String accessToken, String openId, int groupId) {
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		String reqJSON = String.format("{\"openid\":\"%s\",\"to_groupid\":%d}", openId, groupId);
		// 查询所有分组
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", reqJSON);
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
			} else {
				System.out.println("移动用户分组失败 errcode:" + errorCode);
			}
		}
		return result;
	}

	/**
	 * 删除分组
	 * 
	 * @param accessToken
	 * @param groupId 用户组ID
	 * @return
	 */
	public static boolean deleteGroup(String accessToken, int groupId) {
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		String reqJSON = String.format("{\"group\":{\"id\":%d}}", groupId);
		// 查询所有分组
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", reqJSON);
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
			} else {
				System.out.println("删除分组失败 errcode:" + errorCode);
			}
		}
		return result;
	}

	/**
	 * 获取用户基本信息
	 * @param accessToken 接口访问凭证
	 * @param openId 用户的OPENID
	 * @return
	 */
	public static WeixinUser getUserInfo(String accessToken, String openId) {
		WeixinUser weixinUser = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		requestUrl = requestUrl.replace("OPENID", openId);
		// 获取用户基本信息
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				weixinUser = new WeixinUser();
				// 关注状态
				weixinUser.setSubscribe(jsonObject.getInt("subscribe"));
				weixinUser.setOpenId(jsonObject.getString("openid"));
				weixinUser.setNickname(jsonObject.getString("nickname"));
				weixinUser.setSex(jsonObject.getInt("sex"));
				weixinUser.setLanguage(jsonObject.getString("language"));
				weixinUser.setCity(jsonObject.getString("city"));
				weixinUser.setProvince(jsonObject.getString("province"));
				weixinUser.setCountry(jsonObject.getString("country"));
				weixinUser.setHeadimgurl(jsonObject.getString("headimgurl"));
				weixinUser.setSubscribeTime(jsonObject.getInt("subscribe_time"));
				// weixinUser.setUnionid(jsonObject.getString("unionid"));
				weixinUser.setRemark(jsonObject.getString("remark"));
				weixinUser.setGroupId(jsonObject.getInt("groupid"));
			} catch (Exception e) {
				if(0 == weixinUser.getSubscribe()) {
					System.out.println("用户 " + weixinUser.getOpenId() + " 已经取消关注公众号！");
				}
				else {
					int errorCode = jsonObject.getInt("errcode");
					String errorMsg = jsonObject.getString("errmsg");
					System.out.println("获取用户基本信息失败 errcode:" + errorCode);
				}
			}
		}
		return weixinUser;
	}
	
	/**
	 * 通过code换取access_token
	 * @param appID
	 * @param appSecret
	 * @param code 授权码
	 * @return
	 */
	public static WeixinOauth2Token getOAuth2AceessToken(String appID, String appSecret, String code) {
		WeixinOauth2Token wot = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appID);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		// 接口调用
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wot = new WeixinOauth2Token();
				wot.setAccessToken(jsonObject.getString("access_token"));
				wot.setExpiresIn(jsonObject.getInt("expires_in"));
				wot.setRefreshToken(jsonObject.getString("refresh_token"));
				wot.setOpenId(jsonObject.getString("openid"));
				wot.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wot = null;
				
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("通过code换取access_token失败 errcode:" + errorCode);
			}
		}
		return wot;
	}
	
	/**
	 * 网页授权获取用户信息
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
		SNSUserInfo snsUserInfo = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		requestUrl = requestUrl.replace("OPENID", openId);
		// 接口调用
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				snsUserInfo = new SNSUserInfo();
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				snsUserInfo.setCity(jsonObject.getString("city"));
				snsUserInfo.setCountry(jsonObject.getString("country"));
				snsUserInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
				snsUserInfo.setNickName(jsonObject.getString("nickname"));
				snsUserInfo.setPrivilege(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
				snsUserInfo.setProvince(jsonObject.getString("province"));
				snsUserInfo.setSex(jsonObject.getInt("sex"));
			} catch (Exception e) {
				snsUserInfo = null;
				
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("网页授权获取用户信息失败 errcode:" + errorCode);
			}
		}
		return snsUserInfo;
	}
	
	
	/**
	 * 小程序通过code换取access_token
	 * @param appID
	 * @param appSecret
	 * @param code 授权码
	 * @return
	 */
	public static WeixinOauth2Token getSmallToken(String appID, String appSecret, String code) {
		WeixinOauth2Token wot = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appID);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("JSCODE", code);
		// 接口调用
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wot = new WeixinOauth2Token();
				wot.setOpenId(jsonObject.getString("openid"));
				wot.setSessionKey(jsonObject.getString("session_key"));
			} catch (Exception e) {
				wot = null;
				
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("通过code换取session_key失败 errcode:" + errorCode);
			}
		}
		return wot;
	}

	public static void main(String[] args) {
		// 获取access_token
		String accessToekn = CommonUtil.getToken("wx0057906b9b02c814", "bc39d8c8f72aef4394a4ab54729b0e95").getAccess_token();

		// String json = makeTextCustomMessage("oTnKKuHeARMSuDIv7iFr43WyWNKY", "点击查看<a href=\"http://m.blog.csdn.net/blog/lyq8479\">柳峰的博客</a>");
		// sendCustomMessage(accessToekn, json);

		// List<TemplateParam> templateParamList = new ArrayList<TemplateParam>();
		// templateParamList.add(new TemplateParam("first", "亲爱的顾客，您好！现为您安排的K房如下", "#173177"));
		// templateParamList.add(new TemplateParam("keyword1", "2015年09月09日 10:20", "#173177"));
		// templateParamList.add(new TemplateParam("keyword2", "天河店", "#173177"));
		// templateParamList.add(new TemplateParam("keyword3", "018房", "#173177"));
		// templateParamList.add(new TemplateParam("keyword4", "自由人", "#173177"));
		// templateParamList.add(new TemplateParam("keyword5", "42元/位", "#173177"));
		// templateParamList.add(new TemplateParam("remark", "祝您欢唱愉快！", "#173177"));
		//
		// Template template = new Template();
		// template.setTemplateId("1uzEiP79vQJEwijdfXcdDwwsXBp4l8OB3pgSQx12lFM");
		// template.setToUser("oAuZ6s_lfFc-OqbrWkZzZsJp6R6M");
		// template.setTopColor("#173177");
		// template.setUrl("");
		// template.setTemplateParamList(templateParamList);

		// {"group":{"id":101,"name":"测试组"}}
		// createGroup(accessToekn, "测试组");

		// List<WeixinGroup> groupList = getGroups(accessToekn);
		// for(WeixinGroup group : groupList) {
		// System.out.println(group.getId() + " - " + group.getName() +"  - " + group.getCount());
		// }

		// int groupId = getMemberGroup(accessToekn, "oAuZ6s_lfFc-OqbrWkZzZsJp6R6M");
		// System.out.println(groupId);

		// updateGroupName(accessToekn, 101, "管理员");

		// updateMemberGroup(accessToekn, "oAuZ6s_lfFc-OqbrWkZzZsJp6R6M", 101);

		// deleteGroup(accessToekn, 101);
		
		WeixinUser weixinUser = getUserInfo(accessToekn, "oAuZ6s_lfFc-OqbrWkZzZsJp6R6M");
		System.out.println("昵称：" + weixinUser.getNickname());
		System.out.println("OPENID：" + weixinUser.getOpenId());
		System.out.println("头像：" + weixinUser.getHeadimgurl());
		System.out.println("城市：" + weixinUser.getCity());
	}

}

