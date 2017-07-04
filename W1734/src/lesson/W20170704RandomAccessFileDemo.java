package lesson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


@SuppressWarnings({"unused","resource"})
public class W20170704RandomAccessFileDemo {

	public static void main(String[] args) throws IOException {
		String baseName = "";
		//spliteImg(10);
		//joinImg(10);
	}
	static void baseDemo() throws IOException{
		//1.你要操作的文件
		File file = new File("F:/RSATest.txt");
		//r  只读
		//rw 如果文件不存在会尝试创建
		//rws 文件内容和文件metadata原信息 synchronize同步
		//rwd 文件内容必须同步写
		RandomAccessFile raf = new RandomAccessFile(file,"r");
		raf.seek(0);//把光标置换为第几个byte
		raf.skipBytes(1);//跳过多少个byte不读
		raf.length() ;// 文件有多少byte
	}
	
	//文件加密(从一个随机的位置 随机插入一段加密文本)
	//断点续传
	//1.拆分为10块 获取每块的大小还有剩余的部分给最后一块
	//2.小块要写出去 [0,itemSize] [itemSize,itemSize*2]
	
	static void spliteImg(int block) throws IOException{
		File file = new File("F:/test.jpg");
		RandomAccessFile raf = new RandomAccessFile(file,"r");
		long size = raf.length();
		//int block = 10;//byte#总共要分多少块
		
		long item   = size/block;//每一块有多大
		long remain = size%block;//最后一块多大
		System.out.println(item+","+remain);
		
		for (int i = 0; i < block; i++) {
			File f = new File("F:/demo/test.jpg_"+i);
			if(!f.getParentFile().exists()){
				f.getParentFile().mkdirs();
			}
			raf.seek(item*i);
			long byteSize = i==block-1 ? item+remain : item;
			byte buf[] = new byte[(int)byteSize];//准确来讲要考虑 超过int大小的bytes
			int pot = -1;
			if ((pot = raf.read(buf))!=-1) {
				//sb.append(new String(buf,0,pot));
			}
			RandomAccessFile nraf = new RandomAccessFile(f,"rw");
			nraf.write(buf);
			nraf.close();
		}
		raf.close();
	}
	//Join 合并拆分数据
	static void joinImg(int block) throws IOException{
		File file = new File("F:/demo/test2.jpg");
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		RandomAccessFile raf = new RandomAccessFile(file,"rw");
		for (int i = 0; i < block; i++) {
			File f = new File("F:/demo/test.jpg_"+i);
			RandomAccessFile nraf = new RandomAccessFile(f,"r");
			byte buf[] = new byte[1024];
			int pot = -1;
			while ((pot = nraf.read(buf))!=-1) {
				raf.write(buf,0,pot);
			}
			nraf.close();
		}
		raf.close();
	}
}
