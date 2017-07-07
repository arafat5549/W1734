package lesson;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadDemo {


	public static void main(String[] args) throws Exception {
//		MyThread my = new MyThread();
//		my.start(); 
//		//my.run();
//		
//		MyRunnable myrunnable = new MyRunnable();
//		Thread t = new Thread(myrunnable);
//		t.start();
		
		//回调函数 带返回值
		//MyCallable myCallable = new MyCallable();
		//myCallable.call();
		
		//threadDemo();
		
		//counterDemo();
		
		test();
	}
	
	/**
	 * 17、请用程序写出，10个线程对同一个int类型的变量a自增运算，当a==100时，程序结束
	 */
	static void test(){
		//1.创建线程的方式# Runnable
		Runnable r = new Runnable() {
			int a = 0;
			@Override
			public void run() {
				synchronized (this) 
				{
					while(a<10000){
						a++;
					}
					if(a>=100)
					 System.out.println("a="+a);
				}
			}
			
			public int getA(){
				return a;
			}
		};
		
		//2.开10个检查
		for(int i=0;i<10;i++){
			Thread t = new Thread(r);
			t.start();//开线程
		}
		
	}
	
	
	static  void threadDemo(){
		for (int i = 0; i <5; i++) {
			MyThread my = new MyThread();
			my.start();
		}
		//
		MyRunnable myrunnable = new MyRunnable();
		for (int i = 0; i <5; i++) {
			Thread t = new Thread(myrunnable);
			t.start();
		}
	}
	//
	static void counterDemo() throws InterruptedException{
		AtomicCounter myrunnable = new AtomicCounter();
		for (int i = 0; i <10000; i++) {
			Thread t = new Thread(myrunnable);
			t.start();
		}
		Thread.sleep(1000L);
		
		System.out.println("count="+myrunnable.getCount());
	}
}



//计数器
class Counter implements Runnable{
	int count = 0;

	@Override
	public void run() {
		count++;
	}
	
	public int getCount(){
		return count;
	}
}
//推荐方式
class SynCounter implements Runnable{
	int count = 0;

	@Override
	public synchronized void run() {
		count++;
	}
	
	public int getCount(){
		return count;
	}
}

class AtomicCounter implements Runnable{
	AtomicInteger count = new AtomicInteger();
	//int count = 0;
	@Override
	public void run() {
		count.getAndIncrement();
	}
	public int getCount(){
		return count.get();
	}
}
//
class LockCounter implements Runnable{
	Lock lock = new ReentrantLock();//重入锁
    int count = 0;
	@Override
	public void run() {
		try{
			lock.lock();
			count++;
		}finally{
			lock.unlock();//一定要在finally里面市场所
		}
	}
	
	public int getCount(){
		return count;
	}
}

//---------------------------------------------------------------------------

//1.继承Thread类
class MyThread extends Thread{
	static int count = 0;
	@Override
	public void run() {
//		System.out.println("你好");
//		//Daemon 守护线程 一直在后台运行
//		while(true){
//			
//		}
		count++;
		System.out.println("MyThread:"+count);
	}
}
//2.实现Runnable接口
class MyRunnable implements Runnable{
	int count = 0;
	@Override
	public void run() {
		//System.out.println("你好==Runnable");
		count++;
		System.out.println("MyRunnable:"+count);
	}
	
}
//3.回调
class MyCallable implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		return 0;
	}
	
}
