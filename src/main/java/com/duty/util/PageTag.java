package com.duty.util;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PageTag extends TagSupport
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int totleNum; // 总信息数
	private int pageSize;//每页显示的第数，默认是10
	private int pageNum = 1; // 分页数
	private static int maxPageSize = 50;
	public static int getPageSize(int pageSize)
	{
		if(pageSize >maxPageSize)
			return maxPageSize;
		if(pageSize <= 1)
			return 10;
		return pageSize;
	}
	
	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public int getTotleNum()
	{
		return totleNum;
	}

	public void setTotleNum(int totleNum)
	{
		this.totleNum = totleNum;
	}

	public int getPageNum()
	{
		return pageNum;
	}

	public void setPageNum(int pageNum)
	{
		this.pageNum = pageNum;
	}

	
	public int doStartTag() throws JspException
	{
		JspWriter out = pageContext.getOut();
		if(pageSize == 0)
			pageSize = 10;
		int num = totleNum % pageSize == 0 ? totleNum / pageSize : totleNum / pageSize + 1;
		StringBuilder stringBuilder = new StringBuilder(10);
		//stringBuilder.append("<div id=\"pagination-panel\"></div>\n");
		stringBuilder.append("\t\t<div id=\"pagination-items\" align=\"center\">\n");
		//大于第一页
		if(pageNum>1)
		{
			stringBuilder.append("\t\t<input type=\"button\" title='First' class='page_down' value='First' title='To First' onclick='pageToFirst();'/>");
			stringBuilder.append("\t\t<input type=\"button\" title='Previous' class='page_down' value='Previous' title='Previous' onclick='pageToPre();'/>");
		}
		//中间显示页数
		if (pageNum>0&&pageNum<num+1)
		{ 
			//判断从第几页开始显示页数
			int i=pageNum;
				if(i-2<1)
					i=1;
				else
				{
					if(i+2<num)
						i=i-2;
					else
					{
						i=num-5+1;
					}
				}
			int n=i+5;
			//如果页数小于5页，都从第一页起，每页都显示
			if(num<=5)
			{
				n=num+1;
				i=1;
			}
			if(num>5&&pageNum>3)
			{
				stringBuilder.append("···");
			}
			for(;i<num+1&&i<n;i++)
			{
				if(pageNum==i)
				{
					stringBuilder.append("\t\t<input type=\"button\" title='Page "+i+"' class='pagebt_1' value='"+i+"' />\n");
				}
				else
				{
					stringBuilder.append("\t\t<input type=\"button\" title='to Page "+i+"' class='pagebt_2' value='"+i+"' onclick='pageTo("+i+");'/>\n");
				}
			}
			//判断后面是否还有2页以上
			if(num>5&&pageNum+2<num)
			{
				stringBuilder.append("···");
			}
		}
		//非最后一页
		if (pageNum<num)
		{ // 最后一页,向后翻页不可用
			stringBuilder.append("\t\t<input type=\"button\" title='Next' class='page_down' value='Next' title='Next'  onclick='pageToNext();'/>");
			stringBuilder.append("\t\t<input type=\"button\" title='Last' class='page_down' value='Last' title='To Last' onclick='pageToLast(" + num + ");'/>");
		}

		stringBuilder.append("\t\t&nbsp;To&nbsp;<input id='pg-goto-num' name=\"pageNum\"  value=\"" + pageNum + "\" class='appoint_pageinput' />" +
				"&nbsp;Page\n");
		stringBuilder.append("\t\t&nbsp;<input type=\"button\" class='appoint_page' value='GO' title='' onclick='pageGoTo("+num+");'/>");
		stringBuilder.append("\t\t&nbsp;total <b>" +totleNum + "</b> records");
		stringBuilder.append("\t\t</div>\n");
		try
		{
			out.print(stringBuilder.toString());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException
	{
		return EVAL_PAGE;
	}

}
