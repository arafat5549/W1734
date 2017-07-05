package lesson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import poll.Reward;

/***
 * 
 * 
 * 自定义对象的比较
 * 
 * 怎么比较对象相等
 * 1.如何Hash集合 需要重写 hashCode方法
 * 2.重写equals方法
 * 
 * 怎么比较对象大小 排序集合(TreeSet和TreeMap)
 * 1.实现Comparable接口
 * 2.实现compareTo方法
 * 
 * @author wyy
 *
 */
public class CollcetionDemo {

	//集合的查询  方法equals
	static void queryDemo(){
		Reward reward1 = new Reward(1,"鞋子1",0.05);
		Reward reward2 = new Reward(12,"鞋子2",0.05);
		Reward reward3 = new Reward(13,"鞋子3",0.05);
		Reward reward4 = new Reward(4,"鞋子4",0.05);
		Reward reward5 = new Reward(5,"鞋子5",0.05);
		Reward reward6 = new Reward(6,"鞋子6",0.05);
		Reward reward7 = new Reward(22217,"鞋子7",0.05);
		Reward reward8 = new Reward(1218,"鞋子8",0.05);
		Reward reward9 = new Reward(129,"三等奖",0.1);
		Reward reward10 = new Reward(1210,"谢谢参与",0.5);
		
		Set<Reward> list = new TreeSet<>();
		
//		List<Reward> list = new ArrayList<>();
		list.add(reward1);
		list.add(reward2);
		list.add(reward3);
		list.add(reward4);
		list.add(reward5);
		list.add(reward6);
		list.add(reward7);
		list.add(reward8);
		list.add(reward9);
		list.add(reward10);
//		//重写equals方法  如果使用的是hashSet hashMap比对相等还要重写hashcode
//		Reward exist = new Reward(1,"鞋子1",0.05);
//		boolean flag = list.contains(exist);
//		System.out.println("包含1:"+flag);
//		flag = list.contains(reward1);
//		System.out.println("包含2:"+flag);
		
		for (Reward reward : list) {
			System.out.println(reward);
		}
	}
	
	
	//增删改查
	static void collectionDemo(){
		List<String> list = new ArrayList<String>();
		//增
		//list.add(e);
		//list.addAll(c);
		//数组相关可以按索引下标来访问
		///list.add(0, element);
		
		//删除
		//list.remove(o)
		//list.clear();
		
		//改
		//list.set(index, element)
		
		//查
		//list.get(index)
		//list.contains(o) //包含 equals方法
		
		//Set集合没有按下标的方法 它是无序的
		Set<String> set = new HashSet<String>();
//		set.add(e)
//		set.remove(o)
//		set.clear();
//		set.contains(o)
		
		//
		for (int i = 0; i < list.size(); i++) {
			String value = list.get(i);
		}
		//迭代器遍历 # 可以对无序集合进行遍历 (设定遍历的过程中集合不能改变)
		Iterator<String> it = list.iterator();
		while(it.hasNext()){
			String value = it.next();
		}
		
		
		//遍历集合
		for (String string : list) {
			
		}
		
		
	}
	//键值对  { "a":13 ,"b":20}
	static void mapDemo(){
		Map<String,String> map = new HashMap<>();
		//增加
		//map.put(key, value)
		//修改#如果你key值已经存在的话
		//map.put(key, value)
		//删除
		//map.remove(key)
		//查找
		//map.containsKey(key)
		//map.containsValue(value)
		
		
		//Map的遍历  key的集合是一个HashSet
		
		Set<String> keyset =map.keySet();
		for (String key : keyset) {
			System.out.println(key+","+map.get(key));
			
		}
		Set<Entry<String,String>> entrySet =map.entrySet();
		for (Entry<String,String> entry : entrySet) {
			System.out.println(entry.getKey()+","+entry.getValue());
		}
	}
	
	public static void main(String[] args) {
		queryDemo();
	}
}
