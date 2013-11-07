package com.audit.common;

import java.util.Iterator;
import java.util.List;
import com.audit.entity.Function;

/**
 * 全局变量设置
 * 
 * @param
 * @return
 */
public class Global {

	public static List<Function> FunctionList = null;

	public static boolean FunctionListEquals(String url) {
		for (Iterator<Function> i = FunctionList.iterator(); i.hasNext();) {
			if (i.next().getMethod().indexOf(url) != -1) {
				return true;
			}
		}
		return false;
	}
}
