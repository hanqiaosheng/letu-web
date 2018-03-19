package org.util;

public class PageUtil {
	public static Integer size = 10;
	public static Integer weixinsize = 10;

	public static Integer getTotalPage(Integer totalCount) {

		if (totalCount % size == 0) {
			return totalCount / size;
		}
		return totalCount / size + 1;
	}
	public static Integer getWxTotalPage(Integer totalCount) {

		if (totalCount % weixinsize == 0) {
			return totalCount / weixinsize;
		}
		return totalCount / weixinsize + 1;
	}

	public static Integer getStart(Integer pageIndex) {
		return (pageIndex - 1) * size;
	}
	
	public static Integer getWeixinStart(Integer pageIndex) {
		return (pageIndex - 1) * weixinsize;
	}
}
