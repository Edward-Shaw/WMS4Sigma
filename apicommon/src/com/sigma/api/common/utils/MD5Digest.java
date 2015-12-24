package com.sigma.api.common.utils;

import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  
 
public class MD5Digest  
{  
	private MessageDigest md5 = null;
    
    private static MD5Digest instance = null;
    public static MD5Digest getInstance(){
    	if(instance == null){
    		try {
				instance = new MD5Digest();
			} catch (NoSuchAlgorithmException e) { }
    	}
    	
    	return instance;
    }
 
    /**
     * @deprecated 使用getInstance获取实例
     * @throws NoSuchAlgorithmException
     */
    public MD5Digest() throws NoSuchAlgorithmException {  
        md5 = MessageDigest.getInstance("MD5");
    }
    
    /**
     * @deprecated 使用crypt代替
     * @param s
     * @return
     */
	public String md5crypt(String s) {
        return this.crypt(s);
    }
	
	public String crypt(String plain){
		StringBuffer digestBuffer = new StringBuffer(0);
        byte abyte0[] = md5.digest(plain.getBytes());  
        for(int i = 0; i < abyte0.length; i++)  
        	digestBuffer.append(toHex(abyte0[i]));  
 
        return digestBuffer.toString();
	}
	
	private String toHex(byte one){  
		 String HEX = "0123456789abcdef";  
		 char[] result = new char[2];  
		 result[0] = HEX.charAt((one & 0xf0) >> 4);  
		 result[1] = HEX.charAt(one & 0x0f);  
		 String mm = new String(result);  
		 return mm;  
	}  
}  