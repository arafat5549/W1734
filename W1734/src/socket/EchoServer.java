package socket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class EchoServer implements Runnable {
	
	public static void main(String[] args) {
		
		EchoServer es = new EchoServer();
		Thread t = new Thread(es);
		t.start();
	}
	
    private int port = 8091;
	@Override
	public void run() {
		ServerSocket server = null;
		Socket socket = null;
		
		 PrintWriter out = null;
		 BufferedReader in = null;
		try {
			server = new ServerSocket(port);//1.服务器建立监听端口
			System.out.println("Server Start in port:"+port);
			while(true)
			{
				socket = server.accept();//2.建立Socket连接
				System.out.println("--------");
			   //3.处理数据流 - 
				out = new PrintWriter(socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				out.println("Server");
	            System.out.println("Send order 2 client succeed.");
				
	            String resp = in.readLine();
	            System.out.println("Server#Now is : " + resp);
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
//	
//	public static void main(String[] args) {
//		
//		int port = 8091;
//		
//		ServerSocket server = null;
//		Socket socket = null;
//		try {
//			server = new ServerSocket(port);//1.服务器建立监听端口
//			System.out.println("Server Start in port:"+port);
//			while(true){
//				socket = server.accept();//2.建立Socket连接
//				System.out.println("--------");
//				//3.处理数据流 - 
//				new Thread(new TimerServerHandler(socket)).start();
//			}
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//
//
//}
//
//
//class TimerServerHandler implements Runnable{
//	Socket socket =null;
//	public TimerServerHandler(Socket socket){
//		this.socket = socket;
//	}
//	//
//	@Override
//	public void run() {
//		BufferedReader in = null;  //客户端给我发的信息
//		PrintWriter out = null;    //我给客户端发的信息
//		try {
//			 out = new PrintWriter(socket.getOutputStream());
//			 in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			 
//			 String currentTime = null;
//			 String body = null;
//			 //while(true) 
//			 {
//	                body = in.readLine();
//	                System.out.println("body="+body);
//	               // if (body == null) break;
//	                System.out.println("recvive order : " + body);
//
//	                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(
//	                        System.currentTimeMillis()).toString() : "BAD ORDER";
//	                
//	                out.println(currentTime);
//	            }
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}finally{
//			 if(in!=null){
//	                try{
//	                    in.close();
//	                }
//	                catch (IOException el){
//	                    el.printStackTrace();
//	                }
//	            }
//
//	            if(out!=null){
//	                out.close();
//	                out=null;
//	            }
//
//	            if(this.socket != null){
//	                try{
//	                    this.socket.close();
//	                }catch (IOException el){
//	                    el.printStackTrace();
//	                }
//	                this.socket=null;
//	            }
//	        }
//			
//	}
//}