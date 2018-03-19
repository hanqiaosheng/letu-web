package org.server.lock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.ChannelHandlerContext;
/**
 * 
 * @author DRH
 *
 */
public class LockClients {
	private static Map<String, ChannelHandlerContext> map = new ConcurrentHashMap<>();
	private static Map<String, String> key = new ConcurrentHashMap<>();
	private static Map<String, Integer> typeMap = new ConcurrentHashMap<>();

	public static void addLockChannel(String lockId, ChannelHandlerContext lock_channel) {
		map.put(lockId, lock_channel);
	}

	public static Map<String, ChannelHandlerContext> getChannels() {
		return map;
	}

	public static ChannelHandlerContext getLockChannel(String lockId) {
		return map.get(lockId);
	}

	public static void removeLockChannel(String lockId) {
		map.remove(lockId);
	}

	public static void addLockChannelKey(String address, String lockId) {
		key.put(address, lockId);
	}

	public static Map<String, String> getChannelKeys() {
		return key;
	}

	public static String getLockChannelKey(String address) {
		return key.get(address);
	}

	public static void removeLockChanneKey(String address) {
		key.remove(address);
	}
	
	public static void addType(String lockId, Integer type) {
		typeMap.put(lockId, type);
	}

	public static Map<String, Integer> getTypeMap() {
		return typeMap;
	}

	public static Integer getType(String lockId) {
		return typeMap.get(lockId);
	}

	public static void removeLockType(String lockId) {
		typeMap.remove(lockId);
	}
}
