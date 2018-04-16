package com.duty.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Map;


public class HttpURLConnectionUtil
{
	public static String sendPostRequest(String path, Map<String, Object> params)
	{

		StringBuilder sb = new StringBuilder();
		if (params != null && !params.isEmpty())
		{
			
			// 迭代Map拼接请求参数
			for (Map.Entry<String, Object> entry : params.entrySet())
			{
				Object value = entry.getValue();
				if (value instanceof String[])
				{
					String[] values = (String[])value;
					for(String v : values)
						sb.append(entry.getKey()).append('=').append(v).append('&');
				} else
					sb.append(entry.getKey()).append('=').append(value).append('&');

			}
			sb.deleteCharAt(sb.length() - 1);// 删除最后一个"&"
		}
		return getPost(path, sb.toString());
	}
	
	/**
	 * 发送http请求消息
	 * @param urlpath
	 * @param msg
	 * @return
	 */
	public static String getPost(String urlpath, String msg)
	{
		int errCount = 0;
		String responseMsg = "";
		while (responseMsg == null || responseMsg.trim().length() == 0)
		{
			try
			{
				responseMsg = httopPost(urlpath, msg);
			} catch (UnknownHostException e)
			{
				errCount++;
				
				if(errCount > 10)
				{
					errorIOException(e);
					
					return "";
				}
			} catch (IOException e)
			{
				errCount++;
				
				if(errCount > 10)
				{
					errorIOException(e);
					
					return "";
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
				return "";
			}
			
		}

		return responseMsg;

	}
	private static void errorIOException(Exception e)
	{
		try
		{
			Thread.sleep(10000);
		} catch (InterruptedException e1)
		{
			e1.printStackTrace();
		}
		e.printStackTrace();
	}
	private static String httopPost(String urlpath, String msg) throws UnknownHostException, IOException
	{
		URL url = new URL(urlpath);

		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setConnectTimeout(20000);
		//con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;encoding=utf-8");
		con.setDoOutput(true);

		OutputStream out = con.getOutputStream();

		out.write(msg.getBytes("utf-8"));
		con.connect();
		out.close();
		StringBuilder sb = new StringBuilder();
		String responseMsg = StringUtil.getInputStreamString(sb, con.getInputStream());
		return responseMsg;
	}
	
	public static boolean download(String url, File saveLocalFile)
	{

		if (!saveLocalFile.getParentFile().exists())
			saveLocalFile.getParentFile().mkdirs();
		if (saveLocalFile.exists())
			saveLocalFile.delete();

		boolean rs = true;
		FileOutputStream fos = null;
		try
		{
			URL _url = new URL(url);

			URLConnection cnn = _url.openConnection();
			
			InputStream in = cnn.getInputStream();

			byte[] by = new byte[1024 * 70];
			int k = 0;

			fos = new FileOutputStream(saveLocalFile);
			while ((k = in.read(by)) != -1)
			{
				writeIO(fos, k, by);
			}

		} catch (MalformedURLException e2)
		{
			e2.printStackTrace();
			return false;
		} catch (IOException e)
		{
			e.printStackTrace();
			return false;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		} finally
		{
			try
			{
				if (fos != null)
					fos.close();
			} catch (IOException e)
			{
				e.printStackTrace();

				return false;
			}
		}
		return rs;
	}

	private static void writeIO(FileOutputStream fos, int len, byte[] bytes)
	{
		try
		{

			fos.write(bytes, 0, len);
			fos.flush();
		} catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}

	}
}
