//package org.task;
//
//import java.util.Map;
//
//import org.server.lock.LockClients;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.util.lock.CommandUtil;
//
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelHandlerContext;
//
//@Component
//public class LockTask {
//	@Scheduled(fixedDelay = 5000)
//	public void doSomething() {
//		System.out.println("定时获取所有已经连接的锁的状态...");
//		Map<String, ChannelHandlerContext> locks = LockClients.getChannels();
//		if (null != locks && 0 != locks.size())
//			for (Map.Entry<String, ChannelHandlerContext> entry : locks.entrySet()) {
//				entry.getValue().writeAndFlush(Unpooled.copiedBuffer(CommandUtil.GETSTATUS));
//			}
//		else{
//			System.out.println("暂未有硬件连接服务器...");
//		}
//		System.out.println("已经向所有已连接上的锁发送状态请求...");
//	}
//}
