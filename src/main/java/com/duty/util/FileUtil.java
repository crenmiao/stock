package com.duty.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
/**
 * 文件工具类
 * 
 * @author liumj
 * 
 */
public class FileUtil
{
	private static Logger log = Logger.getLogger(FileUtil.class);
	
	

	/**
	 * 检查文件或文件夹路径是否存在 如果文件夹不存在，则自动创建文件夹
	 * 
	 * @param path
	 */
	public static void cheakPath(String path)
	{
		StringBuffer s = new StringBuffer(path);
		int dian = s.indexOf(".");
		if (dian >= 0)
		{
			dian = s.lastIndexOf("\\") > s.lastIndexOf("/") ? s.lastIndexOf("\\") : s.lastIndexOf("/");
			path = s.substring(0, dian);

		}
		if (path != null && !"".equals(path))
		{
			path = PathUtil.buildPath(path);
			File fpath = new File(path);
			if (!fpath.exists())
				fpath.mkdirs();
		}
	}

	/**
	 * 
	 */
	public static boolean mkdir(String dir)
	{
		boolean result = true;
		try
		{
			FileUtils.forceMkdir(new File(dir));
		} catch (IOException e)
		{
			result = false;
			log.error(e);
		}
		return result;
	}

	/**
	 * 获得后缀名
	 * 
	 * @param path
	 * @return
	 */
	public static String getFileSuffix(String path)
	{
		if (path == null || path.length() == 0)
			return "";
		return path.indexOf(".") != -1 ? path.substring(path.lastIndexOf("."), path.length()) : "";
	}

	/**
	 * 复制文件
	 * 
	 * @param inPath
	 * @param outPath
	 * @return
	 */
	public static boolean copyFile(String inPath, String outPath)
	{
		try
		{

			int byteread = 0;
			File oldfile = new File(inPath);
			if (oldfile.exists())
			{
				cheakPath(outPath);
				InputStream inStream = new FileInputStream(inPath);
				FileOutputStream fs = new FileOutputStream(outPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1)
				{
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.close();
			}
		} catch (Exception e)
		{

			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static Document loadXmlFile(String filePath)
	{
		Document doc = null;
		try
		{
			doc = new SAXReader().read(new File(filePath));
		} catch (DocumentException e)
		{
			e.printStackTrace();
			log.error(e.getMessage() + " when read xml: " + filePath);
		}
		return doc;
	}

	public static void saveXmlFile(Document doc, String savePath)
	{
		saveXmlFile(doc, savePath, "utf-8");
	}

	/**
	 * 没有格式的保存
	 * 
	 * @param doc
	 * @param savePath
	 * @param encoding
	 */
	public static void saveXmlFileNoFormu(Document doc, String savePath, String encoding)
	{
		XMLWriter w = null;
		try
		{
			// OutputFormat format = OutputFormat.createPrettyPrint();
			// format.setEncoding(encoding);
			// format.setLineSeparator("\r\n");
			// format.setIndentSize(4);

			w = new XMLWriter(new FileOutputStream(savePath));
			w.write(doc);
		} catch (UnsupportedEncodingException e)
		{
			log.error(e.getMessage() + " when save xml file " + savePath);
		} catch (FileNotFoundException e)
		{
			log.error(e.getMessage() + " when save xml file " + savePath);
		} catch (IOException e)
		{
			log.error(e.getMessage() + " when save xml file " + savePath);
		} finally
		{
			if (w != null)
			{
				try
				{
					w.close();
				} catch (IOException e)
				{
				}
			}
		}
	}

	public static void saveXmlFile(Document doc, String savePath, String encoding)
	{
		XMLWriter w = null;
		try
		{
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding(encoding);
			format.setLineSeparator("\r\n");
			format.setIndentSize(4);

			w = new XMLWriter(new FileOutputStream(savePath), format);
			w.write(doc);
		} catch (UnsupportedEncodingException e)
		{
			log.error(e.getMessage() + " when save xml file " + savePath);
		} catch (FileNotFoundException e)
		{
			log.error(e.getMessage() + " when save xml file " + savePath);
		} catch (IOException e)
		{
			log.error(e.getMessage() + " when save xml file " + savePath);
		} finally
		{
			if (w != null)
			{
				try
				{
					w.close();
				} catch (IOException e)
				{
				}
			}
		}
	}

	/**
	 * 
	 * @param dspath
	 * @return
	 */
	public static boolean validateFolderExists(String folderPath)
	{
		boolean r = true;
		File p = new File(folderPath);
		if (!p.isDirectory())
			try
			{
				FileUtils.forceMkdir(p);
			} catch (IOException e)
			{
				r = false;
				e.printStackTrace();
			}
		return r;
	}

	public static boolean isDoc(String fileName)
	{
		if (fileName == null || "".equals(fileName))
			return false;
		else if (fileName.endsWith(".doc") || fileName.endsWith(".docx") || fileName.endsWith(".wps"))
			return true;
		return false;

	}

	public static boolean isExcel(String fileName)
	{
		if (fileName == null || "".equals(fileName))
			return false;
		else if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx") || fileName.endsWith(".et"))
			return true;
		return false;

	}

	public static boolean isPDF(String fileName)
	{
		if (fileName == null || "".equals(fileName))
			return false;
		else if (fileName.endsWith(".pdf"))
			return true;
		return false;

	}

	public static boolean isPPT(String fileName)
	{
		if (fileName == null || "".equals(fileName))
			return false;
		else if (fileName.endsWith(".ppt"))
			return true;
		return false;

	}

	public static boolean isZIP(String fileName)
	{
		if (fileName == null || "".equals(fileName))
			return false;
		else if (fileName.endsWith(".zip") || fileName.endsWith(".rar"))
			return true;
		return false;

	}

	public static boolean isTXT(String fileName)
	{
		if (fileName == null || "".equals(fileName))
			return false;
		else if (fileName.endsWith(".txt"))
			return true;
		return false;
	}

	public static boolean isEXE(String fileName)
	{
		if (fileName == null || "".equals(fileName))
			return false;
		else if (fileName.endsWith(".exe"))
			return true;
		return false;
	}

	public static boolean isPNG(String fileName)
	{
		if (fileName == null || "".equals(fileName))
			return false;
		else if (fileName.endsWith(".png"))
			return true;
		return false;
	}

	public static boolean isJPG(String fileName)
	{
		if (fileName == null || "".equals(fileName))
			return false;
		else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg"))
			return true;
		return false;
	}

	public static boolean isMOV(String fileName)
	{
		if (fileName == null || "".equals(fileName))
			return false;
		else if (fileName.endsWith(".rmvb"))
			return true;
		return false;
	}

	public static boolean isGIF(String fileName)
	{
		if (fileName == null || "".equals(fileName))
			return false;
		else if (fileName.endsWith(".gif"))
			return true;
		return false;
	}

	public static boolean isMP3(String fileName)
	{
		if (fileName == null || "".equals(fileName))
			return false;
		else if (fileName.endsWith(".mp3"))
			return true;
		return false;
	}
	 
	public static byte[] getFileBytes(File f)
	{
		byte[] bytes = null;
		try
		{
			FileInputStream in = new FileInputStream(f);
			bytes = new byte[in.available()];
			in.read(bytes, 0, in.available());
			in.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return bytes;
	}

	
}
