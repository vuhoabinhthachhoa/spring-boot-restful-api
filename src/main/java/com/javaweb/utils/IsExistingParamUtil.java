package com.javaweb.utils;

import java.util.List;

public class IsExistingParamUtil {
	
	public static boolean isExistingStringParam (String value) {
		return value != null && !value.equals("");
	}
	
	public static <T> boolean isExistingListParam (List<T> list) {
		return list != null && !list.isEmpty();
	}
	
}
