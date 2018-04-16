package com.duty.config;

import org.dom4j.Element;

/**
 * 一般配置
 * 
 * @author Administrator
 * 
 */
public class CommonConfigItem
{
	private String id;
	private String name;
	private String value;
	private String desc;

	public CommonConfigItem(Element ele)
	{
		this.id = ele.attributeValue("id");
		this.name = ele.attributeValue("name");
		this.value = ele.attributeValue("value");
		this.desc = ele.attributeValue("desc");
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

}
