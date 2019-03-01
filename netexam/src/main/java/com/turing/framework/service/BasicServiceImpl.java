package com.turing.framework.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class BasicServiceImpl implements IBasicService{

	@Override
	public String saveFile(CommonsMultipartFile picFile,
			HttpServletRequest request) {
		//1,取得上传文件名称
		String fileName = picFile.getOriginalFilename();
		//2,取得上传文件扩展名
		String kzm = fileName.substring(fileName.lastIndexOf("."));
		//3,取得上传文件存储路径
		String path = request.getSession().getServletContext().getRealPath("upload");
		//4,设置目标存储文件
		long sj = System.currentTimeMillis();
		File destFile = new File(path+"/"+sj+kzm);	//文件名随机数表示,防止覆盖
		//5,设置图片存储路径的URL
		String pn = request.getContextPath();//  /springMVC-Mybatis
		StringBuffer requestURL = request.getRequestURL();	//  http://60.37.1.7:60001/springMVC-Mybatis/upload/addsave.action
		String picUrl = requestURL.substring(0, requestURL.indexOf(pn)+pn.length())+"/upload/"+sj+kzm;
		//6,把图片存储到upload文件夹内
		try {
			picFile.getFileItem().write( destFile );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return picUrl;
	}

	@Override
	public void deleteFile(String url,HttpServletRequest request) {
		//1,得到图片名称
		String fileName = url.substring(url.lastIndexOf("/")+1);
		//2,得到图片存放路径
		String path = request.getSession().getServletContext().getRealPath("upload");
		File file = new File(path+"/"+fileName);
		//3,删除图片
		if(file.exists()){
			file.delete();
		}
	}

}
