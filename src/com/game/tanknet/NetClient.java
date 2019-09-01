package com.game.tanknet;

import java.io.DataOutputStream;
import java.net.Socket;

public class NetClient {

    public void connectServer(String ip, int port) {
        try {
            Socket socket = new Socket(ip, port);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeInt(2223);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
