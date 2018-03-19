package org.service.lock.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.dao.BikeLockInfoMapper;
import org.entity.dto.BikeLockInfo;
import org.entity.dto.BikeLockInfoExample;
import org.entity.dto.BikeLockInfoExample.Criteria;
import org.server.lock.LockServerHandler;
import org.service.lock.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.util.lock.CommandUtil;
import org.util.redis.RedisService;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
/**
 * 发送指令实现类
 * @author DRH
 *
 */
@Service("lockService")
public class LockServiceImpl implements LockService {
	

	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(LockServiceImpl.class);
	
	@Resource
	BikeLockInfoMapper bikeLockInfoMapper;
	
	/**封装之后，实行单例模式，不用每次开关*/
	@Autowired  
    private RedisService redisService;

	@Override
	public boolean sendLockMessage(String lockId) throws Exception {
		// TODO Auto-generated method stub
		if (CommandUtil.lock(lockId))
			return true;
		else
			return false;
	}

	@Override
	public boolean sendUnlockMessage(String lockId) throws Exception {
		// TODO Auto-generated method stub
		if (CommandUtil.unLock(lockId)) {
					//开锁重试机制
//					BikeLockInfoExample example = new BikeLockInfoExample();
//					Criteria criteria = example.createCriteria();
//					criteria.andBikeLockCodeEqualTo(lockId);
//					List<BikeLockInfo> list = bikeLockInfoMapper.selectByExample(example);
//					if(list.size()>0){
//						//新锁启用开锁重发机制
//						if(!"".equals(list.get(0).getBikeLockType())&&
//								null!=list.get(0).getBikeLockType() && 
//								list.get(0).getBikeLockType()==1){
//							class MyThread extends Thread{
//								String bid;
//							    public MyThread(String bid){//定义带参数的构造函数,达到初始化线程内变量的值
//							       this.bid=bid;
//							    }
//								@Override
//								public void run() {
//									try {
//										int i=0;
//										while (i<2) {
//											Thread.sleep(5000);
//											if(""!=redisService.get("open"+bid)&&null!=redisService.get("open"+bid)){
//												break;
//											}else{
//												CommandUtil.unLock(bid);
//											}
//											i++;
//											loggers.info(bid+"重发"+i);
//										}
//								    } catch (Exception e) {
//								    	// TODO Auto-generated catch block
//								    	e.printStackTrace();
//									}finally {
//										redisService.del("open"+bid);
//										redisService.closeJedis();
//									}
//								}
//							}
//							MyThread myThread = new MyThread(lockId);
//							myThread.start();
//						}
//					}
				return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean sendStateMessage(String lockId) throws Exception {
		// TODO Auto-generated method stub
		if (CommandUtil.getStatus(lockId))
			return true;
		else
			return false;
	}

	@Override
	public boolean sendLocationMessage(String lockId) throws Exception {
		// TODO Auto-generated method stub
		if (CommandUtil.getGPS(lockId, 60))
			return true;
		else
			return false;
	}

	@Override
	public boolean sendGetDomainPwdMessage(String lockId) throws Exception {
		// TODO Auto-generated method stub
		if (CommandUtil.getDomainPwd(lockId))
			return true;
		else
			return false;
	}

	@Override
	public boolean sendPowerOffMessage(String lockId,Integer time) throws Exception {
		// TODO Auto-generated method stub
		if (CommandUtil.powerOff(lockId, time))
			return true;
		else
			return false;
	}

	@Override
	public boolean sendDomainPwdMessage(String lockId, String pwd) throws Exception {
		// TODO Auto-generated method stub
		if (CommandUtil.domainPwd(lockId, pwd))
			return true;
		else
			return false;
	}

	@Override
	public boolean sendSetDomianPwdMessage(String lockId, String opwd, String npwd) throws Exception {
		// TODO Auto-generated method stub
		if (CommandUtil.setDomianPwd(lockId, opwd, npwd))
			return true;
		else
			return false;
	}

	@Override
	public boolean sendSetDomianMessage(String lockId, String domain, String port) throws Exception {
		// TODO Auto-generated method stub
		if (CommandUtil.setDomian(lockId, domain, port))
			return true;
		else
			return false;
	}

	@Override
	public boolean judgeLockConnet(String lockId) throws Exception {
		if (CommandUtil.judge(lockId))
			return true;
		else
			return false;
	}

	@Override
	public boolean getVersion(String lockId) throws Exception {
		if (CommandUtil.getVersion(lockId))
			return true;
		else
			return false;
	}

	@Override
	public boolean sendUpLock(String lockId,int version,int image_size,String image_crc) throws Exception {
		// TODO Auto-generated method stub
		if (CommandUtil.sendVersion(lockId, version, image_size, image_crc))
			return true;
		else
			return false;
	}
	
	@Override
	public boolean sendUpLockFile(String lockId,int i,String filePath,int size) throws Exception {
		// TODO Auto-generated method stub
		if (CommandUtil.sendFile(lockId, i, filePath, size))
			return true;
		else
			return false;
	}

}
