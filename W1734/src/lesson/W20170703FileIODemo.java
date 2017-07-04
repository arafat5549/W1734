package lesson;

/**
 * 断点续传
 * @author wyy
 *
 */
public class W20170703FileIODemo {

	//Java的基本数据类型
	boolean boolean1 = false;//布尔值 (错跟对 01)  1位 bit
	//数据
	byte    byte2    = 0x00000000;  //一个字节等于8bit
	char    char3    = 'a';//一个字符 一个字节等于2个byte  16bit;单引号包裹起来的  文字显示用字符)
	//char    error    = 'ax';
	//整型运算(没有小数点就叫整型)
	short short1 = 1; //16位    【-2的8次方- 2的8次方-1】
	int   int1   = 1; //32位 这也是我们默认大小
	long  long1  = 1L;//(Long一般加大写L后缀 显示指定你是long型 L跟1区分开来)
	//long error = 1l;
	//浮点数float
	float float1 = 1.1f;//16位 要求强制加f后缀，如果不写后缀就是double类型的
	double double1 = 1.1d;//32位
	
	//字符和字节
	  //字节不能直接显示为文字 ,需要根据你的编码集charset  编码过encoding某个类型的文件编码
	//文件编码
	  //ascii   1byte=8位
	  //gb2312 ， gbk简体 ， big5繁体编码   中文编码 2byte=16位
	  //Unicode编码   UTF-8 UTF-8带BOM   3byte
	               //UTF-16 UTF-16带BOM的 4byte
	  //ISO-8859-1 %BE 浏览器编码(url编码)  2byte16位
	
	//1.ascii美国标准编码 计算机是美国发明的 早期他们的文字不会超过256个 1byte足够
	//2.计算机不断发展，扩展到其他国际 1byte不够用 每个国家就会扩展自己的编码集
	//一般都是2byte 可以表示65536个字符
	
	//3.不同国家有自己的编码类型而且互相不统一(gb2213,gbk,big5)不同国家根据自己的需要发展自己的编码 没有统一标准十分混乱
	    //操作系统的开发商为了解决这种情况 引入unicode编码
	    //UTF-8 3byte 他有一byte表示你们什么类型的语言 2byte
	    //utf-16 4BYTE  不管什么字符都是4byte(英文字符也要占4个byte 太浪费空间)
	    //utf-8带BOM   windows特有的
	    //utf-16带BOM  windows特有的
	    //结论:推荐使用不带bom的utf-8(解决中文乱码问题 统一用)
	
	
	
	//IO读取
	
	
	
}
