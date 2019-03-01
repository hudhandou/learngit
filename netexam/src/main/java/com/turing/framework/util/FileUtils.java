package com.turing.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUtils {
	/**
	 * 文件上传
	 * @param picFile 接到的文件
	 * @param request
	 * @return
	 */
	public static String upload(CommonsMultipartFile picFile, HttpServletRequest request) {
		String rootPath = request.getSession().getServletContext().getRealPath("");
		File uploadFile = new File(rootPath+"upload");
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();	//创建目录
		}
		//获取扩展名
		String realName = picFile.getOriginalFilename();// a.b.c.d1.jpg
		String ext = realName.substring(realName.lastIndexOf("."));//.jpg
		String fileName = UUID.randomUUID().toString()+ext;//fkhdsiofyfysdifsd.jpg
		File file = new File(rootPath+"upload/"+fileName);
		try {
			//保存文件到目录里
			picFile.getFileItem().write(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}
	/**
	 * 删除文件
	 * @param request
	 * @param cover
	 * @return
	 */
	public static boolean deleteFile(HttpServletRequest request,String cover) {
		boolean success = false;
		if (!"".equals(cover)) {
			String rootPath = request.getSession().getServletContext().getRealPath("");
			File file = new File(rootPath+"upload/"+cover   );
			if ( file.exists() ) {
				success = file.delete();
			}
		}
		return success;
	}
	/**
	 * 下载
	 */
	public static void download(HttpServletRequest request,HttpServletResponse response,String fileName) {
		String rootPath = request.getSession().getServletContext().getRealPath("");
		File file = new File( rootPath+"upload/"+fileName  );
		if (!"".equals(fileName) && file.exists() ) {
			//开始下载
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition","attachment;fileName=" + fileName);// 设置文件名
			byte[] buffer = new byte[1024];
			FileInputStream fis  = null;
			try{
				fis  = new FileInputStream(file);
				OutputStream os = response.getOutputStream();
				int i = fis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = fis.read(buffer);
				}
				fis.close();
				os.close();
			}catch(Exception  e){
				e.printStackTrace();
			}
		}
	}
}
