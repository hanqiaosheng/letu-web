package org.util.redis;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisTemplate;
/**
 * 消息模板
 * @author hellopeace
 *
 */
public class SendMessage {

    private RedisTemplate<String, Object> redisTemplate;


    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }



    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }



    public void sendMessage(String channel, Serializable message) {
        redisTemplate.convertAndSend(channel, message);
    }
}