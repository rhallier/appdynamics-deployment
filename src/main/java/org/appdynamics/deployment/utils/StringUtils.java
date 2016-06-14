package org.appdynamics.deployment.utils;

import java.util.Collection;

public class StringUtils {

	public static boolean isInList(String entry, String... list) {
		boolean result=false;
		if(list!=null)
			for(String value : list) {
				if(entry.equalsIgnoreCase(value)) {
					result=true;
					break;
				}
			}
		return result;
	}
	
	public static String join(String delimiter, String... values) {
		StringBuilder result = new StringBuilder();
		if(values!=null)
			for(String s : values) {
				if(result.length()!=0)
					result.append(delimiter);
				result.append(s);
			}
		return result.toString();
	}

	public static String join(String delimiter, Collection<String> values) {
		StringBuilder result = new StringBuilder();
		if(values!=null)
			for(String s : values) {
				if(result.length()!=0)
					result.append(delimiter);
				result.append(s);
			}
		return result.toString();
	}

	public static boolean isNotBlank(String value) {
		if(value==null || value.trim().length()==0)
			return false;
		else
			return true;
	}
}
