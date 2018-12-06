package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServNet {

	public Socket socket;
	public ServerSocket serverSocket;
	public static Conn[] conns;
	public static int maxConn = 50;
	//����
	public static ServNet instance;
	public ServNet() {
		instance = this;
	}
	
	//�����������Ѱ�ҿ�ʹ�õ�conn
	public int NewIndex() {
		if(conns == null) {
			return -1;
		}
		for(int i = 0; i < conns.length; i++) {
			if(conns[i] == null) {
				conns[i] = new Conn();
				return i;
			}else if(conns[i].isUse == false) {
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		ServNet servNet = new ServNet();
		servNet.Start(6000);
		
	}
	
	public void Start(int port) throws IOException {
		//��ʼ�����ӳ�
		conns = new Conn[maxConn];
		//��ʼ�����ӳ�
		for(int i = 0; i < maxConn; i++) {
			conns[i] = new Conn();
		}
		
		serverSocket =new ServerSocket(port);
		Accept();
	}
	
	public void Accept() throws IOException {
		ExecutorService threadPool = Executors.newFixedThreadPool(100);
		while(true){
			Runnable runnable=()->{
			try {
					Socket socket = serverSocket.accept();
					int index = NewIndex();
					if(index < 0) {
						socket.close();
						System.out.println("��������");
						return;
					}
					Conn conn = conns[index];
					conn.Init(socket);
					String str = conn.GetAdress();
					System.out.println("�ͻ������� ["+str+"]");
		    		// ���������Ӻ󣬴�socket�л�ȡ�����������������������ж�ȡ
		            InputStream inputStream = socket.getInputStream();
		            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
		            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
	                String clientContent = null;
		          /*StringBuilder strb = new StringBuilder();
		            while ((strb = inputStream.read(conn.readBuffer)) != -1) {
		            	strb.append(new String(conn.readBuffer, 0, conn.buffCount, "UTF-8"));
		            }
		            System.out.println("get message from client: " + strb);*/
	                while((clientContent=bufferedReader.readLine()) != null){
	                	System.out.println(clientContent);
	                }  
	                
		            inputStream.close();
		         //  socket.close();
		            } catch (Exception e) {
		            e.printStackTrace();
		            }
		    	};
		        threadPool.submit(runnable);
		}
	 }
	
	public void Close() throws IOException {
		 for(int i = 0; i < conns.length; i++) {
			 Conn conn = conns[i];
			 if(conn == null) continue;
			 if(!conn.isUse) continue;
		/*	 Lock lock = new ReentrantLock();
			 lock.lock();
			 try{
			     //��������*/
			 conn.Close();
		/*	 }catch(Exception ex){

			 }finally{
			     lock.unlock();   //�ͷ���
			 }*/
		 }
	}
	
	public void Send(Socket socket, String str) throws IOException {
		OutputStream out = null;
		out = socket.getOutputStream();
        out.write(str.getBytes());
        System.out.println("send info to server: "+ str);
        //���ͺ�ϵ�����
        out.close();
	}
}

