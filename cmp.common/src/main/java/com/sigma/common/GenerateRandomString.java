package com.sigma.common;

import java.util.Random;

@Deprecated
public class GenerateRandomString {
	
	public GenerateRandomString(){

	}

	public String getRandomString(int length) {  
	    String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";  
	    Random random = new Random();  
	    StringBuffer buf = new StringBuffer();  
	    for (int i = 0; i < length; i++) {  
	        int num = random.nextInt(str.length());  
	        buf.append(str.charAt(num));  
	    }
	    return buf.toString();
	}
	
	public String getRandomString(int length, String str) {
	    Random random = new Random();  
	    StringBuffer buf = new StringBuffer();  
	    for (int i = 0; i < length; i++) {  
	        int num = random.nextInt(str.length());  
	        buf.append(str.charAt(num));  
	    }
	    return buf.toString();
	}
}
