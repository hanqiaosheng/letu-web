package org.util.redis;

import java.io.Serializable;

public class ListenMessage {
	
    public void handleMessage(Serializable message){
    	//当前通知bid
    	String bid = (String) message;
    	System.out.println(bid+"唤醒");
    	Object object = ThreadMapUtil.threadMap.get(bid);
    	//线程唤醒
    	if(null!=object){
    		synchronized (object) {
    			object.notify();
    			ThreadMapUtil.threadMap.remove(bid);
    		}
    	}
//    	thread.interrupt();
    }
}