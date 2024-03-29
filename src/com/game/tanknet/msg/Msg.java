package com.game.tanknet.msg;

import java.io.DataInputStream;
import java.net.DatagramSocket;

public interface Msg {

    int TANK_NEW_MSG = 1;
    int TANK_MOVE_MSG = 2;
    int TANK_SYNC_MSG = 3;
    int MISSILE_NEW_MSG = 4;

    void send(DatagramSocket ds, String ip, int udpPort) throws Exception;

    void parse(DataInputStream dis) throws Exception;
}
