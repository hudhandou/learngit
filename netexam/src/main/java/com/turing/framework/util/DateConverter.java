package com.turing.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import org.springframework.core.convert.converter.Converter;

/**
 * @author 赵刚
*  @date 2018年6月21日00:13:11
*  @desc 功能描述 ：日期格式转换 
*/
public class DateConverter implements Converter<String, Date> {

	private final String reg1 = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
	private final String reg2 = "^\\d{4}-\\d{1,2}-\\d{1,2}[T ]\\d{1,2}:\\d{1,2}$";
	private final String reg3 = "^\\d{4}-\\d{1,2}-\\d{1,2}[T ]\\d{1,2}:\\d{1,2}:\\d{1,2}[Z]{0,1}$";
	private final String reg4 = "^\\d{4}/\\d{1,2}/\\d{1,2}$";
	private final String pattern1 = "yyyy-MM-dd";
	private final String pattern2 = "yyyy-MM-dd HH:mm";
	private final String pattern3 = "yyyy-MM-dd HH:mm:ss";
	private final String pattern4 = "yyyy/MM/dd";
	@Override
	public Date convert(String source) {
		if (source != null) {
			String pattern = null;
			if(Pattern.matches(reg1, source)) {
				pattern = pattern1;
			}else if(Pattern.matches(reg2, source)) {
				pattern = pattern2;
			}else if(Pattern.matches(reg3, source)) {
				pattern = pattern3;
			}else if(Pattern.matches(reg4, source)) {
				pattern = pattern4;
			}else {
				pattern = pattern1;
				try {
					throw new  ParseException("无法解析日期: \""+source+"\" ,格式不匹配.", 0);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			dateFormat.setLenient(false);
	        try {
				return dateFormat.parse(source);
			} catch (ParseException e) {
				e.printStackTrace();
			}   
		}
		return null;
	}
}
