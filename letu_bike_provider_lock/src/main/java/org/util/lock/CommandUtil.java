package org.util.lock;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.server.lock.LockClients;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

/**
 * 指令工具类
 * 
 * @author DRH
 *
 */
public class CommandUtil {
	
	// 指令类型
	// 显示皆为十进制
	// 长度第二位，校验末尾两位
	// 长度固定，末尾校验固定
	public static final byte[] SEND_GET_BID = { (byte) 170, 7, 0, 1, 0, 0, 0, 1, 9, 0 };// 获取bid指令

	public static final byte[] SEND_GET_STATE = { (byte) 170, 7, 0, 2, 0, 0, 0, 1, 10, 0 };// 获取状态指令,锁状态、电池电压
	
	public static final byte[] SEND_UNLOCK = { (byte) 170, 7, 0, 4, 0, 0, 0, 1, 12, 0 };// 开锁指令

	public static final byte[] SEND_LOCK = { (byte) 170, 7, 0, 5, 0, 0, 0, 1, 13, 0 };// 关锁指令

	public static final byte[] SEND_GET_PWD = { (byte) 170, 7, 0, 17, 0, 0, 0, 1, 25, 0 };// 获取握手密码指令

	// 长度固定，末尾校验动态，需添加
	public static final byte[] SEND_POWER_OFF = { (byte) 170, 11, 0, 6, 0, 0, 0, 1 };// 关机指令，末尾添加4字节重启时间int转byte[],末尾校验大小动态

	public static final byte[] SEND_DOMAIN_PWD = { (byte) 170, 13, 0, 7, 0, 0, 0, 1 };// 发送握手密码指令，末尾添加6字节密码string.getByte,开启域名配置功能,末尾校验大小动态

	public static final byte[] SEND_GET_GPS = { (byte) 170, 11, 0, 9, 0, 0, 0, 1 };// 获取定位信息指令，末尾加4字节超时时间int转byte[],末尾校验大小动态

	public static final byte[] SEND_SET_PWD = { (byte) 170, 17, 0, 16, 0, 0, 0};// 设置握手密码指令，末尾添加6字节旧密码、6字节新密码string.getByte,末尾校验大小动态
	// 长度动态需修改，末尾校验动态，需添加
	public static final byte[] SEND_SET_DOMAIN = { (byte) 170, 0, 0, 8, 0, 0, 0 };// 设置IP域名指令,长度动态,末尾校验大小动态

	/*****************************新锁*******************************/
	public static final byte[] NSEND_GET_BID = { (byte) 170,  7, 0, 0, 0, 0, 1, 1, 9, 0 };// 获取bid指令
	
	public static final byte[] NSEND_GET_STATE = { (byte) 170,  7, 0, 0, 0, 0, 2, 1, 10, 0 };// 获取状态指令,锁状态、电池电压
		
	public static final byte[] NSEND_UNLOCK = {(byte) 170, 7, 0, 0, 0, 0, 4, 1, 12, 0 };// 开锁指令
	
	public static final byte[] NSEND_LOCK = {(byte) 170, 7, 0, 0, 0, 0, 5, 1, 13, 0 };// 关锁指令
	
	public static final byte[] NSEND_GET_PWD = { (byte) 170, 7, 0, 0, 0, 0, 17, 1, 25, 0 };// 获取握手密码指令
	
	// 长度固定，末尾校验动态，需添加
	public static final byte[] NSEND_POWER_OFF = { (byte) 170, 11, 0, 0, 0, 0, 6, 1 };// 关机指令，末尾添加4字节重启时间int转byte[],末尾校验大小动态

	public static final byte[] NSEND_DOMAIN_PWD = {(byte) 170, 13, 0, 0, 0, 0, 7, 1 };// 发送握手密码指令，末尾添加6字节密码string.getByte,开启域名配置功能,末尾校验大小动态

	public static final byte[] NSEND_GET_GPS = { (byte) 170, 11, 0, 0, 0, 0, 9, 1 };// 获取定位信息指令，末尾加4字节超时时间int转byte[],末尾校验大小动态

	public static final byte[] NSEND_SET_PWD = {(byte) 170, 17, 0, 0, 0, 16, 0};// 设置握手密码指令，末尾添加6字节旧密码、6字节新密码string.getByte,末尾校验大小动态
	// 长度动态需修改，末尾校验动态，需添加
	public static final byte[] NSEND_SET_DOMAIN = { (byte) 170, 0, 0, 0, 0, 0, 8 };// 设置IP域名指令,长度动态,末尾校验大小动态
	
