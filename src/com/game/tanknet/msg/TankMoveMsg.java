package com.game.tanknet.msg;

import com.game.tanknet.Direction;
import com.game.tanknet.Tank;
import com.game.tanknet.TankClient;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TankMoveMsg implements Msg{

    int msgType = Msg.TANK_MOVE_MSG;
    TankClient tc;
    int id;
    Direction dir;

    public TankMoveMsg(TankClient tc){
        this.tc = tc;
    }

    public TankMoveMsg(int id, Direction dir){
        this.id = id;
        this.dir = dir;
    }

    @Override
    public void send(DatagramSocket ds, String ip, int udpPort) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(msgType);
        dos.writeInt(id);
        dos.writeInt(dir.ordinal());
        byte[] buf = baos.toByteArray();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, new InetSocketAddress(ip, udpPort));
        ds.send(packet);
    }

    @Override
    public void parse(DataInputStream dis) throws Exception {
        int id = dis.readInt();
        if(id == tc.myTank.getId()){
            return;
        }
        Direction dir = Direction.values()[dis.readInt()];
        boolean exist = false;
        for (int i = 0; i < tc.enemyTanks.size(); i++) {
            Tank t = tc.enemyTanks.get(i);
            if(t.getId() == id){
                exist = true;
                t.setDir(dir);
                break;
            }
        }
    }
}
