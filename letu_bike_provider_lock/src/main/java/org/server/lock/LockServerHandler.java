package org.server.lock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.entity.dto.Bike;
import org.entity.dto.BikeLockInfo;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.FixedReturn;
import org.entity.dto.Models;
import org.entity.dto.RentPrice;
import org.entity.dto.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.service.lock.self.LockBikeService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.util.BlockUtil;
import org.util.DateUtil;
import org.util.DistanceUtil;
import org.util.Gps;
import org.util.PositionUtil;
import org.util.SplitUtil;
import org.util.lock.CommandUtil;
import org.util.redis.RedisService;
import org.util.redis.SendMessage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 硬件消息处理类
 * 
 * @author DRH
 *
 */
@Service("lockServerHandler")
@Scope("prototype")
@Sharable
public class LockServerHandler extends ChannelHandlerAdapter {

	private static LockBikeService lockbikeService;
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(LockServerHandler.class);

	@Resource
	public void setLockBikeService(LockBikeService lockbikeService) {
		LockServerHandler.lockbikeService = lockbikeService;
	}
	
	 /**封装之后，实行单例模式，不用每次开关*/
    private static RedisService redisService;
    
    @Resource
	public void setLockBikeService(RedisService redisService) {
		LockServerHandler.redisService = redisService;
	}
    
    private static SendMessage sendMessage;
    
    @Resource
	public void setSendMessage(SendMessage sendMessage) {
		LockServerHandler.sendMessage = sendMessage;
	}

	String bid = "";
	Integer commandType = 0;
	
	//锁的类型
	Integer lockType = 0;

	int[] newReq = null;
	ChannelHandlerContext ctx2 = null;
	
	// 失败计数器：未收到client端发送的ping请求  
    private int unRecPingTimes = 0 ; 
    
