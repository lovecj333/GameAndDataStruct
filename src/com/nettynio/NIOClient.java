package com.nettynio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {

    public static void main(String[] args) throws Exception {
        //得到一个网络通道
        SocketChannel channel = SocketChannel.open();
        //设置非阻塞方式
        channel.configureBlocking(false);
        //提供服务器端的IP地址和端口号
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9999);
        //连接服务器端
        if(!channel.connect(address)){
            //连不上就一直连
            while(!channel.finishConnect()){    //nio作为非阻塞式的优势
                System.out.println("Client:连接服务器端的同时，还可以干别的事情");
            }
        }
        //得到一个缓冲区并存入数据
        String msg = "hello server";
        ByteBuffer writeBuf = ByteBuffer.wrap(msg.getBytes());  //使用这种方式就不用调用flip方法
//        ByteBuffer writeBuf = ByteBuffer.allocate(1024);
//        writeBuf.put(msg.getBytes());
//        writeBuf.flip();
        //发送数据
        channel.write(writeBuf);
        System.in.read();
    }
}
