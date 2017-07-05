package lesson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class W20170705StringDemo {

	public static void main(String[] args) throws IOException {
		//cipherDemo();
		
		randomDemo();
	}
	
	/**
	 * 从键盘上读入一行文本,对其中的字母进行加密。加密原则是：将每个字母向后移动2位，
	 * 例如，’a’变成’c’, ‘Y’变成’A’, ‘z’变成’b’, 非字母不改变。
	 * @throws IOException 
	 */
	//读键盘文本
	
	//换行符 \r\n
	//制表符 \t		四个空格
	static void cipherDemo() throws IOException{
		while(true)
		{
			InputStream in = System.in;
			byte buf[] = new byte[1024];
			int pot=-1;
			StringBuffer sb = new StringBuffer();
			if((pot=in.read(buf))!=-1){
				sb.append(new String(buf,0,pot));
			}
			//System.out.println("quit".equals(sb.toString()));
			if("quit".equals(sb.toString().replace("\r\n", "")))
			{
				//cipher(sb.toString());
				System.out.println("#"+sb.toString());
				System.exit(0);
				return;
			}
			else
				cipher(sb.toString());
		}
		
	}
	
	//第一步 处理字符串
	static void cipher(String str){
		//String str = "adasdasdasdasdzZaaz";
		            //cfcufcufcufcufbBccb
		char array[] = str.toCharArray();
		for (int i=0;i<array.length;i++) {
			char c = array[i];
			if(c>='a' && c<='z'){
				c+=2;
				if(c>'z'){
					c-=26;
				}
			}
			else if(c>='A' && c<='Z'){
				c+=2;
				if(c>'Z'){
					c-=26;
				}
			}
			array[i] = c;
		}
		System.out.println(new String(array));	
	}
	
	
	/**
	 * 16. 在0～100之间产生等差概率随机数
	 * 
	 *  0-25 	40%
	 *  25-50 	30%
	 *  50-75 	20%
	 *  75-100 	10%
	 */
	static void randomDemo(){
		Random rand = new Random();
		
		int result[] = new int[10];
		
		ArrayList<Integer> list  =new ArrayList<Integer>();
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(0);
		
		list.add(1);
		list.add(1);
		list.add(1);
		
		list.add(2);
		list.add(2);
		
		list.add(3);
		//第一次随机 随机抽取四个桶之间的一个
		for(int i=0;i<result.length;i++){
			Integer idx = rand.nextInt(list.size());
			Integer r1 = list.get(idx);
			list.remove(r1);
			//2.在抽取的桶里面再做随机
			switch(r1){
			    case 0: result[i] = rand.nextInt(25); break;
			    case 1: result[i] = 25+rand.nextInt(25); break;
			    case 2: result[i] = 50+rand.nextInt(25); break;
			    case 3: result[i] = 75+rand.nextInt(25); break;
			}
		}
		
		Arrays.sort(result);
		
		for (int i : result) {
			System.out.print(i+",");
		}
		//rand.nextInt();// 2pow-16 - 2pow16-1
		//rand.nextInt(100);//[0-99], 
		//int x =50 + rand.nextInt(51);//[50-100]

	}
}
