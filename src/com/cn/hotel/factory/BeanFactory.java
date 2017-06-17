package com.cn.hotel.factory;

import java.util.ResourceBundle;
/**
 * 用于生成类实例的bean工厂
 */
public class BeanFactory {
	//定义一个resourceBundle对象用于解析instance.properties文件
	private static ResourceBundle bundle;
	static {
		bundle=ResourceBundle.getBundle("instance");
	}
	/*
	 * 根据properties文件的那么得到value并创建value代表类的实例对象
	 */
	public static <T>T getInstance(String name,Class<T> clazz){
		T t=null;
		try {
			String className=bundle.getString(name);
			t=(T)Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t; 

		
	}
	
	
	
}
