package org.util;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 操作日志
 * @author Administrator
 *
 */
public class OperateUtil {
	/**
	 * 登录登出
	 * @param adminRealName
	 * @param flag
	 * @param userRealName
	 * @param userStateStr
	 * @return
	 */
	public static String logOutOption(String adminRealName,Integer flag) {
		String remark = "后台管理员【"+adminRealName+"】在【"+DateUtil.format(new Date())+"】进行了";
		switch (flag) {
		case 1:
			remark += "登录";
			break;
		case 2:
			remark += "退出登录";
			break;
		default:
			remark = "";
		    break; 
		}
	   
		return remark;
	}
	/**
	 * 操作用户
	 * @param adminRealName
	 * @param flag
	 * @param userRealName
	 * @param userStateStr
	 * @param money
	 * @param editMoney
	 * @return
	 */
	public static String operateUser(String adminRealName,Integer flag,String userRealName,String userStateStr,BigDecimal money,BigDecimal editMoney) {
		String remark = "后台管理员【"+adminRealName+"】在【"+DateUtil.format(new Date())+"】进行了";
		switch (flag) {
		case 1:
			remark += "修改用户【"+userRealName+"】状态为【"+userStateStr+"】";
			break;
		case 2:
			remark += "拉黑用户【"+userRealName+"】";
			break;
		case 3:
			remark += "批量拉黑用户【"+userRealName+"】";
			break;
		case 4:
			remark += "删除用户【"+userRealName+"】";
			break;
		case 5:
			remark += "批量删除用户【"+userRealName+"】";
			break;
		case 6:
			remark += "修改用户【"+userRealName+"】预付款从【"+money+"】变为【"+editMoney+"】";
			break;
		case 7:
			remark += "恢复黑名单用户【"+userRealName+"】";
			break;
		case 8:
			remark += "批量恢复黑名单用户【"+userRealName+"】";
			break;
		case 9:
			remark += "删除黑名单用户【"+userRealName+"】";
			break;
		case 10:
			remark += "批量删除黑名单用户【"+userRealName+"】 ";
			break;
		default:
			remark = "";
		    break; 
		}
	   
		return remark;
	}
	
	
	/**
	 * 操作用户
	 * @param adminRealName
	 * @param flag
	 * @param userRealName
	 * @param userStateStr
	 * @param money
	 * @param editMoney
	 * @return
	 */
	public static String operateVip(String adminRealName,Integer flag,String userRealName,String channelName) {
		String remark = "后台管理员【"+adminRealName+"】在【"+DateUtil.format(new Date())+"】进行了";
		switch (flag) {
		case 1:
			remark += "修改会员【"+userRealName+"】渠道为【"+channelName+"】";
			break;
		case 2:
			remark += "拉黑会员【"+userRealName+"】";
			break;
		case 3:
			remark += "批量拉黑会员【"+userRealName+"】";
			break;
		case 4:
			remark += "删除会员【"+userRealName+"】";
			break;
		case 5:
			remark += "批量删除会员【"+userRealName+"】";
			break;
		case 6:
			remark += "导入会员";
			break;
		case 7:
			remark += "增加会员【"+userRealName+"】";
			break;
		default:
			remark = "";
		    break; 
		}
	   
		return remark;
	}
	/**
	 * 操作车辆
	 * @param adminRealName
	 * @param flag
	 * @param userRealName
	 * @param userStateStr
	 * @param money
	 * @param editMoney
	 * @return
	 */
	public static String operateBike(String adminRealName,Integer flag,String bikeCode,Long modelsId,Long fixedReturnId) {
		String remark = "后台管理员【"+adminRealName+"】在【"+DateUtil.format(new Date())+"】进行了";
		switch (flag) {
		case 1:
			remark += "添加车辆【车辆编号"+bikeCode+"】";
			break;
		case 2:
			remark += "导入车辆 ";
			break;
		case 3:
			remark += "校准车辆";
			break;
		case 4:
			remark += "修改车辆【车辆编号"+bikeCode+"】";
			break;
		case 5:
			remark += "定位车辆【车辆编号"+bikeCode+"】";
			break;
		case 6:
			remark += "删除车辆【车辆编号"+bikeCode+"】";
			break;
		case 7:
			remark += "删除车辆维护信息【车辆编号"+bikeCode+"】";
			break;
		case 8:
			remark += "批量删除车辆维护信息【车辆编号"+bikeCode+"】 ";
			break;
		case 9:
			remark += "添加车型【车型ID"+modelsId+"】";
			break;
		case 10:
			remark += "修改车型【车型ID"+modelsId+"】";
			break;
		case 11:
			remark += "删除车型【车型ID"+modelsId+"】";
			break;
		case 12:
			remark += "创建还车点【还车点ID"+fixedReturnId+"】";
			break;
		case 13:
			remark += "编辑还车点【还车点ID"+fixedReturnId+"】";
			break;
		case 14:
			remark += "删除还车点【还车点ID"+fixedReturnId+"】";
			break;
		case 15:
			remark += "完善还车点【还车点ID"+fixedReturnId+"】";
			break;
		case 16:
			remark += "驳回还车点【还车点ID"+fixedReturnId+"】";
			break;
		case 17:
			remark += "导出二维码";
			break;
		case 18:
			remark += "修改车辆状态【车辆编号"+bikeCode+"】";
					break;
		case 19:
			remark += "批量修改车辆状态【车辆编号"+bikeCode+"】";
			break;
		case 20:
			remark += "批量删除车辆信息【车辆编号"+bikeCode+"】 ";
			break;
		case 21:
			remark += "批量修改车辆信息【车辆编号"+bikeCode+"】 ";
			break;
		default:
			remark = "";
		    break; 
		}
	   
		return remark;
	}
	
