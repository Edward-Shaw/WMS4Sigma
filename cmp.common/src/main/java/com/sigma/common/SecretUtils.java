package com.sigma.common;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SecretUtils {

	// 定义加密算法，有DES、DESede(即3DES)、Blowfish
	private static final String Algorithm = "DESede";

	public SecretUtils() {
		
	}

	/**
	 * 加密方法
	 * 
	 * @param src
	 *            源数据的字节数组
	 * @return
	 */
	public static byte[] encryptMode(byte[] src, String password) {
		try {
			SecretKey deskey = new SecretKeySpec(build3DesKey(password),
					Algorithm); // 生成密钥
			Cipher c1 = Cipher.getInstance(Algorithm); // 实例化负责加密/解密的Cipher工具类
			c1.init(Cipher.ENCRYPT_MODE, deskey); // 初始化为加密模式
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密函数
	 * 
	 * @param src
	 *            密文的字节数组
	 * @return
	 */
	public static byte[] decryptMode(byte[] src, String password) {
		try {
			SecretKey deskey = new SecretKeySpec(build3DesKey(password),
					Algorithm);
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey); // 初始化为解密模式
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 用base64编码解码方式将Token在String和Byte数组中转换。
	 * 以下两个方法也是最终加密、解密时要调用的方法！
	 */
	public static String DataEncrypt(String str, String key) {
		String encrypt = null;
		try {
			byte[] ret = encryptMode(str.getBytes("UTF-8"), key);
			BASE64Encoder enc = new BASE64Encoder();
			encrypt = enc.encode(ret);
		} catch (Exception e) {
			System.out.println("encrypt exception");
			System.out.println(e);
			encrypt = str;
		}
		return encrypt;
	}

	public static String DataDecrypt(String str, String key) {
		String decrypt = null;
		try {
			BASE64Decoder dec = new BASE64Decoder();
			byte[] ret = decryptMode(dec.decodeBuffer(str), key);
			decrypt = new String(ret, "UTF-8");
		} catch (Exception e) {
			System.out.println("decrypt exception");
			System.out.println(e);
			decrypt = str;
		}
		return decrypt;
	}

	/*
	 * 根据字符串生成密钥字节数组
	 * 
	 * @param keyStr 密钥字符串
	 * 
	 * @return
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] build3DesKey(String keyStr)
			throws UnsupportedEncodingException {
		byte[] key = new byte[24]; // 声明一个24位的字节数组，默认里面都是0
		byte[] temp = keyStr.getBytes("UTF-8"); // 将字符串转成字节数组

		/*
		 * 执行数组拷贝 System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
		 */
		if (key.length > temp.length) {
			// 如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
			System.arraycopy(temp, 0, key, 0, temp.length);
		} else {
			// 如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
			System.arraycopy(temp, 0, key, 0, key.length);
		}
		return key;
	}
}