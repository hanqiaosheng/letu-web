package org.entity.weixin.message.req;

/**
 * 文本消息
 * 
 * @author haobo
 * @date 2014-09-11
 */
public class TextMessage extends BaseMessage {
	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
