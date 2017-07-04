package lesson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Java中的异常处理
 * 
 * Java的内存模型
 * 栈内存/堆内存/静态方法区    (以及那些Java程序的部分是存放在它们里面的)
 * 
 * String类
 * String s1 = new String("123");这句话产生了几个数据
 * intern()这个方法的作用
 * 
 * 每一次String的操作都会产生一个新的string对象
 * 因为这个特点我们要引入
 * StringBuffer 所有操作都是它本身，一般优先使用线程不安全的类，
 * StringBuilder
 * 
 * ==和equlas的区别
 * 
 * 基本用法：
 * 
 * 		//字符串就是一个字符数组
		char array[]= path.toCharArray();
		//字符串获取字符索引
		int idx = path.indexOf('.');
		int idx2 =path.lastIndexOf('.');
		System.out.println(idx+","+idx2);
		//截取字符串
		String demo = path.substring(0,idx2)+"_1"+path.substring(idx2);
		System.out.println(demo);
		//字符串拆分
		String arr[] = path.split(",");
		//判断开头和结尾
		path.endsWith(".jpg");
		path.startsWith("wang");
		//包含
		path.contains("a,a");
		//trim去空格
		path.trim();
		//String类型和基本类型的转换
		String.valueOf(1);
		Integer.parseInt("123");
 * 
 * @author wyy
 *
 */
public class W20170704ExceptionDemo {
	
	
	//运行期Runtime和编译器Compile
	
    //Java的内存划分
	//堆内存:对象的实例
	//栈内存:基本类型，方法体的引用，对象的引用类型
	//静态区:编译时候产生的东西带static，类模板，常量池("string")，
	
	//基本类型:栈里面
	//对象类型: 堆里面
	/*
	(
		//他的封装类型
		//String类型
		//集合-对象
	)
	*/
	
	//static的关键把static修饰的部分提前到编译器产生
	
	
	
	
	public static void main(String[] args) {
		//SOFErrorDemo();
		//OOMErrorDemo();
		//W20170704ExceptionDemo.value2
		
		//W20170704ExceptionDemo w = new W20170704ExceptionDemo()
		//内存里面会放两块东西
		//w引用放在栈里面
		//对象实例放在堆里面
		
		
		//str
		//对象实例一份
		//静态区
		
		StringBaseDemo();
	}
	// == 比较的是内存的地址
	// EQUALS 比较的是你房间里面的东西
	static void StringDemo(){
		/*
		String str1  = "123";
		String str2 = new String("123");
		String str3 = "345";
		String str4 = str1+str3;
		String str5 = str2+str3;
		
		String str6 = "1"+"2" +"3"; 
		
		//1
		//2
		//3
		//12
		//123
		String str7 = "123"+new String("345");
		
		System.out.println(str1 == str2);
		System.out.println(str1.equals(str2));
		
		System.out.println(str4 == str5);
		System.out.println(str4.equals(str5));
		
		System.out.println(str4 == str6);
		System.out.println(str4 == str7);
		*/
		
		int i = 0;
		i = i+1;
		
		String str ="123";
		str = str.substring(1,2);
		System.out.println(i+","+str);
		//String的特点		
		//每一次String的操作都会产生一个新的string对象
		
		for(int k=0;k<1000;k++){
			str+=k;
		}
		
		//StringBuffer 所有操作都是它本身，一般优先使用线程不安全的类，
		//批量字符串一定要用StringBuffer类的原因
		//StringBuilder 基本等同于StringBuilder
		
		//#String,StringBuffer,StringBuilder有什么区别？
		//#jAVA的内存模型JMM,栈内存，栈内存和静态方法区
	    //#String两种写法的区别
		//String str1  = "123";
		//String str2 = new String("123");
		
		String str2 = new String("123");
		str2 = str2.intern();//
		
		str2+="345";
		String str1  = "123345";
		System.out.println(str1 == str2);
		System.out.println(str1.equals(str2));
	}
	
    //模拟Error信息
	//java.lang.StackOverflowError 递归死循环
	static void SOFErrorDemo(){
		SOFErrorDemo();
	}
	//模拟 OutOfMemory
	static void OOMErrorDemo(){
		int len = Integer.MAX_VALUE;
		int largArray[] = new int[len - 4];
		System.out.print(len);
	}
	
	
	
	//String的基本用法
	static void StringBaseDemo(){
		String path = "F:/demo/test.org.jpg";
		File f = new File(path);
		
		
		//字符串就是一个字符数组
		char array[]= path.toCharArray();
		//字符串获取字符索引
		int idx = path.indexOf('.');
		int idx2 =path.lastIndexOf('.');
		System.out.println(idx+","+idx2);
		//截取字符串
		String demo = path.substring(0,idx2)+"_1"+path.substring(idx2);
		System.out.println(demo);
		//字符串拆分
		String arr[] = path.split(",");
		//判断开头和结尾
		path.endsWith(".jpg");
		path.startsWith("wang");
		//包含
		path.contains("a,a");
		//trim去空格
		path.trim();
		//String类型和基本类型的转换
		String.valueOf(1);
		Integer.parseInt("123");
	}
	
}