	// 长度固定，末尾校验动态，需添加
	public static final byte[] CLOSE_LOCK_OK = { (byte) 170, 7, 0, 0, 0, 0, 18, 1, 26, 0 };// 关锁成功
	
	// 长度固定
    public static final byte[] LOCK_VERSION = { (byte) 170, 7, 0, 0, 0, 0, 20, 1, 28, 0 };// 获取车锁版本信息
    
    // 长度动态需修改，末尾校验动态，需添加
    public static final byte[] NSEND_VERSION = { (byte) 170, 26, 0, 0, 0, 0, 21};// 固件信息传输
    
    // 长度动态需修改，末尾校验动态，需添加
    public static final byte[] NSEND_FILE = { (byte) 170, (byte) 154, 1, 0, 0, 0, 22};// 文件信息传输
    
    public static final byte[] NSEND_COMPLETE = {(byte) 170, 7, 0, 0, 0, 0, 23, 1, 31, 0 };// 确认激活文件传输指令

	// 低功耗报文
	public static final byte[] POWER = { (byte) 170};
	
	public static final Integer TYPEINDEX = 3;// 功能代号下标
	public static final Integer NTYPEINDEX = 6;// 功能代号下标
	public static final Integer BID = 1;// bid功能代号
	public static final Integer STATUS = 2;// 状态功能代号
	public static final Integer BEAT = 3;// 心跳状态功能代号
	public static final Integer UNLOCK = 4;// 开锁功能代号
	public static final Integer LOCK = 5;// 关锁功能代号
	public static final Integer POWEROFF = 6;// 关机功能代号
	public static final Integer DOMAINPWD = 7;// 域名配置密码功能代号
	public static final Integer DOMAIN = 8;// 配置域名功能代号
	public static final Integer GPS = 9;// GPS信息功能代号
	public static final Integer SETPWD = 16;// 配置密码功能代号
	public static final Integer GETPWD = 17;// 获取密码功能代号
	public static final Integer NLOCK = 18;// 新锁关锁功能代号
	public static final Integer VERSION = 20;// 新锁车锁版本获取功能代号
	public static final Integer CRCVERSION = 21;// 传输固件信息功能代号
	public static final Integer SENDFILE = 22;// 传输文件信息功能代号
	public static final Integer COMPLETE = 23;// 传输完成升级功能代号
	private static Logger loggers = Logger.getLogger(CommandUtil.class);

