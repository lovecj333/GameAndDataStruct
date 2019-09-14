package com.game.tanknet.msg;

import com.game.tanknet.Direction;
import com.game.tanknet.Missile;
import com.game.tanknet.TankClient;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class MissileNewMsg implements Msg{

    int msgType = Msg.MISSILE_NEW_MSG;
    TankClient tc;
    Missile m;

    public MissileNewMsg(TankClient tc){
        this.tc = tc;
    }

    public MissileNewMsg(Missile m){
        this.m = m;
    }

    @Override
    public void send(DatagramSocket ds, String ip, int udpPort) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(msgType);
        dos.writeInt(m.tankId);
        dos.writeInt(m.id);
        dos.writeInt(m.x);
        dos.writeInt(m.y);
        dos.writeBoolean(m.good);
        dos.writeInt(m.dir.ordinal());
        byte[] buf = baos.toByteArray();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, new InetSocketAddress(ip, udpPort));
        ds.send(packet);
    }

    @Override
    public void parse(DataInputStream dis) throws Exception {
        int tankId = dis.readInt();
        if(tankId == tc.myTank.getId()){
            return;
        }
        int id = dis.readInt();
        int x = dis.readInt();
        int y = dis.readInt();
        boolean good = dis.readBoolean();
        Direction dir = Direction.values()[dis.readInt()];
        Missile m = new Missile(tankId, id, x, y, good, dir, tc);
        tc.missiles.add(m);
    }
}
