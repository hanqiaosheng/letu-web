package org.entity.weixin.message.resp;

/**
 * 文本消息
 * 
 * @author haobo
 * @date 2014-09-11
 */
public class TextMessage extends BaseMessage {
	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