	/**
	 * 操作用户反馈和保险申请
	 * @param adminRealName
	 * @param flag
	 * @param userRealName
	 * @param userStateStr
	 * @param money
	 * @param editMoney
	 * @return
	 */
	public static String operateFBAndInsurance(String adminRealName,Integer flag,String feedBackId,String insuranceId,String userGuidType) {
		String remark = "后台管理员【"+adminRealName+"】在【"+DateUtil.format(new Date())+"】进行了";
		switch (flag) {
		case 1:
			remark += "接受用户反馈【反馈ID"+feedBackId+"】";
			break;
		case 2:
			remark += "批量接受用户反馈【反馈ID"+feedBackId+"】";
			break;
		case 3:
			remark += "拒绝用户反馈【反馈ID"+feedBackId+"】";
			break;
		case 4:
			remark += "批量拒绝用户反馈【反馈ID"+feedBackId+"】";
			break;
		case 5:
			remark += "删除用户反馈【反馈ID"+feedBackId+"】";
			break;
		case 6:
			remark += "接受保险申请【申请ID"+insuranceId+"】";
			break;
		case 7:
			remark += "拒绝保险申请【申请ID"+insuranceId+"】 ";
			break;
		case 8:
			remark += "修改用户指南【"+userGuidType+"】";
			break;
		case 9:
			remark += "批量删除用户反馈【反馈ID"+feedBackId+"】";
			break;
		case 10:
			remark += "处理用户反馈【反馈ID"+feedBackId+"】";
			break;
		default:
			remark = "";
		    break; 
		}
	   
		return remark;
	}
	
	/**
	 * 操作租赁订单
	 * @param adminRealName
	 * @param flag
	 * @param rentInfoId
	 * @return
	 */
	public static String operateOrder(String adminRealName,Integer flag,String rentInfoId) {
		String remark = "后台管理员【"+adminRealName+"】在【"+DateUtil.format(new Date())+"】进行了";
		switch (flag) {
		case 1:
			remark += "修改租赁订单【编号ID"+rentInfoId+"】";
			break;
		case 2:
			remark += "删除租赁订单【编号ID"+rentInfoId+"】";
			break;
		case 3:
			remark += "批量删除租赁订单【编号ID"+rentInfoId+"】";
			break;
		default:
			remark = "";
		    break; 
		}
	   
		return remark;
	}
	
	/**
	 * 操作资金流水
	 * @param adminRealName
	 * @param flag
	 * @return
	 */
	public static String operateMoneyWater(String adminRealName,Integer flag) {
		String remark = "后台管理员【"+adminRealName+"】在【"+DateUtil.format(new Date())+"】进行了";
		switch (flag) {
		case 1:
			remark += "导出租赁流水";
			break;
		case 2:
			remark += "导出充值退款流水";
			break;
		case 3:
			remark += "导出保险费用流水";
			break;
		default:
			remark = "";
		    break; 
		}
	   
		return remark;
	}
	
	/**
	 * 操作渠道
	 * @param adminRealName
	 * @param flag
	 * @param channelId
	 * @return
	 */
	public static String operateChannel(String adminRealName,Integer flag,Long channelId) {
		String remark = "后台管理员【"+adminRealName+"】在【"+DateUtil.format(new Date())+"】进行了";
		switch (flag) {
		case 1:
			remark += "新增渠道【编号ID"+channelId+"】";
			break;
		case 2:
			remark += "编辑渠道【编号ID"+channelId+"】";
			break;
		case 3:
			remark += "冻结渠道【编号ID"+channelId+"】";
			break;
		case 4:
			remark += "删除渠道【编号ID"+channelId+"】";
			break;
		case 5:
			remark += "解冻渠道【编号ID"+channelId+"】";
			break;
		default:
			remark = "";
		    break; 
		}
	   
		return remark;
	}
	
