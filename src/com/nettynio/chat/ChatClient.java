package com.nettynio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class ChatClient {

    private final String HOST = "127.0.0.1";
    private final int PORT = 9999;
    private SocketChannel socketChannel;
    private String userName;

    public ChatClient(){
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            InetSocketAddress address = new InetSocketAddress(HOST, PORT);
            if(!socketChannel.connect(address)){
                while(!socketChannel.finishConnect()){  //nio作为非阻塞的优势
                    System.out.println("Client:连接服务器的同时、还可以干别的事情");
                }
            }
            userName = socketChannel.getLocalAddress().toString().substring(1);
            System.out.println("--------------Client("+userName+") is ready---------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //向服务器端发送数据
    public void sendMsg(String msg) throws Exception{
        if(msg.equalsIgnoreCase("bye")){
            socketChannel.close();
            return;
        }
        msg = userName +"说: "+ msg;
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        socketChannel.write(buffer);
    }

    //从服务器端接收数据
    public void receiveMsg() throws Exception{
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int count = socketChannel.read(buffer);
        if(count > 0){
            String msg = new String(buffer.array());
            System.out.println(msg.trim());
        }
    }

    public static void main(String[] args) throws Exception{
        ChatClient chatClient = new ChatClient();
        new Thread(
            () -> {
                try {
                    while(true){
                        chatClient.receiveMsg();
                        Thread.sleep(2000);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        ).start();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String msg = scanner.nextLine();
            chatClient.sendMsg(msg);
        }
    }
}