	@Override
  	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
  		// TODO Auto-generated method stub
		if (evt instanceof IdleStateEvent) {

			IdleStateEvent event = (IdleStateEvent) evt;

			if (event.state().equals(IdleState.READER_IDLE)) {

				// 失败计数器次数大于等于2次的时候，关闭链接，等待client重连  
                if(unRecPingTimes >= 1){
                	loggers.info("===服务端===(读超时，关闭chanel)"+bid+";ctx"+ctx.channel().id());  
                    // 连续超过N次未收到client的ping消息，那么关闭该通道，等待client重连  
                    ctx.channel().close();  
                }else{  
                    // 失败计数器加1  
                    unRecPingTimes++;  
                    loggers.info("客户端读超时："+unRecPingTimes+"次"+bid+";ctx"+ctx.channel().id());
                }  

			} else if (event.state().equals(IdleState.WRITER_IDLE)) {
				loggers.info("客户端写超时"+bid);
			} else if (event.state().equals(IdleState.ALL_IDLE)) {
				loggers.info("客户端读&写超时"+bid);
			}
			super.userEventTriggered(ctx, evt);
		}
  	}

	@Override
	public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		// TODO Auto-generated method stub
		super.close(ctx, promise);
		LockClients.removeLockChanneKey(ctx.channel().remoteAddress().toString());
		loggers.info("客户端" + ctx.channel().remoteAddress() + "断开了连接..."+";ctx"+ctx.channel().id());
		stopConnection();
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {// 建立连接时执行
		// 客户端连接上，发送获取bid请求
		super.channelActive(ctx);
		ctx2 = ctx;
		loggers.info("客户端" + ctx.channel().remoteAddress() + "连上了..."+";ctx"+ctx.channel().id());
//		LockClients.addLockChannelKey(ctx2.channel().remoteAddress().toString(), "1");
//		loggers.info("当前连接数!!!!!!!:"+ LockClients.getChannelKeys().size());
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);// 五秒时间给硬件连接缓冲
					sendGetBid();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelInactive(ctx);
		ctx2 = ctx;
		LockClients.removeLockChanneKey(ctx.channel().remoteAddress().toString());
		loggers.info("inactive:客户端" + ctx.channel().remoteAddress() + "断开了连接..."+";ctx"+ctx.channel().id());
		stopConnection();
		ctx.close();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		 // 失败计数器清零  
        unRecPingTimes = 0; 
		// 解析收到的信息
		ByteBuf buf = (ByteBuf) msg;
		byte[] req = null;
		try{
			req =	new byte[buf.readableBytes()];
			newReq = new int[req.length];
			buf.readBytes(req);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(buf!=null){
				buf.release();
			}
		}
		for (int i = 0; i < req.length; i++) {
			newReq[i] = req[i] & 0x0FF;
		}
		loggers.info(bid+"传输数据为："+Arrays.toString(newReq));
		commandType = newReq[CommandUtil.TYPEINDEX];
		//新锁
		if(commandType==0){
			commandType = newReq[CommandUtil.NTYPEINDEX];
			lockType = 1;
		}

	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelReadComplete(ctx);
		ctx2 = ctx;
		new Thread() {
			@Override
			public void run() {
				try {
					if (CommandUtil.BID == commandType) {// 根据获取的bid信息向数据库添加未知的锁
						if (null==bid || "".equals(bid)) {
							bid();
						}
					}else if(CommandUtil.BEAT == commandType){//记录心跳
//						loggers.info("bid为："+bid+"的心跳"+";ctx"+ctx2.channel().id());
						beat();
					}else if (CommandUtil.STATUS == commandType) {// 返回状态信息
						if (ctx2.channel().remoteAddress().toString()
								.equals(LockClients.getLockChannel(bid).channel().remoteAddress().toString())) {
							status();
						}
					} else if (CommandUtil.UNLOCK == commandType) {// 开锁信息
						if (ctx2.channel().remoteAddress().toString()
								.equals(LockClients.getLockChannel(bid).channel().remoteAddress().toString())) {
							loggers.info("开锁状态回复"+newReq[7]);
							if (0 == newReq[7])
								unLock();
							if (1 == newReq[7]){
								//请求超时
								loggers.info(bid+"开锁请求超时");
								unlockfail();
								redisService.set("r"+bid,"1",60000);
								//关闭jedis
								redisService.closeJedis();
								sendMessage.sendMessage("lock", bid);
//								TipsHandler.pushTips(bid,"1");
							}
							if (4 == newReq[7]){
								//开锁一次堵转
								loggers.info(bid+"开锁一次堵转");
								unlockfail();
								redisService.set("r"+bid,"4",60000);
								//关闭jedis
								redisService.closeJedis();
								sendMessage.sendMessage("lock", bid);
//								TipsHandler.pushTips(bid,"4");
							}
							if (5 == newReq[7]){
								//开锁二次堵转
								loggers.info(bid+"开锁二次堵转");
								unlockfail();
								redisService.set("r"+bid,"5",60000);
								//关闭jedis
								redisService.closeJedis();
								sendMessage.sendMessage("lock", bid);
//								TipsHandler.pushTips(bid,"5");
							}
						}
					} else if (CommandUtil.LOCK == commandType||CommandUtil.NLOCK == commandType) {// 关锁信息
						if (ctx2.channel().remoteAddress().toString()
								.equals(LockClients.getLockChannel(bid).channel().remoteAddress().toString())) {
							loggers.info("关锁状态回复"+newReq[7]);
							if (0 == newReq[7]||2 == newReq[7])
								lock();
							if (1 == newReq[7]){
								//请求超时
								loggers.info(bid+"关锁请求超时");
								redisService.set("r"+bid,"1",60000);
								//关闭jedis
								redisService.closeJedis();
								sendMessage.sendMessage("lock", bid);
//								TipsHandler.pushTips(bid,"1");
							}
							if (4 == newReq[7]){
								//关锁一次堵转
								loggers.info(bid+"关锁一次堵转");
								redisService.set("r"+bid,"4",60000);
								//关闭jedis
								redisService.closeJedis();
								sendMessage.sendMessage("lock", bid);
//								TipsHandler.pushTips(bid,"4");
							}
							if (5 == newReq[7]){
								//关锁二次堵转
								loggers.info(bid+"关锁二次堵转");
								redisService.set("r"+bid,"5",60000);
								sendMessage.sendMessage("lock", bid);
								//关闭jedis
								redisService.closeJedis();
//								TipsHandler.pushTips(bid,"5");
							}
						}
					} else if (CommandUtil.GPS == commandType) {// 返回GPS信息
						if (ctx2.channel().remoteAddress().toString()
								.equals(LockClients.getLockChannel(bid).channel().remoteAddress().toString())) {
							if (0 == newReq[7])
								gps();
							//基站定位
							if (3 == newReq[7])
								agps();
							if (1 == newReq[7])
								loggers.info(bid+"定位失败");
						}
					} else if (CommandUtil.POWEROFF == commandType) {// 返回关机信息
						if (ctx2.channel().remoteAddress().toString()
								.equals(LockClients.getLockChannel(bid).channel().remoteAddress().toString())) {
							if (0 == newReq[7])
								powerOff();
							domainPwd();
						}
					} else if (CommandUtil.DOMAINPWD == commandType) {// 返回握手信息
						if (ctx2.channel().remoteAddress().toString()
								.equals(LockClients.getLockChannel(bid).channel().remoteAddress().toString())) {
							domainPwd();
						}
					} else if (CommandUtil.DOMAIN == commandType) {// 返回配置域名信息
						if (ctx2.channel().remoteAddress().toString()
								.equals(LockClients.getLockChannel(bid).channel().remoteAddress().toString())) {
							setDomain();
						}
					} else if (CommandUtil.SETPWD == commandType) {// 返回修改配置域名密码信息
						if (ctx2.channel().remoteAddress().toString()
								.equals(LockClients.getLockChannel(bid).channel().remoteAddress().toString())) {
							setDomainPwd();
						}
					} else if (CommandUtil.GETPWD == commandType) {// 返回获取配置域名密码信息
						if (ctx2.channel().remoteAddress().toString()
								.equals(LockClients.getLockChannel(bid).channel().remoteAddress().toString())) {
							getDomainPwd();
						}
					}else if(commandType == 16 ||commandType == 129){
						loggers.info(bid+"数据传输出错"+commandType);
					}else if (CommandUtil.VERSION == commandType) {// 返回车锁版本信息
						if (ctx2.channel().remoteAddress().toString()
								.equals(LockClients.getLockChannel(bid).channel().remoteAddress().toString())) {
							getVersion();
						}
					}  else if (CommandUtil.CRCVERSION == commandType) {
						if (ctx2.channel().remoteAddress().toString()
								.equals(LockClients.getLockChannel(bid).channel().remoteAddress().toString())) {
							loggers.info("发送固件信息回复"+newReq[7]);
							BikeLockInfo bikeLockInfo = lockbikeService.getLock(bid);
							if(newReq[7]==0){
								Thread.sleep(50);
								int size = CommandUtil.getSize(newReq);
								int i = size/400;
								CommandUtil.sendFile(bid,i,bikeLockInfo.getBikeLockUpfile(),size);
								redisService.set("upfile"+bid, DateUtil.format(new Date())+",file"+","+i+","+size,100);
								redisService.closeJedis();
							}else if(newReq[7]==1){
								bikeLockInfo.setBikeLockUpstate(2);//2升级失败
								loggers.info("超过大小");
							}else if(newReq[7]==2){
								bikeLockInfo.setBikeLockUpstate(2);
								loggers.info("存储出错");
							}else if(newReq[7]==3){
								bikeLockInfo.setBikeLockUpstate(2);
								loggers.info("未四字节对齐");
							}else if(newReq[7]==4){
								bikeLockInfo.setBikeLockUpstate(2);
								loggers.info("偏移地址出错，丢包");
							}
							lockbikeService.updateLock(bikeLockInfo);
							
						}
					}else if (CommandUtil.SENDFILE == commandType) {
						if (ctx2.channel().remoteAddress().toString()
								.equals(LockClients.getLockChannel(bid).channel().remoteAddress().toString())) {
							loggers.info("发送文件回复"+newReq[7]);
							BikeLockInfo bikeLockInfo = lockbikeService.getLock(bid);
							if(newReq[7]==0){
								Thread.sleep(50);
								int size = CommandUtil.getSize(newReq);
								int i = size/400;
								CommandUtil.sendFile(bid,i,bikeLockInfo.getBikeLockUpfile(),size);
								redisService.set("upfile"+bid, DateUtil.format(new Date())+",file"+","+i+","+size,100);
								redisService.closeJedis();
							}else if(newReq[7]==1){
								bikeLockInfo.setBikeLockUpstate(2);
								loggers.info("超过大小");
							}else if(newReq[7]==2){
								bikeLockInfo.setBikeLockUpstate(2);
								loggers.info("存储出错");
							}else if(newReq[7]==3){
								bikeLockInfo.setBikeLockUpstate(2);
								loggers.info("未四字节对齐");
							}else if(newReq[7]==4){
								bikeLockInfo.setBikeLockUpstate(2);
								loggers.info("偏移地址出错，丢包");
							}
							lockbikeService.updateLock(bikeLockInfo);
							
						}
					}else if (CommandUtil.COMPLETE == commandType) {
						if (ctx2.channel().remoteAddress().toString()
								.equals(LockClients.getLockChannel(bid).channel().remoteAddress().toString())) {
							loggers.info("发送固件激活回复"+newReq[7]);
							redisService.set("upfile"+bid, DateUtil.format(new Date())+",firmware",100);
							redisService.closeJedis();
							BikeLockInfo bikeLockInfo = lockbikeService.getLock(bid);
							bikeLockInfo.setBikeLockUpstate(2);
							if(newReq[7]==0){
								bikeLockInfo.setBikeLockUpstate(1);//1升级成功
								loggers.info("升级成功");
							}else if(newReq[7]==1){
								loggers.info("固件校验crc错误");
							}else if(newReq[7]==2){
								loggers.info("固件大于信息中规定的大小");
							}else if(newReq[7]==3){
								loggers.info("固件小于信息中规定的大小");
							}else if(newReq[7]==4){
								loggers.info("存储器擦写失败");
							}
							lockbikeService.updateLock(bikeLockInfo);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();

	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {// 断开连接时调用
		// TODO Auto-generated method stub
		super.channelUnregistered(ctx);
		ctx2 = ctx;
		LockClients.removeLockChanneKey(ctx.channel().remoteAddress().toString());
		loggers.info("unregistered:客户端" + ctx.channel().remoteAddress() + "断开了连接..."+";ctx"+ctx.channel().id());
		stopConnection();
		ctx.close();
	}

	/**
	 * 发送获取bid指令
	 * 
	 * @throws Exception
	 */
	public void sendGetBid() throws Exception {
		ctx2.writeAndFlush(Unpooled.copiedBuffer(CommandUtil.SEND_GET_BID));
		loggers.info("全自动锁bid发送成功");
		loggers.info("成功向客户端" + ctx2.channel().remoteAddress() + "发送获取BID指令...");
	}
	
	/**
	 * 发送获取bid指令(新锁)
	 * 
	 * @throws Exception
	 */
	public void sendGetNBid() throws Exception {
		ctx2.writeAndFlush(Unpooled.copiedBuffer(CommandUtil.POWER));
		Thread.sleep(50);
		ctx2.writeAndFlush(Unpooled.copiedBuffer(CommandUtil.NSEND_GET_BID));
		loggers.info("半自动锁bid发送成功");
		loggers.info("成功向客户端" + ctx2.channel().remoteAddress() + "发送获取BID指令...");
	}

	/**
	 * 处理bid回复
	 * 
	 * @throws Exception
	 */
	public void bid() throws Exception {
		bid = CommandUtil.getBid(newReq);
		LockClients.addLockChannel(bid, ctx2);
		LockClients.addLockChannelKey(ctx2.channel().remoteAddress().toString(), bid);
		if(lockType==0){
			//全自动锁
			LockClients.addType(bid+"t", 0);
		}else if(lockType==1){
			//半自动锁
			LockClients.addType(bid+"t", 1);
		}
		loggers.info("锁id为" + bid + "的连接关联已建立,类型为"+lockType);
		BikeLockInfo bikeLockInfo = lockbikeService.getLock(bid);
		if (null == bikeLockInfo) {
			bikeLockInfo = new BikeLockInfo();
			bikeLockInfo.setBikeLockCode(bid);
			bikeLockInfo.setBikeLockStatus(0);
			bikeLockInfo.setBikeLockDel(0);
			bikeLockInfo.setBikeLockIsFence(0);
			bikeLockInfo.setBikeLockEquipmentState(0);
			bikeLockInfo.setBikeLockState(1);//状态为在线
			if(lockType==0){
				bikeLockInfo.setBikeLockType(0);
			}else if(lockType==1){
				bikeLockInfo.setBikeLockType(1);
			}
			lockbikeService.addLock(bikeLockInfo);
		}
		bikeLockInfo.setBikeLockState(1);
		bikeLockInfo.setBikeLockDel(0);
		lockbikeService.updateLock(bikeLockInfo);
		loggers.info("客户端：" + ctx2.channel().remoteAddress() + "已回复BID：" + bid);

	}
	
	/**
	 * 处理心跳回复
	 * 
	 * @throws Exception
	 */
	public void beat() throws Exception {
		bid = LockClients.getLockChannelKey(ctx2.channel().remoteAddress().toString());
		if (null == bid || "".equals(bid)) {
			loggers.info("未与BID关联的锁" + ctx2.channel().remoteAddress() + "发来心跳信息");
			if(newReq[4]==1){
				sendGetNBid();
			}else if(newReq[4]==0){
				sendGetBid();
			}
		} else {
			if(newReq[4]==0){
				//全自动锁
				loggers.info(bid+"全自动锁心跳");
			}else if(newReq[4]==1){
				loggers.info(bid+"新锁心跳");
				//半自动锁
				double voltage = CommandUtil.getNVoltage(newReq);
				loggers.info("bid为："+bid+"的电压"+voltage);
				
				BikeLockInfo bikeLockInfo = lockbikeService.getLock(bid);
				bikeLockInfo.setBikeLockVoltage(voltage);
				lockbikeService.updateLock(bikeLockInfo);
				Double lowpower = lockbikeService.getParament("lowpower");
				if(null!=bikeLockInfo.getBikeLockBikeId()){
					Bike bike = new Bike();
					bike.setBikeId(bikeLockInfo.getBikeLockBikeId());
					if(voltage<=lowpower){
						//车辆低电压
						bike.setBikeAlert(2);
					}else{
						//车辆电压正常
						bike.setBikeAlert(1);
					}
					lockbikeService.updateBike(bike);
				}
			}
			loggers.info("bid为："+bid+"的心跳"+";锁类型"+lockType+"ctx"+ctx2.channel().id());
		}
	}

	/**
	 * 处理状态回复
	 * 
	 * @throws Exception
	 */
	public void status() throws Exception {
		bid = LockClients.getLockChannelKey(ctx2.channel().remoteAddress().toString());
		if (null == bid || "".equals(bid)) {
			loggers.info("未与BID关联的锁" + ctx2.channel().remoteAddress() + "发来状态信息");
			sendGetBid();
		} else {
			double voltage = 0;
			if(lockType==0){
				voltage = CommandUtil.getVoltage(newReq);
			}
			BikeLockInfo bikeLockInfo = lockbikeService.getLock(bid);
			if (null != bikeLockInfo) {
				if(lockType==0){
					bikeLockInfo.setBikeLockVoltage(voltage);
				}
				bikeLockInfo.setBikeLockStatus(newReq[7]);
				lockbikeService.updateLock(bikeLockInfo);
				Double lowpower = lockbikeService.getParament("lowpower");
				if(lockType==0 && null!=bikeLockInfo.getBikeLockBikeId()){
					if(voltage<=lowpower){
						Bike bike = new Bike();
						bike.setBikeId(bikeLockInfo.getBikeLockBikeId());
						//车辆低电压
						bike.setBikeAlert(2);
						lockbikeService.updateBike(bike);
					}else{
						Bike bike = new Bike();
						bike.setBikeId(bikeLockInfo.getBikeLockBikeId());
						//车辆电压正常
						bike.setBikeAlert(1);
						lockbikeService.updateBike(bike);
					}
				}
			}
//			TipsHandler.pushTips(bid,"锁ID为：" + bid + "的客户端：" + ctx2.channel().remoteAddress() + "已回复状态信息");
			loggers.info("锁ID为：" + bid + "的客户端：" + ctx2.channel().remoteAddress() + "已回复状态信息");
		}
	}

	/**
	 * 处理gps回复
	 * 
	 * @throws Exception
	 */
	public void gps() throws Exception {
		bid = LockClients.getLockChannelKey(ctx2.channel().remoteAddress().toString());
		if (null == bid || "".equals(bid)) {
			loggers.info("未与BID关联的锁" + ctx2.channel().remoteAddress() + "发来状态信息");
			sendGetBid();
		} else {
			double lat = CommandUtil.getLat(newReq);
			double lng = CommandUtil.getLng(newReq);
			double height = CommandUtil.getHeaght(newReq);
			if (0 != lat && 0 != lng) {
				loggers.info("lat:"+lat);
				loggers.info("lng:"+lng);
				loggers.info("height:"+height);
				Gps gps = PositionUtil.gps84_To_Gcj02(lat,lng);
				loggers.info(gps.getWgLat());
				loggers.info(gps.getWgLon());
				BikeLockInfo bikeLockInfo = lockbikeService.getLock(bid);
				if (null != bikeLockInfo) {
					if (0 != lat && 0 != lng) {
						bikeLockInfo.setBikeLockLat(gps.getWgLat());
						bikeLockInfo.setBikeLockLng(gps.getWgLon());
						bikeLockInfo.setBikeLockHeight(height);
					}
					// bikeLockInfo.setBikeLockBlockid(blockId);
					lockbikeService.updateLock(bikeLockInfo);
				}
				
				Bike bike = lockbikeService.getBike(bid);
				if(null!=bike){
					String latLng = gps.getWgLon() + "," + gps.getWgLat();
					String blockCode = BlockUtil.getBlockCode(latLng);
					bike.setBikeBlock(blockCode);
					//Long blockId = lockbikeService.findBlockId(blockCode);
					bike.setBikeAtitude(gps.getWgLat());
					bike.setBikeLongitude(gps.getWgLon());
					/*if(null!=blockId){
						bike.setBikeBlockId(blockId);
					}*/
					
					
					Models models = lockbikeService.getModels(bike.getBikeModelsId());
					if(models.getModelsIsfixedPoint()==1){
						//DataDet dataDet = lockbikeService.getDataDet((long)28);//获取dataDet
						Double fixed_distance = lockbikeService.getParament("fixed_distance");
						//Double errorDistance = Double.valueOf(dataDet.getDataDetVal());
						List<FixedReturn> fixedReturns = new ArrayList<FixedReturn>();
						if(null!=models.getModelsFixedReturn()&&!models.getModelsFixedReturn().equals("")){
							String[] split = SplitUtil.toSplit(models.getModelsFixedReturn());
							for(int i=0;i<split.length;i++){
								FixedReturn fixedReturn = lockbikeService.getFixed(Long.valueOf(split[i]));
								fixedReturns.add(fixedReturn);
							}
							
						}
						Integer flag = 1;
						if(null!=fixedReturns){
							for(FixedReturn fr : fixedReturns){
								double distance=DistanceUtil.LantitudeLongitudeDist(fr.getFixedReturnLng(), fr.getFixedReturnLat(), gps.getWgLon(), gps.getWgLat());
								loggers.info(distance);
								loggers.info(fixed_distance);
								//loggers.info(fr.getFixedReturnName()+":"+fr.getFixedReturnDistance());
								//if(distance<(fr.getFixedReturnDistance()+errorDistance)){
								if(distance<fixed_distance){
									bike.setBikeFixedReturnId(fr.getFixedReturnId());
									flag = 2;
								}
							}
							if(flag==1){
								bike.setBikeFixedReturnId((long)0);
							}
						}
					}else{
						bike.setBikeFixedReturnId((long)0);
					}
					
					
					lockbikeService.updateBike(bike);
				}
//				TipsHandler.pushTips(bid,"锁ID为：" + bid + "的客户端：" + ctx2.channel().remoteAddress() + "已回复定位信息");
				loggers.info("锁ID为：" + bid + "的客户端：" + ctx2.channel().remoteAddress() + "已回复gps定位信息");
			}
		}
	}
	
	/**
	 * 处理agps回复(基站定位)
	 * 
	 * @throws Exception
	 */
	public void agps() throws Exception {
		bid = LockClients.getLockChannelKey(ctx2.channel().remoteAddress().toString());
		if (null == bid || "".equals(bid)) {
			loggers.info("未与BID关联的锁" + ctx2.channel().remoteAddress() + "发来状态信息");
			sendGetBid();
		} else {
			double lat = CommandUtil.getLat(newReq);
			double lng = CommandUtil.getLng(newReq);
			if (0 != lat && 0 != lng) {
				loggers.info("lat:"+lat);
				loggers.info("lng:"+lng);
				Gps gps = PositionUtil.gps84_To_Gcj02(lat,lng);
				loggers.info(gps.getWgLat());
				loggers.info(gps.getWgLon());
				BikeLockInfo bikeLockInfo = lockbikeService.getLock(bid);
				if (null != bikeLockInfo) {
					if (0 != lat && 0 != lng) {
						bikeLockInfo.setBikeLockLat(gps.getWgLat());
						bikeLockInfo.setBikeLockLng(gps.getWgLon());
					}
					// bikeLockInfo.setBikeLockBlockid(blockId);
					lockbikeService.updateLock(bikeLockInfo);
				}
				Bike bike = lockbikeService.getBike(bid);
				if(null!=bike){
					String latLng = gps.getWgLon() + "," + gps.getWgLat();
					String blockCode = BlockUtil.getBlockCode(latLng);
					//Long blockId = lockbikeService.findBlockId(blockCode);
					bike.setBikeBlock(blockCode);
					bike.setBikeAtitude(gps.getWgLat());
					bike.setBikeLongitude(gps.getWgLon());
					/*if(null!=blockId){
						bike.setBikeBlockId(blockId);
					}*/
					
					Models models = lockbikeService.getModels(bike.getBikeModelsId());
					if(models.getModelsIsfixedPoint()==1){
						//DataDet dataDet = lockbikeService.getDataDet((long)28);//获取dataDet
						//Double errorDistance = Double.valueOf(dataDet.getDataDetVal());
						Double fixed_distance = lockbikeService.getParament("fixed_distance");
						List<FixedReturn> fixedReturns = new ArrayList<FixedReturn>();
						if(null!=models.getModelsFixedReturn()&&!models.getModelsFixedReturn().equals("")){
							String[] split = SplitUtil.toSplit(models.getModelsFixedReturn());
							for(int i=0;i<split.length;i++){
								FixedReturn fixedReturn = lockbikeService.getFixed(Long.valueOf(split[i]));
								fixedReturns.add(fixedReturn);
							}
							
						}
						Integer flag = 1;
						if(null!=fixedReturns){
							for(FixedReturn fr : fixedReturns){
								double distance=DistanceUtil.LantitudeLongitudeDist(fr.getFixedReturnLng(), fr.getFixedReturnLat(), gps.getWgLon(), gps.getWgLat());
								loggers.info(distance);
								loggers.info(fixed_distance);
								if(distance<fixed_distance){
									bike.setBikeFixedReturnId(fr.getFixedReturnId());
									flag = 2;
								}
							}
							if(flag==1){
								bike.setBikeFixedReturnId((long)0);
							}
						}
					}else{
						bike.setBikeFixedReturnId((long)0);
					}
					
					
					lockbikeService.updateBike(bike);
				}
//				TipsHandler.pushTips(bid,"锁ID为：" + bid + "的客户端：" + ctx2.channel().remoteAddress() + "已回复基站定位信息");
				loggers.info("锁ID为：" + bid + "的客户端：" + ctx2.channel().remoteAddress() + "已回复基站定位信息");
			}
		}
	}

	/**
	 * 处理开锁回复
	 * 
	 * @throws Exception
	 */
	public void unLock() throws Exception {
		bid = LockClients.getLockChannelKey(ctx2.channel().remoteAddress().toString());
		if (null == bid || "".equals(bid)) {
			loggers.info("未与BID关联的锁" + ctx2.channel().remoteAddress() + "发来开锁成功信息");
			sendGetBid();
		} else {
			BikeLockInfo bikeLockInfo = new BikeLockInfo();
			bikeLockInfo.setBikeLockStatus(1);
			bikeLockInfo.setBikeLockCode(bid);
			lockbikeService.updateLock(bikeLockInfo);
			redisService.set("r"+bid,"success",60000);
			redisService.set("open"+bid,"success",60000);
			try {
				if(""!=redisService.get(bid)&&null!=redisService.get(bid)){
					//根据bid得到当前锁的订单等信息
					String redisString = redisService.get(bid);
					//关闭jedis
					String[] split = redisString.split(",");
					//第一次开锁
					if(split[0].equals("1")||split[0]=="1"){
//						TipsHandler.pushTips(bid,"success,1,"+split[3]);
					}else if(split[0].equals("3")||split[0]=="3"){//继续骑行
						Bike bike = new Bike();
						bike.setBikeId(Long.valueOf(split[1]));
						bike.setBikeState(2);
						lockbikeService.updateBike(bike);
//						TipsHandler.pushTips(bid,"success,3,"+split[2]);
					}
					loggers.info(redisString);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				redisService.del(bid);
				redisService.closeJedis();
			}
			sendMessage.sendMessage("lock", bid);
			loggers.info("锁ID为：" + bid + "的客户端：" + ctx2.channel().remoteAddress() + "已回复开锁");
		}

	}

	/**
	 * 处理关锁回复
	 * 
	 * @throws Exception
	 */
	public void lock() throws Exception {
		bid = LockClients.getLockChannelKey(ctx2.channel().remoteAddress().toString());
		if (null == bid || "".equals(bid)) {
			loggers.info("未与BID关联的锁" + ctx2.channel().remoteAddress() + "发来关锁成功信息");
			sendGetBid();
		} else {
			BikeLockInfo bikeLockInfo = new BikeLockInfo();
			bikeLockInfo.setBikeLockStatus(0);
			bikeLockInfo.setBikeLockCode(bid);
			lockbikeService.updateLock(bikeLockInfo);
			loggers.info(bid+"的lockType为"+lockType);
			if(lockType==1){
				try{
					if(""!=redisService.get("userOpen"+bid)&&null!=redisService.get("userOpen"+bid)){
						Bike bike = lockbikeService.getBike(bid);
						if(null!=bike){
							bike.setBikeState(1);
							bike.setBikeTemporarylocktime(new Date());
							lockbikeService.updateBike(bike);
						}
						BikeLockInfo nowLock = lockbikeService.getLock(bid);
						loggers.info(bid+"的BikeLockIsFence为"+nowLock.getBikeLockIsFence());
						if(nowLock.getBikeLockIsFence()==1){
							//电子围栏使用
							if(""!=redisService.get("r2"+bid)&&null!=redisService.get("r2"+bid)){
								redisService.del("r2"+bid);
							}
							//如果新锁的回复为0，则在电子围栏内还车
							if(0 == newReq[7]){
								loggers.info("如果新锁的回复为0，则在电子围栏内还车");
								redisService.set("r2"+bid,"success");
							}
						}
						
						
					}
					loggers.info(bid+"关锁回复成功");
					ctx2.channel().writeAndFlush(Unpooled.copiedBuffer(CommandUtil.CLOSE_LOCK_OK));
				}catch(Exception e){
					e.printStackTrace();
				}finally {
					redisService.closeJedis();
				}
			}else{
				redisService.set("r"+bid,"success");
				try {
					if(""!=redisService.get(bid)&&null!=redisService.get(bid)){
						//根据bid得到当前锁的订单等信息
						String redisString = redisService.get(bid);
						String[] split = redisString.split(",");
						//临时上锁 关锁成功
						if(split[0].equals("2")||split[0]=="2"){
							Bike bike = new Bike();
							bike.setBikeId(Long.valueOf(split[1]));
							bike.setBikeState(1);
							lockbikeService.updateBike(bike);
						}else if(split[0].equals("4")||split[0]=="4"){//结束行程
							Date nowDay = new Date();
							//BigDecimal nowMoney = new BigDecimal(0);
							//根据订单ID得到订单
							BikeRentInfo rentInfo = lockbikeService.getBikeRentInfoById(Long.valueOf(split[2]));
							Bike bike = lockbikeService.getBike(bid);
							Models models = lockbikeService.getModels(bike.getBikeModelsId());
							/*Long diffMinutes = DateUtil.minuteDiff(rentInfo.getRentStarttime(), nowDay);
							if (diffMinutes > 2) { // 大于2分钟
								Long diffHours = DateUtil.hourDiff(rentInfo.getRentStarttime(), nowDay);
								BigDecimal modelsRentPrice = new BigDecimal(split[5]);
								nowMoney = nowMoney.add(modelsRentPrice.multiply(new BigDecimal(diffHours)));
							}*/
							User user = lockbikeService.getUser(rentInfo.getRentInfoUserId());
							Double nowMoney = 0.0;
							RentPrice price = null;
							if(null!=user.getUserChannelId()){
								if(user.getUserChannelId().equals(models.getModelsChannelId())){
									if(models.getModelsRentType()==1){
										price = lockbikeService.getRentPrice(models.getModelsId(), 1);//游客计费
									}else if(models.getModelsRentType()==2){
										price = lockbikeService.getRentPrice(models.getModelsId(), 2);//会员计费
									}
								}else{
									price = lockbikeService.getRentPrice(models.getModelsId(), 1);//游客计费
								}
							}else{
								price = lockbikeService.getRentPrice(models.getModelsId(), 1);//游客计费
							}
							
							if(null!=price){
									Long diffMinutes = DateUtil.minuteDiff(rentInfo.getRentStarttime(), nowDay);
									if(price.getRentPriceOption()==1){//一小时计费
										Long diffHours = DateUtil.hourDiff(rentInfo.getRentStarttime(), nowDay);
										Integer diffSeconds = (int)(diffHours / 24);
										if(diffSeconds>0){//大于24小时
											if(diffMinutes>price.getRentFreeTime()){
												diffHours = diffHours - diffSeconds*24;
												nowMoney = nowMoney + getDiffNowMoney(price.getRentPrice(),diffHours,diffMinutes);
												if(nowMoney>price.getRentPriceMax()){
													nowMoney = price.getRentPriceMax();
												}
												nowMoney = nowMoney + diffSeconds*price.getRentPriceMax();
											}
											
											
										}else{//小于24小时
											if(diffMinutes>price.getRentFreeTime()){
												nowMoney = nowMoney + getDiffNowMoney(price.getRentPrice(),diffHours,diffMinutes);
												if(nowMoney>price.getRentPriceMax()){
													nowMoney = price.getRentPriceMax();
												}
											}
										}
									}else if(price.getRentPriceOption()==2){//半小时计费
										Long halfHours = DateUtil.halfHourDiff(rentInfo.getRentStarttime(), nowDay);
										Integer haffSeconds = (int)(halfHours *0.5 / 24);
										if(haffSeconds>0){//大于24小时
											if(diffMinutes>price.getRentFreeTime()){
												halfHours = halfHours - haffSeconds*48;
												nowMoney = nowMoney + getHalfNowMoney(price.getRentPrice(),halfHours,diffMinutes);
												if(nowMoney>price.getRentPriceMax()){
													nowMoney = price.getRentPriceMax();
												}
												nowMoney = nowMoney + haffSeconds*price.getRentPriceMax();
											}
										}else{//小于24小时
											if(diffMinutes>price.getRentFreeTime()){
												nowMoney = nowMoney + getHalfNowMoney(price.getRentPrice(),halfHours,diffMinutes);
												if(nowMoney>price.getRentPriceMax()){
													nowMoney = price.getRentPriceMax();
												}
											}
											
										}
									
								}
							}
							
							rentInfo.setRentPrice(new BigDecimal(nowMoney));
							rentInfo.setRentEndtime(nowDay); // 还车时间
							//rentInfo.setRentPrice(new BigDecimal(nowMoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
							// 还车方格id
							String latLng = split[3] + "," + split[4];
							String blockCode = BlockUtil.getBlockCode(latLng);
							//Long rentEndBlockId = lockbikeService.findBlockId(blockCode);
							//rentInfo.setRentEndBlockId(rentEndBlockId);
							rentInfo.setRentEndBlock(blockCode);
							rentInfo.setRentEndlat(Double.valueOf(split[4]));
							rentInfo.setRentEndlng(Double.valueOf(split[3]));
							lockbikeService.updateRentinfo(rentInfo);
							//更改车辆经纬度
							bike.setBikeLongitude(Double.valueOf(split[3]));
							bike.setBikeAtitude(Double.valueOf(split[4]));
							bike.setBikeState(0);// 空闲中
							//bike.setBikeBlockId(rentEndBlockId);
							bike.setBikeBlock(blockCode);
							bike.setBikeLastRentTime(new Date());
							lockbikeService.updateBike(bike);
							//锁的定位
							bikeLockInfo.setBikeLockLng(Double.valueOf(split[3]));
							bikeLockInfo.setBikeLockLat(Double.valueOf(split[4]));
							lockbikeService.updateLock(bikeLockInfo);
							CommandUtil.getGPS(bid,60);
						}
						loggers.info(redisString);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}finally {
					redisService.del(bid);
					redisService.closeJedis();
				}
				sendMessage.sendMessage("lock", bid);
			}
			loggers.info("锁ID为：" + bid + "的客户端：" + ctx2.channel().remoteAddress() + "已回复");
		}
		

	}
	
	/**
	 *一小时计费
	 * @param modelsRentPrice
	 * @param diffHours
	 * @param diffMinutes
	 * @throws Exception
	 */
	public Double getDiffNowMoney(String modelsRentPrice,Long diffHours,Long diffMinutes) throws Exception{
		Double nowMoney = 0.0;
		JSONArray priceArray = new JSONArray(modelsRentPrice);//将字符串转化为jsonArray
		for(int i=0 ; i < priceArray.length() ;i++){
			//获取每一个JsonObject对象
		    JSONObject myjObject = priceArray.getJSONObject(i);
		    Double fromTime = myjObject.optDouble("fromTime",-1);
		    Double toTime = myjObject.optDouble("toTime",-1);
		    Double rentPrice = myjObject.getDouble("rentPrice");
		    if(toTime==-1){//-1为最后时间段
				nowMoney = nowMoney+rentPrice*diffHours;
				break;
		    }else{
		    	if (diffMinutes > toTime*60) { // 大于当前时间段最大时间
		    		nowMoney = nowMoney+rentPrice;
		    		diffHours = (long)(diffHours - (toTime-fromTime));
					continue;
				}else{
					nowMoney = nowMoney+rentPrice*diffHours;
					break;
				}
		    }
		}
		return nowMoney;
	}
	
	/**
	 *半小时计费
	 * @param modelsRentPrice
	 * @param diffHours
	 * @param diffMinutes
	 * @throws Exception
	 */
	public Double getHalfNowMoney(String modelsRentPrice,Long halfHours,Long halfMinutes) throws Exception{
		Double nowMoney = 0.0;
		JSONArray priceArray = new JSONArray(modelsRentPrice);//将字符串转化为jsonArray
		for(int i=0 ; i < priceArray.length() ;i++){
			//获取每一个JsonObject对象
		    JSONObject myjObject = priceArray.getJSONObject(i);
		    Double fromTime = myjObject.optDouble("fromTime",-1);
		    Double toTime = myjObject.optDouble("toTime",-1);
		    Double rentPrice = myjObject.getDouble("rentPrice");
		    if(toTime==-1){//-1为最后时间段
				nowMoney = nowMoney+rentPrice*halfHours;
				break;
		    }else{
		    	if (halfMinutes > toTime*30) { // 大于当前时间段最大时间
		    		nowMoney = nowMoney+rentPrice;
		    		halfHours = (long)(halfHours - (toTime-fromTime));
					continue;
				}else{
					nowMoney = nowMoney+rentPrice*halfHours;
					break;
				}
		    }
		}
		return nowMoney;
	}

	/**
	 * 处理关机回复
	 * 
	 * @throws Exception
	 */
	public void powerOff() throws Exception {
		bid = LockClients.getLockChannelKey(ctx2.channel().remoteAddress().toString());
		if (null == bid || "".equals(bid)) {
			loggers.info("未与BID关联的锁" + ctx2.channel().remoteAddress() + "发来关机确认信息");
			sendGetBid();
		} else {
			BikeLockInfo bikeLockInfo = lockbikeService.getLock(bid);
			if (null != bikeLockInfo) {
				bikeLockInfo.setBikeLockPower(newReq[7]);
				lockbikeService.updateLock(bikeLockInfo);
			}
			if (0 == newReq[7])
				loggers.info("锁ID为：" + bid + "的客户端：" + ctx2.channel().remoteAddress() + "已回复关机确认信息");
			else if (1 == newReq[7])
				loggers.info("锁ID为：" + bid + "的客户端：" + ctx2.channel().remoteAddress() + "已回复关机失败信息");
		}
	}

	/**
	 * 处理握手回复
	 * 
	 * @throws Exception
	 */
	public void domainPwd() throws Exception {
		bid = LockClients.getLockChannelKey(ctx2.channel().remoteAddress().toString());
		if (null == bid || "".equals(bid)) {
			loggers.info("未与BID关联的锁" + ctx2.channel().remoteAddress() + "发来域名配置握手确认信息");
			sendGetBid();
		} else {
			BikeLockInfo bikeLockInfo = lockbikeService.getLock(bid);
			if (null != bikeLockInfo) {
				int state = newReq[7];
				if (0 == state)
					bikeLockInfo.setBikeLockDominState(0);// 开启域名配置功能成功
				else if (1 == state) {
					bikeLockInfo.setBikeLockDominState(4);// 密码错误
				}
				lockbikeService.updateLock(bikeLockInfo);
			}
//			TipsHandler.pushTips(bid,"锁ID为：" + bid + "的客户端：" + ctx2.channel().remoteAddress() + "已回复域名配置握手确认信息");
			loggers.info("锁ID为：" + bid + "的客户端：" + ctx2.channel().remoteAddress() + "已回复域名配置握手确认信息");
		}
	}

	/**
	 * 配置域名回复
	 * 
	 * @throws Exception
	 */
	public void setDomain() throws Exception {
		bid = LockClients.getLockChannelKey(ctx2.channel().remoteAddress().toString());
		if (null == bid || "".equals(bid)) {
			loggers.info("未与BID关联的锁" + ctx2.channel().remoteAddress() + "发来域名配置确认信息");
			sendGetBid();
		} else {
			BikeLockInfo bikeLockInfo = lockbikeService.getLock(bid);
			if (null != bikeLockInfo) {
				int state = newReq[7];
				if (0 == state)
					bikeLockInfo.setBikeLockDominState(2);// 成功
				else if (1 == state)
					bikeLockInfo.setBikeLockDominState(3);// 失败
				lockbikeService.updateLock(bikeLockInfo);
			}
//			TipsHandler.pushTips(bid,"锁ID为：" + bid + "的客户端：" + ctx2.channel().remoteAddress() + "已回复域名配置确认信息");
			loggers.info("锁ID为：" + bid + "的客户端：" + ctx2.channel().remoteAddress() + "已回复域名配置确认信息");
		}
	}

	/**
	 * 处理域名握手密码修改回复
	 * 
	 * @throws Exception
	 */
	public void setDomainPwd() throws Exception {
		bid = LockClients.getLockChannelKey(ctx2.channel().remoteAddress().toString());
		if (null == bid || "".equals(bid)) {
			loggers.info("未与BID关联的锁" + ctx2.channel().remoteAddress() + "发来域名配置确认信息");
			sendGetBid();
		} else {
			BikeLockInfo bikeLockInfo = lockbikeService.getLock(bid);
			if (null != bikeLockInfo) {
				int state = newReq[7];
				if (0 == state)
					bikeLockInfo.setBikeLockDomainPwdState(1);// 成功
				else if (1 == state)
					bikeLockInfo.setBikeLockDomainPwdState(2);// 旧密码错误
				else if (2 == state)
					bikeLockInfo.setBikeLockDomainPwdState(3);// 失败
				lockbikeService.updateLock(bikeLockInfo);
			}
//			TipsHandler.pushTips(bid,"锁ID为：" + bid + "的客户端：" + ctx2.channel().remoteAddress() + "已回复域名握手密码修改确认信息");
			loggers.info("锁ID为：" + bid + "的客户端：" + ctx2.channel().remoteAddress() + "已回复域名握手密码修改确认信息");
		}
	}

	/**
	 * 处理回复域名配置密码信息
	 * 
	 * @throws Exception
	 */
	public void getDomainPwd() throws Exception {
		bid = LockClients.getLockChannelKey(ctx2.channel().remoteAddress().toString());
		if (null == bid || "".equals(bid)) {
			loggers.info("未与BID关联的锁" + ctx2.channel().remoteAddress() + "发来域名配置密码信息");
			sendGetBid();
		} else {
			BikeLockInfo bikeLockInfo = lockbikeService.getLock(bid);
			if (null != bikeLockInfo) {
				bikeLockInfo.setBikeLockDomainPwd(CommandUtil.getPwd(newReq));
				lockbikeService.updateLock(bikeLockInfo);
			}
//			TipsHandler.pushTips(bid,"锁ID为：" + bid + "的客户端：" + ctx2.channel().remoteAddress() + "已回复域名配置密码信息");
			loggers.info("锁ID为：" + bid + "的客户端：" + ctx2.channel().remoteAddress() + "已回复域名配置密码信息");
		}
	}

	/**
	 * 断开连接，置状态为未在线
	 * @throws Exception
	 */
	public void stopConnection() throws Exception{
		if (null!=ctx2 && ""!=bid && null!=bid && null!=LockClients.getLockChannel(bid)&&null!=LockClients.getLockChannel(bid).channel().remoteAddress().toString() && ctx2.channel().remoteAddress().toString()
				.equals(LockClients.getLockChannel(bid).channel().remoteAddress().toString())){
			if(ctx2.channel().remoteAddress().toString()
					.equals(LockClients.getLockChannel(bid).channel().remoteAddress().toString())){
				LockClients.removeLockChannel(bid);
			}
			BikeLockInfo bikeLockInfo = lockbikeService.getLock(bid);
			bikeLockInfo.setBikeLockState(0);
			lockbikeService.updateLock(bikeLockInfo);
		}
	}
	
	/**
	 * 开锁失败
	 * @throws Exception
	 */
	public void unlockfail() throws Exception{
		try {
			if(""!=redisService.get(bid)&&null!=redisService.get(bid)){
				//获取到当前车锁属于的订单信息
				String redisString = redisService.get(bid);
//			redisService.closeJedis();
				String[] dataUnlock = redisString.split(",");
				if(dataUnlock[0].equals("1")||dataUnlock[0]=="1"){
					//还原车的状态
					Bike bike = new Bike();
					bike.setBikeId(Long.valueOf(dataUnlock[2]));
					bike.setBikeState(0);
					lockbikeService.updateBike(bike);
					//还原用户状态
					User user = new User();
					user.setUserId(Long.valueOf(dataUnlock[1]));
					user.setUserState(0);
					lockbikeService.updateUser(user);
					//还原车锁状态
					BikeLockInfo bikeLockInfo = new BikeLockInfo();
					bikeLockInfo.setBikeLockCode(bid);
					bikeLockInfo.setBikeLockStatus(0);
					lockbikeService.updateLock(bikeLockInfo);
					//还原订单状态
					BikeRentInfo bikeRentInfo = new BikeRentInfo();
					bikeRentInfo.setRentInfoId(Long.valueOf(dataUnlock[3]));
					//订单失败
					bikeRentInfo.setRentState(3);
					lockbikeService.updateRentinfo(bikeRentInfo);
				}
			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			//关闭jedis
			redisService.closeJedis();
		}
	}
	
	/**
	 * 关锁失败
	 * @throws Exception
	 */
	public void lockfail() throws Exception{
		try {
			if(""!=redisService.get(bid)&&null!=redisService.get(bid)){
				//获取到当前车锁属于的订单信息
				String redisString = redisService.get(bid);
//			redisService.closeJedis();
				String[] dataUnlock = redisString.split(",");
				if(dataUnlock[0].equals("2")||dataUnlock[0]=="2"){
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			//关闭jedis
			redisService.closeJedis();
		}
	}
	
	
	/**
	 * 处理版本信息回复
	 * 
	 * @throws Exception
	 */
	public void getVersion() throws Exception {
		bid = LockClients.getLockChannelKey(ctx2.channel().remoteAddress().toString());
		if (null == bid || "".equals(bid)) {
			loggers.info("未与BID关联的锁" + ctx2.channel().remoteAddress() + "发来域名配置确认信息");
			sendGetNBid();
		}else{
			//更新车锁版本信息
			String version = newReq[7]+"."+newReq[8]+"."+newReq[9];
			loggers.info(bid+"的版本信息为："+version);
			BikeLockInfo bikeLockInfo = new BikeLockInfo();
			bikeLockInfo.setBikeLockCode(bid);
			bikeLockInfo.setBikeLockVersion(version);
			lockbikeService.updateLock(bikeLockInfo,1);
		}
	}
	
}