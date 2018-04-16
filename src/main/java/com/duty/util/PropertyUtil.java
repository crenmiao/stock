package com.duty.util;
import java.io.*;  
import java.util.Properties;  

import com.duty.config.MailConfig;
  
/** 
 * Desc:properties文件获取工具类 
 * Created by  
 */  
public class PropertyUtil {  
    private static Properties props; 
    private static final String filename = "task.properties";
    private static String filepath = new PropertyUtil().getClass().getClassLoader().getResource("").getPath()+filename;
    static{  
        loadProps();  
    }  
  
    synchronized static private void loadProps(){  
        props = new Properties();  
        InputStream in = null;  
        try {  
            //第一种，通过类加载器进行获取properties文件流  
            //in = PropertyUtil.class.getClassLoader().getResourceAsStream("sysConfig.properties");  
            //第二种，通过类进行获取properties文件流  
            in = PropertyUtil.class.getClassLoader().getResourceAsStream(filename);  
            props.load(in);  
        } catch (FileNotFoundException e) {  
            //logger.error("sysConfig.properties文件未找到");  
        } catch (IOException e) {  
            //logger.error("出现IOException");  
        } finally {  
            try {  
                if(null != in) {  
                    in.close();  
                }  
            } catch (IOException e) {  
                //logger.error("sysConfig.properties文件流关闭出现异常");  
            }  
        }  
        //logger.info("加载properties文件内容完成...........");  
        //logger.info("properties文件内容：" + props);  
    }  
  
    public static String getProperty(String key){  
        if(null == props) {  
            loadProps();  
        }  
        return props.getProperty(key);  
    }  
  
    public static String getProperty(String key, String defaultValue) {  
        if(null == props) {  
            loadProps();  
        }  
        return props.getProperty(key, defaultValue);  
    }  
    
    public static void setProperty(String key, String value) {  
        if(null == props) {  
            loadProps();  
        }  
        props.setProperty(key, value); 
        FileOutputStream fos =null;
     // 文件输出流     
        try {    
            fos = new FileOutputStream(filepath);     
            // 将Properties集合保存到流中     
            props.store(fos, "Copyright (c) Boxcode Studio");     
            fos.close();// 关闭流     
        } catch (FileNotFoundException e) {    
            // TODO Auto-generated catch block    
            e.printStackTrace();    
           
        } catch (IOException e) {    
            // TODO Auto-generated catch block    
            e.printStackTrace();    
           
        } finally {  
            try {  
                if(fos!=null) {  
                	fos.close();  
                }  
            } catch (IOException e) {  
               
            }  
        }    
    }
    
    
}  