package org.service.task;

/**
 * 
 * 定时发送信息
 * @author haobo
 *
 */
public interface InformService {
	
	/**
	 * 课程审核通过后和开课前2天，讲师收到爱蹭课平台的开课通知(短信,微信)
	 */
	public void classStartInform();
	
	/**
	 * 报名截止日前1天，如果报名数量未达最低数，可以和爱蹭课运营总部沟通后确定“解散课程”或“开课”，
	 * 由运营总部在爱蹭课管理平台进行操作（短信通知发起人）
	 */
	public void dismissClassInform();
	
	/**
	 * 报名截止日前7天开始，每天微信通知发起人报名人数
	 */
	public void registrationNumberInform();
	
	/**
	 * 代言人到期提醒（30天）
	 */
	public void spokesmanInform();
	
	/**
	 * 课程结束通知
	 */
	public void classendInform();
	
	/**
	 * 课程状态定时更新
	 */
	public void classStateInform();
	
	/**
	 * 改变代言人到期状态
	 */
	public void ChangeSpokenManState();
}
