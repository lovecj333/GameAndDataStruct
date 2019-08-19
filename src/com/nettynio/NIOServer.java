package com.nettynio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {

    public static void main(String[] args) throws Exception {
        //得到一个ServerSocketChannel对象
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //得到一个Selector对象
        Selector selector = Selector.open();
        //绑定一个端口号
        serverSocketChannel.bind(new InetSocketAddress(9999));
        //设置非阻塞方式
        serverSocketChannel.configureBlocking(false);
        //ServerSocketChannel对象注册到Selector对象
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //开始工作
        while(true){
            //监控客户端
            if(selector.select(2000) == 0){ //nio作为非阻塞式的优势
                System.out.println("Server:等待客户端各种事件的同时，还可以干别的事情");
                continue;
            }
            //得到SelectionKey判断通道里的事件
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while(keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                if(key.isAcceptable()){ //客户端连接事件
                    System.out.println("OP_ACCEPT");
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }else if(key.isReadable()){ //读取客户端数据的事件
                    SocketChannel socketChannel = (SocketChannel)key.channel();
                    ByteBuffer buffer = (ByteBuffer)key.attachment();//这里得到的对象就是给此通道加监听时的第三个参数
                    socketChannel.read(buffer);
                    System.out.println("客户端发来数据: "+new String(buffer.array()));
                }
                //删除当前的key防止重复处理
                keyIterator.remove();
            }
        }
    }
}
