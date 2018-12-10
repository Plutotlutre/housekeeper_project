package Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Conn {
	//����������
	public final int BUFFER_SIZE = 1024;
	//�����׽���
	public Socket socket;
	//���conn�Ƿ�ʹ����
	public boolean isUse = false;
	//��������
	public byte[] readBuffer = new byte[BUFFER_SIZE];
	//��������ʹ�ó��ȱ��
	public int buffCount = 0;
	//մ������������Ϣռ�û������ĳ���
	public byte[] lenBytes = new byte[4];
	//public User user;
	public int msgLength;
	
	public Conn(){
		//��ʼ��������;
		readBuffer = new byte[BUFFER_SIZE];
	}
	//��ʼ��
	public void Init(Socket socket) {
		this.socket = socket;
		isUse = true;
		buffCount = 0;
	}
	//������ʣ��ռ�
	public int BuffRemain() {
		return BUFFER_SIZE - buffCount;
	}
	
	public String GetAdress() {
		if(!isUse)
			return "�޷���ȡ��ַ";
		return socket.getInetAddress().toString();
	}
	
	public void Close() throws IOException {
		if(!isUse)
			return;
		//if(user != null){}
		System.out.println("[�Ͽ�����]:"+ GetAdress());
		socket.close();
		isUse = false;
	}
}
