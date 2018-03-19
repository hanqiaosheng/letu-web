package org.websocket;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.annotation.Resource;

import org.entity.dto.Bike;
import org.entity.dto.BikeLockInfo;
import org.service.lock.self.LockBikeService;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class TipsHandler extends TextWebSocketHandler {

	private static CopyOnWriteArraySet<WebSocketSession> webSocketSessionSet = new CopyOnWriteArraySet<WebSocketSession>();
	
	 private static ConcurrentHashMap<String,WebSocketSession> httpSessionWebSocketMap = new ConcurrentHashMap<String,WebSocketSession>();
	 
	 @Resource
	 LockBikeService lockBikeService;
	 
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 接收到客户端消息时调用
		System.out.println(message.getPayload());
		if(message.getPayload()!=null&&message.getPayload()!=""){
			String fromflag = message.getPayload().split(",")[0];
			if(fromflag.equals("cms")){
//				httpSessionWebSocketMap.put(message.getPayload().split(",")[1], session);
			}else if(fromflag.equals("weixin")){
				Bike bike = lockBikeService.getBikeBybikeCode(message.getPayload().split(",")[1]);
				BikeLockInfo bikeLockInfo = lockBikeService.getBikeLockInfo(bike.getBikeId());
				httpSessionWebSocketMap.put(bikeLockInfo.getBikeLockCode(), session);
			}
//			pushTips();
			//pushTips(message.getPayload());
		}
		
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 与客户端完成连接后调用
		
		webSocketSessionSet.add(session);
		System.out.println("连接数："+webSocketSessionSet.size());
//		System.out.println("afterConnectionEstablished");
//		System.out.println("getId:" + session.getId());
//		System.out.println("getLocalAddress:" + session.getLocalAddress().toString());
//		System.out.println("getTextMessageSizeLimit:" + session.getTextMessageSizeLimit());
//		System.out.println("getUri:" + session.getUri().toString());
//		System.out.println("getPrincipal:" + session.getPrincipal());

	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// 消息传输出错时调用
		//System.out.println("handleTransportError");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// 一个客户端连接断开时关闭
		webSocketSessionSet.remove(session);
		System.out.println("afterConnectionClosed");
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 后台websocket连接消息推送
	 * @param key
	 * @param mess
	 * @throws Exception
	 */
	public static void pushTips(String key , String mess) throws Exception {
//		for (WebSocketSession s : webSocketSessionSet){
			WebSocketSession session = httpSessionWebSocketMap.get(key);
			if(null!=session&&session.isOpen()){
				session.sendMessage(new TextMessage((mess).getBytes()));
			}
//		}
	}
	
}