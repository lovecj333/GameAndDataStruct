package com.game.tanknet;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TankServer {

    private static final int TCP_PORT = 8888;
    private List<Client> clients = new ArrayList<>();

    public void start() throws Exception{
        ServerSocket serverSocket = new ServerSocket(TCP_PORT);
        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("accept socket client"+socket);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String ip = socket.getInetAddress().getHostAddress();
            int udpPort = dis.readInt();
            Client client = new Client(ip, udpPort);
            clients.add(client);
        }
    }

    public static void main(String[] args) throws Exception{
        new TankServer().start();
    }

    private class Client {
        String ip;
        int udpPort;

        public Client(String ip, int udpPort){
            this.ip = ip;
            this.udpPort = udpPort;
        }
    }
}