	/**
	 * 操作消息
	 * @param adminRealName
	 * @param flag
	 * @param msgId
	 * @return
	 */
	public static String operateMsg(String adminRealName,Integer flag,String msgId) {
		String remark = "后台管理员【"+adminRealName+"】在【"+DateUtil.format(new Date())+"】进行了";
		switch (flag) {
		case 1:
			remark += "新增消息【编号ID"+msgId+"】";
			break;
		case 2:
			remark += "编辑消息【编号ID"+msgId+"】";
			break;
		case 3:
			remark += "上线渠道【编号ID"+msgId+"】";
			break;
		case 4:
			remark += "下线消息【编号ID"+msgId+"】";
			break;
		case 5:
			remark += "删除消息【编号ID"+msgId+"】";
			break;
		case 6:
			remark += "批量删除消息【编号ID"+msgId+"】";
			break;
		default:
			remark = "";
		    break; 
		}
	   
		return remark;
	}
	
	/**
	 * 操作退款
	 * @param adminRealName
	 * @param flag
	 * @param refundId
	 * @return
	 */
	public static String operateRefund(String adminRealName,Integer flag,String refundId) {
		String remark = "后台管理员【"+adminRealName+"】在【"+DateUtil.format(new Date())+"】进行了";
		switch (flag) {
		case 1:
			remark += "导出 退款流水";
			break;
		case 2:
			remark += "删除退款【退款单号"+refundId+"】";
			break;
		case 3:
			remark += "批量删除退款【退款单号"+refundId+"】";
			break;
		default:
			remark = "";
		    break; 
		}
	   
		return remark;
	}
	
	/**
	 * 操作锁
	 * @param adminRealName
	 * @param flag
	 * @param bikeCode
	 * @param lockCode
	 * @return
	 */
	public static String operateLock(String adminRealName,Integer flag,String bikeCode,String lockCode) {
		String remark = "后台管理员【"+adminRealName+"】在【"+DateUtil.format(new Date())+"】进行了";
		switch (flag) {
		case 1:
			remark += "导入锁";
			break;
		case 2:
			remark += "锁【"+lockCode+"】关联车【"+bikeCode+"】";
			break;
		case 3:
			remark += "删除锁【"+lockCode+"】";
			break;
		case 4:
			remark += "批量删除锁【"+lockCode+"】";
			break;
		case 5:
			remark += "批量上锁【"+lockCode+"】";
			break;
		case 6:
			remark += "批量开锁【"+lockCode+"】";
			break;
		case 7:
			remark += "批量获取状态【"+lockCode+"】";
			break;
		case 8:
			remark += "批量定位【"+lockCode+"】";
			break;
		case 9:
			remark += "开锁【"+lockCode+"】";
			break;
		case 10:
			remark += "上锁【"+lockCode+"】";
			break;
		case 11:
			remark += "定位【"+lockCode+"】";
			break;
		case 12:
			remark += "获取状态【"+lockCode+"】";
			break;
		case 13:
			remark += "获取域名配置密码【"+lockCode+"】";
			break;
		case 14:
			remark += "获取域名配置功能【"+lockCode+"】";
			break;
		case 15:
			remark += "修改锁【"+lockCode+"】";
			break;
		default:
			remark = "";
		    break; 
		}
	   
		return remark;
	}
	
	/**
	 * 操作管理员
	 * @param adminRealName
	 * @param flag
	 * @param refundId
	 * @param realName
	 * @return
	 */
	public static String operateMng(String adminRealName,Integer flag,String refundId,String realName) {
		String remark = "后台管理员【"+adminRealName+"】在【"+DateUtil.format(new Date())+"】进行了";
		switch (flag) {
		case 1:
			remark += "新增管理员【"+realName+"】";
			break;
		case 2:
			remark += "修改管理员【"+realName+"】 ";
			break;
		case 3:
			remark += "冻结管理员【"+realName+"】";
			break;
		case 4:
			remark += "删除管理员【"+realName+"】";
			break;
		case 5:
			remark += "解冻管理员【"+realName+"】";
			break;
		default:
			remark = "";
		    break; 
		}
	   
		return remark;
	}
	
	/**
	 * 操作角色
	 * @param adminRealName
	 * @param flag
	 * @param refundId
	 * @param roleName
	 * @return
	 */
	public static String operateRole(String adminRealName,Integer flag,String refundId,String roleName) {
		String remark = "后台管理员【"+adminRealName+"】在【"+DateUtil.format(new Date())+"】进行了";
		switch (flag) {
		case 1:
			remark += "新增角色【"+roleName+"】";
			break;
		case 2:
			remark += "修改角色【"+roleName+"】 ";
			break;
		case 3:
			remark += "删除角色【"+roleName+"】";
			break;
		case 4:
			remark += "修改密码";
			break;
		case 5:
			remark += "冻结角色【"+roleName+"】";
			break;
		case 6:
			remark += "解冻角色【"+roleName+"】";
			break;
		default:
			remark = "";
		    break; 
		}
	   
		return remark;
	}
}