	/**
	 * 拼接数组方法，b可为关机的重启时间、握手密码、定位超时时间、修改握手密码（旧密码+新密码）、ip或域名+端口
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	static byte[] concat(byte[] a, byte[] b) {
		byte[] c = new byte[a.length + b.length + 2];
		byte[] tail = new byte[2];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		if (0 == c[1] && 0 == c[2]) {
			byte[] length = intToBytes2(c.length - 3);// 指令长度，去头去校验
			c[1] = length[0];
			c[2] = length[1];
		}
		int tailNum = 0;
		for (int i = 1; i < c.length; i++) {
			if (c[i] < 0) {
				tailNum = tailNum + (c[i] & 0x0FF);
			} else
				tailNum = tailNum + c[i];
		}
		byte[] total = intToBytes2(tailNum);// 尾部校验
		tail[0] = total[0];
		tail[1] = total[1];
		System.arraycopy(tail, 0, c, c.length - 2, tail.length);
		return c;
	}
	
	/**
	 * 拼接数组方法，b可为关机的重启时间、握手密码、定位超时时间、修改握手密码（旧密码+新密码）、ip或域名+端口
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	static byte[] concat1(byte[] a, byte[] b) {
		byte[] c = new byte[a.length + b.length + 2];
		byte[] tail = new byte[2];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		if (0 == c[1] && 0 == c[2]) {
			byte[] length = intToBytes2(c.length - 3);// 指令长度，去头去校验
			c[1] = length[0];
			c[2] = length[1];
		}
		int tailNum = 0;
		for (int i = 1; i < c.length; i++) {
			if (c[i] < 0) {
				tailNum = tailNum + (c[i] & 0x0FF);
			} else
				tailNum = tailNum + c[i];
		}
		byte[] total = intToBytes2(tailNum);// 尾部校验
		tail[0] = total[0];
		tail[1] = total[1];
		System.arraycopy(tail, 0, c, c.length - 2, tail.length);
		return c;
	}
	
	/**
	 * int转byte[]高位在后
	 * @param num
	 * @return
	 */
	public static byte[] intToBytes2(int num) {
		byte[] result = new byte[4];
		result[3] = (byte) ((num >>> 24) & 0xff);
		result[2] = (byte) ((num >>> 16) & 0xff);
		result[1] = (byte) ((num >>> 8) & 0xff);
		result[0] = (byte) ((num >>> 0) & 0xff);
		return result;
	}
	/**
	 * 从收到的回复中读出bid
	 * @param getMessage
	 * @return
	 */
	public static String getBid(int[] getMessage) {
		String bid = "";
		for (int i = 7; i < getMessage.length - 2; i++) {
			String hexString = Integer.toHexString(getMessage[i]);
			if (hexString.length() == 1) {  
				hexString = '0' + hexString;  
			}  
			bid = bid +hexString ;
		}
		return bid.toUpperCase();
	}
	/**
	 * 从收到的回复中读出pwd
	 * @param getMessage
	 * @return
	 */
	public static String getPwd(int[] getMessage) {
		String bid = "";
		for (int i = 7; i < getMessage.length - 2; i++) {
			bid = bid + (char)getMessage[i];
		}
		return bid;
	}
	/**
	 * 从收到的回复中读出电压
	 * @param voltage
	 * @return
	 */
	public static double getVoltage(int[] voltage) {
		int b;
		b = (voltage[9] << 8) | voltage[8];
		double c = (double) b;
		return c / 100;
	}
	
	/**
	 * 从收到的回复中读出电压(半自动锁)
	 * @param voltage
	 * @return
	 */
	public static double getNVoltage(int[] voltage) {
		//电压整数部分
		int a = voltage[11];
		//电压小数部分
		String b = voltage[12]+"";
		if (b.length() == 1) {  
			b = '0' + b;  
		}
		String nowVoltage = a+"."+b;
		Double doubleVoltage = Double.valueOf(nowVoltage);
		return doubleVoltage;
	}
	
	
	/**
	 * 从收到的回复中读出纬度
	 * @param lat
	 * @return
	 */
	public static double getLat(int[] lat) {
		int b;
		b = (lat[11] << 24) | (lat[10] << 16) | (lat[9] << 8) | lat[8];
		double c = (double) b;
		return c / 1000000;
	}
	/**
	 * 从收到的回复中读出经度
	 * @param lng
	 * @return
	 */
	public static double getLng(int[] lng) {
		int b;
		b = lng[15] << 24 | lng[14] << 16 | lng[13] << 8 | lng[12];
		double c = (double) b;
		return c / 1000000;
	}
	/**
	 * 从收到的回复中读出海拔
	 * @param lng
	 * @return
	 */
	public static double getHeaght(int[] height) {
		int b;
		b = height[19] << 24 | height[18] << 16 | height[17] << 8 | height[16];
		double c = (double) b;
		return c / 1000000;
	}

	
	
	/**
	 * 从收到的回复中读出已传文件
	 * @param lat
	 * @return
	 */
	public static int getSize(int[] lat) {
		int b = (lat[14] << 24) | (lat[13] << 16) | (lat[12] << 8) | lat[11];
		return b;
	}
	
	/**
	 * 发送关锁指令
	 * @param bid
	 * @return
	 * @throws Exception
	 */
	public static boolean lock(String bid) throws Exception {
		ChannelHandlerContext ctx = LockClients.getLockChannel(bid);
		if (null == ctx) {
			loggers.info("锁id为：" + bid + "的硬件尚未与服务器关联...");
			return false;
		} else {
			Integer lockType = LockClients.getType(bid+"t");
			if(lockType==0){
				ctx.writeAndFlush(Unpooled.copiedBuffer(SEND_LOCK));
			}else if(lockType==1){
				ctx.writeAndFlush(Unpooled.copiedBuffer(POWER));
				Thread.sleep(50);
				ctx.writeAndFlush(Unpooled.copiedBuffer(NSEND_LOCK));
			}
			loggers.info("成功向客户端" + ctx.channel().remoteAddress() + "发送关锁指令"+";ctx"+ctx.channel().id());
			return true;
		}
	}
	
