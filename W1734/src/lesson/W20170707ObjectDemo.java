package lesson;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class W20170707ObjectDemo {
	//abstract void haha();
	
	
	
	public static void main(String[] args) {
		Demo testDemo = new Demo();
		final Demo demo = testDemo;
		testDemo.name = "haha";
		
		System.out.println(demo.name);
	}
	
	List<String> list = new ArrayList<>();
	ArrayList<String> list2 = new ArrayList<>();
	
	public void dolist(List<String> list2){
		
	}
	
	public void dolist(ArrayList<String> list2){
		
	}
	public void dolist(LinkedList<String> list2){
		
	}
}
class Demo{
	public String name ="";
}

//1.抽象类

//1.抽象方法只能在抽象类里面(抽象类可以没有抽象方法) 抽象方法没有方法体
//3.抽象类不能实例化
//4.抽象类可以有非抽象方法
//5.抽象方法可以是私有的吗,不能是私有的
//6.抽象类是被继承，继承他的类要实现他的抽象方法
abstract class C1{
	abstract void haha();
	//private abstract void kkkk();
	public void lala(){
		
	}
}

abstract class C2 extends C1{
	abstract void haha2();
}

class C1Impl extends C2{

	@Override
	void haha() {
		
	}

	@Override
	void haha2() {
		// TODO Auto-generated method stub
		
	}

}


//API(ApplicationPublicInterface)应用程序接口

//接口Interface(更纯粹更严格的抽象类)
//1.接口里面的所有方法都是 public和abstract的
//2.不能有非抽象方法
//3.别的类是来实现接口Implement
interface A1{
	int MAX =1;
}
class A1Impl implements A1{
	public static void main(String[] args) {
		//A1Impl.MAX = 1;
	}
}