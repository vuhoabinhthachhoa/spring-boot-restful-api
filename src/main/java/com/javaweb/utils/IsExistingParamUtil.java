package com.javaweb.utils;

import java.util.List;

public class IsExistingParamUtil {
	
	public static <T> boolean isExistingObjectParam (Object obj) {
		return obj != null && !obj.equals("");
	}
	
	public static <T> boolean isExistingListParam (List<T> list) {
		return list != null && !list.isEmpty();
	}
	
}
