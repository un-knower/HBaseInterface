package com.min.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: HbaseUntil
 * @Description: TODO
 * @author: han
 * @date: 2017年11月4日 下午4:44:54
 */
public class HbaseUtils {
	/*
	 * 转换rowkey,将传入的身份证和平台ID处理为rowkey
	 */
	public static String transformRowkey(String idcard, String siteid) {
		// 去掉身份证最后一位
		String str = idcard.substring(0, idcard.length() - 1);
		// 反转
		String newStr = new StringBuilder(str).reverse().toString();
		// 身份证号加上平台id
		long keyTmp = 0;
		try {
			keyTmp = Long.valueOf(newStr) + Long.valueOf(siteid);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return idcard.substring(idcard.length() - 1) + String.valueOf(keyTmp);
	}

	/*
	 * java属性名转hbase表列名
	 */
	public static String switchParam(String name) {
		if (name.matches("[a-z]+[A-Z][a-z]+([A-Z][a-z]+)*")) {
			Pattern pattern = Pattern.compile("[A-Z]");
			Matcher matcher = pattern.matcher(name);
			while (matcher.find()) {
				String old = matcher.group();
				String ne = matcher.group().toLowerCase();
				name = name.replaceAll(old, "_" + ne);
			}
		}
		return name;
	}
}
