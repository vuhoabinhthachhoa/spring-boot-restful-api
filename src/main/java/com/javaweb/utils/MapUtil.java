package com.javaweb.utils;

import java.util.Map;

public class MapUtil {
	public static <T> T getObject(Map<String, Object> params, String key, Class<T> tClass) {
		Object obj = params.getOrDefault(key, null); // V getOrDefault(Object key, V defaultValue) if the key is not found, return default value
		
		if(obj != null) {
			if(tClass.getTypeName().equals("java.lang.Long")) {
				obj = Long.valueOf(obj.toString());
			}
			else if(tClass.getTypeName().equals("java.lang.String")) {
				obj = obj.toString();
			}
		}
		
		return tClass.cast(obj);
		
	}
}
