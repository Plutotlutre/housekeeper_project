package Network;

import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.net.*;
import java.util.Iterator;

public class ServNet_Not_mine {

    //�����˿�
    int port = 6000;
    //��������С
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    //������ض���
    Selector selector;
    ServerSocketChannel channel;
    ServerSocket socket;


    public void Start() throws Exception {
        /*��ʼ��һ��Selector*/
        selector = Selector.open();
        /*��ͨ��*/
        channel = ServerSocketChannel.open();
        /*������ģʽ*/
        channel.configureBlocking(false);
        /*����IP*/
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        //InetAddress ip = InetAddress.getLocalHost();
        /*��IP�Ͷ˿�*/
        InetSocketAddress address = new InetSocketAddress(ip,port);
        socket = channel.socket();
        socket.bind(address);
        /*��������*/
        System.out.println("TCP��������ʼ����...");
        Listen();
    }

    /*ֹͣ*/
    public void Stop() throws Exception {
        channel.close();
        selector.close();
    }

    /*����*/
    public void Listen() throws Exception {
        /*ע������¼�*/
        channel.register(selector,SelectionKey.OP_ACCEPT);
        while (true) {
            selector.select();
            /*��ѯ�¼�*/
            Iterator iter = selector.selectedKeys().iterator();
            while (iter.hasNext()) {
                SelectionKey key =  (SelectionKey)iter.next();
                iter.remove();
                /*�¼����ദ��*/
                if (key.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                    System.out.println("���ն�������:"+ sc.getRemoteAddress());
                }
                else if (key.isReadable()) {
                    SocketChannel sc = (SocketChannel)key.channel();
                    int recvCount = sc.read(buffer);
                    if (recvCount > 0) {
                        byte[] arr = buffer.array();
                        System.out.println(sc.getRemoteAddress() + "��������: "+ new String(arr));
                        buffer.flip();
                    }
                    else {
                        sc.close();
                    }
                    buffer.clear();
                }

                else {

                }


            }

        }

    }

}