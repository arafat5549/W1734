package lesson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class W20170707INterviewDemo {

	//16、有一个数组a[n]（数组有8个元素，每个元素是0-1000的随机数），用java代码将数组元素顺序颠倒	
	//随机类 # 数组 # 数组之间的交换
	static void test1(){
		int a[] = new int[8];
		Random random = new Random();
		
		for (int i = 0; i < a.length; i++) {
			a[i] = random.nextInt(1001);
		}
		System.out.println(Arrays.toString(a));
		
		//第一种做法 新建一个数组
//		int b[] = new int[8];
//		for (int i = 0; i < b.length/2; i++) {
//			b[i] = a[8-i];
//		}
		
		//交换
		for (int i = 0; i < a.length/2; i++)
		{
			int temp =  a[8-1-i];
			a[8-1-i] = a[i];
			a[i] = temp; 
		}
		System.out.println(Arrays.toString(a));
	}
	//17、一个5位数，判断它是不是回文数。即45654、35653是回文数，个位与万位相同，十位与千位相同
	public static void test2(){
		int max = 121212121;//max+""
		char cs[]= String.valueOf(max).toCharArray();
		boolean flag = true;
		for (int i = 0; i < cs.length / 2; i++) {
			if(cs[i] != cs[8-1-i]){//用等号判断，需要遍历完循环()
				//像这种全部都要有的循环 可以反向来判断
				flag = false;
				break;//跳出循环
			}
		}
		 
		System.out.println(flag ? max+"是回文数" : max+"不是回文数");
	}
	//18、随机生成一个20大小的数组（数的范围在0-100之间），进行冒泡排序
	//http://zh.visualgo.net  可见
	public static void bubbleSort(){
		Random random = new Random();
		int arr[] = new int[20];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(101);
		}
		System.out.println(Arrays.toString(arr));
		
		//最基本的冒泡排序
//		for (int i = 0; i < arr.length; i++) {
//			for (int j = 0; j < arr.length; j++) {
//				if(arr[i] < arr[j]){
//					int temp =  arr[j];
//					 arr[j] = arr[i];
//					 arr[i] = temp; 
//				}
//			}
//		}
		//
		for (int i = 0; i < arr.length; i++)
		{
			for (int j = i; j < arr.length; j++)
			{
				if(arr[i] > arr[j]){
					int temp =  arr[j];
					 arr[j] = arr[i];
					 arr[i] = temp; 
				}
			}
		}
		
		System.out.println(Arrays.toString(arr));
	}
	
	//
	static boolean test4Contains(int arr[],int value){
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == value){
				return true;
			}
		}
		return false;
	}
	
	//数组里面去掉重复的元素
	private static void test4Plus(){
//		Random random = new Random();
//		int arr[] = new int[10];
//		for (int i = 0; i < arr.length; i++) {
//			arr[i] = random.nextInt(10);
//		}
//		System.out.println(Arrays.toString(arr));
//		
//		int set[] = new int[10];
//		
//		for (int i=0;i<arr.length;i++) {
//			
//			if(test4Contains(set,arr[i])){
//				
//			}else{
//				//set.add(value);
//				set[i] = arr[i];
//			}
//		}
	}
	
	
	//19、去掉一个List集合中重复的元素
	private static void test4(){
		Random random = new Random();
		//初始化
		List<Integer> list = new ArrayList<Integer>();
		for (int i=0;i<10;i++) {
			list.add(random.nextInt(10));
		}
		System.out.println(list );
		Set<Integer> set = new HashSet<Integer>();
		for (int i=0;i<list.size();i++) {
			Integer value = list.get(i);
			if(set.contains(value)){
				list.remove(i);
			}else{
				set.add(value);
			}
		}
		System.out.println(list );
//		//新建一个SET
//		Set<Integer> set = new HashSet<Integer>();
//		for (Integer value : list) {
//			if(set.contains(value)){
//				
//			}else{
//				set.add(value);
//			}
//		}
//		System.out.println(set);
		
//		int arr[] = new int[10];
//		for (int i = 0; i < arr.length; i++) {
//			arr[i] = random.nextInt(10);
//		}
//		System.out.println(Arrays.toString(arr));
//		
//		int demo[] = new int[10];
//		for (int i : demo) {
//			i = -1;
//		}
//		//去除数组中的重复元素
//		
//		//1.判断有没有重复
//		for (int i = 0; i < arr.length; i++) {
//			//
//			int idx  = arr[i];
//			for (int j = 0; j < demo.length; j++) {//contains
//			     if(demo[j] == idx){	
//			    	 continue;
//			     }
//			     else{
//			    	 
//			     }
//			     
//			}
//		}
//		
//		//2.去掉重复
	}
	
	//   10*9*8*7*6/(5*4*3*2*1)
	//21、请用程序写出1到10十个数字中任意取5个数字的所有组合 (没有顺序之分即 12345和54321算一个数) 
	//（比较难）
	private  static void test5(){
		
		int count = 0;
		List<Set<Integer>> list = new ArrayList<Set<Integer>>();
		for (int a = 1; a <= 10; a++) {
			
			for (int b = 1; b <= 10; b++) {
				if(a==b){
					continue;
				}
				for (int c = 1; c <= 10; c++) {
					if(a==c || b ==c){
						continue;
					}
					for (int d = 1; d <= 10; d++) {
						if(a==d || b ==d || c==d){
							continue;
						}
						
						for (int e = 1; e <= 10; e++) {
							if(a==e || b ==e || c==e || d==e){
								continue;
							}
							//1.去掉重复的元素 a/b/c/d/e
							
							//2.去掉5个不能相同 12345和54321是同一个树而不是两个数							
							Set<Integer> set = new HashSet<Integer>();
							set.add(a);
							set.add(b);
							set.add(c);
							set.add(d);
							set.add(e);
							if(!list.contains(set))
								list.add(set);
							
							count++;
						}
					}
				}
			}
		}
		
		System.out.println((10*9*8*7*6)/(5*4*3*2*1)+","+count/(5*4*3*2*1));
		System.out.println(list.size());
	}
	
	//1.后一个数一定比前一个数大
	//2.所有的可能性应该是越来越小
	static void test5Plus(){
		int count = 0;
		for (int a = 1; a <= 10; a++) {
			for (int b = a + 1; b <= 10; b++) {
				for (int c = b + 1; c <= 10; c++) {
					for (int d = c + 1; d <= 10; d++) {
						for (int e = d + 1; e <= 10; e++) {
							count++;
						}
					}
				}
			}
		}
		//
		System.out.println(count);
	}
	
	public static void main(String[] args) {
		//test1();
		//test2();
		
		//bubbleSort();
		
		//test4();
		
		//test5();
		test5Plus();
	}
	
	
}
