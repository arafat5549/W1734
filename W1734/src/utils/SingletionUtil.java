package utils;

import lesson.IoDemo;

/**
 * 单例模式和懒加载的方式
 * @author wyy
 *
 */
public class SingletionUtil {

	//单例模式
	//1.私有化构造器
	private SingletionUtil(){}
	//2.私有化静态实例
	private static SingletionUtil instance;// = new IoDemo();
	//3.公有实例方法给外部调用
	public  static SingletionUtil getInstance(){//懒加载
		if(instance == null){
			instance = new SingletionUtil();
		}
		return instance;
	}
}
