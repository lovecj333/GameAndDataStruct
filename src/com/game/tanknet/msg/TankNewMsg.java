package com.game.tanknet.msg;

import com.game.tanknet.Direction;
import com.game.tanknet.Tank;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TankNewMsg {

    Tank tank;

    public TankNewMsg(){

    }

    public TankNewMsg(Tank tank){
        this.tank = tank;
    }

    public void send(DatagramSocket ds, String ip, int udpPort) throws Exception{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(tank.getId());
        dos.writeInt(tank.getX());
        dos.writeInt(tank.getY());
        dos.writeInt(tank.getDir().ordinal());
        dos.writeBoolean(tank.isGood());
        byte[] buf = baos.toByteArray();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, new InetSocketAddress(ip, udpPort));
        ds.send(packet);
    }

    public void parse(DataInputStream dis) throws Exception{
        int id = dis.readInt();
        int x = dis.readInt();
        int y = dis.readInt();
        Direction dir = Direction.values()[dis.readInt()];
        boolean good = dis.readBoolean();
        System.out.println("id:"+id+" x:"+x+" y:"+y+" dir:"+dir+" good:"+good);
    }
}
