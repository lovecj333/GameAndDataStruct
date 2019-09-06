package com.game.tanknet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class TankServer {

    private static int clientIdStart = 100;
    private static final int TCP_PORT = 8888;
    private static final int UDP_PORT = 6666;
    private List<Client> clients = new ArrayList<>();

    public void start() throws Exception{
        new Thread(new UDPServerThread()).start();
        ServerSocket serverSocket = new ServerSocket(TCP_PORT);
        while(true){
            Socket socket = serverSocket.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String ip = socket.getInetAddress().getHostAddress();
            int udpPort = dis.readInt();
            System.out.println("accept socket client = "+socket+" udpPort = "+udpPort);
            Client client = new Client(ip, udpPort);
            clients.add(client);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeInt(clientIdStart++);
            socket.close();
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

    private class UDPServerThread implements Runnable{

        byte[] buf = new byte[1024];

        @Override
        public void run() {
            try{
                DatagramSocket ds = new DatagramSocket(UDP_PORT);
                System.out.println("UDPServerThread is start");
                while(ds != null){
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    ds.receive(packet);
                    for (int i = 0; i < clients.size(); i++) {
                        Client client = clients.get(i);
                        packet.setSocketAddress(new InetSocketAddress(client.ip, client.udpPort));
                        ds.send(packet);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
