package com.asia.leadsgen.test.util;

public class StringUtils {

	public static String sortString(String st) {
		for (int i = 0; i < st.length(); i++) {
			if (String.valueOf(st.charAt(i)).equals("_")) {
				String upperCase = String.valueOf(st.charAt(i + 1)).toUpperCase();
				st = st.replace(st.substring(i, i + 2), upperCase);
			}
		}
		return st;
	}
}
