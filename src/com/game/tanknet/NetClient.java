package com.game.tanknet;

import com.game.tanknet.msg.Msg;
import com.game.tanknet.msg.TankMoveMsg;
import com.game.tanknet.msg.TankNewMsg;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

public class NetClient {

    private TankClient tc;
    private int udpPort = 2223;
    private DatagramSocket ds;

    public NetClient(TankClient tc){
        this.tc = tc;
        try {
            this.ds = new DatagramSocket(udpPort);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connectServer(String ip, int port) {
        try {
            new Thread(new UDPClientThread()).start();
            Socket socket = new Socket(ip, port);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeInt(udpPort);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            int tankId = dis.readInt();
            tc.myTank.setId(tankId);
            System.out.println("connect tank server success tankId = "+tankId);
            socket.close();

            TankNewMsg msg = new TankNewMsg(tc.myTank);
            send(msg);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void send(Msg msg){
        try {
            msg.send(ds, "127.0.0.1",6666);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class UDPClientThread implements Runnable{

        byte[] buf = new byte[1024];

        @Override
        public void run() {
            try {
                while(ds != null){
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    ds.receive(packet);
                    parse(packet);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        private void parse(DatagramPacket packet) throws Exception{
            ByteArrayInputStream bais = new ByteArrayInputStream(buf, 0, packet.getLength());
            DataInputStream dis = new DataInputStream(bais);
            Msg msg = null;
            int msgType = dis.readInt();
            switch (msgType){
                case Msg.TANK_NEW_MSG:
                    msg = new TankNewMsg(tc);
                    break;
                case Msg.TANK_MOVE_MSG:
                    msg = new TankMoveMsg(tc);
                    break;
            }
            msg.parse(dis);
        }
    }
}
