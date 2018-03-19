package org.entity.weixin.message.resp;

/**
 * 语音消息
 * 
 * @author haobo
 * @date 2014-09-11
 */
public class VoiceMessage extends BaseMessage {
	// 语音
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
}
