package com.duty.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CommonUtil
{
	/**
	 * 通过律师执证号来计算律师执业多少年
	 * @param licenseNO
	 * @return
	 */
    public static String getLawyerJobYear(String licenseNO)
    {
    	
    	if(licenseNO == null || licenseNO.length() != 17)
    		return "1年";
    	
    	try
    	{
    		int year = Integer.parseInt(licenseNO.substring(5, 9));
    		return (Calendar.getInstance().get(Calendar.YEAR) - year +1) + "年";
    	}
    	catch (Exception e) {
    		return "1年";
		}
    
     }
    /**
	 * 通过律师执证号来计算律师执业多少年
	 * @param licenseNO
	 * @return
	 */
    public static int computLawyerJobYear(String licenseNO)
    {
    	
    	if(licenseNO == null || licenseNO.length() != 17)
    		return 1;
    	
    	try
    	{
    		int year = Integer.parseInt(licenseNO.substring(5, 9));
    		return (Calendar.getInstance().get(Calendar.YEAR) - year +1);
    	}
    	catch (Exception e) {
    		return 1;
		}
    
     }
    /**
	 * 通过律师解答次数来判断律师的等级和等级图标地址
	 * @param answerCount 
	 * @return
	 */
    public static String[] getLawyerClassByAnswerCount(int answerCount)
    {
    	String[] classStr = new String[]{"铁牌律师","images/weixin/classimg_1.svg"};
    	
    	if(answerCount>=0 && answerCount<20){
    		classStr[0]="铁牌律师";
    		classStr[1]="images/weixin/classimg_1.svg";
    		return classStr;	
    	}
    	if(answerCount>=20 && answerCount<100){
    		classStr[0]="铜牌律师";
    		classStr[1]="images/weixin/classimg_2.svg";
    		return classStr;
    	}
    	if(answerCount>=100 && answerCount<500){
    		classStr[0]="银牌律师";
    		classStr[1]="images/weixin/classimg_3.svg";
    		return classStr;
    	}
    	if(answerCount>=500 && answerCount<4000){
    		classStr[0]="金牌律师";
    		classStr[1]="images/weixin/classimg_4.svg";
    		return classStr;
    	}
    	if(answerCount>=4000){
    		classStr[0]="钻石律师";
    		classStr[1]="images/weixin/classimg_5.svg";
    		return classStr;
    	}
		return classStr;
     }
    public static void main(String[] args)
	{
	}
    
    /**
     * 首页推荐地区
     * @return
     */
    public static List<String> recommendcity()
    {
    	List<String> citys=new ArrayList<String>();
    	citys.add("北京");
    	citys.add("上海");
    	citys.add("广州");
    	//citys.add("深圳");
    	citys.add("厦门");
    	return citys;
    }
    
    
    public static String convertNUM(String num)
    {
    	String result="";
    	if(num!=null)
    	{
    	char a[]=num.toCharArray();
    	String qq="";
    	if(a.length>0)
    	for (int i = 0; i < a.length; i++)
    	{
    		String m =a[i]+"";
    		
    		if(m.equals("零"))
            {
    			qq="0";
            }
    		if(m.equals("一"))
            {
    			qq="1";
            }
    		if(m.equals("二"))
            {
    			qq="2";
            }
    		if(m.equals("三"))
            {
    			qq="3";
            }
    		if(m.equals("四"))
            {
    			qq="4";
            }
    		if(m.equals("五"))
            {
    			qq="5";
            }
    		if(m.equals("六"))
            {
    			qq="6";
            }
    		if(m.equals("七"))
            {
    			qq="7";
            }
    		if(m.equals("八"))
            {
    			qq="8";
            }
    		if(m.equals("九"))
            {
    			qq="9";
            }
    		result=result+qq;
    		qq="";
            }

    	}
    	return result;
    }
}
