package lesson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class IoDemo {
	
	//MAIN方法就是我们程序的入口
	public static void main(String[] args) throws IOException {
		/*
		//System.out.println("Hello World!");
		//1.导入相应的包  统一选择	java.io.*
		//1.确认流的类型 输入流和字符流 FileReader
		File file = new File("F:/2.html");//文件的全路径
		//两种文件分割符的写法  F:/demodemo.txt  F:\\demodemo.txt
		//String c = File.separator;
		FileReader fr = new FileReader(file);//ctrl +shirt+o
		StringBuffer sb = new StringBuffer();
		char cbuf[] = new char[1024];//2的整数倍  设置文件读取缓存(这样子可以提高读取效率)
		int pot = -1;
		int count = 0;
		//1.buf数组被填满 返回-1
		//2.文件结尾也会返回-1
		while ((pot = fr.read(cbuf))!=-1) {
			count++;
			sb.append(new String(cbuf,0,pot));
		}
		//fr.read(cbuf);
		//String content = new String(cbuf);
		System.out.println("次数:"+count);
		System.out.println(sb.toString());
		*/
		
//		String content = readFile_Stream("F:/2.html");
//		System.out.println(content);
//		
//		writeFile_Stream("F:/abcde/a.demo", content);
		
		//String content = readFile_Stream("F:/abcde/a.demo");
		//readWrite("F:/RSATest.txt",content);
		
//		String content = readFileBuffer("F:/RSATest.txt");
//		System.out.println(content.getBytes().length);
//		System.out.println(content);
		
		//fileDemo();
		
		//RAFDemo(10);
		
		RAFDemoJoinFile();
	}
	
	/**
	 * File类的具体操作
	 * 
	 * 在Java里面File类既能文件也能表示文件夹
	 * @throws IOException 
	 */
	static void fileDemo() throws IOException{
		//1.如何区分File类是文件还是文件夹
		
		String filePath = "F:/RSATest.txt";
		String dirPath = "F:/dirTest.txt";
		File file = new File(filePath);
		File dir = new File(dirPath);
		System.out.println("是文件?"+file.isFile());
		System.out.println("是文件夹?"+dir.isDirectory());
		
		//创建文件夹
		//if(!dir.exists())
		//	dir.mkdir(); //dir.mkdirs()
		//创建
		//if(!dir.exists())
		//	dir.createNewFile();
		//只能有一份逻辑含义 比如dirPath可以有文件也可以为文件夹但是只能为其中一个
		
		//删除  delte / remove / clear
		//dir.delete();
		
		//文件夹的遍历
		listFiles(new File("F:/Python27"));
	}
	//文件夹的遍历 递归(1.函数调用函数本身 2.函数一定要收敛)
	static void listFiles(File root){//跟路径
		
		File[] files = root.listFiles();
		for (File file : files) {
			if(file.isDirectory()){
				listFiles(file);
			}else{
				if(file.getName().endsWith(".exe")){
					System.out.println(file);
					System.out.println(file.getName());
					System.out.println(file.getAbsolutePath());
					System.out.println("---------------------------------------------");
				}
				
				
			}
		}
	}
	/**
	 * 断点续传 (一定要用字节流)
	 * 
	 * 0-1000byte
	 * 0-500 ， 500-100
	 * 
	 * RandomAccessFile
	 * @throws IOException 
	 */
	static void RandomAccessFileDemo() throws IOException{
		String filePath = "F:/RSATest.txt";
		RandomAccessFile raf = new RandomAccessFile(filePath,"r");
		
		raf.seek(0);
		//raf.skipBytes(2);
		StringBuffer sb = new StringBuffer();
		byte buf[] = new byte[30];
		raf.read(buf,0,buf.length);
		sb.append(new String(buf,0,buf.length));
//		int pot = -1;
//		while ((pot = raf.read(buf))!=-1) {
//			sb.append(new String(buf,0,pot));
//		}
		raf.close();
		System.out.println(sb.toString());
	}
	//断点续传
	//(取模 %)
	static void RAFDemo(int time) throws IOException{
		//1.获取源文件的大小
		String filePath = "F:/RSATest.txt";
		RandomAccessFile raf = new RandomAccessFile(filePath,"r");
		
		long size =(raf.length() / time);
		long extra = raf.length() % time;
		System.out.println("源文件大小:"+raf.length());//byte
		System.out.println("每份大小:"+size+","+extra);//byte
		//System.out.println(raf.get);
		
		for(int i=0;i<10;i++)
		{
			RAFDemoSplitFile(raf,filePath,300*i,300,i);
		}
		RAFDemoSplitFile(raf,filePath,3000,raf.length()-3000,10);
	}
	//拆分为固定大小的文件
	static void RAFDemoSplitFile(RandomAccessFile raf,String filePath,long idx,long bytes,int number) throws IOException{
		raf.seek(idx);
		byte buf[] = new byte[(int)bytes];//准确来讲要考虑 超过int大小的bytes
		int pot = -1;
		if ((pot = raf.read(buf))!=-1) {
			//sb.append(new String(buf,0,pot));
		}
		//F:/RSATest.txt_0
		System.out.println((int)bytes+"-"+buf.length+"-"+pot);
		//System.out.println(sb.toString());
		String newFileName= filePath +"_"+number;
		RandomAccessFile newraf = new RandomAccessFile(newFileName,"rw");
		newraf.write(buf);
		newraf.close();
	}
	//拼接这些零碎的文件
	static void RAFDemoJoinFile() throws IOException{
		
		String filePath = "F:/RSATest.txt";
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<11;i++)
		{
			String newFileName= filePath +"_"+i;
			RandomAccessFile newraf = new RandomAccessFile(newFileName,"r");
			
			byte buf[] = new byte[1024];
			int pot = -1;
			while ((pot = newraf.read(buf))!=-1) {
				sb.append(new String(buf,0,pot));
			}
			newraf.close();
		}
		System.out.println(sb.toString());
		
		writeFile("F:/断点续传.txt", sb.toString()); 
	}
	
	//JDK默认提供的缓存读取reader#推荐方式
	static String readFileBuffer(String path)  throws IOException{
		File file = new File(path);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		StringBuffer sb = new StringBuffer();
		char cbuf[] = new char[1024];
		int pot = -1;
		while ((pot = fr.read(cbuf))!=-1) {
			sb.append(new String(cbuf,0,pot));
		}
		br.close();
		return sb.toString();
		
	}
	
	/**
	 * ---------------------------------------------------------------------------
	 */
	//抽取成通用方法  读取文件#字符流的方式
	static String readFile(String path) throws IOException{
		File file = new File(path);
		
		
		//String c = File.separator;
		FileReader fr = new FileReader(file);
		StringBuffer sb = new StringBuffer();
		char cbuf[] = new char[1024];
		int pot = -1;
		while ((pot = fr.read(cbuf))!=-1) {
			sb.append(new String(cbuf,0,pot));
		}
		//System.out.println(sb.toString());
		fr.close();
		return sb.toString();
	}
	//读取文件#字节流的方式
	static String readFile_Stream(String path) throws IOException{
		File file = new File(path);
		FileInputStream fi = new FileInputStream(file);
		StringBuffer sb = new StringBuffer();
		//这个时候缓存的是字节数组
		byte buf[] = new byte[1024];
		int pot = -1;
		while ((pot = fi.read(buf))!=-1) {
			sb.append(new String(buf,0,pot));
		}
		fi.close();
		return sb.toString();
	}
	
	//写文件的方式  # 1.直接覆盖源文件  2.先读再写 append,在原有文件的基础上添加内容
	
	//写入文件 #字符流的方式
	/**
	 * 
	 * @param path     写入文件的全路径 路径+名称
	 * @param content  你要写入的内容
	 */
	static void writeFile(String path,String content) throws IOException{
		File file = new File(path);
		if(!file.getParentFile().exists()){//如果你中间的文件夹不存在
			file.getParentFile().mkdirs(); //mkdir
		}
		FileWriter fw = new FileWriter(file);
		fw.write(content);
		fw.close();//记得强制关闭流
	}
	//写入文件 #字节流的方式
	static void writeFile_Stream(String path,String content)  throws IOException{
		File file = new File(path);
		if(!file.getParentFile().exists()){//如果你中间的文件夹不存在
			file.getParentFile().mkdirs(); //mkdir
		}
		FileOutputStream fo = new FileOutputStream(file);
		
		fo.write(content.getBytes());
		//fo.write(b, off, len);
		fo.close();
	}
	
	//文件的读写操作 
	/**
	 * 
	 * @param path
	 * @param content
	 */
	static void readWrite(String srcPath,String content)throws IOException{
		String f = readFile_Stream(srcPath);//
		writeFile_Stream(srcPath, f+content);
	}
	
	//统计一个文件中 英文字符，数字字符，中文字符出现的总次数
	//static void 
}
