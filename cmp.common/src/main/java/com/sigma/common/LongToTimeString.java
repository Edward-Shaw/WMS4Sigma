package com.sigma.common;

@Deprecated
public class LongToTimeString {
	
	public static String getTimeString(long millis) {
		
		long time = millis / 1000;
		
		int day = 0;
		int hour = 0;
		int minute = 0;
		int second = 0;
		
		for(int i = 1; time >= 60 * 60 * 24; i++){
			time -= 60 * 60 * 24;
			day = i;
		}
		
		if(time > 60 && time < 60 * 60){
			minute = (int)(time / 60);
			second = (int)(time % 60);
		}else if(time >= 60*60 && time < 60 * 60 * 24){
			hour = (int)(time / 3600);
			int temp = (int)(time % 3600);
			
			if(temp < 60){
				second = (int)(temp);
			}else {
				minute = (int)(temp / 60);
				second = (int)(temp % 60);
			}
		}else if(time < 60){
			second = (int)(time);
		}
		
		if(day != 0){
			return day + "天" + hour + "时" + minute + "分" + second + "秒";
		}else if(hour != 0){
			return hour + "时" + minute + "分" + second + "秒";
		}else if(minute != 0){
			return minute + "分" + second + "秒";
		}else{
			return second + "秒";
		}
	}
}
