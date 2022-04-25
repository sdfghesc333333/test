package com.asia.leadsgen.test.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class SortAndDirUtils {

	private static String sortString(String st) {
		for (int i = 0; i < st.length(); i++) {
			if (String.valueOf(st.charAt(i)).equals("_")) {
				String upperCase = String.valueOf(st.charAt(i + 1)).toUpperCase();
				st = st.replace(st.substring(i, i + 2), upperCase);
			}
		}
		return st;
	}

	public static Pageable sortAndDir(int page, int pageSize, String sort, String dir) {
		if (dir.equals("asc")) {
			return PageRequest.of(page - 1, pageSize, Sort.by(sortString(sort)).ascending());
		} else {
			return PageRequest.of(page - 1, pageSize, Sort.by(sortString(sort)).descending());
		}
	}
}
