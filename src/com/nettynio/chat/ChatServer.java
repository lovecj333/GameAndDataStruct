package com.nettynio.chat;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class ChatServer {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private static final int PORT = 9999;

    public ChatServer(){
        try {
            serverSocketChannel = ServerSocketChannel.open();
            selector = Selector.open();
            serverSocketChannel.bind(new InetSocketAddress(PORT));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            printInfo("Chat Server is ready......");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(){
        try {
            while(true) {
                if (selector.select(2000) == 0) {
                    printInfo("Server:没有客户端找我、我就干别的事情");
                    continue;
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if(key.isAcceptable()){ //连接请求事件
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        printInfo(socketChannel.getRemoteAddress().toString().substring(1)+"上线了...");
                    }else if(key.isReadable()){ //读取数据事件
                        readMsg(key);
                    }
                    iterator.remove();  //删除当前的key防止重复处理
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //读取客户端发来的消息广播出去
    private void readMsg(SelectionKey key) throws Exception{
        SocketChannel socketChannel = (SocketChannel)key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int count = socketChannel.read(buffer);
        if(count > 0){
            String msg = new String(buffer.array());
            printInfo(msg);
            broadCast(socketChannel, msg);
        }
    }

    //给所有的客户端发广播
    private void broadCast(SocketChannel exceptChannel, String msg) throws Exception{
        //得到所有的通道
        Iterator<SelectionKey> iterator = selector.keys().iterator();
        while(iterator.hasNext()){
            SelectionKey key = iterator.next();
            SelectableChannel channel = key.channel();
            if(channel instanceof SocketChannel){
                SocketChannel targetChannel = (SocketChannel)channel;
                if(targetChannel != exceptChannel){
                    ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                    targetChannel.write(buffer);
                }
            }
        }
    }

    private void printInfo(String str){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("["+sdf.format(new Date())+"] -> "+str);
    }

    public static void main(String[] args) {
        new ChatServer().start();
    }
}