	/**
	 * 发送开锁指令
	 * @param bid
	 * @return
	 * @throws Exception
	 */
	public static boolean unLock(String bid) throws Exception {
		ChannelHandlerContext ctx = LockClients.getLockChannel(bid);
		if (null == ctx) {
//			TipsHandler.pushTips(bid,"3");
			loggers.info("锁id为：" + bid + "的硬件尚未与服务器关联...");
			return false;
		} else {
			Integer lockType = LockClients.getType(bid+"t");
			if(lockType==0){
				ctx.writeAndFlush(Unpooled.copiedBuffer(SEND_UNLOCK));
			}else if(lockType==1){
				ctx.writeAndFlush(Unpooled.copiedBuffer(POWER));
				Thread.sleep(50);
				ctx.writeAndFlush(Unpooled.copiedBuffer(NSEND_UNLOCK));
			}
			loggers.info("成功向客户端" + ctx.channel().remoteAddress() + "发送开锁指令"+";ctx"+ctx.channel().id());
			return true;
		}
	}
	/**
	 * 发送获取状态指令
	 * @param bid
	 * @return
	 * @throws Exception
	 */
	public static boolean getStatus(String bid) throws Exception {
		ChannelHandlerContext ctx = LockClients.getLockChannel(bid);
		if (null == ctx) {
			loggers.info("锁id为：" + bid + "的硬件尚未与服务器关联...");
			return false;
		} else {
			Integer lockType = LockClients.getType(bid+"t");
			if(lockType==0){
				ctx.writeAndFlush(Unpooled.copiedBuffer(SEND_GET_STATE));
			}else if(lockType==1){
				ctx.writeAndFlush(Unpooled.copiedBuffer(POWER));
				Thread.sleep(50);
				ctx.writeAndFlush(Unpooled.copiedBuffer(NSEND_GET_STATE));
			}
			loggers.info("成功向客户端" + ctx.channel().remoteAddress() + "发送获取信息指令"+";ctx"+ctx.channel().id());
			return true;
		}
	}
	
	
	/**
	 * 发送获取域名配置密码指令
	 * @param bid
	 * @return
	 * @throws Exception
	 */
	public static boolean getDomainPwd(String bid) throws Exception {
		ChannelHandlerContext ctx = LockClients.getLockChannel(bid);
		if (null == ctx) {
			loggers.info("锁id为：" + bid + "的硬件尚未与服务器关联...");
			return false;
		} else {
			Integer lockType = LockClients.getType(bid+"t");
			if(lockType==0){
				ctx.writeAndFlush(Unpooled.copiedBuffer(SEND_GET_PWD));
			}else if(lockType==1){
				ctx.writeAndFlush(Unpooled.copiedBuffer(NSEND_GET_PWD));
			}
			loggers.info("成功向客户端" + ctx.channel().remoteAddress() + "发送获取域名配置密码指令");
			return true;
		}
	}
	
	
	
	
	
	
	
