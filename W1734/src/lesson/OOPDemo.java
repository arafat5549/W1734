package lesson;

public class OOPDemo {

    public OOPDemo(){
    	System.out.println("2.构造器 WOOPDemo");
    }
	
	private int value = 0; 
	public static int value2 = 0;
	
	public void dddd(){
		System.out.println("OOPDemo###dddd");
	}
	
	public static void test(){
		
	}
	
	public static void main(String[] args) {
//		for (int i = 0; i < 10; i++) {
//			OOPDemo p = new OOPDemo();
//			p.value++;
//			OOPDemo.value2++;
//		}
//		
//		OOPDemo oop = new OOPDemo();
//		System.out.println(oop.value+","+OOPDemo.value2);
		//OOPDemo p = new OOPDemo();
		//p.dddd();
		//IoDemo m = IoDemo.getInstance();
		
		OOPDemo p2 = new Child();
		p2.dddd();
	}
	static{
		System.out.println("--------1.OOPDemo静态初始化-----------------");
	}
}

class Child extends OOPDemo{
	static{
		System.out.println("--------1.Child静态初始化-----------------");
	}
	
    public Child(){
    	System.out.println("2.构造器 Child");
    }
	
	private int value = 0; 
	public static int value2 = 0;
	
	public void dddd(){
		super.dddd();
		System.out.println("Child#dddd");
	}
}
