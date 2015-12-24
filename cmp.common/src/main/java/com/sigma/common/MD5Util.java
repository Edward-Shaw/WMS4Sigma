package com.sigma.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sigma.common.MD5Hex;

@Deprecated
public class MD5Util {
	
	public final static String encode(String s){
		byte[] input = s.getBytes(), output = null;
		char hex[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(input);
			output = md.digest();
			
			char[] chars = new char[output.length * 2];
			for(int i = 0; i < output.length; i++){
				byte b = output[i];
                chars[i * 2] = hex[b >>> 4 & 0xF];
                chars[i * 2 + 1] = hex[b & 0xF];
			}
			
			return new String(chars);
		}catch(Exception ex){
		}
		
		return null;
	}
	
	
	  public final static String MD5(String s) throws NoSuchAlgorithmException {
	        char hexDigits[] = { '0', '1', '2', '3', '4',
	                             '5', '6', '7', '8', '9',
	                             'A', 'B', 'C', 'D', 'E', 'F' };
	        
	            byte[] btInput = s.getBytes();
	            //获得MD5摘要算法的 MessageDigest 对象
	            MessageDigest mdInst = MessageDigest.getInstance("MD5");
	            //使用指定的字节更新摘要
	            mdInst.update(btInput);
	            //获得密文
	            byte[] md = mdInst.digest();
	            //把密文转换成十六进制的字符串形式
	            int j = md.length;
	            char str[] = new char[j * 2];
	            int k = 0;
	            for (int i = 0; i < j; i++) {
	                byte byte0 = md[i];
	                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	                str[k++] = hexDigits[byte0 & 0xf];
	            }
	            
	            return new String(str);	       
	  }
	  
	  /** 
	     * 根据给定摘要算法创建一个消息摘要实例 
	     *  
	     * @param algorithm 
	     *            摘要算法名 
	     * @return 消息摘要实例 
	     * @see MessageDigest#getInstance(String) 
	     * @throws RuntimeException 
	     *             当 {@link java.security.NoSuchAlgorithmException} 发生时 
	     */  
	    static MessageDigest getDigest(String algorithm) {  
	        try {  
	            return MessageDigest.getInstance(algorithm);  
	        } catch (NoSuchAlgorithmException e) {  
	            throw new RuntimeException(e.getMessage());  
	        }  
	    }  
	  
	    /** 
	     * 获取 MD5 消息摘要实例 
	     *  
	     * @return MD5 消息摘要实例 
	     * @throws RuntimeException 
	     *             当 {@link java.security.NoSuchAlgorithmException} 发生时 
	     */  
	    private static MessageDigest getMd5Digest() {  
	        return getDigest("MD5");  
	    }  
	    
	    /** 
	     * 使用MD5消息摘要算法计算消息摘要 
	     *  
	     * @param data 
	     *            做消息摘要的数据 
	     * @return 消息摘要（长度为16的字节数组） 
	     */  
	    public static byte[] encodeMD5(byte[] data) {  
	        return getMd5Digest().digest(data);  
	    }  
	  
	    /** 
	     * 使用MD5消息摘要算法计算消息摘要 
	     *  
	     * @param data 
	     *            做消息摘要的数据 
	     * @return 消息摘要（长度为32的十六进制字符串） 
	     */  
	    public static String encodeMD5Hex(byte[] data) {  
	        return MD5Hex.encodeHexStr(encodeMD5(data));  
	    }  
}