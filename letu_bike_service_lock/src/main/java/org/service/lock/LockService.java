package org.service.lock;
/**
 * 发送指令接口
 * @author DRH
 *
 */
public interface LockService {
	/**
	 * 发送关锁指令
	 * @param lockId
	 * @return
	 * @throws Exception
	 */
	public boolean sendLockMessage(String lockId) throws Exception;
	/**
	 * 发送开锁指令
	 * @param lockId
	 * @return
	 * @throws Exception
	 */
	public boolean sendUnlockMessage(String lockId) throws Exception;
	/**
	 * 发送获取状态信息指令
	 * @param lockId
	 * @return
	 * @throws Exception
	 */
	public boolean sendStateMessage(String lockId) throws Exception;
	/**
	 * 发送定位指令
	 * @param lockId
	 * @return
	 * @throws Exception
	 */
	public boolean sendLocationMessage(String lockId) throws Exception;
	/**
	 * 发送获取域名配置握手密码指令
	 * @param lockId
	 * @return
	 * @throws Exception
	 */
	public boolean sendGetDomainPwdMessage(String lockId) throws Exception;
	/**
	 * 发送关机指令
	 * @param lockId
	 * @return
	 * @throws Exception
	 */
	public boolean sendPowerOffMessage(String lockId,Integer time) throws Exception;
	/**
	 * 发送域名配置握手指令
	 * @param lockId
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public boolean sendDomainPwdMessage(String lockId,String pwd) throws Exception;
	/**
	 * 发送设置域名握手密码指令
	 * @param lockId
	 * @param opwd
	 * @param npwd
	 * @return
	 * @throws Exception
	 */
	public boolean sendSetDomianPwdMessage(String lockId,String opwd,String npwd) throws Exception;
	/**
	 * 发送设置域名指令
	 * @param lockId
	 * @param domain
	 * @param port
	 * @return
	 * @throws Exception
	 */
	public boolean sendSetDomianMessage(String lockId,String domain,String port) throws Exception;
	
	/**
	 * 判断当前车锁连接是否存在
	 * @param lockId
	 * @return
	 * @throws Exception
	 */
	public boolean judgeLockConnet(String lockId) throws Exception;
	
	/**
	 * 车锁版本
	 * @param lockId
	 * @return
	 * @throws Exception
	 */
	public boolean getVersion(String lockId) throws Exception;
	
	/**
	 * 发送升级指令
	 * @param lockId
	 * @return
	 * @throws Exception
	 */
	public boolean sendUpLock(String lockId,int version,int image_size,String image_crc) throws Exception;
	
	/**
	 * 发送文件指令
	 * @param lockId
	 * @return
	 * @throws Exception
	 */
	public boolean sendUpLockFile(String lockId,int i,String filePath,int size) throws Exception;
}
