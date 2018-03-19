package org.entity.weixin.pojo;


/**
 * 微信通用接口凭证
 * 
 * @author james
 * @date 2015-02-28
 */
public class AccessToken {
	// 获取到的凭证
	private String token;
	// 获取到ticket
	private String ticket;
	// 凭证有效时间，单位：秒
	private int expiresIn;
	// 添加时间
	private long addTime;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public long getAddTime() {
		return addTime;
	}

	public void setAddTime(long addTime) {
		this.addTime = addTime;
	}

}