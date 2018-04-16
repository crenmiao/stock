package com.duty.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;

import com.duty.config.CommonConfigCache;


public class PathUtil
{

	public static String serverSysteRoot = null;
	public static String classPath = null;
	public static String FILE_ROOT;
	public final static String URL = "/sfj/";
	public static boolean SYSTEM_IS_WINDOW;
	static
	{

		Properties prop = System.getProperties();

		String os = prop.getProperty("os.name");

		if (os.startsWith("win") || os.startsWith("Win"))
			SYSTEM_IS_WINDOW = true;
		else
			SYSTEM_IS_WINDOW = false;
		
		serverSysteRoot = tomcatWebContenRoot();

		classPath = new PathUtil().getClass().getClassLoader().getResource("").getPath();
	}

	/**
	 * 通过文件来获得网页地址，地址是IP不是域名
	 * 
	 * @param path
	 * @return
	 */
	public static String getIPURLByFilePath(String path)
	{
//		if (path != null && path.contains(ROOT_FOLDER))
//			return CommonConfigCache.getServerROOTURL() + ROOT_FOLDER + path.split(ROOT_FOLDER)[1].replaceAll("\\\\", "/");

		return null;
	}

	 
	 

	/**
	 * 检查路径是否存在，不存在就创建
	 * 
	 * @param path
	 */
	public static String checkPathExit(String path)
	{
		File file = new File(path);
		if (!file.exists())
			file.mkdirs();
		return path;
	}

	/**
	 * 获得tomcat、webcontent的路径
	 * 
	 * @return
	 */
	public static String tomcatWebContenRoot()
	{
		String path = new PathUtil().getClass().getClassLoader().getResource("").getPath();
		path = path.split("WEB-INF")[0] + File.separator;

		try
		{
			path = URLDecoder.decode(path, "utf-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}

		return path;
	}
	public static String buildPath(String... path)
	{
		String fullPath = "";
		for (String p : path)
		{
			fullPath = FilenameUtils.concat(fullPath, p);
		}

		return fullPath;
	}

	 public  static String  getCSVUrl(String path,String date){
		 return path+File.separator+date+"_list.csv";
		 
	 }
	
	
}
