package net;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class send2 {
	public static void main(String[] args) throws IOException {
		String host = "localhost"; 
		int port = 6000;
		// �����˽�������
		Socket socket = new Socket(host, port);
		//socket.connect(new InetSocketAddress(host, port));
		String str = "1234";
        PrintStream out = new PrintStream(socket.getOutputStream());
        System.out.println("ready send info to server!!");
        out.println(str);
        System.out.println("send info to server: "+ str);
        //���ͺ�ϵ�����
        out.close();
	}
}
