package org.util.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

public class Subscriber extends JedisPubSub {

    private static Logger logger = LoggerFactory.getLogger(Subscriber.class);

    
    /**
     * 取得订阅的消息后的处理
     */
    @Override
    public void onMessage(String channel, String message) {
    	System.out.println(123);
        logger.info("Message received. Channel: {}, Msg: {}", channel, message);
    }

    
    /**
     * 取得按表达式的方式订阅的消息后的处理
     */
    @Override
    public void onPMessage(String pattern, String channel, String message) {

    }

    
    /**
     * 初始化订阅时候的处理   
     */
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {

    }

    
    /**
     * 取消订阅时候的处理 
     */
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {

    }

    
    /**
     * 取消按表达式的方式订阅时候的处理
     */
    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {

    }

    /**
     * 初始化按表达式的方式订阅时候的处理 
     */
    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {

    }
}