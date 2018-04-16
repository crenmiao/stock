package com.duty.config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.duty.util.FileUtil;
import com.duty.util.PathUtil;



/**
 * 一般配置缓存
 * @author Administrator
 *
 */ 
public class CommonConfigCache
{
	private static CommonConfigCache instance = new CommonConfigCache();
	private List<CommonConfigItem> items ;
	@SuppressWarnings("unchecked")
	private CommonConfigCache()
	{
		String path = PathUtil.buildPath(PathUtil.classPath, "commonconfig.xml");
		Document doc = FileUtil.loadXmlFile(path);
		List<Element> eles = doc.getRootElement().elements();  
		items = new ArrayList<CommonConfigItem>(eles.size());
 
		for(Element e : eles){  
			items.add(new CommonConfigItem(e));
		}
	}
	public static void refresh()
	{
		instance = new CommonConfigCache();
	}
	 
	public static String getFilePath()
	{
		for(CommonConfigItem item : instance.items)
		{
			if(item.getId().equals("filepath"))
				return item.getValue();
		}
		return "";
	}
	
	public static String getEndMark()
	{
		for(CommonConfigItem item : instance.items)
		{
			if(item.getId().equals("mark"))
				return item.getValue();
		}
		return "";
	}
	
	public static String getSendTime()
	{
		for(CommonConfigItem item : instance.items)
		{
			if(item.getId().equals("sendtime"))
				return item.getValue();
		}
		return "";
	}
	
}