	/**
	 * 发送关机指令
	 * @param bid
	 * @param time
	 * @return
	 * @throws Exception
	 */
	public static boolean powerOff(String bid,int time) throws Exception {
		ChannelHandlerContext ctx = LockClients.getLockChannel(bid);
		if (null == ctx) {
			loggers.info("锁id为：" + bid + "的硬件尚未与服务器关联...");
			return false;
		} else {
			Integer lockType = LockClients.getType(bid+"t");
			if(lockType==0){
				byte[] command=concat(SEND_POWER_OFF, intToBytes2(time));
				ctx.writeAndFlush(Unpooled.copiedBuffer(command));
			}else if(lockType==1){
				byte[] command=concat(NSEND_POWER_OFF, intToBytes2(time));
				ctx.writeAndFlush(Unpooled.copiedBuffer(command));
			}
			loggers.info("成功向客户端" + ctx.channel().remoteAddress() + "发送获取域名配置密码指令");
			return true;
		}
	}
	/**
	 * 发送域名配置握手
	 * @param bid
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public static boolean domainPwd(String bid,String pwd) throws Exception {
		ChannelHandlerContext ctx = LockClients.getLockChannel(bid);
		if (null == ctx) {
			loggers.info("锁id为：" + bid + "的硬件尚未与服务器关联...");
			return false;
		} else {
			Integer lockType = LockClients.getType(bid+"t");
			if(lockType==0){
				byte[] command=concat(SEND_DOMAIN_PWD, pwd.getBytes());
				ctx.writeAndFlush(Unpooled.copiedBuffer(command));
			}else if(lockType==1){
				byte[] command=concat(NSEND_DOMAIN_PWD, pwd.getBytes());
				ctx.writeAndFlush(Unpooled.copiedBuffer(command));
			}
			loggers.info("成功向客户端" + ctx.channel().remoteAddress() + "发送域名配置握手指令");
			return true;
		}
	}
	
	/**
	 * 发送获取定位信息指令
	 * @param bid
	 * @param time
	 * @return
	 * @throws Exception
	 */
	public static boolean getGPS(String bid,int time) throws Exception {
		ChannelHandlerContext ctx = LockClients.getLockChannel(bid);
		if (null == ctx) {
			loggers.info("锁id为：" + bid + "的硬件尚未与服务器关联...");
			return false;
		} else {
			Integer lockType = LockClients.getType(bid+"t");
			if(lockType==0){
				byte[] command=concat(SEND_GET_GPS, intToBytes2(time));
				ctx.writeAndFlush(Unpooled.copiedBuffer(command));
			}else if(lockType==1){
				ctx.writeAndFlush(Unpooled.copiedBuffer(POWER));
				Thread.sleep(50);
				byte[] command=concat(NSEND_GET_GPS, intToBytes2(time));
				ctx.writeAndFlush(Unpooled.copiedBuffer(command));
			}
			loggers.info("成功向客户端" + ctx.channel().remoteAddress() + "发送获取定位信息指令");
			return true;
		}
	}
	/**
	 * 发送修改域名握手密码指令
	 * @param bid
	 * @param opwd
	 * @param npwd
	 * @return
	 * @throws Exception
	 */
	public static boolean setDomianPwd(String bid,String opwd,String npwd) throws Exception {
		ChannelHandlerContext ctx = LockClients.getLockChannel(bid);
		if (null == ctx) {
			loggers.info("锁id为：" + bid + "的硬件尚未与服务器关联...");
			return false;
		} else {
			String pwds=opwd+npwd;
			Integer lockType = LockClients.getType(bid+"t");
			if(lockType==0){
				byte[] command=concat(SEND_SET_PWD, pwds.getBytes());
				ctx.writeAndFlush(Unpooled.copiedBuffer(command));
			}else if(lockType==1){
				byte[] command=concat(NSEND_SET_PWD, pwds.getBytes());
				ctx.writeAndFlush(Unpooled.copiedBuffer(command));
			}
			loggers.info("成功向客户端" + ctx.channel().remoteAddress() + "发送修改域名握手密码指令");
			return true;
		}
	}
	/**
	 * 发送修改域名指令
	 * @param bid
	 * @param domain
	 * @param port
	 * @return
	 * @throws Exception
	 */
	public static boolean setDomian(String bid,String domain,String port) throws Exception {
		ChannelHandlerContext ctx = LockClients.getLockChannel(bid);
		if (null == ctx) {
			loggers.info("锁id为：" + bid + "的硬件尚未与服务器关联...");
			return false;
		} else {
			String dp="\""+domain+"\","+port+"\0";
			Integer lockType = LockClients.getType(bid+"t");
			if(lockType==0){
				byte[] command=concat(SEND_SET_DOMAIN, dp.getBytes());
				ctx.writeAndFlush(Unpooled.copiedBuffer(command));
			}else if(lockType==1){
				ctx.writeAndFlush(Unpooled.copiedBuffer(POWER));
				Thread.sleep(50);
				byte[] command=concat(NSEND_SET_DOMAIN, dp.getBytes());
				ctx.writeAndFlush(Unpooled.copiedBuffer(command));
			}
			loggers.info("成功向客户端" + ctx.channel().remoteAddress() + "发送修改域名指令");
			return true;
		}
	}
	
	
	/**
	 * 判断锁是否在线
	 * @param bid
	 * @return
	 * @throws Exception
	 */
	public static boolean judge(String bid) throws Exception {
		ChannelHandlerContext ctx = LockClients.getLockChannel(bid);
		if (null == ctx) {
			loggers.info("锁id为：" + bid + "的硬件尚未与服务器关联...");
			return false;
		} else {
			return true;
		}
	}
	
	
	/**
	 * 发送获取车锁版本信息指令
	 * @param bid
	 * @param time
	 * @return
	 * @throws Exception
	 */
	public static boolean getVersion(String bid) throws Exception {
		ChannelHandlerContext ctx = LockClients.getLockChannel(bid);
		if (null == ctx) {
			loggers.info("锁id为：" + bid + "的硬件尚未与服务器关联...");
			return false;
		} else {
			Integer lockType = LockClients.getType(bid+"t");
			 if(lockType==1){
				ctx.writeAndFlush(Unpooled.copiedBuffer(POWER));
				Thread.sleep(50);
				ctx.writeAndFlush(Unpooled.copiedBuffer(LOCK_VERSION));
			}
			loggers.info("成功向客户端" + ctx.channel().remoteAddress() + "发送获取车锁信息指令");
			return true;
		}
	}
	
