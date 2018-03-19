package org.entity.dto;
/**
 * Alipay
 * 业务请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递
 * @author morning
 *
 */
public class UserTask {
	private String userTaskName;//任务名
	private Integer userTaskSate;//任务进度
	private String userTaskBrief;//任务介绍
	private String userTaskMessage;//任务信息
	private String userTaskImageName;//任务图标名
	public String getUserTaskName() {
		return userTaskName;
	}
	public void setUserTaskName(String userTaskName) {
		this.userTaskName = userTaskName;
	}
	public Integer getUserTaskSate() {
		return userTaskSate;
	}
	public void setUserTaskSate(Integer userTaskSate) {
		this.userTaskSate = userTaskSate;
	}
	public String getUserTaskBrief() {
		return userTaskBrief;
	}
	public void setUserTaskBrief(String userTaskBrief) {
		this.userTaskBrief = userTaskBrief;
	}
	public String getUserTaskMessage() {
		return userTaskMessage;
	}
	public void setUserTaskMessage(String userTaskMessage) {
		this.userTaskMessage = userTaskMessage;
	}
	public String getUserTaskImageName() {
		return userTaskImageName;
	}
	public void setUserTaskImageName(String userTaskImageName) {
		this.userTaskImageName = userTaskImageName;
	}
	
}
