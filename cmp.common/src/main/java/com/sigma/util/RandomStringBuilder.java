package com.sigma.util;

import java.util.Date;
import java.util.Random;

public class RandomStringBuilder {
	private static final String all = "qazxswertgbcvdfynhmjuiklopPLOKMIJNBHUYGVCZXASDFTREWQ0912345687";
	
	public static String random(int length){
		if(length <= 0){
			return "";
		}
		
		Random r = new Random((new Date()).getTime());
		StringBuffer result = new StringBuffer();
		for(int i = 0; i++ < length;){
			result.append(all.charAt(r.nextInt(all.length() - 1)));
		}
		return result.toString();
	}
}
