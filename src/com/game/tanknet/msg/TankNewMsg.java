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

public class TankNewMsg implements Msg{

    int msgType = Msg.TANK_NEW_MSG;
    TankClient tc;
    Tank tank;

    public TankNewMsg(TankClient tc){
        this.tc = tc;
    }

    public TankNewMsg(Tank tank){
        this.tank = tank;
    }

    @Override
    public void send(DatagramSocket ds, String ip, int udpPort) throws Exception{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(msgType);
        dos.writeInt(tank.getId());
        dos.writeInt(tank.getX());
        dos.writeInt(tank.getY());
        dos.writeInt(tank.getDir().ordinal());
        dos.writeBoolean(tank.isGood());
        byte[] buf = baos.toByteArray();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, new InetSocketAddress(ip, udpPort));
        ds.send(packet);
    }

    @Override
    public void parse(DataInputStream dis) throws Exception{
        int id = dis.readInt();
        if(id == tc.myTank.getId()){
            return;
        }
        int x = dis.readInt();
        int y = dis.readInt();
        Direction dir = Direction.values()[dis.readInt()];
        boolean good = dis.readBoolean();
        Tank t = new Tank(x, y, good, dir, tc);
        t.setId(id);
        tc.enemyTanks.add(t);
        TankSyncMsg tankSyncMsg = new TankSyncMsg(tc.myTank);
        tc.netClient.send(tankSyncMsg);
    }
}
