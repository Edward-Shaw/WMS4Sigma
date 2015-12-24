package com.sigma.api.common.utils;

/**
 * 用处处理数字的类,包括整数和浮点数
 * @author XuGang
 *
 */
public class Number {
	/**
	 * Not A Number
	 */
	public static Number NaN = new Number();
	
	/**
	 * 仅用于构造NaN
	 * 此时doubleValue返回值为Double.NaN
	 */
	protected Number(){}
	
	private double value = Double.NaN;
	
	/**
	 * 私有成员, 仅在parseNumber方法中调用
	 * @param value
	 */
	private Number(double value){
		this.value = value;
	}
	
	/**
	 * 将字符串转换为数字,如果失败返回NaN
	 * @param number
	 * @return 不是数字返回NaN; 否则返回Number对象
	 */
	static public Number parseNumber(String number){
		double value = Double.NaN;
		try{
			value = Double.parseDouble(number);
		}catch(NumberFormatException ex){
			return NaN;
		}
		
		return new Number(value);
	}
	
	/**
	 * 获得数字的整型值
	 * @return
	 */
	public int intValue(){
		return (int) value;
	}
	
	/**
	 * 获得数字的浮点值
	 * @return
	 */
	public double doubleValue(){
		return this.value;
	}
}
