package lesson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * 数据结构
 * 
 * 1.定义和作用描述?
 * 数据结构是计算机存储、组织数据的方式。数据结构是指相互之间存在一种或多种特定关系的数据元素的集合。
 * 通常情况下，精心选择的数据结构可以带来更高的运行或者存储效率。数据结构往往同高效的检索算法和索引技术有关。
 * 
 * 在Java语言里，java的集合包 java.lang.Collection就是根据数据结构定义的一系列实现类，
 * 利用集合类可以让程序运行和存储起来更加高效。
 * 
 * 数组也是一种数据结构类型，集合跟数组相比 最大的特点 可以动态扩容
 * 
 * 2.集合类API
 *  分为两大接口Collection和Map
 *  
 *  List->stack,Set,Queue,Map
 * 3.泛型
 *   什么是泛型
 *   为什么要使用泛型
 * 3-2.多态
 * 3-3.迭代器 
 *   
 * 4.接口方法
 *   Collection接口通用方法：
 *   
 *   添加：
 *   	add(T obj)
 *   	addAll(Collecion)
 *   	retainAll(Collecion)
 *   删除:
 *   	remove
 *   	removeAll
 *   	clear
 *   查询:
 *   	contains
 *    	containsAll
 *    	size()
 *   遍历:
 *      iterator()  迭代器
 *   其他方法:
 *  
 *  集合的特性：
 *  
 *  1.是不是有序 (按照我怕添加的顺序来排列)
 *     -是不是排序的
 *  2.是不是可重复 (如果你存储了同样的元素，是不是只会显示一个)
 *  3.是不是固定长度(除了数组 剩下的集合包里面类都不是固定长度)
 *  4.是不是线程安全(我们平时常用的集合类都是线程不安全 我们如果要保证线程安全会专门有一个包来实现集合的线程安全)
 *  --
 *  java.lang.Collection 集合包
 *  java.lang.Concurrent 并发包
 *  
 *  List，Set，Queue，Map，Stack是什么？各有什么特点
 *  
 *  ArrayList,LinkedList,HashMap各自的特点和区别
 * 
 * @author wyy
 *
 */
public class W20170623_CollectionDemo {

	public static void main(String[] args) {
		//retainDemo();
		//CollectionDemo();
		//List线性表
		//1.有序但不是排序
		//2.可重复
		//3.不定长
		//4.也不是线程安全
		//ListDemo();
		
		//Set数学集合
		//1.无序
		//2.不可重复
		//3.不定长
		//4.也不是线程安全
		//SetDemo();
		
		//队列
		//先进先出
		//queueDemo();
		
		//栈先进后出
		//stackDemo();
		
		//键值对 key-value
		//MapDemo();
	}
	//泛型
	//1.限定了你集合存放的类型  如果你存放了不符合的类型 编译器就会报错
	//2.使用的时候不需要强转类型 (强转还有可能报错)
	//使用集合类一定要使用泛型
	
	
	private static void GenericDemo(){
		//泛型的作用
//		List<String> list = new ArrayList<String>();
//		list.add(1);
//		
//		List list2 = new ArrayList();
//		list2.add(1);
//		for (int i = 0; i < list2.size(); i++) {
//			Object obj = list2.get(i);
//		}
		
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new LinkedList<String>();
		
		demo(list1);
		demo(list2);
		
		ArrayList<String> listx = new ArrayList<String>();
		LinkedList<String> listx2 = new LinkedList<String>();
		demo2(listx);
		demo2(listx2);
		
		//ArrayList<String> listx = new ArrayList<String>();
	}
	//多态 - 面向接口编程
	private static void demo(List<String> list){
		
	}
	
	private static void demo2(ArrayList<String> list){
		
	}
	private static void demo2(LinkedList<String> list){
		
	}
	
