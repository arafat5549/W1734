package lesson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 * -----常用类------language
 * 都在 
 * java.util.*;  
 * java.lang.*;  
 * java.collection.*;
 * java.concurrent.*;
 * 
 * 
 * System 系统
 * String 字符串
 * Random 随机数
 * Date日期处理
 * 
 * @author wyy
 *
 */
public class W20170708UserfulClassDemo {

	static void systemDemo(){
		
		//boolean key = true;
		//long key = 1L;
		int key2 = 1;
		switch (key2) {
			case 1: break;
			case 2: break;
			default:break;
		}
		
		//控制台Console输入输出流
		//System.out
		//System.err
		//System.in
		
		//System.exit(0);
		
		//系统属性
		Properties props = System.getProperties();
		for (Object key : props.keySet()) {
			System.out.println(key+","+props.get(key));
		}
		//获取当前工程路径
		System.out.println(props.get("user.dir"));
		//文件夹分隔符
		System.out.println(props.get("file.separator"));
		
		//垃圾清理  gabarge clear
		//System.gc();
		//Runtime.getRuntime().gc();
		
		//获取CPU的核心数
		int cpus = Runtime.getRuntime().availableProcessors();
		System.out.println(cpus);
		//获取当前时间
		System.out.println(System.currentTimeMillis());
	}
	
	static void dateDemo() throws ParseException{
		//1.计算机怎么表示时间 # LONG型单位表示毫秒
		//从1970.1.1开始算起的毫秒数相加
		
		
		//1.获取当前时间的毫秒数 time
		long time = System.currentTimeMillis();
		
		//2.获取当前时间的方式
		Date date = new Date();
		System.out.println(date.getTime());
		
		Date date2 = new Date(time);
		System.out.println(date2);
		//3.格式转换  yyyy表示年 MM表示月 dd天 时HH 分mm 秒ss  毫秒SSS
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(format.format(date2));
		
		String dateStr = "2017-07-08 13:57:58";
		System.out.println(format.parse(dateStr));
		
		//3.第三种表示当前时间的方式
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTimeInMillis());
		//怎么取得去年的这个时候
		//cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)-1);
		System.out.println(format.format(new Date(cal.getTimeInMillis())));
		
		//几天周几  # 国外是从星期天开始算 1-7
		System.out.println("这周第几天:"+cal.get(Calendar.DAY_OF_WEEK));
		//月份是从0开始算 
		System.out.println("第几月:"+cal.get(Calendar.MONTH));
		
		//Calendar.YEAR
		//Calendar.MONTH
		//Calendar.DAY_OF_YEAR
		//Calendar.DAY_OF_MONTH
		//Calendar.DAY_OF_WEEK
	}
	
	public static void main(String[] args) throws ParseException {
		//systemDemo();
		dateDemo();
	}
}
