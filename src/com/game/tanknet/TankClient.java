package com.game.tanknet;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TankClient extends Frame {

    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    Image offScreenImage = null;
    public Tank myTank = new Tank(50, 50, true, Direction.STOP,this);
    public List<Tank> enemyTanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();
    public List<Missile> missiles = new ArrayList<>();
    public NetClient netClient = new NetClient(this);
    ConnDialog connDialog = new ConnDialog();

    public void lauchFrame(){
        this.setLocation(400,200);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setTitle("TankWarNet");
        this.setBackground(Color.GREEN);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);
        this.setVisible(true);
        connDialog.setVisible(true);
        this.addKeyListener(new KeyMonitor());
        new Thread(new PaintThread()).start();
    }

    @Override
    public void paint(Graphics g) {
        g.drawString("missiles count : "+missiles.size(),10,50);
        g.drawString("explodes count : "+explodes.size(),10,70);
        for(int i = 0; i < missiles.size(); i++){
            Missile m = missiles.get(i);
            m.hitTank(myTank);
            m.hitTanks(enemyTanks);
            m.draw(g);
        }
        for(int i = 0; i < explodes.size(); i++){
            Explode e = explodes.get(i);
            e.draw(g);
        }
        for(int i = 0; i < enemyTanks.size(); i++){
            Tank enemyTank = enemyTanks.get(i);
            enemyTank.draw(g);
        }
        myTank.draw(g);
    }

    @Override
    public void update(Graphics g) {
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gImage = offScreenImage.getGraphics();
        Color c = gImage.getColor();
        gImage.setColor(Color.GREEN);
        gImage.fillRect(0,0, GAME_WIDTH, GAME_HEIGHT);
        gImage.setColor(c);
        paint(gImage);
        g.drawImage(offScreenImage, 0,0, null);
    }

    private class PaintThread implements Runnable{
        @Override
        public void run() {
            while(true){
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class KeyMonitor extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }
    }

    private class ConnDialog extends Dialog{
        public ConnDialog(){
            super(TankClient.this, true);
            TextField textIp = new TextField("127.0.0.1",12);
            TextField textPort = new TextField("8888",5);
            TextField textUdpPort = new TextField("2223",5);
            Button button = new Button("确定");
            this.setLayout(new FlowLayout());
            this.add(new Label("IP:"));
            this.add(textIp);
            this.add(new Label("PORT:"));
            this.add(textPort);
            this.add(new Label("UDP PORT"));
            this.add(textUdpPort);
            this.add(button);
            this.pack();
            this.setLocation(500,500);
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    setVisible(false);
                }
            });
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String ip = textIp.getText();
                    int port = Integer.parseInt(textPort.getText());
                    int udpPort = Integer.parseInt(textUdpPort.getText());
                    netClient.connectServer(ip, port, udpPort);
                    setVisible(false);
                }
            });
        }
    }

    public static void main(String[] args) {
        TankClient tc = new TankClient();
        tc.lauchFrame();
    }
}
