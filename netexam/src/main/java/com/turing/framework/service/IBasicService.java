package com.turing.framework.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface IBasicService {
	/**
	 * 保存文件到本地并返回保存路径
	 * @param picFile
	 * @param request
	 * @return
	 */
	public String saveFile(CommonsMultipartFile picFile,HttpServletRequest request);
	
	/**
	 * 根据文件路径对文件进行删除
	 * @param url
	 */
	public void deleteFile(String url,HttpServletRequest request);
}