	private static void CollectionDemo(){
		//添加：
		//add(T obj)
		//addAll(Collecion)
		//retainAll(Collecion)
		List<String> list = new ArrayList<String>();
		
		list.add("第三个元素");
		list.add("第一个元素");
		list.add("第二个元素");
		list.add("第二个元素");
		
		List<String> sublist = new ArrayList<String>();
		sublist.add("wang");
		sublist.add("hello");
		
		list.addAll(sublist);
		//保留元素
		List<String> retainlist = new ArrayList<String>();
		retainlist.add("第二个元素");
		retainlist.add("wang");
		retainlist.add("hello");
		
//		list.retainAll(retainlist);
//		List<Integer> list2 = new ArrayList<Integer>();
//		list2.add(4);
//		list2.add(1);
//		list2.add(3);
		System.out.println(list);
		
		//删除:
		//remove
		//removeAll
		//clear
		
		//只会删除碰到的第一个元素
		//list.remove("第二个元素");
		
		//removeAll 会删除所有
		List<String> removelist = new ArrayList<String>();
		removelist.add("第二个元素");
		removelist.add("wang");
		removelist.add("hello");
		//list.removeAll(removelist);
		//list.clear();
		System.out.println(list);
		
		//查询:
		//contains
		//containsAll
		//size()
		
		boolean f = list.contains("第二个元素");
		System.out.println(f);
		
		List<String> containslist = new ArrayList<String>();
		containslist.add("第二个元素");
		containslist.add("wang2");
		containslist.add("hello");
		boolean f2 = list.containsAll(containslist);
		System.out.println(f2);
		//获取集合的大小  . 数组的大小是一个属性length ，集合的大小是用size方法来获取
		System.out.println(list.size());
	}
	/**
	 * ArrayList
	 * 
	 * 可变长度数组  有数组的一切特点
	 * 
	 * 查询快(不需要遍历直接通过下标访问)
	 * 增删慢(紧凑结构 就是10000个大小的数组 如果我删除了第三个元素 后面的9997个是不是都得移动一位)
	 * 
	 * 一开始是给10个长度的数组
	 * 什么时候开始扩容
	 * 数组到一半的时候 第6个元素，直接扩容一倍。能保证你扩容的次数不会特别频繁
	 * 
	 * 
	 * LinkedList
	 * 双向链表 
	 * 
	 * 链表:
	 * 查询慢(我只知道我的上一个和下一个节点，如果要查询某个节点的话 需要进行集合的遍历)
	 * 增删快(不管你集合多大 只会有三个元素参与)
	 * Node
	 * 
	 */
	private static void ListDemo(){
		List<String> list = new ArrayList<String>();
		list.add("第一个元素");
		list.add("第二个元素");
		list.add("第三个元素");
		list.add("第四个元素");
		//list.add(1, "第X个元素");
		//list.addAll(index, c)
		
		//list.remove(index);
		
		list.set(1, "第X个元素");
		System.out.println(list);
		System.out.println(list.get(0));
		
		//for(int i =1;i<2;i++)  前开后闭
		System.out.println(list.subList(1, 3));
		
		//遍历的方式 
		//遍历的过程 不要修改你遍历的集合的内容
		//for循环 
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		//for each循环 (推荐)
		for (String string : list) {
			System.out.println(string);
		}
		//迭代器循环
		Iterator<String> it = list.iterator();
		while(it.hasNext()){
			String keyw = it.next();
			System.out.println(keyw);
		}
	}
	/**
	 * 数学集合的概念
	 * 
	 * 1.是不是有序的
	 * 
	 */
	private static void SetDemo(){
//		Set<Integer> set = new HashSet<Integer>();
//		set.add(1);
//		set.add(15);
//		set.add(4);
//		set.add(3);
//		set.add(13);
		Set<String> set = new HashSet<String>();
		set.add("第一个元素");
		set.add("第二个元素");
		set.add("第三个元素");
		set.add("第四个元素");
		set.add("第四个元素");
		for (String integer : set) {
			System.out.println(integer);
		}
		
	}
	/**
	 * Queue队列
	 * FIFO 先进先出
	 * 
	 * List可以移除任意位置的元素 队列一定是先进先出的
	 * 
	 * PriorityQueue自然排序  按照你字符的索引顺序来排
	 */
	private static void queueDemo(){
		Queue<String> queue = new PriorityQueue<String>();
		queue.add("a一个元素");
		queue.add("b二个元素");
		queue.add("cX个元素");
		queue.add("d三个元素");
		queue.add("e四个元素");
		queue.add("f四个元素");
		System.out.println(queue);
		
		String ele = queue.peek();//获得队列收首个元素 但不会移除
		System.out.println(ele);
		System.out.println(queue);
		
		ele = queue.poll();//获得队列收首个元素 并且移除
		System.out.println(ele);
		System.out.println(queue);
		
		queue.clear();
		System.out.println("peek:"+queue.peek());//都是返回第一个元素 如果队列为空peek返回nul
		System.out.println("element:"+queue.element());//都是返回第一个元素 如果队列为空 element会报空指针异常
		
		//queue.remove()
		//queue.add(e)
		//System.out.println((int)'a');
		//System.out.println((int)'A');
	}
	/**
	 * Stack 栈结构
	 * FILO 先进后出
	 */
	private static void stackDemo(){
		Stack<String> stack = new Stack<String>();
		stack.add("a一个元素");
		stack.add("b二个元素");
		stack.add("cX个元素");
		stack.add("d三个元素");
		stack.add("e四个元素");
		stack.add("f四个元素");
		
		System.out.println(stack);
		
		String ele =  stack.peek();
		System.out.println(ele);
		System.out.println(stack);
		
		ele = stack.pop();//获得队列收最后一个个元素 并且移除
		System.out.println(ele);
		System.out.println(stack);
		
		stack.push("a最后一个");//加入到队尾
		
		System.out.println(stack);
		
		//System.out.println(stack.firstElement());
	}
	
	/**
	 * Map键值对
	 * 
	 * HashMap是基于数组的链表
	 * 
	 * key可以重复 不可以重复
	 * key是有序的 
	 * 
	 * Map的key值的集合是一个HashSet
	 */
	private static void MapDemo(){
		Map<String, String> map = new HashMap<String, String>();
		//添加
		String key = "中国";
		map.put(key, "福建省");
		map.put(key, "浙江省");
		map.put(key, "北京市");
		
		map.put("1", "北a京市");
		map.put("aa2aa", "北京市");
		map.put("3", "北京市");
		//删除
		map.remove(key);
		//查询
		//map.containsKey(key)
		//map.containsValue(value)
		//遍历
		Set<String> keyset = map.keySet();
		for (String keyw : keyset) {
			System.out.println(keyw+","+map.get(keyw));
		}
		//迭代器
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String keyw = it.next();
			System.out.println(keyw+","+map.get(keyw));
		}
		
		System.out.println(map);
	}
	
	
	
	private  static void retainDemo(){
		 	List<String> list=new ArrayList<String>();
		    list.add("第一个元素");  //向列表中添加数据
		    list.add("第二个元素");  //向列表中添加数据
		    list.add("第三个元素");  //向列表中添加数据

		    List<String> retainList=new ArrayList<String>();

		    retainList.add("第一个元素");  //向列表中添加数据
		    retainList.add("第三个元素");  //向列表中添加数据

		    boolean ret=list.retainAll(retainList);
		    if(ret){
		    	System.out.println("元素被移除成功");
		    }else{
		    	System.out.println("列表中不包含要移除的元素");

		    }
		    
		    System.out.println(list);
		    
	}
	
	
}