	/**
	 * 发送固件版本信息
	 * @param bid
	 * @param domain
	 * @param port
	 * @return
	 * @throws Exception
	 */
	public static boolean sendVersion(String bid,int version,int image_size,String image_crc) throws Exception {
		ChannelHandlerContext ctx = LockClients.getLockChannel(bid);
		if (null == ctx) {
			loggers.info("锁id为：" + bid + "的硬件尚未与服务器关联...");
			return false;
		} else {
			Integer lockType = LockClients.getType(bid+"t");
			if(lockType==1){
				//版本
//				version = 16;
//				int version_int = Integer.parseInt(version,16);
				byte[] version_byte = intToBytes2(version);
				//文件大小
//				image_size = 82828;
//				image_size = 82800;
				byte[] image_size_byte = intToBytes2(image_size);
				//文件校验码
//				image_crc = "84e914a9"; 
				BigInteger image_crc_big = new BigInteger(image_crc,16);
				int image_crc_int = image_crc_big.intValue();
				byte[] image_crc_byte = intToBytes2(image_crc_int);
				//固件类型
				int bank_code =  1; 
				byte[] bank_code_byte = intToBytes2(bank_code);
				//堆栈
				int sd_size = 0;
				byte[] sd_size_byte = intToBytes2(sd_size);
				byte[] concat = copyArry(version_byte ,image_size_byte , image_crc_byte ,bank_code_byte , sd_size_byte );
				byte[] bs = concat(NSEND_VERSION, concat);
				int newReq[] =null;
				newReq = new int[bs.length];
				for (int i = 0; i < bs.length; i++) {
					newReq[i] = bs[i] & 0x0FF;
				}
				loggers.info("传输数据为："+Arrays.toString(newReq));
				ctx.writeAndFlush(Unpooled.copiedBuffer(POWER));
				Thread.sleep(50);
				byte[] command=bs;
				ctx.writeAndFlush(Unpooled.copiedBuffer(command));
			}
			loggers.info("成功向客户端" + ctx.channel().remoteAddress() + "发送获取固件信息指令");
			return true;
		}
	}
	
