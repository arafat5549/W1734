package wcw;

import java.io.IOException;
import java.util.Random;
/**
 * 数组Demo
 * @author wyy
 *
 */
public class HelloWorld {
    
	
	private static String myName = null;
	private static final String MY_NAME = "sss";
	
	//注释
	//命名规范
	
	//包名package: 全部是小写  一般就是 你公司主业的反写 + 模块名 + 分层名 : com.ssf.module.web
	//类名class ：首字母大写 然后每个单词的首字母大写 (驼峰格式)
	//属性名或者字段名Filed: 首字母小写 然后每个单词的首字母大写 (驼峰格式)
	//特殊的字段 常量Constant: 在内存有且只会有一份而且不可变,全部是大小而且以下划线连接(常量一定要显示初始化)	
	//方法名Method ：首字母小写 然后每个单词的首字母大写 (驼峰格式)
	//参数名 Parameter: 首字母小写 然后每个单词的首字母大写 (驼峰格式)，参数名可能会简写
	
	//命名空间(NameSpace)：如果我有两个HelloWorld.java 如果你包名不一样我会当成两个文件
	 //   wcw/HelloWorld.java
	
	
	public static void main(String[] args) throws IOException {
		//MY_NAME = "";
		
		//System常用类
		/*
		System.out.println("HelloWolrd！"); //打印输出 显示到屏幕上面去
		System.err.println("HelloWolrd！");
		byte buf [] =new byte[1024];
		int idx = -1;
		if((idx  = System.in.read(buf)) != -1){
			System.out.println(new String(buf,0,idx));
		}
		*/
		//空值类型 null
		//1.8大基本类型
		boolean b1= false;//布尔值#1bit 1位
		byte    b2= 0;//字节#8bit 8位   0X00000001
		char    b3='\u0000';//字符#16位
		short   b4=0;//16位             0X0000000000000001
		int     b5=0;//32位
		long    b6=0L;//64位
		float   b7=0.0f;//32位
		double  b8=0.0d;//64位
		
		//b4= b4 + 1;		  //隐式类型转换 低的向高的转换
		//b4= (short)(b4 + 1);  //显示类型转换 可以从高的向低的转 有可能损失
		
		//封装类型 ， 装箱和拆箱  封装类型的默认值是null
		Boolean a1 = false;//对象  Boolean a1 = new Boolean(false);  默认值是null
		Byte    a2 = 0;
		Character a3 ='a';
		
		//arrayDemo();
		int array[] = {1,22,33,999,44,33333};
		//array_getMax(array);
		//array_bubbleSort(array);
		
		//array_count();
		array_count2();
		//stringDemo();
	}
	//#获取数组中最大的树
	static void array_getMax(int array[]){		
		//1.设置最大的数max跟每一个相比
		//2.如果比max 就要把max置换为那个数
		
		int max = Integer.MIN_VALUE;//array[0] 下表访问
		for (int i = 0; i < array.length; i++) {
			if(array[i] > max)
			{
				max = array[i];
			}
		}
		System.out.println(max);
	}
	//#冒泡排序 bubbleSort 交换
	static void array_bubbleSort(int array[]){
		for (int i = 0; i < array.length; i++) {
			for (int j = i; j < array.length; j++) {
				if(array[i] > array[j]){
					int temp =  array[j];//缓存你要替换的那个数
					array[j] = array[i];
					array[i] =temp;
				}
			}
		}
		printArray(array);
	}
	//#统计0-9各出现多少次   #  限定死数组里面的元素都是 0-9
	static void array_count(){
		//随机一个数组
		Random rand = new Random();
		//1.设定数组大小
		int size =100;
		int array[] = new int[size];
		//2.随机分配 
		for (int i = 0; i < array.length; i++) {
			array[i]= rand.nextInt(10);// 0-9
		}
		printArray(array);
		
		int countArr[]= new int[10];
		for (int i = 0; i < array.length; i++) {
			int key = array[i];
			if(key>=0 && key < countArr.length)
				countArr[key] +=1;
		}
		printArray(countArr);
	}
    //#统计a,d,j各出现多少次
	static void array_count2(){
//		Random rand = new Random();
//		//1.设定数组大小
//		int size =100;
//		char array[] = new char[size];
//		//2.随机分配 
//		for (char i = 0; i < array.length; i++) {
//			array[i]= (char)(97 + rand.nextInt(26));
//		}
		
		char array[] = {'a','d','d','j','j','j'};
		printArray(array);
		
		int aCount = 0;
		int dCount = 0;
		int jCOunt = 0;
		for (char i = 0; i < array.length; i++) {
			switch(array[i]){
			   case 'a' : aCount+=1; break;
			   case 'd' : dCount+=1; break;
			   case 'j' : jCOunt+=1; break;
			   default: break;
			}
		}
		
		System.out.println(aCount+","+dCount+","+jCOunt);
	}
	
	
	static void printArray(char array[]){
		for (char i : array) {
			System.out.print(i+",");
		}
		System.out.println();
	}
	
	static void printArray(int array[]){
		int count = 0;
		for (int i : array) {
			System.out.print(i+",");
			count+=i;//count = count +i;
		}
		System.out.println();
		
		System.out.println(count);
	}
	
	static void arrayDemo(){
		//数组 (定长的数据类型的集合)一定要指定大小
		int arr1 = 0;
		int arr2 = 0;
		int arr3 = 0;
		int arr4 = 0;
		int arr5 = 0;
		//数组array初始化的方式
		int array[] = new int[5];
		int array2[] = {1,2,3};
		int array3[]  =new int[]{0,0,1};
		
		//数组手动初始化
		//基础的for循环
		for (int i = 0; i < array.length; i++) {
			array[i] = 1;
		}
		//foreach
		for (int i : array) {
			 
		}                  
		addArray(array,1,9);//1,9,1,1,1,1
	}
	
	//数组的操作
	static int[] addArray(int array[],int idx,int value){
		int newarr[] = new int[array.length+1];
		
		for (int i = 0; i < newarr.length; i++) {
			if(i<idx){
				newarr[i]=array[i];
			}
			else if(i==idx)
			{
				newarr[i]=value;
			}
			else{
				newarr[i]=array[i-1];
			}
		}
		
		for (int i : newarr) {
			System.out.print(i+",");
		}
		System.out.println();
		return newarr;
		
	}
	
	static void stringDemo(){
		//String字符串类型
		String str = "asasasasa";
		String filePath = "F:/RSATest.xxxx.txt"; //"_0"
		
		//取其中的一段和索引
		int idx  = filePath.indexOf('.');
		int idx2 = filePath.lastIndexOf('.');
		String demo = filePath.substring(0, idx2);
		System.out.println(demo+"_0"+filePath.substring(idx2,filePath.length()));
		
		//字符串拆分splite
		//filePath.split(regex)
	}
}
