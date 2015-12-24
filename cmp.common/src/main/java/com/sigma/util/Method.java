package com.sigma.util;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

@Deprecated
/**
 * TODO: 并到,分散到该在的工具类中
 * @author XuGang
 *
 */
public class Method {

	/**
	 * 若传入参数为null，则转换成""
	 * 
	 * @param obj
	 * @return String
	 */
	public static String NullToBlank(Object obj) {
		if (obj == null || "".equals(obj)) {
			return "";
		}

		return obj.toString();
	}

	/**
	 * 若传入参数为null，则转换成0 参数：obj 返回：int
	 */
	public static int NullToZero(Integer obj) {

		if (obj == null) {
			return 0;
		}

		return obj;
	}

	/**
	 * 判断String是否为空或者"" 参数：String 返回：boolean
	 */
	public static boolean NullOrBlank(Object obj) {

		if (obj == null || "".equals(obj.toString().trim())) {
			return true;
		} else
			return false;
	}

	/**
	 * 判断String的长度是否超过指定长度 参数：String,length 返回：boolean
	 */
	public static boolean limitLength(String obj, int length) {

		if (obj == null || "".equals(obj.trim())) {
			return true;
		} else if (obj.length() > length) {
			return true;
		} else
			return false;
	}

	/**
	 * MD5加密
	 * 
	 * @throws Exception
	 */
	public static String codeStringByMD5(String string) throws Exception {

		String result = "";
		if (NullOrBlank(string)) {
			return result;
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(string.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString().substring(3, 13);// 取10位
			System.out.println("MD5 result: " + result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
}
