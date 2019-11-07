package com.aiyun.common.manager;

import java.util.HashMap;
import java.util.Map;

public class CacheManager {

	static Map mapObject = new HashMap();

	public static Object get(String key) {
		return mapObject.get(key);
	}

	public static void put(String key, Object cacheObj) {
		mapObject.put(key, cacheObj);
	}

	public static void removeListRight(String key) {
		//
	}

	public static void clearAllCache() {
		mapObject = new HashMap();
	}

	public static void clearCache(String key) {
		mapObject.remove(key);
	}
}
