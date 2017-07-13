package socket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient implements Runnable{

	private int port;
	private String host;
	public EchoClient(String host,int port){
		this.port = port;
		this.host = host;
	}
	
	public static void main(String[] args) {
		
		//1.IP地址#本地Ip地址 127.0.0.1 等价于 localhost
		//localhost最后会被转化为127.0.0.1 本地的DNS配置
		//C:\Windows\System32\drivers\etc\hosts里面保存你们的本地DNS配置
		
		//CDN
		
		//2.端口
		EchoClient ec = new EchoClient("127.0.0.1",8091);
		Thread t = new Thread(ec);
		t.start();
	}

	@Override
	public void run() {
		 Socket socket = null;
		 PrintWriter out = null;
		 BufferedReader in = null;
		try {
			socket = new Socket(host, port);
			
			//while(true)
			{
				out = new PrintWriter(socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				out.println("QUERY TIME ORDER");
				out.flush();//默认不自动刷新 
				System.out.println("Send order 2 server succeed.");

				String resp = in.readLine();
				System.out.println("Now is : " + resp);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
                try{
                    in.close();
                }
                catch (IOException el){
                    el.printStackTrace();
                }
            }

            if(out!=null){
                out.close();
                out=null;
            }

            if(socket != null){
                try{
                    socket.close();
                }catch (IOException el){
                    el.printStackTrace();
                }
                socket=null;
            }
		}
		
	}
	
	
}