	public static byte[] copyArry(byte[] version_byte ,byte[] image_size_byte ,byte[] image_crc_byte ,byte[] bank_code_byte ,byte[] sd_size_byte ){
		    List collect = new ArrayList();  
	        collect.add(version_byte);  
	        collect.add(image_size_byte); 
	        collect.add(image_crc_byte); 
	        collect.add(bank_code_byte);
	        collect.add(sd_size_byte);
	        byte[] aa0 = null;  
	        // tyy 每次都是两个数组合并 所以合并的次数为 collect.size() ，第一个是虚拟的数组  
	        for (int i = 0; i < collect.size(); i++) {  
	        	byte[] aa1 = (byte[]) collect.get(i);  
	        	byte[] c = null;
	        	if( i==0){
	        		c = aa1;
	        	}else{
	        		c= new byte[aa0.length+aa1.length]; 
	        		System.arraycopy(aa0, 0, c, 0, aa0.length);  
	        		System.arraycopy(aa1, 0, c, aa0.length, aa1.length); 
	        	}
	            aa0 = c;  
	        }  
	        return aa0;  
	}
    
	
	/**
	 * 发送文件内容
	 * @param bid
	 * @param domain
	 * @param port
	 * @return
	 * @throws Exception
	 */
	public static boolean sendFile(String  bid ,int i,String filePath,int size) throws Exception {
		ChannelHandlerContext ctx = LockClients.getLockChannel(bid);
		if (null == ctx) {
			loggers.info("锁id为：" + bid + "的硬件尚未与服务器关联...");
			return false;
		} else {
			Integer lockType = LockClients.getType(bid+"t");
			if(lockType==1){
				FileInputStream fileInput = null;
//				String fileName="/opt/app-key-0x84e914a9.bin";
				String fileName=filePath;
				File file = new File(fileName);  
			    if (!file.exists()) {  
						file.createNewFile();
			    }
			    if(file.length()==size){
			    	sendComplete(bid);
			    	return true;
			    }
			    
			    byte[] buffer = new byte[400];  
			    fileInput = new FileInputStream(file);  
			    int byteread = 0;  
			    // byteread表示一次读取到buffers中的数量。  
			    int j = 0;
			    while ((byteread = fileInput.read(buffer)) != -1) {  
			    	if(i==j){
			    		int readySend = i * 400; 
			    		byte[] aa0 = intToBytes2(readySend);
			    		if((file.length()-i*400)<400){
			    			int byte_length = byteread+2+4+4;
							byte[] bytes2 = intToBytes2(byte_length);
							byte[] NSEND_FILE1 = { (byte) 170, (byte) 154, 1, 0, 0, 0, 22};
							NSEND_FILE1[1] = (byte) (bytes2[0] & 0x0FF);
							NSEND_FILE1[2] = (byte) (bytes2[1] & 0x0FF);
						    byte[] bs = new byte[byteread];  
							System.arraycopy(buffer, 0, bs, 0, byteread);
							byte[] c= new byte[aa0.length+bs.length]; 
			        		System.arraycopy(aa0, 0, c, 0, aa0.length);  
			        		System.arraycopy(bs, 0, c, aa0.length, bs.length); 
				    		
				    		byte[] bs1 = concat(NSEND_FILE1, c);
				    		int newReq[] =null;
							newReq = new int[bs1.length];
							for (int k = 0; k < bs1.length; k++) {
								newReq[k] = bs1[k] & 0x0FF;
							}
							loggers.info("传输数据为："+Arrays.toString(newReq));
							ctx.writeAndFlush(Unpooled.copiedBuffer(POWER));
							Thread.sleep(50);
							byte[] command=bs1;
							ctx.writeAndFlush(Unpooled.copiedBuffer(command));
							loggers.info("成功向客户端" + ctx.channel().remoteAddress() + "发送最后一包文件信息");
				    		return true;
					    }else{
					    	byte[] c= new byte[aa0.length+buffer.length]; 
					    	System.arraycopy(aa0, 0, c, 0, aa0.length);  
					    	System.arraycopy(buffer, 0, c, aa0.length, buffer.length); 
					    	
					    	byte[] bs = concat(NSEND_FILE, c);
					    	int newReq[] =null;
					    	newReq = new int[bs.length];
					    	for (int k = 0; k < bs.length; k++) {
					    		newReq[k] = bs[k] & 0x0FF;
					    	}
					    	loggers.info("传输数据为："+Arrays.toString(newReq));
					    	ctx.writeAndFlush(Unpooled.copiedBuffer(POWER));
					    	Thread.sleep(50);
					    	byte[] command=bs;
					    	ctx.writeAndFlush(Unpooled.copiedBuffer(command));
					    	loggers.info("成功向客户端" + ctx.channel().remoteAddress() + "发送文件信息");
					    	return true;
					    }
			    	}
			        j++;
			    }  
			    
			}
			return true;
		}
	}
	
	/**
	 * 发送文件激活指令
	 * @param bid
	 * @param domain
	 * @param port
	 * @return
	 * @throws Exception
	 */
	public static boolean sendComplete(String  bid) throws Exception {
		ChannelHandlerContext ctx = LockClients.getLockChannel(bid);
		if (null == ctx) {
			loggers.info("锁id为：" + bid + "的硬件尚未与服务器关联...");
			return false;
		} else {
			ctx.writeAndFlush(Unpooled.copiedBuffer(POWER));
			Thread.sleep(50);
			ctx.writeAndFlush(Unpooled.copiedBuffer(NSEND_COMPLETE));
			loggers.info("成功向客户端" + ctx.channel().remoteAddress() + "发送固件激活信息");
			return true;
		}
	}
	
}
