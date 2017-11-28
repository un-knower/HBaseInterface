package com.min.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: HbaseUtils
 * @Description: TODO
 * @author: han
 * @date: 2017年11月4日 下午4:44:54
 */
public class HbaseUtils {
	/*
	 * 转换rowkey,将传入的身份证和平台ID处理为rowkey
	 */
	public static String transformRowkey(String idcard, String siteid, String mobile) {
		//long keyTmp = 0;
		try {
			// 去掉身份证最后一位
			// String str = idcard.substring(0, idcard.length() - 1);
			// 反转
			String newStr = new StringBuilder(mobile).reverse().toString();
			// 身份证号加上平台id
			// keyTmp = Long.valueOf(newStr) + Long.valueOf(siteid) + Long.valueOf(mobile);
			return newStr + idcard + siteid;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}

	}

	/*
	 * java属性名转hbase表列名 特别注意：当类属性名采用驼峰命名法后，此方法返回为每个大写字母前面加下划线
	 * 如果不需要返回下划线，类属性名不要采用驼峰命名法 举例：addTime 返回 add_time addtime 返回 addtime
	 */
	public static String switchParam(String name) {
		// System.out.println("hjbghj:"+name);
		if (name.matches("[a-z]+[A-Z][a-z]+([A-Z][a-z]+)*")) {
			Pattern pattern = Pattern.compile("[A-Z]");
			Matcher matcher = pattern.matcher(name);
			while (matcher.find()) {
				String old = matcher.group();
				String ne = matcher.group().toLowerCase();
				name = name.replaceAll(old, "_" + ne);
			}
		}
		return name.toUpperCase();
	}

	// 统一设置Response
	public static void setResponse(HttpServletResponse response) {
		response.setContentType("text/plain;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	}
	// 测试
	// public static void main(String[] args) {
	// System.out.println();
	// }
}
