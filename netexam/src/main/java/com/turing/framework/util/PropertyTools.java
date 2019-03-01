package com.turing.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyTools{

	private static Properties props = new Properties();
	
	static {
		// 1，读取配置文件
		InputStream in = PropertyTools.class.getClassLoader().getResourceAsStream("common.properties");
		try {
			props.load(in);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	//根据属性文件的key获取value
	public static String getValueFromKey(String key){
		
		 String value = props.getProperty(key);
		 
		 if(value !=null){
			 return value;
		 }else{
			 return ""; 
		 }
	}
}
